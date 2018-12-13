package br.com.wcorrea.transport.api.repository.itinerario;

import br.com.wcorrea.transport.api.model.Itinerario;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ItinerarioRepositoryImpl implements ItinerarioRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * CARREGA TODOS OS REGISTROS DE FORMA PAGINADA
     *
     * @param filtro
     * @param paginacao
     * @return
     */
    @Override
    public Page<Itinerario> findAll(ItinerarioFiltro filtro, Pageable paginacao) {
        TypedQuery<Itinerario> queryList = manager.createQuery(this.createQuery(filtro, false), Itinerario.class);
        TypedQuery<Long> totalRegistros = manager.createQuery(this.createQuery(filtro, true), Long.class);

        UtilsRepository.adicionarRestricoesPaginacao(queryList, paginacao);
        return new PageImpl<>(queryList.getResultList(), paginacao, totalRegistros.getSingleResult());
    }

    /**
     * Cria as consultas da aplicação
     *
     * @param filtro
     * @param count
     * @return
     */
    private String createQuery(ItinerarioFiltro filtro, boolean count) {

        filtro.setFiltroGlobal(UtilsRepository.removeCaracteresProblematicos(filtro.getFiltroGlobal()));
        filtro.setCodigo(UtilsRepository.removeCaracteresProblematicos(filtro.getCodigo()));
        filtro.setNome(UtilsRepository.removeCaracteresProblematicos(filtro.getNome()));
        filtro.setDescricao(UtilsRepository.removeCaracteresProblematicos(filtro.getDescricao()));

        String sql;
        if (count) {
            sql = "select count(a) from itinerario a where 1=1 ";
        } else {
            sql = "from itinerario a where 1=1 ";
        }

        if (StringUtils.isNotBlank(filtro.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.nome) like '%" + filtro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " or upper(a.codigo) like '%" + filtro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " or upper(a.descricao) like '%" + filtro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(filtro.getNome())) {
                sql += " and upper(a.nome) like '%" + filtro.getNome().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(filtro.getCodigo())) {
                sql += " and upper(a.codigo) like '%" + filtro.getCodigo().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(filtro.getDescricao())) {
                sql += " and upper(a.descricao) like '%" + filtro.getDescricao().toUpperCase().trim() + "%'";
            }
        }

        sql = UtilsRepository.adicionarOrdenacaoConsulta(sql, count, filtro.getCampoOrdenacao(), filtro.getOrdemClassificacao());
        return sql;
    }

}
