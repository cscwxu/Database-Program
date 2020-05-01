package data;
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

public class tea_own_set extends Panel implements ActionListener {
	// �������
	JLabel jLStudentInfoTable = null;//ѧ����Ϣ��
	JLabel jLSelectQueryField = null;//ѡ���ѯ�ֶ�
	JLabel jLEqual = null;//=
	JLabel jLteacher = null;//ѧ��
	JLabel jLScourse = null;//����
	JLabel jLjidian = null;//�༶
	JLabel jLclassroom = null;//�༶
	JLabel jLwe_day = null;//�༶
	JLabel jLclasnumer = null;//�༶
	JLabel jLjiaoshi = null;//�༶

	JTextField jTteacher = null;//��ѯ�ֶ�
	JTextField jTScourse = null;//ѧ��
	JTextField jTjidian = null;//����
	JTextField jTclassroom = null;//�༶
	JTextField jTwe_day = null;//�༶
	JTextField jTclasnumer = null;//�༶
	JTextField jTFQueryField=null;
	
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
	JPanel jP1, jP2,jP3,jP4,jP5,jP6 = null;
	JPanel jPTop, jPBottom = null;
	DefaultTableModel studentTableModel = null;
	JTable studentJTable = null;
	JScrollPane studentJScrollPane = null;
	Vector studentVector = null;
	Vector titleVector = null;
	
	private static DbProcess dbProcess;
	String SelectQueryFieldStr = "ѧ��";
	
	// ���캯��
	public tea_own_set() {
		// �������	
		jLStudentInfoTable = new JLabel("��ʦ���˿γ����ñ�");
		jLSelectQueryField = new JLabel("ѡ���ѯ�ֶ�");
		jLEqual = new JLabel(" = ");
		jLteacher = new JLabel("��ʦ����	  ");
		jLScourse = new JLabel("�γ���");
		jLjidian = new JLabel("ѧ�ּ���");
		jLclassroom = new JLabel("�Ͽν���");
		jLwe_day = new JLabel("�Ͽ��ܴ�");
		jLclasnumer = new JLabel("�ڴ�");
		jLjiaoshi = new JLabel("��");
		
		
		jTteacher = new JTextField(10);//��ѯ�ֶ�
		jTScourse = new JTextField(10);//ѧ��
		jTjidian = new JTextField(10);//����
		jTclassroom = new JTextField(10);//�Ա�
		jTwe_day = new JTextField(10);//�Ա�
		jTclasnumer = new JTextField(10);//�Ա�
		jTFQueryField=new JTextField(10);
		
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
		jCBSelectQueryField.addItem("��ʦ");  
		jCBSelectQueryField.addItem("�γ���");  
		jCBSelectQueryField.addItem("ѧ�ּ���");
		jCBSelectQueryField.addItem("�Ͽν���");
		jCBSelectQueryField.addItem("�Ͽ��ܴ�");
		jCBSelectQueryField.addItem("�ڴ�");
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
		titleVector.add("��ʦ");
		titleVector.add("�γ���");
		titleVector.add("ѧ�ּ���");
		titleVector.add("�Ͽν���");
		titleVector.add("�Ͽ��ܴ�");
		titleVector.add("�ڴ�");
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

				jTteacher.setText((String) v.get(0));// ѧ��
				jTScourse.setText((String) v.get(1));
				jTjidian.setText((String) v.get(2));// ����
				jTclassroom.setText((String) v.get(3));// �༶
				jTwe_day.setText((String) v.get(4));
				jTclasnumer.setText((String) v.get(5));
				
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
		
		
		//jP3.add(jLSelectQueryField);    //ѡ���ѯ�ֶ�
		//jP3.add(jCBSelectQueryField);   //��ѯ�ֶ�
		//jP3.add(jLEqual);  //=
		//jP3.add(jTFQueryField);	
		
		jP3.add(jBQueryAll);
		jP3.setLayout(new FlowLayout(FlowLayout.CENTER));
		jP3.setPreferredSize(new Dimension(20,20));
		
		
		jP4.add(jLteacher);
		//jP4.add(jTteacher);
		jP4.add(jLScourse);
		jP4.add(jTScourse);
		jP4.add(jLjidian);
		jP4.add(jTjidian);
		jP4.add(jBQuery);
		jP4.setLayout(new FlowLayout(FlowLayout.CENTER));
		jP4.setPreferredSize(new Dimension(30,30));
		jP5.add(jLclassroom);
		jP5.add(jTclassroom);
		jP5.add(jLwe_day);
		jP5.add(jTwe_day);
		jP5.add(jLclasnumer);
		jP5.add(jTclasnumer);
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
		//this.setTitle("���ҿγ����ñ�");
		this.setSize(580, 500);
		this.setLocation(150, 150);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//this.setResizable(false);
		
		
		dbProcess = new DbProcess();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("��ѯ")  
				&& !jTFQueryField.getText().isEmpty()){
				System.out.println("actionPerformed().��ѯ");
				String sQueryField = jTFQueryField.getText().trim();
				queryProcess(sQueryField);
				jTFQueryField.setText("");
			}else if(e.getActionCommand().equals("��ѯ���м�¼")) {
				System.out.println("actionPerformed(). ��ѯ���м�¼");
				queryAllProcess();
			}else if(e.getActionCommand().equals("����")
					&& !jTteacher.getText().isEmpty()
					&& !jTScourse.getText().isEmpty()
					&& !jTjidian.getText().isEmpty()
					&& !jTclassroom.getText().isEmpty()
					&& !jTwe_day.getText().isEmpty()
					&& !jTclasnumer.getText().isEmpty()){
				System.out.println("actionPerformed(). ����");
				insertProcess();
			}else if(e.getActionCommand().equals("����")
					&& !jTteacher.getText().isEmpty()
					&& !jTScourse.getText().isEmpty()
					&& !jTjidian.getText().isEmpty()
					&& !jTclassroom.getText().isEmpty()
					&& !jTwe_day.getText().isEmpty()
					&& !jTclasnumer.getText().isEmpty()){
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
		tea_own_set getcon = new  tea_own_set();
   }
   */
	
