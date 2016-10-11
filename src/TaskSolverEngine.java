import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Bogdan on 10/10/2016.
 */
public class TaskSolverEngine {
    List<Task> taskSet;
    List<TaskSolverAgent> agents;

    public TaskSolverEngine(List<Task> taskSet, int numAgents, boolean debug) {
        this.taskSet = taskSet;
        agents = new LinkedList<>();

        for (int i = 0; i < numAgents; i++) {
            agents.add(new TaskSolverAgent(i, taskSet, debug));
        }
    }

    boolean completeTaskSolved(List<Task> taskList) {
        for (Task t : taskList) {
            if(!t.isFinished()) {
                return false;
            }
        }

        return true;
    }

    public void solve() {
        Random g = new Random();

        while (!completeTaskSolved(taskSet)) {
            int workerId = Math.abs(g.nextInt()) % agents.size();
            agents.get(workerId).step();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
