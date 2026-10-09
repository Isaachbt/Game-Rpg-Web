package com.isaac.br.gamerpgweb.entitys;

import com.isaac.br.gamerpgweb.entitys.enums.StatusBatalha;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "batalha")
@Data
public class Batalha {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idBatalha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_player", nullable = false)
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_monstro", nullable = false)
    private Monstro monstro;

    private int vidaAtualMonstro;

    private int turno;

    @Enumerated(EnumType.STRING)
    private StatusBatalha status;

    @Version
    private Long versao;
}
