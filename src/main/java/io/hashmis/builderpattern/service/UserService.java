/*
 *    Copyright [2021] [Shehzad Hashmi]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.hashmis.builderpattern.service;

import io.hashmis.builderpattern.constant.Constants;
import io.hashmis.builderpattern.domain.User;
import io.hashmis.builderpattern.dto.UserDTO;
import io.hashmis.builderpattern.exception.BadRequestException;
import io.hashmis.builderpattern.exception.UserNotFoundException;
import io.hashmis.builderpattern.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A simple service for the {@link User} CRUD operations using h2 in-memory datasource.
 *
 * @author shehzadhashmis
 */
@Slf4j
@Service
public class UserService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Gets the list of all users.
   *
   * @return The list of all users.
   */
  public List<User> getAllUsers() {
    log.info("Inside UserService.getAllUsers");
    return userRepository.findAll();
  }

  /**
   * Creates a new user.
   *
   * @param userDTO The {@link UserDTO} containing user information.
   * @return The {@link UserDTO} containing user information.
   */
  public void createUser(UserDTO userDTO) {
    log.info(
        "Inside UserService.createUser with firstName: {}, lastName: {} and email: {}",
        userDTO.getFirstName(),
        userDTO.getLastName(),
        userDTO.getEmail());
    userRepository.save(userDTO.toUser());
  }

  /**
   * Updates an existing user information.
   *
   * @param userDTO The {@link UserDTO} containing user information.
   */
  public void updateUser(UserDTO userDTO) {
    log.info(
        "Inside UserService.updateUser with id: {}, firstName: {}, lastName: {} and email: {}",
        userDTO.getId(),
        userDTO.getFirstName(),
        userDTO.getLastName(),
        userDTO.getEmail());
    Optional<User> userResult = userRepository.findById(userDTO.getId());
    userResult.ifPresentOrElse(
        user -> {
          userRepository.save(userDTO.toUser());
        },
        () -> {
          log.error(Constants.USER_NOT_FOUND_ERROR_MSG + userDTO.getId());
          throw new UserNotFoundException(Constants.USER_NOT_FOUND_ERROR_MSG + userDTO.getId());
        });
  }

  /**
   * Finds a user by id.
   *
   * @param id The user id.
   * @return The {@link UserDTO} containing user information.
   */
  public UserDTO findUserById(Long id) {
    log.info("Inside UserService.findUserById with id: {}", id);
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      return user.get().toUserDTO();
    } else {
      log.error(Constants.USER_NOT_FOUND_ERROR_MSG + id);
      throw new UserNotFoundException(Constants.USER_NOT_FOUND_ERROR_MSG + id);
    }
  }

  /**
   * Validates user request.
   *
   * @param userDTO The {@link UserDTO} containing user information.
   */
  public void validateUserRequest(UserDTO userDTO) {
    if (Objects.isNull(userDTO.getId()) || userDTO.getId().longValue() == 0) {
      throw new BadRequestException("Incorrect id provided!");
    }
    if (Objects.isNull(userDTO.getFirstName())) {
      throw new BadRequestException("Incorrect firstName provided!");
    }
    if (Objects.isNull(userDTO.getLastName())) {
      throw new BadRequestException("Incorrect lastName provided!");
    }
    if (Objects.isNull(userDTO.getEmail())) {
      throw new BadRequestException("Incorrect email provided!");
    }
  }
}
