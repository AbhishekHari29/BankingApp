package Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author abhis
 */
public class CustomerForm extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerForm</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"Style.css\">");
            out.println("</head>");
            out.println("<body>");

            request.getRequestDispatcher("PageLink.html").include(request, response);

            out.println("<div class='container'>\n"
                    + "    <form action='InsertCustomerDetails' method='post'>\n"
                    + "        <caption><b><font size = 6 face = 'calibri'>Customer Form</font></b></caption>\n"
                    + "        <pre>First Name:        <input type='text' name='first_name'></pre>\n"
                    + "        <pre>Last Name:         <input type='text' name='last_name'></pre>\n"
                    + "        <pre>E - Mail:          <input type='text' name='user_mail'></pre>\n"
                    + "        <pre>Contact Number:    <input type='text' name='user_phone'></pre>\n"
                    + "        <pre>Select Gender:     <input type='radio' name='user_gender'value='Male'>Male     <input type='radio' name='user_gender'value='Female'>Female</pre>\n"
                    + "        <center><input type='submit' value='Submit Form'></center>\n"
                    + "    </form>\n"
                    + "</div>");
            out.println("</body>");
            out.println("</html>");
        }
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
