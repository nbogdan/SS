import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Bogdan on 10/10/2016.
 *
 * A task specifies a number that needs to be added to the valueMap.
 */
public class PolygonTask implements Task{
    List<String> vertices; // A ABCD square will consist of A,B,C,D,A

    public PolygonTask(List<String> vertices) {
        this.vertices = vertices;
    }

    @Override
    public List<Task> solveTask() {
        List<Task> result = new LinkedList<>();
        Random g = new Random();

        int cuttingPoint = (vertices.size() - 1) / 2;
        List<String> firstList = new LinkedList<>();
        List<String> secondList = new LinkedList<>();
        firstList.addAll(vertices.subList(0, cuttingPoint + 1));
        firstList.add(firstList.get(0));
        secondList.addAll(vertices.subList(cuttingPoint, vertices.size()));
        secondList.add(secondList.get(0));
        result.add(new PolygonTask(firstList));
        result.add(new PolygonTask(secondList));

        return result;
    }

    @Override
    public boolean isFinished() {
        return vertices.size() == 4;
    }
    public String toString() {
        return vertices.toString();
    }
}
