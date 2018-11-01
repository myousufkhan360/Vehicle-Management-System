/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicle.demo;

/**
 *
 * @author 2015_SE_092 / 2015_SE_101
 */
import java.sql.Connection;
import java.sql.*;

/**
 *
 * @author huma-pc
 */
public final class vh_db {
    
    protected String conn_url;
protected Connection db_conn;
protected PreparedStatement p_get;
protected PreparedStatement p_sav;
protected PreparedStatement p_ins;
 protected PreparedStatement p_jlist;

public vh_db() throws SQLException {conn_url = "jdbc:mysql://localhost:3306/vhdb";
connect_to_db();
prepare_stms();}


public void connect_to_db() throws SQLException {
db_conn = DriverManager.getConnection(conn_url,
"root","");
System.out.println("The connection is made with the database vhdb having table vdata");}

    

    public void prepare_stms() throws SQLException {
String s;
s = "select * from vdata where id = ?";
p_get = db_conn.prepareStatement(s);

s = "update vdata set vtype = ?, make = ? , doors = ? , wheels = ? , seats = ? , mspeed = ? , control = ?";
s += " " + " where id = ?";
p_sav = db_conn.prepareStatement(s);

s = "insert into vdata (id,vtype,make,doors,wheels,seats,mspeed,control) values(?,?,?,?,?,?,?,?)";
p_ins = db_conn.prepareStatement(s);
    
    s = "select id from vdata";
    p_jlist = db_conn.prepareStatement(s);
    
    }

    
    
 public ResultSet load_data(int x_id) throws SQLException {

p_get.setInt(1,x_id);
return p_get.executeQuery();
    }
    
    
    
    
public int db_save(Object x_id, Object x_vtype ,Object x_make , Object x_doors , Object x_wheels , Object x_seats , Object x_mspeed ,
        Object x_control) throws SQLException {
System.out.println("Saving (id ,vehicle type , brand, doors , wheels , seats , max speed , control) " + x_id + ", "+x_vtype + ", " + x_make
        + ", " +x_doors + ", " +x_wheels+ ", " +x_seats+ ", " +x_mspeed+ ", " +x_control);
//p_sav.setInt(1, (int) x_id);
p_sav.setInt(1, (int) x_vtype);
p_sav.setString(2, (String) x_make);
p_sav.setInt(3, (int) x_doors);
p_sav.setInt(4, (int) x_wheels);
p_sav.setInt(5, (int) x_seats);
p_sav.setInt(6, (int) x_mspeed);
p_sav.setBoolean(7, (boolean) x_control);
p_sav.setInt(8, (int) x_id);
return p_sav.executeUpdate();}




public int db_insert(Object x_id, Object x_vtype ,Object x_make , Object x_doors , Object x_wheels , Object x_seats , Object x_mspeed ,
        Object x_control) throws SQLException {
p_ins.setObject(1, x_id);
p_ins.setObject(2, x_vtype);
p_ins.setObject(3, x_make);
p_ins.setObject(4, x_doors);
p_ins.setObject(5, x_wheels);
p_ins.setObject(6, x_seats);
p_ins.setObject(7, x_mspeed);
p_ins.setObject(8, x_control);

return p_ins.executeUpdate();}


public ResultSet get_jlist( ) throws SQLException{
return  p_jlist.executeQuery();


}


}

    
    
    
    

