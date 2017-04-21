package tt.access;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import tt.service.TTServiceImpl;

public class Access {

	
	
	public static void createAccess(Class clazz)  {
		
		try {
			Field fields[] = clazz.getDeclaredFields();
			Method[] methods = clazz.getMethods();
			
			for (Field field : fields) {
				  //if ( !Modifier.isPublic(field.getModifiers())) {
				    //field.setAccessible(true);
				  //}
				  System.out.println("Field: " + field.getName());
				  //System.out.println(", value: " + field.get(someObject));
			}
			
			for(Method method: methods) {
				//System.out.println("Method : " + method);
			}
			
			clazz.getAnnotations();
			//Method method;
			//method = clazz.getMethod("test",null);
			//method.setAccessible(false);
		
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
