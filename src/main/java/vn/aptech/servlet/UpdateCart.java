package vn.aptech.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.aptech.entity.CartItem;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateCart", urlPatterns = {"/UpdateCart"})
public class UpdateCart extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        String id = request.getParameter("id");
        String quantityParam = request.getParameter("quantity");
        int quantity = Integer.parseInt(quantityParam);

        if (cart != null) {
            CartItem item = finItem(Integer.parseInt(id), cart);
            if (item != null) {
                item.setQuantity(quantity);
            }
        }

        // Update the cart in the session
        session.setAttribute("cart", cart);
        response.sendRedirect("viewcart.jsp");
    }

    // ... existing finItem method
    CartItem finItem(int id, List<CartItem> list) {
        CartItem result = null;
        for (CartItem p : list) {
            if (p.getProductId() == id) {
                result = p;
                break;
            }
        }
        return result;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}