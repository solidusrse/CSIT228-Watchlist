/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * 
 */
public class Connect {
    
    Connection conn=null;
    public Connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/dbmovieseries","root","d3rp1ng1");
            JOptionPane.showMessageDialog(null, "Connected");
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean addMovieSeries(MovieSeries movieseries) {
        Statement stmt;
        String sql=null;
        ResultSet rs=null;
        try {
            stmt = conn.createStatement();
            sql="select * from tblmovieseries where title='"+movieseries.getTitle()+"'";
            rs = stmt.executeQuery(sql);
            if(rs.next()==false){
                sql="insert into tblmovieseries values('"+movieseries.getTitle()+"',"+movieseries.getEpisodes()+",'"+movieseries.getType()+"' ,"+movieseries.getWatchStatus()+")";
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Movie successfully added!");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public boolean favorite(MovieSeries favorite) {
        Statement stmt;
        String sql=null;
        ResultSet rs=null;
        try {
            stmt = conn.createStatement();
            sql="select * from tblfavorites where title='"+favorite.getTitle()+"'";
            rs = stmt.executeQuery(sql);
            if(rs.next()==false){
                sql="insert into tblfavorites values('"+favorite.getTitle()+"','"+favorite.getGenre()+"',"+favorite.getEpisodes()+" ,'"+favorite.getType()+"')";
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Successfully Added!");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    
    
    MovieSeries searchMovieSeries(String title){
        Statement stmt;
        String sql=null;
        ResultSet rs=null;
        try {
            stmt = conn.createStatement();
            sql="select * from tblmovieseries where title='"+title+"'";
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                return new MovieSeries(rs.getString(1), rs.getInt(2), rs.getString(3));
            }else
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public boolean updateWatchStatus(MovieSeries ms){
        Statement stmt;
        String sql;
        try {
            stmt = conn.createStatement();
            sql = "UPDATE tblmovieseries SET watchstatus = "+ms.getWatchStatus()+" WHERE title = '"+ms.getTitle()+"'";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }    
}
