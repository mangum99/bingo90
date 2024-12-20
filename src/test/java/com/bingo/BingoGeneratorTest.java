package com.bingo;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BingoGeneratorTest {
  @Test
  void validateRules() {
    var strip = new BingoGenerator().generate();

    validateAllNumbersArePresent(strip);
    validateEachColumnHasCorrectCountOfNumbers(strip);
    validateEachRowHasCorrectCountOfNumbers(strip);
    validateIfTicketColumnHasAtLeastOneNumber(strip);
    validateNumbersAreInAscendingOrder(strip);
  }

  private void validateNumbersAreInAscendingOrder(int[][] strip) {
    for (int column = 0; column < 9; column++) {
      int previousNumber = 0;
      for (int row = 0; row < 18; row++) {
        var currentNumber = strip[column][row];
        if (currentNumber > 0) {
          Assertions.assertTrue(currentNumber > previousNumber);
          previousNumber = currentNumber;
        }
      }
    }
  }

  private void validateIfTicketColumnHasAtLeastOneNumber(int[][] strip) {
    for (int column = 0; column < 9; column++) {
      for (int ticket = 0; ticket < 6; ticket++) {
        var atLeastOneNumber = Stream.of(
                strip[column][ticket * 3],
                strip[column][ticket * 3 + 1],
                strip[column][ticket * 3 + 2])
            .anyMatch(n -> n > 0);
        Assertions.assertTrue(atLeastOneNumber);
      }
    }
  }

  private void validateEachRowHasCorrectCountOfNumbers(int[][] strip) {
    for (int row = 0; row < 18; row++) {
      int numbersAdded = 0;
      for (int column = 0; column < 9; column++) {
        if (strip[column][row] > 0) {
          numbersAdded++;
        }
      }
      Assertions.assertEquals(5, numbersAdded);
    }
  }

  private void validateEachColumnHasCorrectCountOfNumbers(int[][] strip) {
    for (int column = 0; column < 9; column++) {
      int numbersToAdd = column == 0 ? 9 : column == 8 ? 11 : 10;
      int numbersAdded = 0;

      for (int row = 0; row < 18; row++) {
        if (strip[column][row] > 0) {
          numbersAdded++;
        }
      }

      Assertions.assertEquals(numbersToAdd, numbersAdded);
    }
  }

  private void validateAllNumbersArePresent(int[][] strip) {
    int[] oneDimensionalArray = Arrays.stream(strip)
        .flatMapToInt(Arrays::stream)
        .filter(v -> v != 0)
        .toArray();

    Assertions.assertEquals(90, oneDimensionalArray.length);

        /*
            following check makes sure that:
            - there are no duplicate numbers
            - the strip contains all numbers from 1 to 90
        */
    var checkAgainst = IntStream.range(1, 91).toArray();
    Assertions.assertArrayEquals(oneDimensionalArray, checkAgainst);

  }
}
