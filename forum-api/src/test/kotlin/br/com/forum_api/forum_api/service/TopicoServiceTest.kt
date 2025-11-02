package br.com.forum_api.forum_api.service

import br.com.forum_api.forum_api.exception.NotFoundException
import br.com.forum_api.forum_api.mapper.TopicoFormMapper
import br.com.forum_api.forum_api.mapper.TopicoViewMapper
import br.com.forum_api.forum_api.model.TopicoTest
import br.com.forum_api.forum_api.model.TopicoViewTest
import br.com.forum_api.forum_api.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*
import kotlin.test.Test

class TopicoServiceTest {

    val topicos = PageImpl(listOf(TopicoTest.build()))

    val paginacao: Pageable = mockk()

    val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
        every { findAll(paginacao) } returns topicos
    }

    val topicoViewMapper: TopicoViewMapper = mockk {
        every { map(any()) } returns TopicoViewTest.build()
    }

    val topicoFormMapper: TopicoFormMapper = mockk()

    val topicoService = TopicoService(
        topicoRepository,topicoViewMapper,topicoFormMapper
    )

    @Test
    fun `deve listar topicos a partir do nome do curso`() {

        topicoService.listar("Kotlin Avancado", paginacao)

        verify (exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify (exactly = 0) { topicoRepository.findAll(paginacao) }
        verify (exactly = 1) { topicoViewMapper.map(any()) }
    }

    @Test
    fun `deve listar todos os topicos quando o nome do curso for nulo`() {

        topicoService.listar(null, paginacao)

        verify (exactly = 0) { topicoRepository.findByCursoNome(any(), any()) }
        verify (exactly = 1) { topicoRepository.findAll(paginacao) }
        verify (exactly = 1) { topicoViewMapper.map(any()) }
    }

    @Test
    fun `deve listar not found exception quando tópico não for encontrado`() {

        every { topicoRepository.findById(any()) } returns Optional.empty()

        val atual = assertThrows<NotFoundException> {
            topicoService.buscarPorId(1)
        }

        assertThat(atual.message).isEqualTo("Tópico não encontrado!")
    }
}