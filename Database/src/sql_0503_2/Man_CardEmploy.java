package sql_0503_2;

import javax.swing.*;

import java.awt.*;

public class Man_CardEmploy extends Panel{

	

	CardLayout c;

    //��ѯ��

	Man_StudentInformation selE;

	//��ӱ�

	Man_StudentScore addE;

	

	Man_TeacherInformation teaE;

	

	Man_TeacherCourse kaikeE;

	

	Man_StudentCourseSelect  resE;

	

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

    public Man_CardEmploy()

    {

    	//��ѯԱ����

    	selE = new Man_StudentInformation();

		

		//���Ա����

		addE = new Man_StudentScore();

		

    	teaE = new Man_TeacherInformation();

    	

    	kaikeE =new Man_TeacherCourse();

    	

    	resE  = new Man_StudentCourseSelect();

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

    	this.add(resE,"3");

    	this.add(teaE,"4");

    	this.add(kaikeE,"5");

    	/*

    	this.add(exaE,"6");

    	this.add(His,"7");

    	*/

    }

}
