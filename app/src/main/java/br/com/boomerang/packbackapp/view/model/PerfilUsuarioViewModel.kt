package br.com.boomerang.packbackapp.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.boomerang.packbackapp.domain.Usuario
import br.com.boomerang.packbackapp.repository.UsuarioRepository

class PerfilUsuarioViewModel (val id: Long, repositorio: UsuarioRepository): ViewModel() {

    val usuario : LiveData<Usuario> = repositorio.getUsuario(id)
}