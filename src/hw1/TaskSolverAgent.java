package hw1;

import java.util.List;
import java.util.Random;

/**
 * Created by Bogdan on 10/10/2016.
 */
public class TaskSolverAgent {
    int id;
    List<Task> taskPool;
    boolean withLog;
    Random g;

    public TaskSolverAgent(int id, List<Task> taskPool, boolean withLog) {
        this.id = id;
        this.taskPool = taskPool;
        this.withLog = withLog;
        g = new Random();
    }

    public void step() {
        int index = Math.abs(g.nextInt()) % taskPool.size();
        Task currentTask = taskPool.remove(0);

        if(currentTask instanceof SieveTask) {
            SieveTask t = (SieveTask) currentTask;

            if(!SieveTask.primes.contains(new Integer(t.value))) {
                return;
            }
        }

        if(withLog) {
            System.out.print("[" + id + "] Picked task " + currentTask.toString());
        }
        if(!currentTask.isFinished()) {
            List<Task> result = currentTask.solveTask();
            taskPool.addAll(result);
            if(withLog) {
                System.out.println(" result was " + result.toString());
            }
        }
        else {
            taskPool.add(currentTask);
            if(withLog) {
                System.out.println(" Task is final.");
            }
        }
    }

}
