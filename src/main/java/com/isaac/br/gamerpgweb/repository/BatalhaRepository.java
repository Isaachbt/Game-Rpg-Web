package com.isaac.br.gamerpgweb.repository;

import com.isaac.br.gamerpgweb.entitys.Batalha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BatalhaRepository extends JpaRepository<Batalha, UUID> {

}
