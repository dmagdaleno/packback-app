package br.com.boomerang.packbackapp.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.boomerang.packbackapp.R
import br.com.boomerang.packbackapp.domain.Login
import br.com.boomerang.packbackapp.domain.Usuario
import br.com.boomerang.packbackapp.repository.web.LoginService
import br.com.boomerang.packbackapp.repository.web.PackbackService
import br.com.boomerang.packbackapp.ui.Keys
import br.com.boomerang.packbackapp.ui.Keys.Companion.PERMISSION_REQUEST_COARSE_LOCATION
import br.com.boomerang.packbackapp.util.funcionalidadeIndisponivel
import br.com.boomerang.packbackapp.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalArgumentException

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LoginActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_entrar_btn.setOnClickListener {
            val email = login_email.text.toString()
            val senha = login_senha.text.toString()
            val login = Login(email, senha)

            Log.d(TAG, login.toString())

            PackbackService().cria(LoginService::class.java)?.let { service ->
                autentica(service, login)
            }
        }

        login_esq_senha.setOnClickListener {
            funcionalidadeIndisponivel(this)
        }

        login_cadastrar_btn.setOnClickListener {
            funcionalidadeIndisponivel(this)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Acesso a localização")
                builder.setMessage("Este aplicativo utiliza sua localização para sugerir opções de reciclagem.")
                builder.setPositiveButton(android.R.string.ok, null)
                builder.setOnDismissListener {
                    requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                            PERMISSION_REQUEST_COARSE_LOCATION)
                }
                builder.show()
            }
        }

    }

    private fun autentica(service: LoginService, login: Login) {
        service.autentica(login).enqueue(object : Callback<Usuario> {

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Log.e(TAG, "Erro na autenticação com o email ${login.email}", t)
            }

            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.body() != null) {
                    val usuario = response.body()!!
                    Log.d(TAG, "Autenticação realizada com sucesso: ${usuario.nome}")
                    abreDashboard(usuario)
                }
                else {
                    val msg = "Erro ao tentar efetuar login com o email ${login.email}"
                    Log.d(TAG, msg)
                    toast(this@LoginActivity, msg)
                }
            }

        })
    }

    private fun abreDashboard(usuario: Usuario) {
        Log.d(TAG, "Abrindo Dashboard para o usuário ${usuario.nome}")
        val intent = Intent(this@LoginActivity, PerfilUsuarioActivity::class.java)
                .apply { putExtra(Keys.ID_USUARIO, usuario.id) }

        startActivity(intent)
    }
}
