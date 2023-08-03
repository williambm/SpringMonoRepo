package com.wbm.algamoneyapi.algamoneyapi.service;

import com.wbm.algamoneyapi.algamoneyapi.model.Lancamento;
import com.wbm.algamoneyapi.algamoneyapi.model.Pessoa;
import com.wbm.algamoneyapi.algamoneyapi.repository.PessoaRepository;
import com.wbm.algamoneyapi.algamoneyapi.repository.filter.LancamentoFilter;
import com.wbm.algamoneyapi.algamoneyapi.repository.lancamento.LancamentoRepository;
import com.wbm.algamoneyapi.algamoneyapi.repository.projection.ResumoLancamento;
import com.wbm.algamoneyapi.algamoneyapi.service.exception.PessoaInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Page<Lancamento> listarLancamentos(LancamentoFilter filter, Pageable pageable) {
        return repository.filtrar(filter, pageable);
    }

    public Page<ResumoLancamento> resumirLancamentos(LancamentoFilter filter, Pageable pageable) {
        return repository.resumoLancamento(filter, pageable);
    }

    public Lancamento buscarLancamentoPorCodigo(Long codigo) {
        Optional<Lancamento> lancamentoOptional = repository.findById(codigo);

        if (lancamentoOptional.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        return lancamentoOptional.get();
    }

    public Lancamento criarLancamento(Lancamento lancamento) {

        Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo())
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        if (!pessoa.getAtivo()) {
            throw new PessoaInativaException();
        }

        return repository.save(lancamento);
    }

    public void deletarPeloCodigo(Long codigo) {
        repository.deleteById(codigo);
    }
}
