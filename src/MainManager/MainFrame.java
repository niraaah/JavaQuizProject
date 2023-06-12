package MainManager;

import GameWindow.GAME;
import ScoreManager.ScoreBoardManager;
<<<<<<< HEAD
import project_setting.GameSettings;
=======
import GameSettingManager.*;
>>>>>>> origin/gamebtn

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    JButton startButton;
    JButton scoreBoardButton;
    JButton exitButton;
    JButton OptionButton;
    JLabel TextLabel;
    ImageIcon gifIcon;
    ImageIcon resizedIcon;
    JLabel gifLabel;
    JLayeredPane layeredPane;

    public MainFrame() {
        super("그림 퀴즈");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

<<<<<<< HEAD
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
=======

        ImageIcon gifIcon = new ImageIcon("home.jpg");
        Image image = gifIcon.getImage();
        Image resizedImage = image.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel gifLabel = new JLabel(resizedIcon);
        gifLabel.setBounds(0,0,600,400);
>>>>>>> origin/gamebtn

        SetImage();
        SetTitle();

        layeredPane.add(TextLabel, BorderLayout.CENTER);
        layeredPane.add(gifLabel, JLayeredPane.DEFAULT_LAYER);

        BGMManager BGM = new BGMManager("music/sqidgameBGM.wav");
        Thread BGMThread = new Thread(BGM);
        BGMThread.start();

<<<<<<< HEAD
        addStartButton();
        addScoreButton();
        addOptionButton();
        addExitButton();

        add(layeredPane);
        setSize(600, 400);
        setVisible(true);
    }

    public void SetImage()
    {
        gifIcon = new ImageIcon("home.jpg");
        Image image = gifIcon.getImage();
        Image resizedImage = image.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImage);
        gifLabel = new JLabel(resizedIcon);
        gifLabel.setBounds(0,0,600,400);
    }

    public void SetTitle()
    {
        TextLabel = new JLabel("Java Quiz");
        Font font = new Font("Arial", Font.BOLD, 36);
        TextLabel.setFont(font);
        TextLabel.setBounds(215,-25,200,200);
    }

    public void addStartButton()
    {
        startButton = new JButton("게임 시작");
        startButton.setBounds(200, 125, 200, 30);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameRun game = new GameRun();
                game.isStart = true;

                layeredPane.setVisible(false);

                add(game.JP);
                setSize(800,600);
                revalidate();
                repaint();
            }
        });
        layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER);
    }
=======
        GAME game = new GAME("게임시작");
        startButton = game.getGameButton();
        startButton.setBounds(200, 125, 200, 30);
        panel.add(startButton);
>>>>>>> origin/gamebtn

    public void addScoreButton()
    {
        scoreBoardButton = new JButton("순위표 보기");
        scoreBoardButton.setBounds(200, 175, 200, 30);
        scoreBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //순위표 함수
                ScoreBoardManager bd = new ScoreBoardManager();
            }
        });
        layeredPane.add(scoreBoardButton, JLayeredPane.PALETTE_LAYER);
    }

    public void addOptionButton()
    {
        OptionButton = new JButton("옵션");
        OptionButton.setBounds(200, 225, 200, 30);
        OptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
                GameSettings GameSetting = new GameSettings();
=======
                new GameSettings();
>>>>>>> origin/gamebtn
            }
        });
        layeredPane.add(OptionButton, JLayeredPane.PALETTE_LAYER);
    }

    public void addExitButton()
    {
        exitButton = new JButton("종료");
        exitButton.setBounds(200, 275, 200, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
<<<<<<< HEAD
        layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);
=======
        panel.add(exitButton);

        frame.add(panel);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
>>>>>>> origin/gamebtn
    }

    public static void main(String args[]){
        new MainFrame();
    }
}