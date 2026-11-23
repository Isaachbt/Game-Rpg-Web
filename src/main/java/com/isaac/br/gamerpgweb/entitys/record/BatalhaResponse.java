package com.isaac.br.gamerpgweb.entitys.record;

import com.isaac.br.gamerpgweb.entitys.Monstro;
import com.isaac.br.gamerpgweb.entitys.Player;

import java.math.BigDecimal;
import java.util.UUID;

public record BatalhaResponse(UUID idBatalha,
                              MonstroResponse monstro,
                              PlayerResponse player,
                              int vidaAtualMonstro,
                              int turno,
                              int danoCausadoMonstro,
                              int danoCausadoPlayer,
                              BigDecimal moedasGanha) {
}
