package com.example.auth.service;

import com.example.auth.entity.User;
import com.example.auth.entity.dto.Token;
import com.example.auth.entity.dto.UserDTO;
import com.example.auth.entity.dto.UserResponseDTO;
import com.example.auth.entity.dto.UserUpdateDTO;
import com.example.auth.entity.enums.Role;
import com.example.auth.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ModelMapper modelMapper;

    public void register(UserDTO userDTO) {
        verifyUser(userDTO.getUsername());
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.DEFAULT);

        userRepository.save(user);
    }

    public Token getAuthorizationToken(UserDTO userDTO) {
        UsernamePasswordAuthenticationToken userAuthencation =
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        authenticationManager.authenticate(userAuthencation);

        User user = userRepository
                .findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(("User Not Found")));
        String jwtToken = jwtService.generateToken(user);
        return new Token(jwtToken);
    }

    public UserResponseDTO editarUsuario(Long id, UserUpdateDTO userUpdateDTO) {
        User user = getById(id);
        modelMapper.map(userUpdateDTO, user);

        user = userRepository.save(user);

        return modelMapper.map(user, UserResponseDTO.class);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado!"));
    }

    private void verifyUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            throw new IllegalArgumentException("Usuario já cadastrado");
        }
    }
}
