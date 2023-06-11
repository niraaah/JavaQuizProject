package GameWindow;

import javax.swing.*;

public class TimerThread extends Thread{
    JLabel la;
    int sec=0;
    int millis=0;
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
                sleep(10);
                millis+=10;
                secCal();
                la.setText(getSec());
                if (GR.getProblemCount()>10) break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        GR.dispose();
        System.out.println(GR.getScore());
        new GameEnd(GR.getScore());
    }

    void secCal(){
        while(millis>=1000){
            millis-=1000;
            sec++;
        }
    }

    String getSec(){
        String temp = String.valueOf(sec);
        temp+=".";
        temp+=String.valueOf(millis);
        return temp;
    }

}
