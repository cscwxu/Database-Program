package sql_0503_2;

import javax.swing.*;

import useless.tea_own_set;

import java.awt.*;

public class Tea_CardEmploy extends Panel{

	

	CardLayout c;

    //��ѯ��

	Tea_TeacherInformation selE;

	//��ӱ�

	Tea_StudentScore addE;

	

	Man_TeacherInformation teaE;

	

	Tea_Course kaikeE;

	

	tea_own_set ownE;

	

    //�޸�Ա����Ϣ��

	/*

    ReviseEmploy revE;

    

    //ɾ��Ա�����

    DelEmploy delE;

    

    //����Ա����Ϣ

    AllEmploy allE;

    

    //���±䶯��Ϣ

    Examine exaE;

    

    //��ʷ��¼����

    History His;

    */

    public Tea_CardEmploy(String id)

    {

    	//��ѯԱ����

    	selE = new Tea_TeacherInformation(id);

		

		//���Ա����

		addE = new Tea_StudentScore(id);

		

    	//teaE = new Teachercahar();

    	

    	kaikeE =new Tea_Course(id);

    	

    	//ownE =new  tea_own_set();

    	//�޸�Ա����Ϣ

		/*

    	revE = new ReviseEmploy();

    	

        //ɾ��Ա�����

    	

    	

        //����Ա����Ϣ

    	allE =new AllEmploy();

    	

        //���±䶯��Ϣ

        exaE = new Examine();

        //��ʷ��¼����

        His = new History();

        */

        

        JPanel jp = new JPanel();

        

    	//����cardemploy���  Ϊ��Ƭ����

        //�Ѹ��������뵽C�Ŀ�Ƭ������

    	c = new CardLayout();

    	this.setLayout(c);

    	this.add(selE,"1");

    	this.add(addE,"2");

    	this.add(kaikeE,"4");

    	//this.add(teaE,"3");

    	//this.add(ownE,"5");

    	//this.add(kaikeE,"5");

    	/*

    	this.add(exaE,"6");

    	this.add(His,"7");

    	*/

    }

}
