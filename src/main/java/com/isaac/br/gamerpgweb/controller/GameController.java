package com.isaac.br.gamerpgweb.controller;

import com.isaac.br.gamerpgweb.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/game")
public class GameController {

    @Autowired
    private GameService gameService;


    public ResponseEntity<Void> GameIniciarNovoPlayer(){

        if (this.gameService.GameIniciarNovoPlayer()){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
