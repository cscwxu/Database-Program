package sql_0503_2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Tea_TeacherInformation extends Panel implements ActionListener {

	String tea_id;

	// 定义组件
	JLabel oldPassName=null;
	JLabel newPassName=null;
	
	JTextField oldPass=null;
	JTextField newPass=null;

	JLabel jLStudentInfoTable = null;//学生信息表

	//定义界面上的button

	JButton jBQuery = null;//查询
	
	JButton jBUpdate = null;//更新

	JPanel jP1, jP2,jP3,jp4,jp5,jp6 = null;

	JPanel jPTop, jPBottom = null;

	DefaultTableModel studentTableModel = null;

	JTable studentJTable = null;

	JScrollPane studentJScrollPane = null;

	Vector studentVector = null;

	Vector titleVector = null;

	private static DbProcess dbProcess;

	String SelectQueryFieldStr = "学号";

	// 构造函数

	public Tea_TeacherInformation(String id) {

		tea_id=id;
		// 创建组件	
		oldPassName=new JLabel("旧密码");
		newPassName=new JLabel("新密码");
		oldPass=new JTextField(20);
		newPass=new JTextField(20);
		jLStudentInfoTable = new JLabel("信息表");

		jBQuery = new JButton("查询教师信息");
		jBUpdate=new JButton("确认修改密码");
		// 设置监听

		jBQuery.addActionListener(this);
		jBUpdate.addActionListener(this);

		studentVector = new Vector();

		titleVector = new Vector();

		// 定义表头

		titleVector.add("编号");

		titleVector.add("姓名");

		titleVector.add("系别");  
		
		titleVector.add("工资");

		//studentTableModel = new DefaultTableModel(tableTitle, 15);

		studentJTable = new JTable(studentVector, titleVector);

		studentJTable.setPreferredScrollableViewportSize(new Dimension(600,260));

		studentJScrollPane = new JScrollPane(studentJTable);

		//分别设置水平和垂直滚动条自动出现

		studentJScrollPane.setHorizontalScrollBarPolicy(                

                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		studentJScrollPane.setVerticalScrollBarPolicy(                

                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		jP1 = new JPanel();

		jP2 = new JPanel();

		jP3 = new JPanel();
		
		jp4 = new JPanel();
		
		jp5 = new JPanel();
		
		jp6 =new JPanel();

		jPTop = new JPanel();

		jPBottom = new JPanel();

		jP1.add(jLStudentInfoTable,BorderLayout.NORTH);

		jP2.add(studentJScrollPane);
		
		jP3.add(jBQuery,BorderLayout.SOUTH);
		
		//jP3.add(new JLabel("                                                                "));
		//jP3.setLayout(new FlowLayout(FlowLayout.));
		//jP3.setPreferredSize(new Dimension(40,40));
		jp4.add(oldPassName);
		jp4.add(oldPass);
		jp4.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		jp5.add(newPassName);
		jp5.add(newPass);
		jp5.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		jp6.add(jBUpdate);
		jp6.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		jPTop.add(jP1);

		jPTop.add(jP2);
		//jPTop.add(jP3);
		jPBottom.setLayout(new GridLayout(3, 1));

		//jPBottom.add(jP3);
		jPBottom.add(jp4);
		jPBottom.add(jp5);
		jPBottom.add(jp6);
		this.add(jPTop);
		this.add(jP3);
		this.add( jPBottom);

		this.setLayout(new GridLayout(3, 1));

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

		if(e.getActionCommand().equals("查询教师信息") ){

				System.out.println("actionPerformed(). 查询教师信息");

				queryProcess();

				//jTFQueryField.setText("");

		}
		else if(e.getActionCommand().equals("确认修改密码")) {
			updatePassword();
		}

	}
	public void updatePassword(){
		boolean flag=false;
		String oldP=oldPass.getText().trim();
		String newP=newPass.getText().trim();
		try{
			// 建立查询条件
			String sql = "select * from teacher where Tn="+tea_id+";";
			System.out.println("queryProcess(). sql = " + sql);
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			studentVector.clear();
			while(rs.next()){
				if(rs.getString("Tpwd").equals(oldP)) {
					sql="update Teacher set Tpwd="+newP+" where Tn="+tea_id+";";
					if(dbProcess.executeUpdate(sql)!=0) {
						oldPass.setText("");
						newPass.setText("");
						flag=true;
						JOptionPane.showMessageDialog(null,
								"密码更新成功","正确",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
				else {
					JOptionPane.showMessageDialog(null,
							"原始密码不正确","错误",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(!flag) dbProcess.disconnect();
		}catch(SQLException sqle){
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}
		oldPass.setText("");
		newPass.setText("");
	}
	/*

	public static void main(String[] args) {

		DatabaseCourseDesign getcon = new  DatabaseCourseDesign();

    }

    */

	public void queryProcess()

	{

		try{

			// 建立查询条件

			String sql = "select * from Teacher where Tn="+tea_id+";";

		
			System.out.println("queryProcess(). sql = " + sql);


			dbProcess.connect();

			ResultSet rs = dbProcess.executeQuery(sql);


			// 将查询获得的记录数据，转换成适合生成JTable的数据形式

			studentVector.clear();

			while(rs.next()){

				Vector<String> v = new Vector();

				v.add(rs.getString("Tn"));
				
				v.add(rs.getString("Tname"));
				
				//v.add(rs.getString("Sclass"));
				
				v.add(rs.getString("Dn"));

				//v.add(rs.getString("Ssex"));
				
				v.add(String.valueOf(rs.getInt("Salary")));

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
