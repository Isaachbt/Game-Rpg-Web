package com.isaac.br.gamerpgweb.service;

import com.isaac.br.gamerpgweb.entitys.ClassesPlayer;
import com.isaac.br.gamerpgweb.entitys.record.ClassesResponseDTO;
import com.isaac.br.gamerpgweb.repository.ClassesPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    ClassesPlayerRepository classesPlayerRepository;

    public List<ClassesResponseDTO> GameIniciarNovoPlayer(){

        List<ClassesPlayer> classesPlayers = classesPlayerRepository.findAll();

        if (classesPlayers.isEmpty()){
            throw new RuntimeException("Não foi possivel recuperar ClassesPlayer");
        }

        return classesPlayers.stream()
                .map(user -> new ClassesResponseDTO(
                        user.getIdclasses(),
                        user.getNome(),
                        user.getTipo(),
                        user.getVida(),
                        user.getDano(),
                        user.getDefesa(),
                        user.getTipoArma()
                )).toList();
    }
}
