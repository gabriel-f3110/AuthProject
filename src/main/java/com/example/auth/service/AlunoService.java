package com.example.auth.service;

import com.example.auth.entity.Aluno;
import com.example.auth.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> getAlunos() {
        return alunoRepository.findAll();
    }

    public void createAluno(Aluno aluno) {
        alunoRepository.save(aluno);
    }
}
