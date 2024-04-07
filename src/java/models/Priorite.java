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
public class Priorite {
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

    public Priorite() {
    }
    public static Priorite[] getAll(Connection con)throws Exception{
        String sql = "select * from priorite";
        Priorite[] priorites = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            priorites = new Priorite[rowCount];
            rs = stmt.executeQuery();
            int index = 0;
            while(rs.next()){
                Priorite priorite = new Priorite();
                priorite.setId(rs.getString("id"));
                priorite.setNom(rs.getString("nom"));
                priorites[index] = priorite;
                index++;
            }
        } catch (Exception e) {
            throw e;
        }
        return priorites;
    }
    public Priorite getById(String id,Connection con)throws Exception{
        String sql = "select * from priorite where id=?";
        Priorite priorite = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                priorite = new Priorite();
                priorite.setId(id);
                priorite.setNom(rs.getString("nom"));
            }
        }catch (Exception e) {
            throw new Exception("Erreur lors de la récupération du priorite : " + e.getMessage());
        }
        return priorite;
    }
}
