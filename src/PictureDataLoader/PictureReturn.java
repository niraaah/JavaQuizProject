package PictureDataLoader;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class PictureReturn extends PictureDataLoader{
    protected class PictureContent{//사진의 이미지가 담긴 라벨과 정답을 소유하는 클래스
        public String answer;
        public JLabel la;
        PictureContent(String ans,JLabel la){
            this.answer = ans;
            this.la=la;
        }
    }

    List<PictureContent> pictureContainer = new Vector<>();//라벨과 정답을 쌍으로 묶어서 저장하는 벡터

    public PictureReturn(){
        //생성자 호출 되자마자 벡터에 값 집어넣고 섞은뒤 내보낼 준비 완료
        initContainer();
        shuffleContainer();
    }

    private void initContainer(){
        for(String path : pictureData.keySet()){
            pictureContainer.add(new PictureContent(pictureData.get(path),getImageLabel(path)));
        }
    }

    private void shuffleContainer(){//
        Collections.shuffle(pictureContainer);
    }

    protected PictureContent getContent(){//실질적으로 계속 쓰일 함수
        shuffleContainer();
        PictureContent temp  = removeContent();
        String answerWithoutExtension = temp.answer.substring(0, temp.answer.lastIndexOf('.'));
        temp.answer = answerWithoutExtension;
        return temp;
    }

    private PictureContent removeContent(){
        return pictureContainer.remove(0);
    }

    //make wrong answer return function
    //format is List or Vector
    //content is not overlapping
    //size 3
    //메모장에 오답 선지들을 모두 작성하고
    //파일 입출력 이용해서 오답 선지에 대한 list작성하고
    //그 리스트를 한번 섞고
    //인덱스 0번 1번 2번의 값을 포함하는 리스트나 벡터를 리턴
}
