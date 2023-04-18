/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2022.framework;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Mapping {
    String className;
    String Methode;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethode() {
        return Methode;
    }

    public void setMethode(String Methode) {
        this.Methode = Methode;
    }

    public Mapping(String className, String Methode) {
        this.setClassName(className);
        this.setMethode(Methode);
    } 
     public static String[] getClassList(String directoryPath)
    {
        File file=new File(directoryPath);
//        ty zany afaka simplifier-na otran'zao
//        de filtrena fotsiny le .java avy eo
        File[] f=file.listFiles();
        FilenameFilter textFilefilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
//        String lowercaseName = name.toLowerCase();
                if (name.endsWith(".java")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        String [] listefile=file.list(textFilefilter);
        for (int i = 0; i < listefile.length; i++) {
            listefile[i]=listefile[i].split(".java")[0];
        }
        return listefile;
    }
///home/kevin/Documents/GitHub/Framework/framework/src/etu2022/framework/Mapping.java

public static HashMap<String, Mapping> getMethodsHashMapFromPackage(String packageDirectory,String ObjectPackage) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        HashMap<String,Mapping> mapping=new HashMap<>();

        String[] classes=getClassList(packageDirectory);
        for (int i = 0; i < classes.length; i++) {
            System.out.println("name: "+classes[i]);
            Class<?> tempClass=null;
            try{
                tempClass = Class.forName(ObjectPackage+classes[i]);
            }catch(ClassNotFoundException e){
                e.getMessage();
            }
            Object obj=tempClass.newInstance();
            Method [] methods=obj.getClass().getDeclaredMethods();
            for (int j = 0; j < methods.length; j++) {
                if(methods[j].isAnnotationPresent(Url.class))
                {
                    String url=methods[j].getAnnotation(Url.class).url();
                    String className=classes[i];
                    String methodName=methods[j].getName();
                    mapping.put(url,new Mapping(className,methodName));
                }
            }
        }
        return mapping;
    }
}
