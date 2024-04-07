/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Connexion;
import models.DetailRoute;
import models.Detruit;
import models.Priorite;
import models.Route;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class Reparation extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Connection con = Connexion.getconnection();
            double budget = Double.parseDouble(request.getParameter("budget"));
            String prioriteId = request.getParameter("prioriteId");
            String routeId = request.getParameter("routeId");
            try {
                
                Route route = new Route().getById(routeId, con);
                Priorite priorite = new Priorite().getById(prioriteId, con);
                double newbudget = budget+route.getBudget();
                //
                route.reparer(newbudget,priorite, con);
                //route.updateBudget(newbudget, con);
                //
                Route newRoute = new Route().getById(routeId, con);
                DetailRoute detailRoute = new DetailRoute().getByRoute(routeId, con);
                Priorite[] listPriorite = Priorite.getAll(con);
                Detruit[] listDetruit = Detruit.getLitesByRoute(routeId, con);
                con.close();
                request.setAttribute("listDetruit", listDetruit);
                request.setAttribute("listPriorite", listPriorite);
                request.setAttribute("route", newRoute);
                request.setAttribute("detailRoute", detailRoute);
                RequestDispatcher dispatcher = request.getRequestDispatcher("ListDetruit.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                //e.printStackTrace(out);
                request.setAttribute("error", e.getMessage());
                Route route = new Route().getById(routeId, con);
                DetailRoute detailRoute = new DetailRoute().getByRoute(routeId, con);
                Priorite[] listPriorite = Priorite.getAll(con);
                Detruit[] listDetruit = Detruit.getLitesByRoute(routeId, con);
                request.setAttribute("listDetruit", listDetruit);
                request.setAttribute("listPriorite", listPriorite);
                request.setAttribute("route", route);
                request.setAttribute("detailRoute", detailRoute);
                RequestDispatcher dispatcher = request.getRequestDispatcher("ListDetruit.jsp");
                dispatcher.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Reparation.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Reparation.class.getName()).log(Level.SEVERE, null, ex);
        }
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
