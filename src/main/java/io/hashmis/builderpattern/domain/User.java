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

package io.hashmis.builderpattern.domain;

import io.hashmis.builderpattern.dto.UserDTO;
import lombok.*;
import javax.persistence.*;

/**
 * An entity class for the user.
 *
 * @author shehzadhashmis
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email", nullable = false, length = 200)
  private String email;

  /**
   * Builds an object of {@link UserDTO} with the {@link User} field values so that we can pass
   * {@link UserDTO} instead of the original {@link User} object.
   *
   * @return The {@link UserDTO} having a copy of the field values from the {@link User} entity.
   */
  public UserDTO toUserDTO() {
    return new UserDTO()
        .toBuilder()
        .id(this.id)
        .firstName(this.firstName)
        .lastName(this.lastName)
        .email(this.email)
        .build();
  }
}
