package com.gmnsystems.meliza.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gmnsystems.meliza.dto.UserDTO;
import com.gmnsystems.meliza.models.UserModel;
import com.gmnsystems.meliza.repositories.UserRepository;
import com.gmnsystems.meliza.services.exceptions.DatabaseException;
import com.gmnsystems.meliza.services.exceptions.DuplicateEntryException;
import com.gmnsystems.meliza.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  // Listar todos os usuários
  public List<UserModel> getUsers() {
    return userRepository.findAll();
  }

  // Buscar um usuário pelo id
  public UserModel findById(Long id) {
    Optional<UserModel> user = userRepository.findById(id);
    return user.orElseThrow(() -> new ResourceNotFoundException(id));
  }

  // Cadastrar um usuário
  public UserModel registerUser(UserModel user) {
    try {
      userRepository.save(user);
    } catch (DataIntegrityViolationException e) {
      throw new DuplicateEntryException();
    }
    return user;
  }

  // Atualizar um usuário
  public UserModel update(UserModel user) {
    try {
      // Diferente de findById, o método abaixo não busca
      // diretamente do banco de dados. Apenas prepara o
      // objeto para que seja monitorado e depois inserido
      // no banco de dados.
      UserModel entity = userRepository.getReferenceById(user.getIdUser());
      updateData(entity, user);
      return userRepository.save(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(user.getIdUser());
    }
  }

  // Método que atualiza a entidade existente com base
  // na nova
  private void updateData(UserModel entity, UserModel user) {

    // Por fim, setando a nova informação
    if (user.getName() != null)
      entity.setName(user.getName());
    if (user.getEmail() != null)
      entity.setEmail(user.getEmail());
    if (user.getPassword() != null)
      entity.setPassword(user.getPassword());
  }

  // Deletar um usuário
  public void delete(Long id) {
    try {
      userRepository.deleteById(id);
    } catch (NoSuchElementException e) {
      throw new ResourceNotFoundException(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }
  }

  public UserModel fromDTO(UserDTO userDTO) {
    return new UserModel(userDTO.getIdUser(), userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
  }
}
