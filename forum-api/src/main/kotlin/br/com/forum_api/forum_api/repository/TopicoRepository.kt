package br.com.forum_api.forum_api.repository

import br.com.forum_api.forum_api.dto.TopicoPorCategoriaDTO
import br.com.forum_api.forum_api.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TopicoRepository: JpaRepository<Topico, Long> {

    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<Topico>

    @Query("""
    SELECT new br.com.forum_api.forum_api.dto.TopicoPorCategoriaDTO(c.categoria, COUNT(t))
    FROM Topico t
    JOIN t.curso c
    GROUP BY c.categoria
""")
    fun relatorio(): List<TopicoPorCategoriaDTO>
}