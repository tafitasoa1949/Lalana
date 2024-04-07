/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class Route {
    private String id;
    private String nom;
    private double budget;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Route() {
    }
    public static Route[] getAll(Connection con)throws Exception{
        String sql = "select * from route";
        Route[] routes = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            routes = new Route[rowCount];
            rs = stmt.executeQuery();
            int index = 0;
            while(rs.next()){
                Route route = new Route();
                route.setId(rs.getString("id"));
                route.setNom(rs.getString("nom"));
                route.setBudget(rs.getDouble("budget"));
                routes[index] = route;
                index++;
            }
        } catch (Exception e) {
            throw e;
        }
        return routes;
    }
    public Route getById(String id,Connection con)throws Exception{
        String sql = "select * from route where id=?";
        Route route = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                route = new Route();
                route.setId(id);
                route.setNom(rs.getString("nom"));
                route.setBudget(rs.getDouble("budget"));
            }
        }catch (Exception e) {
            throw new Exception("Erreur lors de la récupération du route : " + e.getMessage());
        }
        return route;
    }
    public void updateBudget(double addBudget,Connection con)throws Exception{
        String sql = " update route set budget= ? where id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDouble(1, addBudget);
            stmt.setString(2, this.getId());
            stmt.executeUpdate();
        }catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }
    public static double reparerOnePk(double etat,Connection con)throws Exception{
        double budget = 0;
        int newEtat = 0;
        Fanamboarana[] listFanamboaranas = Fanamboarana.getAll(con);
        for(int i=(int)etat ; i < 10 ; i++){
            newEtat = i+1;
            //System.out.println("d"+newEtat);
            budget += listFanamboaranas[newEtat].getTypeProjet().getPrix();
        }
        return budget;
    }
    public static double reparerOneDetruit(Detruit detruit,Connection con)throws Exception{
        double budget = 0;
        try {
            if(detruit.getEtat() == 0){
                Fanamboarana[] listFanamboaranas = Fanamboarana.getAll(con);
                double reparerOne = listFanamboaranas[0].getTypeProjet().getPrix();
                double longeur = detruit.getPkFin()-detruit.getPkDebut();
                budget = reparerOne * longeur;
            }else{
                double reparerOne = reparerOnePk(detruit.getEtat(), con);
                double longeur = detruit.getPkFin()-detruit.getPkDebut();
                budget = reparerOne*longeur;
            }
        } catch (Exception e) {
            throw e;
        }
        return budget;
    }
    public void reparer(double budget,Priorite priorite,Connection con)throws Exception{
        DetruitProche[] list_detruitProche = DetruitProche.getPlusProche(this, priorite, con);
        double prix = 0;
        double reste = budget;
        for(int i=0 ; i < list_detruitProche.length ; i++){
            prix = reparerOneDetruit(list_detruitProche[i].getDetruit(), con);
            reste = reste - prix;
            System.out.println("Prix one"+prix+"Reste"+reste);
            if(reste >= 0){
                list_detruitProche[i].getDetruit().updateEtat(10, con);
                this.updateBudget(reste, con);
            }else{
                throw new Exception("Solde insuffisant.Manque "+Math.abs(reste)+" Ar pour le detruit indice"+list_detruitProche[i].getDetruit().getId());
            }
        }
    }
    public static void main(String args[])throws Exception{
        Connection con = Connexion.getconnection();
        Route route = new Route().getById("R001", con);
        //route.updateBudget(200000, con);
        //System.out.println("reparer one "+reparerOnePk(6,con));
        //Detruit dd = Detruit.getById("DET001", con);
        //System.out.println("budget "+reparerOneDetruit(dd, con));
        Priorite priorite = new Priorite().getById("PRIO001", con);
        route.reparer(1350000, priorite, con);
        con.close();
    }
}
