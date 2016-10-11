import java.util.*;

/**
 * Created by Bogdan on 10/11/2016.
 */
public class SieveTask implements Task{
    static Set<Integer> marked = new TreeSet<>();
    static List<Integer> primes = new LinkedList<>();
    static int limit;
    int value;

    public SieveTask(int value, int limit) {
        this.value = value;
        this.limit = limit;
    }


    @Override
    public List<Task> solveTask() {
        int first = -1;
        List<Task> result = new LinkedList<>();

        primes.add(value);
        for (int i = value + value; i < limit; i+=value) {
            marked.add(i);
        }
        for (int i = value + 1; i < limit; i++) {
            if(!marked.contains(i)) {
                result.add(new SieveTask(i, limit));
                return result;
            }
        }

        return result;
    }

    @Override
    public boolean isFinished() {
        return value >= limit;
    }

    public String toString() {
        return value + " " + marked;
    }
}
