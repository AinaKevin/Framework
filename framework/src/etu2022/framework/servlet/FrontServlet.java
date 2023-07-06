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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
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
import java.sql.Date;
import javax.servlet.ServletConfig;

/**
 *
 * @author kevin
 */
//@WebServlet(name = "FrontServlet", urlPatterns = {"/"})
public class FrontServlet extends HttpServlet {
    
    HashMap<String, Mapping> mapping;
    
    public void setMapping(HashMap<String, Mapping> map) {
        this.mapping = map;
    }
    
    public HashMap<String, Mapping> getMapping() {
        return this.mapping;
    }
    
    public String formatFilePath(File file) {
        String className = file.getAbsolutePath().replace(Thread.currentThread().getContextClassLoader().getResource(".").getFile(), "");
        className = className.replace(".class", "");
        className = className.replace("/", ".");
        return className;
    }
    
    public void fillMappingUrl(File file) throws ClassNotFoundException {
        for (File fileUnderFile : file.listFiles()) {
            if (fileUnderFile.isFile() && fileUnderFile.getName().contains(".class")) {
                String className = formatFilePath(fileUnderFile);
                Class classTemp = Class.forName(className);
                checkUrlAnnotation(classTemp);
            } else if (fileUnderFile.isDirectory()) {
                fillMappingUrl(fileUnderFile);
            }
        }
    }
    
    public void checkUrlAnnotation(Class classToChecked) {
        for (Method method : classToChecked.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Url.class)) {
                String url = method.getAnnotation(Url.class).url();
                mapping.put(url, new Mapping(classToChecked.getName(), method.getName()));
            }
        }
    }
    
    @Override
    public void init() throws ServletException {
        mapping = new HashMap<>();
        try {
            fillMappingUrl(new File(Thread.currentThread().getContextClassLoader().getResource(".").getPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ModelView comparer(String variable, String pack, PrintWriter out) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        ModelView rep = null;
        out.print("1");
        if (getMapping().get(variable) instanceof Mapping) {
            out.print("2");
            Mapping v = getMapping().get(variable);
            out.print("3");
            Class classname = Class.forName(pack + v.getClassName());
            out.print("4");
            Object test = classname.newInstance();
            out.print("5");
            Method method = test.getClass().getMethod(v.getMethode());
            out.print("6");
            Object page = method.invoke(test);
            out.print("7");
            rep = (ModelView) page;
        }
        
        return rep;
    }
    
    public boolean checkfield(Object obj, HttpServletRequest req) {
        Field[] fs = obj.getClass().getDeclaredFields();
        for (Field f : fs) {
            if (req.getParameter(f.getName()) == null) {
                return false;
            }
        }
        return true;
    }
    
    public String upperFirst(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
    
    public void fillDataOfModeliew(HashMap<String, Object> hm, HttpServletRequest req) {
        for (Map.Entry<String, Object> entry : hm.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }
    }
    
    public void sprint6(String url, String ObjectPackage, PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        try {
            out.println("hu");
            out.print("url: " + comparer(url, ObjectPackage, out));
            if (comparer(url, ObjectPackage, out) != null) {
                ModelView vue = comparer(url, ObjectPackage, out);
                out.println(vue.getUrl());
                String page = vue.getUrl();
                for (Map.Entry<String, Object> entry : vue.getData().entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                RequestDispatcher dis = request.getRequestDispatcher(page);
                dis.forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void useSet(Object object, HttpServletRequest req) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, ServletException {
//        Enumeration<String> attributeNames = req.getParameterNames();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            String attributeName = field.getName();
//            try {
//                field = object.getClass().getDeclaredField(attributeName);
//            } catch (NoSuchFieldException e) {
//                continue;
//            }
            String attributeValue = req.getParameter(attributeName);
            String methodName = "set" + upperFirst(attributeName);
            Class fieldType = field.getType();
            Method setMethod = object.getClass().getDeclaredMethod(methodName, fieldType);
            if (field.getType().equals(int.class)) {
                int attribute = Integer.parseInt(attributeValue);
                setMethod.invoke(object, attribute);
            }
            if (field.getType().equals(double.class)) {
                double attribute = Double.parseDouble(attributeValue);
                setMethod.invoke(object, attribute);
            }
            if (field.getType().equals(float.class)) {
                float attribute = Float.parseFloat(attributeValue);
                setMethod.invoke(object, attribute);
            }
            if (field.getType().equals(String.class)) {
                String attribute = attributeValue;
                setMethod.invoke(object, attribute);
            }
            if (field.getType().equals(Date.class)) {
                Date attribute = Date.valueOf(attributeValue);
                setMethod.invoke(object, attribute);
            }
            int x = 0;
        }
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
    public void executeAction(HttpServletRequest req, HttpServletResponse resp) {
        if (!req.getServletPath().equals("/")) {
            Mapping mapUsed = null;
            String url = req.getServletPath().split("/")[1];
            mapUsed = mapping.get(url);
            String objectName = mapUsed.getClassName();
            Class classCalled = null;
            Object objectInstance = null;
            Method methodCalled = null;
            try {
                classCalled = Class.forName(objectName);
                objectInstance = classCalled.newInstance();

                //appeler une fonction qui set les attributs d' ObjectInstance
                useSet(objectInstance, req);
                // rechercher la fonction inclut dans Mapping
                methodCalled = objectInstance.getClass().getDeclaredMethod(mapUsed.getMethode());

                // invoker la fonction une fois trouvee
                // pas d'argument
                ModelView mv = (ModelView) methodCalled.invoke(objectInstance);
                //si mvn'as pas de data
                if (mv.getData() != null) {
                    fillDataOfModeliew(mv.getData(), req);
                }
                req.getRequestDispatcher(mv.getUrl()).forward(req, resp);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        executeAction(request, response);
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
