package com.isaac.br.gamerpgweb.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "classes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClassesPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idclasses")
    private UUID idclasses;
    private String nome;
    private String tipo;
    private int vida;
    private int dano;
    private int defesa;
    private String tipoArma;
}
