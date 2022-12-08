package day_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {

    static Integer[] heights;
    static Integer[] visibile; 
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_8/input.txt");
        Scanner sc = new Scanner(f);

        List<List<Integer>> heights = new ArrayList<>();
        List<List<Integer>> memo = new ArrayList<>();

        while(sc.hasNextLine()) {
            String data = sc.nextLine();
            heights.add(Arrays.stream(data.split("")).mapToInt(Integer::parseInt).boxed().toList());
        }

        int result = 0;

        for (int i = 0; i < heights.size(); i++) {
            for (int j=0; j< heights.get(i).size(); j++) {
                if (i==0 || j==0 || i==heights.size()-1 || j ==heights.get(0).size()-1) continue;
                int curr = heights.get(i).get(j);
                int ctr = 0;
                boolean up = true;
                boolean down = true;
                boolean left = true;
                boolean right = true;

                while(ctr < j) {
                    if (curr <= heights.get(i).get(ctr)) {
                        left = false;
                        break;
                    }
                    ctr++;
                }

                ctr = 1;

                while(j+ctr < heights.get(i).size()) {
                    if (curr <= heights.get(i).get(j+ctr)) {
                        right = false;
                        break;
                    }
                    ctr++;
                }
                
                ctr = 0;

                while(ctr < i) {
                    if (curr <= heights.get(ctr).get(j)) {
                        up = false;
                        break;
                    }
                    ctr++;
                }
                
                ctr = 1;

                while(i+ctr < heights.size()) {
                    if (curr <= heights.get(i+ctr).get(j)) {
                        down = false;
                        break;
                    }
                    ctr++;
                }

                if (up || down || left || right) result++;
                
            }
        }


        int maxDistance = 1;


        for (int i = 0; i < heights.size(); i++) {
            for (int j=0; j< heights.get(i).size(); j++) {
                if (i==0 || j==0 || i == heights.size()-1 || j == heights.get(i).size()-1) continue;
                int curr = heights.get(i).get(j);
                boolean up = true;
                boolean down = true;
                boolean left = true;
                boolean right = true;

                int distance = 1;

                int leftCount = 0;
                int rightCount = 0;
                int upCount = 0;
                int downCount = 0;

                for(int ctr=j-1; ctr>=0; ctr--) {
                    if (heights.get(i).get(ctr) < curr) {
                        leftCount++;
                    } else {
                        leftCount++;
                        break;
                    }
                }
                
                distance *= Math.max(1, leftCount);

                for(int ctr=j+1; ctr<heights.get(0).size(); ctr++) {
                    if (heights.get(i).get(ctr) < curr) {
                        rightCount++;
                    } else {
                        rightCount++;
                        break;
                    }
                }

                distance *= Math.max(1, rightCount);
                
                for(int ctr=i-1; ctr>=0; ctr--) {
                    if (heights.get(ctr).get(j) < curr) {
                        upCount++;
                    } else {
                        upCount++;
                        break;
                    }
                }

                distance *= Math.max(1, upCount);

                for(int ctr=i+1; ctr<heights.size(); ctr++) {
                    if (heights.get(ctr).get(j) < curr) {
                        downCount++;
                    } else {
                        downCount++;
                        break;
                    }
                }

                distance *= Math.max(1, downCount);

                maxDistance = Math.max(maxDistance, distance);

                System.out.println(String.format("%d, %d => %d", i, j, distance));

                if (up || down || left || right) result++;
                
            }
        }

        
        System.out.println(result);

        System.out.println(maxDistance);
    }
}
