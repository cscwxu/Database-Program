package sql_0503_2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Stu_CourseSelect extends Panel implements ActionListener {

	// 定义组件
		String stu_id;
		String course_id;
		JLabel jLStudentInfoTable = null;//学生信息表

		JLabel jLSelectQueryField = null;//选择查询字段

		JLabel jLEqual = null;//=

		//JLabel jLsno = null;//学号

		//JLabel jLsname = null;//姓名

		//JLabel jLcno = null;//性别

		JLabel jLcname = null;//年龄

		//JLabel jLcoom = null;//专业

		//JLabel jLteme = null;//住址
		
		//JLabel jLcji = null;//学分

		//JLabel jLtea = null;//任课老师



		JTextField jTFQueryField = null;//查询字段

		//JTextField jTFsno = null;//学号

		//JTextField jTFsname = null;//姓名

		//JTextField jTFcno = null;//性别

		JTextField jTFcname = null;//年龄

		//JTextField jTFcoom = null;//专业

		//JTextField jTFtime = null;//住址

		//JTextField jTFcji = null;//学分

		//JTextField jTFtea = null;//任课老师

		

		JButton jBQuery = null;//查询

		JButton jBQueryAll = null;//查询所有记录

		JButton jBInsert = null;//插入

		//JButton jBUpdate = null;//更新

		//JButton jBDeleteCurrentRecord = null;//删除当前记录

		//JButton jBDeleteAllRecords = null;//删除所有记录

		

		//JComboBox jCBSelectQueryField = null;

		JComboBox<String> jCBSelectQueryField = null;//查询字段

		JPanel jP1, jP2,jP3,jP4,jP5,jP6,jP7 = null;

		JPanel jPTop, jPBottom = null;

		DefaultTableModel studentTableModel = null;

		JTable studentJTable = null;

		JScrollPane studentJScrollPane = null;

		Vector studentVector = null;

		Vector titleVector = null;

		

		private static DbProcess dbProcess;

		String SelectQueryFieldStr = "学号";

		

		// 构造函数

		public Stu_CourseSelect(String id) {

			// 创建组件	
			stu_id=id;

			jLStudentInfoTable = new JLabel("选课结果");

			jLSelectQueryField = new JLabel("选择查询字段");

			jLEqual = new JLabel(" = ");

			//jLsno = new JLabel("学    号    ");

			//jLsname = new JLabel("   姓    名   ");

			//jLcno = new JLabel(" 课程号  ");

			jLcname = new JLabel("课程名");

			//jLcoom = new JLabel("上课教室");

			//jLteme = new JLabel("上课时间");

			//jLcji = new JLabel("课程学分");

			//jLtea = new JLabel("任课教师");

			

			

			jTFQueryField = new JTextField(18);//查询字段

			//jTFsno = new JTextField(14);//学号

			//jTFsname = new JTextField(14);//姓名

			//jTFcno = new JTextField(14);//性别

			jTFcname = new JTextField(14);//年龄

			//jTFcoom = new JTextField(14);//专业

			//jTFtime = new JTextField(14);//住址

			//jTFcji = new JTextField(14);//住址

			//jTFtea = new JTextField(14);//住址

			

			jBQuery = new JButton("查询选课结果");

			jBQueryAll = new JButton("查询所有记录");

			jBInsert = new JButton("确认选课");

			//jBUpdate = new JButton("修改选课");

			//jBDeleteCurrentRecord = new JButton("删除当前记录");

			//jBDeleteAllRecords = new JButton("删除所有记录");

			// 设置监听

			jBQuery.addActionListener(this);

			jBQueryAll.addActionListener(this);

			jBInsert.addActionListener(this);

			//jBUpdate.addActionListener(this);

			//jBDeleteCurrentRecord.addActionListener(this);

			//jDeleteAllRecords.addActionListener(this);

			

			jCBSelectQueryField = new JComboBox<String>();//查询字段

			jCBSelectQueryField.addItem("课程号");

			jCBSelectQueryField.addItem("课程名");

			jCBSelectQueryField.addItem("课时");

			jCBSelectQueryField.addItem("学分");

			jCBSelectQueryField.addItem("任课教师");

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

			titleVector.add("课程名");

			titleVector.add("学时");

			titleVector.add("学分");

			titleVector.add("任课教师");

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
					jTFcname.setText((String) v.get(1));
					course_id=(String) v.get(0);
				}

			});





			jP1 = new JPanel();

			jP2 = new JPanel();

			jP3 = new JPanel();

			jP4 = new JPanel();

			jP5 = new JPanel();


			jP7 = new JPanel();

			jPTop = new JPanel();

			jPBottom = new JPanel();

			

			jP1.add(jLStudentInfoTable,BorderLayout.SOUTH);

			jP2.add(studentJScrollPane);

			

			jP3.add(jLSelectQueryField);

			jP3.add(jCBSelectQueryField);

			jP3.add(jLEqual);

			jP3.add(jTFQueryField);

			jP3.add(jBQuery);

			jP3.add(jBQueryAll);

			jP3.setLayout(new FlowLayout(FlowLayout.LEFT));

			jP3.setPreferredSize(new Dimension(20,20));

			

			jP4.setLayout(new FlowLayout(FlowLayout.CENTER));

			jP4.setPreferredSize(new Dimension(20,20));

		

			jP5.add(jLcname);

			jP5.add(jTFcname);

			//jP5.add(jLteme);

			//jP5.add(jTFtime);

			jP5.setLayout(new FlowLayout(FlowLayout.CENTER));

			jP5.setPreferredSize(new Dimension(20,20));

	

			

			jP7.add(jBInsert);


			jP7.setLayout(new FlowLayout(FlowLayout.CENTER));

			jP7.setPreferredSize(new Dimension(20,20));

			

			jPTop.add(jP1);

			jPTop.add(jP2);

			

			jPBottom.setLayout(new GridLayout(5, 1));

			jPBottom.add(jP3);

			jPBottom.add(jP4);

			jPBottom.add(jP5);



			jPBottom.add(jP7);

			

			this.add("North", jPTop);

			this.add("South", jPBottom);

		

			this.setLayout(new GridLayout(2, 1));

			//this.setTitle("");

			this.setSize(700, 652);

			this.setLocation(150, 150);

			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			this.setVisible(true);

			//this.setResizable(false);

			

			dbProcess = new DbProcess();

		}



		@Override

		public void actionPerformed(ActionEvent e) {

			if(e.getActionCommand().equals("查询选课结果")  

				&& !jTFQueryField.getText().isEmpty()){

				System.out.println("actionPerformed(). 查询");

				String sQueryField = jTFQueryField.getText().trim();

				queryProcess(sQueryField);

				jTFQueryField.setText("");

			}else if(e.getActionCommand().equals("查询所有记录")) {

				System.out.println("actionPerformed(). 查询所有记录");

				queryAllProcess();

			}else if(e.getActionCommand().equals("确认选课")){

				System.out.println("actionPerformed(). 确认选课");

				insertProcess();

			}

		}

		

		public static void main(String[] args) {

	        // TODO Auto-generated method stub

			Man_StudentCourseSelect getcon = new  Man_StudentCourseSelect();

	    }

		

		public void queryProcess(String sQueryField)

		{

			try{

				// 建立查询条件

				String sql = "select SC.Sn, s.Sname, c.Cn, c.Cname, c.Chours, c.Credit, t.Tname from student s,SC,course c,teacher t where s.Sn=SC.Sn  AND SC.Cn=c.Cn AND C.Tn=t.Tn AND ";

				String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);

			

				if(queryFieldStr.equals("c.Chours")||queryFieldStr.equals("c.Credit")){//int sAge.

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

					v.add(rs.getString("c.Cn"));

					v.add(rs.getString("c.Cname"));
					
					v.add(rs.getString("c.Chours"));
					
					v.add(rs.getString("c.Credit"));

					v.add(rs.getString("t.Tname"));

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
				String sql= "select c.Cn,c.Cname,c.Chours,c.Credit,t.Tname from teacher t,course c where t.Tn=c.Tn AND c.Cn not in (select Cn from sc where Sn='"+stu_id+"');";
				//String sql = "select SC.Sn, s.Sname, c.Cn, c.Cname, c.Chours, c.Credit, t.Tname from student s,SC,course c,teacher t where s.Sn=SC.Sn  AND SC.Cn=c.Cn AND C.Tn=t.Tn;";

				System.out.println("queryAllProcess(). sql = " + sql);

		

				dbProcess.connect();

				ResultSet rs = dbProcess.executeQuery(sql);



				// 将查询获得的记录数据，转换成适合生成JTable的数据形式

				studentVector.clear();

				while(rs.next()){

					Vector v = new Vector();

					v.add(rs.getString("c.Cn"));

					v.add(rs.getString("c.Cname"));
					
					v.add(rs.getString("c.Chours"));
					
					v.add(rs.getString("c.Credit"));

					v.add(rs.getString("t.Tname"));

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

			// 建立插入条件
			if(jTFcname.getText().equals("")) {
				JOptionPane.showMessageDialog(null,
						"你还未选择课程","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String sql="insert into sc(sn,cn) values('"+stu_id+"','"+course_id+"');";

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
			jTFcname.setText("");
		}

		

		public String jCBSelectQueryFieldTransfer(String InputStr)

		{

			String outputStr = "";

			System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);

			

			if(InputStr.equals("学号")){

				outputStr = "SC.Sn";

			}else if(InputStr.equals("姓名")){

				outputStr = "s.Sname";

			}else if(InputStr.equals("课程号")){

				outputStr = "c.Cn";

			}else if(InputStr.equals("课程名")){

				outputStr = "c.Cname";

			}else if(InputStr.equals("学时")){

				outputStr = "c.Chours";

			}else if(InputStr.equals("学分")){

				outputStr = "c.Credit";

			}else if(InputStr.equals("教师")){

				outputStr = "t.Tname";

			}

			System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);

			return outputStr;

		}

}
