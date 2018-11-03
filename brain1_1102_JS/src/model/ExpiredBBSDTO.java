package model;

import java.sql.Date;

public class ExpiredBBSDTO {
	private int seqNo;	//게시물 번호
	private String title;	// 제목
	private String content;	// 내용
	private Date date;		// 만료 날짜
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
