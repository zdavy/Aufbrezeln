package Helpers;

import java.io.File;
import java.io.PrintWriter;

public class FileHelper {
  public static void createFile(String filename) throws Exception {
    new File(filename).createNewFile();
    PrintWriter writer = new PrintWriter(filename, "UTF-8");
    writer.println("test");
    writer.close();
  }

  public static void createDirectory(String filename) throws Exception {
    new File(filename).mkdir();
  }

  public static void destroyFile(String filename) {
    new File(filename).delete();
  }
}
