package GameWindow;

public class GameThread extends Thread{
    GameRun GR;
    public void setGR(GameRun GR){
        this.GR=GR;
    }
    public GameRun getGR(){
        return GR;
    }
    public void run(){
        GR.runGame();
    }
}
