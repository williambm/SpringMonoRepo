package com.wbm.algamoneyapi.algamoneyapi.repository;

import com.wbm.algamoneyapi.algamoneyapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
