package com.company.with_spring.annotation;

import com.company.with_spring.annotation.owners.Owner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("context.xml");
        context.getBean(Clinic.class)
                .work(context.getBean(Owner.class));
        context.close();
    }
}
