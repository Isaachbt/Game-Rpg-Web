package com.isaac.br.gamerpgweb.entitys.record;

import java.math.BigDecimal;
import java.util.UUID;

public record PlayerDTO(
        UUID id,
        String nomePlayer,
        String nomeClasses,
        String tipo,
        int vida,
        int dano,
        int defesa,
        String tipoArma,
        int xp,
        BigDecimal moeda,
        String rank
) {}
