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
    Day15 day15 = new Day15(2020);
    int the2020thSpokenNumber = day15.partOne(new int[] { 0, 1, 4, 13, 15, 12, 16 });
    logger.warn(String.format("The 2020th spoken number is '%s'", the2020thSpokenNumber));
  }

  public Day15(int nthSpokenNumber) {
    this.nthSpokenNumber = nthSpokenNumber;
  }

  public int partOne(int[] input) {
    HashMap<Integer, Integer> memory = new HashMap<>();

    logger.info("Initialise memory with all starting numbers ...");
    IntStream inputStream = Arrays.stream(input);
    inputStream.forEach((int currentNumber) -> {
      this.startTurn += 1;
      memory.put(currentNumber, this.startTurn);
    });

    logger.info(String.format("Starting with %s on turn %s", input[input.length - 1], this.startTurn));
    return playMemoryGame(this.startTurn, memory, input[input.length - 1]);
  }

  private int playMemoryGame(int turn, HashMap<Integer, Integer> memory, int lastSpokenNumber) {
    int currentSpokenNumber = 0;

    Integer lastSpokenTurn = memory.get(lastSpokenNumber);
    if (lastSpokenTurn != null) {
      currentSpokenNumber = turn - lastSpokenTurn;
    }

    memory.put(lastSpokenNumber, turn);

    turn += 1;
    logger.debug(String.format("Number %s was spoken for turn %s", currentSpokenNumber, turn));

    if (turn < nthSpokenNumber) {
      return playMemoryGame(turn, memory, currentSpokenNumber);
    }

    return currentSpokenNumber;
  }

}
