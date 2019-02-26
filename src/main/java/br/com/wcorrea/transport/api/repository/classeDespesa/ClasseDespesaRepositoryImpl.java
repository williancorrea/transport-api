package br.com.wcorrea.transport.api.repository.classeDespesa;

import br.com.wcorrea.transport.api.model.ClasseDespesa;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ClasseDespesaRepositoryImpl implements ClasseDespesaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Buscar registro de forma paginada
     *
     * @param classeDespesaFiltro
     * @param pageable
     * @return
     */
    @Override
    public Page<ClasseDespesa> findAll(ClasseDespesaFiltro classeDespesaFiltro, Pageable pageable) {
        TypedQuery<ClasseDespesa> queryList = manager.createQuery(this.createQuery(classeDespesaFiltro, false), ClasseDespesa.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(classeDespesaFiltro, true), Long.class);

        UtilsRepository.adicionarRestricoesPaginacao(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(ClasseDespesaFiltro classeDespesaFiltro, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from classe_despesa a where 1=1";
        } else {
            sql = "from classe_despesa a where 1=1 ";
        }

        if (StringUtils.isNotBlank(classeDespesaFiltro.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.descricao) like '%" + classeDespesaFiltro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(classeDespesaFiltro.getDescricao())) {
                sql += " and upper(a.descricao) like '%" + classeDespesaFiltro.getDescricao().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(classeDespesaFiltro.getCampoOrdenacao())) {
                sql += " order by a." + classeDespesaFiltro.getCampoOrdenacao();
            }
            if (StringUtils.isNotBlank(classeDespesaFiltro.getOrdemClassificacao()) && StringUtils.isNotBlank(classeDespesaFiltro.getCampoOrdenacao())) {
                sql += " " + classeDespesaFiltro.getOrdemClassificacao();
            }
        }
        return sql;
    }
}
