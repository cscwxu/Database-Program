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



public class couse_result extends Panel implements ActionListener {

	// �������

	JLabel jLStudentInfoTable = null;//ѧ����Ϣ��

	JLabel jLSelectQueryField = null;//ѡ���ѯ�ֶ�

	JLabel jLEqual = null;//=

	JLabel jLsno = null;//ѧ��

	JLabel jLsname = null;//����

	JLabel jLcno = null;//�Ա�

	JLabel jLcname = null;//����

	JLabel jLcoom = null;//רҵ

	//JLabel jLteme = null;//סַ
	
	JLabel jLcji = null;//ѧ��

	JLabel jLtea = null;//�ο���ʦ



	JTextField jTFQueryField = null;//��ѯ�ֶ�

	JTextField jTFsno = null;//ѧ��

	JTextField jTFsname = null;//����

	JTextField jTFcno = null;//�Ա�

	JTextField jTFcname = null;//����

	JTextField jTFcoom = null;//רҵ

	//JTextField jTFtime = null;//סַ

	JTextField jTFcji = null;//ѧ��

	JTextField jTFtea = null;//�ο���ʦ

	

	JButton jBQuery = null;//��ѯ

	JButton jBQueryAll = null;//��ѯ���м�¼

	JButton jBInsert = null;//����

	JButton jBUpdate = null;//����

	JButton jBDeleteCurrentRecord = null;//ɾ����ǰ��¼

	JButton jBDeleteAllRecords = null;//ɾ�����м�¼

	

	//JComboBox jCBSelectQueryField = null;

	JComboBox<String> jCBSelectQueryField = null;//��ѯ�ֶ�

	JPanel jP1, jP2,jP3,jP4,jP5,jP6,jP7 = null;

	JPanel jPTop, jPBottom = null;

	DefaultTableModel studentTableModel = null;

	JTable studentJTable = null;

	JScrollPane studentJScrollPane = null;

	Vector studentVector = null;

	Vector titleVector = null;

	

	private static DbProcess dbProcess;

	String SelectQueryFieldStr = "ѧ��";

	

	// ���캯��

