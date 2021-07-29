import interfaces.Student;
import interfaces.Clone;
public class Senior implements Student,Clone {
    private int ID;
    private String name;
    private String mobileNo;
    private String address;
    

   private Senior(Builder builder){
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
    public void listen() {
        System.out.println(name+" is Listening");
        
    }

    @Override
    public void takeNotes() {
        System.out.println(name+" is Taking Notes");
        
    }

    @Override
    public int getId() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }


    public void payFee(){
        new StudentPaymentHandler().payFee(this);
    }

    @Override
    public String toString() {
        
        return "ID=>"+ID+"\nname=>"+name+"\nmobileNo=>"+mobileNo+"\naddress=>"+address;
    }

    @Override
    public Student clone()  {
        return new Builder(ID,name).mobileNo(mobileNo).address(address).build();
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
            return new Senior(this);
        }

    }


    
    
}