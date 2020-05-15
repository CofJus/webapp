<%@ page import="dao.UserDao" %>
<%@ page import="static factory.DaoFactory.getUserDaoInstance" %>
<%@ page import="vo.User" %><%--
  Created by IntelliJ IDEA.
  User: CofJu
  Date: 2020/4/25
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>FUCK JSP</title>
  </head>
  <body>
  <%
    UserDao userDao=getUserDaoInstance();
    User user = userDao.queryById("1806010633");
  %>
  <tr>
    <td><%=user.getId()%></td>
    <td><%=user.getName()%></td>
    <td><%=user.getAcademy()%></td>
    <td><%=user.getMajor()%></td>
  </tr>
  </body>
</html>
