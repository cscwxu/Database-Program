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

	// �������
	JLabel oldPassName=null;
	JLabel newPassName=null;
	
	JTextField oldPass=null;
	JTextField newPass=null;

	JLabel jLStudentInfoTable = null;//ѧ����Ϣ��

	//��������ϵ�button

	JButton jBQuery = null;//��ѯ
	
	JButton jBUpdate = null;//����

	JPanel jP1, jP2,jP3,jp4,jp5,jp6 = null;

	JPanel jPTop, jPBottom = null;

	DefaultTableModel studentTableModel = null;

	JTable studentJTable = null;

	JScrollPane studentJScrollPane = null;

	Vector studentVector = null;

	Vector titleVector = null;

	private static DbProcess dbProcess;

	String SelectQueryFieldStr = "ѧ��";

	// ���캯��

	public Tea_TeacherInformation(String id) {

		tea_id=id;
		// �������	
		oldPassName=new JLabel("������");
		newPassName=new JLabel("������");
		oldPass=new JTextField(20);
		newPass=new JTextField(20);
		jLStudentInfoTable = new JLabel("��Ϣ��");

		jBQuery = new JButton("��ѯ��ʦ��Ϣ");
		jBUpdate=new JButton("ȷ���޸�����");
		// ���ü���

		jBQuery.addActionListener(this);
		jBUpdate.addActionListener(this);

		studentVector = new Vector();

		titleVector = new Vector();

		// �����ͷ

		titleVector.add("���");

		titleVector.add("����");

		titleVector.add("ϵ��");  
		
		titleVector.add("����");

		//studentTableModel = new DefaultTableModel(tableTitle, 15);

		studentJTable = new JTable(studentVector, titleVector);

		studentJTable.setPreferredScrollableViewportSize(new Dimension(600,260));

		studentJScrollPane = new JScrollPane(studentJTable);

		//�ֱ�����ˮƽ�ʹ�ֱ�������Զ�����

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

		//this.setTitle("ѧ����Ϣ����");

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

		if(e.getActionCommand().equals("��ѯ��ʦ��Ϣ") ){

				System.out.println("actionPerformed(). ��ѯ��ʦ��Ϣ");

				queryProcess();

				//jTFQueryField.setText("");

		}
		else if(e.getActionCommand().equals("ȷ���޸�����")) {
			updatePassword();
		}

	}
	public void updatePassword(){
		boolean flag=false;
		String oldP=oldPass.getText().trim();
		String newP=newPass.getText().trim();
		try{
			// ������ѯ����
			String sql = "select * from teacher where Tn="+tea_id+";";
			System.out.println("queryProcess(). sql = " + sql);
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);
			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			studentVector.clear();
			while(rs.next()){
				if(rs.getString("Tpwd").equals(oldP)) {
					sql="update Teacher set Tpwd="+newP+" where Tn="+tea_id+";";
					if(dbProcess.executeUpdate(sql)!=0) {
						oldPass.setText("");
						newPass.setText("");
						flag=true;
						JOptionPane.showMessageDialog(null,
								"������³ɹ�","��ȷ",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
				else {
					JOptionPane.showMessageDialog(null,
							"ԭʼ���벻��ȷ","����",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(!flag) dbProcess.disconnect();
		}catch(SQLException sqle){
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
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

			// ������ѯ����

			String sql = "select * from Teacher where Tn="+tea_id+";";

		
			System.out.println("queryProcess(). sql = " + sql);


			dbProcess.connect();

			ResultSet rs = dbProcess.executeQuery(sql);


			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ

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

				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);

		}catch(Exception e){

			System.out.println("e = " + e);

			JOptionPane.showMessageDialog(null,

				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);

		}

	}

}
