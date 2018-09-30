package br.com.wcorrea.transport.api.repository.classe;

import br.com.wcorrea.transport.api.model.Classe;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ClasseRepositoryImpl implements ClasseRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Buscar registro de forma paginada
     *
     * @param classeFiltro
     * @param pageable
     * @return
     */
    @Override
    public Page<Classe> findAll(ClasseFiltro classeFiltro, Pageable pageable) {
        TypedQuery<Classe> queryList = manager.createQuery(this.createQuery(classeFiltro, false), Classe.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(classeFiltro, true), Long.class);

        UtilsRepository.adicionarRestricoesPaginacao(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(ClasseFiltro classeFiltro, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from classe a where 1=1";
        } else {
            sql = "from classe a where 1=1 ";
        }

        if (StringUtils.isNotBlank(classeFiltro.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.descricao) like '%" + classeFiltro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(classeFiltro.getDescricao())) {
                sql += " and upper(a.descricao) like '%" + classeFiltro.getDescricao().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(classeFiltro.getSortField())) {
                sql += " order by a." + classeFiltro.getSortField();
            }
            if (StringUtils.isNotBlank(classeFiltro.getSortOrder()) && StringUtils.isNotBlank(classeFiltro.getSortField())) {
                sql += " " + classeFiltro.getSortOrder();
            }
        }
        return sql;
    }
}
