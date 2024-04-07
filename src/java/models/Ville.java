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
public class Ville {
    private String id;
    private String nom;

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

    public Ville() {
    }
    public Ville getById(String id,Connection con)throws Exception{
        String sql = "select * from ville where id=?";
        Ville ville = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ville = new Ville();
                ville.setId(id);
                ville.setNom(rs.getString("nom"));
            }
        }catch (Exception e) {
            throw new Exception("Erreur lors de la récupération du ville : " + e.getMessage());
        }
        return ville;
    }
}
