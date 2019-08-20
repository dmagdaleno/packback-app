package br.com.boomerang.packbackapp.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import br.com.boomerang.packbackapp.domain.Usuario
import br.com.boomerang.packbackapp.repository.UsuarioRepository

class PerfilUsuarioViewModel (
        savedStateHandle: SavedStateHandle,
        repositorio: UsuarioRepository
): ViewModel() {
    val id : Long = savedStateHandle["uid"] ?: throw IllegalArgumentException("id não encontrado")
    val usuario : LiveData<Usuario> = repositorio.getUsuario(id)
}