package fr.univtln.bruno.po43;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        //We use the reflections library to easily scan packages and classes
        Reflections reflections = new Reflections("fr.univtln");
        //We get all Classes implementing the SayHello Interface
        Set<Class<? extends SayHello>> helloClasses = reflections.getSubTypesOf(SayHello.class);

        //We get a constructor for each class and call the getMyName method.
        for (Class<? extends SayHello> sayHello : helloClasses) {
            try {
                logger.log(Level.INFO, "Hello {0}", sayHello.getConstructor().newInstance().sayHello());
            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}