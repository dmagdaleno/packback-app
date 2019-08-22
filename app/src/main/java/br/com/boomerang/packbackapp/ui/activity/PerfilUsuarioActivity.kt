package br.com.boomerang.packbackapp.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.boomerang.packbackapp.R
import br.com.boomerang.packbackapp.domain.Usuario
import br.com.boomerang.packbackapp.repository.local.AppDatabase
import br.com.boomerang.packbackapp.view.model.PerfilUsuarioViewModel
import br.com.boomerang.packbackapp.view.model.PerfilUsuarioViewModelFactory
import kotlinx.android.synthetic.main.activity_perfil_usuario.*

class PerfilUsuarioActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "PerfilUsuarioActivity"
    }

    private val viewModel: PerfilUsuarioViewModel by viewModels {
        PerfilUsuarioViewModelFactory(this, 1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)

        viewModel.usuario.observe(this, Observer<Usuario> { usuario ->
            usuario_nome.text = usuario?.nome
        })
    }
}
