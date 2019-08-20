package boomerang.com.br.packbackapp.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import boomerang.com.br.packbackapp.domain.Usuario
import boomerang.com.br.packbackapp.repository.UsuarioRepository

class PerfilUsuarioViewModel (
        savedStateHandle: SavedStateHandle,
        repositorio: UsuarioRepository
): ViewModel() {
    val id : Long = savedStateHandle["uid"] ?: throw IllegalArgumentException("id não encontrado")
    val usuario : LiveData<Usuario> = repositorio.getUsuario(id)
}