/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * 
 */
public class Connect {
    
    Connection conn=null;
    public Connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunderforgesys","root","");
            //JOptionPane.showMessageDialog(null, "Connected");
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
    
    public boolean favorite(Favorite favorite) {
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
    
    public boolean showFavs(){
        Statement stmt = null;
        String sql=null;
        ResultSet rs=null;
        
        try {
            sql ="SELECT * FROM tblfavorites";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String type = rs.getString("episodes");
                String episodes = String.valueOf(rs.getInt("type"));
                String tbData[] = {title, genre, episodes, type};
                DefaultTableModel tblModel = null;
                tblModel.addRow(tbData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
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
            sql = "select * from tblmovieseries where title like '%"+str+"%' or genre like '%"+str+"%'";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                MovieSeries movieseries = new MovieSeries(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(5), rs.getString(6));
                mvs.add(movieseries);
            }
            return mvs;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
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
            stmt = conn.createStatement();
            sql = "SELECT * FROM tbluser WHERE binary username='" + username + "'";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String storedPassword = rs.getString("password");

                if (password.equals(storedPassword)) {
                    return 1; // Username exists and password matches
                } else {
                    return 2; // Username exists but password doesn't match
                }
            } else {
                return 3; // Username doesn't exist
            }
        } catch (SQLException ex) {
            // Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public boolean register(User user) throws Exception{
        Statement stmt;
        String sql;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            sql="select * from tbluser where binary username='"+user.getUsername()+"'";
            rs =stmt.executeQuery(sql);
            if(rs.next()==false){
                sql="insert into tbluser(username, password, firstname, lastname) values('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getFirstname()+"','"+user.getLastname()+"')";
                stmt.executeUpdate(sql);
                conn.close();
                return true;
            } else {
                throw new Exception("Username already exists.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void logout() {
        try {
            conn.close();
            JOptionPane.showMessageDialog(null, "Logged out");
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String userProfName(String username){
        Statement stmt;
        String sql;
        ResultSet rs;
        
        String firstName, lastName;
        try {
            
            stmt=conn.createStatement();
            sql = "select * from tbluser where binary username='" + username + "'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                firstName = rs.getString("firstname");
                lastName = rs.getString("lastname");
                return firstName+ " " + lastName;
            }
           
               
        } catch (SQLException ex) {
            // Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
       return username;
    }
}
