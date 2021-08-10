package Utilities;

import java.util.Random;

public class IdGenerator {
    
    private static IdGenerator Instance;

    private IdGenerator(){}//Singleton

    public String generateID(){
        char[] chars = "123456789bcdfghjklmnpqrstvwxyz".toCharArray();
        int IdLength = 7;
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        
        for(int i=0;i<IdLength;i++){
            builder.append(chars[random.nextInt(chars.length-1)]);
        }
        return builder.toString();
    }

    public static IdGenerator getInstance(){
        if(Instance==null){
            Instance = new IdGenerator();
        }

        return Instance;
    }
}
