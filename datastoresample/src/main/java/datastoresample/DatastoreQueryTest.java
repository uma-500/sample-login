package datastoresample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.EntityQuery.Builder;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

public class DatastoreQueryTest {

	public Builder buildQuery(List<QueryData> queryFilterList, List<OrderbyQueryData> orderByDataList)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Builder queryBuilder = Query.newEntityQueryBuilder().setKind("Task");

		PropertyFilter[] propertyFiltersList = new PropertyFilter[queryFilterList.size()];

		for (int i = 0; i < queryFilterList.size(); i++) {
			QueryData queryData = queryFilterList.get(i);
			if (queryData != null) {
				String key = queryData.getKey();
				if (key != null) {

					Method method = PropertyFilter.class.getDeclaredMethod(queryData.getCondition().getCondition(),
							String.class, queryData.getDataType().getTypeClass());
					PropertyFilter propertyFilter = null;

					if (queryData.getDataType().equals(DBDataType.INTEGER)) {
						propertyFilter = (PropertyFilter) method.invoke(null, queryData.getKey(),
								Integer.parseInt(queryData.getValue()));
					}
					if (queryData.getDataType().equals(DBDataType.STRING)) {
						propertyFilter = (PropertyFilter) method.invoke(null, queryData.getKey(), queryData.getValue());
					}
					if (queryData.getDataType().equals(DBDataType.BOOLEAN)) {
						propertyFilter = (PropertyFilter) method.invoke(null, queryData.getKey(),
								Boolean.parseBoolean(queryData.getValue()));
					}
					if (queryData.getDataType().equals(DBDataType.ARRAY)) {
						propertyFilter = (PropertyFilter) method.invoke(null, queryData.getKey(), queryData.getValue());
					}

					propertyFiltersList[i] = propertyFilter;
				}
			}
		}
		OrderBy[] orderByDataArr = new OrderBy[orderByDataList.size()];
		for (int i = 0; i < orderByDataList.size(); i++) {
			OrderbyQueryData orderByData = orderByDataList.get(i);
			if (orderByData != null) {
				String key = orderByData.getKey();
				OrderBy orderByObj;
				Method method = OrderBy.class.getDeclaredMethod(orderByData.getOrderByType().getOrderByType(),
						String.class);
				orderByObj = (OrderBy) method.invoke(null, key);

				orderByDataArr[i] = orderByObj;
			}
		}

		try {
			PropertyFilter propertyFilter1 = propertyFiltersList[0];
			if (propertyFilter1 != null) {
				if (propertyFiltersList.length > 1) {
					PropertyFilter[] listArr = Arrays.copyOfRange(propertyFiltersList, 1, (propertyFiltersList.length));
					if (listArr.length > 0)
						queryBuilder.setFilter(CompositeFilter.and(propertyFilter1, listArr));
				} else {
					queryBuilder.setFilter(propertyFilter1);
				}
			} else {
				System.out.println("No Property Filter exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (orderByDataArr[0] != null) {
			if (orderByDataArr.length > 1) {
				queryBuilder.setOrderBy(orderByDataArr[0],
						Arrays.copyOfRange(orderByDataArr, 1, orderByDataArr.length));
			} else {
				queryBuilder.setOrderBy(orderByDataArr[0]);
			}
		}

		System.out.println("queryBuilder....");
		System.out.println(queryBuilder);
		return queryBuilder;
	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		QueryData qd = new QueryData();

		qd.setKey("description");
		qd.setValue("task 1");
		qd.setCondition(DBQueryCondition.EQUAL);
		qd.setDataType(DBDataType.STRING);

		QueryData qd2 = new QueryData();
		qd2.setKey("priority");
		qd2.setValue("4");
		qd2.setCondition(DBQueryCondition.GREATER_THAN);
		qd2.setDataType(DBDataType.INTEGER);

		List<QueryData> queryFilterList = new ArrayList<QueryData>();

		queryFilterList.add(qd);
		queryFilterList.add(qd2);

		OrderbyQueryData orderbyQueryData = new OrderbyQueryData();
		orderbyQueryData.setKey("priority");
		orderbyQueryData.setOrderByType(OrderbyType.ASC);
		List<OrderbyQueryData> orderByList = new ArrayList<OrderbyQueryData>();
		orderByList.add(orderbyQueryData);

		DatastoreQueryTest dqt = new DatastoreQueryTest();
		// Builder queryBuilder = dqt.buildQuery(queryFilterList);
		Builder queryBuilder = dqt.buildQuery(queryFilterList, orderByList);
		Datastore datastore = DatastoreOptions.newBuilder().setHost("localhost:8081").build().getService();

		Query<Entity> customFieldsQuery = queryBuilder.setLimit(1000).build();

		System.out.println("customFieldsQuery...");
		System.out.println(customFieldsQuery);

		QueryResults<Entity> customFieldresults = datastore.run(customFieldsQuery);

		while (customFieldresults.hasNext()) {
			Entity entity = customFieldresults.next();
			System.out.println("entity...");
			System.out.println(entity);
		}

	}

}
