package day_14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Solution2 {

    public static void main(String[] args) throws InterruptedException, IOException{
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_14/input.txt");
        Scanner sc = new Scanner(f);
        ArrayList<String[]> lines = new ArrayList<>();
        int max_x = -1;
        int max_y = -1;
        int min_x = Integer.MAX_VALUE;
        int min_y = Integer.MAX_VALUE;
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            // System.out.println(data);
            data = data.replace(" ", "");
            String[] tokens = data.split("->");
            for (String strings : tokens) {
                String[] coords = strings.split(",");
                max_x = Math.max(max_x, Integer.parseInt(coords[0]));
                max_y = Math.max(max_y, Integer.parseInt(coords[1]));
                min_x = Math.min(min_x, Integer.parseInt(coords[0]));
                min_y = Math.min(min_y, Integer.parseInt(coords[1]));
            }
            // System.out.println(Arrays.toString(tokens));
            lines.add(tokens);
        }

        char[][] grid = new char[max_y+5][max_x-min_x+20+500];

        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(grid[i], '.');            
        }

        for (String[] line : lines) {
            for (int i = 0; i < line.length-1; i++) {
                Integer[] coords_1 = Arrays.stream(line[i].split(",")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
                Integer[] coords_2 = Arrays.stream(line[i+1].split(",")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
                if (coords_1[0].equals(coords_2[0])) {
                    // vertical
                    int count = Math.abs(coords_2[1]-coords_1[1])+1;
                    int starting_x = Math.min(coords_1[1], coords_2[1]);
                    for (int j = 0; j < count; j++) {
                        grid[starting_x+j][coords_1[0]-min_x+10+200] = '#';
                        // Thread.sleep(500);
                        // System.out.print("\033[H\033[2J");  
                        // System.out.flush();
                        // print_grid(grid);
                    }
                } else {
                    // horizontal
                    int count = Math.abs(coords_2[0]-coords_1[0])+1;
                    int starting_y = Math.min(coords_1[0], coords_2[0]);
                    for (int j = 0; j < count; j++) {
                        grid[coords_1[1]][starting_y-min_x+j+10+200] = '#';
                        // Thread.sleep(500);
                        // System.out.print("\033[H\033[2J");  
                        // System.out.flush();
                        // print_grid(grid);
                    }
                }
            }
        }

        // print_grid(grid);
        Arrays.fill(grid[max_y+2], '#');

        int[] starting = { 0, 500-min_x+10+200 };

        int[] particle = new int[2];
        int count = 0;
        do {
            count++;
            particle[0] = starting[0];
            particle[1] = starting[1];
            int[] prev = new int[2];
            do {
                prev[0] = particle[0];
                prev[1] = particle[1];
                if (grid[particle[0]+1][particle[1]] == '.') {
                    particle[0] += 1;
                } else {
                    if (grid[particle[0]+1][particle[1]-1] == '.') {
                        particle[0] += 1;
                        particle[1] -= 1;
                    } else if (grid[particle[0]+1][particle[1]+1] == '.') {
                        particle[0] += 1;
                        particle[1] += 1;
                    } else {
                        // stay
                    }
                }
            } while( prev[0] != particle[0] || prev[1] != particle[1]);
            grid[particle[0]][particle[1]] = 'o';
            // print_grid(grid);
            // System.out.println();
            // Thread.sleep(500);
            // System.out.print("\033[H\033[2J");  
            // System.out.flush();
        } while(grid[starting[0]][starting[1]] != 'o');

        print_grid(grid);
        System.out.println(count);

    }

    // private static isInBox()

    private static void print_grid(char[][] grid) {
        for (int k = 0; k < grid.length; k++) {
            for (int l = 0; l < grid[k].length; l++) {
                System.out.print(grid[k][l]);
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }

}