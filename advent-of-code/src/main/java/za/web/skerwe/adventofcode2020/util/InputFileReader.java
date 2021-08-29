package za.web.skerwe.adventofcode2020.util;

import java.util.List;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class InputFileReader {
  public static String[] readInputFile(String fileName) throws IOException {
    List<String> lines = new ArrayList<>();

    BufferedReader inputStream = new BufferedReader(new FileReader(fileName));

    String line;
    while ((line = inputStream.readLine()) != null) {
      lines.add(line);
    }

    inputStream.close();

    return lines.toArray(new String[0]);
  }
}
