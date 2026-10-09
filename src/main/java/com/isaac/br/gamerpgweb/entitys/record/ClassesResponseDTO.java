package com.isaac.br.gamerpgweb.entitys.record;

import jakarta.annotation.Nonnull;

import java.util.UUID;
public record ClassesResponseDTO(@Nonnull UUID idclasses,
                                 @Nonnull String nome,
                                 @Nonnull String tipo,
                                 @Nonnull int vida,
                                 @Nonnull int dano,
                                 @Nonnull int defesa,
                                 @Nonnull String tipoArma) {
}
