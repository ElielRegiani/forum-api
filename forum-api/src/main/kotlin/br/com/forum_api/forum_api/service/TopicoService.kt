package br.com.forum_api.forum_api.service

import br.com.forum_api.forum_api.dto.AtualizacaoTopicoForm
import br.com.forum_api.forum_api.dto.NovoTopicoForm
import br.com.forum_api.forum_api.dto.TopicoView
import br.com.forum_api.forum_api.exception.NotFoundException
import br.com.forum_api.forum_api.mapper.TopicoFormMapper
import br.com.forum_api.forum_api.mapper.TopicoViewMapper
import br.com.forum_api.forum_api.repository.TopicoRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private val topicoRepository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado!"
) {

    fun listar(): List<TopicoView> {
        return topicoRepository.findAll().stream().map { t -> topicoViewMapper.map(t) }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicoRepository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(novoTopicoForm: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(novoTopicoForm)
        topicoRepository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = topicoRepository.findById(form.id).orElseThrow{NotFoundException(notFoundMessage)}
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        topicoRepository.deleteById(id)
    }
}