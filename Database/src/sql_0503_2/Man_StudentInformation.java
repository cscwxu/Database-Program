package sql_0503_2;
//学生信息管理面板
import javax.swing.*;

import javax.swing.table.DefaultTableModel;



import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;

import java.awt.event.ItemListener;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Vector;



public class Man_StudentInformation extends Panel implements ActionListener {

	// 定义组件

	JLabel jLStudentInfoTable = null;//学生信息表

	JLabel jLSelectQueryField = null;//选择查询字段

	JLabel jLEqual = null;//=

	JLabel jLSNo = null;//学号

	JLabel jLSName = null;//姓名

	JLabel jLclass = null;//班级
	
	JLabel jLDn = null;//系别号

	JLabel jLSsex = null;//性别

	JLabel jLSage = null;//年龄



	JTextField jTFQueryField = null;//查询字段

	JTextField jTFSNo = null;//学号

	JTextField jTFSName = null;//姓名

	JTextField jTclass = null;//班级
	
	JTextField jTDn = null;//系别号

	JTextField jTSsex = null;//性别

	JTextField jTSage = null;//年龄

	

	//定义界面上的button

	JButton jBQuery = null;//查询

	JButton jBQueryAll = null;//查询所有记录

	JButton jBInsert = null;//插入

	JButton jBUpdate = null;//更新

	JButton jBDeleteCurrentRecord = null;//删除当前记录

	JButton jBDeleteAllRecords = null;//删除所有记录

	JButton jBCancle=null;  

	//JComboBox jCBSelectQueryField = null;

	//下拉框

	JComboBox<String> jCBSelectQueryField = null;//查询字段

	JPanel jP1, jP2,jP3,jP4,jP5 = null;

	JPanel jPTop, jPBottom = null;

	DefaultTableModel studentTableModel = null;

	JTable studentJTable = null;

	JScrollPane studentJScrollPane = null;

	Vector studentVector = null;

	Vector titleVector = null;

	

	private static DbProcess dbProcess;

	String SelectQueryFieldStr = "学号";

	
	private void SetEnable(boolean flag) {
		if(flag) {
			jTFSNo.setText("");
			
			jTFSName.setText("");
			
			jTclass.setText("");
			
			jTDn.setText("");
			
			jTSsex.setText("");
			
			jTSage.setText("");
			
			jBInsert.setEnabled(true);
			
			jBDeleteCurrentRecord.setEnabled(false);
			
			jBUpdate.setEnabled(false);
			
			jBCancle.setEnabled(false);
		}
		else {
			jBInsert.setEnabled(false);
			
			jBDeleteCurrentRecord.setEnabled(true);
			
			
			jBUpdate.setEnabled(true);
			
			jBCancle.setEnabled(true);
		}
	}
	// 构造函数

