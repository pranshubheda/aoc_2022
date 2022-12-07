package day_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

class Solution {

    static long result = 0;
    static ArrayList<FileNode> nodes = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException{
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_7/input.txt");
        Scanner sc = new Scanner(f);
        Stack<FileNode> location = new Stack<>();
        FileNode root = new FileNode("/");

        while(sc.hasNextLine()) {
            String data = sc.nextLine();
            System.out.println(data);
            if (data.charAt(0) == '$') {
                // command
                if (data.contains("$ cd ")) {
                    // cd
                    String[] tokens = data.split(" ");
                    String destination = tokens[2];
                    if (destination.equals("..")) {
                        location.pop();
                    } else if (destination.equals("/")) {
                        while(!location.isEmpty()) {
                            location.pop();
                        }
                        location.push(root);
                    } else {
                        FileNode parent = location.peek();
                        FileNode childNode = parent.getChild(destination);
                        if (childNode == null) parent.addChild(new FileNode(destination));
                        location.push(childNode);
                    }
                } else {
                    // ls
                }
            } else {
                String[] tokens = data.split(" ");
                if (tokens[0].equals("dir")) {
                    String destination = tokens[1];
                    FileNode parent = location.peek();
                    FileNode childNode = parent.getChild(destination);
                    if (childNode == null) parent.addChild(new FileNode(destination));
                } else {
                    String destination = tokens[1];
                    FileNode parent = location.peek();
                    FileNode childNode = parent.getChild(destination);
                    if (childNode == null) parent.addChild(new FileNode(tokens[1], Long.parseLong(tokens[0])));
                }
            }
        }

        findSize(root);

        System.out.println("Result => " + result);

        long totalSize = 70000000l;
        long available = totalSize - root.size;
        long required = 30000000l - available;
        // System.out.println(nodes);
        Collections.sort(nodes);
        // System.out.println(nodes);
        for (FileNode node : nodes) {
            if (node.size > required) {
                System.out.println("**************");
                System.out.println(node);
                break;
            }
        }

    }

    public static long findSize(FileNode node) {
        if (node.children.isEmpty()) return node.size;
        Long currSize = 0l;
        for (FileNode child : node.children) {
            currSize += findSize(child);
        }
        node.size = currSize;

        System.out.println(node);
        nodes.add(node);
        
        if (node.size < 100000) result += node.size;

        return node.size;
    }
}

class FileNode implements Comparable<FileNode>{
    String name;
    Long size;
    ArrayList<FileNode> children = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("Name: %s, Size : %d", name, size);
    }

    FileNode(String name, Long size) {
        this.name = name;
        this.size = size;
    }
    
    FileNode(String name) {
        this.name = name;
    }

    public void addChild(FileNode child) {
        this.children.add(child);
    }

    public FileNode getChild(String name) {
        for (FileNode child : children) {
            if (child.name.equals(name)) {
                return child;
            }
        }
        return null;
    }

    @Override
    public int compareTo(FileNode o) {
        if (this.size > o.size) {
            return 1;
        } else if (this.size < o.size) {
            return -1;
        } else {
            return 0;
        }
    }
}