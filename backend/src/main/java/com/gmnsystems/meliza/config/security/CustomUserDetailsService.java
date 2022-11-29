package com.gmnsystems.meliza.config.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gmnsystems.meliza.models.RoleModel;
import com.gmnsystems.meliza.models.UserModel;
import com.gmnsystems.meliza.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // Classe que faz a linha de frente com o banco de dados
  // aqui se encontra o serviço que, junto ao repositório de user,
  // buscará o usuário e encaminhará para as próximas etapas da
  // autenticação
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserModel user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
  }

  // Classe que mapeará as roles do usuário para o tipo GrantedAuthority
  // recebe uma lista de roles como parâmetro, faz um stream na lista
  // e cada item (role) será instanciado para o novo tipo.
  // No final, devolverá a stream para a forma de lista novamente,
  // assim como o método user.getRoles() espera
  private Collection<GrantedAuthority> mapRolesToAuthorities(List<RoleModel> roles) {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
  }

}
