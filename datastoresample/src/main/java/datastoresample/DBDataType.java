package datastoresample;

import java.lang.reflect.Array;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Blob;
import com.google.cloud.datastore.Key;

public enum DBDataType {
	ARRAY(Array.class), BOOLEAN(boolean.class), BLOB(Blob.class), DATE_TIME(Timestamp.class), INTEGER(long.class), KEY(Key.class),
	STRING(String.class), GEOGRAPHICAL_POlong(long.class), FLOATING_POlong(long.class);

	private Class typeClass;

	DBDataType(Class tclass) {
		this.typeClass = tclass;
	}

	public Class getTypeClass() {
		// TODO Auto-generated method stub
		return this.typeClass;
	}
}
