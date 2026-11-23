package com.isaac.br.gamerpgweb.service;

import com.isaac.br.gamerpgweb.entitys.Batalha;
import com.isaac.br.gamerpgweb.entitys.Monstro;
import com.isaac.br.gamerpgweb.entitys.Player;
import com.isaac.br.gamerpgweb.entitys.enums.StatusBatalha;
import com.isaac.br.gamerpgweb.entitys.record.BatalhaResponse;
import com.isaac.br.gamerpgweb.repository.BatalhaRepository;
import com.isaac.br.gamerpgweb.repository.MonstroRepository;
import com.isaac.br.gamerpgweb.repository.PlayerRepository;
import com.isaac.br.gamerpgweb.util.BatalhaMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BatalhaService {

    @Autowired
    private BatalhaRepository batalhaRepository;
    @Autowired
    private MonstroRepository monstroRepository;
    @Autowired
    private PlayerRepository playerRepository;
    private final BatalhaMapper mapper;

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
    public BatalhaResponse atacando(UUID idBatalha){
        Batalha batalha = buscarBatalhaAtiva(idBatalha);
        Player p =  batalha.getPlayer();
        Monstro m =  batalha.getMonstro();
        Random random = new Random();
        if (batalha.getStatus() != StatusBatalha.EM_ANDAMENTO){
            throw new IllegalStateException("Batalha finalizada");
        }

        int danoPlayer = Math.max(random.nextInt( 2,p.getDano() +1) - m.getDefesa(), 1);
        batalha.setVidaAtualMonstro(Math.max(batalha.getVidaAtualMonstro() - danoPlayer,0));

        if (batalha.getVidaAtualMonstro() == 0) {
            batalha.setTurno(batalha.getTurno() + 1);
            batalha.setStatus(StatusBatalha.VITORIA);
            p.setXp(p.getXp() + m.getXp());
            var moedaBonus = new  BigDecimal( String.valueOf(random.nextInt(11)+1));
            var moedaAtual = new  BigDecimal(String.valueOf(p.getMoeda()));
            p.setMoeda(moedaAtual.add(moedaBonus));
            return mapper.toDTO(batalha,0,danoPlayer,moedaBonus);
        }

        var danoMonstro = Math.max(random.nextInt(2,m.getDano()+1) - p.getDefesa(), 3);
        p.setVida(Math.max(p.getVida() - danoMonstro,0));

        if (p.getVida() == 0) {
            batalha.setStatus(StatusBatalha.DERROTA);

        }

        batalha.setTurno(batalha.getTurno() + 1);
        return mapper.toDTO(batalha,danoMonstro,danoPlayer,null);

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