	public couse_result() {

		// �������	

		jLStudentInfoTable = new JLabel("ѡ�ν��");

		jLSelectQueryField = new JLabel("ѡ���ѯ�ֶ�");

		jLEqual = new JLabel(" = ");

		jLsno = new JLabel("ѧ    ��    ");

		jLsname = new JLabel("   ��    ��   ");

		jLcno = new JLabel(" �γ̺�  ");

		jLcname = new JLabel("�γ���");

		jLcoom = new JLabel("�γ�ѧʱ");

		//jLteme = new JLabel("�Ͽ�ʱ��");

		jLcji = new JLabel("�γ�ѧ��");

		jLtea = new JLabel("�ον�ʦ");

		

		

		jTFQueryField = new JTextField(18);//��ѯ�ֶ�

		jTFsno = new JTextField(14);//ѧ��

		jTFsname = new JTextField(14);//����

		jTFcno = new JTextField(14);//�Ա�

		jTFcname = new JTextField(14);//����

		jTFcoom = new JTextField(14);//רҵ

		//jTFtime = new JTextField(14);//סַ

		jTFcji = new JTextField(14);//סַ

		jTFtea = new JTextField(14);//סַ

		

		jBQuery = new JButton("��ѯѡ�ν��");

		jBQueryAll = new JButton("��ѯ���м�¼");

		jBInsert = new JButton("����ѡ��");

		jBUpdate = new JButton("�޸�ѡ��");

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

		jCBSelectQueryField.addItem("ѧ��");  

		jCBSelectQueryField.addItem("����");  

		jCBSelectQueryField.addItem("�γ̺�");

		jCBSelectQueryField.addItem("�γ���");

		jCBSelectQueryField.addItem("��ʱ");

		jCBSelectQueryField.addItem("ѧ��");

		jCBSelectQueryField.addItem("�ον�ʦ");

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

		titleVector.add("ѧ  ��");

		titleVector.add("��  ��");

		titleVector.add("�γ̺�");

		titleVector.add("�γ���");

		titleVector.add("ѧʱ");

		titleVector.add("ѧ��");

		titleVector.add("�ον�ʦ");

		//studentTableModel = new DefaultTableModel(tableTitle, 15);

		studentJTable = new JTable(studentVector, titleVector);

		studentJTable.setPreferredScrollableViewportSize(new Dimension(600,200));

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



				jTFsno.setText((String) v.get(0));// ѧ��

				jTFsname.setText((String) v.get(1));// ����

				jTFcno.setText((String) v.get(2));// �Ա�

				jTFcname.setText((String) v.get(3));// ����

				jTFcoom.setText((String) v.get(4));// רҵ

				//jTFtime.setText((String) v.get(5));// סַ

				jTFcji.setText((String) v.get(5));// סַ

				jTFtea.setText((String) v.get(6));// סַ

			}

		});





		jP1 = new JPanel();

		jP2 = new JPanel();

		jP3 = new JPanel();

		jP4 = new JPanel();

		jP5 = new JPanel();

		jP6 = new JPanel();

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

		

		jP4.add(jLsno);

		jP4.add(jTFsno);

		jP4.add(jLsname);

		jP4.add(jTFsname);

		jP4.add(jLcno);

		jP4.add(jTFcno);

		jP4.setLayout(new FlowLayout(FlowLayout.CENTER));

		jP4.setPreferredSize(new Dimension(20,20));

	

		jP5.add(jLcname);

		jP5.add(jTFcname);

		jP5.add(jLcoom);

		jP5.add(jTFcoom);

		//jP5.add(jLteme);

		//jP5.add(jTFtime);

		jP5.setLayout(new FlowLayout(FlowLayout.CENTER));

		jP5.setPreferredSize(new Dimension(20,20));

		

		jP6.add(jLcji);

		jP6.add(jTFcji);

		jP6.add(jLtea);

		jP6.add(jTFtea);

		jP6.setLayout(new FlowLayout(FlowLayout.CENTER));

		jP6.setPreferredSize(new Dimension(20,20));

		

		jP7.add(jBInsert);

		jP7.add(jBUpdate);

		jP7.add(jBDeleteCurrentRecord);

		jP7.add(jBDeleteAllRecords);

		jP7.setLayout(new FlowLayout(FlowLayout.CENTER));

		jP7.setPreferredSize(new Dimension(20,20));

		

		jPTop.add(jP1);

		jPTop.add(jP2);

		

		jPBottom.setLayout(new GridLayout(5, 1));

		jPBottom.add(jP3);

		jPBottom.add(jP4);

		jPBottom.add(jP5);

		jPBottom.add(jP6);

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

		if(e.getActionCommand().equals("��ѯѡ�ν��")  

			&& !jTFQueryField.getText().isEmpty()){

			System.out.println("actionPerformed(). ��ѯ");

			String sQueryField = jTFQueryField.getText().trim();

			queryProcess(sQueryField);

			jTFQueryField.setText("");

		}else if(e.getActionCommand().equals("��ѯ���м�¼")) {

			System.out.println("actionPerformed(). ��ѯ���м�¼");

			queryAllProcess();

		}else if(e.getActionCommand().equals("����ѡ��")

				&& !jTFsno.getText().isEmpty()

				&& !jTFsname.getText().isEmpty()

				&& !jTFcno.getText().isEmpty()

				&& !jTFcname.getText().isEmpty()

				&& !jTFcoom.getText().isEmpty()

				&& !jTFcji.getText().isEmpty()

				&& !jTFtea.getText().isEmpty()){

			System.out.println("actionPerformed(). ����ѡ��");

			insertProcess();

		}else if(e.getActionCommand().equals("�޸�ѡ��")

				&& !jTFsno.getText().isEmpty()

				&& !jTFsname.getText().isEmpty()

				&& !jTFcno.getText().isEmpty()

				&& !jTFcname.getText().isEmpty()

				&& !jTFcoom.getText().isEmpty()

				&& !jTFcji.getText().isEmpty()

				&& !jTFtea.getText().isEmpty()){

			System.out.println("actionPerformed(). �޸�ѡ��");

			updateProcess();

		}else if(e.getActionCommand().equals("ɾ����ǰ��¼")){

			System.out.println("actionPerformed(). ɾ����ǰ��¼");

			deleteCurrentRecordProcess();

		}else if(e.getActionCommand().equals("ɾ�����м�¼")){

			System.out.println("actionPerformed(). ɾ�����м�¼");

			deleteAllRecordsProcess();

		}

	}

	

	public static void main(String[] args) {

        // TODO Auto-generated method stub

		couse_result getcon = new  couse_result();

    }

	

	public void queryProcess(String sQueryField)

	{

		try{

			// ������ѯ����

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

	

			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ

			studentVector.clear();

			while(rs.next()){

				Vector v = new Vector();

				v.add(rs.getString("SC.Sn"));

				v.add(rs.getString("s.Sname"));

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

			String sql = "select SC.Sn, s.Sname, c.Cn, c.Cname, c.Chours, c.Credit, t.Tname from student s,SC,course c,teacher t where s.Sn=SC.Sn  AND SC.Cn=c.Cn AND C.Tn=t.Tn;";

			System.out.println("queryAllProcess(). sql = " + sql);

	

			dbProcess.connect();

			ResultSet rs = dbProcess.executeQuery(sql);



			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ

			studentVector.clear();

			while(rs.next()){

				Vector v = new Vector();

				v.add(rs.getString("SC.Sn"));

				v.add(rs.getString("s.Sname"));

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

				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);

		}

	}

	

	public void insertProcess()

	{

		String sno = jTFsno.getText().trim();

		String sname = jTFsname.getText().trim();

		String cno = jTFcno.getText().trim();

		String cname = jTFcname.getText().trim();

		String croom = jTFcoom.getText().trim();


		String cji = jTFcji.getText().trim();

		String tea = jTFtea.getText().trim();

		

		// ������������

		String sql = "insert into change_couse values('";

		sql = sql + sno + "','";

		sql = sql + sname + "','";

		sql = sql + cno + "','";

		sql = sql + cname + "','";

		sql = sql + croom + "','";


		sql = sql + cji + "','";

		sql = sql + tea + "');";



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

		String sno = jTFsno.getText().trim();

		String sname = jTFsname.getText().trim();

		String cno = jLcno.getText().trim();

		String cname = jTFcname.getText().trim();

		String croom = jTFcoom.getText().trim();

		String cji = jTFcji.getText().trim();

		String tea = jTFtea.getText().trim();

		

		// ������������

		String sql = "update change_couse set sname = '";

		sql = sql + sname + "', cno = '";

		sql = sql + cno + "', cname = '";

		sql = sql + cname + "', croom = '";

		sql = sql + croom + "', time = '";


		sql = sql + cji + "', teacher = '";

		sql = sql + tea + "'";

		sql = sql + " WHERE sNo = '" + sno + "';";

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

		String sno = jTFsno.getText().trim();

		

		// ����ɾ������

		String sql = "delete from sn where sn = '" + sno + "';";

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

		String sql = "delete from change_couse;";

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

		

		if(InputStr.equals("ѧ��")){

			outputStr = "SC.Sn";

		}else if(InputStr.equals("����")){

			outputStr = "s.Sname";

		}else if(InputStr.equals("�γ̺�")){

			outputStr = "c.Cn";

		}else if(InputStr.equals("�γ���")){

			outputStr = "c.Cname";

		}else if(InputStr.equals("ѧʱ")){

			outputStr = "c.Chours";

		}else if(InputStr.equals("ѧ��")){

			outputStr = "c.Credit";

		}else if(InputStr.equals("��ʦ")){

			outputStr = "t.Tname";

		}

		System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);

		return outputStr;

	}

}


