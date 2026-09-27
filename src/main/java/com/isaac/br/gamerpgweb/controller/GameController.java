package com.isaac.br.gamerpgweb.controller;

import com.isaac.br.gamerpgweb.entitys.record.ClassesResponseDTO;
import com.isaac.br.gamerpgweb.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;


    @GetMapping("/listClass")
    public ResponseEntity<List<ClassesResponseDTO>> GameIniciarNovoPlayer(){
            List<ClassesResponseDTO> dto = this.gameService.GameIniciarNovoPlayer();
        if (!dto.isEmpty()){
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
