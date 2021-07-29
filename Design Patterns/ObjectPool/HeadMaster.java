package ObjectPool;
public class HeadMaster {
    
    HeadMaster(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error occured");
        }
    }
}
