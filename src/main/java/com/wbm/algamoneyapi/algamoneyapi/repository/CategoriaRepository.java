package com.wbm.algamoneyapi.algamoneyapi.repository;

import com.wbm.algamoneyapi.algamoneyapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
