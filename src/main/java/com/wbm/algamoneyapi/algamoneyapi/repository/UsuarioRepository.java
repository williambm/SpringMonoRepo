package com.wbm.algamoneyapi.algamoneyapi.repository;

import com.wbm.algamoneyapi.algamoneyapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);
}
