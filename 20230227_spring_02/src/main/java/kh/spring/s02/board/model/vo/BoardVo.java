package kh.spring.s02.board.model.vo;

import java.sql.Date;
import java.util.List;

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
public class BoardVo {
	private static final long serialVersionUID = 1L;
	private int boardNum;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String boardOriginalFilename;
	private String boardRenameFilename;
	private Date boardDate;
	private int boardLevel;
	private int boardRef;
	private int boardReplySeq;
	private int boardReadcount;
	
	// 1:N 관계일 경우 List 사용 → mapper에서 collection 사용
	private List<BoardFileVo> boardFileList;

//	@Override
//	public String toString() {
//		return "BoardVo [boardNum=" + boardNum + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
//				+ ", boardContent=" + boardContent + ", boardOriginalFilename=" + boardOriginalFilename
//				+ ", boardRenameFilename=" + boardRenameFilename + ", boardDate=" + boardDate + ", boardLevel="
//				+ boardLevel + ", boardRef=" + boardRef + ", boardReplySeq=" + boardReplySeq + ", boardReadcount="
//				+ boardReadcount + ", boardFileList=" + boardFileList + "]";
//	}
//
//	public int getBoardNum() {
//		return boardNum;
//	}
//
//	public void setBoardNum(int boardNum) {
//		this.boardNum = boardNum;
//	}
//
//	public String getBoardTitle() {
//		return boardTitle;
//	}
//
//	public void setBoardTitle(String boardTitle) {
//		this.boardTitle = boardTitle;
//	}
//
//	public String getBoardWriter() {
//		return boardWriter;
//	}
//
//	public void setBoardWriter(String boardWriter) {
//		this.boardWriter = boardWriter;
//	}
//
//	public String getBoardContent() {
//		return boardContent;
//	}
//
//	public void setBoardContent(String boardContent) {
//		this.boardContent = boardContent;
//	}
//
//	public String getBoardOriginalFilename() {
//		return boardOriginalFilename;
//	}
//
//	public void setBoardOriginalFilename(String boardOriginalFilename) {
//		this.boardOriginalFilename = boardOriginalFilename;
//	}
//
//	public String getBoardRenameFilename() {
//		return boardRenameFilename;
//	}
//
//	public void setBoardRenameFilename(String boardRenameFilename) {
//		this.boardRenameFilename = boardRenameFilename;
//	}
//
//	public Date getBoardDate() {
//		return boardDate;
//	}
//
//	public void setBoardDate(Date boardDate) {
//		this.boardDate = boardDate;
//	}
//
//	public int getBoardLevel() {
//		return boardLevel;
//	}
//
//	public void setBoardLevel(int boardLevel) {
//		this.boardLevel = boardLevel;
//	}
//
//	public int getBoardRef() {
//		return boardRef;
//	}
//
//	public void setBoardRef(int boardRef) {
//		this.boardRef = boardRef;
//	}
//
//	public int getBoardReplySeq() {
//		return boardReplySeq;
//	}
//
//	public void setBoardReplySeq(int boardReplySeq) {
//		this.boardReplySeq = boardReplySeq;
//	}
//
//	public int getBoardReadcount() {
//		return boardReadcount;
//	}
//
//	public void setBoardReadcount(int boardReadcount) {
//		this.boardReadcount = boardReadcount;
//	}
//
//	public List<BoardFileVo> getBoardFileList() {
//		return boardFileList;
//	}
//
//	public void setBoardFileList(List<BoardFileVo> boardFileList) {
//		this.boardFileList = boardFileList;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//	public BoardVo() {
//		super();
//	}
	
	
	
	 

}
