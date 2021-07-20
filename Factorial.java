import java.util.Scanner;
public class Factorial {
    
    Factorial(int num){
        WrapperDouble result=new WrapperDouble(1.0);
        for(int i=2;i<=num;i++){
            result.setvalue(result.getValue()*i);
            
        }
        dummyMeth(result);
        System.out.println("Result=>"+result.getValue());
        
    }

    public void dummyMeth(WrapperDouble result){
      WrapperDouble dummy=result;
     // dummy = new WrapperDouble(0.0);
      dummy.setvalue(0.0);
      System.out.println("dummy=>"+dummy.getValue());
    }
public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    System.out.println("Enter a number");
    int num=s.nextInt();
    new Factorial(num);
    s.close();
}
}

class WrapperDouble{
    double d;
    WrapperDouble(double d){
        this.d=d;
    }

    public void setvalue(double d){
        this.d=d;
    }

    public double getValue(){
        return d;
    }
}
