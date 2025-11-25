package br.com.forum_api.forum_api.service

import br.com.forum_api.forum_api.model.Curso
import br.com.forum_api.forum_api.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(
    private val cursoRepository: CursoRepository
) {

    fun buscarPorId(id: Long): Curso {
        return cursoRepository.getOne(id)
    }
}
