package datastoresample;

import java.util.ArrayList;
import java.util.List;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.EntityQuery.Builder;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

public class DatastoreQueryTest {

	public Builder buildQuery(List<QueryData> queryFilterList) {
		Builder queryBuilder = Query.newEntityQueryBuilder().setKind("Task");

		for (QueryData queryData : queryFilterList) {
			if (queryData != null) {
				String key = queryData.getKey();
				int value = Integer.parseInt(queryData.getValue());
				if (key != null) {
					PropertyFilter propertyFilter = null;

					this.setPropertyFilter(queryData);

					if (queryData.getCondition().equals(DBQueryCondition.EQUAL)) {
						propertyFilter = PropertyFilter.eq(key, value);
					}

					if (queryData.getCondition().equals(DBQueryCondition.GREATER_THAN)) {
						propertyFilter = PropertyFilter.gt(key, value);
					}

					if (queryData.getCondition().equals(DBQueryCondition.LESS_THAN)) {
						propertyFilter = PropertyFilter.lt(key, value);
					}

					if (queryData.getCondition().equals(DBQueryCondition.GREATER_THAN_OR_EQUAL)) {
						propertyFilter = PropertyFilter.ge(key, value);
					}
					if (queryData.getCondition().equals(DBQueryCondition.LESS_THAN_OR_EQUAL)) {
						propertyFilter = PropertyFilter.le(key, value);
					}
					System.out.println("propertyFilter.....");
					System.out.println(propertyFilter);

					if (propertyFilter != null) {
						queryBuilder.setFilter(propertyFilter);
					}
				}
			}
		}
		System.out.println("queryBuilder....");
		System.out.println(queryBuilder);
		return queryBuilder;

		// Query<Entity> customFieldsQuery = queryBuilder.setLimit(1000).build();

	}

	public PropertyFilter setPropertyFilter(QueryData queryData) {

		PropertyFilter propertyFilter = null;

		// JSONObject jo = new JSONObject();
		if (queryData.getDataType().equals(DBDataType.INTEGER)) {
			int value = Integer.parseInt(queryData.getValue());
		}
		if (queryData.getDataType().equals(DBDataType.STRING)) {
			String value = queryData.getValue();
		}
//		if(queryData.getDataType().equals(DBDataType.BOOLEAN)) {
//			Boolean value= queryData.getValue();
//		}

		return propertyFilter;

	}

	public static void main(String[] args) {
	//	QueryData qd = new QueryData();

//		qd.setKey("name");
//		qd.setValue("task3");
//		qd.setCondition(DBQueryCondition.EQUAL);
//		qd.setDataType(DBDataType.STRING);

		QueryData qd2 = new QueryData();
		qd2.setKey("priority");
		qd2.setValue("1");
		qd2.setCondition(DBQueryCondition.GREATER_THAN);
		qd2.setDataType(DBDataType.INTEGER);

		List<QueryData> queryFilterList = new ArrayList<QueryData>();

	//	queryFilterList.add(qd);
		queryFilterList.add(qd2);

		DatastoreQueryTest dqt = new DatastoreQueryTest();
		Builder queryBuilder = dqt.buildQuery(queryFilterList);

		Datastore datastore = DatastoreOptions.newBuilder().setHost("localhost:8081").build().getService();

		Query<Entity> customFieldsQuery = queryBuilder.setLimit(1000).build();

		QueryResults<Entity> customFieldresults = datastore.run(customFieldsQuery);
		System.out.println("customFieldresults...");
		System.out.println(customFieldresults);

		while (customFieldresults.hasNext()) {
			Entity entity = customFieldresults.next();
			System.out.println("entity...");
			System.out.println(entity);
		}
		System.out.println("customFieldsQuery...");
		System.out.println(customFieldsQuery);

	}

}
