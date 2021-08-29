package za.web.skerwe.adventofcode2020;

import java.io.File;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Day16Test {

  private Day16 classUnderTest;

  @Test
  void testPart1() {
    classUnderTest = new Day16();

    File inputFile = new File("src/test/resources/test-input-day16.txt");

    assertEquals(71, classUnderTest.partOne(inputFile.getAbsolutePath()));
  }

}
