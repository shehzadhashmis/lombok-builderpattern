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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A spring boot application to demonstrate the usage of the DTO classes with Lombok's {@link
 * lombok.Builder} annotation.
 *
 * @author shehzadhashmis
 */
@SpringBootApplication
public class BuilderPatternApplication {

  public static void main(String[] args) {
    SpringApplication.run(BuilderPatternApplication.class, args);
  }
}
