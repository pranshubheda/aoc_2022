package day_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Solution2 {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/pbheda/Documents/aoc_2022/day_2/input.txt");
        Scanner sc = new Scanner(f);

        // ArrayList<Integer> opponentPlays = new ArrayList<>();
        // ArrayList<Integer> myPlays = new ArrayList<>();

        int totalScore = 0;

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().toUpperCase().split(" ");
            Integer opponent = data[0].charAt(0) - 'A' + 1;
            Integer result = data[1].charAt(0) - 'X' + 1;

            int roundScore = result == 2 ? 3 : (result == 1 ? 0 : 6);
            int playScore = 0;

            int[] win = new int[] { -9999999, 2, 3, 1 };
            int[] lose = new int[] { -9999999, 3, 1, 2 };

            switch (result) {
                case 1:
                    // lose
                    playScore = lose[opponent];
                    break;
                case 2:
                    // draw
                    playScore = opponent;
                    break;
                case 3:
                    // win
                    playScore = win[opponent];
                    break;
            }
            
            int score = roundScore + playScore;

            totalScore += score;

            System.out.println(String.format("%d %d => %d %d %d", opponent, result, playScore, roundScore, totalScore));
        }

        System.out.println(totalScore);
    }
}
// R
// P
// S

// 1 > 3

// 2 > 1

// 3 > 2