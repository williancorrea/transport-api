package br.com.wcorrea.transport.api.repository.NivelFormacao;

import br.com.wcorrea.transport.api.model.pessoa.NivelFormacao;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class NivelFormacaoRepositoryImpl implements NivelFormacaoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<NivelFormacao> findAll(NivelFormacaoFilter NivelFormacaoFilter, Pageable pageable) {
        TypedQuery<NivelFormacao> queryList = manager.createQuery(this.createQuery(NivelFormacaoFilter, false), NivelFormacao.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(NivelFormacaoFilter, true), Long.class);

        UtilsRepository.adicionarRestricoesPaginacao(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(NivelFormacaoFilter NivelFormacaoFilter, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from nivel_formacao a where 1=1 ";
        } else {
            sql = "from nivel_formacao a where 1=1 ";
        }

        if (StringUtils.isNotBlank(NivelFormacaoFilter.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.descricao) like '%" + NivelFormacaoFilter.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " or upper(a.nome) like '%" + NivelFormacaoFilter.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(NivelFormacaoFilter.getDescricao())) {
                sql += " and upper(a.descricao) like '%" + NivelFormacaoFilter.getDescricao().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(NivelFormacaoFilter.getNome())) {
                sql += " and upper(a.nome) like '%" + NivelFormacaoFilter.getNome().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(NivelFormacaoFilter.getCampoOrdenacao())) {
                sql += " order by a." + NivelFormacaoFilter.getCampoOrdenacao();
            }
            if (StringUtils.isNotBlank(NivelFormacaoFilter.getCampoOrdenacao()) && StringUtils.isNotBlank(NivelFormacaoFilter.getOrdemClassificacao())) {
                sql += " " + NivelFormacaoFilter.getOrdemClassificacao();
            }
        }
        return sql;
    }


}
