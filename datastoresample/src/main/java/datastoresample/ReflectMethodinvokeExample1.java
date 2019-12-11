package datastoresample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectMethodinvokeExample1 {

	   private static void process(String str) {  
	        System.out.println("processing " + str);  
	    }  
	  
	    public static void main(String... args) throws NoSuchMethodException,  
	            InvocationTargetException, IllegalAccessException {  
	        Method m = ReflectMethodinvokeExample1.class.getDeclaredMethod("process", String.class);  
	        Object rv = m.invoke(null, "test");  
	        System.out.println(rv);  
	    }  
}
