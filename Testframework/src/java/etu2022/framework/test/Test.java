/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2022.framework.test;

import etu2022.framework.ModelView;
import etu2022.framework.Url;

/**
 *
 * @author kevin
 */
public class Test {
    @Url(url="huhu")
    public ModelView hehe(){
        return new ModelView("test.jsp");
//        System.out.println("etu2022.framework.test.Test.hehe()");
    }
}
