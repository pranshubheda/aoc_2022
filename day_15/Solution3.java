package day_15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution3 {
    public static void main(String[] args) throws FileNotFoundException{
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_15/input.txt");
        Scanner sc = new Scanner(f);
        ArrayList<Long[]> sb = new ArrayList<>();

        while(sc.hasNextLine()) {
            String data = sc.nextLine();
            data = data.replace("Sensor at ", "").replace(" closest beacon is at ", "");
            data = data.replace(":", ",");
            data = data.replace("x=", "");
            data = data.replace("y=", "");
            data = data.replace(" ", "");
    
            Long[] coords_pair = Arrays.stream(data.split(",")).mapToLong(Long::parseLong).boxed().toArray(Long[]::new);
    
            sb.add(coords_pair);
        }

        
        /**
         * Referred from here 
         * https://github.com/hyper-neutrino/advent-of-code/blob/main/2022/day15p2.py
         * 
         */
        
        long M = 4000000;

        for (long i = 0; i <= M; i++) {
            long Y = i;
            ArrayList<Long[]> intervals = new ArrayList<>();
            for (Long[] coords : sb) {
                long d = Math.abs(coords[2]-coords[0]) + Math.abs(coords[3]-coords[1]);
                long dx = d - Math.abs(Y - coords[1]);
                if (dx < 0) continue;
                Long[] interval = new Long[] { coords[0]-dx, coords[0]+dx };
                intervals.add(interval);
            }

            intervals.sort((l1, l2) -> {
                for (long j = 0; j < Math.min(l1.length, l2.length); j++) {
                    if (!l1[(int)j].equals(l2[(int)j])) return (int) (l1[(int)j] - l2[(int)j]);
                }
                return l1.length - l2.length;
            });

            ArrayList<Long[]> merged_intervals = new ArrayList<>();

            for (Long[] interval : intervals) {
                if (merged_intervals.isEmpty()) {
                    merged_intervals.add(interval);
                } else {
                    Long[] last_interval = merged_intervals.get(merged_intervals.size()-1);
                    if (interval[0] > last_interval[1] + 1) {
                        merged_intervals.add(interval);
                    } else {
                        last_interval[1] = Math.max(interval[1], last_interval[1]);
                    }
                }
            }

            long x = 0;
            long result = 0;

            for (Long[] interval : merged_intervals) {
                if (x < interval[0]) {
                    System.out.println(Arrays.toString(interval));
                    result = x*4000000+Y;
                    System.out.println(result);
                    break;
                }

                x = Math.max(x, interval[1]+1);
                if (x > M) break;
            }

            if (result != 0) {
                break;
            };

        }
    }
}
