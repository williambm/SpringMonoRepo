package com.wbm.algamoneyapi.algamoneyapi.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Permissao {

    @Id
    private Long codigo;
    private String descricao;
}
