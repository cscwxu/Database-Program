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



public class Teachercahar extends Panel implements ActionListener {

	// �������

	JLabel jLStudentInfoTable = null;//ѧ����Ϣ��

	JLabel jLSelectQueryField = null;//ѡ���ѯ�ֶ�

	JLabel jLEqual = null;//=

	JLabel jLSNo = null;//���
	JLabel jLSDn = null;//ϵ���
	JLabel jLSName = null;//����
	JLabel jLSsalary = null;//нˮ
	//JLabel jLclass = null;//�ڿ�רҵ



	JTextField jTFQueryField = null;//��ѯ�ֶ�

	JTextField jTFSNo = null;//���
	JTextField jTFSDn = null;//���
	JTextField jTFSName = null;//����
	JTextField jTFsalary = null;//���
	//JTextField jTclass = null;//�ڿ�רҵ

	

	//��������ϵ�button

	JButton jBQuery = null;//��ѯ

	JButton jBQueryAll = null;//��ѯ���м�¼

	JButton jBInsert = null;//����

	JButton jBUpdate = null;//����

	JButton jBDeleteCurrentRecord = null;//ɾ����ǰ��¼

	JButton jBDeleteAllRecords = null;//ɾ�����м�¼

	

	//JComboBox jCBSelectQueryField = null;

	//������

	JComboBox<String> jCBSelectQueryField = null;//��ѯ�ֶ�

	JPanel jP1, jP2,jP3,jP4,jP5 = null;

	JPanel jPTop, jPBottom = null;

	DefaultTableModel studentTableModel = null;

	JTable studentJTable = null;

	JScrollPane studentJScrollPane = null;

	Vector studentVector = null;

	Vector titleVector = null;

	

	private static DbProcess dbProcess;

	String SelectQueryFieldStr = "���";

	

	// ���캯��

