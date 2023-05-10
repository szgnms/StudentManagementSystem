public class Runner extends Methods   {

    /*
Proje:Student Management System
   -1-Herhangi bir eğitim kurumu için öğrenci yönetim uygulaması geliştiriniz.
   -2-Kullanıcı
             -öğrenci kayıt
             -öğrenci veya öğrencileri görüntüleme
             -id ile öğrenci güncelleme
             -id ile öğrenci silme
     işlemlerini yapabilmeli.
   -3-öğrenci:id,name,lastname,city,age özelliklerine sahiptir.
*/
    public static void main(String[] args) {
      StudentService service=new StudentService();
      service.createStudentTable();
        start();

    }




    }
