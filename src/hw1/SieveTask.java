package hw1;

import java.util.*;

/**
 * Created by Bogdan on 10/11/2016.
 */
public class SieveTask implements Task{
    static List<Integer> primes = new LinkedList<>();
    static int limit;
    int value;
    String eliminated;

    public SieveTask(int value, int limit) {
        this.value = value;
        this.limit = limit;
        eliminated = "(";
    }


    @Override
    public List<Task> solveTask() {
        List<Task> result = new LinkedList<>();

        if(!primes.contains(value)) {
            return result;
        }

        for (int i = value + value; i < limit; i+=value) {
            primes.remove(new Integer(i));
            eliminated += i + " ";
        }
        eliminated += ")\t";
        System.out.print(eliminated);
        return result;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public String toString() {
        return value + " eliminated ";
    }
}
