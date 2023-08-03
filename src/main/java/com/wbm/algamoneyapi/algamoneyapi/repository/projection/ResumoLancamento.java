package com.wbm.algamoneyapi.algamoneyapi.repository.projection;

import com.wbm.algamoneyapi.algamoneyapi.model.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ResumoLancamento {
    private Long codigo;
    private String descicao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private TipoLancamento tipo;
    private String categoria;
    private String pessoa;


}
