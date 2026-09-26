package com.isaac.br.gamerpgweb.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassesPlayer {

    @Id
    private int idClasses;
    private String nome;
    private String tipo;
    private int vida;
    private int dano;
    private int defesa;
    private String tipoArma;
}
