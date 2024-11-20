<%@page import="vn.aptech.entity.CartItem"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Your Cart</title>
</head>
<body>
<h1>Your cart</h1>
<table border="1">
    <thead>
    <tr>
        <th>Product Id</th>
        <th>Description</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Remove</th>
    </tr>
    </thead>
    <tbody>
    <%
        DecimalFormat df = new DecimalFormat("#.00");
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        double totalPrice = 0.0; // Initialize total price

        if (cart != null && !cart.isEmpty()) {
            for (CartItem p : cart) {
                double itemTotal = p.getTotal(); // Assuming getTotal() returns the total price for this item
                totalPrice += itemTotal; // Accumulate total price
    %>
    <tr>
        <td><%= p.getProductId() %></td>
        <td><%= p.getName() %></td>
        <td><%= df.format(itemTotal) %></td> <!-- Format price to 2 decimal places -->
        <td>
            <form action="UpdateCart" method="post">
                <input type="hidden" name="id" value="<%= p.getProductId() %>" />
                <input type="number" name="quantity" value="<%= p.getQuantity() %>" min="1" />
                <input type="submit" value="Update" />
            </form>
        </td>
        <td><a href="RemoveCart?id=<%= p.getProductId() %>">Remove Cart</a></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5">Your cart is empty.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<% if (cart != null && !cart.isEmpty()) { %>
<h3>Total Price: <%= df.format(totalPrice) %></h3> <!-- Display total price -->
<% } %>

<p><b>To change the quantity</b>, enter the new quantity and click on the Update button.</p>
<a href="index.jsp">
    <button type="button">Continue Shopping</button>
</a>
</body>
</html>