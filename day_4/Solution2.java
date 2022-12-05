package day_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution2 {
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


            if (!((range_1[1] < range_2[0]) || (range_2[1] < range_1[0]))) {
                overlap ++;
            }

        }
        System.out.println(overlap);
    }
}
