/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

        

/**
 *
 * @author AbhishekHari
 */

public class UserDetails extends HttpServlet {
    
    /*static User user[] = new User[10];
    static int user_count = 0;
    
    public static void Initialize(){
        for(int i=0;i<10;i++)
                user[i] = new User();
        user[0].addUser("Abhishek", "abhishek@gmail.com", "M", "1234");
        user[1].addUser("Subha Shri", "subhashri@gmail.com", "F", "12345");
        user[2].addUser("Akshaya", "akshaya@gmail.com", "F", "123456");
        user_count = 3;
    }*/

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

            
            response.setContentType("text/html");  
            out.println("<html><head><title>Banking App</title><link rel='stylesheet' type='text/css' href='Style.css'></head><body>");
            request.getRequestDispatcher("PageLink.html").include(request, response);
            
            String submitbutton = request.getParameter("submitbutton");
            if(submitbutton.equals("Login")){
                
                String userID = request.getParameter("user_id");
                String password = request.getParameter("user_pass");
                
                User result = null;
                try{
                    result = User.userExist(userID);
                }
                catch(Exception e){
                    out.println("<h1>"+e+"</h1>");
                }
                if(result == null){
                    out.println("<h1>User Account does not exist...</h1>");
                    
                }
                else{
                    if(result.validateUser(password)){
                            out.println("<h1>Hello "+result.getUserName()+" ! Welcome to Banking App</h1>");
                            Cookie ck = new Cookie("uname",result.getUserName());  
                            response.addCookie(ck);
                            getServletContext().setAttribute("loginUser", result);
                            response.sendRedirect("HomePage");
                    }
                    else{
                            out.println("<h1>Password is incorrect</h1>");
                            request.getRequestDispatcher("LoginPage.html").include(request, response);
                            
                    }
                }
                
            }
            if(submitbutton.equals("Create Account")){
                
                String userName = request.getParameter("user_name");
                String userID = request.getParameter("user_id");
                String gender = request.getParameter("gender");
                String password1 = request.getParameter("password1");
                String password2 = request.getParameter("password2");

                
                if(password1.equals(password2)){
                    User result = User.userExist(userID);
                    if(result == null){
                        result = new User();
                        result.addUser(userName, userID, gender, password2);
                        if(result.insertUser()){
                            out.println("<h1>User Account created and added successfully");
                        }
                        else{
                            out.println("<h1>User could not be added</h1>");
                        }
                    }
                    else{
                        out.println("<h1>User Account already exists...</h1>");
                    }
                }
                else{
                    out.print("<h1>Password does not match</h1>");
                }
            }
            
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