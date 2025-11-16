import java.lang.reflect.Method;

public class AnnotationProcessor {
    
    public void processAnnotations() {
        try {
            TestClass testInstance = new TestClass();
            Class<?> clazz = testInstance.getClass();
            
            Method[] methods = clazz.getDeclaredMethods();
            
            for (Method method : methods) {
                if (method.isAnnotationPresent(Invoke.class)) {
                    Invoke invokeAnnotation = method.getAnnotation(Invoke.class);
                    int invokeCount = invokeAnnotation.value();
                    
                    method.setAccessible(true);
                    
                    Class<?>[] paramTypes = method.getParameterTypes();
                    
                    System.out.println("Found annotated method: " + method.getName() + 
                                     ", will invoke " + invokeCount + " times");
                    
                    for (int i = 0; i < invokeCount; i++) {
                        System.out.print("Invocation " + (i + 1) + ": ");
                        
                        Object[] params = createParameters(paramTypes);
                        method.invoke(testInstance, params);
                    }
                    System.out.println();
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Object[] createParameters(Class<?>[] paramTypes) {
        Object[] params = new Object[paramTypes.length];
        
        for (int i = 0; i < paramTypes.length; i++) {
            if (paramTypes[i] == String.class) {
                params[i] = "test" + (i + 1);
            } else if (paramTypes[i] == int.class) {
                params[i] = (i + 1) * 10;
            }
        }
        return params;
    }
}