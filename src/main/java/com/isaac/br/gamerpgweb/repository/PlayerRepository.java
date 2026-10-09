package com.isaac.br.gamerpgweb.repository;

import com.isaac.br.gamerpgweb.entitys.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
}
