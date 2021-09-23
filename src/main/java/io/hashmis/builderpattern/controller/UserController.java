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

package io.hashmis.builderpattern.controller;

import io.hashmis.builderpattern.domain.User;
import io.hashmis.builderpattern.dto.UserDTO;
import io.hashmis.builderpattern.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * A {@link RestController} for the user CRUD APIs using H2 in-memory datasource.
 *
 * @author shehzadhashmis
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

  private UserService userService;

  public UserController(UserService service) {
    this.userService = service;
  }

  @PostMapping("/user")
  public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
    userService.createUser(userDTO);
    return new ResponseEntity<>("User created successfully", new HttpHeaders(), HttpStatus.OK);
  }

  @PutMapping("/user")
  public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
    userService.validateUserRequest(userDTO);
    userService.updateUser(userDTO);
    return new ResponseEntity<>("User updated successfully", new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    return new ResponseEntity<>(userService.getAllUsers(), new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<UserDTO> findUserByCriteria(@PathVariable Long id) {
    return new ResponseEntity<>(userService.findUserById(id), new HttpHeaders(), HttpStatus.OK);
  }
}
