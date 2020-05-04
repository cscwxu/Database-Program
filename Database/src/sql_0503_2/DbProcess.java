package sql_0503_2;

import java.sql.*;

import java.util.ArrayList;

import java.util.List;





public class DbProcess{

	Connection connection = null;

	ResultSet rs = null;



	//mysql数据库url

	String userMySql="root"; 

	String passwordMySql="qwe6555013";

	String urlMySql = "jdbc:mysql://localhost:3306/biglab?user="

			+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&serverTimezone=UTC";

	public DbProcess() {

		try {

			//mysql数据库设置驱动程序类型

			Class.forName("com.mysql.cj.jdbc.Driver"); 

			System.out.println("mysql数据库驱动加载成功");

		}

		catch(java.lang.ClassNotFoundException e) {

			e.printStackTrace();

		}

	}





	public void connect(){

		try{
			//数据库连结串
			String url = "jdbc:mysql://localhost:3306/biglab?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
		    String username = "root";
		    String password = "qwe6555013";
		    //创建连结
		    connection=(Connection) DriverManager.getConnection(url, username, password);
		    
		    if(connection!=null){

	            System.out.println("数据库连接成功");

	        }
		    
		}catch(Exception e){
			System.out.println("数据库连结失败！" + e);
			e.printStackTrace();
		}
		
		

	}

	

	public void disconnect(){

		try{

			if(connection != null){

				connection.close();

				connection = null;

			}

		}

		catch(Exception e){

			e.printStackTrace();

		}

	}





	public ResultSet executeQuery(String sql) {

		try {

			System.out.println("executeQuery(). sql = " + sql);

			

			PreparedStatement pstm = connection.prepareStatement(sql);

			// 执行查询

			rs = pstm.executeQuery();

		} 

		catch(SQLException ex) { 

			ex.printStackTrace();

		}

		return rs;

	}

	

	//插入

	//executeUpdate 的返回值是一个整数，指示受影响的行数（即更新计数）。

	//executeUpdate用于执行 INSERT、UPDATE 或 DELETE 语句

	//以及 SQL DDL（数据定义语言）语句，例如 CREATE TABLE 和 DROP TABLE。

	

	//执行增、删、改语句的方法

	public int executeUpdate(String sql) {

		int count = 0;

		connect();

		try {

			Statement stmt = connection.createStatement();

			count = stmt.executeUpdate(sql);

		} 

		catch(SQLException ex) { 

			System.err.println(ex.getMessage());		

		}

		disconnect();

		return count;

	}

}
