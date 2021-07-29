import interfaces.Player;
import interfaces.Student;

public class Junior implements Student,Player{
    private int ID;
    private String name;
    private String mobileNo;
    private String address;

   private Junior(Builder builder){
        this.ID = builder.ID;
         this.name=builder.name;
         this.mobileNo=builder.mobileNo;
        this.address=builder.address;
    }

    public String getAddress() {
        return address;
    }

    

    public String getMobileNo() {
        return mobileNo;
    }
    @Override
    public int getId() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    

    @Override
    public void listen() {
        System.out.println(name+" is Listening");
        
    }

    @Override
    public void takeNotes() {
        System.out.println(name+" is Taking Notes");
        
    }

    public void payFee(){
        new StudentPaymentHandler().payFee(this);
    }

    @Override
    public void play() {
       System.out.println(name+" started to play");
        
    }

    @Override
    public String toString() {
        
        return "ID=>"+ID+"\nname=>"+name+"\nmobileNo=>"+mobileNo+"\naddress=>"+address;
    }
    
    
    public static class Builder{
        Student student;
        private int ID;
        private String name;
        private String mobileNo;
        private String address;
        
        public Builder(int ID,String name){
            this.ID = ID;
            this.name = name;
        }

        public Builder mobileNo(String mobileNo){
            this.mobileNo = mobileNo;
            return this;
        } 

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Student build(){
            return new Junior(this);
        }

    }


    


   

    
}
