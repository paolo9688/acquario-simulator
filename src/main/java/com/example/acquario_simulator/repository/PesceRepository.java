package com.example.acquario_simulator.repository;

import com.example.acquario_simulator.entity.Pesce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesceRepository extends JpaRepository<Pesce, Long> {

}