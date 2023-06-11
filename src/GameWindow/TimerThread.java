package GameWindow;

import javax.swing.*;

public class TimerThread extends Thread{
    JLabel la;
    int sec=0;
    GameThread GT;
    GameRun GR;
    public void getLabel(JLabel la){
        this.la = la;
    }
    public void setGameThread(GameThread GT){
        this.GT=GT;
        getGameRun(GT.getGR());
    }
    public void getGameRun(GameRun GR){
        this.GR=GR;
        getLabel(GR.getLabel());
    }
    public void run(){
        GT.start();

        while(sec<31){
            try {
                sleep(1000);
                sec++;
                la.setText(String.valueOf(sec));
                if (GR.getProblemCount()>10) break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        GR.dispose();
        new GameEnd(GR.getScore());
    }

}
