<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find a User</title>
</head>
<body>
<form action="findcharacters" method="post">
    <h1>Search for Characters by Email</h1>
    <p>
        <label for="email">Email</label>
        <input id="email" name="email" value="${fn:escapeXml(param.email)}">
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="responseMessage"><b>${messages.response}</b></span>
    </p>
</form>
<br/>
<div id="userCreate"><a href="usercreate">Create Player</a></div>
<br/>
<h1>Matching Characters</h1>
<table border="1">
    <tr>
        <th>UserName</th>
        <th>FirstName</th>
        <th>LastName</th>
        <th>Race</th>
        <th>Clan</th>
        <th>Equipment</th>
        <th>Currency</th>
<%--        <th>Delete Character</th>--%>
<%--        <th>Update Character</th>--%>
    </tr>
    <c:forEach items="${Characters}" var="Characters" >
        <tr>
            <td><c:out value="${Characters.getPlayer().getFullName()}" /></td>
            <td><c:out value="${Characters.getFirstName()}" /></td>
            <td><c:out value="${Characters.getLastName()}" /></td>
            <td><c:out value="${Characters.getRace().getName()}" /></td>
            <td><c:out value="${Characters.getClan().getClan()}" /></td>
            <td><a href="characterequipment?characterid=<c:out value="${Characters.getCharacterID()}"/>">Equipment</a></td>
            <td><a href="charactercurrency?characterid=<c:out value="${Characters.getCharacterID()}"/>">Currency</a></td>
<%--            <td><a href="userdelete?username=<c:out value="${Characters.getUserName()}"/>">Delete</a></td>--%>
<%--            <td><a href="userupdate?username=<c:out value="${Characters.getUserName()}"/>">Update</a></td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>
