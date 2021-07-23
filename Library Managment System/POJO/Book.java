package POJO;

import java.util.Comparator;

public class Book{
    String name;
    String authorName;
    int volume;
    int count;

    public Book(String name,String authorName,int volume,int count){
        this.name=name;
        this.authorName = authorName;
        this.volume=volume;
        this.count=count;
    }

    public void setName(String name){
        this.name=name;
    }
    public void setAuthorName(String authorName){
        this.authorName=authorName;
    }
    public void setVolume(int volume){
        this.volume=volume;
    }
    public void setCount(int count){
        this.count = count;
    }

    public String getName(){
        return name;
    }
    public String getAuthorName(){
        return authorName;
    }
    public int getVolume(){
        return volume;
    }
    public int getCount(){
        return count;
    }

    @Override
    public String toString() {
        return "name:"+name+"\nAuthor name:"+authorName+"\nVolume:"+volume+"\nCount:"+count;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Book)){return false;}
        
        Book book2 = (Book) obj;
        return this.getName().equals(book2.getName()) && this.authorName.equals(book2.getAuthorName());
    }
}
