package br.com.forum_api.forum_api.repository

import br.com.forum_api.forum_api.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
}