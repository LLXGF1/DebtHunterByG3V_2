<%-- 
    Document   : EditProfile
    Created on : Nov 9, 2020, 8:46:41 PM
    Author     : GuideKai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit Profile</h1>
        <form action="EditProfile" method="POST">
            <p>First Name  <input type="text" name="firstname" value="" /> </p>
            <p>Last Name  <input type="text" name="lastname" value="" /> </p>
            <p>Tel.  <input type="text" name="tel" value=""/> </p> 
            <p><input type="Submit" name="Confirm"> </p> 
        </form>
        <a href="Profile.jsp"> Back</a>
    </body>
</html>
