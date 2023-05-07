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

    Connect(String jdbcmysqllocalhost3307dbmovieseries, String root, String d3rp1ng1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
                sql="insert into tblmovieseries values('"+movieseries.getTitle()+"',"+movieseries.getEpisodes()+",'"+movieseries.getType()+"' ,"+movieseries.getWatchStatus()+", '"+movieseries.getGenre()+"', '"+movieseries.getSynopsis()+"')";
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
                sql="insert into tblfavorites values('"+favorite.getTitle()+"','"+favorite.getType()+"',"+favorite.getEpisodes()+" ,'"+favorite.getType()+"')";
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
    
    
    
    public ArrayList<MovieSeries> searchMovieSeries(String str){
        ArrayList<MovieSeries> mvs = new ArrayList<>();
        Statement stmt;
        String sql=null;
        ResultSet rs=null;
        try {
            stmt = conn.createStatement();
            sql="select * from tblmovieseries where title='%"+str+"%' or genre='%"+str+"%'";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                MovieSeries movieseries = new MovieSeries(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(5), rs.getString(6));
                mvs.add(movieseries);
                System.out.print("Added");
            }
            return mvs;
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
    
    public int login(String username, String password){
        Statement stmt;
        String sql;
        ResultSet rs;
        try {
            stmt=conn.createStatement();
            sql ="select * from tbluser where username='"+username+"' and password='"+password+"'";
            rs = stmt.executeQuery(sql);
            if (rs.next()==true)
                return 1;
            else
                return 0;
        } catch (SQLException ex) {
            // Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public boolean register(User user){
        Statement stmt;
        String sql;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            sql="select * from tbluser where username='"+user.getUsername()+"'";
            rs =stmt.executeQuery(sql);
            if(rs.next()==false){
                sql="insert into tbluser values('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getFirstname()+"','"+user.getLastname()+"')";
                stmt.executeUpdate(sql);
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
}
