package br.com.forum_api.forum_api.controller

import br.com.forum_api.forum_api.model.Curso
import br.com.forum_api.forum_api.model.Topico
import br.com.forum_api.forum_api.model.Usuario
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topicos")
class TopicoController {

    @GetMapping
    fun listar(): List<Topico> {
        val topico = Topico(
            id = 1,
            titulo = "titulo",
            mensagem = "mensagem",
            curso = Curso(
                id = 1,
                nome = "nome",
                categoria = "categoria",

            ),
            autor = Usuario(
                id = 1,
                nome = "nome",
                email = "email"
            )
        )
        return listOf(topico, topico, topico)
    }
}