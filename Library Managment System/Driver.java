import java.util.*;
import POJO.Student;
import UserDefinedExceptions.InvalidLoginIdException;
import UserDefinedExceptions.KeyNotFoundException;
import POJO.Book;
public class Driver {
    
    public static void main(String[] args) {
        HashSet<Student> StudentData = new HashSet<>();
        StudentData.add(new Student("name1", "101"));
        StudentData.add(new Student("name2", "102"));
        StudentData.add(new Student("name3", "103"));
        StudentData.add(new Student("name4", "104"));
        StudentData.add(new Student("name5", "105"));
        StudentData.add(new Student("name6", "106"));
        StudentData.add(new Student("name7", "107"));

        LibraryManagmentSystem libraryManagmentSystem = new LibraryManagmentSystem(StudentData);
        
        libraryManagmentSystem.AddBook("001","A", "B", 1, 10);
        libraryManagmentSystem.AddBook("002","C", "D", 2, 100);
        libraryManagmentSystem.AddBook("003","E", "F", 3, 8);
        libraryManagmentSystem.AddBook("004","G", "H", 4, 7);
        libraryManagmentSystem.AddBook("005","I", "J", 5, 60);
        libraryManagmentSystem.AddBook("006","K", "L", 6, 5);

        ArrayList<Map.Entry<String,Book>> SortedList = libraryManagmentSystem.SortByCount();
       
        System.out.println(SortedList);
        
        Book b=null;
        
        
        try{b=libraryManagmentSystem.getBook("006");
            System.out.println(b.toString());
        }
        catch(KeyNotFoundException e){
            e.printStackTrace();
        }

           libraryManagmentSystem.Login("101");
           libraryManagmentSystem.Login("102");
           libraryManagmentSystem.Login("103");
           libraryManagmentSystem.Login("108");
    
        
    

    libraryManagmentSystem.PrintRegister();

    libraryManagmentSystem.WriteBookDataAsFile();
    
    libraryManagmentSystem.printBookNameStartsWith("A");

    

    libraryManagmentSystem.PlaceOrder("101", "001");
    libraryManagmentSystem.PlaceOrder("102","001");
    libraryManagmentSystem.PlaceOrder("102","8");
    libraryManagmentSystem.PlaceOrder("102", "002");

    libraryManagmentSystem.PrintOrderQueue();
    
    }
}
