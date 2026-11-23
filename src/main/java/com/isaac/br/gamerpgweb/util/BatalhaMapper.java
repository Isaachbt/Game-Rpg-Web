package com.isaac.br.gamerpgweb.util;

import com.isaac.br.gamerpgweb.entitys.Batalha;
import com.isaac.br.gamerpgweb.entitys.Monstro;
import com.isaac.br.gamerpgweb.entitys.Player;
import com.isaac.br.gamerpgweb.entitys.record.BatalhaResponse;
import com.isaac.br.gamerpgweb.entitys.record.MonstroResponse;
import com.isaac.br.gamerpgweb.entitys.record.PlayerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "Spring")
public interface BatalhaMapper {

    @Mapping(target = "danoCausadoMonstro",source = "danoMonstro")
    @Mapping(target = "danoCausadoPlayer",source = "danoPlayer")
    @Mapping(target = "moedasGanha",source = "moedaGanha")
    BatalhaResponse toDTO(Batalha batalha, int danoMonstro, int danoPlayer, BigDecimal moedaGanha);
    PlayerResponse toDTO(Player player);
    MonstroResponse toDTO(Monstro monstro);
}
