package sql_0503_2;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.table.DefaultTableModel;

public class stu_scoer extends Panel implements ActionListener {

	String stu_id;

	// �������

	JLabel jLStudentInfoTable = null;//ѧ����Ϣ��

	//��������ϵ�button

	JButton jBQuery = null;//��ѯ

	JPanel jP1, jP2,jP3 = null;

	JPanel jPTop, jPBottom = null;

	DefaultTableModel studentTableModel = null;

	JTable studentJTable = null;

	JScrollPane studentJScrollPane = null;

	Vector studentVector = null;

	Vector titleVector = null;

	private static DbProcess dbProcess;

	

	// ���캯��

	public stu_scoer(String id) {

		stu_id=id;
		// �������	

		jLStudentInfoTable = new JLabel("ѧ����");

		jBQuery = new JButton("��ѯ");

		// ���ü���

		jBQuery.addActionListener(this);

		studentVector = new Vector();

		titleVector = new Vector();

		// �����ͷ

		titleVector.add("ѧ��");

		titleVector.add("����");

		titleVector.add("�γ̺�");
		
		titleVector.add("�γ���");  

		titleVector.add("�ɼ�");  

		//titleVector.add("����");

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

		jPTop = new JPanel();

		jPBottom = new JPanel();

		jP1.add(jLStudentInfoTable,BorderLayout.NORTH);

		jP2.add(studentJScrollPane);

		jP3.add(jBQuery);

		jPTop.add(jP1);

		jPTop.add(jP2);

		jPBottom.setLayout(new GridLayout(3, 1));

		jPBottom.add(jP3);
		this.add("North", jPTop);

		this.add("South", jPBottom);

		this.setLayout(new GridLayout(2, 1));

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

		if(e.getActionCommand().equals("��ѯ") ){

				System.out.println("actionPerformed(). ��ѯ");

				queryProcess();

				//jTFQueryField.setText("");

			}

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

			String sql = "select * from stu_view where Sn="+stu_id+";";

		
			System.out.println("queryProcess(). sql = " + sql);


			dbProcess.connect();

			ResultSet rs = dbProcess.executeQuery(sql);


			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ

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

				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);

		}catch(Exception e){

			System.out.println("e = " + e);

			JOptionPane.showMessageDialog(null,

				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);

		}

	}

}
