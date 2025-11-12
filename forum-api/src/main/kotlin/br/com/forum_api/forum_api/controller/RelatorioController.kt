package br.com.forum_api.forum_api.controller

import br.com.forum_api.forum_api.dto.TopicoPorCategoriaDTO
import br.com.forum_api.forum_api.service.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("relatorios")
class RelatorioController (
    private val topicoService: TopicoService

){

    @GetMapping
    fun relatorio(): List<TopicoPorCategoriaDTO> {
        return topicoService.relatorio()
    }
}