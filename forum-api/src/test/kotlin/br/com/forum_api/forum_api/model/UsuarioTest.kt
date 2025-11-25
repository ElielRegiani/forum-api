package br.com.forum_api.forum_api.model

object UsuarioTest {
    fun build() = Usuario(
        id = 1,
        nome = "Joao",
        email = "email@email.com",
        password = "password",
    )
}
