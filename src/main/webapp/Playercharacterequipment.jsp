<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>BlogComments</title>
</head>
<body>
<h1>${messages.title}</h1>
<table border="1">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Slot</th>
        <th>Item</th>
    </tr>
    <c:forEach items="${characterEquipments}" var="characterEquipments" >
        <tr>
            <td><c:out value="${characterEquipments.getCharacter().getFirstName}" /></td>
            <td><c:out value="${characterEquipments.getCharacter().getLastName()}" /></td>
            <td><c:out value="${characterEquipments.getSlot().getName()}" /></td>
            <td><c:out value="${characterEquipments.getItem().getItemName()}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
