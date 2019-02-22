import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

public class Connect {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		String username="root";
		String password ="cubensis";
		String url ="jdbc:mysql://localhost:3306/test";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,username,password);
		System.out.println("connected");
		Statement statement=con.createStatement();
		
		
        /*statement.executeUpdate("INSERT INTO books(name) values('inferno')");
        statement.executeUpdate("insert into books set name='solomon key'");*/
        
       /* ResultSet rs=statement.executeQuery("select * from books");
        while(rs.next()) {
        	System.out.print(rs.getInt(1)+"\t");
        	System.out.print(rs.getString(2));
        	System.out.println();
        }*/
		statement.executeUpdate("INSERT INTO USERS(name,password)values('ar','cub')");
		statement.executeUpdate("INSERT INTO USERS(name,password)values('ka','ket')");
        String userId="1' or 1='1";
      /*  ResultSet rs=statement.executeQuery("select *from users where id='"+userId+"'");
        while(rs.next()) {
        	System.out.println("username"+" "+rs.getString("name"));
        	System.out.println("password"+" "+rs.getString("password"));
        }*/
        PreparedStatement prstmt=con.prepareStatement("select *from users where id=?");
        prstmt.setString(1,userId);
        ResultSet rs=prstmt.executeQuery();
        while(rs.next()) {
        	System.out.println("username"+" "+rs.getString("name"));
        	System.out.println("password"+" "+rs.getString("password"));
        }
        BufferedImage image=ImageIO.read(new File("road.png"));
        Blob blob=con.createBlob();
        OutputStream out=blob.setBinaryStream(1);
        ImageIO.write(image, "png", out);
        PreparedStatement statement1 =con.prepareStatement("insert into users(name,password,img) values(?,?,?)");
        statement1.setString(1, "chu");
        statement1.setString(2, "chux");
        statement1.setBlob(3,blob);
        statement1.execute();
        Blob blob2=null;
        ResultSet rs1=statement.executeQuery("select * from users");
        while(rs1.next()){
        	 blob2=rs1.getBlob("img");
        }
     /*  BufferedImage image2=ImageIO.read(blob2.getBinaryStream());
       File output=new File("road2.png");
       ImageIO.write(image2, "png", output);*/
       
       PreparedStatement datum=con.prepareStatement("insert into books(name,dt) values('def',?)");
       datum.setDate(1, new Date( 	System.currentTimeMillis()));
       datum.execute();
       ResultSet rs3=
	}

}
