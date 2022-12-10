package day_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_10/input.txt");
        Scanner sc = new Scanner(f);

        int clockCycle = 0;
        int x = 1;
        int temp = x;
        boolean temp_loaded = false;
        int[] signal_strength = new int[250];
        String crt = "";
        
        while(true) {
            // clock
            clockCycle++;
            // System.out.println(String.format("%d -> %d", clockCycle, x));
            signal_strength[clockCycle] = x;
            int sprite_location = x;
            int current_draw = (clockCycle-1)%40;
            if (current_draw == 0) {
                crt += "\n";
            } else if (x-1 <= current_draw && current_draw <= x+1) {
                crt += "#";
            } else {
                crt += ".";
            }


            if (temp_loaded) {
                temp_loaded = false;
                x = temp;
            } else {
                if (sc.hasNextLine()) {
                    String data = sc.nextLine();
                    String[] tokens = data.split(" ");
                    switch (tokens[0]) {
                        case "noop":
                            break;
                        case "addx":
                            temp = x + Integer.parseInt(tokens[1]);
                            temp_loaded = true;                 
                            break;
                        default:
                            break;
                    }
                }
            }
            if (clockCycle > 248) {
                break;
            }
        }

        double result = 0;
        int start = 20;
        int d_start = 40;
        for(int i=0; i<6; i++) {
            int index = (d_start * i) + start;
            result += (index * signal_strength[index]);
            // System.out.println(index + " -> " + signal_strength[index]);
        }

        // System.out.println("Result : " + result);
        // System.out.println(Arrays.toString(signal_strength));
        System.out.println(crt);
    }
}