	public Man_StudentInformation() {

		// 创建组件	

		jLStudentInfoTable = new JLabel("学生表");

		jLSelectQueryField = new JLabel("选择查询字段");

		jLEqual = new JLabel(" = ");

		jLSNo = new JLabel("学号");

		jLSName = new JLabel("姓名");

		jLclass = new JLabel("班级");
		
		jLDn = new JLabel("系别");;//系别号

		jLSsex = new JLabel("性别");;//性别

		jLSage = new JLabel("年龄");;//年龄

		

		jTFQueryField = new JTextField(10);//查询字段

		jTFSNo = new JTextField(10);//学号

		jTFSName = new JTextField(10);//姓名

		jTclass = new JTextField(10);//性别
		
		jTDn = new JTextField(10);//系别号

		jTSsex = new JTextField(10);//性别

		jTSage = new JTextField(10);//年龄

		

		jBQuery = new JButton("查询学生信息");

		jBQueryAll = new JButton("查询所有记录");

		jBInsert = new JButton("添加学生信息");

		jBUpdate = new JButton("修改学生信息");

		jBDeleteCurrentRecord = new JButton("删除当前记录");

		jBDeleteAllRecords = new JButton("删除所有记录");
		
		jBCancle=new JButton("取消");

		// 设置监听

		jBQuery.addActionListener(this);

		jBQueryAll.addActionListener(this);

		jBInsert.addActionListener(this);

		jBUpdate.addActionListener(this);

		jBDeleteCurrentRecord.addActionListener(this);

		jBDeleteAllRecords.addActionListener(this);

		jBCancle.addActionListener(this);
		
		SetEnable(true);

		jCBSelectQueryField = new JComboBox<String>();//查询字段

		jCBSelectQueryField.addItem("学号");  

		jCBSelectQueryField.addItem("姓名");  

		jCBSelectQueryField.addItem("班级");
		
		jCBSelectQueryField.addItem("系别");  

		jCBSelectQueryField.addItem("性别");  

		jCBSelectQueryField.addItem("年龄");

		jCBSelectQueryField.addItemListener(new ItemListener() {//下拉框事件监听  

            public void itemStateChanged(ItemEvent event) {  

                switch (event.getStateChange()) {  

                case ItemEvent.SELECTED:  

                	SelectQueryFieldStr = (String) event.getItem();  

                    System.out.println("选中：" + SelectQueryFieldStr);  

                    break;  

                case ItemEvent.DESELECTED:  

                    System.out.println("取消选中：" + event.getItem());  

                    break;  

                }  

            }  

        });

	

		studentVector = new Vector();

		titleVector = new Vector();

		

		// 定义表头

		titleVector.add("学号");

		titleVector.add("姓名");

		titleVector.add("班级");
		
		titleVector.add("系别");  

		titleVector.add("性别");  

		titleVector.add("年龄");

		//studentTableModel = new DefaultTableModel(tableTitle, 15);

		studentJTable = new JTable(studentVector, titleVector);

		studentJTable.setPreferredScrollableViewportSize(new Dimension(600,260));

		studentJScrollPane = new JScrollPane(studentJTable);

		//分别设置水平和垂直滚动条自动出现

		studentJScrollPane.setHorizontalScrollBarPolicy(                

                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		studentJScrollPane.setVerticalScrollBarPolicy(                

                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		

		//为表格添加监听器 

		studentJTable.addMouseListener(new MouseAdapter()

		{ 

			public void mouseClicked(MouseEvent e) 

			{ 

				int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置

				System.out.println("mouseClicked(). row = " + row);

				Vector v = new Vector();

				v = (Vector) studentVector.get(row);



				jTFSNo.setText((String) v.get(0));// 学号

				jTFSName.setText((String) v.get(1));// 姓名

				jTclass.setText((String) v.get(2));// 班级
				
				jTDn.setText((String) v.get(3));//系别号

				jTSsex.setText((String) v.get(4));//性别

				jTSage.setText((String) v.get(5));//年龄
				
				SetEnable(false);

			}

		});





		jP1 = new JPanel();

		jP2 = new JPanel();

		jP5 = new JPanel();

		jP3 = new JPanel();

		jP4 = new JPanel();

		jPTop = new JPanel();

		jPBottom = new JPanel();

		

		jP1.add(jLStudentInfoTable,BorderLayout.NORTH);

		jP2.add(studentJScrollPane);

		

		

		jP3.add(jLSelectQueryField);    //选择查询字段

		jP3.add(jCBSelectQueryField);   //查询字段

		jP3.add(jLEqual);  //=

		jP3.add(jTFQueryField);	

		jP3.add(jBQuery);

		jP3.add(jBQueryAll);

		jP3.setLayout(new FlowLayout(FlowLayout.CENTER));

		jP3.setPreferredSize(new Dimension(20,20));

		

		jP4.add(jLSNo);
		jP4.add(jTFSNo);

		jP4.add(jLSName);
		jP4.add(jTFSName);

		jP4.add(jLclass);
		jP4.add(jTclass);
		
		jP4.add(jLDn);//系别号
		jP4.add(jTDn);
		
		jP4.add(jLSsex);//性别
		jP4.add(jTSsex);

		jP4.add(jLSage);//年龄
		jP4.add(jTSage);

		jP4.setLayout(new FlowLayout(FlowLayout.CENTER));

		jP4.setPreferredSize(new Dimension(30,30));

		

		

		jP5.add(jBInsert);

		jP5.add(jBUpdate);

		jP5.add(jBDeleteCurrentRecord);

		jP5.add(jBDeleteAllRecords);
		
		jP5.add(jBCancle);

		jP5.setLayout(new FlowLayout(FlowLayout.CENTER));

		jP5.setPreferredSize(new Dimension(20,20));

		

		jPTop.add(jP1);

		jPTop.add(jP2);

		

		jPBottom.setLayout(new GridLayout(3, 1));

		jPBottom.add(jP3);

		jPBottom.add(jP4);

		jPBottom.add(jP5);

		this.add("North", jPTop);

		this.add("South", jPBottom);

	

		

		this.setLayout(new GridLayout(2, 1));

		//this.setTitle("学生信息操作");

		this.setSize(500, 500);

		//this.setLocation(150, 150);

		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

		//this.setResizable(false);

		

		

		dbProcess = new DbProcess();

	}



	@Override

	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("查询学生信息")  

				&& !jTFQueryField.getText().isEmpty()){

				System.out.println("actionPerformed(). 查询学生信息");

				String sQueryField = jTFQueryField.getText().trim();

				queryProcess(sQueryField);

				jTFQueryField.setText("");

			}else if(e.getActionCommand().equals("查询所有记录")) {

				System.out.println("actionPerformed(). 查询所有记录");

				queryAllProcess();

			}else if(e.getActionCommand().equals("添加学生信息")

					&& !jTFSNo.getText().isEmpty()

					&& !jTFSName.getText().isEmpty()

					&& !jTclass.getText().isEmpty()
					&& !jTDn.getText().isEmpty()
					&& !jTSsex.getText().isEmpty()
					&& !jTSage.getText().isEmpty()){

				System.out.println("actionPerformed(). 添加学生信息");

				insertProcess();

			}else if(e.getActionCommand().equals("修改学生信息")

					&& !jTFSNo.getText().isEmpty()

					&& !jTFSName.getText().isEmpty()

					&& !jTclass.getText().isEmpty()
					&& !jTDn.getText().isEmpty()
					&& !jTSsex.getText().isEmpty()
					&& !jTSage.getText().isEmpty()){

				System.out.println("actionPerformed(). 修改学生信息");

				updateProcess();
				
				SetEnable(true);

			}else if(e.getActionCommand().equals("删除当前记录")){

				System.out.println("actionPerformed(). 删除当前记录");

				deleteCurrentRecordProcess();
				SetEnable(true);

			}else if(e.getActionCommand().equals("取消")){

				System.out.println("actionPerformed(). 取消");

				SetEnable(true);

			}

		}

	

