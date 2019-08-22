package br.com.boomerang.packbackapp.view.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.boomerang.packbackapp.repository.UsuarioRepository
import br.com.boomerang.packbackapp.repository.local.AppDatabase
import br.com.boomerang.packbackapp.repository.web.PackbackService
import br.com.boomerang.packbackapp.repository.web.UsuarioService

class PerfilUsuarioViewModelFactory(
    private val context: Context,
    private val id: Long
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val service = PackbackService().cria(UsuarioService::class.java)
                ?: throw IllegalArgumentException("Não foi possível instanciar o serviço")

        val dao = AppDatabase.getInstance(context).usuarioDao()

        val repo = UsuarioRepository(service, dao)

        val viewModel = PerfilUsuarioViewModel(id, repo)

        return viewModel as T
    }
}