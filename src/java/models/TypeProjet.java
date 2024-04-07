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
public class TypeProjet {
    private String id;
    private String nom;
    private double prix;

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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public TypeProjet() {
    }
    public TypeProjet getById(String id,Connection con)throws Exception{
        String sql = "select * from type_projet where id=?";
        TypeProjet typeProjet = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                typeProjet = new TypeProjet();
                typeProjet.setId(id);
                typeProjet.setNom(rs.getString("nom"));
                typeProjet.setPrix(rs.getDouble("prix"));
            }
        }catch (Exception e) {
            throw new Exception("Erreur lors de la récupération du route : " + e.getMessage());
        }
        return typeProjet;
    }
    public static TypeProjet[] getAll(Connection con)throws Exception{
        String sql = "select * from type_projet";
        TypeProjet[] listTypeProjets = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            listTypeProjets = new TypeProjet[rowCount];
            rs = stmt.executeQuery();
            int index = 0;
            while(rs.next()){
                TypeProjet typeProjet = new TypeProjet();
                typeProjet.setId(rs.getString("id"));
                typeProjet.setNom(rs.getString("nom"));
                typeProjet.setPrix(rs.getDouble("prix"));
                listTypeProjets[index] = typeProjet;
                index++;
            }
        } catch (Exception e) {
            throw e;
        }
        return listTypeProjets;
    }
}
