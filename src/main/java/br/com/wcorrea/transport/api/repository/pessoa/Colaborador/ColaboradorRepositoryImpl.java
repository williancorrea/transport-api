package br.com.wcorrea.transport.api.repository.pessoa.Colaborador;

import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ColaboradorRepositoryImpl implements ColaboradorRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Pessoa> findAll(ColaboradorFiltro colaboradorFiltro, Pageable pageable) {
        TypedQuery<Pessoa> queryList = manager.createQuery(this.createQuery(colaboradorFiltro, false), Pessoa.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(colaboradorFiltro, true), Long.class);

        UtilsRepository.adicionarRestricoesPaginacao(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(ColaboradorFiltro colaboradorFiltro, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from pessoa a where 1=1 ";
        } else {
            sql = "from pessoa a where 1=1 ";
        }

        if (StringUtils.isNotBlank(colaboradorFiltro.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.nome) like '%" + colaboradorFiltro.getFiltroGlobal().toUpperCase().trim() + "%'";
//            sql += " or upper(a.name) like '%" + colaboradorFiltro.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
//            if (StringUtils.isNotBlank(colaboradorFiltro.getDescricao())) {
//                sql += " and upper(a.description) like '%" + colaboradorFiltro.getDescricao().toUpperCase().trim() + "%'";
//            }
//            if (StringUtils.isNotBlank(colaboradorFiltro.getNome())) {
//                sql += " and upper(a.nome) like '%" + colaboradorFiltro.getNome().toUpperCase().trim() + "%'";
//            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(colaboradorFiltro.getCampoOrdenacao())) {
                sql += " order by a." + colaboradorFiltro.getCampoOrdenacao();
            }
            if (StringUtils.isNotBlank(colaboradorFiltro.getOrdemClassificacao()) && StringUtils.isNotBlank(colaboradorFiltro.getCampoOrdenacao())) {
                sql += " " + colaboradorFiltro.getOrdemClassificacao();
            }
        }
        return sql;
    }
}
