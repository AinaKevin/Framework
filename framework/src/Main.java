
import etu2022.framework.Mapping;
import etu2022.framework.Url;
import java.lang.reflect.Method;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kevin
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        /*String[] classes = Mapping.getClassList("wawa");
        for (int i = 0; i < classes.length; i++) {
            System.out.println("name: "+classes[i]);
            Class<?> tempClass=Class.forName("test."+classes[i]);
            Object obj=tempClass.newInstance();
            Method [] methods=obj.getClass().getDeclaredMethods();
            for (int j = 0; j < methods.length; j++) {
                if(methods[j].isAnnotationPresent(Url.class))
                {
                    String url=methods[j].getAnnotation(Url.class).url();
                    String className=classes[i];
                    String methodName=methods[j].getName();
//                    mapping.put(url,new Mapping(className,methodName));
                }
            }
        }*/
        
    }
}
