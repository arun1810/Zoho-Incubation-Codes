package Utilities;

import java.io.BufferedReader;
import java.io.IOException;
public class UserDataGetter {

    private static UserDataGetter Instance;
    private BufferedReader inputReader;
    private Printer printer;

    private UserDataGetter(){
        printer = Printer.getInstance();
    }//Singleton
    
    public SortUtil.SortOrder getSortOrderFromUser(){
        int option=1;//default
        option =  getIntegerDataFromUser("Enter a option\n1.Ascending\n2.Descending");
        option = option==0?1:option;
        
        return option==1 ? SortUtil.SortOrder.Ascending : SortUtil.SortOrder.Descending;

    }
    public String getStringDataFromUser(String msg){
        printer.printMsg(msg);
        String data="";
        try {
            data = inputReader.readLine();
        } catch (IOException e) {
            printer.printErrorMsg("Unexpected error occured! restart program");
        }
        return data;
    }
    public int getIntegerDataFromUser(String msg){
        int data=0;//default
        try{data=Integer.parseInt(getStringDataFromUser(msg));}
        catch(NumberFormatException e){
            // doNothing
        }
        return data;
        
    }

    public static UserDataGetter getInstance(BufferedReader inputReader){
        if(Instance==null){
            Instance = new UserDataGetter();
        }
        Instance.inputReader = inputReader;
        return Instance;

    }
}
