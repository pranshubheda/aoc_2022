package day_15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException{
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
            // System.out.println(Arrays.toString(coords_pair));

            grid_min[0] = Math.min(grid_min[0], Math.min(coords_pair[0], coords_pair[2]));
            grid_min[1] = Math.min(grid_min[1], Math.min(coords_pair[1], coords_pair[3]));
            grid_max[0] = Math.max(grid_max[0], Math.max(coords_pair[0], coords_pair[2]));
            grid_max[1] = Math.max(grid_max[1], Math.max(coords_pair[1], coords_pair[3]));
        }

        // System.out.println(Arrays.toString(grid_min));
        // System.out.println(Arrays.toString(grid_max));

        Queue<Integer[]> q = new LinkedList<>();
        int[][] children = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
        };

        HashSet<String> visited = new HashSet<>();

        HashSet<String> result = new HashSet<>();

        for (Integer[] coords : sb) {
            int d = Math.abs(coords[2]-coords[0]) + Math.abs(coords[3]-coords[1]);
            q.add(new Integer[] { coords[0], coords[1] });            
            while(!q.isEmpty()) {
                Integer[] curr = q.poll();
                // if (!visited.contains(Arrays.toString(child)) && child[0] >= grid_min[0] && child[0] <= grid_max[0] && child[1] >= grid_min[1] && child[1] <= grid_max[1]) {
                if (!visited.contains(Arrays.toString(curr))) {
                    visited.add(Arrays.toString(curr));
                    int curr_d = Math.abs(curr[0]-coords[0]) + Math.abs(curr[1]-coords[1]);
                    if (curr_d <= d) {
                        result.add(Arrays.toString(curr));
                        for (int i = 0; i < children.length; i++) {   
                            q.add(new Integer[] { curr[0]+children[i][0], curr[1]+children[i][1] });
                        }
                    }
                }
            }
            visited.clear();
        }

        for (Integer[] coords : sb) {
            result.remove(Arrays.toString(new Integer[] { coords[2], coords[3] }));
        }

        int result_count = 0;
        for (String string : result) {
            string = string.replace("[", "");
            string = string.replace("]", "");
            string = string.replace(" ", "");
            if (string.split(",")[1].equals("2000000")) {
                // System.out.println(string);
                result_count++;
            }
        }
        
        System.out.println(result_count);
        
        // System.out.println(visited.size());
        // System.out.println(Arrays.toString(grid_min));
        // System.out.println(Arrays.toString(grid_max));
    }
}
