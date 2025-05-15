package com.example.acquario_simulator.service;

import com.example.acquario_simulator.repository.AcquarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcquarioService {

    @Autowired
    private AcquarioRepository acquarioRepository;
}
