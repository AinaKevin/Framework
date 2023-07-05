<%-- 
    Document   : test
    Created on : 7 avr. 2023, 11:44:48
    Author     : kevin
--%>
<%@page import="etu2022.framework.test.Test"%>
<%
   Object obj = request.getAttribute("listetest");
    Test[]emps = (Test[])obj;
     out.println("tiko ndry");
        
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h1>Jean</h1>
       <%
            for (Test emp : emps) {
                    out.println(emp.getMarque());
            }
        %>
    </body>
</html>
