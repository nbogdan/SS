package hw2;

import java.util.List;

/**
 * Created by Bogdan on 10/17/2016.
 */
public abstract class Population {
    int lifeCycle;
    int lifeCycleSpent;
    int size;
    double x, y; // x - increase, y - keep the same
    double increasePerc;

    public Population(int lifeCycle, int lifeCycleSpent, int size, double x, double y, double ip) {
        this.lifeCycle = lifeCycle;
        this.lifeCycleSpent = lifeCycleSpent;
        this.size = size;
        this.x = x;
        this.y = y;
        this.increasePerc = ip;
    }

    public abstract List<Population> mutate();
    public void layEggs() {
        this.size = (int) Math.floor(size * increasePerc) + size;
        this.lifeCycleSpent = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Population that = (Population) o;

        if (lifeCycle != that.lifeCycle) return false;
        if (lifeCycleSpent != that.lifeCycleSpent) return false;
        if (size != that.size) return false;
        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        return Double.compare(that.increasePerc, increasePerc) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = lifeCycle;
        result = 31 * result + lifeCycleSpent;
        result = 31 * result + size;
        temp = Double.doubleToLongBits(x);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(increasePerc);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "num=" + size +
                ",l=" + lifeCycleSpent +
                "/" + lifeCycle +
                '}';
    }
}
