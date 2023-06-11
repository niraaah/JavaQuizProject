package GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GAME {
    JButton gameButton;
    public GAME(String text){
        gameButton = new JButton(text);
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimerThread TT=new TimerThread();
                GameRun GR = new GameRun();
                GameThread GT = new GameThread();
                GT.setGR(GR);
                TT.setGameThread(GT);
                TT.start();
            }
        });
    }

    public JButton getGameButton(){
        return this.gameButton;
    }

}
