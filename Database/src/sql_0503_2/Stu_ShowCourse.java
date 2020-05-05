package sql_0503_2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Stu_ShowCourse extends Panel implements ActionListener {

	String stu_id;

	// 定义组件
	
	JLabel jCnl = null;
	JLabel jLStudentInfoTable = null;//学生信息表
	JTextField jCnc = null;//课程号
	//定义界面上的button

	JButton jBQuery,jBDelte = null;//查询

	JPanel jP1, jP2,jP3,jP4= null;

	JPanel jPTop, jPBottom = null;

	DefaultTableModel studentTableModel = null;

	JTable studentJTable = null;

	JScrollPane studentJScrollPane = null;

	Vector studentVector = null;

	Vector titleVector = null;

	private static DbProcess dbProcess;

	

	// 构造函数

	public Stu_ShowCourse(String id) {

		stu_id=id;
		// 创建组件	

		jLStudentInfoTable = new JLabel("学生表");

		jBQuery = new JButton("查询");
		jBDelte =new JButton("取消选课");
		jCnl = new JLabel("课程号");//课程号
		jCnc = new JTextField(20);
		// 设置监听

		jBQuery.addActionListener(this);
		jBDelte.addActionListener(this);
		studentVector = new Vector();

		titleVector = new Vector();

		// 定义表头

		titleVector.add("学号");

		titleVector.add("姓名");

		titleVector.add("课程号");
		
		titleVector.add("课程名");  

		titleVector.add("成绩");  

		//titleVector.add("年龄");

		//studentTableModel = new DefaultTableModel(tableTitle, 15);

		studentJTable = new JTable(studentVector, titleVector);

		studentJTable.setPreferredScrollableViewportSize(new Dimension(600,260));

		studentJScrollPane = new JScrollPane(studentJTable);

		//分别设置水平和垂直滚动条自动出现

		studentJScrollPane.setHorizontalScrollBarPolicy(                

                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		studentJScrollPane.setVerticalScrollBarPolicy(                

                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		studentJTable.addMouseListener(new MouseAdapter()

		{ 

			public void mouseClicked(MouseEvent e) 

			{ 

				int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置

				System.out.println("mouseClicked(). row = " + row);

				Vector v = new Vector();

				v = (Vector) studentVector.get(row);




				//jTFSName.setText((String) v.get(1));// 姓名

				jCnc.setText((String) v.get(2));// 课程号
				

			}

		});

		jP1 = new JPanel();

		jP2 = new JPanel();

		jP3 = new JPanel();
		
		jP4= new JPanel();
		jPTop = new JPanel();

		jPBottom = new JPanel();

		jP1.add(jLStudentInfoTable,BorderLayout.NORTH);

		jP2.add(studentJScrollPane);

		jP3.add(jBQuery);

		jPTop.add(jP1);

		jPTop.add(jP2);
		
		jP4.setLayout(new FlowLayout(FlowLayout.CENTER));
		jP4.add(jCnl);
		jP4.add(jCnc);
		jPBottom.add(jBDelte);
		this.add(jPTop);

		this.add(jP3);
		
		this.add(jP4);
		
		this.add(jPBottom);
		this.setLayout(new GridLayout(4, 1));

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
		System.out.println(e.getActionCommand());

		if(e.getActionCommand().equals("查询") ){

				System.out.println("actionPerformed(). 查询");

				queryProcess();

				//jTFQueryField.setText("");

		}
		else if(e.getActionCommand().equals("取消选课") ) {
			System.out.println("actionPerformed(). 取消选课");

			deleteProcess();
		}

		}

	/*

	public static void main(String[] args) {

		DatabaseCourseDesign getcon = new  DatabaseCourseDesign();

    }

    */
	public void deleteProcess() {

		String cn = jCnc.getText().trim();
		// 建立删除条件
		if(cn.equals("")) {
			JOptionPane.showMessageDialog(null,
					"你还未选择课程","错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String sql = "delete from sc where sn = '" + stu_id + "' AND cn= '"+cn+"';";

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
		queryProcess();
		jCnc.setText("");
	}
	public void queryProcess()

	{

		try{

			// 建立查询条件

			String sql = "select * from stu_view where Sn="+stu_id+";";

		
			System.out.println("queryProcess(). sql = " + sql);


			dbProcess.connect();

			ResultSet rs = dbProcess.executeQuery(sql);


			// 将查询获得的记录数据，转换成适合生成JTable的数据形式

			studentVector.clear();

			while(rs.next()){

				Vector<String> v = new Vector();

				v.add(rs.getString("Sn"));
				
				v.add(rs.getString("Sname"));
				
				//v.add(rs.getString("Dn"));
				
				v.add(rs.getString("Cn"));

				v.add(rs.getString("Cname"));
				
				v.add(rs.getString("Score"));

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

}
