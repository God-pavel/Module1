package study;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.model.Event;
import study.model.User;
import study.storage.Storage;

import java.util.Map;

public class Application {
    public static void main(String [] args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Storage storage = applicationContext.getBean("storage", Storage.class);

        for (Map.Entry<String, Event> stringEventEntry : storage.getEvents().entrySet()) {
            System.out.println(stringEventEntry);
        }
        for (Map.Entry<String, User> stringEventEntry : storage.getUsers().entrySet()) {
            System.out.println(stringEventEntry);
        }
    }
}
