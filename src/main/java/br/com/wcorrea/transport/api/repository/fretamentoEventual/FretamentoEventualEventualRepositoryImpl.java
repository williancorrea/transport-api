package br.com.wcorrea.transport.api.repository.fretamentoEventual;

import br.com.wcorrea.transport.api.model.fretamento.FretamentoEventual;
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

public class FretamentoEventualEventualRepositoryImpl implements FretamentoEventualRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<FretamentoEventual> findAll(FretamentoEventualFiltro filtro, Pageable pageable) {

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

    /**
     * RECUPERA A QUANTIDADE DE REGISTRO
     *
     * @param filtro
     * @return
     */
    public int quantidadeRegistrosFiltrados(FretamentoEventualFiltro filtro) {
        Criteria criteria = criarCriteriaParaFiltro(filtro);
        criteria.setProjection(Projections.rowCount());
        return ((Number) criteria.uniqueResult()).intValue();
    }

    /**
     * MONTA OS CRITERIOS PARA A PESQUISA
     *
     * @param filtro
     * @return
     */
    private Criteria criarCriteriaParaFiltro(FretamentoEventualFiltro filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(FretamentoEventual.class);

        criteria.createAlias("cliente", "c",  JoinType.LEFT_OUTER_JOIN);

        // Objetos embedded não precisa ter um Alias
        criteria.createAlias("itinerario.partidaCidade", "pc",  JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("itinerario.retornoCidade", "pr",  JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("itinerario.veiculo", "v",  JoinType.LEFT_OUTER_JOIN);

        if (StringUtils.isNotBlank(filtro.getFiltroGlobal())) {
            Disjunction disjunction = Restrictions.disjunction(); // Restricao com OR
            disjunction.add(Restrictions.ilike("numeroContrato", filtro.getFiltroGlobal(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("c.nome", filtro.getFiltroGlobal(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("pc.nome", filtro.getFiltroGlobal(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("pr.nome", filtro.getFiltroGlobal(), MatchMode.ANYWHERE));

            // Objetos embedded não precisa ter um Alias
            disjunction.add(Restrictions.ilike("contato.nome", filtro.getFiltroGlobal(), MatchMode.ANYWHERE));

            criteria.add(disjunction);
        }


        // Faz filtro pelo id do veiculo
        if(StringUtils.isNotBlank(filtro.getVeiculoKey())){
            criteria.add(Restrictions.eq("v.id", filtro.getVeiculoId()));
        }


        // Data de partida >=
        if(filtro.getDataPartida() != null){
            criteria.add(Restrictions.ge("itinerario.partida", filtro.getDataPartida()));
        }

        // Data de Retorno <=
        if(filtro.getDataRetorno() != null){
            criteria.add(Restrictions.le("itinerario.retorno", filtro.getDataRetorno()));
        }

        return criteria;
    }

//    public List<LancamentoEstatisticaPessoa> porPessoa(LocalDate inicio, LocalDate fim) {
//        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
//
//        CriteriaQuery<LancamentoEstatisticaPessoa> criteriaQuery = criteriaBuilder.createQuery(LancamentoEstatisticaPessoa.class);
//
//        Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
//
//        criteriaQuery.select(criteriaBuilder.construct(LancamentoEstatisticaPessoa.class,
//                root.get(Lancamento_.tipo),
//                root.get(Lancamento_.pessoa),
//                criteriaBuilder.sum(root.get(Lancamento_.valor))));
//
//        criteriaQuery.where(
//                criteriaBuilder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), inicio),
//                criteriaBuilder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), fim)
//        );
//
//        criteriaQuery.groupBy(root.get(Lancamento_.tipo), root.get(Lancamento_.pessoa));
//
//        TypedQuery<LancamentoEstatisticaPessoa> typedQuery = manager.createQuery(criteriaQuery);
//
//        return typedQuery.getResultList();
//    }
}
