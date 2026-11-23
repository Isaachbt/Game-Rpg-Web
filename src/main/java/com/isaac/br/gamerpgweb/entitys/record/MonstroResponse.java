package com.isaac.br.gamerpgweb.entitys.record;

import com.isaac.br.gamerpgweb.entitys.enums.Enum_rank;

import java.util.UUID;

public record MonstroResponse(UUID idMonstro, String nome, int vida, int dano, int defesa, int xp, Enum_rank monstroRank) {
}
