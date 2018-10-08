package br.com.wcorrea.transport.api.repository.tipoPagamento;

import br.com.wcorrea.transport.api.model.TipoPagamento;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class TipoPagamentoRepositoryImpl implements TipoPagamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Buscar registro de forma paginada
     *
     * @param tipoPagamentoFiltro
     * @param pageable
     * @return
     */
    @Override
    public Page<TipoPagamento> findAll(TipoPagamentoFiltro tipoPagamentoFiltro, Pageable pageable) {
        TypedQuery<TipoPagamento> queryList = manager.createQuery(this.createQuery(tipoPagamentoFiltro, false), TipoPagamento.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(tipoPagamentoFiltro, true), Long.class);

        UtilsRepository.adicionarRestricoesPaginacao(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(TipoPagamentoFiltro tipoPagamentoFiltro, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from tipo_pagamento a where 1=1";
        } else {
            sql = "from tipo_pagamento a where 1=1 ";
        }

        if (StringUtils.isNotBlank(tipoPagamentoFiltro.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.descricao) like '%" + tipoPagamentoFiltro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(tipoPagamentoFiltro.getDescricao())) {
                sql += " and upper(a.descricao) like '%" + tipoPagamentoFiltro.getDescricao().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(tipoPagamentoFiltro.getSortField())) {
                sql += " order by a." + tipoPagamentoFiltro.getSortField();
            }
            if (StringUtils.isNotBlank(tipoPagamentoFiltro.getSortOrder()) && StringUtils.isNotBlank(tipoPagamentoFiltro.getSortField())) {
                sql += " " + tipoPagamentoFiltro.getSortOrder();
            }
        }
        return sql;
    }
}
