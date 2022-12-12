package day_11;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Solution {

    static HashMap<Integer, Monkey> monkeys = new HashMap<>();
    public static void main(String[] args) throws FileNotFoundException{
        File f = new File("/Users/pbheda/Documents/personal_projects/aoc_2022/day_11/input.txt");
        Scanner sc = new Scanner(f);

        final String monkeyNumberPattern = "Monkey ";
        final String startingItemsPattern = "  Starting items: ";
        final String operationPattern = "  Operation: new = ";
        final String testPattern = "  Test: divisible by ";
        final String trueTestPattern = "If true: throw to monkey ";
        final String falseTestPattern = "If false: throw to monkey ";

        int monkeyNumber = -1;
        int modulo = 1;

        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            // System.out.println(data);
            if (data.contains(monkeyNumberPattern)) {
                String[] tokens = data.split(" ");
                monkeyNumber = Integer.parseInt(tokens[1].substring(0, tokens[1].length()-1));
                if (!monkeys.containsKey(monkeyNumber)) {
                    monkeys.put(monkeyNumber, new Monkey());
                    monkeys.get(monkeyNumber).number = monkeyNumber;
                }
            } else if (data.contains(startingItemsPattern)) {
                String[] startingItems = data.substring(startingItemsPattern.length()).replaceAll(" ", "").split(",");
                monkeys.get(monkeyNumber).startingItems = Arrays.stream(startingItems).mapToLong(Integer::parseInt).boxed().collect(Collectors.toCollection(ArrayList::new));
                // System.out.println("Starting items "+Arrays.toString(startingItems));
            } else if (data.contains(operationPattern)) {
                String[] tokens = data.split(" ");
                String operator = tokens[tokens.length-2];
                String operand = tokens[tokens.length-1];
                monkeys.get(monkeyNumber).operator = operator;
                monkeys.get(monkeyNumber).operand = operand;
                // System.out.println("Operation " +operator + " " + operand);
            } else if (data.contains(testPattern)) {
                String[] tokens = data.split(" ");
                String divisibleBy = tokens[tokens.length-1];
                monkeys.get(monkeyNumber).testDivisor = Integer.parseInt(divisibleBy);
                modulo *= monkeys.get(monkeyNumber).testDivisor;
                // System.out.println("Divisible by " +divisibleBy);
            } else if (data.contains(trueTestPattern)) {
                String[] tokens = data.split(" ");
                String throwToMonkey = tokens[tokens.length-1];
                monkeys.get(monkeyNumber).successThrowToMonkey = Integer.parseInt(throwToMonkey);
                // System.out.println("Throw to monkey " +throwToMonkey);
            } else if (data.contains(falseTestPattern)) {
                String[] tokens = data.split(" ");
                String throwToMonkey = tokens[tokens.length-1];
                monkeys.get(monkeyNumber).failureThrowToMonkey = Integer.parseInt(throwToMonkey);
                // System.out.println("Throw to monkey " +throwToMonkey);
            }
        }
        sc.close();

        System.out.println(modulo);

        for(int i=0; i<10000; i++) {
            System.out.println("Round " + (i+1));
            for(int j=0; j<monkeys.size(); j++) {
                Monkey monkey = monkeys.get(j);
                monkey.inspectCount += monkey.startingItems.size();
                while(!monkey.startingItems.isEmpty()) {
                    Long item = monkey.startingItems.remove(0);
                    Long operand = monkey.operand.equals("old") ? item : Long.parseLong(monkey.operand);
                    switch (monkey.operator) {
                        case "*":
                            item *= operand;
                            break;
                        case "+":
                            item += operand;
                            break;
                        default:
                            break;
                    }
                    // item /= 3;
                    item %= modulo; // supposed to be lcm but all divisors are prime in this case
                    if (item % monkey.testDivisor == 0) {
                        monkeys.get(monkey.successThrowToMonkey).startingItems.add(item);
                    } else {
                        monkeys.get(monkey.failureThrowToMonkey).startingItems.add(item);
                    }
                }
            }

            // for (Monkey monkey : monkeys.values()) {
            //     System.out.println(monkey);
            // }
        }
        
        ArrayList<Monkey> sortedMonkeys = new ArrayList<>();

        for (Monkey monkey : monkeys.values()) {
            sortedMonkeys.add(monkey);
        }

        Collections.sort(sortedMonkeys, Comparator.reverseOrder());


        for (Monkey monkey : sortedMonkeys) {
            System.out.println(monkey);
        }

        Long result = sortedMonkeys.get(0).inspectCount * sortedMonkeys.get(1).inspectCount;

        System.out.println(result);
    }
}

class Monkey implements Comparable<Monkey>{
    int number;
    List<Long> startingItems;
    String operator;
    String operand;
    int testDivisor;
    int successThrowToMonkey;
    int failureThrowToMonkey;
    long inspectCount;

    @Override
    public String toString() {
        return String.format("%d -> %s -> %s", number, inspectCount, startingItems);
    }

    @Override
    public int compareTo(Monkey o) {
        if (this.inspectCount > o.inspectCount) return 1;
        if (this.inspectCount < o.inspectCount) return -1;
        return 0;
    }
    
}