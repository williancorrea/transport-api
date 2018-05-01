package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.Pessoa;
import br.com.wcorrea.transport.api.repository.filter.PersonFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Encontra todas as Pessoas e retornar o valor de forma paginada
     *
     * @param personFilter
     * @param pageable
     * @return
     */
    @Override
    public Page<Pessoa> findAll(PersonFilter personFilter, Pageable pageable) {
        TypedQuery<Pessoa> queryList = manager.createQuery(this.createQuery(personFilter, false), Pessoa.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(personFilter, true), Long.class);

        UtilsRepository.pagingRestrictions(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(PersonFilter personFilter, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from pessoa a where 1=1 ";
        } else {
            sql = "from pessoa a where 1=1 ";
        }

        if (StringUtils.isNotBlank(personFilter.getGlobalFilter())) {
            sql += " and (";
            sql += " upper(a.nome) like '%" + personFilter.getGlobalFilter().toUpperCase().trim() + "%'";
//            sql += " or upper(a.name) like '%" + personFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
//            if (StringUtils.isNotBlank(personFilter.getDescricao())) {
//                sql += " and upper(a.description) like '%" + personFilter.getDescricao().toUpperCase().trim() + "%'";
//            }
            if (StringUtils.isNotBlank(personFilter.getName())) {
                sql += " and upper(a.nome) like '%" + personFilter.getName().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(personFilter.getSortField())) {
                sql += " order by a." + personFilter.getSortField();
            }
            if (StringUtils.isNotBlank(personFilter.getSortOrder()) && StringUtils.isNotBlank(personFilter.getSortField())) {
                sql += " " + personFilter.getSortOrder();
            }
        }
        return sql;
    }

    @Override
    public Pessoa findOneByCPF(String cpf) {
        StringBuilder sb = new StringBuilder();
        sb.append("from pessoa p ");
        sb.append("where 1=1 ");
        sb.append("and p.pessoaFisica.cpf = :cpf ");

        TypedQuery<Pessoa> query = manager.createQuery(sb.toString(), Pessoa.class);
        query.setParameter("cpf", cpf);

        return query.getResultList().size() == 0 ? null : query.getResultList().get(0);
    }

    @Override
    public Pessoa findOneByCNPJ(String cnpj) {
        StringBuilder sb = new StringBuilder();
        sb.append("from pessoa p ");
        sb.append("where 1=1 ");
        sb.append("and p.pessoaJuridica.cnpj = :cnpj ");

        TypedQuery<Pessoa> query = manager.createQuery(sb.toString(), Pessoa.class);
        query.setParameter("cnpj", cnpj);

        return query.getResultList().size() == 0 ? null : query.getResultList().get(0);
    }
}
