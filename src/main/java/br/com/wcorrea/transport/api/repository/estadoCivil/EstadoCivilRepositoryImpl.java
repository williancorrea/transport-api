package br.com.wcorrea.transport.api.repository.estadoCivil;

import br.com.wcorrea.transport.api.model.EstadoCivil;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class EstadoCivilRepositoryImpl implements EstadoCivilRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * CARREGA TODOS OS ESTADOS CIVIS DE FORMA PAGINADA
     *
     * @param filtro
     * @param paginacao
     * @return
     */
    @Override
    public Page<EstadoCivil> findAll(EstadoCivilFiltro filtro, Pageable paginacao) {
        TypedQuery<EstadoCivil> queryList = manager.createQuery(this.createQuery(filtro, false), EstadoCivil.class);
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
    private String createQuery(EstadoCivilFiltro filtro, boolean count) {

        filtro.setFiltroGlobal(removeCaracteresProblematicos(filtro.getFiltroGlobal()));
        filtro.setNome(removeCaracteresProblematicos(filtro.getNome()));
        filtro.setDescricao(removeCaracteresProblematicos(filtro.getDescricao()));

        String sql;
        if (count) {
            sql = "select count(a) from estado_civil a where 1=1 ";
        } else {
            sql = "from estado_civil a where 1=1 ";
        }

        if (StringUtils.isNotBlank(filtro.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.descricao) like '%" + filtro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " or upper(a.nome) like '%" + filtro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(filtro.getDescricao())) {
                sql += " and upper(a.descricao) like '%" + filtro.getDescricao().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(filtro.getNome())) {
                sql += " and upper(a.nome) like '%" + filtro.getNome().toUpperCase().trim() + "%'";
            }
        }

        sql = UtilsRepository.adicionarOrdenacaoConsulta(sql, count, filtro.getSortField(), filtro.getSortOrder());
        return sql;
    }


    //TODO: MELHORAR ESSE METODO, FAZER ELE FICAR GENERICO O SUFICIENTE PARA PODER RECEBER UM OBJETO POR PARAMETRO
    public String removeCaracteresProblematicos(String txt) {
        if (txt == null) {
            return null;
        }
        return txt.replaceAll("\\\\", "").replaceAll("\'", "");
    }
}
