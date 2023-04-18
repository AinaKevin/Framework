/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu2022.framework.servlet;


import etu2022.framework.Mapping;
import static etu2022.framework.Mapping.getMethodsHashMapFromPackage;
import etu2022.framework.ModelView;
import etu2022.framework.Url;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kevin
 */
//@WebServlet(name = "FrontServlet", urlPatterns = {"/"})
public class FrontServlet extends HttpServlet {
        HashMap<String,Mapping> mapping;
    
    public void setMapping(HashMap<String,Mapping> map){
        this.mapping = map;
    }
    public HashMap<String,Mapping> getMapping(){
        return this.mapping;
    }

    @Override
    public void init() throws ServletException {
        String packageDirectory = "/home/kevin/Documents/GitHub/Framework/Testframework/src/java/etu2022/framework/test";
        String ObjectPackage = "etu2022.framework.test.";
        try { 
            HashMap<String, Mapping> v = new HashMap();
            v =Mapping.getMethodsHashMapFromPackage(packageDirectory, ObjectPackage);
            this.setMapping(v);
        } catch (Exception e) {
            System.out.println("HashMapnotfound");
        }
    }
    
    public ModelView comparer(String variable,String pack,PrintWriter out) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        ModelView rep =null;
        out.print("1");
        if(getMapping().get(variable) instanceof Mapping){
            out.print("2");
            Mapping v = getMapping().get(variable);
            out.print("3");
            Class classname = Class.forName(pack + v.getClassName());
            out.print("4");
            Object test  = classname.newInstance();
            out.print("5");
            Method method = test.getClass().getMethod(v.getMethode());
            out.print("6");
            Object page = method.invoke(test);
            out.print("7");
            rep = (ModelView) page;
        }
        return rep;
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String ObjectPackage = "etu2022.framework.test.";
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FrontServlet at " + request.getContextPath() + "</h1>");
            String url = request.getServletPath().split("/")[1];
//            out.print(this.getMapping().get(url));
            try {
                out.println("hu");
                out.print("url: "+comparer(url,ObjectPackage,out));
                if(comparer(url,ObjectPackage,out)!=null){
                    ModelView vue = comparer(url,ObjectPackage,out);
                    Set jeddy = vue.getData(). keySet();
                    for (Map.Entry entry : vue.getData().entrySet()) {
                        request.setAttribute((String) entry.getKey(),entry.getValue());
                        Object key = entry.getKey();
                        Object val = entry.getValue();
                 
                    }
                    out.println(vue.getUrl());
                    String page = vue.getUrl();
                    RequestDispatcher dis = request.getRequestDispatcher(page);
                    dis.forward(request,response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
