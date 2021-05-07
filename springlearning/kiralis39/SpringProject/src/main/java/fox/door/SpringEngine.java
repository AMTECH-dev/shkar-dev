package fox.door;

import fox.annotations.Spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringEngine {
    private static ClassPathXmlApplicationContext context;

    @Spring
    public SpringEngine() {
        context = new ClassPathXmlApplicationContext("appCont.xml");

    }

    public static AbstractApplicationContext getContext() {
        return context;
    }

    public static void closeContext() {
        if (context != null) {context.close();}
    }
}
