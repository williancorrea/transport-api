package br.com.wcorrea.transport.api.repository.TipoRelacionamento;

import br.com.wcorrea.transport.api.model.pessoa.TipoRelacionamento;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class TipoRelacionamentoRepositoryImpl implements TipoRelacionamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Find all TypeRelationships paginated
     *
     * @param tipoRelacionamentoFilter
     * @param pageable
     * @return
     */
    @Override
    public Page<TipoRelacionamento> findAll(TipoRelacionamentoFilter tipoRelacionamentoFilter, Pageable pageable) {
        TypedQuery<TipoRelacionamento> queryList = manager.createQuery(this.createQuery(tipoRelacionamentoFilter, false), TipoRelacionamento.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(tipoRelacionamentoFilter, true), Long.class);

        UtilsRepository.adicionarRestricoesPaginacao(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(TipoRelacionamentoFilter tipoRelacionamentoFilter, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from tipo_relacionamento a where 1=1 ";
        } else {
            sql = "from tipo_relacionamento a where 1=1 ";
        }

        if (StringUtils.isNotBlank(tipoRelacionamentoFilter.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.descricao) like '%" + tipoRelacionamentoFilter.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " or upper(a.nome) like '%" + tipoRelacionamentoFilter.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " or upper(a.codigo) like '%" + tipoRelacionamentoFilter.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(tipoRelacionamentoFilter.getDescricao())) {
                sql += " and upper(a.descricao) like '%" + tipoRelacionamentoFilter.getDescricao().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(tipoRelacionamentoFilter.getNome())) {
                sql += " and upper(a.nome) like '%" + tipoRelacionamentoFilter.getNome().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(tipoRelacionamentoFilter.getCodigo())) {
                sql += " and upper(a.codigo) like '%" + tipoRelacionamentoFilter.getCodigo().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(tipoRelacionamentoFilter.getCampoOrdenacao())) {
                sql += " order by a." + tipoRelacionamentoFilter.getCampoOrdenacao();
            }
            if (StringUtils.isNotBlank(tipoRelacionamentoFilter.getCampoOrdenacao()) && StringUtils.isNotBlank(tipoRelacionamentoFilter.getOrdemClassificacao())) {
                sql += " " + tipoRelacionamentoFilter.getOrdemClassificacao();
            }
        }
        return sql;
    }


}
