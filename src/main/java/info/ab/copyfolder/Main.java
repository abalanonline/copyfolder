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

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import static java.nio.file.Files.createTempFile;

/**
 * The Class copyfolder.Main.
 *
 * @author Aleksei Balan
 */
public class Main {

  public static void main(String[] args) throws IOException {
    Launcher launcher = new Launcher();
    try (SessionFactory factory = launcher.launch(Arrays.asList("--headless", "--disable-gpu"))) {
      String context = factory.createBrowserContext();
      try (Session session = factory.create(context)) {
        session.navigate("https://angular.io/");
        session.waitDocumentReady();
        session.wait(1000);
        byte[] content = session
            .getCommand()
            .getPage()
            .printToPDF();
        FileUtils.writeByteArrayToFile(new File("target/webpage.pdf"), content);
      }
    }
  }

}
