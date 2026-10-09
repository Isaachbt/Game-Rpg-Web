package com.isaac.br.gamerpgweb.entitys.record;

import jakarta.annotation.Nonnull;

import java.util.UUID;

public record CriandoPlayerDTO(@Nonnull String nomePlayer,
                               @Nonnull UUID idclasses) {
}
