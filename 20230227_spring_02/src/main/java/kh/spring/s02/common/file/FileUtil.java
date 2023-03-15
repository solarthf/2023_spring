package kh.spring.s02.common.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtil") // Controller에 @Autowired,@Qualifier("fileUtil")를 같이 써줘야 한다.
@PropertySource("classpath:properties/khs2.properties") // 파일경로를 properties폴더에 저장하고 불러올때 쓰는 어노테이션
public class FileUtil {	
//	private final static String UPLOAD_FOLDER = "${local.repository}";	
	
	@Value("${local.repository}") // @Value 사용시 final static 제거
	private String UPLOAD_FOLDER;
	
	public List<Map<String, String>> saveFileList(MultipartHttpServletRequest multiReq, HttpServletRequest request, String addedPath) throws Exception {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		Iterator<String> iterator = multiReq.getFileNames(); // Name <input name="n1" type="file">
		while(iterator.hasNext()) {
			String name = iterator.next();  // "n1", "n2"
			MultipartFile multiFile = multiReq.getFile(name);
			
			Map<String, String> map = new HashMap<String, String>();
			map = saveFile(multiFile, request, addedPath);			
			result.add(map);
		}		
		return result; 
	}
	/**
	 * 파일저장 메소드
	 * @param multi
	 * @param request
	 * @return: map - "original":original file path, "rename":saved file path
	 */
	public Map<String, String> saveFile(MultipartFile multi, HttpServletRequest request, String addedPath) throws Exception {
		Map<String, String> result = null;
		String renameFilePath = null;
		String renameByTime = null;
		if(multi != null && !multi.equals("")) {
			result = new HashMap<String, String>();
			
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
			renameByTime = System.currentTimeMillis()+"_"+ originalFilename;
			// UUID
			// String renameByUUID = UUID.randomUUID().toString()+"_"+ originalFilename;
			
			renameFilePath = savePath + "\\" + renameByTime;
			
			// 파일을 savePath 위치에 저장
			multi.transferTo(new File(savePath + "\\" + renameByTime));		
			
			result.put("original", originalFilename);
			result.put("rename", renameFilePath);
		}
		return result; 
	}
	

	
}
