package za.web.skerwe.adventofcode2020;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.HashMap;

/**
 * Day 15: Rambunctious Recitation
 */
public class Day15 {

  private static Logger logger = LogManager.getLogger();

  private int startTurn = 0;
  private int nthSpokenNumber;

  public static void main(String... args) {
    Day15 day15Part1 = new Day15(2020);
    int the2020thSpokenNumber = day15Part1.process(new int[] { 0, 1, 4, 13, 15, 12, 16 });
    logger.warn(String.format("The 2020th spoken number is '%s'", the2020thSpokenNumber));

    Day15 day15Part2 = new Day15(30_000_000);
    int the30000000thSpokenNumber = day15Part2.process(new int[] { 0, 1, 4, 13, 15, 12, 16 });
    logger.warn(String.format("The 30,000,000th spoken number is '%s'", the30000000thSpokenNumber));
  }

  public Day15(int nthSpokenNumber) {
    this.nthSpokenNumber = nthSpokenNumber;
  }

  public int process(int[] input) {
    HashMap<Integer, Integer> memory = new HashMap<>(nthSpokenNumber);

    IntStream inputStream = Arrays.stream(input);
    inputStream.forEach((int currentNumber) -> {
      startTurn += 1;
      memory.put(currentNumber, this.startTurn);
    });

    int lastSpokenNumber = input[input.length - 1];
    int currentSpokenNumber = -1;

    while (startTurn < this.nthSpokenNumber) {
      currentSpokenNumber = 0;

      Integer lastSpokenTurn = memory.get(lastSpokenNumber);
      if (lastSpokenTurn != null) {
        currentSpokenNumber = startTurn - lastSpokenTurn;
      }

      memory.put(lastSpokenNumber, startTurn);

      startTurn += 1;
      lastSpokenNumber = currentSpokenNumber;
    }

    return currentSpokenNumber;
  }

}
