package AreaWithIf;
import java.util.Scanner;
public class Area {
    float area;
    private final float pi=(float)3.14;
    
    public static enum Shape{
        SQUARE,
        RECTANGLE,
        TRIANGLE,
        CIRCLE
    }
    
     public Area(Scanner s,int option){

        if(option==1){
            AreaOfSquare(s);
        }
        else if(option==2){
            AreaOfRectangle(s);
        }
        else if(option==3){
            AreaOfTriangle(s);
        }
        else if(option==4){
            AreaOfCircle(s);
        }
        else{
            System.out.println("Invalid option");
        }
    }

    private void AreaOfSquare(Scanner s){
        System.out.println("enter value for a side=>");
        double side = s.nextDouble();
        area = side*side;
    }

    private void AreaOfRectangle(Scanner s){
        System.out.println("enter value for length=>");
        float length = s.nextFloat();
        System.out.println("enter value for width=>");
        float width = s.nextFloat();
        area = length*width;
    }

    private void AreaOfTriangle(Scanner s){
        System.out.println("enter value for base=>");
        float base = s.nextFloat();
        System.out.println("enter value for height=>");
        float height = s.nextFloat();
        area =(float) 0.5 * base * height;
    }

    private void AreaOfCircle(Scanner s){
        System.out.println("enter value for radius=>");
        float radius = s.nextFloat();
        area = pi*radius*radius;
    }

    protected float getArea(){
        return area;
    }

}


class Main{ //Driver Class
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
       
        char choice;
        do{
            System.out.println("Enter a option from the list\n1 for Square\n2 for Rectangle\n3 for Triangle\n4 for Circle");
            int option = s.nextInt();
            Area area = new Area(s,option);
            System.out.println("Area is=>"+area.getArea());
            System.out.println("do you want to continue? y/n");
            choice=s.next().charAt(0);
       }
       while(choice=='y');

       System.out.println("Program terminated");
       s.close();
    }
}
