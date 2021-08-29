package za.web.skerwe.adventofcode2020;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Day16Test {

  private Day16 classUnderTest;
  private File inputFile;

  @BeforeEach
  void setup() {
    classUnderTest = new Day16();
  }

  @Test
  void testPartOne() {
    inputFile = new File("src/test/resources/test-input-day16-part1.txt");
    assertEquals(71, classUnderTest.partOne(inputFile.getAbsolutePath()));
  }

  @Test
  void testPartTwo() {
    inputFile = new File("src/test/resources/test-input-day16-part2.txt");
    assertTrue(0 < classUnderTest.partTwo(inputFile.getAbsolutePath()));
  }

}
