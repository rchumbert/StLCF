package org.thompson.stlcf.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.thompson.stlcf.user.donor.Donor;
import org.thompson.stlcf.user.donor.Nonprofit;


public class JdbcConnection
	{
		
		public Connection connect() throws Exception
		{
			Connection con;
			try
			{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stlcf", "admin","admin");
			return con;
			}
			catch(Exception e)
			{
				System.out.println(e);
				return null;
			}
			
		
		}
		public void addUser(Donor donor, Connection con) throws SQLException
		{
			PreparedStatement addUser;
			String stmnt = "INSERT INTO users(lName, fName, email,zip,passwd) values(?,?,?,?, SHA(?))";
			addUser = con.prepareStatement(stmnt);
			addUser.setString(1, donor.getlName());
			addUser.setString(2, donor.getfName());
			addUser.setString(3, donor.getEmail());
			addUser.setInt(4, donor.getZip());
			addUser.setString(5, donor.getPasswd());
			addUser.executeUpdate();
		}
		public void addUser(Nonprofit np, Connection con) throws SQLException
		{
			PreparedStatement addUser;
			String stmnt = "INSERT INTO nonprof(name, password, email, state, city, street, zipcode, ein) values(?, sha(?), ?,?,?,?,?,?)";
			addUser= con.prepareStatement(stmnt);
			addUser.setString(1, np.getName());
			addUser.setString(2, np.getPass());
			addUser.setString(3, np.getEmail());
			addUser.setString(4, np.getState());
			addUser.setString(5, np.getCity());
			addUser.setString(6, np.getStreet());
			addUser.setInt(7, np.getZip());
			addUser.setInt(8, np.getEin());
			addUser.executeUpdate();
		}
		public String getEmail(String email, Connection con)
		{
			PreparedStatement emailCheck;
			String stmnt = "SELECT email FROM users WHERE email=?";
			try
				{
					emailCheck=con.prepareStatement(stmnt);
					emailCheck.setString(1, email);
					ResultSet result =emailCheck.executeQuery();
					String em=null;
					while(result.next())
						{
							em= result.getString("email");
						}
					return em;
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "Couldn't Connect to DB";
				}
		}
		public String getNpEmail(String email, Connection con)
			{
				PreparedStatement emailCheck;
				String stmnt = "SELECT email FROM nonprof WHERE email=?";
				try
					{
						emailCheck=con.prepareStatement(stmnt);
						emailCheck.setString(1, email);
						ResultSet result =emailCheck.executeQuery();
						String em=null;
						while(result.next())
							{
								em= result.getString("email");
							}
						return em;
					} catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
						return "Couldn't Connect to DB";
					}
			}
		public String getNpName(String name, Connection con) throws SQLException
		{
			PreparedStatement nameCheck;
			String stmnt = "SELECT name FROM nonprof WHERE name =?";
			nameCheck = con.prepareStatement(stmnt);
			nameCheck.setString(1, name);
			ResultSet result = nameCheck.executeQuery();
			String nm = null;
			while(result.next())
				{
					nm = result.getString("name");
				}
			return nm;
		}
	}