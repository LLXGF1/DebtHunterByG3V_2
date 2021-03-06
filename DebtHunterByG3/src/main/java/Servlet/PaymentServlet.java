/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Database.DatabaseConnection;
import Entity.Debts;
import Entity.Paymenthistory;
import Entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GuideKai
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/Payment"})
public class PaymentServlet extends HttpServlet {

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_DebtHunterByG3_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        String c = request.getParameter("cost");
        int cost = Integer.parseInt(c);
        HttpSession session = request.getSession();
        Users u = (Users) session.getAttribute("user");
        Paymenthistory ph = new Paymenthistory();

        if (u != null) {
            String insert = "INSERT INTO PAYMENTHISTORY (COST, DEBTS_DEBT_ID) VALUES (?, ?, ?, ?, ?)";
//            String sql = "INSERT INTO DEBTS (DEBT_NAME, DEBTOR_MAIL, DESCRIPTION, COST) VALUES (?, ?, ?, ?)";
            Debts d = new Debts();
            try ( Connection conn = DatabaseConnection.getConn();  PreparedStatement stm = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
                stm.setInt(1, cost);
                stm.setInt(2, d.getDebtId());

                stm.executeUpdate();

                conn.close();

            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (SQLException ex) {
                System.out.println(ex);
            }

            String sql = "UPDATE DEBTS SET Cost = ? WHERE Debt_ID = ? ";
            try ( Connection conn = DatabaseConnection.getConn();  PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setInt(1, d.getCost() - cost);
                stm.setInt(2, d.getDebtId());

                stm.executeUpdate();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EditProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(EditProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
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
