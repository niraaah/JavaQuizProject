package GameWindow;

import PictureDataLoader.PictureReturn;
import getAnswer.getNotAnswer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Random;

public class GameRun extends PictureReturn {

    getNotAnswer makerWrong=new getNotAnswer();
    JButton[] fourSelect;
    int totalScore=0;
    int problemCount=0;
    int currentAnswerNum=0;
    public Container c= getContentPane();
    protected PictureContent currentContent;
    JLabel countLabel=new JLabel("0");
    JLabel scoreLabel=new JLabel("0");
    public boolean close =false;

    public GameRun() {}

    public void runGame(){
        setTitle("Game Running");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        c.setLayout(new BorderLayout());
        setInformationLocation();
        this.setButtonGroup();
        this.addEvent();
        this.setButtonLocation();
        setProblem();
        addWindowListener(new windowEvent());
        setSize(1280,800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void setInformationLocation(){
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        tempPanel.add(new JLabel("Timer : "));
        tempPanel.add(countLabel);
        tempPanel.add(new JLabel("Score : "));
        tempPanel.add(scoreLabel);

        c.add(tempPanel,BorderLayout.NORTH);
    }

    void setButtonGroup() { // 4지 선다 버튼 그룹 생성 메서드
        fourSelect=new JButton[4];
        for(int i =0;i<4;i++){
            fourSelect[i]=new JButton();
            fourSelect[i].setSize(40,20);
        }
    }//한번만 실행

    void setButtonLocation(){//버튼을 하단부에 위치시키는 매소드
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        for(int i =0;i<4;i++) {
            buttonPanel.add(fourSelect[i]);
        }
        c.add(buttonPanel, BorderLayout.SOUTH);
    }//한번만 실행

    void addEvent(){//각 버튼마다 이벤트를 추가시키는 메소드
        for(int i =0;i<4;i++){
            fourSelect[i].addActionListener(new AnswerEvent());
        }
    }//한번만 실행

    void callContent(){//이미지 컨텐트를 불러오는 함수
        currentContent = super.getContent();
    }
    void addPicture(){//그림 추가 메소드
        c.add(currentContent.la,BorderLayout.CENTER);
    }
    void renameFourSelect() { // 생성된 버튼에 이름을 다시 할당하는 메서드
        Random ran = new Random();
        currentAnswerNum=ran.nextInt(4);

        List<String> wrongList=makerWrong.getNotAnswers();

        for(int i = 0 ;i <4;i++){
            if(i==currentAnswerNum) fourSelect[i].setText(currentContent.answer);
            else fourSelect[i].setText(wrongList.get(i));
        }

    }

    public int getProblemCount(){
        return problemCount;
    }

    public int getScore(){
        return totalScore;
    }

    void setProblem(){
        problemCount++;
        this.callContent();
        this.renameFourSelect();
        this.addPicture();

    }//문제를 새로 갱신하는 메서드

    public JLabel getLabel() {
        return this.countLabel;
    }

    class AnswerEvent implements ActionListener {//게임중 정답을 누르는 버튼에 이벤트를 추가하는 메소드
        public void actionPerformed(ActionEvent e){
            JButton temp = (JButton) e.getSource();

            if(temp.getText().equals(currentContent.answer)){
                totalScore+=1;
                scoreLabel.setText(String.valueOf(totalScore));
            }

            c.remove(currentContent.la);
            setProblem();
        }
    }

    class windowEvent extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            close = true;
        }
    }
}


