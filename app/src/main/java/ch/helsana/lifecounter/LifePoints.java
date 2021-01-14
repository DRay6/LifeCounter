package ch.helsana.lifecounter;

public class LifePoints {

    private static LifePoints instance;
    private int lp;
    private static final int startLp = 8000;

    private LifePoints() {
        lp = startLp;
    }

    public static LifePoints getSingletonInstance(){
        if (instance == null){
            instance = new LifePoints();
        }

        return instance;
    }

    public void resetLifePoints(){
        lp = startLp;
    }

    public void modifyLifePoints(int i){
        if (i % 100 == 0){
            lp = lp + i;
        }

        if (lp < 0){
            lp = 0;
        }
    }

    public int getLp() {
        return lp;
    }
}
