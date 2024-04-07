/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Connexion;
import models.DetailRoute;
import models.Detruit;
import models.Route;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class SplitServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try {
                //pk debut
                String debut = request.getParameter("pkDebutSplit");
                Connection con = Connexion.getconnection();
                if (debut != null && !debut.isEmpty()) {
                    if(debut.endsWith("-")){
                        String debutValuer = debut.substring(0, debut.length() - 1);
                        double chiffre = Double.parseDouble(debutValuer);
                        //out.print(chiffre);
                        //double pkdebut = Math.abs(pkDebutSplit);
                        String routeId = request.getParameter("routeId");
                        String date = request.getParameter("date");
                        Timestamp dateTimestamp = new Detruit().casterTimestamp(date);
                        String etatSplit = request.getParameter("etatSplit");
                        double etat = Double.parseDouble(etatSplit);
                        Route route = new Route().getById(routeId, con);
                        Detruit detruit = new Detruit();
                        detruit.setRoute(route);
                        detruit.setPkDebut(chiffre);
                        DetailRoute detailRoute = new DetailRoute().getByRoute(route.getId(), con);
                        detruit.setPkFin(detailRoute.getPkFin());
                        detruit.setEtat(etat);
                        detruit.setDate(dateTimestamp);
                        detruit.inserer(con);
                    }else{
                        double pkDebutSplit = Double.parseDouble(request.getParameter("pkDebutSplit"));
                        double pkdebut = Math.abs(pkDebutSplit);
                        String routeId = request.getParameter("routeId");
                        String date = request.getParameter("date");
                        Timestamp dateTimestamp = new Detruit().casterTimestamp(date);
                        String etatSplit = request.getParameter("etatSplit");
                        double etat = Double.parseDouble(etatSplit);
                        Route route = new Route().getById(routeId, con);
                        Detruit detruit = new Detruit();
                        detruit.setRoute(route);
                        detruit.setPkDebut(1);
                        detruit.setPkFin(pkdebut);
                        detruit.setEtat(etat);
                        detruit.setDate(dateTimestamp);
                        detruit.inserer(con);
                    }
                }
                Route[] list_route = Route.getAll(con);
                con.close();
                request.setAttribute("list_route", list_route);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
                /*String pkDebutSplit = request.getParameter("pkDebutSplit");
                String[] valeursPKDebut = pkDebutSplit.split(";");
                double[] PKDebut = new double[valeursPKDebut.length];
                for (int i = 0; i < valeursPKDebut.length; i++) {
                    PKDebut[i] = Double.parseDouble(valeursPKDebut[i]);
                }
                //pk fin
                String pkFinSplit = request.getParameter("pkFinSplit");
                String[] valeursPKFin = pkFinSplit.split(";");
                double[] PKFin = new double[valeursPKFin.length];
                for (int i = 0; i < valeursPKFin.length; i++) {
                    PKFin[i] = Double.parseDouble(valeursPKFin[i]);
                }
                String etatSplit = request.getParameter("etatSplit");
                String[] valeursEtat = etatSplit.split(";");
                double[] etat = new double[valeursPKFin.length];
                for (int i = 0; i < valeursEtat.length; i++) {
                    etat[i] = Double.parseDouble(valeursEtat[i]);
                }
                String routeId = request.getParameter("routeId");
                String date = request.getParameter("date");
                Timestamp dateTimestamp = new Detruit().casterTimestamp(date);
                Connection con = Connexion.getconnection();
                Route route = new Route().getById(routeId, con);
                for(int i=0 ; i < PKDebut.length ; i++){
                    Detruit detruit = new Detruit();
                    detruit.setRoute(route);
                    detruit.setPkDebut(PKDebut[i]);
                    detruit.setPkFin(PKFin[i]);
                    detruit.setEtat(etat[i]);
                    detruit.setDate(dateTimestamp);
                    detruit.inserer(con);
                }
                Route[] list_route = Route.getAll(con);
                con.close();
                request.setAttribute("list_route", list_route);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);*/
            } catch (Exception e) {
                e.printStackTrace(out);
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
