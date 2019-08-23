package br.com.boomerang.packbackapp.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.boomerang.packbackapp.R
import br.com.boomerang.packbackapp.domain.Usuario
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

    private val viewModel: PerfilUsuarioViewModel by viewModels {
        PerfilUsuarioViewModelFactory(this, 1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)

        viewModel.usuario.observe(this, Observer<Usuario> { usuario ->
            usuario?.let {
                perfil_usuario_nome.text = it.nome
                val dataCadastro = "Coletando desde ${formatador.format(it.dataCadastro)}"
                perfil_usuario_data_inicio.text = dataCadastro
            }
        })
    }
}
