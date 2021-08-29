package za.web.skerwe.adventofcode2020;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Day15Test {

  private Day15 classUnderTest;
  private static final int THE_2020TH_NUMBER_SPOKEN = 2020;
  private static final int THE_30000000TH_NUMBER_SPOKEN = 30_000_000;

  @Test
  void the2020thNumberSpokenPart1() {
    classUnderTest = new Day15(Day15Test.THE_2020TH_NUMBER_SPOKEN);
    // assertEquals(436, classUnderTest.partOne(new int[] { 0, 3, 6 }));
    // assertEquals(1, classUnderTest.partOne(new int[] { 1, 3, 2 }));
    // assertEquals(10, classUnderTest.partOne(new int[] { 2, 1, 3 }));
    // assertEquals(27, classUnderTest.partOne(new int[] { 1, 2, 3 }));
    // assertEquals(78, classUnderTest.partOne(new int[] { 2, 3, 1 }));
    // assertEquals(438, classUnderTest.partOne(new int[] { 3, 2, 1 }));
    assertEquals(1836, classUnderTest.process(new int[] { 3, 1, 2 }));
  }

  @Test
  void the30000000thNumberSpokenPart2() {
    classUnderTest = new Day15(Day15Test.THE_30000000TH_NUMBER_SPOKEN);
    // assertEquals(175594, classUnderTest.partOne(new int[] { 0, 3, 6 }));
    // assertEquals(2578, classUnderTest.partOne(new int[] { 1, 3, 2 }));
    // assertEquals(3544142, classUnderTest.partOne(new int[] { 2, 1, 3 }));
    // assertEquals(261214, classUnderTest.partOne(new int[] { 1, 2, 3 }));
    // assertEquals(6895259, classUnderTest.partOne(new int[] { 2, 3, 1 }));
    // assertEquals(18, classUnderTest.partOne(new int[] { 3, 2, 1 }));
    assertEquals(362, classUnderTest.process(new int[] { 3, 1, 2 }));
  }
}
