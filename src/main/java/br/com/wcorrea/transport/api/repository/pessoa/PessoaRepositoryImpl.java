package br.com.wcorrea.transport.api.repository.pessoa;

import br.com.wcorrea.transport.api.model.Pessoa;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
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
     * @param pessoaFiltro
     * @param pageable
     * @return
     */
    @Override
    public Page<Pessoa> findAll(PessoaFiltro pessoaFiltro, Pageable pageable) {
        TypedQuery<Pessoa> queryList = manager.createQuery(this.createQuery(pessoaFiltro, false), Pessoa.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(pessoaFiltro, true), Long.class);

        UtilsRepository.adicionarRestricoesPaginacao(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(PessoaFiltro pessoaFiltro, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from pessoa a where 1=1 ";
        } else {
            sql = "from pessoa a where 1=1 ";
        }

        if (StringUtils.isNotBlank(pessoaFiltro.getGlobalFilter())) {
            sql += " and (";
            sql += " upper(a.nome) like '%" + pessoaFiltro.getGlobalFilter().toUpperCase().trim() + "%'";
//            sql += " or upper(a.name) like '%" + pessoaFiltro.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
//            if (StringUtils.isNotBlank(pessoaFiltro.getDescricao())) {
//                sql += " and upper(a.description) like '%" + pessoaFiltro.getDescricao().toUpperCase().trim() + "%'";
//            }
            if (StringUtils.isNotBlank(pessoaFiltro.getName())) {
                sql += " and upper(a.nome) like '%" + pessoaFiltro.getName().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(pessoaFiltro.getSortField())) {
                sql += " order by a." + pessoaFiltro.getSortField();
            }
            if (StringUtils.isNotBlank(pessoaFiltro.getSortOrder()) && StringUtils.isNotBlank(pessoaFiltro.getSortField())) {
                sql += " " + pessoaFiltro.getSortOrder();
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
