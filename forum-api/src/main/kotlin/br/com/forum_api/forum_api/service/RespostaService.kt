package br.com.forum_api.forum_api.service

import br.com.forum_api.forum_api.model.Resposta
import br.com.forum_api.forum_api.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val respostaRepository: RespostaRepository,
    private val emailService: EmailService

)  {
    fun salvar(resposta: Resposta) {
        respostaRepository.save(resposta)

        val emailAutor = resposta.autor.email
        emailService.notificar(emailAutor)
    }
}