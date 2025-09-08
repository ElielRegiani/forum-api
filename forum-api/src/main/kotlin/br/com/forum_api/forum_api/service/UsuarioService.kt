package br.com.forum_api.forum_api.service

import br.com.forum_api.forum_api.model.Usuario
import br.com.forum_api.forum_api.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository) {

    fun buscarPorId(id: Long): Usuario {
        return usuarioRepository.getOne(id)
    }
}
