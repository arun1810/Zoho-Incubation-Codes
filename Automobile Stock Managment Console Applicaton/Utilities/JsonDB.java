package Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import CustomExceptions.CannotAddDataException;
import CustomExceptions.CannotRemoveDataException;


public class JsonDB {
    private Gson gson;
    private static JsonDB Instance;
    private Printer printer;
  
    private JsonDB(){
        gson = new Gson();
        printer = Printer.getInstance();
    } //Singleton

   private<T> void saveDataAsJsonToFile(List<T> data,FileWriter writer){
         gson.toJson(data,writer);
    }
    private<T> T JsonToObject(JsonElement json,Type classType){
        return gson.fromJson(json, classType);
    }

    public<T> void updateDataOnDb(List<T> updatedData,File file){
        try(FileWriter writer = new FileWriter(file,false)){
            saveDataAsJsonToFile(updatedData, writer);
        }
        catch(IOException e){
           printer.printErrorMsg("Unexpected error occured! restart program");
        }
        
    }

    public<T> List<T> getAllDataFromFile(File file,Type classType){
        JsonArray jsonarray=null;
        List<T> data = new ArrayList<>();
        try {
            jsonarray = (JsonArray)JsonParser.parseReader(new FileReader(file));
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
            printer.printErrorMsg("Unexpected error occured! restart program");
        }
        Iterator<JsonElement> iterator = jsonarray.iterator();
        while (iterator.hasNext()) {
            data.add(JsonToObject(iterator.next(), classType));
        }
        return data;
    }

    public<T> List<T> writeDataToDataBase(T data,File file,Type classType) throws CannotAddDataException {
        FileWriter filewriter = null;
        List<T> listOfData = new ArrayList<>();

        try {
            if(file.length()==0){
                filewriter = new FileWriter(file,true); 
            }
            else{
            listOfData = getAllDataFromFile(file,classType);
            filewriter = new FileWriter(file,false);
            }
            listOfData.add(data);
            saveDataAsJsonToFile(listOfData, filewriter);
                
               
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            throw new CannotAddDataException("Given data cannot be added");
            
        } 
        finally{
            try {
                if(filewriter!=null){
                filewriter.flush();
                filewriter.close();
                }
            } catch (IOException e) {
                throw new CannotAddDataException("Given data cannot be added");
                
            }
            
        }
        return listOfData;
    }
    public<T> List<T> removeDataFromDataBase(T data,File file,Type classType) throws CannotRemoveDataException{
        FileWriter filewriter = null;
        List<T> listOfData = new ArrayList<>();

        try {
            if(file.length()==0){
                throw new CannotRemoveDataException("Cannot remove given Data");
            }
            else{
            listOfData = getAllDataFromFile(file,classType);
            filewriter = new FileWriter(file,false);
            }
           listOfData.remove(data);
           saveDataAsJsonToFile(listOfData, filewriter);     
        
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            throw new CannotRemoveDataException("Cannot remove given Data");
            
        } 
        finally{
            try {
                if(filewriter!=null){
                filewriter.flush();
                filewriter.close();
                }
            } catch (IOException e) {
                throw new CannotRemoveDataException("Cannot remove given Data");
                
            }
            
        }
        return listOfData;

    }


    public static JsonDB getInstance(){
        if(Instance==null){
            Instance = new JsonDB();
        }
        return Instance;

    }

}
