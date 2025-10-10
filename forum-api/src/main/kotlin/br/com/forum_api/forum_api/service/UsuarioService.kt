package br.com.forum_api.forum_api.service

import br.com.forum_api.forum_api.model.Usuario
import br.com.forum_api.forum_api.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository): UserDetailsService {

    fun buscarPorId(id: Long): Usuario {
        return usuarioRepository.getOne(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = usuarioRepository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(usuario)
    }
}
