package sql_0503_2;

import javax.swing.*;

import java.awt.*;

public class Man_CardEmploy extends Panel{

	

	CardLayout c;

    //查询表

	Man_StudentInformation selE;

	//添加表

	Man_StudentScore addE;

	

	Man_TeacherInformation teaE;

	

	Man_TeacherCourse kaikeE;

	

	Man_StudentCourseSelect  resE;

	

    //修改员工信息表

	/*

    ReviseEmploy revE;

    

    //删除员工表格

    DelEmploy delE;

    

    //所有员工信息

    AllEmploy allE;

    

    //人事变动信息

    Examine exaE;

    

    //历史记录界面

    History His;

    */

    public Man_CardEmploy()

    {

    	//查询员工表

    	selE = new Man_StudentInformation();

		

		//添加员工表

		addE = new Man_StudentScore();

		

    	teaE = new Man_TeacherInformation();

    	

    	kaikeE =new Man_TeacherCourse();

    	

    	resE  = new Man_StudentCourseSelect();

    	//修改员工信息

		/*

    	revE = new ReviseEmploy();

    	

        //删除员工表格

    	

    	

        //所有员工信息

    	allE =new AllEmploy();

    	

        //人事变动信息

        exaE = new Examine();

        //历史记录界面

        His = new History();

        */

        

        JPanel jp = new JPanel();

        

    	//定义cardemploy面板  为卡片布局

        //把各个面板加入到C的卡片布局中

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
