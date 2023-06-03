package com.org.latelier.repository;

import com.org.latelier.models.entities.TennisPlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TennisPlayerRepository extends JpaRepository<TennisPlayerEntity, Long> {
}
