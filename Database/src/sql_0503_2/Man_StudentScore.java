package sql_0503_2;
//学生成绩管理面板
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



public class Man_StudentScore extends Panel implements ActionListener {

	// 定义组件

	JLabel jLStudentInfoTable = null;//学生信息表

	JLabel jLSelectQueryField = null;//选择查询字段

	JLabel jLEqual = null;//=

	JLabel jLSNo = null;//学号

	//JLabel jLSName = null;//姓名

	JLabel jLcourse = null;//课程号
	
	//JLabel jLcourseName = null;//课程名

	JLabel jLscoer = null;//成绩
	
	

	



	JTextField jTFQueryField = null;//查询字段

	JTextField jTFSNo = null;//学号

	//JTextField jTFSName = null;//姓名

	JTextField jTFcourse = null;//课程号
	
	//JTextField jTFcourseName = null;//课程名

	JTextField jTFscoer = null;//成绩

	

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

	JPanel jP1, jP2,jP3,jP4,jP5 = null;

	JPanel jPTop, jPBottom = null;

	DefaultTableModel studentTableModel = null;

	JTable studentJTable = null;

	JScrollPane studentJScrollPane = null;

	Vector studentVector = null;

	Vector titleVector = null;

	

	private static DbProcess dbProcess;

	String SelectQueryFieldStr = "课程名";

	

	// 构造函数

