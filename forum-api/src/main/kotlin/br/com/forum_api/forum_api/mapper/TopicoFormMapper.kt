package br.com.forum_api.forum_api.mapper

import br.com.forum_api.forum_api.dto.NovoTopicoForm
import br.com.forum_api.forum_api.model.Topico
import br.com.forum_api.forum_api.service.CursoService
import br.com.forum_api.forum_api.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val cursoService: CursoService,
    private val autorService: UsuarioService
): Mapper<NovoTopicoForm, Topico> {

    override fun map(t: NovoTopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = autorService.buscarPorId(t.idAutor)
        )
    }
}