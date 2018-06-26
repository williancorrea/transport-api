package br.com.wcorrea.transport.api.repository.controleKm;

import br.com.wcorrea.transport.api.model.ControleKm;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import br.com.wcorrea.transport.api.service.exception.veiculo.ControleKmPeriodoInvalidoDeEntradaKmSaida;
import br.com.wcorrea.transport.api.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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

    public Integer encontrarKmNaoInformado(ControleKm controleKm) {
        StringBuilder sql = new StringBuilder();
        sql.append("select max(kmChegada) from controle_km where dataHoraSaida < :pDataHoraSaida and veiculo.id = :pVeiculoId");
        Query cKm = manager.createQuery(sql.toString(), String.class)
                .setParameter("pDataHoraSaida", controleKm.getDataHoraSaida())
                .setParameter("pVeiculoId", controleKm.getVeiculo().getId());

        if (cKm.getResultList().size() == 0 || cKm.getResultList().get(0) == null) {
            return 0;
        }
        return Integer.parseInt(controleKm.getKmSaida()) - Integer.parseInt(cKm.getSingleResult().toString());
    }

    /**
     * Verifica se o km informado já está cadastrado em outro apontamento
     *
     * @param kmSaida
     * @return
     */
    public boolean validarPeriodoInvalidoKmSaida(Long controleKmId, Long veiculoId, String kmSaida) {
        StringBuilder sql = new StringBuilder();
        sql.append("from controle_km where :pkmSaida >= km_saida and :pkmSaida <= km_chegada and veiculo.id = :pveiculoId");
        Query cKm = manager.createQuery(sql.toString(), ControleKm.class)
                .setParameter("pkmSaida", kmSaida)
                .setParameter("pveiculoId", veiculoId);
        if (controleKmId == null) {
            if (cKm.getResultList().size() == 0) {
                return false;
            } else if (cKm.getResultList().size() > 1) {
                return true;
            } else if (cKm.getResultList().size() == 1 && ((ControleKm) cKm.getSingleResult()).getKmChegada().equalsIgnoreCase(kmSaida)) {
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
    public boolean validarPeriodoInvalidoKmChegada(Long controleKmId, Long veiculoId, String kmChegada) {
        StringBuilder sql = new StringBuilder();
        sql.append("from controle_km where :pkmChegada >= km_saida and :pkmChegada <= km_chegada and veiculo.id = :pveiculoId");
        Query cKm = manager.createQuery(sql.toString(), ControleKm.class)
                .setParameter("pkmChegada", kmChegada)
                .setParameter("pveiculoId", veiculoId);
        if (controleKmId == null) {
            if (cKm.getResultList().size() == 0) {
                return false;
            } else if (cKm.getResultList().size() > 1) {
                return true;
            } else if (cKm.getResultList().size() == 1 && ((ControleKm) cKm.getSingleResult()).getKmSaida().equalsIgnoreCase(kmChegada)) {
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
     * Verifica se o intevalo da data pode ser inserido o Km informado (verifica se já nao existe um km superior neste mesmo perio)
     *
     * @return
     */
    public boolean validarPeriodoInvalidoDeEntradaKmSaida(ControleKm controleKm) {
        StringBuilder sql = new StringBuilder();
        sql.append("from controle_km where data_hora_chegada >= :pDataSaida and data_hora_saida <= :pDataChegada and veiculo.id = :pveiculoId order by km_saida");
        Query max = manager.createQuery(sql.toString(), ControleKm.class)
                .setParameter("pDataSaida", controleKm.getDataHoraSaida())
                .setParameter("pDataChegada", controleKm.getDataHoraChegada())
                .setParameter("pveiculoId", controleKm.getVeiculo().getId());

        if (controleKm.getId() == null) {
            if (max.getResultList().size() == 0 || max.getResultList().get(0) == null) {
                return false;
            }
            // KM Saida
            if (Long.parseLong(controleKm.getKmSaida()) < Long.parseLong(((ControleKm) max.getResultList().get(0)).getKmChegada())) {
                throw new ControleKmPeriodoInvalidoDeEntradaKmSaida();
            }
        } else {
            if (max.getResultList().size() > 1) {
                return true;
            }
        }
        return false;
    }

    public boolean validarPeriodoInvalidoDeEntradaDataSaida(ControleKm controleKm) {
        StringBuilder sql = new StringBuilder();
        sql.append("from controle_km where data_hora_chegada >= :pDataSaida and data_hora_saida <= :pDataChegada and veiculo.id = :pveiculoId order by km_saida");
        Query max = manager.createQuery(sql.toString(), ControleKm.class)
                .setParameter("pDataSaida", controleKm.getDataHoraSaida())
                .setParameter("pDataChegada", controleKm.getDataHoraChegada())
                .setParameter("pveiculoId", controleKm.getVeiculo().getId());

        if (controleKm.getId() == null) {
            if (max.getResultList().size() == 0 || max.getResultList().get(0) == null) {
                return false;
            }
            // Verifica a data de Saida
            if (Utils.convertToLocalDateTime(controleKm.getDataHoraSaida()).isBefore(
                    Utils.convertToLocalDateTime(
                            ((ControleKm) max.getResultList().get(0)).getDataHoraChegada()
                    )
            )) {
                return true;
            }
        } else {
            //TODO: VERIFICA A ATUALIZAÇAÔ
            return true;
        }
        return false;
    }

    /**
     * Verifica se o intevalo da data pode ser inserido o Km informado (verifica se já nao existe um km superior neste mesmo perio)
     *
     * @return
     */
    public boolean validarPeriodoInvalidoDeEntradaKmChegada(ControleKm controleKm) {
        StringBuilder sql = new StringBuilder();
        sql.append("from controle_km where data_hora_chegada >= :pDataSaida and data_hora_saida <= :pDataChegada and veiculo.id = :pveiculoId order by km_saida");
        Query max = manager.createQuery(sql.toString(), ControleKm.class)
                .setParameter("pDataSaida", controleKm.getDataHoraSaida())
                .setParameter("pDataChegada", controleKm.getDataHoraChegada())
                .setParameter("pveiculoId", controleKm.getVeiculo().getId());
        if (controleKm.getId() == null) {
            if (max.getResultList().size() == 0 || max.getResultList().get(0) == null) {
                return false;
            }
            // KM Chegada
            if (Long.parseLong(controleKm.getKmChegada()) > Long.parseLong(((ControleKm) max.getResultList().get(max.getResultList().size() - 1)).getKmSaida())) {
                return true;
            }
        } else {
            //TODO: Verificar a atualização
            return true;
        }
        return false;
    }

    public boolean validarPeriodoInvalidoDeEntradaDataChegada(ControleKm controleKm) {
        StringBuilder sql = new StringBuilder();
        sql.append("from controle_km where data_hora_chegada >= :pDataSaida and data_hora_saida <= :pDataChegada and veiculo.id = :pveiculoId order by km_saida");
        Query max = manager.createQuery(sql.toString(), ControleKm.class)
                .setParameter("pDataSaida", controleKm.getDataHoraSaida())
                .setParameter("pDataChegada", controleKm.getDataHoraChegada())
                .setParameter("pveiculoId", controleKm.getVeiculo().getId());

        if (controleKm.getId() == null) {
            if (max.getResultList().size() == 0 || max.getResultList().get(0) == null) {
                return false;
            }
            // Verifica a data de chegada
            if (Utils.convertToLocalDateTime(controleKm.getDataHoraChegada()).isAfter(
                    Utils.convertToLocalDateTime(
                            ((ControleKm) max.getResultList().get(max.getResultList().size() - 1)).getDataHoraSaida()
                    )
            )) {
                return true;
            }
        } else {
            return true;
        }
        return false;
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
            sql += " upper(a.kmSaida) like '%" + filtro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " or upper(a.kmChegada) like '%" + filtro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(filtro.getKmSaida())) {
                sql += " and upper(a.kmSaida) like '%" + filtro.getKmSaida().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(filtro.getKmChegada())) {
                sql += " and upper(a.kmChegada) like '%" + filtro.getKmChegada().toUpperCase().trim() + "%'";
            }
        }

        sql = UtilsRepository.adicionarOrdenacaoConsulta(sql, count, filtro.getSortField(), filtro.getSortOrder());
        return sql;
    }

}
