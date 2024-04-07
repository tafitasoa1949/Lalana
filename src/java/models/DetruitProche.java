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
public class DetruitProche {
    private Detruit detruit;
    private Route route;
    private double longeurRoute;
    private double distanceMin;

    public Detruit getDetruit() {
        return detruit;
    }

    public void setDetruit(Detruit detruit) {
        this.detruit = detruit;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public double getLongeurRoute() {
        return longeurRoute;
    }

    public void setLongeurRoute(double longeurRoute) {
        this.longeurRoute = longeurRoute;
    }

    public double getDistanceMin() {
        return distanceMin;
    }

    public void setDistanceMin(double distanceMin) {
        this.distanceMin = distanceMin;
    }
    public static DetruitProche[] getPlusProche(Route route,Priorite priorite,Connection con)throws Exception{
        DetruitProche[] listDetruitProche = null;
        String sql = null;
        try {
            if(priorite.getId().equals("PRIO002")){
                sql = "select * from vue_detruit_proche where route_id = ? order by distance_min desc";
            }else{
                sql = " select * from vue_detruit_proche where route_id= ? order by distance_min asc;";
            }
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, route.getId());
                ResultSet rs = stmt.executeQuery();
                int rowCount = 0;
                while (rs.next()) {
                    rowCount++;
                }
                listDetruitProche = new DetruitProche[rowCount];
                rs = stmt.executeQuery();
                int index = 0;
                while(rs.next()){
                    DetruitProche detruitProche = new DetruitProche();
                    String detruitId = rs.getString("detruit_id");
                    Detruit detruit = Detruit.getById(detruitId, con);
                    detruitProche.setDetruit(detruit);
                    String route_id = rs.getString("route_id");
                    detruitProche.setRoute(route);
                    double longeur = rs.getDouble("longueur");
                    detruitProche.setLongeurRoute(longeur);
                    double distance_min = rs.getDouble("distance_min");
                    detruitProche.setDistanceMin(distance_min);
                    listDetruitProche[index] = detruitProche;
                    index++;
                }
        } catch (Exception e) {
            throw e;
        }
        } catch (Exception e) {
            throw  e;
        }
        return listDetruitProche;
    }
}
