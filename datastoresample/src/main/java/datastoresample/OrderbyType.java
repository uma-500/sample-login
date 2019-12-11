package datastoresample;

public enum OrderbyType {
	ASC("asc"), DESC("desc");
	private String orderbyType;

	OrderbyType(String type) {
		this.orderbyType = type;
	}

	public String getOrderByType() {
		return this.orderbyType;
	}
}
