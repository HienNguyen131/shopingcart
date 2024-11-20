<%@ page import="vn.aptech.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<h1>CD list</h1>
<h2><a href="viewcart.jsp">View Cart</a></h2>
<table border="1">
    <thead>
    <tr>
        <th>Product Id</th>
        <th>Description</th>
        <th>Price</th>
        <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Product> products = (List<Product>)session.getAttribute("products");
        for(Product p : products){
    %>
    <tr>
        <td><%= p.getId()%></td>
        <td><%= p.getName()%></td>
        <td><%= p.getPrice()%></td>

        <td>
            <form action="AddCart" method="post">
                <input type="hidden" name="id" value="<%= p.getId() %>" />
                <input type="number" name="quantity" value="1" min="1" />
                <input type="submit" value="Add Cart" />
            </form>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>