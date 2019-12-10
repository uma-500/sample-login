package datastoresample;

public class QueryData {

	private String key;
	private String value;
	private DBQueryCondition condition;
	private DBDataType dataType;

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public DBQueryCondition getCondition() {
		return condition;
	}

	public DBDataType getDataType() {
		return dataType;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setCondition(DBQueryCondition condition) {
		this.condition = condition;
	}

	public void setDataType(DBDataType dataType) {
		this.dataType = dataType;
	}


}
