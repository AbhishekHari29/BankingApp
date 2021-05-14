package Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Bean.User;
import Bean.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author abhis
 */
public class HomePage extends HttpServlet {

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

            response.setContentType("text/html");
            out.println("<html>\n"
                    + "<head>\n"
                    + "    <title>Banking App</title>\n"
                    + "    <link rel='stylesheet' type='text/css' href='Style.css'>\n"
                    + "</head>\n"
                    + "<body>");
            request.getRequestDispatcher("PageLink.html").include(request, response);

            User loginUser = (User) getServletContext().getAttribute("loginUser");

            Cookie ck[] = request.getCookies();
            if (ck != null) {
                String name = ck[0].getValue();
                if (!name.equals("") || name != null) {
                    out.println("<h1><b>Welcome to Banking App</b></h1>");
                    out.println("<h1>Hello " + loginUser.getUserName() + "</h1>");
                    Account[] userAccount = Account.findAccount(loginUser.getUserID());
                    if (userAccount != null) {
                        getServletContext().setAttribute("userAccount", userAccount[0]);
                        for (Account a : userAccount) {
                            out.println("<hr>Account Number: " + a.getAccountNumber() + "<br>");
                            out.println("Account Type: " + a.getAccountType() + "<br>");
                            out.println("Current Balance: " + a.getCurrentBalance() + "<br><hr>");
                        }
                        out.println("<form action='BankOperation' method='post'>\n"
                                + "    <input type='text' style='width: 500px;' name='amount' value='0'><br>\n"
                                + "    <input type='submit' style='width: 250px;' value='Deposit' name='submitbutton'>\n"
                                + "    <input type='submit' style='width: 250px;' value='Withdraw' name='submitbutton'><br>\n"
                                + "    <input type='submit' style='width: 500px;' value='Enquiry' name='submitbutton'><br>\n"
                                + "</form>");
                    } else {
                        out.println("<h1>You don't have an Bank Account...</h1><br>");
                        out.println("<div class='container' style='margin:200px 0px'>\n"
                                + "    <form action='BankOperation' method='POST'>\n"
                                + "        <caption><b><font size = 6 face = 'calibri'>Create Account</font></b></caption>\n"
                                + "        <pre>Accout Name:      <input type='text' name='account_name'></pre>\n"
                                + "        <pre>Account Number:   <input type='text' name='account_number'></pre>\n"
                                + "        <pre>Account Owner:    <input type='text' name='account_owner'></pre>\n"
                                + "        <pre>Account Type:     <input type='text' name='account_type'></pre>\n"
                                + "        <pre>Current Balance:  <input type='text' name='account_balance'></pre>\n"
                                + "        <input type='submit' value='Create Account' name = 'submitbutton'>\n"
                                + "    </form>\n"
                                + "</div>");
                    }
                }
            } else {
                request.getRequestDispatcher("LoginPage.html").forward(request, response);
            }

            out.println("</body></html>");
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
