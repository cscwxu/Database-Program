package pack01;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

//ʵ��Excel�������ݿ�����࣬��ȡExcel�������е����ݣ��������ݣ���ѯ�����£�
public class StuService {
    /**
     * ��ѯ���ݿ���Student�������е�����
     */
    public static List<Stu> getAllByDb(){
        List<Stu> list = new ArrayList<Stu>();
        DBhelper db = new DBhelper();
        String sql = "select * from student";
        ResultSet rs = db.Search(sql, null);
        try {
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int num = rs.getInt("num");
                list.add(new Stu(id,name,sex,num));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    /**
     * ��ѯָ��Ŀ¼��Excel�������������
     * @param fileΪ�ļ�����·��
     * @return
     */
    public static List<Stu> getAllByExcel(String file){
        List<Stu> list = new ArrayList<Stu>();
            try {
                Workbook rwb = Workbook.getWorkbook(new File(file));
                Sheet rs = rwb.getSheet("Test Shee 1"); //����rwb.getSheet(0)
                int clos=rs.getColumns();//�õ����е���
                int rows=rs.getRows();//�õ����е���
                System.out.println("clos:"+clos+" rows:"+rows);
                for(int i=1;i<rows;i++){
                    int j=0;
                    //��һ�����������ڶ���������
                    String id=rs.getCell(j++, i).getContents();//Ĭ������߱��Ҳ��һ�� ���������j++
                    String name=rs.getCell(j++, i).getContents();
                    String sex=rs.getCell(j++, i).getContents();
                    String num=rs.getCell(j++, i).getContents();
                        
                    System.out.println("id:"+id+" name:"+name+" sex:"+sex+" num:"+num);
                    list.add(new Stu(Integer.parseInt(id), name, sex, Integer.parseInt(num)));                    
                }
            } catch (BiffException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return list;
    }
    
    /**
     * ͨ��id�ж��Ƿ����
     * @param id
     * @return
     */    
    public static boolean isExist(int id){
        try {
            DBhelper db=new DBhelper();
            ResultSet rs=db.Search("select * from student where id=?", new String[]{id+""});
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        /*List<Stu> all=getAllByDb();
        for (Stu stuEntity : all) {
            System.out.println(stuEntity.toString());
        }*/
        
        System.out.println(isExist(1));
        
    }
}