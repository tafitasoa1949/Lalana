/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Connexion;
import models.Detruit;
import models.Priorite;
import models.Route;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class DetruitServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Connection con = Connexion.getconnection();
            try {
                String routeId = request.getParameter("routeId");
                String date = request.getParameter("date");
                Route route = new Route().getById(routeId, con);
                // Récupérer les valeurs des champs pdDebut
                String[] pkDebutValues = request.getParameterValues("pkDebut[]");
                String[] pkFinValues = request.getParameterValues("pkFin[]");
                String[] list_etat = request.getParameterValues("etat[]");
                Timestamp dateTimestamp = new Detruit().casterTimestamp(date);
                if (pkDebutValues != null) {
                    for (int i = 0; i < pkDebutValues.length ; i++) {
                        Detruit detruit = new Detruit();
                        detruit.setRoute(route);
                        double pk_debut = Double.parseDouble(pkDebutValues[i]);
                        detruit.setPkDebut(pk_debut);
                        double pk_fin = Double.parseDouble(pkFinValues[i]);
                        detruit.setPkFin(pk_fin);
                        double etat = Double.parseDouble(list_etat[i]);
                        detruit.setEtat(etat);
                        detruit.setDate(dateTimestamp);
                        detruit.inserer(con);
                    }
                }else{
                    out.print("Il y a un champ vide");
                }
                
                Route[] list_route = Route.getAll(con);
                con.close();
                request.setAttribute("list_route", list_route);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                out.print(e.getMessage());
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
            Logger.getLogger(DetruitServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DetruitServlet.class.getName()).log(Level.SEVERE, null, ex);
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
