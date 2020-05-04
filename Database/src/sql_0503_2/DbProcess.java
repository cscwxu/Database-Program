package sql_0503_2;

import java.sql.*;

import java.util.ArrayList;

import java.util.List;





public class DbProcess{

	Connection connection = null;

	ResultSet rs = null;



	//mysql���ݿ�url

	String userMySql="root"; 

	String passwordMySql="qwe6555013";

	String urlMySql = "jdbc:mysql://localhost:3306/biglab?user="

			+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&serverTimezone=UTC";

	public DbProcess() {

		try {

			//mysql���ݿ�����������������

			Class.forName("com.mysql.cj.jdbc.Driver"); 

			System.out.println("mysql���ݿ��������سɹ�");

		}

		catch(java.lang.ClassNotFoundException e) {

			e.printStackTrace();

		}

	}





	public void connect(){

		try{
			//���ݿ����ᴮ
			String url = "jdbc:mysql://localhost:3306/biglab?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
		    String username = "root";
		    String password = "qwe6555013";
		    //��������
		    connection=(Connection) DriverManager.getConnection(url, username, password);
		    
		    if(connection!=null){

	            System.out.println("���ݿ����ӳɹ�");

	        }
		    
		}catch(Exception e){
			System.out.println("���ݿ�����ʧ�ܣ�" + e);
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

			// ִ�в�ѯ

			rs = pstm.executeQuery();

		} 

		catch(SQLException ex) { 

			ex.printStackTrace();

		}

		return rs;

	}

	

	//����

	//executeUpdate �ķ���ֵ��һ��������ָʾ��Ӱ��������������¼�������

	//executeUpdate����ִ�� INSERT��UPDATE �� DELETE ���

	//�Լ� SQL DDL�����ݶ������ԣ���䣬���� CREATE TABLE �� DROP TABLE��

	

	//ִ������ɾ�������ķ���

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
