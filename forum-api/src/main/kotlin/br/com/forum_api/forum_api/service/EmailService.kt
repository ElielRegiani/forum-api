package br.com.forum_api.forum_api.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Service
import org.springframework.mail.javamail.JavaMailSender

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
)  {
    fun notificar(emailAutor: String) {
        val message = SimpleMailMessage()

        message.setSubject("[Alura] Resposta Recebida")
        message.setText("Olá, o seu tópico foi respondido. Vamos lá conferir?")
        message.setTo(emailAutor)

        javaMailSender.send(message)
    }
}