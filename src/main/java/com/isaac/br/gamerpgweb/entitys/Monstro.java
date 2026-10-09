package com.isaac.br.gamerpgweb.entitys;

import com.isaac.br.gamerpgweb.entitys.enums.Enum_rank;
import com.isaac.br.gamerpgweb.entitys.enums.EnumFaseMonstro;
import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "monstro")
@Data
public class Monstro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idMonstro;
    private String nome;
    private int vida;
    private int dano;
    private int defesa;
    private int xp;
    @Enumerated(EnumType.STRING)
    private EnumFaseMonstro tipoFaseMonstro;
    @Enumerated(EnumType.STRING)
    private Enum_rank monstroRank;

}
