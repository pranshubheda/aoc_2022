package day_9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Solution {
    static int[] h = {0, 0};

    static int[] t = {0, 0};

    static ArrayList<Integer[]> children = new ArrayList<>();

    static int result = 0;

    static HashSet<String> visited = new HashSet<>();


    static {
        children.add(new Integer[] {1, 0});
        children.add(new Integer[] {-1, 0});
        children.add(new Integer[] {0, 1});
        children.add(new Integer[] {0, -1});
        children.add(new Integer[] {1, 1});
        children.add(new Integer[] {1, -1});
        children.add(new Integer[] {-1, 1});
        children.add(new Integer[] {-1, -1});
        visited.add(Arrays.toString(t));
    }

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_9/input.txt");
        Scanner sc = new Scanner(f);

        while(sc.hasNextLine()) {
            String data = sc.nextLine();
            System.out.println(data);
            String[] tokens = data.split(" ");
            String direction = tokens[0];
            int magnitude = Integer.parseInt(tokens[1]);
            switch (direction) {
                case "R":
                    for(int i=1; i<=magnitude; i++) {
                        h[1] += 1;
                        if (doesTMove()) moveT();
                        visited.add(Arrays.toString(t));
                    }
                    break;
                case "L":
                    for(int i=1; i<=magnitude; i++) {
                        h[1] -= 1;
                        if (doesTMove()) moveT();
                        visited.add(Arrays.toString(t));
                    }
                    break;
                case "U":
                    for(int i=1; i<=magnitude; i++) {
                        h[0] += 1;
                        if (doesTMove()) moveT();
                        visited.add(Arrays.toString(t));
                    }
                    break;
                case "D":
                    for(int i=1; i<=magnitude; i++) {
                        h[0] -= 1;
                        if (doesTMove()) moveT();
                        visited.add(Arrays.toString(t));
                    }
                    break;
            }
        }

        System.out.println(visited.size());

        sc.close();
    }

    static boolean doesTMove() {
        double distance = distance(t, h);
        boolean doesTMove = distance > Math.sqrt(2);
        System.out.println("T moves : "+doesTMove);
        return doesTMove;
    }

    static double distance(int[] p1, int [] p2) {
        return Math.sqrt(Math.pow(p1[0]-p2[0], 2) + Math.pow(p1[1]-p2[1], 2));
    }

    static void moveT() {
        Double min = Double.MAX_VALUE;
        int[] move = new int[2];

        for (Integer[] child : children) {
            int[] temp = { t[0]+child[0], t[1]+child[1] };
            double distance = distance(temp, h);
            if (distance < min) {
                min = distance;
                move[0] = temp[0];
                move[1] = temp[1];
            }
        }

        t[0] = move[0];
        t[1] = move[1];

        System.out.println("H => "+Arrays.toString(h));
        System.out.println("T => "+Arrays.toString(t));
    }
}
