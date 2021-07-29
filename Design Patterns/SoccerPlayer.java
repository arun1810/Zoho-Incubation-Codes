public class SoccerPlayer extends ExternalJuniorStudent {
    
    SoccerPlayer(int rollNo,String name){
        super(rollNo, name);
    }

    public void playSoccer(){
        System.out.println(name+" is playing Soccer");
    }

    @Override
    public void Hear() {
        System.out.println(name+" can't hear");
    }
}
