package sql_0503_2;

import javax.swing.*;

import java.awt.*;

public class tea_CardEmploy extends Panel{

	

	CardLayout c;

    //��ѯ��

	tea_DatabaseCourseDesign selE;

	//��ӱ�

	tea_scoer addE;

	

	Teachercahar teaE;

	

	tea_course kaikeE;

	

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

    public tea_CardEmploy(String id)

    {

    	//��ѯԱ����

    	selE = new tea_DatabaseCourseDesign(id);

		

		//���Ա����

		addE = new tea_scoer(id);

		

    	//teaE = new Teachercahar();

    	

    	kaikeE =new tea_course(id);

    	

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
