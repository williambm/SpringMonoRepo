package com.wbm.algamoneyapi.algamoneyapi.service;


import com.wbm.algamoneyapi.algamoneyapi.model.Pessoa;
import com.wbm.algamoneyapi.algamoneyapi.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = buscarPessoaPorCodigo(codigo);

        //Copiar as propriedades de um objeto para outro (copia tudo!!)
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return repository.save(pessoaSalva);
    }


    public Pessoa atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaSalva = buscarPessoaPorCodigo(codigo);

        pessoaSalva.setAtivo(ativo);
        return repository.save(pessoaSalva);
    }


    public Pessoa buscarPessoaPorCodigo(Long codigo) {
        Optional<Pessoa> pessoaSalvaOpt = repository.findById(codigo);

        if (pessoaSalvaOpt.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaSalvaOpt.get();
    }
}
