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
  private HashMap<Integer, ArrayList<String>> ticketForm = new HashMap<>();
  private ArrayList<Integer> markFieldFound = new ArrayList<>();

  public static void main(String... args) {
    Day16 day16Part1 = new Day16();

    File inputFile = new File("src/main/resources/input-day16.txt");
    int ticketScanningErrorRate = day16Part1.partOne(inputFile.getAbsolutePath());
    logger.warn(String.format("The ticket scanning error rate is '%s'", ticketScanningErrorRate));

    long departureFieldsValue = day16Part1.partTwo(inputFile.getAbsolutePath());
    logger.warn(String.format("Multiply the six departure field values together is '%s'", departureFieldsValue));
  }

  public int partOne(String inputFile) {
    buildDataModel(readInputFile(inputFile));
    return calculateTicketScanningErrorRate();
  }

  public long partTwo(String inputFile) {
    buildDataModel(readInputFile(inputFile));
    calculateTicketScanningErrorRate();
    buildTicketForm();
    reduceTicketForm();
    return multiplyDepartureFieldValuesTogether();
  }

  private void printTicketForm() {
    for (int position = 0; position < yourTicket.size(); position++) {
      String names = "";
      for (String name : ticketForm.get(position)) {
        names += ":" + name;
      }
      logger.debug(String.format("%d -> %s", position, names));
    }
  }

  /**
   * Part 1: Find all invalid tickets to calculate ticketScanningErrorRate.
   * 
   * Part 2: Remove all invalid tickets.
   * 
   * @return ticketScanningErrorRate
   */
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
        }
      }

    }
    logger.warn("Removing " + errorTicketsToRemove.size());
    nearbyTickets.removeAll(errorTicketsToRemove);
    logger.warn("Valid tickets: " + nearbyTickets.size());
    return ticketScanningErrorRate;
  }

  private void buildTicketForm() {
    for (String fieldName : ticketData.keySet()) {
      for (int position = 0; position < yourTicket.size(); position++) {
        if (determinePosibleTickePositions(ticketData.get(fieldName), position)) {
          ArrayList<String> fieldNames = ticketForm.get(position);
          if (fieldNames == null) {
            fieldNames = new ArrayList<>();
          }
          fieldNames.add(fieldName);
          ticketForm.put(position, fieldNames);
        }
      }
    }
  }

  /**
   * If the postion (column) of each ticket (row) is outside the ranges of the
   * dataset return false.
   * 
   * @param dataSet  the current field data ranges.
   * @param position the current column.
   * @return false if the entire row is outside the range of the dataset.
   */
  private boolean determinePosibleTickePositions(int[][] dataSet, int position) {
    boolean validposition = true;

    for (ArrayList<Integer> arrayList : nearbyTickets) {

      if (arrayList.get(position) < dataSet[0][0]
          || arrayList.get(position) > dataSet[0][1] && arrayList.get(position) < dataSet[1][0]
          || arrayList.get(position) > dataSet[1][1]) {
        validposition = false;
        break;
      }
    }

    return validposition;
  }

  private void reduceTicketForm() {
    while (checkIfTicketFormStillContainDuplicates()) {
      printTicketForm();
      String fieldName = findSingleFieldEntry();
      removeFieldFromOtherPostions(fieldName);
      logger.warn("");
    }
  }

  private String findSingleFieldEntry() {
    String foundFieldName = "";
    for (Integer position : ticketForm.keySet()) {
      if (ticketForm.get(position).size() == 1 && !markFieldFound.contains(position)) {
        foundFieldName = ticketForm.get(position).get(0);
        markFieldFound.add(position);
      }
    }
    return foundFieldName;
  }

  private void removeFieldFromOtherPostions(String fieldName) {
    for (Integer position : ticketForm.keySet()) {
      if (ticketForm.get(position).size() > 1) {
        ticketForm.get(position).remove(fieldName);
      }
    }
  }

  private boolean checkIfTicketFormStillContainDuplicates() {
    boolean containsDuplicates = false;
    for (int position = 0; position < yourTicket.size(); position++) {
      if (ticketForm.get(position).size() > 1) {
        containsDuplicates = true;
        break;
      }
    }

    return containsDuplicates;
  }

  private long multiplyDepartureFieldValuesTogether() {
    long departureFieldsValue = 1;

    for (Integer position : ticketForm.keySet()) {
      if (ticketForm.get(position).size() == 1) {
        String fieldName = ticketForm.get(position).get(0);
        logger.debug(String.format("%s has position %d", fieldName.toUpperCase(), position + 1));
        if (fieldName.startsWith("departure")) {
          logger.info(departureFieldsValue + "*" + yourTicket.get(position));
          departureFieldsValue *= yourTicket.get(position);
        }
      }
    }

    return departureFieldsValue;
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

    logger.info("yourTicket=" + yourTicket.size());
    logger.info("nearbyTickets=" + nearbyTickets.size());
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
    ArrayList<Integer> ticketValues = new ArrayList<>();
    for (String value : ticketValuesRaw) {
      ticketValues.add(Integer.parseInt(value));
    }
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
