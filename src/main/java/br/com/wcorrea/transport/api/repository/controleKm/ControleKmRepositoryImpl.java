package br.com.wcorrea.transport.api.repository.controleKm;

import br.com.wcorrea.transport.api.model.ControleKm;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
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
        sql.append("from controle_km where :pkmSaida >= km_saida and :pkmSaida < km_chegada and veiculo.id = :pveiculoId");
        Query cKm = manager.createQuery(sql.toString(), ControleKm.class)
                .setParameter("pkmSaida", Integer.parseInt(kmSaida))
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
        sql.append("from controle_km where :pkmChegada > km_saida and :pkmChegada <= km_chegada and veiculo.id = :pveiculoId");
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
        Query cKm = manager.createQuery(sql.toString(), String.class)
                .setParameter("pDataSaida", dataSaida)
                .setParameter("pveiculoId", veiculoId);

        if (cKm.getResultList().size() == 0 || cKm.getResultList().get(0) == null) {
            return 0L;
        }
        return Long.parseLong(cKm.getSingleResult().toString());
    }

    public Long recuperarKmChegadaMaximo(Date dataChegada, Long veiculoId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select min(kmSaida)from controle_km where veiculo.id = :pveiculoId and dataHoraSaida >= :pDataChegada");
        //TODO: Mudara para long depois que alterar o tipo do banco
        Query cKm = manager.createQuery(sql.toString(), String.class)
                .setParameter("pDataChegada", dataChegada)
                .setParameter("pveiculoId", veiculoId);

        if (cKm.getResultList().size() == 0 || cKm.getResultList().get(0) == null) {
            return 0L;
        }
        return Long.parseLong(cKm.getSingleResult().toString());
    }

    public boolean validarPeriodoInvalidoDeEntradaDataSaida(ControleKm controleKm) {
        StringBuilder sql = new StringBuilder();
        sql.append("from controle_km where dataHoraChegada <= :pDataSaida or (:pDataSaida between dataHoraSaida and dataHoraChegada)  and veiculo.id = :pveiculoId order by km_saida ASC");
        Query max = manager.createQuery(sql.toString(), ControleKm.class)
                .setParameter("pDataSaida", controleKm.getDataHoraSaida())
                .setParameter("pveiculoId", controleKm.getVeiculo().getId())
                .setMaxResults(1);


        if (controleKm.getId() == null) {
            if (max.getResultList().size() == 0 || max.getResultList().get(0) == null) {
                return false;
            } else if (Utils.convertToLocalDateTime(controleKm.getDataHoraSaida()).isAfter(Utils.convertToLocalDateTime(((ControleKm) max.getResultList().get(0)).getDataHoraChegada()))) {
                return false;
            } else if (Utils.convertToLocalDateTime(controleKm.getDataHoraSaida()).isEqual(Utils.convertToLocalDateTime(((ControleKm) max.getResultList().get(0)).getDataHoraChegada()))) {
                return false;
            }
        } else {
            if (max.getResultList().size() == 0 || max.getResultList().get(0) == null) {
                return false;
            } else if (max.getResultList().size() > 0 && ((ControleKm) max.getSingleResult()).getId() == controleKm.getId() && Utils.convertToLocalDateTime(controleKm.getDataHoraSaida()).isAfter(Utils.convertToLocalDateTime(((ControleKm) max.getResultList().get(0)).getDataHoraChegada()))) {
                return false;
            } else if (max.getResultList().size() > 0 && ((ControleKm) max.getSingleResult()).getId() == controleKm.getId() && Utils.convertToLocalDateTime(controleKm.getDataHoraSaida()).isEqual(Utils.convertToLocalDateTime(((ControleKm) max.getResultList().get(0)).getDataHoraChegada()))) {
                return false;
            }
        }
        return true;
    }

    public boolean validarPeriodoInvalidoDeEntradaDataChegada(ControleKm controleKm) {
//        StringBuilder sql = new StringBuilder();
//        sql.append("from controle_km where data_hora_saida >= :pDataChegada  or (:pDataChegada between dataHoraSaida and dataHoraChegada) and veiculo.id = :pveiculoId order by km_saida ASC");
//        Query max = manager.createQuery(sql.toString(), ControleKm.class)
//                .setParameter("pDataChegada", controleKm.getDataHoraChegada())
//                .setParameter("pveiculoId", controleKm.getVeiculo().getId())
//                .setMaxResults(1);
//
//        if (controleKm.getId() == null) {
//            if (max.getResultList().size() == 0 || max.getResultList().get(0) == null) {
//                return false;
//            } else if (Utils.convertToLocalDateTime(controleKm.getDataHoraChegada()).isBefore(Utils.convertToLocalDateTime(((ControleKm) max.getResultList().get(0)).getDataHoraSaida()))) {
//                return false;
//            } else if (Utils.convertToLocalDateTime(controleKm.getDataHoraChegada()).equals(Utils.convertToLocalDateTime(((ControleKm) max.getResultList().get(0)).getDataHoraSaida()))) {
//                return false;
//            }
//        } else {
//            if (max.getResultList().size() == 0 || max.getResultList().get(0) == null) {
//                return false;
//            } else if (((ControleKm) max.getSingleResult()).getId() == controleKm.getId() && Utils.convertToLocalDateTime(controleKm.getDataHoraChegada()).isBefore(Utils.convertToLocalDateTime(((ControleKm) max.getResultList().get(0)).getDataHoraSaida()))) {
//                return false;
//            } else if (((ControleKm) max.getSingleResult()).getId() == controleKm.getId() && Utils.convertToLocalDateTime(controleKm.getDataHoraChegada()).equals(Utils.convertToLocalDateTime(((ControleKm) max.getResultList().get(0)).getDataHoraSaida()))) {
//                return false;
//            }
//        }
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
            sql += " upper(a.itinerario.nome) like '%" + filtro.getFiltroGlobal().toUpperCase().trim() + "%'";
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
