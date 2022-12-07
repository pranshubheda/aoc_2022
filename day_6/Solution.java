package day_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_6/input.txt");
        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            String message = sc.nextLine();
            int windowSize = 4;
            int begin = 0;
            int n = message.length();
            for (int i=0; i<=n-windowSize; i++) {
                begin = i;
                HashSet<Character> window = new HashSet<>();
                while(begin < begin+windowSize) {
                    Character c = message.charAt(begin);
                    if (window.contains(c)) {
                        break;
                    } else {
                        window.add(c);
                        begin++;
                    }
                    if (window.size() == windowSize) {
                        break;
                    }
                }
                if (window.size() == windowSize) {
                    break;
                }
            }
            int end = begin+windowSize-1;
    
            System.out.println(message);
    
            System.out.println(String.format("%s => %d , %d", message.substring(begin, end+1), begin, end));

        }
        sc.close();

    }   
}
