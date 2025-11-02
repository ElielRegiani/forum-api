package br.com.forum_api.forum_api.integration

import br.com.forum_api.forum_api.dto.TopicoPorCategoriaDTO
import br.com.forum_api.forum_api.model.TopicoTest
import br.com.forum_api.forum_api.repository.TopicoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicoRepositoryTest {

    @Autowired
    private lateinit var topicoRepository: TopicoRepository

    private val topico = TopicoTest.build()

    companion object {
        @Container
        private val mysqlcontainer = MySQLContainer<Nothing>("mysql:8.0.28").apply {
            withDatabaseName("testedb")
            withUsername("joao")
            withPassword("123456")
        }

        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlcontainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlcontainer::getPassword)
            registry.add("spring.datasource.username", mysqlcontainer::getUsername)
        }
    }

    @Test
    fun `deve gerar um relatorio`() {
        topicoRepository.save(topico)
        val relatorio = topicoRepository.relatorio()

        assertThat(relatorio).isNotNull
        assertThat(relatorio.first()).isExactlyInstanceOf(TopicoPorCategoriaDTO::class.java)
    }

    @Test
    fun `deve listar topico pelo nome do curso`() {
        topicoRepository.save(topico)
        val topico = topicoRepository.findByCursoNome(topico.curso.nome, PageRequest.of(0, 5))

        assertThat(topico).isNotNull
    }
}