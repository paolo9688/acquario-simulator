package com.example.acquario_simulator.repository;

import com.example.acquario_simulator.entity.Acquario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquarioRepository extends JpaRepository<Acquario, Long> {

}
