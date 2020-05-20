package Objects;

public class Column {

	private String name, type;
	private Long typeSize;
	private boolean notNull;
	private String table;

	public Column(String name) {
		this.name = name;
		this.notNull = false;
	}

	public Column() {
		this.notNull = false;
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

	@Override
	public boolean equals(Object o) {
		Column other = (Column) o;
		return this.name.equals(other.name);
	}

}
