package data;
//��ʦ��¼
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

class teacher_login extends JFrame implements ActionListener  {
	// �������
	String  stu_num="��";
	String stu_pwd="123";
	JLabel jLuser = null;//�û�
	JLabel jLpossword = null;//����	
	JLabel jLloging=null;
	
	JTextField jTFuser = null;//����
	JTextField jTFpossword = null;//�Ա�
	
	JButton logings = null;//��¼
	JButton register = null;//��¼
	public  void clear()  
  {  
		jTFuser.setText("");  
		jTFpossword.setText("");  
  }
	
	// ���캯��
	public teacher_login() {
		// �������	
		jLloging = new JLabel("��ʦ�û���¼");
		jLloging.setForeground(Color.blue);
		jLloging.setFont(new   java.awt.Font("Dialog",   0,   25));
		
		jLuser = new JLabel("�û�:");
		jLpossword= new JLabel("����:");
		jLuser.setFont(new   java.awt.Font("Dialog",   0,   19));
		jLpossword.setFont(new   java.awt.Font("Dialog",   0,   19));
		
		jTFuser = new JTextField(10);//��ѯ�ֶ�
		jTFpossword = new JTextField(10);//ѧ��
		jTFuser.setFont(new   java.awt.Font("Dialog",   0,   19));
		jTFpossword.setFont(new   java.awt.Font("Dialog",   0,   19));
		
		logings = new JButton("��¼");
		logings.setFont(new   java.awt.Font("Dialog",   1,   16));
		register = new JButton("ע��");
		register.setFont(new   java.awt.Font("Dialog",   1,   16));

		JPanel jP1, jP2,jP3,jP4=null;
		JPanel jPTop, jPBottom = null;
		
		// ���ü���
		logings.addActionListener(this);
		jP1 = new JPanel();
		jP2 = new JPanel();
		jP3 = new JPanel();
		jP4 = new JPanel();
		
		jPTop = new JPanel();
		jPBottom = new JPanel();
		
		jP1.add(jLloging);
		
		jP2.add(jLuser);
		jP2.add(jTFuser);
		jP2.setLayout(new FlowLayout(FlowLayout.CENTER));
		jP2.setPreferredSize(new Dimension(20,20));
		
		jP3.add(jLpossword);
		jP3.add(jTFpossword);
		jP3.setLayout(new FlowLayout(FlowLayout.CENTER));
		jP3.setPreferredSize(new Dimension(20,20));
		
		jP4.add(logings);
		jP4.add(register);
	
		jP4.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		
		jPTop.setLayout(new GridLayout(4, 1));
		jPTop.add(jP1);
		
		jPTop.add(jP2);
		jPTop.add(jP3);
		//jPBottom.add(jP2);
		//jPBottom.add(jP3);
		jPTop.add(jP4);
		
		this.add("North",jP1);
		this.add("Center",jP2);
		this.add("Center",jP3);
		this.add("Center",jP4);
		
		/*
		BufferedImage img=null;
		try {
			img=ImageIO.read(new File("./img/bg.gif"));
		}catch (IOException e) {
			e.printStackTrace();
		}
		JLabel labl=new JLabel(new ImageIcon(img));
		getContentPane().add(labl);
		labl.setBounds(0, 0, img.getWidth(), img.getHeight());
      */
		this.setLayout(new GridLayout(4, 1));
		this.setTitle("���񴦵�¼");
		this.setSize(370,270);
		this.setLocation(555, 225);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		//dbProcess = new DbProcess();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(stu_num.equals(jTFuser.getText())&&stu_pwd.equals(jTFpossword.getText()))  
		        {   
					new tea_Manger();
					this.setVisible(false);;
		            
		              //�����½���  
		        }else if(jTFuser.getText().isEmpty()&&jTFpossword.getText().isEmpty())  
		        {  
		            JOptionPane.showMessageDialog(null,"�������û��������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
		        }else if(jTFuser.getText().isEmpty())  
		        {  
		            JOptionPane.showMessageDialog(null,"�������û�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
		        }else if(jTFuser.getText().isEmpty())  
		        {  
		            JOptionPane.showMessageDialog(null,"���������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
		        }else  
		        {  
		            JOptionPane.showMessageDialog(null,"�û��������������\n����������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);  
		            //��������  
		            clear();  
		        } 
				
	}
}



	//@Override
	
	//-----------------------------------------------------------------------------------------
	

	
