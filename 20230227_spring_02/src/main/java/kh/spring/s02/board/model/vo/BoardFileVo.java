package kh.spring.s02.board.model.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Component // Component를 적어야 스프링이 객체를 전달해줄수 있다.
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardFileVo {
//	private int boardNum;	// BoardVo의 boardNum과 중복되므로 삭제 무방
	private String originalFilename;
	private String renameFilename;
//	@Override
//	public String toString() {
//		return "BoardFileVo [originalFilename=" + originalFilename + ", renameFilename=" + renameFilename + "]";
//	}
//	public String getOriginalFilename() {
//		return originalFilename;
//	}
//	public void setOriginalFilename(String originalFilename) {
//		this.originalFilename = originalFilename;
//	}
//	public String getRenameFilename() {
//		return renameFilename;
//	}
//	public void setRenameFilename(String renameFilename) {
//		this.renameFilename = renameFilename;
//	}
//	public BoardFileVo() {
//		super();
//	}
	 
	
	 

}
