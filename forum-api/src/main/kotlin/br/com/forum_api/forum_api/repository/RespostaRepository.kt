package br.com.forum_api.forum_api.repository

import br.com.forum_api.forum_api.model.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository: JpaRepository<Resposta, Long>