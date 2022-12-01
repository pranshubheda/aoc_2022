package day_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        long currentElfCalories = 0;
        File file = new File("/Users/pbheda/Documents/aoc_2022/day_1/input.txt");
        Scanner sc = new Scanner(file);
        PriorityQueue<Long> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        while(sc.hasNextLine()) {
            String data = sc.nextLine();
            if (data.equals("")) {
                // compare
                maxHeap.add(currentElfCalories);
                currentElfCalories = 0;
            } else {
                long calories = Long.parseLong(data);
                currentElfCalories += calories;
            }
        }
        System.out.println(Arrays.toString(maxHeap.toArray()));

        long sum = maxHeap.poll() + maxHeap.poll() +maxHeap.poll();

        System.out.println(sum);
    }
}