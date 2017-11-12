/*
 * Copyright 2017 Aleksei Balan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package info.ab.copyfolder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * The Class copyfolder.Main.
 *
 * @author Aleksei Balan
 */
@lombok.Getter
@lombok.Setter
public class Main {

  private String userName;

  public static void main(String[] args) {
    try {
      try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
        // https://github.com/jacoco/jacoco/pull/500
        // Case that was already presented in our tests and not filtered -
        // when body of try-with-resources is empty
        stream.size();
      }

      // use Apache Commons copyDirectory to copy folder
      org.apache.commons.io.FileUtils.copyDirectory(new File(args[0]), new File(args[1]));

    } catch (final IOException e) {
      // rethrow runtime exception
      throw new IllegalArgumentException(e);
    }

  }

}
