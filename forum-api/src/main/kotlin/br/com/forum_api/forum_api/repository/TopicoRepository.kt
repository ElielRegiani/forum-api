package br.com.forum_api.forum_api.repository

import br.com.forum_api.forum_api.model.Topico
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicoRepository: JpaRepository<Topico, Long> {

}