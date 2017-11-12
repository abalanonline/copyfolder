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

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * The test for the class copyfolder.Main.
 *
 * @author Aleksei Balan
 */
public class MainTest {

  private Path srcFolder;
  private Path dstFolder;

  @Before
  public void setUp() throws Exception {
    srcFolder = Files.createTempDirectory("copyfolder-test-src-");
    dstFolder = Files.createTempDirectory("copyfolder-test-dst-");
  }

  @After
  public void tearDown() throws Exception {
    FileUtils.deleteDirectory(srcFolder.toFile());
    FileUtils.deleteDirectory(dstFolder.toFile());
  }

  @Test
  public void testMain() throws Exception {
    final String fileName = UUID.randomUUID().toString();
    Files.createFile(Paths.get(srcFolder.toString(), fileName));
    (new Main()).main(new String[]{srcFolder.toString(), dstFolder.toString()});
    assertTrue("Folder is not copied", Paths.get(dstFolder.toString(), fileName).toFile().isFile());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMainException() {
    (new Main()).main(new String[]{"", ""});
  }

}
