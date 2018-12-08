package br.com.wcorrea.transport.api.repository.banco;

import br.com.wcorrea.transport.api.model.Banco;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class BancoRepositoryImpl implements BancoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Busca todos os bancos - paginacao de dados
     *
     * @param bancoFiltro
     * @param pageable
     * @return
     */
    @Override
    public Page<Banco> findAll(BancoFiltro bancoFiltro, Pageable pageable) {
        TypedQuery<Banco> queryList = manager.createQuery(this.createQuery(bancoFiltro, false), Banco.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(bancoFiltro, true), Long.class);

        UtilsRepository.adicionarRestricoesPaginacao(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(BancoFiltro bancoFiltro, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from banco a where 1=1";
        } else {
            sql = "from banco a where 1=1 ";
        }

        if (StringUtils.isNotBlank(bancoFiltro.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.codigo) like '%" + bancoFiltro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " or upper(a.nome) like '%" + bancoFiltro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " or upper(a.url) like '%" + bancoFiltro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(bancoFiltro.getCodigo())) {
                sql += " and upper(a.codigo) like '%" + bancoFiltro.getCodigo().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(bancoFiltro.getNome())) {
                sql += " and upper(a.nome) like '%" + bancoFiltro.getNome().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(bancoFiltro.getUrl())) {
                sql += " and upper(a.url) like '%" + bancoFiltro.getUrl().toUpperCase().trim() + "%'";
            }
        }

        /**
         * Ordenacao da lista
         */
        if (count == false) {
            if (StringUtils.isNotBlank(bancoFiltro.getPropriedadeOrdenacao())) {
                sql += " order by a." + bancoFiltro.getPropriedadeOrdenacao();
            }
            if (StringUtils.isNotBlank(bancoFiltro.getOrdemClassificacao()) && StringUtils.isNotBlank(bancoFiltro.getPropriedadeOrdenacao())) {
                sql += " " + bancoFiltro.getOrdemClassificacao();
            }
        }
        return sql;
    }
}
