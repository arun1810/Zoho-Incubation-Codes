package AreaWithSwitch;
import java.util.Scanner;
import AreaWithIf.Area;
public class Area2 {

    private final float pi=(float)3.14;
    private float area;
    Area2(Scanner s,Area.Shape shape){
        
        switch(shape){
            case SQUARE:
                AreaOfSquare(s);
                break;
            case RECTANGLE:
                AreaOfRectangle(s);
                break;
            case TRIANGLE:
                AreaOfTriangle(s);
                break;
            case CIRCLE:
                AreaOfCircle(s);
                break;
            default:
                break;

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

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
        System.out.println("Finalize called");
    }
}

class Main{
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        Area2 area = new Area2(s,Area.Shape.RECTANGLE);
        System.out.println("Area=>"+area.getArea());
 
        //Area area2 = new Area(s,1);
        //System.out.println(area2.getArea());

        Area2 a = new Area2(s,Area.Shape.RECTANGLE);
        System.gc();
        System.runFinalization();

        s.close();
    }
}