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
public class DetailRoute {
    private String id;
    private Route route;
    private Ville villeDebut;
    private Ville villeFin;
    private double pkDebiut;
    private double pkFin;

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

    public Ville getVilleDebut() {
        return villeDebut;
    }

    public void setVilleDebut(Ville villeDebut) {
        this.villeDebut = villeDebut;
    }

    public Ville getVilleFin() {
        return villeFin;
    }

    public void setVilleFin(Ville villeFin) {
        this.villeFin = villeFin;
    }

    public double getPkDebiut() {
        return pkDebiut;
    }

    public void setPkDebiut(double pkDebiut) {
        this.pkDebiut = pkDebiut;
    }

    public double getPkFin() {
        return pkFin;
    }

    public void setPkFin(double pkFin) {
        this.pkFin = pkFin;
    }

    public DetailRoute() {
    }
    public DetailRoute getByRoute(String id,Connection con)throws Exception{
        String sql = "select * from detail_route where route_id=?";
        DetailRoute detailRoute = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                detailRoute = new DetailRoute();
                detailRoute.setId(rs.getString("id"));
                Route route = new Route().getById(id, con);
                detailRoute.setRoute(route);
                String villeDebutId = rs.getString("ville_debut");
                Ville villeDebut = new Ville().getById(villeDebutId, con);
                detailRoute.setVilleDebut(villeDebut);
                String villeFinId = rs.getString("ville_fin");
                Ville villeFin = new Ville().getById(villeFinId, con);
                detailRoute.setVilleFin(villeFin);
                detailRoute.setPkDebiut(rs.getDouble("pk_debut"));
                detailRoute.setPkFin(rs.getDouble("pk_fin"));
            }
        }catch (Exception e) {
            throw new Exception("Erreur lors de la récupération du detailroute : " + e.getMessage());
        }
        return detailRoute;
    }
}
