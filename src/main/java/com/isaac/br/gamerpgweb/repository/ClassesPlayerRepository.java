package com.isaac.br.gamerpgweb.repository;

import com.isaac.br.gamerpgweb.entitys.ClassesPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesPlayerRepository extends JpaRepository<ClassesPlayer, Integer> {
}
