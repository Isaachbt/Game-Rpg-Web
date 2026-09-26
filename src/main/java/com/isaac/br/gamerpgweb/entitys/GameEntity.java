package com.isaac.br.gamerpgweb.entitys;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "gameController")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

}
