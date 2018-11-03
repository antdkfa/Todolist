package web;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class BBSList {
	private ArrayList<Integer> seqNoList = new ArrayList<Integer>();
	private ArrayList<String> titleList = new ArrayList<String>();
	private ArrayList<Date> dateList = new ArrayList<Date>();
	private boolean lastPage = false;
	public BBSList() {
	}

	public void setSeqNo(int index, Integer seqNo){
		this.seqNoList.add(index, seqNo);
	}
	
	public void setTitle(int index, String title){
		this.titleList.add(index, title);
	}
	
	public void setDate(int index, Date date) {
		this.dateList.add(index,date);
	}
	public void setLastPage(boolean lastPage){
		this.lastPage = lastPage;
	}
	
	public Integer[] getSeqNo() {
		return seqNoList.toArray(new Integer[seqNoList.size()]);
	}
	
	public String[] getTitle() {
		return titleList.toArray(new String[titleList.size()]);
	}
	
	public Date[] getDate(){
		return dateList.toArray(new Date[dateList.size()]);
	}
 	
	public boolean isLastPage() {
		return lastPage;
	}
	
	public int getListSize() {
		return seqNoList.size();
	}
}
