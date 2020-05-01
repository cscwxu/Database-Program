package pack01;

import java.io.File;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class FromDbToExcel {
    public static void main(String[] args) {
        try {
            WritableWorkbook wwb = null;
             
               // ������д���Excel������
               String fileName = "E:\\����Ͽγ����\\���ݿ����\\student_couse.xls";
               File file=new File(fileName);
               if (!file.exists()) {
                   file.createNewFile();
               }
               //��fileNameΪ�ļ���������һ��Workbook
               wwb = Workbook.createWorkbook(file);

               // ����������
               WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
               
               //��ѯ���ݿ������е�����
               List<Stu> list= StuService.getAllByDb();
               //Ҫ���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ
               Label labelId= new Label(0, 0, "ѧ��(id)");//��ʾ��
               Label labelName= new Label(1, 0, "����(name)");
               Label labelSex= new Label(2, 0, "�γ̺�(cno)");
               Label labelNum= new Label(3, 0, "�ɼ�(grade)");
               
               ws.addCell(labelId);
               ws.addCell(labelName);
               ws.addCell(labelSex);
               ws.addCell(labelNum);
               for (int i = 0; i < list.size(); i++) {
                   
                   Label labelId_i= new Label(0, i+1, list.get(i).getId()+"");
                   Label labelName_i= new Label(1, i+1, list.get(i).getName());
                   Label labelSex_i= new Label(2, i+1, list.get(i).getSex());
                   Label labelNum_i= new Label(3, i+1, list.get(i).getNum()+"");
                   ws.addCell(labelId_i);
                   ws.addCell(labelName_i);
                   ws.addCell(labelSex_i);
                   ws.addCell(labelNum_i);
               }
             
              //д���ĵ�
               wwb.write();
              // �ر�Excel����������
               System.out.println("���ݵ����ɹ�!");
               wwb.close();
             
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}