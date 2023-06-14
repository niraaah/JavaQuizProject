package PictureDataLoader;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;

public class PictureDataLoader extends JFrame{

	private static final long serialVersionUID = 1L;
	protected Map<String, String> pictureData; // 그림 데이터를 저장할 맵

	public PictureDataLoader(){
		 // 그림 데이터 초기화
        pictureData = new HashMap<>();
        // 그림 폴더 경로 설정
        String pictureFolderPath = "image";//그림경로 적 
        
        // 그림 폴더에서 이미지 파일들을 읽어서 그림 데이터에 추가
        File folder = new File(pictureFolderPath); 
        if (folder.isDirectory()) { //folder 가 directory
            File[] files = folder.listFiles(); //이미지 경로에 이미지 파일 리스트들을 배열로 설정
            if (files != null) { //아무것도 열
                for (File file : files) { //배열 files배열에 있는 애들 file에 하나씩 넣으면서 반환
                    if (isImageFile(file)) { //file이 이미지 파일이면
                        String filePath = file.getAbsolutePath(); //file의 경로
                        String fileName = file.getName(); //file의 이름
                        pictureData.put(filePath, fileName); //그리고 pictureData에 Hashmap형태로 key는 경로 value는 이름으로 저
                    }
                }
            }
        }

	}

	//파일이 이미지 파일인지 확인하는 메서드
	private boolean isImageFile(File file) {
		String extension = getFileExtension(file);
		return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif");
	}
	// 파일의 확장자를 변환하는 메서드
	 private String getFileExtension(File file) {
	        String extension = "";
	        String fileName = file.getName();
	        int dotIndex = fileName.lastIndexOf(".");
	        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
	            extension = fileName.substring(dotIndex + 1).toLowerCase();
	        }
	        return extension;
	    }
	 //게임을 시작하는 메서드


	protected JLabel getImageLabel(String path) {//키값에 해당하는 이미지를 리턴하는 함수
		ImageIcon img = new ImageIcon(path);

		Image tempImg= img.getImage();
		Image updateImg = tempImg.getScaledInstance(400,500,Image.SCALE_SMOOTH);
		ImageIcon updateIcon=new ImageIcon(updateImg);
		JLabel la = new JLabel(updateIcon);
		return la;
	}
}
       
