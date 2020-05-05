package sql_0503_2;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class course extends Panel implements ActionListener {

	// 定义组件

		JLabel jLStudentInfoTable = null;//学生信息表

		JLabel jLSelectQueryField = null;//选择查询字段

		JLabel jLEqual = null;//=

		JLabel jLCn = null;//课号
		JLabel jLteacher = null;//教师号

		JLabel jLCname = null;//课名

		JLabel jLwe_day = null;//学时
		JLabel jLcredit = null;//学分

		

		JTextField jTCn = null;//查询字段

		JTextField jTteacher = null;//学号

		JTextField jTCname = null;//姓名

		JTextField jTwe_day = null;//班级
		JTextField jTcredit = null;//班级

		JTextField jTFQueryField=null;

		

		//定义界面上的button

		JButton jBQuery = null;//查询

		JButton jBQueryAll = null;//查询所有记录

		JButton jBInsert = null;//插入

		JButton jBUpdate = null;//更新

		JButton jBDeleteCurrentRecord = null;//删除当前记录

		JButton jBDeleteAllRecords = null;//删除所有记录

		

		//JComboBox jCBSelectQueryField = null;

		//下拉框

		JComboBox<String> jCBSelectQueryField = null;//查询字段

		JPanel jP1, jP2,jP3,jP4,jP5,jP6 = null;

		JPanel jPTop, jPBottom = null;

		DefaultTableModel studentTableModel = null;

		JTable studentJTable = null;

		JScrollPane studentJScrollPane = null;

		Vector studentVector = null;

		Vector titleVector = null;

		

		private static DbProcess dbProcess;

		String SelectQueryFieldStr = "教师";

		

		// 构造函数

		public course() {

			// 创建组件	

			jLStudentInfoTable = new JLabel("教师课程设置表");

			jLSelectQueryField = new JLabel("选择查询字段");

			jLEqual = new JLabel(" = ");

			jLCn = new JLabel("课程号");

			jLteacher = new JLabel("教师编号");

			jLCname = new JLabel("课程名");

			jLwe_day = new JLabel("学时");

			jLcredit = new JLabel("学分");


			


			jTCn = new JTextField(10);//查询字段

			jTteacher = new JTextField(10);//学号

			jTCname = new JTextField(10);//姓名

			jTwe_day = new JTextField(10);//性别

			jTcredit = new JTextField(10);//性别

			jTFQueryField=new JTextField(10);

			

			jBQuery = new JButton("查询");

			jBQueryAll = new JButton("查询所有记录");

			jBInsert = new JButton("插入");

			jBUpdate = new JButton("更新");

			jBDeleteCurrentRecord = new JButton("删除当前记录");

			jBDeleteAllRecords = new JButton("删除所有记录");

			// 设置监听

			jBQuery.addActionListener(this);

			jBQueryAll.addActionListener(this);

			jBInsert.addActionListener(this);

			jBUpdate.addActionListener(this);

			jBDeleteCurrentRecord.addActionListener(this);

			jBDeleteAllRecords.addActionListener(this);

			

			jCBSelectQueryField = new JComboBox<String>();//查询字段

			jCBSelectQueryField.addItem("教师");  

			jCBSelectQueryField.addItem("课程名");  

			jCBSelectQueryField.addItem("学分");

			jCBSelectQueryField.addItem("学时");

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
			titleVector.add("课程号");

			titleVector.add("教师编号");

			titleVector.add("教师名");

			titleVector.add("课程名");

			titleVector.add("学分");

			titleVector.add("学时");

			//studentTableModel = new DefaultTableModel(tableTitle, 15);

			studentJTable = new JTable(studentVector, titleVector);

			studentJTable.setPreferredScrollableViewportSize(new Dimension(600,200));

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



					jTCn.setText((String) v.get(0));// 学号

					jTteacher.setText((String) v.get(1));

					jTCname.setText((String) v.get(3));// 姓名

					jTwe_day.setText((String) v.get(4));// 班级

					jTcredit.setText((String) v.get(5));
					
					
					

				}

			});





			jP1 = new JPanel();

			jP2 = new JPanel();

			jP5 = new JPanel();

			jP3 = new JPanel();

			jP4 = new JPanel();

			jP5 = new JPanel();

			jP6 = new JPanel();

			

			jPTop = new JPanel();

			jPBottom = new JPanel();

			

			jP1.add(jLStudentInfoTable,BorderLayout.SOUTH);

			jP2.add(studentJScrollPane);

			

			

			jP3.add(jLSelectQueryField);    //选择查询字段

			jP3.add(jCBSelectQueryField);   //查询字段

			jP3.add(jLEqual);  //=

			jP3.add(jTFQueryField);	

			jP3.add(jBQuery);

			jP3.add(jBQueryAll);

			jP3.setLayout(new FlowLayout(FlowLayout.CENTER));

			jP3.setPreferredSize(new Dimension(20,20));

			
			jP4.add(jLCn);
			jP4.add(jTCn);

			jP4.add(jLteacher);
			jP4.add(jTteacher);

			jP4.add(jLCname);
			jP4.add(jTCname);

			jP4.setLayout(new FlowLayout(FlowLayout.CENTER));

			jP4.setPreferredSize(new Dimension(30,30));

			jP5.add(jLwe_day);
			jP5.add(jTwe_day);

			jP5.add(jLcredit);
			jP5.add(jTcredit);

			jP5.setLayout(new FlowLayout(FlowLayout.CENTER));

			jP5.setPreferredSize(new Dimension(20,20));

			

			

			jP6.add(jBInsert);

			jP6.add(jBUpdate);

			jP6.add(jBDeleteCurrentRecord);

			jP6.add(jBDeleteAllRecords);

			jP6.setLayout(new FlowLayout(FlowLayout.CENTER));

			jP6.setPreferredSize(new Dimension(20,20));

			

			jPTop.add(jP1);

			jPTop.add(jP2);

			

			jPBottom.setLayout(new GridLayout(4, 1));

			jPBottom.add(jP3);

			jPBottom.add(jP4);

			jPBottom.add(jP5);

			jPBottom.add(jP6);

			this.add("North", jPTop);

			this.add("South", jPBottom);

			

		

			

			this.setLayout(new GridLayout(2, 1));

			//this.setTitle("教室课程设置表");

			this.setSize(580, 500);

			this.setLocation(150, 150);

			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			this.setVisible(true);

			//this.setResizable(false);

			

			

			dbProcess = new DbProcess();

		}



		@Override

		public void actionPerformed(ActionEvent e) {

			if(e.getActionCommand().equals("查询")  

					&& !jTFQueryField.getText().isEmpty()){

					System.out.println("actionPerformed().查询");

					String sQueryField = jTFQueryField.getText().trim();

					queryProcess(sQueryField);

					jTFQueryField.setText("");

				}else if(e.getActionCommand().equals("查询所有记录")) {

					System.out.println("actionPerformed(). 查询所有记录");

					queryAllProcess();

				}else if(e.getActionCommand().equals("插入")

						&& !jTCn.getText().isEmpty()

						&& !jTteacher.getText().isEmpty()

						&& !jTCname.getText().isEmpty()

						&& !jTwe_day.getText().isEmpty()

						&& !jTcredit.getText().isEmpty()){

					System.out.println("actionPerformed(). 插入");

					insertProcess();

				}else if(e.getActionCommand().equals("更新")

						&& !jTCn.getText().isEmpty()

						&& !jTteacher.getText().isEmpty()

						&& !jTCname.getText().isEmpty()

						&& !jTwe_day.getText().isEmpty()

						&& !jTcredit.getText().isEmpty()){

					System.out.println("actionPerformed(). 更新");

					updateProcess();

				}else if(e.getActionCommand().equals("删除当前记录")){

					System.out.println("actionPerformed(). 删除当前记录");

					deleteCurrentRecordProcess();

				}else if(e.getActionCommand().equals("删除所有记录")){

					System.out.println("actionPerformed(). 删除所有记录");

					deleteAllRecordsProcess();

				}

			}

		

		

		/*

		public static void main(String[] args) {

			teacher_couse_aet getcon = new  teacher_couse_aet();

	   }

	   */

		

		public void queryProcess(String sQueryField)

		{

			try{

				// 建立查询条件

				String sql = "select  c.Cn,t.Tn,t.Tname,c.Cname, c.Credit, c.Chours from course c, teacher t where c.Tn=t.Tn AND ";

				String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);

			System.out.println("queryFieldStr="+queryFieldStr);

				if(queryFieldStr.equals("c.Credit") || queryFieldStr.equals("c.Chours")){//int sAge.

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
					v.add(rs.getString("t.Tn"));

					v.add(rs.getString("t.Tname"));

					v.add(rs.getString("c.Cname"));

					v.add(rs.getString("c.Credit"));

					v.add(rs.getString("c.Chours"));

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

				//String sql = "select * from teacher_set;";
				String sql = "select c.Cn,t.Tn,t.Tname,c.Cname, c.Credit, c.Chours from course c, teacher t where c.Tn=t.Tn ; ";

				System.out.println("queryAllProcess(). sql = " + sql);

		

				dbProcess.connect();

				ResultSet rs = dbProcess.executeQuery(sql);



				// 将查询获得的记录数据，转换成适合生成JTable的数据形式

				studentVector.clear();

				while(rs.next()){

					Vector v = new Vector();
					
					v.add(rs.getString("c.Cn"));
					
					v.add(rs.getString("t.Tn"));

					v.add(rs.getString("t.Tname"));

					v.add(rs.getString("c.Cname"));

					v.add(rs.getString("c.Credit"));

					v.add(rs.getString("c.Chours"));

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

			String Cn = jTCn.getText().trim();

			String teacher = jTteacher.getText().trim();

			String Cname = jTCname.getText().trim();

			String we_day = jTwe_day.getText().trim();

			String credit = jTcredit.getText().trim();

			// 建立插入条件

			String sql = "insert into course values('";

			sql = sql + Cn + "','";

			sql = sql + teacher + "','";

			sql = sql + Cname + "',";

			sql = sql + we_day + ",";

			sql = sql + credit + ");";

			



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

			String Cn = jTCn.getText().trim();

			String teacher = jTteacher.getText().trim();

			String Cname = jTCname.getText().trim();

			String we_day = jTwe_day.getText().trim();

			String credit = jTcredit.getText().trim();

			// 建立更新条件

		

			String sql = "update course set Tn = '";

			sql = sql + teacher + "', Cname= '";

			sql = sql + Cname + "', Chours= ";

			sql = sql + we_day + ", Credit= ";

			sql = sql + credit ;

			sql =sql +" WHERE Cn ='"+Cn+"';";

			System.out.println("updateProcess(). sql = " + sql);
			System.out.println(sql);

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

			String Cn = jTCn.getText().trim();

			

			// 建立删除条件

			String sql = "delete from course where Cn= '" + Cn + "';";

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

			String sql = "delete from course;";

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

			

			if(InputStr.equals("教师")){

				outputStr = "t.Tname";

			}else if(InputStr.equals("课程名")){

				outputStr = "c.Cname";

			}else if(InputStr.equals("学分")){

				outputStr = "c.Credit";

			}

			else if(InputStr.equals("学时")){

				outputStr = "c.Chours";

			}

			System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);

			return outputStr;

		}
}
