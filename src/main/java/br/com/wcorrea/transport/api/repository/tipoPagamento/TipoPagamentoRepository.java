package br.com.wcorrea.transport.api.repository.tipoPagamento;

import br.com.wcorrea.transport.api.model.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Long>, TipoPagamentoRepositoryQuery {

    @Query("select t from tipo_pagamento t where t.inativo = false")
    List<TipoPagamento> listarTudoAtivo();

}