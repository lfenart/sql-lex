package Objects;


import java.util.ArrayList;
import java.util.List;

public class Column {

	private List<String> datas;
	private String name;

	public Column(String name) {
		setDatas(new ArrayList<String>());
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
	
}
