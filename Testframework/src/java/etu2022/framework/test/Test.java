/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2022.framework.test;

import etu2022.framework.ModelView;
import etu2022.framework.Url;
import java.util.Vector;

/**
 *
 * @author kevin
 */
public class Test {
    String voiture;
    String marque;
    public void setVoiture(String v){
        this.voiture = v;
    }
    public String getVoiture(){
      return this.voiture ;
    } 
    
    public void setMarque(String m){
        this.marque = m;
    }
    
    public String getMarque(){
        return this.marque;
    }
    
    public Test(String marque,String voiture){
        this.setMarque(marque);
        this.setVoiture(voiture);
    }
    public Vector<Test> vectorisation(){
        Vector<Test> vector = new Vector<>();
        Test v1 = new Test("mercedes","merc");
        Test v2 = new Test("porsche","porsc");
        vector.add(v1);
        vector.add(v2);
        
        return vector;
    }
    @Url(url="huhu")
    public ModelView hehe(){
        ModelView m = new ModelView("test.jsp");
        m.addItem("jean", "jeannnnnnnnne");
        
//        return new ModelView("test.jsp");
//        System.out.println("etu2022.framework.test.Test.hehe()");
        return m;
    }
   
}
