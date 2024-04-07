/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Tafitasoa-P15B-140
 */
public class Detruit {
    private String id;
    private Route route;
    private double pkDebut;
    private double pkFin;
    private double etat;
    private Timestamp date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public double getPkDebut() {
        return pkDebut;
    }

    public void setPkDebut(double pkDebut) {
        this.pkDebut = pkDebut;
    }

    public double getPkFin() {
        return pkFin;
    }

    public void setPkFin(double pkFin) {
        this.pkFin = pkFin;
    }

    public double getEtat() {
        return etat;
    }

    public void setEtat(double etat) {
        this.etat = etat;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Detruit() {
    }
    public String getNextId(Connection con)throws Exception{
        String sql = "SELECT MAX(id) AS max_id FROM detruit";
        String nextId = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String maxId =  rs.getString("max_id");
                if(maxId != null){
                    String prefix = maxId.substring(0, 3);
                    int numericPart = Integer.parseInt(maxId.substring(3));
                    numericPart++;
                    nextId = prefix + String.format("%03d", numericPart);
                }else{
                    nextId = "DET001";
                }
            }
        } catch (Exception e) {
            throw new Exception("Erreur id non recuperer : "+e.getMessage());
        }
        return nextId;
    }
    public void inserer(Connection con) throws Exception{
        String sql = "insert into detruit values(?,?,?,?,?,?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, this.getNextId(con));
            stmt.setString(2, this.getRoute().getId());
            stmt.setDouble(3, this.getPkDebut());
            stmt.setDouble(4, this.getPkFin());
            stmt.setDouble(5, this.getEtat());
            stmt.setTimestamp(6, this.getDate());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'insertion d'un detruit : " + e.getMessage());
        }
    }
    public static Detruit[] getAll(Connection con)throws Exception{
        String sql = "select * from detruit";
        Detruit[] detruits = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            detruits = new Detruit[rowCount];
            rs = stmt.executeQuery();
            int index = 0;
            while(rs.next()){
                Detruit detruit = new Detruit();
                detruit.setId(rs.getString("id"));
                String route_id = rs.getString("route_id");
                Route route = new Route().getById(route_id, con);
                detruit.setRoute(route);
                detruit.setPkDebut(rs.getDouble("pk_debut"));
                detruit.setPkFin(rs.getDouble("pk_fin"));
                detruit.setEtat(rs.getDouble("etat"));
                detruit.setDate(rs.getTimestamp("date"));
                detruits[index] = detruit;
                index++;
            }
        } catch (Exception e) {
            throw e;
        }
        return detruits;
    }
    public static Detruit getById(String id,Connection con)throws Exception{
        String sql = "select * from detruit where id=?";
        Detruit detruit = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                detruit = new Detruit();
                detruit.setId(id);
                String route_id = rs.getString("route_id");
                Route route = new Route().getById(route_id, con);
                detruit.setRoute(route);
                detruit.setPkDebut(rs.getDouble("pk_debut"));
                detruit.setPkFin(rs.getDouble("pk_fin"));
                detruit.setEtat(rs.getDouble("etat"));
                detruit.setDate(rs.getTimestamp("date"));
                
            }
        }catch (Exception e) {
            throw new Exception("Erreur lors de la récupération du detruit : " + e.getMessage());
        }
        return detruit;
    }
    public static Detruit[] getLitesByRoute(String routeId,Connection con)throws Exception{
        String sql = "select * from detruit where route_id = ?";
        Detruit[] detruits = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, routeId);
            ResultSet rs = stmt.executeQuery();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            detruits = new Detruit[rowCount];
            rs = stmt.executeQuery();
            int index = 0;
            while(rs.next()){
                Detruit detruit = new Detruit();
                detruit.setId(rs.getString("id"));
                String route_id = rs.getString("route_id");
                Route route = new Route().getById(route_id, con);
                detruit.setRoute(route);
                detruit.setPkDebut(rs.getDouble("pk_debut"));
                detruit.setPkFin(rs.getDouble("pk_fin"));
                detruit.setEtat(rs.getDouble("etat"));
                detruit.setDate(rs.getTimestamp("date"));
                detruits[index] = detruit;
                index++;
            }
        } catch (Exception e) {
            throw e;
        }
        return detruits;
    }
    public Timestamp casterTimestamp(String dateInput)throws Exception{
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Convertissez la chaîne en objet Date
            Date parsedDate = dateFormat.parse(dateInput);

            // Convertissez la Date en Timestamp
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (Exception e) {
            throw e;
        }
    }
    public void updateEtat(double etat,Connection con)throws Exception{
        String sql = "update detruit set etat = ? where id= ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDouble(1, etat);
            stmt.setString(2, this.getId());
            stmt.executeUpdate();
        }catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }
    public static void main(String args[])throws Exception{
        Connection con = Connexion.getconnection();
        Detruit[] listDetruit = Detruit.getLitesByRoute("R003", con);
        for (int i = 0; i < listDetruit.length; i++) {
            System.out.println(listDetruit[i].getEtat());
        }
        con.close();
    }
}
