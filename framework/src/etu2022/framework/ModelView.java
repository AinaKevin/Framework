/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kevin
 */
package etu2022.framework;

import java.util.HashMap;

public class ModelView {
    String url;
    HashMap<String ,Object> data = new HashMap<>();

    public String getUrl() {
        return url;
    }
    public  void setData(HashMap<String,Object> map){
        this.data = map ;
    }
    public HashMap<String,Object> getData(){
    return this.data;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public ModelView(String url) {
        this.url = url;
    }
    public ModelView(String url,HashMap data){
        this.setUrl(url);
        this.setData(data);
    }
    
   public void addItem(String key,Object ob){
      this.getData().put(key, ob);
   }
}
