package useless;

import java.awt.BorderLayout;

import java.awt.Color;

import java.awt.Font;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;



import javax.swing.*;

import sql_0503_2.Man_StudentInformation;
import sql_0503_2.Man_TeacherInformation;
import sql_0503_2.Man_StudentScore;



public class family extends  JFrame{

	public family() {

		 Font font=new Font("΢���ź�",0,18);

		 Font font1=new Font("΢���ź�",1,13);

		JMenuBar jMenuBar=new JMenuBar();

		this.setJMenuBar(jMenuBar);

		JMenu menu1=new JMenu("ѧ����Ϣ��");

		

		 menu1.setFont(font);

	    //menu1.setFont(getFont());

		// menu1.setBackground("blue");

		jMenuBar.add(menu1);

		JMenu menu2=new JMenu("��ʦ��Ϣ��");

		menu2.setFont(font);

		jMenuBar.add(menu2);

		JMenu menu3=new JMenu("�ɼ���Ϣ��");

		menu3.setFont(font);

		jMenuBar.add(menu3);

		JMenu menu4=new JMenu("�γ���Ϣ��");

		menu4.setFont(font);

		jMenuBar.add(menu4);

		JMenu menu5=new JMenu("ѡ�ν����Ϣ��");

		menu5.setFont(font);

		jMenuBar.add(menu5);

		JMenuItem jMenuItem1=new JMenuItem("����");

		jMenuItem1.setFont(font1);

		JMenuItem jMenuItem1_1=new JMenuItem("�ر�");

		jMenuItem1_1.setFont(font1);

		JMenuItem jMenuItem2=new JMenuItem("����");

		JMenuItem jMenuItem2_1=new JMenuItem("�ر�");

		JMenuItem jMenuItem3=new JMenuItem("����");

		JMenuItem jMenuItem3_1=new JMenuItem("�ر�");

		JMenuItem jMenuItem4=new JMenuItem("����");

		JMenuItem jMenuItem4_1=new JMenuItem("�ر�");

		JMenuItem jMenuItem5=new JMenuItem("����");

		JMenuItem jMenuItem5_1=new JMenuItem("�ر�");

		JPanel jP1, jP2;

		

		//�����¼�

		//DatabaseCourseDesign d1=new DatabaseCourseDesign();

		jMenuItem1.addActionListener(new ActionListener() {

			

			@Override

			public void actionPerformed(ActionEvent e) {

				new Man_StudentInformation();

				

				

			}

		});

		

		//��ʦ��Ϣ���¼�����

		jMenuItem2.addActionListener(new ActionListener() {

			

			@Override

			public void actionPerformed(ActionEvent e) {

				new Man_TeacherInformation();

				

				

			}

		});

		

		//�ɼ�����

		jMenuItem3.addActionListener(new ActionListener() {

			

			@Override

			public void actionPerformed(ActionEvent e) {

				new Man_StudentScore();

				

				

			}

		});

		

		//��ѯ����ɼ�����

		jMenuItem5.addActionListener(new ActionListener() {

					

			@Override

			public void actionPerformed(ActionEvent e) {

					new course_select();

				

					}

		});

		jMenuItem1_1.addActionListener(new ActionListener() {

			

			@Override

			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				System.exit(0);

				

			}

		});

		jMenuItem1_1.addActionListener(new ActionListener() {

			

			@Override

			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				System.exit(0);

				

			}

		});

		jMenuItem2_1.addActionListener(new ActionListener() {

	

			@Override

			public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub

				System.exit(0);

		

			}

		});

		jMenuItem3_1.addActionListener(new ActionListener() {

	

			@Override

			public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub

				System.exit(0);

		

			}

		});

		jMenuItem4_1.addActionListener(new ActionListener() {

	

			@Override

			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				System.exit(0);

		

			}

		});

		jMenuItem5_1.addActionListener(new ActionListener() {

	

			@Override

			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				System.exit(0);

		

			}

		});

		menu1.add(jMenuItem1);

		menu1.add(jMenuItem1_1);

		menu2.add(jMenuItem2);

		menu2.add(jMenuItem2_1);

		menu3.add(jMenuItem3);

		menu3.add(jMenuItem3_1);

		menu4.add(jMenuItem4);

		menu4.add(jMenuItem4_1);

		menu5.add(jMenuItem5);

		menu5.add(jMenuItem5_1);

		//jP1 = new JPanel();

		//jP2 = new JPanel();

		

		

		//jP1.add(jLStudentInfoTable,BorderLayout.SOUTH);

		//jP2.add(studentJScrollPane);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setTitle("��ӭʹ�ý�ѧ����ϵͳ");

		this.setSize(600, 500);

		this.setLocation(300, 150);

		this.setVisible(true);

	}

	//public static void main (String args[]) {

		

	//	new family();

		//this.setVisible(true);

	//}

	

}