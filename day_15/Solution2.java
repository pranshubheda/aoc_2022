package day_15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Solution2 {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_15/input.txt");
        Scanner sc = new Scanner(f);
        int[] grid_min = { Integer.MAX_VALUE, Integer.MAX_VALUE };
        int[] grid_max = { Integer.MIN_VALUE, Integer.MIN_VALUE };
        ArrayList<Integer[]> sb = new ArrayList<>();
        while(sc.hasNextLine()) {
            String data = sc.nextLine();
            data = data.replace("Sensor at ", "").replace(" closest beacon is at ", "");
            data = data.replace(":", ",");
            data = data.replace("x=", "");
            data = data.replace("y=", "");
            data = data.replace(" ", "");

            Integer[] coords_pair = Arrays.stream(data.split(",")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);

            sb.add(coords_pair);
        }

        int y = 2000000;

        ArrayList<Integer[]> intervals = new ArrayList<>();

        HashSet<Integer> allowedX = new HashSet<>();

        HashSet<Integer> notAllowedX = new HashSet<>();

        int min_x = Integer.MAX_VALUE;
        int max_x = Integer.MIN_VALUE;


        /**
         * Referred from here 
         * https://github.com/womogenes/AoC-2022-Solutions/blob/main/day_15/day_15_p1.py
         * 
         * Tried to understand the math and do on my own
         */

        for (Integer[] coords : sb) {
            int d = Math.abs(coords[2]-coords[0]) + Math.abs(coords[3]-coords[1]);
            int dx = d - Math.abs(y - coords[1]);
            if (dx <= 0) continue;
            Integer[] interval = new Integer[] { coords[0]-dx, coords[0]+dx };
            intervals.add(interval);
            max_x = Math.max(max_x, Math.max(interval[0], interval[1]));
            min_x = Math.min(min_x, Math.min(interval[0], interval[1]));
            if (coords[3] == y) allowedX.add(coords[2]);
        }

        for (int i = min_x; i <= max_x; i++) {
            if (allowedX.contains(i)) continue;
            for (Integer[] interval : intervals) {
                if (interval[0] <= i && i <= interval[1]) {
                    notAllowedX.add(i);
                }
            }
        }

        System.out.println(notAllowedX.size());
    }
}
