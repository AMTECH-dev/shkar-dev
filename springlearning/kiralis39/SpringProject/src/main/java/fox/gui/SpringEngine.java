package fox.door;

import fox.settings.SpringConfigClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringEngine {
    private static AnnotationConfigApplicationContext context;

    public SpringEngine() {
        context = new AnnotationConfigApplicationContext(SpringConfigClass.class);
    }

    public static AnnotationConfigApplicationContext getContext() {
        return context;
    }
}