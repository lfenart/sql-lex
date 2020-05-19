package Objects;


import java.util.ArrayList;
import java.util.List;

public class Column {

	private List<String> datas;
	private String name,type;
	private Long typeSize;
	private boolean notNull;

	public Column(String name) {
		this.name = name;
		setDatas(new ArrayList<String>());
		this.notNull=false;
	}
	public Column() {
		setDatas(new ArrayList<String>());
		this.notNull=false;
	}


	public List<String> getDatas() {
		return datas;
	}

	public void setDatas(List<String> datas) {
		this.datas = datas;
	}
	
	public void addData(String data) {
		this.datas.add(data);
	}
	
	public void removeData(String data) {
		this.datas.remove(data);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getTypeSize() {
		return typeSize;
	}
	public void setTypeSize(Long long1) {
		this.typeSize = long1;
	}
	public boolean isNotNull() {
		return notNull;
	}
	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}
	
}