	public Man_StudentScore() {

		// 创建组件	

		jLStudentInfoTable = new JLabel("成绩信息");

		jLSelectQueryField = new JLabel("选择查询字段");

		jLEqual = new JLabel(" = ");

		jLSNo = new JLabel("学    号");

		//jLSName = new JLabel("姓  名");

	    jLcourse = new JLabel("课程号");//课程号
	    
	    //jLcourseName = new JLabel("课程名");//课程名

		jLscoer = new JLabel("成  绩");;//成绩

		

		jTFQueryField = new JTextField(10);//查询字段

		jTFSNo = new JTextField(18);//学号

		//jTFSName = new JTextField(18);//姓名

		jTFcourse = new JTextField(18);//课程号
		
		//jTFcourseName = new JTextField(18);//课程名

	    jTFscoer = new JTextField(18);//成绩

		

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
		
		jCBSelectQueryField.addItem("课程名");

		jCBSelectQueryField.addItem("学号");  

		jCBSelectQueryField.addItem("姓名");  

		jCBSelectQueryField.addItem("课程号");
		
		

		jCBSelectQueryField.addItem("成绩");

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

		titleVector.add("课程号");
		
		titleVector.add("课程名");

		titleVector.add("成绩");

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

				//jTFSName.setText((String) v.get(1));// 姓名

				jTFcourse.setText((String) v.get(2));// 课程号
				
				//jTFcourseName.setText((String) v.get(3));// 课程名

				jTFscoer.setText((String) v.get(4));// 成绩号

			}

		});





		jP1 = new JPanel();

		jP2 = new JPanel();

		jP5 = new JPanel();

		jP3 = new JPanel();

		jP4 = new JPanel();

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

		

		jP4.add(jLSNo);
		jP4.add(jTFSNo);
		
		//jP4.add(jLSName);
		//jP4.add(jTFSName);
		
		jP4.add(jLcourse);
		jP4.add(jTFcourse);
		
		//jP4.add(jLcourseName);
		//jP4.add(jTFcourseName);
		
		jP4.add(jLscoer);
		jP4.add(jTFscoer);

		

		jP4.setLayout(new FlowLayout(FlowLayout.CENTER));

		jP4.setPreferredSize(new Dimension(30,30));

		

		

		jP5.add(jBInsert);

		jP5.add(jBUpdate);

		jP5.add(jBDeleteCurrentRecord);

		jP5.add(jBDeleteAllRecords);

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

		//this.setTitle("成绩信息操作");

		this.setSize(530, 500);

		this.setLocation(150, 150);

		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

		//this.setResizable(false);

		

		dbProcess = new DbProcess();

	}



	@Override

	public void actionPerformed(ActionEvent e) {
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
		if(e.getActionCommand().equals("查询")  

				&& !jTFQueryField.getText().isEmpty()){

				System.out.println("actionPerformed(). 查询");

				String sQueryField = jTFQueryField.getText().trim();

				queryProcess(sQueryField);

				jTFQueryField.setText("");

			}else if(e.getActionCommand().equals("查询所有记录")) {

				System.out.println("actionPerformed(). 查询所有记录");

				queryAllProcess();

			}else if(e.getActionCommand().equals("插入")

					&& !jTFSNo.getText().isEmpty()

					//&& !jTFSName.getText().isEmpty()

					&& !jTFcourse.getText().isEmpty()
					
					//&& !jTFcourseName.getText().isEmpty()

					&& !jTFscoer.getText().isEmpty()){

				System.out.println("actionPerformed(). 插入");

				insertProcess();

			}else if(e.getActionCommand().equals("更新")

					&& !jTFSNo.getText().isEmpty()

					//&& !jTFSName.getText().isEmpty()

					&& !jTFcourse.getText().isEmpty()
					
					//&& !jTFcourseName.getText().isEmpty()

					&& !jTFscoer.getText().isEmpty()){

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

		scoer  getcon = new  scoer();

   }

	

	

	*/

	public void queryProcess(String sQueryField)

	{

		try{

			// 建立查询条件

			String sql = "select sc.Sn, s.Sname, sc.Cn, c.Cname, sc.Score from sc,course c,student s where sc.Sn=s.Sn AND sc.Cn=c.Cn AND ";

			String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);

		

			if(queryFieldStr.equals("sc.score")){//int sAge.

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

				v.add(rs.getString("sc.Sn"));

				v.add(rs.getString("s.Sname"));

				v.add(rs.getString("sc.Cn"));
				
				v.add(rs.getString("c.Cname"));

				v.add(rs.getString("sc.score"));

				

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

			//String sql = "select * from score;";
			String sql = "select sc.Sn, s.Sname, sc.Cn, c.Cname, sc.Score from sc,course c,student s where sc.Sn=s.Sn AND sc.Cn=c.Cn; ";

			System.out.println("queryAllProcess(). sql = " + sql);

	

			dbProcess.connect();

			ResultSet rs = dbProcess.executeQuery(sql);



			// 将查询获得的记录数据，转换成适合生成JTable的数据形式

			studentVector.clear();

			while(rs.next()){

				Vector v = new Vector();

				v.add(rs.getString("sc.Sn"));

				v.add(rs.getString("s.Sname"));

				v.add(rs.getString("sc.Cn"));
				
				v.add(rs.getString("c.Cname"));

				v.add(rs.getString("sc.score"));

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

		//String name = jTFSName.getText().trim();

		String course = jTFcourse.getText().trim();
		
		String score = jTFscoer.getText().trim();

		// 建立插入条件

		String sql = "insert into sc  values('";

		sql = sql + id + "','";

		//sql = sql + name + "','";

		sql = sql + course + "','";

		sql = sql + score + "');";

		



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

		//String sName = jTFSName.getText().trim();

		String course= jTFcourse.getText().trim();

		String score= jTFscoer.getText().trim();

		// 建立更新条件

		String sql = "update sc set  score=";

		//sql = sql + sName + "', name = '";

		//sql = sql + course + ", department = '";

		sql = sql + score + " where Sn='";
		sql = sql + sNo + "' AND Cn='";
		sql = sql + course + "';";

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

		

		// 建立删除条件

		String sql = "delete from sc where  Sn= '" + sNo + "';";

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

		String sql = "delete from sc;";

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

			outputStr = "sc.Sn";

		}else if(InputStr.equals("姓名")){

			outputStr = "s.Sname";

		}else if(InputStr.equals("课程号")){

			outputStr = "sc.Cn";

		}else if(InputStr.equals("课程名")){

			outputStr = "c.Cname";

		}else if(InputStr.equals("成绩")){

			outputStr = "sc.score";

		}

		System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);

		return outputStr;

	}

}