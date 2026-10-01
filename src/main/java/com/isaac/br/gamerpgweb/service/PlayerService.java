package com.isaac.br.gamerpgweb.service;

import com.isaac.br.gamerpgweb.entitys.ClassesPlayer;
import com.isaac.br.gamerpgweb.entitys.Player;
import com.isaac.br.gamerpgweb.entitys.record.CriandoPlayerDTO;
import com.isaac.br.gamerpgweb.repository.ClassesPlayerRepository;
import com.isaac.br.gamerpgweb.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private ClassesPlayerRepository classesPlayerRepository;


    public List<ClassesPlayer> getClassesPlayer(){
        Optional<List<ClassesPlayer>> opt = Optional.of(classesPlayerRepository.findAll());
        if (opt.isEmpty()) {
            throw new RuntimeException("Erro ao tentar obter a classes player");
        }
        return opt.get();
    }

    public void criandoPlayer(CriandoPlayerDTO playerDTO){

        ClassesPlayer classesPlayer;

        System.out.println(playerDTO.idclasses());
        System.out.println(playerDTO.nomePlayer());

        try {
            classesPlayer = classesPlayerRepository.getById(playerDTO.idclasses());

            if (classesPlayer == null) {
                throw new RuntimeException("Classes player nao encontrado");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao tentar obter a classes player");
        }

        if (playerDTO != null){
            Player player = new Player();
            player.setNomePlayer(playerDTO.nomePlayer());
            player.setNomeClasses(classesPlayer.getNome());
            player.setTipo(classesPlayer.getTipo());
            player.setVida(classesPlayer.getVida());
            player.setDano(classesPlayer.getDano());
            player.setDefesa(classesPlayer.getDefesa());
            player.setTipoArma(classesPlayer.getTipoArma());
            try {
                playerRepository.save(player);
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("Erro ao salvar jogador.");
            }
        }
    }
}
