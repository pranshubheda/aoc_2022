package day_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

import javax.naming.ldap.SortControl;

public class Solution2 {

    static ArrayList<ArrayList<String>> stacks = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException{
        createStacks();
        performOperartion();
        String result = "";
        for(int i=0; i<stacks.size(); i++) {
            result += stacks.get(i).get(0);
        }
        System.out.println(result);
    }

    private static void createStacks() throws FileNotFoundException {
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_5/stacks.txt");
        Scanner sc = new Scanner(f);

        for(int i=0; i<9; i++) {
            stacks.add(new ArrayList<String>());
        }
        
        while(sc.hasNextLine()) {
            String data = sc.nextLine();
            int stackCount = 0;
            for(int i=1; i<data.length(); i+=4) {
                ArrayList<String> stack = stacks.get(stackCount);
                if (Character.isAlphabetic(data.charAt(i))) {
                    stack.add(data.charAt(i)+"");
                }
                stackCount++;
            }
        }

        for(int j=0; j<stacks.size(); j++) {
            System.out.println(stacks.get(j));
        }

        sc.close();
    }

    private static void performOperartion() throws FileNotFoundException {
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_5/commands.txt");
        Scanner sc = new Scanner(f);

        while(sc.hasNextLine()) {
            String[] data = sc.nextLine().split(" ");
            System.out.println(Arrays.toString(data));
            int n = Integer.parseInt(data[1]);
            int source = Integer.parseInt(data[3]);
            int destination = Integer.parseInt(data[5]);

            System.out.println(String.format("%d %d %d", n, source, destination));
            ArrayList<String> cratesToAdd = new ArrayList<>();
            for(int i=0; i<n; i++) {
                ArrayList<String> sourceStack = stacks.get(source-1);
                if (!sourceStack.isEmpty()) {
                    cratesToAdd.add(0, sourceStack.remove(0));
                }
            }
            ArrayList<String> destinationStack = stacks.get(destination-1);
            while(!cratesToAdd.isEmpty()) {
                destinationStack.add(0, cratesToAdd.remove(0));
            }
            for(int j=0; j<stacks.size(); j++) {
                System.out.println(stacks.get(j));
            }
        }

        sc.close();
    }
}
