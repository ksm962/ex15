package example01;

import java.sql.Timestamp;

public class ExampleDTO {

	private int id;
	private String name;
	private String memo;
	private Timestamp wdate;
	
	
	
	public ExampleDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Timestamp getWdate() {
		return wdate;
	}
	public void setWdate(Timestamp wdate) {
		this.wdate = wdate;
	}
	
	
	
	
	
}
