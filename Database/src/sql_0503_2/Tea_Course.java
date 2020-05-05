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



public class tea_course extends Panel implements ActionListener {

	// 定义组件
	String tea_id;
	JLabel jLStudentInfoTable = null;//学生信息表

	JLabel jLSelectQueryField = null;//选择查询字段

	JLabel jLEqual = null;//=

	//JLabel jLCn = null;//课号
	//JLabel jLteacher = null;//教师号

	//JLabel jLCname = null;//课名

	//JLabel jLwe_day = null;//学时
	//JLabel jLcredit = null;//学分

	

	JTextField jTCn = null;//查询字段

	//JTextField jTteacher = null;//学号

	//JTextField jTCname = null;//姓名

	//JTextField jTwe_day = null;//班级
	//JTextField jTcredit = null;//班级

	JTextField jTFQueryField=null;

	

	//定义界面上的button

	JButton jBQuery = null;//查询

	JButton jBQueryAll = null;//查询所有记录

	//JButton jBInsert = null;//插入

	//JButton jBUpdate = null;//更新

	//JButton jBDeleteCurrentRecord = null;//删除当前记录

	//JButton jBDeleteAllRecords = null;//删除所有记录

	

	

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

	String SelectQueryFieldStr = "课程名";

	

	// 构造函数

	public tea_course(String id) {

		// 创建组件	
		tea_id=id;
		System.out.println(id);
		jLStudentInfoTable = new JLabel("教师课程设置表");

		jLSelectQueryField = new JLabel("选择查询字段");

		jLEqual = new JLabel(" = ");
		/*

		jLCn = new JLabel("课程号");

		jLteacher = new JLabel("教师编号");

		jLCname = new JLabel("课程名");

		jLwe_day = new JLabel("学时");

		jLcredit = new JLabel("学分");
		

		


		

		jTteacher = new JTextField(10);//学号

		jTCname = new JTextField(10);//姓名

		jTwe_day = new JTextField(10);//性别

		jTcredit = new JTextField(10);//性别

		

		*/
		jTCn = new JTextField(10);//查询字段
		jBQuery = new JButton("查询");
		jTFQueryField=new JTextField(10);
		jBQueryAll = new JButton("查询所有记录");
		/*

		jBInsert = new JButton("插入");

		jBUpdate = new JButton("更新");

		jBDeleteCurrentRecord = new JButton("删除当前记录");

		jBDeleteAllRecords = new JButton("删除所有记录");
		 */

		// 设置监听

		jBQuery.addActionListener(this);

		jBQueryAll.addActionListener(this);

		//jBInsert.addActionListener(this);

		//jBUpdate.addActionListener(this);

		//jBDeleteCurrentRecord.addActionListener(this);

		//jBDeleteAllRecords.addActionListener(this);

		

		jCBSelectQueryField = new JComboBox<String>();//查询字段

		

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

		titleVector.add("教师");

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

		

		jPTop.add(jP1);

		jPTop.add(jP2);

		

		jPBottom.setLayout(new GridLayout(1, 1));

		jPBottom.add(jP3);



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

			String sql = "select t.Tname, c.Cname, c.Credit, c.Chours from course c, teacher t where c.Tn=t.Tn AND t.Tn="+tea_id+" AND ";

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
			String sql = "select t.Tname, c.Cname, c.Credit, c.Chours from course c, teacher t where c.Tn=t.Tn AND t.Tn="+tea_id+";";

			System.out.println("queryAllProcess(). sql = " + sql);

	

			dbProcess.connect();

			ResultSet rs = dbProcess.executeQuery(sql);



			// 将查询获得的记录数据，转换成适合生成JTable的数据形式

			studentVector.clear();

			while(rs.next()){

				Vector v = new Vector();

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