package ir.ac.kntu.model.gameRuntime;

public class GameAI implements Runnable{

    protected Integer X = 0;
    protected Integer Y = 0;
    public Boolean isAlive = true;

    public GameAI(Integer x, Integer y){
        this.X = x;
        this.Y = y;
    }

    @Override
    public void run() {
        //nothing
    }

    public Integer getX() {
        return X;
    }

    public Integer getY() {
        return Y;
    }

    public void setX(Integer x) {
        X = x;
    }

    public void setY(Integer y) {
        Y = y;
    }
}
