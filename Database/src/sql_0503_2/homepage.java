package sql_0503_2;

import javax.swing.*;

import javax.swing.border.Border;



import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;

import java.awt.event.ItemListener;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Vector;



public class homepage extends JFrame implements ActionListener{

	Panel cardPanle=new Panel();

	Panel controlpaPanlel=new Panel();

	Button button1,button2;

	CardLayout cardLayout=new CardLayout();

	public homepage() {

		setSize(800,600);

		setVisible(true);

		this.setLocation(280, 80);

		//��ӹر�ʱ�¼�������

		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				homepage.this.dispose();

			}

		});

		cardPanle.setLayout(cardLayout);

		//cardPanle.add(new Label("��һ������"));

		//cardPanle.add(new Label("��һ������"));

		//cardPanle.add();

		

		//��ť����

		button1=new Button("ѧ����Ϣ");

		button1.setBounds(20, 20, 30, 30);

		button2=new Button("�ɼ�");

		

		//��ťע�����

		button1.addActionListener(this);

		button2.addActionListener(this);

		

		//����ť��ӵ�controlpaPanlel������

		controlpaPanlel.add(button1);

		controlpaPanlel.add(button2);

		

		//��cardpanle�����ڴ��ڲ����м䣬Ĭ�ϱ߽粼��

		this.add(cardPanle,BorderLayout.CENTER);

		this.add(controlpaPanlel,BorderLayout.WEST);

	}

	//��ť�ļ���������

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==button1)

		{

			cardLayout.show(new DatabaseCourseDesign(), "ѧ����Ϣ");

		}

		

		if(e.getSource()==button2)

		{

			cardLayout.last(cardPanle);

		}

	}

	public static void main(String args[]) {

		homepage s1=new homepage();

	}

	

}
