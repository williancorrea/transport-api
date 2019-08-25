package br.com.wcorrea.transport.api.repository.pessoa;

import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
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

        if (StringUtils.isNotBlank(pessoaFiltro.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.nome) like '%" + pessoaFiltro.getFiltroGlobal().toUpperCase().trim() + "%'";
//            sql += " or upper(a.name) like '%" + pessoaFiltro.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
//            if (StringUtils.isNotBlank(pessoaFiltro.getDescricao())) {
//                sql += " and upper(a.description) like '%" + pessoaFiltro.getDescricao().toUpperCase().trim() + "%'";
//            }
            if (StringUtils.isNotBlank(pessoaFiltro.getNome())) {
                sql += " and upper(a.nome) like '%" + pessoaFiltro.getNome().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(pessoaFiltro.getCampoOrdenacao())) {
                sql += " order by a." + pessoaFiltro.getCampoOrdenacao();
            }
            if (StringUtils.isNotBlank(pessoaFiltro.getOrdemClassificacao()) && StringUtils.isNotBlank(pessoaFiltro.getCampoOrdenacao())) {
                sql += " " + pessoaFiltro.getOrdemClassificacao();
            }
        }
        return sql;
    }

    @Override
    public Pessoa findOneByCPF(String cpf) {
        StringBuilder sb = new StringBuilder();
        sb.append("from pessoa p where 1=1 and p.pessoaFisica.cpf = :cpf");

        TypedQuery<Pessoa> query = manager.createQuery(sb.toString(), Pessoa.class);
        query.setParameter("cpf", cpf);

        return query.getResultList().size() == 0 ? null : query.getResultList().get(0);
    }

    @Override
    public Boolean verificarCPFJaCadastrado(String cpf, Long id) {
        StringBuilder sb = new StringBuilder();
        sb.append("select p from pessoa p where 1=1 and p.pessoaFisica.cpf = :cpf");

        TypedQuery<Pessoa> query = manager.createQuery(sb.toString(), Pessoa.class);
        query.setParameter("cpf", cpf);

        if (query.getResultList().size() == 0 || query.getSingleResult().getId() == id) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean verificarCNPJJaCadastrado(String cnpj, Long id) {
        StringBuilder sb = new StringBuilder();
        sb.append("select p from pessoa p where 1=1 and p.pessoaJuridica.cnpj = :cnpj");

        TypedQuery<Pessoa> query = manager.createQuery(sb.toString(), Pessoa.class);
        query.setParameter("cnpj", cnpj);

        if (query.getResultList().size() == 0 || query.getSingleResult().getId() == id) {
            return false;
        }
        return true;
    }

    @Override
    public Pessoa findOneByCNPJ(String cnpj) {
        StringBuilder sb = new StringBuilder();
        sb.append("from pessoa p where 1=1 and p.pessoaJuridica.cnpj = :cnpj");

        TypedQuery<Pessoa> query = manager.createQuery(sb.toString(), Pessoa.class);
        query.setParameter("cnpj", cnpj);

        return query.getResultList().size() == 0 ? null : query.getResultList().get(0);
    }
}
