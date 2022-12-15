package day_13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
    

    static ArrayList<ArrayList<Integer>> root1 = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> root2 = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_13/input.txt");
        Scanner sc = new Scanner(f);
        while(sc.hasNextLine()) {
            Boolean result = compare(sc.nextLine(), sc.nextLine(), 0);
            sc.nextLine();
        }
    }



    // static ArrayList<ArrayList<Integer>> create(String data) {
    //     ArrayList<Array> parent = new ArrayList<>();
    //     root1.add(parent);
    //     Stack<String> s = new Stack<>();
    //     for (int i = 0; i < data.length(); i++) {
    //         if ((data.charAt(i) == ']')) {
    //             ArrayList<Integer> numList = new ArrayList<>();
    //             String number = "";
    //             while (s.peek() != "[") {
    //                 String popped = s.pop();
    //                 if (popped == ",") {
    //                     numList.add(Integer.parseInt(number));
    //                     number = "";
    //                 } else {
    //                     number = popped + number;
    //                 }
    //             }
    //             if (number != "") numList.add(Integer.parseInt(number));
    //             s.pop();
    //         } else {
    //             s.push(data.charAt(i)+"");    
    //         }
    //     }
    //     return null;
    // }

    static boolean compare(String x, String y, int ctr) {
        if (ctr >= x.length() && ctr < y.length()) return true;
        if (ctr < x.length() && ctr >= y.length()) return false;
        if (x.charAt(ctr) == '[' && y.charAt(ctr) == '[') {
            return compare(x, y, ctr+1);
        } else {
            if (Character.isDigit(x.))
        }
    }
}