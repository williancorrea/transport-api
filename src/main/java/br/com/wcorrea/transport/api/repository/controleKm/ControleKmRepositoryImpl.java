package br.com.wcorrea.transport.api.repository.controleKm;

import br.com.wcorrea.transport.api.model.ControleKm;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        //GAMBIARA DOS INFERNOS, DESISTEI (E FIZ ESSA MERDA AI)
        List<ControleKm> controleKmList = new ArrayList<>();
        for (ControleKm obj : queryList.getResultList()) {
            obj.setKmNaoInformado(this.encontrarKmNaoInformado(obj));
            controleKmList.add(obj);
        }
        return new PageImpl<>(controleKmList, paginacao, totalRegistros.getSingleResult());
    }

    public Long encontrarKmNaoInformado(ControleKm controleKm) {
        StringBuilder sql = new StringBuilder();
        sql.append("select max(kmChegada) from controle_km where dataHoraSaida < :pDataHoraSaida and veiculo.id = :pVeiculoId");
        Query cKm = manager.createQuery(sql.toString(), Long.class)
                .setParameter("pDataHoraSaida", controleKm.getDataHoraSaida())
                .setParameter("pVeiculoId", controleKm.getVeiculo().getId());

        if (cKm.getResultList().size() == 0 || cKm.getResultList().get(0) == null) {
            return 0L;
        }
        return controleKm.getKmSaida() - ((Long) cKm.getSingleResult());
    }

    /**
     * Verifica se o km informado já está cadastrado em outro apontamento
     *
     * @param kmSaida
     * @return
     */
    public boolean validarPeriodoInvalidoKmSaida(Long controleKmId, Long veiculoId, Long kmSaida) {
        StringBuilder sql = new StringBuilder();
        sql.append("from controle_km where :pkmSaida >= km_saida and :pkmSaida < km_chegada and veiculo.id = :pveiculoId");
        Query cKm = manager.createQuery(sql.toString(), ControleKm.class)
                .setParameter("pkmSaida", kmSaida)
                .setParameter("pveiculoId", veiculoId);
        if (controleKmId == null) {
            if (cKm.getResultList().size() == 0) {
                return false;
            } else if (cKm.getResultList().size() > 1) {
                return true;
            } else if (cKm.getResultList().size() == 1 && ((ControleKm) cKm.getSingleResult()).getKmChegada() == kmSaida) {
                return false;
            }
        }
        if (controleKmId != null) {
            if (cKm.getResultList().size() == 1 && ((ControleKm) cKm.getSingleResult()).getId() == controleKmId) {
                return false;
            } else if (cKm.getResultList().size() == 0) {
                return false;
            } else if (cKm.getResultList().size() > 1) {
                return true;
            }
        }
        return true;
    }

    /**
     * Verifica se o km informado já está cadastrado em outro apontamento
     *
     * @param kmChegada
     * @return
     */
    public boolean validarPeriodoInvalidoKmChegada(Long controleKmId, Long veiculoId, Long kmChegada) {
        StringBuilder sql = new StringBuilder();
        sql.append("from controle_km where :pkmChegada > km_saida and :pkmChegada <= km_chegada and veiculo.id = :pveiculoId");
        Query cKm = manager.createQuery(sql.toString(), ControleKm.class)
                .setParameter("pkmChegada", kmChegada)
                .setParameter("pveiculoId", veiculoId);
        if (controleKmId == null) {
            if (cKm.getResultList().size() == 0) {
                return false;
            } else if (cKm.getResultList().size() > 1) {
                return true;
            } else if (cKm.getResultList().size() == 1 && ((ControleKm) cKm.getSingleResult()).getKmSaida() == kmChegada) {
                return false;
            }
        }
        if (controleKmId != null) {
            if (cKm.getResultList().size() > 1) {
                return true;
            } else if (cKm.getResultList().size() == 1 && ((ControleKm) cKm.getSingleResult()).getId() == controleKmId) {
                return false;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Recupera o km minimo a ser inserido para o veiculo informado, impossibilitando a digitação de intervalos de km inválidos
     *
     * @param dataSaida
     * @param veiculoId
     * @return
     */
    public Long recuperarKmSaidaMinimo(Date dataSaida, Long veiculoId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select max(kmChegada) from controle_km where veiculo.id = :pveiculoId and dataHoraChegada <= :pDataSaida");
        //TODO: Mudara para long depois que alterar o tipo do banco
        Query cKm = manager.createQuery(sql.toString(), Long.class)
                .setParameter("pDataSaida", dataSaida)
                .setParameter("pveiculoId", veiculoId);

        if (cKm.getResultList().size() == 0 || cKm.getResultList().get(0) == null) {
            return 0L;
        }
        return (Long) cKm.getSingleResult();
    }

    public Long recuperarKmChegadaMaximo(Date dataChegada, Long veiculoId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select min(kmSaida)from controle_km where veiculo.id = :pveiculoId and dataHoraSaida >= :pDataChegada");
        Query cKm = manager.createQuery(sql.toString(), Long.class)
                .setParameter("pDataChegada", dataChegada)
                .setParameter("pveiculoId", veiculoId);

        if (cKm.getResultList().size() == 0 || cKm.getResultList().get(0) == null) {
            return 0L;
        }
        return (Long) cKm.getSingleResult();
    }

    public boolean validarPeriodoInvalidoDeEntradaDataSaida(ControleKm controleKm) {
        StringBuilder sql = new StringBuilder();
//        or (:pDataSaida  between dataHoraSaida and dataHoraChegada )
        sql.append("from controle_km where :pDataSaida >= dataHoraSaida and :pDataChegada <= dataHoraChegada and veiculo.id = :pveiculoId");
        Query cKm = manager.createQuery(sql.toString(), ControleKm.class)
                .setParameter("pDataSaida", controleKm.getDataHoraSaida())
                .setParameter("pDataChegada", controleKm.getDataHoraChegada())
                .setParameter("pveiculoId", controleKm.getVeiculo().getId());

        if (cKm.getResultList().size() == 0 || cKm.getResultList().get(0) == null) {
            return false;
        } else if (cKm.getResultList().size() > 1) {
            return true;
        }

        if (controleKm.getId() == null) {
            ControleKm c = (ControleKm) cKm.getSingleResult();
            if (controleKm.getKmSaida() >= c.getKmSaida()) {
                return false;
            }
        } else {
            ControleKm c = (ControleKm) cKm.getSingleResult();
            if (c.getVeiculo().getId() == c.getVeiculo().getId() && controleKm.getKmSaida() >= c.getKmSaida()) {
                return false;
            }
        }
        return true;
    }

    public boolean validarPeriodoInvalidoDeEntradaDataChegada(ControleKm controleKm) {
        StringBuilder sql = new StringBuilder();
        sql.append("from controle_km where :pDataSaida >= dataHoraSaida and :pDataChegada <= dataHoraChegada and veiculo.id = :pveiculoId");
        Query cKm = manager.createQuery(sql.toString(), ControleKm.class)
                .setParameter("pDataSaida", controleKm.getDataHoraSaida())
                .setParameter("pDataChegada", controleKm.getDataHoraChegada())
                .setParameter("pveiculoId", controleKm.getVeiculo().getId());

        if (cKm.getResultList().size() == 0 || cKm.getResultList().get(0) == null) {
            return false;
        } else if (cKm.getResultList().size() > 1) {
            return true;
        }

        if (controleKm.getId() == null) {
            ControleKm c = (ControleKm) cKm.getSingleResult();
            if (controleKm.getKmChegada() <= c.getKmChegada()) {
                return false;
            }
        } else {
            ControleKm c = (ControleKm) cKm.getSingleResult();
            if (c.getVeiculo().getId() == c.getVeiculo().getId() && controleKm.getKmChegada() <= c.getKmChegada()) {
                return false;
            }
        }
        return true;
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

        String sql;
        if (count) {
            sql = "select count(a) from controle_km a where 1=1 ";
        } else {
            sql = "from controle_km a where 1=1 ";
        }

        if (StringUtils.isNotBlank(filtro.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.itinerario.nome) like '%" + filtro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (filtro.getDataSaida() != null) {
                sql += " and a.dataHoraSaida >= '" + filtro.getDataSaida() + "'";
            }
            if (filtro.getDataChegada() != null) {
                sql += " and a.dataHoraChegada <= '" + filtro.getDataChegada() + "'";
            }
            if (filtro.getKmSaida() != null) {
                sql += " and a.kmSaida >= " + filtro.getKmSaida();
            }
            if (filtro.getKmChegada() != null) {
                sql += " and a.kmChegada <= " + filtro.getKmChegada();
            }

            if (filtro.getVeiculoId() != null) {
                sql += " and a.veiculo.id = " + filtro.getVeiculoId();
            }
            if (filtro.getPessoaId() != null) {
                sql += " and a.pessoa.id = " + filtro.getPessoaId();
            }
            if (filtro.getItinerarioId() != null) {
                sql += " and a.itinerario.id = " + filtro.getItinerarioId();
            }
        }

        sql = UtilsRepository.adicionarOrdenacaoConsulta(sql, count, filtro.getSortField(), filtro.getSortOrder());
        return sql;
    }

}
