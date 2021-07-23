package POJO;

public class BookOrder {
    Book book;
    Student student;

   public BookOrder(Book book,Student student){
        this.book=book;
        this.student = student;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public void setBook(Book book){
        this.book = book;
    }

    public Student getStudent(){
        return student;
    }
    
    public Book getBook(){
        return book;
    }

    @Override
    public String toString(){
        return "Book name:"+book.getName()+"\nStudent name:"+student.getName();
    }
}
