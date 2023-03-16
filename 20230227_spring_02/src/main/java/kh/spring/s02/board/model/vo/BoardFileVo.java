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
public class BoardFileVo {
//	private int boardNum;	// BoardVo의 boardNum과 중복되므로 삭제 무방
	private String originalFilename;
	private String renameFilename;
	 
	
	 

}
