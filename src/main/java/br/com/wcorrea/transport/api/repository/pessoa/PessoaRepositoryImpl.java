package br.com.wcorrea.transport.api.repository.pessoa;

import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
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
    public Page<Pessoa> findAll(PessoaFiltro filtro, Pageable pageable) {

        //Criterios da pesquisa
        Criteria criteria = criarCriteriaParaFiltro(filtro);

        //Paginacao
        criteria.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        criteria.setMaxResults(pageable.getPageSize());

        //Ordenacao
        if (filtro.getCampoOrdenacao() != null && filtro.getOrdemClassificacao() != null) {
            if (filtro.getOrdemClassificacao().equalsIgnoreCase("ASC")) {
                criteria.addOrder(Order.asc(filtro.getCampoOrdenacao()));
            } else {
                criteria.addOrder(Order.desc(filtro.getCampoOrdenacao()));
            }
        }

        //Consulta paginada
        return new PageImpl<>(criteria.list(), pageable, quantidadeRegistrosFiltrados(filtro));
    }

    public int quantidadeRegistrosFiltrados(PessoaFiltro filtro) {
        Criteria criteria = criarCriteriaParaFiltro(filtro);
        criteria.setProjection(Projections.rowCount());
        return ((Number) criteria.uniqueResult()).intValue();
    }

    private Criteria criarCriteriaParaFiltro(PessoaFiltro filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Pessoa.class);

        criteria.createAlias("pessoaFisica", "pf", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("pessoaJuridica", "pj", JoinType.LEFT_OUTER_JOIN);

        if (StringUtils.isNotBlank(filtro.getFiltroGlobal())) {
            Disjunction disjunction = Restrictions.disjunction(); // Restricao com OR
            disjunction.add(Restrictions.ilike("nome", filtro.getFiltroGlobal(), MatchMode.ANYWHERE));

            criteria.add(disjunction);
            return criteria;
        }

        if (StringUtils.isNotBlank(filtro.getNome())) {
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
        }

        //FILRTAR SOMENTE OS MOTORISTAS
        if (filtro.isMotorista()) {
            Conjunction c = Restrictions.conjunction();
            c.add(Restrictions.eq("pf.inativoMotorista", false));
//            c.add(Restrictions.isNotNull("pf.cnhNumero"));
//            c.add(Restrictions.ne("pf.cnhNumero", ""));
            criteria.add(c);
        }

        //Filtrar somente empresa do Grupo Rosinha Transportes
        if(filtro.isEmpresaRosinhaTransportes()){
            Conjunction c = Restrictions.conjunction();
            c.add(Restrictions.eq("empresaRosinhaTransportes", true));
            criteria.add(c);
        }

        //Filtrar somente empresa do Grupo Rosinha Transportes
        if(filtro.isRepresentanteComercialRosinhaTransportes()){
            Conjunction c = Restrictions.conjunction();
            c.add(Restrictions.eq("representanteComercialRosinhaTransportes", true));
            criteria.add(c);
        }

        //SOMENTE CADASTROS ATIVOS
        if (filtro.isSomenteAtivo()) {
            criteria.add(Restrictions.eq("inativo", false));
        }
        return criteria;
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
