package hw2;

import java.util.LinkedList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        runTrial(1000, 100, 4, 2, false);
    }

    public static void runTrial(int numC, int numP, int lifeCycleC, int lifeCycleP, boolean debug){
        Cricket initialCricket = new Cricket(lifeCycleC,0,numC,0.3,0.3,0.2);
        Parasite initialParasite = new Parasite(lifeCycleP,0,numP,0.3,0.3,0.2,3,0, 0.3);
        List<Population> cricketPopulation = new LinkedList<>();
        List<Population> parasitePopulation = new LinkedList<>();
        int year = 0;
        cricketPopulation.add(initialCricket);
        parasitePopulation.add(initialParasite);


        while(cricketPopulation.size() > 0 && parasitePopulation.size() > 0) {
            year++;
            if(debug) System.out.println("Year: " + year);
            List<Population> cricketDone = new LinkedList<>();
            List<Population> parasiteDone = new LinkedList<>();
            for(Population c : cricketPopulation) {
                c.lifeCycleSpent++;
                if(c.lifeCycleSpent == c.lifeCycle) {
                    cricketDone.add(c);
                }
            }
            for(Population p : parasitePopulation) {
                p.lifeCycleSpent++;
                if(p.lifeCycleSpent == p.lifeCycle) {
                    parasiteDone.add(p);
                }
            }


            if(cricketDone.size() > 0 && parasiteDone.size() > 0) {
                double decreaseRate = ((Parasite) parasiteDone.get(0)).decreaseToCricketPerc;
                for(Population c : cricketDone) {
                    cricketPopulation.remove(c);
                    c.layEggs();
                    c.size -= c.size * decreaseRate;
                    cricketPopulation.addAll(c.mutate());
                }
                for(Population c : parasiteDone) {
                    c.layEggs();
                }
            }
            else if(cricketDone.size() > 0) {
                for(Population c : cricketDone) {
                    c.layEggs();
                }
            }
            else if(parasiteDone.size() > 0) {
                for(Population c : parasiteDone) {
                    parasitePopulation.remove(c);
                    c.lifeCycleSpent = 0;
                    parasitePopulation.addAll(c.mutate());
                }
            }


            int cSize = 0, pSize = 0;
            for(Population c : cricketPopulation) cSize += c.size;
            for(Population c : parasitePopulation) pSize += c.size;
            if(debug) System.out.println("(C) population: " + cricketPopulation);
            if(debug) System.out.println("(P) population: " + parasitePopulation);
            if(debug) System.out.println("Number of populations at end of tour: " + cricketPopulation.size() + "(C)\t" + parasitePopulation.size() + "(P)\t");
            if(debug) System.out.println("Number of individuals at end of tour: " + cSize + "(C)\t" + pSize + "(P)\t");
            if(debug) System.out.println("\n");

            if(!debug) {
                double totalCLifeSpan = 0, totalPLifeSpan = 0;

                for (int i = 0; i < parasitePopulation.size(); i++) {
                    totalPLifeSpan += parasitePopulation.get(i).lifeCycle * parasitePopulation.get(i).size;
                }
                for (int i = 0; i < cricketPopulation.size(); i++) {
                    totalCLifeSpan += cricketPopulation.get(i).lifeCycle * cricketPopulation.get(i).size;
                }

                totalCLifeSpan /= cSize;
                totalPLifeSpan /= pSize;
                System.out.println(year + "\t" + cSize + "\t" + String.format("%.2f", totalCLifeSpan) + "\t" + pSize + "\t" + String.format("%.2f", totalPLifeSpan));
            }

//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

}


