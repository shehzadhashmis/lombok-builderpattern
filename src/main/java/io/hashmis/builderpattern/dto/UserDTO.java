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

package io.hashmis.builderpattern.dto;

import io.hashmis.builderpattern.domain.User;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO class representing {@link User} fields.
 *
 * @author shehzadhashmis
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDTO implements Serializable {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;

  /**
   * Builds an object of the {@link User} with the {@link UserDTO} values so that we can convert
   * {@link UserDTO} to the {@link User} object directly.
   *
   * @return The {@link User} field values.
   */
  public User toUser() {
    return new User()
        .toBuilder()
        .id(this.id)
        .firstName(this.firstName)
        .lastName(this.lastName)
        .email(this.email)
        .build();
  }
}
