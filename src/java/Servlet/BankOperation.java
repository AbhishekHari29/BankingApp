package Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Bean.Account;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author abhis
 */
public class BankOperation extends HttpServlet {

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

            String submitbutton = "";
            submitbutton = request.getParameter("submitbutton");

            if (submitbutton.equals("Create Account")) {
                try {
                    String accountNumber = request.getParameter("account_number");
                    String accountName = request.getParameter("account_name");
                    String accountOwner = request.getParameter("account_owner");
                    String accountType = request.getParameter("account_type");
                    double accountBalance = Double.parseDouble(request.getParameter("account_balance"));

                    Account newAccount = new Account();
                    newAccount.addAccountDetails(accountName, accountNumber, accountOwner, accountType, accountBalance);

                    if (newAccount.insertAccountDetails()) {
                        out.println("<h1>Account has been created and added successfully...</h1>");
                    } else {
                        out.println("<h1>Account couldn't be created and added...</h1>");
                    }
                } catch (Exception e) {
                    out.println(e);
                    out.println("<h1>Account couldn't be created and added..." + e.getMessage() + "</h1>");
                }
            } else {
                double amount = 0;
                amount = Double.parseDouble(request.getParameter("amount"));
                Account userAccount = (Account) getServletContext().getAttribute("userAccount");

                if (submitbutton.equals("Deposit")) {
                    if (userAccount.depositAmount(amount)) {
                        out.println("Amount Deposited Successfully...");
                    } else {
                        out.println("Amount could not be Deposited...");
                    }
                }
                if (submitbutton.equals("Withdraw")) {
                    if (userAccount.withdrawAmount(amount)) {
                        out.println("Amount Withdrawn Successfully...");
                    } else {
                        out.println("Amount could not be Withdrawn...");
                    }
                }
                if (submitbutton.equals("Enquiry")) {
                    out.println("<h1>Account Summary</h1><br/>");
                    out.println("Account Name: " + userAccount.getAccountName() + "<br/>");
                    out.println("Account Number: " + userAccount.getAccountNumber() + "<br/>");
                    out.println("Account Owner: " + userAccount.getAccountOwner() + "<br/>");
                    out.println("Account Type: " + userAccount.getAccountType() + "<br/>");
                    out.println("Current Balance: " + userAccount.getCurrentBalance() + "<br/>");
                }
            }
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
