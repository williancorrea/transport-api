package br.com.wcorrea.transport.api.repository.UnidadeMedida;

import br.com.wcorrea.transport.api.model.UnidadeMedida;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class UnidadeMedidaRepositoryImpl implements UnidadeMedidaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Find all ProductUnits paginated
     *
     * @param unidadeMedidaFilter
     * @param pageable
     * @return
     */
    @Override
    public Page<UnidadeMedida> findAll(UnidadeMedidaFilter unidadeMedidaFilter, Pageable pageable) {
        TypedQuery<UnidadeMedida> queryList = manager.createQuery(this.createQuery(unidadeMedidaFilter, false), UnidadeMedida.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(unidadeMedidaFilter, true), Long.class);

        UtilsRepository.adicionarRestricoesPaginacao(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(UnidadeMedidaFilter UnidadeMedidaFilter, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from unidade_medida a where 1=1 ";
        } else {
            sql = "from unidade_medida a where 1=1 ";
        }

        if (StringUtils.isNotBlank(UnidadeMedidaFilter.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.sigla) like '%" + UnidadeMedidaFilter.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " or upper(a.nome) like '%" + UnidadeMedidaFilter.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(UnidadeMedidaFilter.getSigla())) {
                sql += " and upper(a.sigla) like '%" + UnidadeMedidaFilter.getSigla().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(UnidadeMedidaFilter.getNome())) {
                sql += " and upper(a.nome) like '%" + UnidadeMedidaFilter.getNome().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(UnidadeMedidaFilter.getCampoOrdenacao())) {
                sql += " order by a." + UnidadeMedidaFilter.getCampoOrdenacao();
            }
            if (StringUtils.isNotBlank(UnidadeMedidaFilter.getCampoOrdenacao()) && StringUtils.isNotBlank(UnidadeMedidaFilter.getOrdemClassificacao())) {
                sql += " " + UnidadeMedidaFilter.getOrdemClassificacao();
            }
        }
        return sql;
    }


}
