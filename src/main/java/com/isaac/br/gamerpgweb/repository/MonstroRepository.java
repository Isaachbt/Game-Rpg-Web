package com.isaac.br.gamerpgweb.repository;

import com.isaac.br.gamerpgweb.entitys.Monstro;
import com.isaac.br.gamerpgweb.entitys.enums.Enum_rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MonstroRepository extends JpaRepository<Monstro, UUID> {

    @Query(value = """
        SELECT *
        FROM monstro
        WHERE monstro_rank = :rank
        ORDER BY RAND()
        LIMIT 1
        """, nativeQuery = true)
    Optional<Monstro> findRandomByRank(@Param("rank") String rank);


}
