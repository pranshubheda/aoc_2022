package day_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_4/input.txt");
        Scanner sc = new Scanner(f);
        int overlap = 0;

        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            System.out.println(data);
            String[] ranges = data.split(",");

            int[] range_1 = Arrays.stream(ranges[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] range_2 = Arrays.stream(ranges[1].split("-")).mapToInt(Integer::parseInt).toArray();

            System.out.println(Arrays.toString(range_1));
            System.out.println(Arrays.toString(range_2));

            if ((range_1[0] <= range_2[0] && range_1[1] >= range_2[1]) || (range_1[0] >= range_2[0] && range_1[1] <= range_2[1])) {
                overlap++;
            }

            System.out.println(overlap);
        }

            // System.out.println(String.format("%s - %s == %s - %s", range_1[0], range_1[1], range_2[0], range_2[1]));

        System.out.println(overlap);
    }
}