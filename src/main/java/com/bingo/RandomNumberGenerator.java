package com.bingo;

import java.util.Random;

public class RandomNumberGenerator {
  private static final int RANDOM_COLUMNS = 4;
  public static int[] randomColumns() {
    Random random = new Random();
    int[] numbers = new int[RANDOM_COLUMNS];

    for (int i = 0; i < numbers.length; i++) {
      numbers[i] = random.nextInt(9); // Generates a number between 0 (inclusive) and 9 (exclusive)
    }

    return numbers;
  }
}
