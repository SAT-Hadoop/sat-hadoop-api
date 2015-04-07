/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.doa;

import edu.iit.model.User;
import edu.iit.model.User_Jobs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author supramo
 */
public class DOA {

    private Connection connect = null;
    private PreparedStatement preparedStatement = null;

    /**
     *
     * @return 
     */
    public Connection makeConnection() {
        // this will load the MySQL driver, each DB has its own driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // setup the connection with the DB.
            return DriverManager
                    .getConnection(""
                            + "jdbc:mysql://"
                            +"64.131.111.18/itmd544?"
                            + "user=root&password=root"
                    );
            
            
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Could not make connection");
            System.exit(1);
        }
        
        return null;
    }

    public User_Jobs getUserJob(String jobid){
    try {
            connect = makeConnection();
            preparedStatement = connect
                    .prepareStatement("select * from user_jobs where jobid=?");
            preparedStatement.setString(1, jobid);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            User_Jobs job1 = new User_Jobs();
            job1.setUserid(rs.getString("userid"));
            job1.setInputurl(rs.getString("inputurl"));
            job1.setOutputurl(rs.getString("outputurl"));
            job1.setNodes(rs.getString("nodes"));
            job1.setJobid(rs.getString("jobid"));
            job1.setJobname(rs.getString("jobname"));
            rs.close();
            preparedStatement.close();
            connect.close();
            return job1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fetch failed");
        }
        return new User_Jobs();
    }
    
    /**
     *
     */
    public void createTables() {
        try {
            connect = makeConnection();
            preparedStatement = connect
                    .prepareStatement("CREATE TABLE IF NOT EXISTS user_jobs ("
                            + "userid varchar(30),"
                            + "jobid varchar(255) PRIMARY KEY,"
                            + "jobstatus varchar(100),"
                            + "inputurl varchar(200),"
                            + "outputurl varchar(200),"
                            + "nodes varchar(10),"
                            + "jobname varchar(100)"
                            + ")");

            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = connect
                    .prepareStatement("CREATE TABLE IF NOT EXISTS ec2_queue ("
                            + "ec2ip varchar(255),"
                            + "queuename varchar(255),"
                            + "type varchar(255)"
                            + ")");

            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = connect
                    .prepareStatement("CREATE TABLE IF NOT EXISTS hadoop_slaves ("
                            + "ec2ip varchar(255),"
                            + "status varchar(255)"
                            + ")");

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connect.close();
        } catch (Exception e) {
            
            e.printStackTrace();
            //System.out.println("Table could not be created");
            //System.exit(1);
        }

    }

    /**
     *
     * @param ec2
     * @param queue
     */
    public void addEc2Queue(String ec2, String queue) {
        try {
            connect = makeConnection();
            preparedStatement = connect
                    .prepareStatement("insert into ec2_queue values (?,?,?)");
            preparedStatement.setString(1, ec2);
            preparedStatement.setString(2, queue);
            preparedStatement.setString(3,"send");

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Addition failed");
        }
    }

    /**
     *
     * @param ec2
     * @param queue
     */
    public void updateEc2Queue(String ec2, String queue) {
        try {
            connect = makeConnection();
            preparedStatement = connect
                    .prepareStatement("update ec2_queue set ec2ip = ? where queuename = ?");
            preparedStatement.setString(1, ec2);
            preparedStatement.setString(2, queue);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("update failed");
        }
    }

    /**
     *
     * @param user
     */
    public void addUser(User user) {
        try {
            connect = makeConnection();
            preparedStatement = connect
                    .prepareStatement("insert into user values (?,?,?,?)");
            preparedStatement.setString(1, user.getUserid());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmailid());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement.close();
            connect.close();
        } catch (Exception e) {
            System.out.println("Could not add job to the database");
        }
    }

    /**
     *
     * @param userjob
     */
    public void addJob(User_Jobs userjob) {
        try {
            connect = makeConnection();
            System.out.println(userjob.toString());
            preparedStatement = connect
                    .prepareStatement("insert into user_jobs values (?,?,?,?,?,?,?);");
            preparedStatement.setString(1, userjob.getUserid());
            preparedStatement.setString(2, userjob.getJobid());
            preparedStatement.setString(3, userjob.getJobstatus());
            preparedStatement.setString(4, userjob.getInputurl());
            preparedStatement.setString(5, userjob.getOutputurl());
            preparedStatement.setString(6, userjob.getNodes());
            preparedStatement.setString(7, userjob.getJobname());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connect.close();
        } catch (Exception e) {
            System.out.println("There was an error buddy");
            e.printStackTrace();
        }
    }
    public int getJobs(String userid){
        int result = 0;
        try {
            connect = makeConnection();
            preparedStatement = connect
                    .prepareStatement("select * from user_jobs "
                            + " where userid=?");
            preparedStatement.setString(1,userid);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                result++;
            }
            rs.close();
            preparedStatement.close();
            connect.close();
        }
        catch(Exception e){
            System.out.println("There was a problem with updating the instance status");
        }
        
        return 0;
    }

    /**
     *
     * @param userjob
     */
    public void updateJob(User_Jobs userjob) {
        try {
            connect = makeConnection();
            preparedStatement = connect
                    .prepareStatement("update user_jobs set jobstatus=?,inputurl=?,outputurl=? where"
                            + " userid=? and jobid=? and nodes=? and jobname=?" );
            preparedStatement.setString(4, userjob.getUserid());
            preparedStatement.setString(5, userjob.getJobid());
            preparedStatement.setString(1, userjob.getJobstatus());
            preparedStatement.setString(2, userjob.getInputurl());
            preparedStatement.setString(3, userjob.getOutputurl());
            preparedStatement.setString(6, userjob.getNodes());
            preparedStatement.setString(7, userjob.getJobname());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param userid
     * @return
     */
    public User getUser(String userid){
        User usr = new User();
        try {
            connect = makeConnection();
            preparedStatement = connect
                    .prepareStatement("select * from user where"
                            + " userid=?");

            preparedStatement.setString(1, userid);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                usr.setUserid(userid);
                usr.setEmailid(rs.getString("emailid"));
                usr.setPhonenumber(rs.getString("phonenumber"));
                usr.setPassword(rs.getString("password"));
            }
            rs.close();
            preparedStatement.close();
            connect.close();
        } catch (Exception e) {
            System.out.println("Could not update the job");
        }
        return usr;
    }
    
    /**
     *
     * @param ec2ip
     * @return
     */
    public String getEc2Queue(String ec2ip){
        String queuename = "";
        try{
            connect = makeConnection();
            preparedStatement = connect
                    .prepareStatement("select * from ec2_queue where"
                            + " ec2ip=?");
            preparedStatement.setString(1,ec2ip);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            queuename = rs.getString(2);
            rs.close();
            preparedStatement.close();
            connect.close();
        }
        catch(Exception e){
            System.out.println("sai is aesome");
        }
        return queuename;
    }
    
    /**
     *
     * @return
     */
    public String getEc2SendQueue(){
        String queuename = "";
        
        try{
            connect = makeConnection();
            preparedStatement = connect
                    .prepareStatement("select * from ec2_queue where "
                            + "ec2ip='' and type = 'send'");
            ResultSet rs = preparedStatement.executeQuery();
            rs.next(); 
            queuename = rs.getString("queuename");
            rs.close();
            preparedStatement.close();
            connect.close();
        }
        catch(Exception e){
            System.out.println("sai is aesome");
        }
        return queuename;
    }
    
    public List getSlaves(int n){
        List listOfSlaves = new ArrayList();
        try{
            connect = makeConnection();
            preparedStatement = connect
                    .prepareStatement("select * from hadoop_slaves where "
                            + "status= 'a'");
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("Query is here at 1");
            int count = 1;
            while (rs.next() && count < n){
                System.out.println("The ip is "+rs.getString("ec2ip"));
                listOfSlaves.add(rs.getString("ec2ip"));
                count++;
            }
            System.out.println("Query is here at 2");
            if (listOfSlaves.size() == n){
                for (int i=0;i<listOfSlaves.size();i++){
                    updateSlave((String)listOfSlaves.get(i),"n");
                }
            }
            rs.close();
            preparedStatement.close();
            connect.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Issue executing the query sai");
        }
        
        return listOfSlaves;
    }
    
    public void updateSlave(String ec2ip,String status){
        try {
            connect = makeConnection();
            preparedStatement = connect
                    .prepareStatement("update hadoop_slaves set status=?"
                            + " where ec2ip=?");
            preparedStatement.setString(2,ec2ip);
            preparedStatement.setString(1,status);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connect.close();
        }
        catch(Exception e){
            System.out.println("There was a problem with updating the instance status");
        }
    }
    
    
}
