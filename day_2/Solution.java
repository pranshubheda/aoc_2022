package day_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/pbheda/Documents/aoc_2022/day_2/input.txt");
        Scanner sc = new Scanner(f);

        // ArrayList<Integer> opponentPlays = new ArrayList<>();
        // ArrayList<Integer> myPlays = new ArrayList<>();

        int totalScore = 0;

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().toUpperCase().split(" ");
            Integer opponent = data[0].charAt(0) - 'A' + 1;
            Integer me = data[1].charAt(0) - 'X' + 1;

            int playScore = me;
            int roundScore = 0;

            if (me == opponent) {
                roundScore = 3;
            } else {
                switch (me) {
                    case 1:
                        roundScore = opponent == 3 ? 6 : 0;
                        break;
                    case 2:
                        roundScore = opponent == 1 ? 6 : 0;
                        break;
                    case 3:
                        roundScore = opponent == 2 ? 6 : 0;
                        break;
                }
            }
            
            int score = roundScore + playScore;

            totalScore += score;

            System.out.println(String.format("%d %d => %d %d %d", opponent, me, playScore, roundScore, totalScore));
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