	public void queryProcess(String sQueryField)
	{
		try{
			// ������ѯ����
			String sql = "select * from teacher_set where ";
			String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);
		
			if(queryFieldStr.equals("ѧ�ּ���")){//int sAge.
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
				v.add(rs.getString("��ʦ"));
				v.add(rs.getString("�γ���"));
				v.add(rs.getString("ѧ�ּ���"));
				v.add(rs.getString("�Ͽν���"));
				v.add(rs.getString("�Ͽ��ܴ�"));
				v.add(rs.getString("�ڴ�"));
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
			String sql = "select * from teacher_set;";
			System.out.println("queryAllProcess(). sql = " + sql);
	
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);

			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			studentVector.clear();
			while(rs.next()){
				Vector v = new Vector();
				v.add(rs.getString("��ʦ"));
				v.add(rs.getString("�γ���"));
				v.add(rs.getString("ѧ�ּ���"));
				v.add(rs.getString("�Ͽν���"));
				v.add(rs.getString("�Ͽ��ܴ�"));
				v.add(rs.getString("�ڴ�"));
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
		String teacher =jLjiaoshi.getText().trim();
		String souse = jTScourse.getText().trim();
		String jisian = jTjidian.getText().trim();
		String room = jTclassroom.getText().trim();
		String we_day = jTwe_day.getText().trim();
		String num = jTclasnumer.getText().trim();
		// ������������
		String sql = "insert into teacher_set values('";
		sql = sql + teacher + "','";
		sql = sql + souse + "','";
		sql = sql + jisian + "',";
		sql = sql + room + ",'";
		sql = sql + we_day + "','";
		sql = sql + num + "');";
		

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
		String teacher = jTteacher.getText().trim();
		String souse = jTScourse.getText().trim();
		String jisian = jTjidian.getText().trim();
		String room = jTclassroom.getText().trim();
		String we_day = jTwe_day.getText().trim();
		String num = jTclasnumer.getText().trim();
		// ������������
	
		String sql = "update teacher_set values set �γ��� = '";
		sql = sql + souse + "', ѧ�ּ��� = '";
		sql = sql + jisian + "', �Ͽν���= ";
		sql = sql + room + ", �Ͽ��ܴ�= '";
		sql = sql + we_day + "', �ڴ� = '";
		sql = sql + num + "'";
		sql =sql +"WHERE ��ʦ ='"+teacher+"';";
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
		String teacher = jTteacher.getText().trim();
		
		// ����ɾ������
		String sql = "delete from teacher_set where ��ʦ = '" + teacher + "';";
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
		String sql = "delete from teacher_set;";
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
		
		if(InputStr.equals("��ʦ")){
			outputStr = "��ʦ";
		}else if(InputStr.equals("�γ���")){
			outputStr = "�γ���";
		}else if(InputStr.equals("ѧ�ּ���")){
			outputStr = "ѧ�ּ���";
		}
		else if(InputStr.equals("�Ͽν���")){
			outputStr = "�Ͽν���";
		}
		else if(InputStr.equals("�Ͽ��ܴ�")){
			outputStr = "�Ͽ��ܴ�";
		}
		else if(InputStr.equals("�ڴ�")){
			outputStr = "�ڴ�";
		}
		System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
		return outputStr;
	}
}
