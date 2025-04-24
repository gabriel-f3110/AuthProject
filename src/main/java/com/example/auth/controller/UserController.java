package com.example.auth.controller;

import com.example.auth.entity.dto.Token;
import com.example.auth.entity.dto.UserDTO;
import com.example.auth.entity.dto.UserResponseDTO;
import com.example.auth.entity.dto.UserUpdateDTO;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("registrar")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        userService.register(userDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("logar")
    public ResponseEntity<Token> login(@RequestBody UserDTO userDTO) {
        Token token = userService.getAuthorizationToken(userDTO);
        return ResponseEntity.ok().body(token);
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<UserResponseDTO> editarUsuario(@PathVariable("id") Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        UserResponseDTO userResponseDTO = userService.editarUsuario(id, userUpdateDTO);
        return ResponseEntity.ok().body(userResponseDTO);
    }

}
