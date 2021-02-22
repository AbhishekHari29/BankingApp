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
public class BankOperation extends HttpServlet {

    static Account acc[] = new Account[10];
    static int accout_count = 0;
	
    public static void Initialize(){
        for(int i=0;i<10;i++)
                acc[i] = new Account();
        acc[0].addAccountDetails("Abhishek", "1001", "abhishek@gmail.com", "Current", 2000);
        acc[1].addAccountDetails("Subha Shri", "1002", "subhashri@gmail.com", "Saving", 3000);
        acc[2].addAccountDetails("Akshaya", "1003", "akshaya@gmail.com", "Current", 2500);
        accout_count = 3;
    }
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
            Initialize();
            response.setContentType("text/html");  
            out.println("<html><head><title>Banking App</title><link rel='stylesheet' type='text/css' href='Style.css'></head><body>");
            request.getRequestDispatcher("PageLink.html").include(request, response);  
            
            String submitbutton = "";
            submitbutton = request.getParameter("submitbutton");
            double amount = Double.parseDouble(request.getParameter("amount"));
            Account userAccount = (Account)getServletContext().getAttribute("userAccount");
            if(submitbutton.equals("Deposit")){
                if(userAccount.depositAmount(amount)){
                    out.println("Amount Deposited Successfully...");
                }
                else{
                    out.println("Amount could not be Deposited...");
                }
            }
            if(submitbutton.equals("Withdraw")){
                if(userAccount.withdrawAmount(amount)){
                    out.println("Amount Withdrawn Successfully...");
                }
                else{
                    out.println("Amount could not be Withdrawn...");
                }
            }
            if(submitbutton.equals("Enquiry")){
                out.println("Account Name: "+userAccount.getAccountName());
                out.println("Account Number: "+userAccount.getAccountNumber());
                out.println("Account Owner: "+userAccount.getAccountOwner());
                out.println("Account Type: "+userAccount.getAccountType());
                out.println("Current Balance: "+userAccount.getCurrentBalance());
            }
            /* TODO output your page here. You may use following sample code. */
            
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
