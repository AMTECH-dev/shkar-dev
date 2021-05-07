package fox.door;

import fox.settings.SpringConfigClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringEngine {
    private static AnnotationConfigApplicationContext context;

    public SpringEngine() {
        context = new AnnotationConfigApplicationContext(SpringConfigClass.class);
    }

    public static AnnotationConfigApplicationContext getContext() {
        return context;
    }
}