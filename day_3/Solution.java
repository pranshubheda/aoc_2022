package day_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

class Solution {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_3/input.txt");
        Scanner sc = new Scanner(f);

        int total = 0;

        while(sc.hasNextLine()) {
            String data = sc.nextLine();
            int n = data.length();
            String compartment1 = data.substring(0, n/2);
            String compartment2 =data.substring(n/2);

            HashSet<String> set1 = new HashSet<String>(Arrays.asList(compartment1.split("")));
            HashSet<String> set2 = new HashSet<String>(Arrays.asList(compartment2.split("")));


            set1.retainAll(set2);

            System.out.println(set1.toString().charAt(1));


            char item = set1.toString().charAt(1);
            int point = 0;
            
            if (item > 'Z') {
                point = item - 96;
            } else {
                point = item - 38;
            }

            total += point;


            System.out.println(String.format("%s -> %s    |    %s  -> %s", data, compartment1, compartment2, set1));
            System.out.println(String.format("%d -> %d    |    %d", data.length(), compartment1.length(), compartment2.length()));
        }
        System.out.println(total);

    }
}