package com.example.acquario_simulator.service;

import com.example.acquario_simulator.repository.PesceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PesceService {

    @Autowired
    private PesceRepository pesceRepository;
}
