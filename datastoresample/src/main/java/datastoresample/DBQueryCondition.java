package datastoresample;

public enum DBQueryCondition {
	EQUAL("eq"), GREATER_THAN("gt"), LESS_THAN("lt"), GREATER_THAN_OR_EQUAL("ge"), LESS_THAN_OR_EQUAL("le");

	private String condition;

	DBQueryCondition(String condition) {
		this.condition = condition;
		System.out.println(condition);
	}

	public String getCondition() {
		return this.condition;
	}
//	public static void main (String[] args) {
//        System.out.println("DatastoreConditions values: "+DatastoreConditions.values().length);
//    }
}
