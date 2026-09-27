package com.isaac.br.gamerpgweb.entitys.record;

import jakarta.annotation.Nonnull;

public record ClassesResponseDTO(@Nonnull Integer idclasses,
                                 @Nonnull String nome,
                                 @Nonnull String tipo,
                                 @Nonnull int vida,
                                 @Nonnull int dano,
                                 @Nonnull int defesa,
                                 @Nonnull String tipoArma) {
}
