package hw2;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bogdan on 10/17/2016.
 */
public class Parasite extends Population {

    double decreaseToCricketPerc;
    int survivalTime;
    int survivalCounter;

    public Parasite(int lifeCycle, int timeLeft, int size, double x, double y, double increasePerc, int survivalTime, int survivalCounter, double decreasePercentage) {
        super(lifeCycle, timeLeft, size, x, y, increasePerc);
        this.survivalTime = survivalTime;
        this.survivalCounter = survivalCounter;
        this.decreaseToCricketPerc = decreasePercentage;
    }

    @Override
    public List<Population> mutate() {
        int sizeLonger = (int)Math.floor(size * x);
        int sizeSame = (int)Math.floor(size * y);
        int sizeSmall = size - sizeLonger - sizeSame;
        List<Population> result = new LinkedList<>();

        if(++survivalCounter == survivalTime)
            return result;

        if(lifeCycle == 1)
            sizeSame += sizeSmall;

        if(sizeLonger > 0)
            result.add(new Parasite(lifeCycle + 1, lifeCycleSpent, sizeLonger, x, y, increasePerc, survivalTime, survivalCounter, decreaseToCricketPerc));
        if(sizeSame > 0)
            result.add(new Parasite(lifeCycle, lifeCycleSpent, sizeSame, x, y, increasePerc, survivalTime, survivalCounter, decreaseToCricketPerc));
        if(sizeSmall > 0 && lifeCycle > 1)
            result.add(new Parasite(lifeCycle - 1, lifeCycleSpent, sizeSmall, x, y, increasePerc, survivalTime, survivalCounter, decreaseToCricketPerc));

        return result;
    }

    @Override
    public void layEggs() {
        super.layEggs();
        survivalCounter = 0;
    }
    @Override
    public String toString() {
        return "{" +
                "num=" + size +
                ",l=" + lifeCycleSpent +
                "/" + lifeCycle + " (" + survivalCounter + "/" + survivalTime + ")" +
                '}';
    }
}
