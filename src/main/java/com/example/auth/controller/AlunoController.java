package com.example.auth.controller;

import com.example.auth.entity.Aluno;
import com.example.auth.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;


    @GetMapping("/buscar")
    public ResponseEntity<List<Aluno>> getAlunos() {
        return ResponseEntity.ok().body(alunoService.getAlunos());
    }


    @GetMapping("/criar")
    public ResponseEntity<?> criarAluno(@RequestBody Aluno aluno) {

        alunoService.createAluno(aluno);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
