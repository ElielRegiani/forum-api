package br.com.forum_api.forum_api.controller

import br.com.forum_api.forum_api.model.Resposta
import br.com.forum_api.forum_api.service.RespostaService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/respostas")
@RestController
class RespostaController(
    private val respostaService: RespostaService
)  {

    @PostMapping
    fun salvar(@RequestBody @Valid resposta: Resposta) = respostaService.salvar(resposta)
}