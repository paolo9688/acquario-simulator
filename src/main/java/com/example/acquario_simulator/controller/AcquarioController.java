package com.example.acquario_simulator.controller;

import com.example.acquario_simulator.service.AcquarioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/acquario")
public class AcquarioController {

    private AcquarioService acquarioService;
}
