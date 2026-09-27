package com.isaac.br.gamerpgweb.entitys;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "classes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClassesPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idclasses;
    private String nome;
    private String tipo;
    private int vida;
    private int dano;
    private int defesa;
    private String tipoArma;
}
