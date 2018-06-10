package br.com.wcorrea.transport.api.repository.veiculo;

import br.com.wcorrea.transport.api.model.Veiculo;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class VeiculoRepositoryImpl implements VeiculoRepositoryQuery {

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
    public Page<Veiculo> findAll(VeiculoFiltro filtro, Pageable paginacao) {
        TypedQuery<Veiculo> queryList = manager.createQuery(this.createQuery(filtro, false), Veiculo.class);
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
    private String createQuery(VeiculoFiltro filtro, boolean count) {

        filtro.setFiltroGlobal(UtilsRepository.removeCaracteresProblematicos(filtro.getFiltroGlobal()));
        filtro.setPlaca(UtilsRepository.removeCaracteresProblematicos(filtro.getPlaca()));
        filtro.setFrota(UtilsRepository.removeCaracteresProblematicos(filtro.getFrota()));

        String sql;
        if (count) {
            sql = "select count(a) from veiculo a where 1=1 ";
        } else {
            sql = "from veiculo a where 1=1 ";
        }

        if (StringUtils.isNotBlank(filtro.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.placa) like '%" + filtro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " or upper(a.frota) like '%" + filtro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(filtro.getPlaca())) {
                sql += " and upper(a.placa) like '%" + filtro.getPlaca().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(filtro.getFrota())) {
                sql += " and upper(a.frota) like '%" + filtro.getFrota().toUpperCase().trim() + "%'";
            }
        }

        sql = UtilsRepository.adicionarOrdenacaoConsulta(sql, count, filtro.getSortField(), filtro.getSortOrder());
        return sql;
    }

}
