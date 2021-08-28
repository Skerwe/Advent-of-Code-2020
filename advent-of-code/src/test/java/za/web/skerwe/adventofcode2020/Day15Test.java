package za.web.skerwe.adventofcode2020;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Day15Test {

  private Day15 classUnderTest;
  private static final int THE_2020TH_NUMBER_SPOKEN = 2020;
  private static final int THE_30000000TH_NUMBER_SPOKEN = 30000000;

  @Test
  void the2020thNumberSpokenExampleTest1() {
    classUnderTest = new Day15(Day15Test.THE_2020TH_NUMBER_SPOKEN);
    assertEquals(436, classUnderTest.partOne(new int[] { 0, 3, 6 }));
  }

  @Test
  void the2020thNumberSpokenExampleTest2() {
    classUnderTest = new Day15(Day15Test.THE_2020TH_NUMBER_SPOKEN);
    assertEquals(1, classUnderTest.partOne(new int[] { 1, 3, 2 }));
  }

  @Test
  void the2020thNumberSpokenExampleTest3() {
    classUnderTest = new Day15(Day15Test.THE_2020TH_NUMBER_SPOKEN);
    assertEquals(10, classUnderTest.partOne(new int[] { 2, 1, 3 }));
  }

  @Test
  void the2020thNumberSpokenExampleTest4() {
    classUnderTest = new Day15(Day15Test.THE_2020TH_NUMBER_SPOKEN);
    assertEquals(27, classUnderTest.partOne(new int[] { 1, 2, 3 }));
  }

  @Test
  void the2020thNumberSpokenExampleTest5() {
    classUnderTest = new Day15(Day15Test.THE_2020TH_NUMBER_SPOKEN);
    assertEquals(78, classUnderTest.partOne(new int[] { 2, 3, 1 }));
  }

  @Test
  void the2020thNumberSpokenExampleTest6() {
    classUnderTest = new Day15(Day15Test.THE_2020TH_NUMBER_SPOKEN);
    assertEquals(438, classUnderTest.partOne(new int[] { 3, 2, 1 }));
  }

  @Test
  void the2020thNumberSpokenExampleTest7() {
    classUnderTest = new Day15(Day15Test.THE_2020TH_NUMBER_SPOKEN);
    assertEquals(1836, classUnderTest.partOne(new int[] { 3, 1, 2 }));
  }
}
