package br.com.wcorrea.transport.api.repository.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;

import javax.persistence.TypedQuery;

public class UtilsRepository {
    /**
     * ADICIONA AS RESTRIÇÕES DE PAGINAÇÃO A CONSULTA PASSADA POR PARAMETRO
     *
     * @param query
     * @param pageable
     */
    public static void adicionarRestricoesPaginacao(TypedQuery<?> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRegistration = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPageRegistration);
        query.setMaxResults(totalRecordsPerPage);
    }

    /**
     * ADICIONA A ORDENAÇÃO DA CONSULTA PELO CAMPO PASSADO POR PARAMETRO
     *
     * @param sql
     * @param tipoConsulta
     * @param ordenarPeloCampo
     * @param tipoOrdenacao
     * @return
     */
    public static String adicionarOrdenacaoConsulta(String sql, boolean tipoConsulta, String ordenarPeloCampo, String tipoOrdenacao) {
        if (tipoConsulta == false) {
            if (StringUtils.isNotBlank(ordenarPeloCampo)) {
                sql += " order by a." + ordenarPeloCampo;
            }
            if (StringUtils.isNotBlank(ordenarPeloCampo) && StringUtils.isNotBlank(tipoOrdenacao)) {
                sql += " " + tipoOrdenacao;
            }
        }
        return sql;
    }

    public static String removeCaracteresProblematicos(String txt) {
        if (txt == null) {
            return null;
        }
        return txt.replaceAll("\\\\", "").replaceAll("\'", "");
    }
}
