/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Sheer
 */
public class Connector {
    Connection conn=null;
    public Connector(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunderforgesys","root","");
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AddToWatchList(String name, String genre, int duration){
        Statement stmt;
        String sql =null;
        ResultSet rs=null;
        
        try {
            stmt = conn.createStatement();
            sql ="select * from tblwatchlist where title='"+name+"'";
            rs = stmt.executeQuery(sql);
            if(rs.next() == false){
                sql="insert into tblwatchlist(title,genre,duration) values('"+name+"','"+genre+"',duration)";
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"New record saved");
            }else{
                JOptionPane.showMessageDialog(null,"Movie already existing.");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void RemoveFromWatchList(String name){
        Statement stmt;
        String sql =null;
        ResultSet rs=null;
        
        try {
            stmt = conn.createStatement();
            sql ="delete from tblwatchlist where title='"+name+"'";
            stmt.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
