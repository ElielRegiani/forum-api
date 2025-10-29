package br.com.forum_api.forum_api.service

import br.com.forum_api.forum_api.mapper.TopicoFormMapper
import br.com.forum_api.forum_api.mapper.TopicoViewMapper
import br.com.forum_api.forum_api.model.TopicoTest
import br.com.forum_api.forum_api.model.TopicoViewTest
import br.com.forum_api.forum_api.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import kotlin.test.Test

class TopicoServiceTest {

    val topicos = PageImpl(listOf(TopicoTest.build()))

    val paginacao: Pageable = mockk()

    val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
    }

    val topicoViewMapper: TopicoViewMapper = mockk()

    val topicoFormMapper: TopicoFormMapper = mockk()

    val topicoService = TopicoService(
        topicoRepository,topicoViewMapper,topicoFormMapper
    )

    @Test
    fun `deve listar topicos a partir do nome do curso`() {
        every { topicoViewMapper.map(any()) } returns TopicoViewTest.build()
        topicoService.listar("Kotlin Avancado", paginacao)

        verify (exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify (exactly = 0) { topicoRepository.findAll(paginacao) }
        verify (exactly = 1) { topicoViewMapper.map(any()) }
    }
}