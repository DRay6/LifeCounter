package ch.helsana.lifecounter;

import java.sql.SQLOutput;

public class LifePoints {

    private static LifePoints instance;
    private int lp;
    private static final int startLp = 8000;

    private LifePoints() {
        lp = startLp;
    }

    public static LifePoints getSingletonInstance(){
        System.out.println("SINGLETON: RECEIVED INSTANCE");
        if (instance == null){
            instance = new LifePoints();
        }

        return instance;
    }

    public void resetLifePoints(){
        lp = startLp;
    }

    public void modifyLifePoints(int i){
        System.out.println("SINGLETON: MODIFY POINTS");
        System.out.println(this.lp);
        if (i % 100 == 0){
            lp = lp + i;
        }

        if (lp < 0){
            lp = 0;
        }
        System.out.println(this.lp);
    }

    public int getLp() {
        return lp;
    }
}
