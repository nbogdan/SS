import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bogdan on 10/10/2016.
 *
 * A task specifies a number that needs to be added to the valueMap.
 */
public class PascalTask implements Task{
    static HashMap<Pair, Integer> valueMap; //((1,2),3) line 1, 2nd element value 3.
    int number;
    int line;
    static int maxLine;
    String type;

    public PascalTask(int line, int number, String type) {
        this.number = number;
        this.line = line;
        this.type = type;
        if(valueMap == null) {
            valueMap = new HashMap<>();
        }
    }

    @Override
    public List<Task> solveTask() {
        List<Task> result = new LinkedList<>();
        if(type.equals("margin")) {
            valueMap.put(new Pair(line, number), 1);
            if(number == 1) {
                result.add(new PascalTask(line + 1, 1, "margin"));
            }
            else {
                result.add(new PascalTask(line + 1, number, "center"));
                result.add(new PascalTask(line + 1, number + 1, "margin"));
            }
        }
        else {
            Pair parentLeft = new Pair(line - 1, number - 1);
            Pair parentRight = new Pair(line - 1, number);
            if(valueMap.containsKey(parentLeft) && valueMap.containsKey(parentRight)) {
                System.out.println(parentLeft + "(" + valueMap.get(parentLeft) + ") + " +
                        parentRight + "(" + valueMap.get(parentRight) + ") ->" + this);
                valueMap.put(new Pair(line, number), valueMap.get(parentLeft) + valueMap.get(parentRight));
                result.add(new PascalTask(line + 1, number, "center"));
            }
            else {
                System.out.println(this + " At least one of the parent nodes is missing, try later.");
                result.add(this);
            }
        }
        return result;
    }

    @Override
    public boolean isFinished() {
        return line == maxLine + 1;
    }
    public String toString() {
        return "(line: " + line + ", #" + number + ")";
    }
}
