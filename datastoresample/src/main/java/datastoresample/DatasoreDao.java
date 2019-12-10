package datastoresample;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.testing.LocalDatastoreHelper;

public class DatasoreDao {
	public static void main(String args[]) {

//		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
//			   // Production
//			 } else {
//			  // Local development server
//			  // which is: SystemProperty.Environment.Value.Development
//			}

		LocalDatastoreHelper helper = LocalDatastoreHelper.create();
		DatastoreOptions dbOptions = helper.getOptions();

		Datastore datastore = dbOptions.newBuilder().setHost("localhost:8081").build().getService();

		// Datastore datastore = dbOptions.getService();

//		Key taskKey = datastore.newKeyFactory().setKind("Task").newKey("task1");
//
//		Entity task = datastore.get(taskKey);
//
//		System.out.println(task);

		// To update an entity
		// Entity task = Entity.newBuilder(datastore.get(taskKey)).set("priority",
		// 5).build();
		// datastore.update(task);

		Query<Entity> customFieldsQuery = Query.newEntityQueryBuilder().setKind("Task")
				.setOrderBy(OrderBy.asc("priority"))
				// .setFilter(PropertyFilter.gt("task1", keyFactory.newKey("task")))
//				.setFilter(CompositeFilter.and(PropertyFilter.lt("priority", 6), 
//						PropertyFilter.eq("description", "task 1")))
				.setLimit(1000).build();
		QueryResults<Entity> customFieldresults = datastore.run(customFieldsQuery);
		System.out.println(" custom field size ");
		System.out.println(customFieldresults);

//		Entity task = datastore.get("task1");

		while (customFieldresults.hasNext()) {
			Entity entity = customFieldresults.next();
			System.out.println("***** entity ****  ");
			System.out.println(entity);
		}

		// Datastore datastore=DatastoreOptions.getDefaultInstance().getService();

		KeyFactory keyFactory = datastore.newKeyFactory().setKind("Task");
		Key taskKey = keyFactory.newKey("task3");
		Entity taskEntity = Entity.newBuilder(taskKey).set("priority", 4).set("done", true).set("description", "task 3").build();
		Entity savedEntity = datastore.put(taskEntity);
		System.out.println("saved entity");
		System.out.println(savedEntity);
//		
//		Key taskKey2 = keyFactory.newKey("task2");
//		Entity taskEntity2 = Entity.newBuilder(taskKey2).set("priority", 2).set("done", false).set("priority", 2).set("description", "task 1").build();
//		Entity savedEntity2 = datastore.put(taskEntity2);
	}

}
