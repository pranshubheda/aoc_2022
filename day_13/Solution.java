package day_13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
    

    static ArrayList<ArrayList<Integer>> root1 = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> root2 = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_13/input.txt");
        Scanner sc = new Scanner(f);
        int index = 1;
        int result = 1;
        ArrayList<Node> nodes = new ArrayList<>();
        while(sc.hasNextLine()) {
            String line1 = sc.nextLine();
            String line2 = sc.nextLine();
            Node n1 = create(line1);
            Node n2 = create(line2);
            // System.out.println(n1);
            // System.out.println(n2);
            // int comp_result = n1.compareTo(n2);
            // if (comp_result < 0) result += index;
            // System.out.println(n1.compareTo(n2));
            // System.out.println();
            nodes.add(n1);
            nodes.add(n2);
            sc.nextLine();
            index++;
        }
        Collections.sort(nodes);
        for (int i = 0; i < nodes.size(); i++) {
            String node = nodes.get(i).toString();
            if ( node.equals("[[2]]") || node.equals("[[6]]") ) {
                result *= (i+1);
            }
        }
        System.out.println(result);
    }



    static Node create(String data) {
        // ArrayList<Node> nodes = new ArrayList<>();
        Node current = new Node();
        String number = "";
        for (int i = 1; i < data.length(); i++) {
            char current_char = data.charAt(i);
            switch(current_char) {
                case '[':
                    current.nodes.add(create(data.substring(i)));
                    i += current.nodes.get(current.nodes.size()-1).toString().length()-1;
                    break;
                case ']':
                    if (number.length() != 0) {
                        Node n = new Node();
                        n.n = Integer.parseInt(number);
                        number = "";
                        current.nodes.add(n);
                    }
                    return current;
                case ',':
                    if (number.length() != 0) {
                        Node n = new Node();
                        n.n = Integer.parseInt(number);
                        number = "";
                        current.nodes.add(n);
                    }
                    break;
                default:
                    number += (current_char+"");
                    break;
            }   
        }
        return current;
    }
}


class Node implements Comparable<Node> {
    int n = -1;
    ArrayList<Node> nodes = new ArrayList<>();

    @Override
    public String toString() {
        if (n != -1) {
            return n+"";
        } else {
            String result = "[";
            for (Node node : nodes) {
                result += (node.toString() +",");
            }
            if (result.charAt(result.length()-1) == ',') {
                result = result.substring(0, result.length()-1);
            }
            return result + "]";
        }
    }

    @Override
    public int compareTo(Node o) {
        if (this.n != -1 && o.n != -1) {
            return this.n - o.n; 
        } else if (o.n != -1 && this.n == -1) {
            Node x = new Node();
            x.nodes.add(o);
            return this.compareTo(x);
        } else if (o.n == -1 && this.n != -1) {
            Node x = new Node();
            x.nodes.add(this);
            return x.compareTo(o);
        } else {
            for (int i = 0; i < Math.min(this.nodes.size(), o.nodes.size()); i++) {
                if (this.nodes.get(i).compareTo(o.nodes.get(i)) != 0) {
                    return this.nodes.get(i).compareTo(o.nodes.get(i));
                }
            }
            return this.nodes.size() - o.nodes.size();
        }
    }
}