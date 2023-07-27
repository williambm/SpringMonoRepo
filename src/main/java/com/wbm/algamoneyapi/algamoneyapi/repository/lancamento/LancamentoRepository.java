package com.wbm.algamoneyapi.algamoneyapi.repository.lancamento;

import com.wbm.algamoneyapi.algamoneyapi.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
}
