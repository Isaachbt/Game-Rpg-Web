package com.isaac.br.gamerpgweb.controller;

import com.isaac.br.gamerpgweb.entitys.Batalha;
import com.isaac.br.gamerpgweb.entitys.record.BatalhaIdDTO;
import com.isaac.br.gamerpgweb.entitys.record.PlayerIdDTO;
import com.isaac.br.gamerpgweb.service.BatalhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batalha")
public class BatalhaController {

    @Autowired
    private BatalhaService batalhaService;

    @PostMapping("/batalhaEncontrada")
    public ResponseEntity<Batalha> batalhaEncontrada(@RequestBody PlayerIdDTO playerIdDTO){

        if (playerIdDTO != null){
            return ResponseEntity.ok(batalhaService.batalhaEncontrada(playerIdDTO.idPlayer()));
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/atacando")
    public ResponseEntity<Batalha> atacando(@RequestBody BatalhaIdDTO batalhaIdDTO){
        if (batalhaIdDTO != null){
            return ResponseEntity.ok(batalhaService.atacando(batalhaIdDTO.idBatalha()));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

}
