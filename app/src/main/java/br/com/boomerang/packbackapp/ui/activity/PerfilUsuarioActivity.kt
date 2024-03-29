package br.com.boomerang.packbackapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.boomerang.packbackapp.R
import br.com.boomerang.packbackapp.domain.Usuario
import br.com.boomerang.packbackapp.ui.Keys
import br.com.boomerang.packbackapp.view.model.PerfilUsuarioViewModel
import br.com.boomerang.packbackapp.view.model.PerfilUsuarioViewModelFactory
import kotlinx.android.synthetic.main.activity_perfil_usuario.*
import java.text.SimpleDateFormat

class PerfilUsuarioActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "PerfilUsuarioActivity"
        
        @SuppressLint("SimpleDateFormat")
        private val formatador = SimpleDateFormat("dd/MM/yyyy")
    }

    private val idUsuario: Long by lazy { intent.extras.getLong(Keys.ID_USUARIO) }

    private val viewModel: PerfilUsuarioViewModel by viewModels {
        PerfilUsuarioViewModelFactory(this, idUsuario)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)

        viewModel.usuario.observe(this, Observer<Usuario> { usuario ->
            usuario?.let {
                Log.d(TAG, "Atualiza informações do usuário $it")
                perfil_usuario_nome.text = it.nome
                val dataCadastro = "Coletando desde ${formatador.format(it.dataCadastro)}"
                perfil_usuario_data_inicio.text = dataCadastro
                perfil_usuario_coletas.text = it.totalColetas.toString()
                perfil_usuario_materiais.text = it.getPesoColetasFormatado()
            }
        })

        perfil_usuario_todas_coletas_ico.setOnClickListener {
            abreListaDeColetas(idUsuario)
        }

        perfil_usuario_solicitar_btn.setOnClickListener {
            abreFormularioSolicitacaoColeta(idUsuario)
        }

        perfil_usuario_buscar_btn.setOnClickListener {
            listaProdutosPorRegiao()
        }
    }

    private fun abreFormularioSolicitacaoColeta(idUsuario: Long) {
        Log.d(TAG, "Abrindo formulario de nova coleta para o usuário $idUsuario")
        val intent = Intent(this@PerfilUsuarioActivity, NovaColetaActivity::class.java)
                .apply { putExtra(Keys.ID_USUARIO, idUsuario) }

        startActivity(intent)
    }

    private fun abreListaDeColetas(idUsuario: Long) {
        Log.d(TAG, "Abrindo lista de coletas para o usuário $idUsuario")
        val intent = Intent(this@PerfilUsuarioActivity, ListaColetaActivity::class.java)
                .apply { putExtra(Keys.ID_USUARIO, idUsuario) }

        startActivity(intent)
    }

    private fun listaProdutosPorRegiao() {
        Log.d(TAG, "Abrindo lista de embalagens por região")
        startActivity(Intent(this@PerfilUsuarioActivity, ListaProdutoActivity::class.java))
    }
}
