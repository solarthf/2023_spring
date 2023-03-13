package kh.spring.s02.common.file;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;


public class FileUtil {
	
	private final static String UPLOAD_FOLDER = "\\resources\\uploadfiles";
	
	/**
	 * 파일저장 메소드
	 * @param multi
	 * @param request
	 * @return: saved file path
	 */
	public String saveFile(MultipartFile multi, HttpServletRequest request, String addedPath) throws Exception {
		String renameFilePath = null;
		if(multi != null && !multi.equals("")) {
			String originalFilename = multi.getOriginalFilename();
			
			// file을 server의 특정 위치에 저장
			String webServerRoot = request.getSession().getServletContext().getRealPath("");
			String savePath = webServerRoot + UPLOAD_FOLDER;
			if(addedPath != null) {
				savePath += addedPath;
			}
			// 저장할 폴더(\\resources\\uploadfiles)가 없다면 만들어줘야 함.
			File folder = new File(savePath);
			if(!folder.exists()) {
				folder.mkdirs();
			}
			
			// 시간을 활용한 rename			
			String renameByTime = System.currentTimeMillis()+"_"+ originalFilename;
			// UUID
			// String renameByUUID = UUID.randomUUID().toString()+"_"+ originalFilename;
			
			renameFilePath = savePath + "\\" + renameByTime;
			
			// 파일을 savePath 위치에 저장
			multi.transferTo(new File(savePath + "\\" + renameByTime));			 
		}
		return renameFilePath; 
	}
}
