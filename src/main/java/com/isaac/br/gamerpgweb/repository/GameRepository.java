package com.isaac.br.gamerpgweb.repository;

import com.isaac.br.gamerpgweb.entitys.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity,Integer> {
}
