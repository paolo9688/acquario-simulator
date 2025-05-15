package com.example.acquario_simulator.controller;

import com.example.acquario_simulator.service.PesceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pesci")
public class PesceController {

    private PesceService pesceService;
}
