import java.util.*;
import java.util.Map.Entry;
import POJO.*;
import UserDefinedExceptions.*;
import java.io.FileOutputStream;

public class LibraryManagmentSystem {

    HashMap<String,Book> BookData = new HashMap<>(10,0.5f);//Initial capacity->10, Load factor->50% 
    HashSet<Student> StudentData;
    Stack<String> Register = new Stack<>();
    Queue<BookOrder> BookOrderQueue = new LinkedList<>();

    LibraryManagmentSystem(HashSet<Student> StudentData){
        this.StudentData = StudentData;
    }

    public void AddBook(String BookId,String name, String authorName, int volume,int count){
        BookData.put(BookId,new Book(name,authorName,volume,count));
    }

    public ArrayList<Map.Entry<String,Book>> getAllBooks(){
        ArrayList<Map.Entry<String,Book>> arr = new ArrayList<Map.Entry<String,Book>>(BookData.entrySet());
        return arr;
    }

    public ArrayList<Map.Entry<String,Book>> SortByName(){
        ArrayList<Map.Entry<String,Book>> arr = getAllBooks();
        Collections.sort(arr,
                         new Comparator<Map.Entry<String,Book>>(){
                            @Override
                            public int compare(Entry<String, Book> E1, Entry<String, Book> E2) {
                                
                                return E1.getValue().getName().compareTo(E2.getValue().getName());
                            }
                         });

        return arr;
        

    }

    public ArrayList<Map.Entry<String,Book>> SortByCount(){
        ArrayList<Map.Entry<String,Book>> arr = getAllBooks();

        arr.sort((E1,E2)->E1.getValue().getCount()-E2.getValue().getCount());                
        return arr;
    }

    public Book getBook(String Id) throws KeyNotFoundException{
        Book book=null;
        if(!BookData.containsKey(Id)){ 
            throw new KeyNotFoundException("given StudentId:"+Id+" is not found");
        }
        else{
            book=BookData.get(Id);
        }
        return book;
    }

    private Student getStudent(String StudentId) throws InvalidLoginIdException{
        Iterator<Student> iter = StudentData.iterator();
        while(iter.hasNext()){
            Student s =iter.next(); 
            if(s.getId().equals(StudentId)){
                return s;
            }
        }

        throw new InvalidLoginIdException("Given ID "+StudentId+" is Invalid");
    }

    public void Login(String StudentId) {
        try{
        if(getStudent(StudentId)!=null){
            Register.add(StudentId);
        }
        
    }
        catch(InvalidLoginIdException e){
            e.printStackTrace();
        }
    }

    public void PrintRegister(){
        Register.forEach((data)->System.out.println(data));
        
    }

    public void WriteBookDataAsFile(){
        try(FileOutputStream fileoutputstream = new FileOutputStream(System.getProperty("user.dir")+"//BookData.txt")){
            StringBuffer buffer = new StringBuffer();
            buffer.append("{");
            
            for(Map.Entry<String,Book> entry:getAllBooks()){
                buffer.append("\nkey:"+entry.getKey()+"\n");
                buffer.append(entry.getValue().toString()+"\n");
            }
            buffer.append("}");
            fileoutputstream.write(buffer.toString().getBytes());
        }
        catch(Exception e ){
            e.printStackTrace();
        }
    }

    public void PlaceOrder(String StudentId,String BookId){
        try{
            
            BookOrderQueue.add(new BookOrder(getBook(BookId),getStudent(StudentId) ));
        }
        catch(InvalidLoginIdException e){
            e.printStackTrace();
        }
        catch(KeyNotFoundException e){
            e.printStackTrace();
        }

    }

    public void PrintOrderQueue(){
        System.out.println(BookOrderQueue);
    }

    public void printBookNameStartsWith(String s){
        ArrayList<Map.Entry<String,Book>> arr = new ArrayList<Map.Entry<String,Book>>(BookData.entrySet());
       
        arr.stream()
           .filter((entry)->entry.getValue().getName().startsWith(s))
           //.forEach((element)->System.out.println(element));
           .forEach(System.out::println);
    }



    
}
