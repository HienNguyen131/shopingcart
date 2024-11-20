package vn.aptech.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import static java.nio.file.Files.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.aptech.entity.CartItem;
import vn.aptech.entity.Product;

/**
 *
 * @author Nguyễn Thanh Hiền
 */
@WebServlet(name = "AddCart", urlPatterns = {"/AddCart"})
public class AddCart extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            List<Product> products = (List<Product>) session.getAttribute("products");
            String id = request.getParameter("id");
            Product p = findById(Integer.parseInt(id), products);

            List<CartItem> cart = null;
            if (p != null) {

                if (session.getAttribute("cart") == null) {
                    cart = new ArrayList<>();
                } else {
                    cart = (List<CartItem>) session.getAttribute("cart");
                }


                String quantityParam = request.getParameter("quantity");
                int quantity = quantityParam != null ? Integer.parseInt(quantityParam) : 1;


                CartItem existingItem = finItem(Integer.parseInt(id), cart);
                if (existingItem != null) {
                    // If it exists, just increase the quantity
                    existingItem.setQuantity(existingItem.getQuantity() + quantity);
                } else {
                    // If not, add a new item to the cart
                    CartItem newItem = new CartItem(p.getId(), p.getName(), p.getPrice(), quantity);
                    cart.add(newItem);
                }

                // Update the cart in the session
                session.setAttribute("cart", cart);
            }

            // Redirect to the ProductServlet after adding to cart
            response.sendRedirect("ProductServlet");
        }
    }
    Product findById(int id, List<Product> list){
        Product result =null;
        for (Product p: list){
            if(p.getId()==id){
                result = p;
                break;
            }
        }
        return result;
    }
    CartItem finItem(int id,List<CartItem> list){
        CartItem result =null;
        for (CartItem p: list){
            if(p.getProductId()==id){
                result = p;
                break;
            }
        }
        return result;
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
