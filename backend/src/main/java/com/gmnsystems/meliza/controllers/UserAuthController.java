package com.gmnsystems.meliza.controllers;

import java.net.URI;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.gmnsystems.meliza.config.security.JWTGenerator;
import com.gmnsystems.meliza.dto.AuthResponseDTO;
import com.gmnsystems.meliza.dto.LoginDTO;
import com.gmnsystems.meliza.dto.RegisterDTO;
import com.gmnsystems.meliza.models.RoleModel;
import com.gmnsystems.meliza.models.UserModel;
import com.gmnsystems.meliza.repositories.RoleRepository;
import com.gmnsystems.meliza.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

  private AuthenticationManager authenticationManager;
  private UserService userService;
  private RoleRepository roleRepository;
  private PasswordEncoder passwordEncoder;
  private JWTGenerator jwtGenerator;

  @Autowired
  public UserAuthController(AuthenticationManager authenticationManager, UserService userService,
      RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtGenerator = jwtGenerator;
  }

  // Endpoint para registrar um usuário
  @PostMapping("/signup")
  public ResponseEntity<String> createuser(@RequestBody RegisterDTO registerDTO) {

    UserModel user = new UserModel();
    user.setName(registerDTO.getName());
    user.setEmail(registerDTO.getEmail());
    user.setUsername(registerDTO.getUsername());
    user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

    // Todo novo usuário receberá a role <USER>
    RoleModel roles = roleRepository.findByName("USER").get();
    user.setRoles(Collections.singletonList(roles));

    user = userService.registerUser(user);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getIdUser()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {

    // Lógica de autenticação de um provável usuário
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginDTO.getUsername(),
            loginDTO.getPassword()));

    // SecurityContextHolder é o que irá garantir que o
    // usuário possa manter uma sessão, sem precisar
    // logar todas as vezes que precisar acessar um endpoint
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtGenerator.generateToken(authentication);
    return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
  }
}
