/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author supramo
 */
public class DOA {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public void makeConnection(){
      // this will load the MySQL driver, each DB has its own driver
        try{
            Class.forName("com.mysql.jdbc.Driver");
      // setup the connection with the DB.
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost/itmd544?"
              + "user=root&password=root");
        }
        catch (Exception e){
            System.out.println("Could not make connection");
            System.exit(1);
        }
      
    }
    
    public void createTables(){
        try{
            preparedStatement = connect
                    .prepareStatement("CREATE TABLE IF NOT EXISTS users ("
                            + "userid varchar(30) PRIMARY KEY,"
                            + "emailid varchar(50),"
                            + "phonenumber int,"
                            + "password varchar(30)"
                            + ")");
            preparedStatement.executeUpdate();
            
            preparedStatement = connect
          .prepareStatement("CREATE TABLE IF NOT EXISTS user_jobs ("
                    + "userid varchar(30),"
                    + "jobid varchar(255) PRIMARY KEY,"
                    + "jobstatus varchar(100),"
                    + "intputurl varchar(200),"
                    + "outputurl varchar(200),"
                  + "CONSTRAINT userid_key FOREIGN KEY (userid) REFERENCES users(userid)"
                  + ")");
            
            preparedStatement.executeUpdate();
            connect.close();
        }
        catch(Exception e){
            System.out.println("Table could not be created");
            System.exit(1);
        }
        
    }
    
    public void addJob(String userid,String jobid,String jobstatus,String inputurl,String outputurl){
        try{
            preparedStatement = connect
                    .prepareStatement("insert into user_jobs values (?,?,?,?,?)");
                     preparedStatement.setString(1, userid);
                     preparedStatement.setString(2, jobid);
                     preparedStatement.setString(3, jobstatus);
                     preparedStatement.setString(4, inputurl);
                     preparedStatement.setString(5, outputurl);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Could not add job to the database");
        }
    }
    
    public void updateJob(String userid,String jobid,String jobstatus,String inputurl,String outputurl){
        try{
            preparedStatement = connect
                    .prepareStatement("update user_jobs set jobstatus");
                     preparedStatement.setString(1, userid);
                     preparedStatement.setString(2, jobid);
                     preparedStatement.setString(3, jobstatus);
                     preparedStatement.setString(4, inputurl);
                     preparedStatement.setString(5, outputurl);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Could not update the job");
        }
    }
}
