package com.isaac.br.gamerpgweb.controller;

import com.isaac.br.gamerpgweb.entitys.Player;
import com.isaac.br.gamerpgweb.entitys.record.CriandoPlayerDTO;
import com.isaac.br.gamerpgweb.entitys.record.PlayerDTO;
import com.isaac.br.gamerpgweb.entitys.record.PlayerIdDTO;
import com.isaac.br.gamerpgweb.service.PlayerService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/salvandoPlayer")
    public ResponseEntity<?> savePlayer(@RequestBody @Validated CriandoPlayerDTO playerDTO){

        if (playerDTO != null){
            playerService.criandoPlayer(playerDTO);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/get-player")
    public ResponseEntity<Player> getPlayer(@RequestBody @Validated @Nonnull PlayerIdDTO playerIdDTO){

        return ResponseEntity.ok().body(playerService.getPlayer(playerIdDTO.idPlayer()));
    }
}
