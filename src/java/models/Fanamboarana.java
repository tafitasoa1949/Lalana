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
public class Fanamboarana {
    private String id;
    private TypeProjet typeProjet;
    private double etat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeProjet getTypeProjet() {
        return typeProjet;
    }

    public void setTypeProjet(TypeProjet typeProjet) {
        this.typeProjet = typeProjet;
    }

    public double getEtat() {
        return etat;
    }

    public void setEtat(double etat) {
        this.etat = etat;
    }

    public Fanamboarana() {
    }
    public static Fanamboarana[] getAll(Connection con)throws Exception{
        String sql = "select * from fanamboarana";
        Fanamboarana[] fanamboarana_list = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            fanamboarana_list = new Fanamboarana[rowCount];
            rs = stmt.executeQuery();
            int index = 0;
            while(rs.next()){
                Fanamboarana fanamboarana = new Fanamboarana();
                fanamboarana.setId(rs.getString("id"));
                String type_id = rs.getString("type_id");
                TypeProjet typeProjet = new TypeProjet().getById(type_id, con);
                fanamboarana.setTypeProjet(typeProjet);
                fanamboarana.setEtat(rs.getDouble("etat"));
                fanamboarana_list[index] = fanamboarana;
                index++;
            }
        } catch (Exception e) {
            throw e;
        }
        return fanamboarana_list;
    }
}
