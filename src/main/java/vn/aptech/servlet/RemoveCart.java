package vn.aptech.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.aptech.entity.CartItem;

/**
 *
 * @author Nguyễn Thanh Hiền
 */
@WebServlet(name = "RemoveCart", urlPatterns = {"/RemoveCart"})
public class RemoveCart extends HttpServlet {

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
        // Thiết lập kiểu nội dung phản hồi
        response.setContentType("text/html;charset=UTF-8");

        // Lấy session hiện tại
        HttpSession session = request.getSession();

        // Lấy giỏ hàng từ session
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        // Nhận id của sản phẩm cần xoá từ request
        String id = request.getParameter("id");

        // Nếu giỏ hàng không rỗng và id không null
        if (cart != null && id != null) {
            // Tìm sản phẩm trong giỏ hàng
            CartItem existingItem = finItem(Integer.parseInt(id), cart);

            // Nếu sản phẩm có trong giỏ hàng, xoá nó
            if (existingItem != null) {
                cart.remove(existingItem);
            }
        }

        // Cập nhật lại giỏ hàng trong session
        session.setAttribute("cart", cart);

        // Chuyển hướng về trang giỏ hàng (viewcart.jsp)
        response.sendRedirect("viewcart.jsp");
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