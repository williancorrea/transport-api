package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {
}
