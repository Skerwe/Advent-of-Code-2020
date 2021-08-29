package za.web.skerwe.adventofcode2020;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import za.web.skerwe.adventofcode2020.util.InputFileReader;

/**
 * Day 16: Ticket Translation
 */
public class Day16 {

  private static Logger logger = LogManager.getLogger();

  private HashMap<String, int[][]> ticketData = new HashMap<>();
  private ArrayList<Integer> yourTicket = new ArrayList<>();
  private ArrayList<ArrayList<Integer>> nearbyTickets = new ArrayList<>();

  public static void main(String... args) {
    Day16 day16Part1 = new Day16();

    File inputFile = new File("src/main/resources/input-day16.txt");
    int ticketScanningErrorRate = day16Part1.partOne(inputFile.getAbsolutePath());
    logger.warn(String.format("The ticket scanning error rate is '%s'", ticketScanningErrorRate));
  }

  public int partOne(String inputFile) {
    buildDataModel(readInputFile(inputFile));
    return calculateTicketScanningErrorRate();
  }

  private int calculateTicketScanningErrorRate() {
    int ticketScanningErrorRate = 0;
    ArrayList<ArrayList<Integer>> errorTicketsToRemove = new ArrayList<>();

    for (ArrayList<Integer> arrayList : nearbyTickets) {
      for (Integer integer : arrayList) {
        boolean validTicketNumber = false;
        for (int[][] dataSet : ticketData.values()) {
          if (integer >= dataSet[0][0] && integer <= dataSet[0][1]
              || integer >= dataSet[1][0] && integer <= dataSet[1][1]) {
            validTicketNumber = true;
            break;
          }
        }
        if (!validTicketNumber) {
          ticketScanningErrorRate += integer;
          errorTicketsToRemove.add(arrayList);
          break;
        }
      }
    }
    nearbyTickets.removeAll(errorTicketsToRemove);
    return ticketScanningErrorRate;
  }

  /* BUILDING DATA MODEL METHODS */

  private void buildDataModel(String[] inputLines) {

    Pattern pattern = Pattern.compile(": [0-9]*-[0-9]* or [0-9]*-[0-9]*");

    boolean calculatingnearByTickets = false;
    for (int index = 0; index < inputLines.length; index++) {
      if (calculatingnearByTickets) {
        nearbyTickets.add(parseNumbers(inputLines[index].split(",")));
        continue;
      }

      Matcher matcher = pattern.matcher(inputLines[index]);
      if (matcher.find()) {
        logger.debug(String.format("Found Ticket Data: '%s'", inputLines[index]));
        addTicketData(inputLines[index]);
      }

      if (inputLines[index].equals("your ticket:")) {
        index += 1;
        yourTicket = parseNumbers(inputLines[index].split(","));
        continue;
      }

      if (inputLines[index].equals("nearby tickets:")) {
        calculatingnearByTickets = true;
      }

    }

    logger.debug("yourTicket=" + yourTicket.size());
    logger.debug("nearbyTickets=" + nearbyTickets.size());
  }

  private void addTicketData(String ticketDataEntry) {
    String field = ticketDataEntry.substring(0, ticketDataEntry.indexOf(':'));

    int[][] ranges = new int[2][2];
    int partCount = 0;

    String[] fieldParts = ticketDataEntry.substring(ticketDataEntry.indexOf(':') + 1).trim().split(" ");
    for (String part : fieldParts) {
      if (!part.equals("or")) {
        String[] range = part.split("-");
        ranges[partCount][0] = Integer.parseInt(range[0]);
        ranges[partCount][1] = Integer.parseInt(range[1]);
        partCount += 1;
      }
    }

    ticketData.put(field, ranges);
  }

  private ArrayList<Integer> parseNumbers(String[] ticketValuesRaw) {
    String values = "";
    ArrayList<Integer> ticketValues = new ArrayList<>();
    for (String value : ticketValuesRaw) {
      ticketValues.add(Integer.parseInt(value));
      values += value + ":";
    }
    logger.debug(values);
    return ticketValues;
  }

  private String[] readInputFile(String inputFile) {
    try {
      return InputFileReader.readInputFile(inputFile);
    } catch (IOException ex) {
      logger.error(ex.getMessage(), ex);
    }
    return new String[0];
  }

}
