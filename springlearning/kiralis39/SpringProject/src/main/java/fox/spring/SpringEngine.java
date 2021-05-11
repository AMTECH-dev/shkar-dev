package fox.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringEngine {
    private static AnnotationConfigApplicationContext context;

    public SpringEngine() {
        context = new AnnotationConfigApplicationContext(SpringConfig.class);
    }

    public static AnnotationConfigApplicationContext getContext() {
        return context;
    }

	public static void close() {context.close();}
}