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
    public Test(){}
    public Test(String marque,String voiture){
        this.setMarque(marque);
        this.setVoiture(voiture);
    }
    @Url(url="emp-save")
    public ModelView empsave(){
        ModelView mv = new ModelView("sauvegarde.jsp");
        System.out.println(getMarque());
        System.out.println(getVoiture());
        return mv;
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
         Test[] tests={new Test("jean","jean"),new Test("nissan","gtr")};
//        return new ModelView("test.jsp");
//        m.addItem("listetest", tests);
          m.addItem("listetest", tests);
        return m;
    }
    
    @Url(url="huhu")
    public ModelView sprint8(int id){
        ModelView mv =  new ModelView("sprint8.jsp");
        System.out.println(id);
        return mv;
    }
   
}
