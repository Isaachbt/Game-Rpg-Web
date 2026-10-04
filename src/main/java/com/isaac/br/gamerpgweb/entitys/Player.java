package com.isaac.br.gamerpgweb.entitys;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "players")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nomePlayer;
    private String nomeClasses;
    private String tipo;
    private int vida;
    private int dano;
    private int defesa;
    private String tipoArma;
    private int xp;
    private BigDecimal moeda;
    private String player_rank;
    //private List<InventarioPlayer> inventario;

}
