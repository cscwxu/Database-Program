package sql_0503_2;

import java.awt.*;

import javax.swing.*;

import javax.swing.event.TreeSelectionEvent;

import javax.swing.event.TreeSelectionListener;

import javax.swing.tree.*;

import java.awt.event.*;



public class Tea_Manger extends JFrame implements TreeSelectionListener {

	JPanel jp;

	JSplitPane js;

	JScrollPane jsp;

	JTree tree;

	DefaultMutableTreeNode root, t1, t2, t1_1, t1_2, t1_3, t1_4, t1_5, t2_1,

			t2_2,t2_3;

	Tea_CardEmploy ae;

	

	public static void main(String[] args) {

		Tea_Manger stu_M = new Tea_Manger("001");

	}

	

	public Tea_Manger(String id) {

		// �����ĸ�����㸳ֵ

		root = new DefaultMutableTreeNode("�������ϵͳ��������ʦ");



		t1 = new DefaultMutableTreeNode("ʦ����Ϣ����");

		t1_1 = new DefaultMutableTreeNode("�鿴��ʦ��Ϣ");

		t1_2 = new DefaultMutableTreeNode("ѧ���ɼ�����");

		//t1_3 = new DefaultMutableTreeNode("��ʦ��Ϣ����");

		//t1_4 = new DefaultMutableTreeNode("ɾ��Ա������");

		//t1_5 = new DefaultMutableTreeNode("��ѯȫ��Ա��");



		t2 = new DefaultMutableTreeNode("�γ���Ϣ����");

		t2_1 = new DefaultMutableTreeNode("�鿴���пγ�");

		//t2_2 = new DefaultMutableTreeNode("���ÿγ�");

		//t2_3 = new DefaultMutableTreeNode("�鿴��ѡ�γ�");

		



		t1.add(t1_1);

		t1.add(t1_2);

		//t1.add(t1_3);

		//t1.add(t1_4);

		//t1.add(t1_5);



		t2.add(t2_1);

		//t2.add(t2_2);



		root.add(t1);

		root.add(t2);



		tree = new JTree(root);

		// �������м���



		tree.addTreeSelectionListener(this);



		// ʵ����CardEmploy��� ���ӵ�jsplitpane�ı�

		ae = new Tea_CardEmploy(id);



		js = new JSplitPane();
		js.setDividerLocation(170);

		js.setLeftComponent(tree);

		js.setRightComponent(ae);



		this.getContentPane().add(js);

		this.setTitle("�������������ʦ");

		this.setVisible(true);

		this.setSize(850, 620);

		this.setResizable(false);

		this.setLocation(150, 150);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}



	public void valueChanged(TreeSelectionEvent e) {



		// ��ȡ����������

		DefaultMutableTreeNode dpath = (DefaultMutableTreeNode) tree

				.getLastSelectedPathComponent();



		// ͨ�������ͬ����л���ͬ����

		if (dpath.equals(t1_1)) {

			ae.c.show(ae, "1");

		} else if (dpath.equals(t1_2)) {

			ae.c.show(ae, "2");

		} else if (dpath.equals(t2_1)) {

			ae.c.show(ae, "4");

		} 

	}



}