package com.isaac.br.gamerpgweb.service;

import com.isaac.br.gamerpgweb.entitys.Batalha;
import com.isaac.br.gamerpgweb.entitys.Monstro;
import com.isaac.br.gamerpgweb.entitys.Player;
import com.isaac.br.gamerpgweb.entitys.enums.StatusBatalha;
import com.isaac.br.gamerpgweb.repository.BatalhaRepository;
import com.isaac.br.gamerpgweb.repository.MonstroRepository;
import com.isaac.br.gamerpgweb.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BatalhaService {

    @Autowired
    private BatalhaRepository batalhaRepository;
    @Autowired
    private MonstroRepository monstroRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @Transactional
    public Batalha batalhaEncontrada(UUID idMonstro){
        Optional<Player> player = playerRepository.findById(UUID.fromString("3d0004a3-82aa-417a-908a-36e7863afe8f"));
        if(player.isEmpty()){
            throw new RuntimeException("Não  foi possivel encontrar player");
        }

        Optional<Monstro> monstro = monstroRepository.findRandomByRank(String.valueOf(player.get().getPlayer_rank()));
        if(monstro.isEmpty()){
            throw new RuntimeException("Não foi possivel encontrar monstro");
        }
        Batalha batalha = batalha(monstro.get(),player.get());

        try {
            return batalhaRepository.save(batalha);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar status da batalha");
        }
    }

    @Transactional
    public Batalha atacando(UUID idBatalha){
        Batalha batalha = buscarBatalhaAtiva(idBatalha);
        Player p =  batalha.getPlayer();
        Monstro m =  batalha.getMonstro();

        if (batalha.getStatus() != StatusBatalha.EM_ANDAMENTO){
            throw new IllegalStateException("Batalha finalizada");
        }

        int danoPlayer = Math.max(p.getDano() - m.getDefesa(), 1);
        batalha.setVidaAtualMonstro(Math.max(batalha.getVidaAtualMonstro() - danoPlayer,0));

        if (batalha.getVidaAtualMonstro() == 0) {
            batalha.setTurno(batalha.getTurno() + 1);
            batalha.setStatus(StatusBatalha.VITORIA);
            p.setXp(p.getXp() + m.getXp());
            return batalha;
        }

        var danoMonstro = Math.max(m.getDano() - p.getDefesa(), 1);
        p.setVida(Math.max(p.getVida() - danoMonstro,0));

        if (p.getVida() == 0) {
            batalha.setTurno(batalha.getTurno() + 1);
            batalha.setStatus(StatusBatalha.DERROTA);}
        batalha.setTurno(batalha.getTurno() + 1);
        return batalha;

    }


    public Batalha buscarBatalhaAtiva(UUID idBatalha){
       return batalhaRepository.findById(idBatalha).orElseThrow(()-> new RuntimeException("Batalha não encontrada."));
    }

    public Batalha batalha(Monstro monstro,Player player){
        Batalha batalha = new Batalha();

        batalha.setPlayer(player);
        batalha.setMonstro(monstro);
        batalha.setVidaAtualMonstro(monstro.getVida());
        batalha.setTurno(1);
        batalha.setStatus(StatusBatalha.EM_ANDAMENTO);
        return batalha;
    }
}
