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

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** @author shehzadhashmis */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
class UserControllerTest {

  private static final String REQUEST_BODY =
      "{\"id\":\"1\", \"firstName\": \"John\",\"lastName\": \"Wick\",\"email\": \"jwick@test.com\"}";

  @Autowired private MockMvc mockMvc;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void createUserTest() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void updateUserTest() throws Exception {
    createUserTest();
    mockMvc
        .perform(
            MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void updateIncorrectUserTest() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"id\":\"999\", \"firstName\": \"John\",\"lastName\": \"Wick\",\"email\": \"jwick@test.com\"}"))
        .andExpect(status().is4xxClientError())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void badRequestExceptionTest() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.request(HttpMethod.PUT, "/api/v1/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"John\"}"))
        .andExpect(status().is4xxClientError())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void getAllUsersTest() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void findUsersByIdTest() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(REQUEST_BODY));

    mockMvc
        .perform(
            MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(REQUEST_BODY))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Wick"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("jwick@test.com"))
        .andExpect(status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void userNotFoundExceptionTest() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/user/555")
                .contentType(MediaType.APPLICATION_JSON)
                .content(REQUEST_BODY))
        .andExpect(status().is4xxClientError())
        .andDo(MockMvcResultHandlers.print());
  }
}
