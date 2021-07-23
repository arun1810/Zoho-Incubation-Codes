package POJO;

public class Student{

    String name;
    String Id;

    public Student(String name,String Id){
        this.name = name;
        this.Id = Id;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setId(String Id){
        this.Id = Id;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return Id;
    }

    @Override
    public String toString() {
        return "name:"+name+"\nId:"+Id;

    }
}