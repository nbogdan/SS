package hw2;

import java.util.LinkedList;
import java.util.List;

public class Cricket extends Population{

    public Cricket(int lifeCycle, int timeLeft, int size, double x, double y, double ip) {
        super(lifeCycle, timeLeft, size, x, y, ip);
    }

    @Override
    public List<Population> mutate() {
        int sizeLonger = (int)Math.floor(size * x);
        int sizeSame = (int)Math.floor(size * y);
        int sizeSmall = size - sizeLonger - sizeSame;
        List<Population> result = new LinkedList<>();

        if(lifeCycleSpent == lifeCycle) {
            return result;
        }
        if(lifeCycle == 1)
            sizeSame += sizeSmall;

        if(sizeLonger > 0)
            result.add(new Cricket(lifeCycle + 1, 0, sizeLonger, x, y, increasePerc));
        if(sizeSame > 0)
            result.add(new Cricket(lifeCycle, 0, sizeSame, x, y, increasePerc));
        if(sizeSmall > 0 && lifeCycle > 1)
            result.add(new Cricket(lifeCycle - 1, 0, sizeSmall, x, y, increasePerc));

        return result;
    }

    @Override
    public void layEggs() {
        super.layEggs();
    }
}
