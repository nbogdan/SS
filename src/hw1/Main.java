package hw1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void exercise1(int order, int numAgents) {
        List<Task> taskList = new LinkedList<>();
        taskList.add(new PascalTask(1,1, "margin"));
        taskList.add(new PascalTask(1,2, "margin"));

        if(order < 1)
            System.out.println("Incorrect parameter. Order must be at least 1");
        else if(order == 1)
            System.out.println("1 - 1");
        else {
            PascalTask.maxLine = order;

            TaskSolverEngine taskEngine = new TaskSolverEngine(taskList, numAgents, true);
            taskEngine.solve();

            for (int j = 2; j < order; j++) {
                for (int i = 0; i < order; i++) {
                    System.out.print(PascalTask.valueMap.get(new Pair(j, i + 1)) + " - ");
                }
                System.out.println("1");
            }
        }
    }

    public static void exercise2(String polygon, int numAgents) {
        List<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(polygon.split(",")));
        nodes.add(nodes.get(0));
        List<Task> taskList = new LinkedList<>();
        taskList.add(new PolygonTask(nodes));

        TaskSolverEngine taskEngine = new TaskSolverEngine(taskList, numAgents, true);
        taskEngine.solve();

        System.out.println("A possible split is: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(taskList.get(i) + ", ");
        }
    }

    public static void exercise3(int top, int numAgents) {
        List<Task> taskList = new LinkedList<>();
        List<Integer> candidatePrimes = new LinkedList<>();


        for (int i = 2; i <= top; i++) {
            candidatePrimes.add(i);
            taskList.add(new SieveTask(i, top));
        }
        SieveTask.primes = candidatePrimes;

        TaskSolverEngine taskEngine = new TaskSolverEngine(taskList, numAgents, true);
        taskEngine.solve();

        System.out.println(SieveTask.primes);
    }

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello");
        //exercise1(6,4);
        //exercise2("A,B,C,D,E",2);
        exercise3(100, 11);
    }
}
