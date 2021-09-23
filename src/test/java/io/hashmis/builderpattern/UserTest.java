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

package io.hashmis.builderpattern;

import io.hashmis.builderpattern.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/** @author shehzadhashmis */
@Slf4j
@SpringBootTest
class UserTest {

  @Test
  void builderTest() {
    User user =
        User.builder().id(1L).firstName("John").lastName("Wick").email("jwhick@savior.com").build();

    Assertions.assertEquals(1L, user.getId());
    Assertions.assertEquals("John", user.getFirstName());
    Assertions.assertEquals("Wick", user.getLastName());
    Assertions.assertEquals("jwhick@savior.com", user.getEmail());
    log.info("User created: {}", user.toUserDTO());
  }

  @Test
  void setterTest() {
    User user = new User();
    user.setId(1L);
    user.setFirstName("John");
    user.setLastName("Wick");
    user.setEmail("jwhick@savior.com");

    Assertions.assertEquals(1L, user.getId());
    Assertions.assertEquals("John", user.getFirstName());
    Assertions.assertEquals("Wick", user.getLastName());
    Assertions.assertEquals("jwhick@savior.com", user.getEmail());
    log.info("User created: {}", user);
  }
}
