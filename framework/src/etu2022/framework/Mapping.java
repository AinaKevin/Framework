/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2022.framework;

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
}