	public Teachercahar() {

		// �������	

		jLStudentInfoTable = new JLabel("��ʦ��");

		jLSelectQueryField = new JLabel("ѡ���ѯ�ֶ�");

		jLEqual = new JLabel(" = ");

		jLSNo = new JLabel("���");
		jLSDn = new JLabel("ϵ���");
		jLSName = new JLabel("����");
		jLSsalary = new JLabel("нˮ");
		//jLclass = new JLabel("ϵ��");

		

		jTFQueryField = new JTextField(10);//��ѯ�ֶ�

		jTFSNo = new JTextField(10);//���
		jTFSDn = new JTextField(10);//���
		jTFSName = new JTextField(10);//����
		jTFsalary = new JTextField(10);//����

		//jTclass = new JTextField(10);//�ڿ�רҵ

		

		jBQuery = new JButton("��ѯ");

		jBQueryAll = new JButton("��ѯ���м�¼");

		jBInsert = new JButton("����");

		jBUpdate = new JButton("����");

		jBDeleteCurrentRecord = new JButton("ɾ����ǰ��¼");

		jBDeleteAllRecords = new JButton("ɾ�����м�¼");

		// ���ü���

		jBQuery.addActionListener(this);

		jBQueryAll.addActionListener(this);

		jBInsert.addActionListener(this);

		jBUpdate.addActionListener(this);

		jBDeleteCurrentRecord.addActionListener(this);

		jBDeleteAllRecords.addActionListener(this);

		

		jCBSelectQueryField = new JComboBox<String>();//��ѯ�ֶ�

		jCBSelectQueryField.addItem("���");
		
		jCBSelectQueryField.addItem("����");  

		jCBSelectQueryField.addItem("ϵ��");

		jCBSelectQueryField.addItemListener(new ItemListener() {//�������¼�����  

            public void itemStateChanged(ItemEvent event) {  

                switch (event.getStateChange()) {  

                case ItemEvent.SELECTED:  

                	SelectQueryFieldStr = (String) event.getItem();  

                    System.out.println("ѡ�У�" + SelectQueryFieldStr);  

                    break;  

                case ItemEvent.DESELECTED:  

                    System.out.println("ȡ��ѡ�У�" + event.getItem());  

                    break;  

                }  

            }  

        });

	

		studentVector = new Vector();

		titleVector = new Vector();

		

		// �����ͷ

		titleVector.add("���");

		titleVector.add("����");

		titleVector.add("ϵ��");

		//studentTableModel = new DefaultTableModel(tableTitle, 15);

		studentJTable = new JTable(studentVector, titleVector);

		studentJTable.setPreferredScrollableViewportSize(new Dimension(600,260));

		studentJScrollPane = new JScrollPane(studentJTable);

		//�ֱ�����ˮƽ�ʹ�ֱ�������Զ�����

		studentJScrollPane.setHorizontalScrollBarPolicy(                

                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		studentJScrollPane.setVerticalScrollBarPolicy(                

                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		

		//Ϊ�����Ӽ����� 

		studentJTable.addMouseListener(new MouseAdapter()

		{ 

			public void mouseClicked(MouseEvent e) 

			{ 

				int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // �����λ��

				System.out.println("mouseClicked(). row = " + row);

				Vector v = new Vector();

				v = (Vector) studentVector.get(row);



				jTFSNo.setText((String) v.get(0));// ѧ��
				jTFSDn.setText((String) v.get(1));// ѧ��
				jTFSName.setText((String) v.get(2));// ����
				jTFsalary.setText((String) v.get(3));// ѧ��

				//jTclass.setText((String) v.get(2));// �༶

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

		

		

		jP3.add(jLSelectQueryField);    //ѡ���ѯ�ֶ�

		jP3.add(jCBSelectQueryField);   //��ѯ�ֶ�

		jP3.add(jLEqual);  //=

		jP3.add(jTFQueryField);	

		jP3.add(jBQuery);

		jP3.add(jBQueryAll);

		jP3.setLayout(new FlowLayout(FlowLayout.CENTER));

		jP3.setPreferredSize(new Dimension(20,20));

		

		jP4.add(jLSNo);
		jP4.add(jTFSNo);
		
		jP4.add(jLSDn);
		jP4.add(jTFSDn);

		jP4.add(jLSName);
		jP4.add(jTFSName);
		
		jP4.add(jLSsalary);
		jP4.add(jTFsalary);

		//jP4.add(jLclass);

		//jP4.add(jTclass);

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

		//this.setTitle("��ʦ��Ϣ����");

		this.setSize(530, 500);

		//this.setLocation(150, 150);

		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

		//this.setResizable(false);

		

		dbProcess = new DbProcess();

	}



	@Override

	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("��ѯ")  

				&& !jTFQueryField.getText().isEmpty()){

				System.out.println("actionPerformed(). ��ѯ");

				String sQueryField = jTFQueryField.getText().trim();

				queryProcess(sQueryField);

				jTFQueryField.setText("");

			}else if(e.getActionCommand().equals("��ѯ���м�¼")) {

				System.out.println("actionPerformed(). ��ѯ���м�¼");

				queryAllProcess();

			}else if(e.getActionCommand().equals("����")

					&& !jTFSNo.getText().isEmpty()
					&& !jTFSDn.getText().isEmpty()

					&& !jTFSName.getText().isEmpty()
					&& !jTFsalary.getText().isEmpty()){

				System.out.println("actionPerformed(). ����");

				insertProcess();

			}else if(e.getActionCommand().equals("����")

					&& !jTFSNo.getText().isEmpty()
					&& !jTFSDn.getText().isEmpty()

					&& !jTFSName.getText().isEmpty()
					&& !jTFsalary.getText().isEmpty()){

				System.out.println("actionPerformed(). ����");

				updateProcess();

			}else if(e.getActionCommand().equals("ɾ����ǰ��¼")){

				System.out.println("actionPerformed(). ɾ����ǰ��¼");

				deleteCurrentRecordProcess();

			}else if(e.getActionCommand().equals("ɾ�����м�¼")){

				System.out.println("actionPerformed(). ɾ�����м�¼");

				deleteAllRecordsProcess();

			}

		}

	

     /*

	

	public static void main(String[] args) {

		Teachercahar getcon = new  Teachercahar();

   }

	

	

	*/

	public void queryProcess(String sQueryField)

	{

		try{

			// ������ѯ����

			String sql = "select t.Tn, t.Tname, d.Dname from teacher t,departments d where t.Dn=d.Dn AND ";

			String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);

		

//			if(queryFieldStr.equals("id")){//int sAge.
//
//				sql = sql + queryFieldStr;
//
//				sql = sql + " = " + sQueryField;
//
//			}else{

				sql = sql + queryFieldStr;

				sql = sql + " = ";

				sql = sql + "'" + sQueryField + "';";

//			}

			

			System.out.println("queryProcess(). sql = " + sql);

	

			dbProcess.connect();

			ResultSet rs = dbProcess.executeQuery(sql);

	

			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ

			studentVector.clear();

			while(rs.next()){

				Vector v = new Vector();

				v.add(rs.getString("t.Tn"));

				v.add(rs.getString("t.Tname"));

				v.add(rs.getString("d.Dname"));

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

	

	public void queryAllProcess()

	{

		try{

			// ������ѯ����

			//String sql = "select * from teacher;";
			String sql = "select t.Tn, t.Tname, d.Dname from teacher t,departments d where t.Dn=d.Dn; ";

			System.out.println("queryAllProcess(). sql = " + sql);

	

			dbProcess.connect();

			ResultSet rs = dbProcess.executeQuery(sql);



			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ

			studentVector.clear();

			while(rs.next()){

				Vector v = new Vector();

				v.add(rs.getString("t.Tn"));

				v.add(rs.getString("t.Tname"));

				v.add(rs.getString("d.Dname"));

				studentVector.add(v);

			}

			

			studentJTable.updateUI();



			dbProcess.disconnect();

		}catch(SQLException sqle){

			System.out.println("sqle = " + sqle);

			JOptionPane.showMessageDialog(null,

				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);

		}

	}

	

	public void insertProcess()

	{

		String id = jTFSNo.getText().trim();
		String Dn = jTFSDn.getText().trim();
		String name = jTFSName.getText().trim();
		String salary = jTFsalary.getText().trim();
		//String department = jTclass.getText().trim();

		// ������������

		String sql = "insert into teacher  values('";

		sql = sql + id + "','";
		sql = sql + Dn + "','";
		sql = sql + name + "','";
		sql = sql + salary + "','";
		sql = sql + "88888888" + "');";

		



		System.out.println("insertProcess(). sql = " + sql);

		try{

			if (dbProcess.executeUpdate(sql) < 1) {

				System.out.println("insertProcess(). insert database failed.");

			}

		}catch(Exception e){

			System.out.println("e = " + e);

			JOptionPane.showMessageDialog(null,

				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);

		}

		queryAllProcess();

	}



	public void updateProcess()

	{

		String sid = jTFSNo.getText().trim();
		String sDn = jTFSDn.getText().trim();
		String sname = jTFSName.getText().trim();
		String ssalary = jTFsalary.getText().trim();

		//String sClass = jTclass.getText().trim();

		// ������������

		String sql = "update teacher set Dn = '";

		sql = sql + sDn + "', Tname = '";
		sql = sql + sname + "', salary = ";
		sql = sql + ssalary ;

		sql = sql + " where Tn = '"+sid + "';";

		System.out.println("updateProcess(). sql = " + sql);

		try{

			if (dbProcess.executeUpdate(sql) < 1) {

				System.out.println("updateProcess(). update database failed.");

			}

		}catch(Exception e){

			System.out.println("e = " + e);

			JOptionPane.showMessageDialog(null,

				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);

		}

		queryAllProcess();

	}



	public void deleteCurrentRecordProcess()

	{

		String sNo = jTFSNo.getText().trim();

		

		// ����ɾ������

		String sql = "delete from teacher where Tn = '" + sNo + "';";

		System.out.println("deleteCurrentRecordProcess(). sql = " + sql);

		try{

			if (dbProcess.executeUpdate(sql) < 1) {

				System.out.println("deleteCurrentRecordProcess(). delete database failed.");

			}

		}catch(Exception e){

			System.out.println("e = " + e);

			JOptionPane.showMessageDialog(null,

				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);

		}

		queryAllProcess();

	}



	public void deleteAllRecordsProcess()

	{

		// ����ɾ������

		String sql = "delete from teacher;";

		System.out.println("deleteAllRecordsProcess(). sql = " + sql);

		try{

			if (dbProcess.executeUpdate(sql) < 1) {

				System.out.println("deleteAllRecordsProcess(). delete database failed.");

			}

		}catch(Exception e){

			System.out.println("e = " + e);

			JOptionPane.showMessageDialog(null,

				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);

		}

		queryAllProcess();

	}

	

	public String jCBSelectQueryFieldTransfer(String InputStr)

	{

		String outputStr = "";

		System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);

		

		if(InputStr.equals("���")){

			outputStr = "t.Tn";

		}else if(InputStr.equals("����")){

			outputStr = "t.Tname";

		}else if(InputStr.equals("ϵ��")){

			outputStr = "d.Dname";

		}

		System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);

		return outputStr;

	}

}
