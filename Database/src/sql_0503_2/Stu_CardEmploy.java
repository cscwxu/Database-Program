package sql_0503_2;

import javax.swing.*;

import java.awt.*;

public class Stu_CardEmploy extends Panel{

	

	CardLayout c;

    //��ѯ��

	Stu_Information selE;

	//��ӱ�

	Stu_ShowCourse addE;

	

	Stu_CourseSelect teaE;

	

	//teacher_couse_aet kaikeE;

	

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

    public Stu_CardEmploy(String id)

    {

    	//��ѯԱ����

    	selE = new Stu_Information(id);

		

		//���Ա����

		addE = new Stu_ShowCourse(id);

		

    	teaE = new Stu_CourseSelect(id);

    	

    	//kaikeE =new teacher_couse_aet();

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

    	this.add(addE,"3");

    	//this.add(kaikeE,"3");

    	this.add(teaE,"4");

    	//this.add(kaikeE,"5");

    	/*

    	this.add(exaE,"6");

    	this.add(His,"7");

    	*/

    }

}