	/*

	public static void main(String[] args) {

		DatabaseCourseDesign getcon = new  DatabaseCourseDesign();

    }

    */

	

	public void queryProcess(String sQueryField)

	{

		try{

			// 建立查询条件

			String sql = "select * from student where ";

			String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);

		

			if(queryFieldStr.equals("Sn")){//int sAge.

				sql = sql + queryFieldStr;

				sql = sql + " = " + sQueryField;

			}else{

				sql = sql + queryFieldStr;

				sql = sql + " = ";

				sql = sql + "'" + sQueryField + "';";

			}

			

			System.out.println("queryProcess(). sql = " + sql);

	

			dbProcess.connect();

			ResultSet rs = dbProcess.executeQuery(sql);

	

			// 将查询获得的记录数据，转换成适合生成JTable的数据形式

			studentVector.clear();

			while(rs.next()){

				Vector v = new Vector();

				v.add(rs.getString("Sn"));
				
				v.add(rs.getString("Sname"));
				
				v.add(rs.getString("Sclass"));
				
				v.add(rs.getString("Dn"));

				v.add(rs.getString("Ssex"));
				
				v.add(rs.getString("Sage"));

				studentVector.add(v);

			}

			

			studentJTable.updateUI();



			dbProcess.disconnect();

		}catch(SQLException sqle){

			System.out.println("sqle = " + sqle);

			JOptionPane.showMessageDialog(null,

				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);

		}catch(Exception e){

			System.out.println("e = " + e);

			JOptionPane.showMessageDialog(null,

				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);

		}

	}

	

	public void queryAllProcess()

	{

		try{

			// 建立查询条件

			String sql = "select * from student;";

			System.out.println("queryAllProcess(). sql = " + sql);

	

			dbProcess.connect();

			ResultSet rs = dbProcess.executeQuery(sql);



			// 将查询获得的记录数据，转换成适合生成JTable的数据形式

			studentVector.clear();

			while(rs.next()){

				Vector v = new Vector();

				v.add(rs.getString("Sn"));
				
				v.add(rs.getString("Sname"));
				
				v.add(rs.getString("Sclass"));
				
				v.add(rs.getString("Dn"));

				v.add(rs.getString("Ssex"));
				
				v.add(rs.getString("Sage"));
				
				//v.add(rs.getString("Spwd"));

				studentVector.add(v);

			}

			

			studentJTable.updateUI();



			dbProcess.disconnect();

		}catch(SQLException sqle){

			System.out.println("sqle = " + sqle);

			JOptionPane.showMessageDialog(null,

				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);

		}

	}

	

	public void insertProcess()

	{

		String id = jTFSNo.getText().trim();

		String name = jTFSName.getText().trim();

		String class1 = jTclass.getText().trim();
		
		String Dn = jTDn.getText().trim();

		String sex = jTSsex.getText().trim();

		String age = jTSage.getText().trim();

		// 建立插入条件

		String sql = "insert into student values('";

		sql = sql + id + "','";
		
		sql = sql + Dn + "','";

		sql = sql + name + "','";
		
		sql = sql + sex + "','";
		
		sql = sql + age + "','";
		
		sql = sql + class1 + "','";

		sql = sql + "88888888" + "');";

		



		System.out.println("insertProcess(). sql = " + sql);

		try{

			if (dbProcess.executeUpdate(sql) < 1) {

				System.out.println("insertProcess(). insert database failed.");

			}

		}catch(Exception e){

			System.out.println("e = " + e);

			JOptionPane.showMessageDialog(null,

				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);

		}

		queryAllProcess();

	}



	public void updateProcess()

	{

		String sNo = jTFSNo.getText().trim();

		String sName = jTFSName.getText().trim();

		String sClass = jTclass.getText().trim();
		
		String sDn = jTDn.getText().trim();

		String ssex = jTSsex.getText().trim();

		String sage = jTSage.getText().trim();

		// 建立更新条件

		String sql = "update student set Sname = '";

		sql = sql + sName + "', Dn = '";
		
		sql = sql + sDn + "', Ssex = '";
		
		sql = sql + ssex + "', Sage = '";
		
		sql = sql + sage + "', Sclass = '";

		sql = sql + sClass + "'";

		sql = sql +"WHERE Sn = '"+sNo + "';";

		System.out.println("updateProcess(). sql = " + sql);

		try{

			if (dbProcess.executeUpdate(sql) < 1) {

				System.out.println("updateProcess(). update database failed.");

			}

		}catch(Exception e){

			System.out.println("e = " + e);

			JOptionPane.showMessageDialog(null,

				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);

		}

		queryAllProcess();
	}



	public void deleteCurrentRecordProcess()
	{
		String sNo = jTFSNo.getText().trim();
		//删除学生信息，首先要删除所有依赖关系
		//1.删除该同学选修的课程
		
		String sql = "delete from student where Sn = '" + sNo + "';";

		String rsql="delete from sc where Sn= '"+sNo+"';";
		
		System.out.println("delete reference Information. sql = " + sql);
		
		try{

			if (dbProcess.executeUpdate(rsql) < 1) {

				System.out.println("deleteCurrentRecordProcess(). delete database failed.");

			}

		}catch(Exception e){

			System.out.println("e = " + e);

			JOptionPane.showMessageDialog(null,

				"删除数据依赖错误","错误",JOptionPane.ERROR_MESSAGE);

		}
		
		System.out.println("deleteCurrentRecordProcess(). sql = " + sql);

		try{

			if (dbProcess.executeUpdate(sql) < 1) {

				System.out.println("deleteCurrentRecordProcess(). delete database failed.");

			}

		}catch(Exception e){

			System.out.println("e = " + e);

			JOptionPane.showMessageDialog(null,

				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);

		}

		queryAllProcess();

	}



	public void deleteAllRecordsProcess()

	{

		// 建立删除条件
		
		String sql = "delete from student;";
		
		String rsql="delete from sc;";
		
		System.out.println("delete reference Information. sql = " + sql);
		
		try{

			if (dbProcess.executeUpdate(rsql) < 1) {

				System.out.println("deleteCurrentRecordProcess(). delete database failed.");

			}

		}catch(Exception e){

			System.out.println("e = " + e);

			JOptionPane.showMessageDialog(null,

				"删除数据依赖错误","错误",JOptionPane.ERROR_MESSAGE);

		}

		System.out.println("deleteAllRecordsProcess(). sql = " + sql);

		try{

			if (dbProcess.executeUpdate(sql) < 1) {

				System.out.println("deleteAllRecordsProcess(). delete database failed.");

			}

		}catch(Exception e){

			System.out.println("e = " + e);

			JOptionPane.showMessageDialog(null,

				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);

		}

		queryAllProcess();

	}

	

	public String jCBSelectQueryFieldTransfer(String InputStr)

	{

		String outputStr = "";

		System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);

		

		if(InputStr.equals("学号")){

			outputStr = "Sn";

		}else if(InputStr.equals("姓名")){

			outputStr = "Sname";

		}else if(InputStr.equals("班级")){

			outputStr = "Sclass";

		}else if(InputStr.equals("系别")){

			outputStr = "Dn";

		}else if(InputStr.equals("性别")){

			outputStr = "Ssex";

		}else if(InputStr.equals("年龄")){

			outputStr = "Sage";

		}else if(InputStr.equals("密码")){

			outputStr = "Spwd";

		}

		System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);

		return outputStr;

	}

}


