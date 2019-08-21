package br.com.boomerang.packbackapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.boomerang.packbackapp.R
import br.com.boomerang.packbackapp.domain.Usuario
import br.com.boomerang.packbackapp.repository.UsuarioRepository
import br.com.boomerang.packbackapp.repository.local.AppDatabase
import br.com.boomerang.packbackapp.repository.web.PackbackService
import br.com.boomerang.packbackapp.repository.web.UsuarioService
import br.com.boomerang.packbackapp.view.model.PerfilUsuarioViewModel
import kotlinx.android.synthetic.main.activity_perfil_usuario.*

class PerfilUsuarioActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "PerfilUsuarioActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)

        val service = PackbackService().cria(UsuarioService::class.java)
                ?: throw IllegalArgumentException("Não foi possível instanciar o serviço")

        val dao = AppDatabase.getInstance(this).usuarioDao()

        val repo = UsuarioRepository(service, dao)

        val viewModel = PerfilUsuarioViewModel(1, repo)

        viewModel.usuario.observe(this, Observer<Usuario> { usuario ->
            usuario_nome.text = usuario.nome
        })

        AppDatabase.destroyInstance()
    }
}
