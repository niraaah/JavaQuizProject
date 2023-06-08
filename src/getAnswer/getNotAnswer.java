package getAnswer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class getNotAnswer{
    public static List<String> WrongAnswers(String filePath) { //주어진 파일 경로에서 섞인 오답 선지를 가져오는 메서드
        List<String> wrongAnswers = readOptionsFromFile(filePath);//-메서드 호출해서 모든 선지 읽고 선지 섞은뒤 상위 3개 선지 반환
        Collections.shuffle(wrongAnswers);
        return wrongAnswers.subList(0, 4);
    }

    public static List<String> readOptionsFromFile(String filePath) {//주어진 파일에서 선지를 읽어오는 메소드(파일을 열고 각 줄을 읽어 선지 목록에 추가 후, 모든 선지를 담고 있는 리스트 반환)
        List<String> options = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                options.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return options;
    }

    public List<String> getNotAnswers() { //오답 선지를 리턴하는 메서드(WrongAnswers 메서드 호출하여 오답선지 가져옴)
        String filePath = "src/getAnswer/file.txt";
        List<String> temp =WrongAnswers(filePath);
        return temp;
    }
}
