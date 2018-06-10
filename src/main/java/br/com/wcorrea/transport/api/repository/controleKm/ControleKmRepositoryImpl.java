package br.com.wcorrea.transport.api.repository.controleKm;

import br.com.wcorrea.transport.api.model.ControleKm;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ControleKmRepositoryImpl implements ControleKmRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * CARREGA TODOS OS REGISTROS DE FORMA PAGINADA
     *
     * @param filtro
     * @param paginacao
     * @return
     */
    @Override
    public Page<ControleKm> findAll(ControleKmFiltro filtro, Pageable paginacao) {
        TypedQuery<ControleKm> queryList = manager.createQuery(this.createQuery(filtro, false), ControleKm.class);
        TypedQuery<Long> totalRegistros = manager.createQuery(this.createQuery(filtro, true), Long.class);

        UtilsRepository.adicionarRestricoesPaginacao(queryList, paginacao);
        return new PageImpl<>(queryList.getResultList(), paginacao, totalRegistros.getSingleResult());
    }

    /**
     * Cria as consultas da aplicação
     *
     * @param filtro
     * @param count
     * @return
     */
    private String createQuery(ControleKmFiltro filtro, boolean count) {

        filtro.setFiltroGlobal(UtilsRepository.removeCaracteresProblematicos(filtro.getFiltroGlobal()));
        filtro.setKmSaida(UtilsRepository.removeCaracteresProblematicos(filtro.getKmSaida()));
        filtro.setKmChegada(UtilsRepository.removeCaracteresProblematicos(filtro.getKmChegada()));

        String sql;
        if (count) {
            sql = "select count(a) from controle_km a where 1=1 ";
        } else {
            sql = "from controle_km a where 1=1 ";
        }

        if (StringUtils.isNotBlank(filtro.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.km_saida) like '%" + filtro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " or upper(a.km_chegada) like '%" + filtro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(filtro.getKmSaida())) {
                sql += " and upper(a.km_saida) like '%" + filtro.getKmSaida().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(filtro.getKmChegada())) {
                sql += " and upper(a.km_chegada) like '%" + filtro.getKmChegada().toUpperCase().trim() + "%'";
            }
        }

        sql = UtilsRepository.adicionarOrdenacaoConsulta(sql, count, filtro.getSortField(), filtro.getSortOrder());
        return sql;
    }

}
