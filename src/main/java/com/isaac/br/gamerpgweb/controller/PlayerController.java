package com.isaac.br.gamerpgweb.controller;

import com.isaac.br.gamerpgweb.entitys.record.CriandoPlayerDTO;
import com.isaac.br.gamerpgweb.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
