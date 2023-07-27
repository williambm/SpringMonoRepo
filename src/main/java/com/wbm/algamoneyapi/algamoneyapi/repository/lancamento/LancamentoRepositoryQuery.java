package com.wbm.algamoneyapi.algamoneyapi.repository.lancamento;

import com.wbm.algamoneyapi.algamoneyapi.model.Lancamento;
import com.wbm.algamoneyapi.algamoneyapi.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable);
}
