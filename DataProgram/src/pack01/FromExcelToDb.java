package pack01;
import java.util.List;

public class FromExcelToDb {
    public static void main(String[] args) {
        //�õ���������е�����
        List<Stu> listExcel=StuService.getAllByExcel("E:\\����Ͽγ����\\���ݿ����\\student_couse.xls");
        /*//�õ����ݿ�������е�����
        List<Stu> listDb=StuService.getAllByDb();*/
        
        DBhelper db=new DBhelper();
        
        for (Stu stuEntity : listExcel) {
            int id=stuEntity.getId();
            if (!StuService.isExist(id)) {
                //�����ھ����
                String sql="insert into student (name,sex,num) values(?,?,?)";
                String[] str=new String[]{stuEntity.getName(),stuEntity.getSex(),stuEntity.getNum()+""};
                db.AddU(sql, str);
            }else {
                //���ھ͸���
                String sql="update student set name=?,sex=?,num=? where id=?";
                String[] str=new String[]{stuEntity.getName(),stuEntity.getSex(),stuEntity.getNum()+"",id+""};
                db.AddU(sql, str);
            }
        }
        System.out.println("���ݸ��³ɹ���");
    }
}