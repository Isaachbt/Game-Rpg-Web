package com.isaac.br.gamerpgweb.entitys.record;

import com.isaac.br.gamerpgweb.entitys.enums.Enum_rank;

import java.math.BigDecimal;

public record PlayerResponse(String nomePlayer, int vida, int dano, int defesa, int xp, BigDecimal moeda, Enum_rank player_rank) {
}
