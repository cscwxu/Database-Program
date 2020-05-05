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

public class Tea_StudentScore extends Panel implements ActionListener {

	// �������
		String tea_id;
		
		String sNo="";
		
		String course="";
		JLabel jLStudentInfoTable = null;//ѧ����Ϣ��

		JLabel jLSelectQueryField = null;//ѡ���ѯ�ֶ�

		JLabel jLEqual = null;//=

		//JLabel jLSNo = null;//ѧ��

		//JLabel jLSName = null;//����

		//JLabel jLcourse = null;//�γ̺�
		
		//JLabel jLcourseName = null;//�γ���

		JLabel jLscoer = null;//�ɼ�
		
		

		



		JTextField jTFQueryField = null;//��ѯ�ֶ�

		//JTextField jTFSNo = null;//ѧ��

		//JTextField jTFSName = null;//����

		//JTextField jTFcourse = null;//�γ̺�
		
		//JTextField jTFcourseName = null;//�γ���

		JTextField jTFscoer = null;//�ɼ�

		

		//��������ϵ�button

		JButton jBQuery = null;//��ѯ

		JButton jBQueryAll = null;//��ѯ���м�¼

		JButton jBInsert = null;//����

		JButton jBUpdate = null;//����

		//JButton jBDeleteCurrentRecord = null;//ɾ����ǰ��¼

		//JButton jBDeleteAllRecords = null;//ɾ�����м�¼

		

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

		String SelectQueryFieldStr = "ѧ��";

		

		// ���캯��

		public Tea_StudentScore(String id) {

			// �������	
			tea_id=id;
			jLStudentInfoTable = new JLabel("�ɼ���Ϣ");

			jLSelectQueryField = new JLabel("ѡ���ѯ�ֶ�");

			jLEqual = new JLabel(" = ");

			//jLSNo = new JLabel("ѧ    ��");

			//jLSName = new JLabel("��  ��");

		    //jLcourse = new JLabel("�γ̺�");//�γ̺�
		    
		    //jLcourseName = new JLabel("�γ���");//�γ���

			jLscoer = new JLabel("��  ��");;//�ɼ�

			

			jTFQueryField = new JTextField(10);//��ѯ�ֶ�

			//jTFSNo = new JTextField(18);//ѧ��

			//jTFSName = new JTextField(18);//����

			//jTFcourse = new JTextField(18);//�γ̺�
			
			//jTFcourseName = new JTextField(18);//�γ���

		    jTFscoer = new JTextField(18);//�ɼ�

			

			jBQuery = new JButton("��ѯ");

			jBQueryAll = new JButton("��ѯ���м�¼");

			//jBInsert = new JButton("����");

			jBUpdate = new JButton("����");

			//jBDeleteCurrentRecord = new JButton("ɾ����ǰ��¼");

			//jBDeleteAllRecords = new JButton("ɾ�����м�¼");

			// ���ü���

			jBQuery.addActionListener(this);

			jBQueryAll.addActionListener(this);

			//jBInsert.addActionListener(this);

			jBUpdate.addActionListener(this);

			//jBDeleteCurrentRecord.addActionListener(this);

			//jBDeleteAllRecords.addActionListener(this);

			

			jCBSelectQueryField = new JComboBox<String>();//��ѯ�ֶ�

			jCBSelectQueryField.addItem("ѧ��");  

			jCBSelectQueryField.addItem("����");  

			jCBSelectQueryField.addItem("�γ̺�");
			
			jCBSelectQueryField.addItem("�γ���");

			jCBSelectQueryField.addItem("�ɼ�");

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

			titleVector.add("ѧ��");

			titleVector.add("����");

			titleVector.add("�γ̺�");
			
			titleVector.add("�γ���");

			titleVector.add("�ɼ�");

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



					//jTFSNo.setText((String) v.get(0));// ѧ��
					sNo=(String) v.get(0);
					course=(String) v.get(2);

					//jTFSName.setText((String) v.get(1));// ����

					//jTFcourse.setText((String) v.get(2));// �γ̺�
					
					//jTFcourseName.setText((String) v.get(3));// �γ���

					jTFscoer.setText((String) v.get(4));// �ɼ���

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

			

			//jP4.add(jLSNo);
			//jP4.add(jTFSNo);
			
			//jP4.add(jLSName);
			//jP4.add(jTFSName);
			
			//jP4.add(jLcourse);
			//jP4.add(jTFcourse);
			
			//jP4.add(jLcourseName);
			//jP4.add(jTFcourseName);
			
			jP4.add(jLscoer);
			jP4.add(jTFscoer);

			

			jP4.setLayout(new FlowLayout(FlowLayout.CENTER));

			jP4.setPreferredSize(new Dimension(30,30));

			

			

			//jP5.add(jBInsert);

			jP5.add(jBUpdate);

			//jP5.add(jBDeleteCurrentRecord);

			//jP5.add(jBDeleteAllRecords);

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

			//this.setTitle("�ɼ���Ϣ����");

			this.setSize(530, 500);

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

					System.out.println("actionPerformed(). ��ѯ");

					String sQueryField = jTFQueryField.getText().trim();

					queryProcess(sQueryField);

					jTFQueryField.setText("");

				}else if(e.getActionCommand().equals("��ѯ���м�¼")) {

					System.out.println("actionPerformed(). ��ѯ���м�¼");

					queryAllProcess();

				}else if(e.getActionCommand().equals("����")

						
						//&& !jTFcourseName.getText().isEmpty()

						&& !jTFscoer.getText().isEmpty()){

					System.out.println("actionPerformed(). ����");

					updateProcess();

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

				// ������ѯ����

				String sql = "select sc.Sn, s.Sname, sc.Cn, c.Cname, sc.Score from sc,course c,student s,teacher t where sc.Sn=s.Sn AND sc.Cn=c.Cn AND t.tn=c.tn AND t.tn="+tea_id+" AND ";

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

		

				// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ

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

				//String sql = "select * from score;";
				String sql = "select sc.Sn, s.Sname, sc.Cn, c.Cname, sc.Score from sc,course c,student s,teacher t where sc.Sn=s.Sn AND sc.Cn=c.Cn AND t.tn=c.tn AND t.tn="+tea_id+";";

				System.out.println("queryAllProcess(). sql = " + sql);

		

				dbProcess.connect();

				ResultSet rs = dbProcess.executeQuery(sql);



				// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ

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

					"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);

			}

		}

		

		



		public void updateProcess()

		{
			if(sNo.equals("")||course.equals("")) {
				JOptionPane.showMessageDialog(null,
						"δѡ��Ҫ�޸ĵ���","����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			//String sNo = jTFSNo.getText().trim();

			//String sName = jTFSName.getText().trim();

			//String course= jTFcourse.getText().trim();

			String score= jTFscoer.getText().trim();

			// ������������

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

					"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);

			}

			queryAllProcess();
			
			JOptionPane.showMessageDialog(null,
					"�ɼ����³ɹ�","��ȷ",JOptionPane.INFORMATION_MESSAGE);
			sNo="";
			course="";
		}



		

		public String jCBSelectQueryFieldTransfer(String InputStr)

		{

			String outputStr = "";

			System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);

			

			if(InputStr.equals("ѧ��")){

				outputStr = "sc.Sn";

			}else if(InputStr.equals("����")){

				outputStr = "s.Sname";

			}else if(InputStr.equals("�γ̺�")){

				outputStr = "sc.Cn";

			}else if(InputStr.equals("�γ���")){

				outputStr = "c.Cname";

			}else if(InputStr.equals("�ɼ�")){

				outputStr = "sc.score";

			}

			System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);

			return outputStr;

		}

}
