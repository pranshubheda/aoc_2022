package day_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution2 {

    static int[][] children = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1},
    };

    static ArrayList<ArrayList<Node>> map = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_12/input.txt");
        Scanner sc = new Scanner(f);
        HashMap<Node, Node> previous = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        ArrayList<Node> low_level = new ArrayList<>();
        Node source = null;
        Node destination = null;
        int rowCtr = 0;
        while(sc.hasNextLine()) {
            String data = sc.nextLine();
            ArrayList<Node> row = new ArrayList<>();
            String[] tokens = data.split("");
            int colCtr = 0;
            for (String token : tokens) {
                Node n;
                if (token.equals("S")) {
                    n = new Node(rowCtr, colCtr, 'a'-'a');
                    destination = n;
                } else if (token.equals("E")) {
                    n = new Node(rowCtr, colCtr, 'z'-'a');
                    source = n;
                } else {
                    n = new Node(rowCtr, colCtr, token.charAt(0)-'a');
                }
                if (token.equals("a")) {
                    low_level.add(n);
                }
                row.add(n);
                previous.put(n, null);
                colCtr++;
            }
            map.add(row);
            rowCtr++;
        }

        source.distance = 0;
        pq.add(source);


        HashSet<String> visited = new HashSet<String>();

        while(!pq.isEmpty()) {
            Node n = pq.poll();
            visited.add(n.toString());
            for (int i=0; i<children.length; i++) {
                int[] child = { n.row+children[i][0], n.column+children[i][1] };
                if (inBox(child[0], child[1])) {
                    Node childNode = map.get(child[0]).get(child[1]);
                    int d = n.distance + 1;
                    if ( !visited.contains(childNode.toString()) && ( childNode.height - n.height >= -1) && (d < childNode.distance)) {
                        previous.put(childNode, n);
                        childNode.distance = d;
                        pq.remove(childNode);
                        pq.add(childNode);
                    }
                }
            }
            if (n.height == 0) {
                System.out.println(n.distance);
                break;
            }
        }

        sc.close();
    }

    static boolean inBox(int i, int j) {
        return i >= 0 && i <map.size() && j>=0 && j<map.get(0).size();
    }
}


class Node implements Comparable<Node> {
    int height;
    int row;
    int column;
    int distance = Integer.MAX_VALUE;

    Node(int row, int column, int height) {
        this.row = row;
        this.column = column;
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("%d,%d", row, column);
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(distance, o.distance);
    }
}