package ObjectPool;

import java.util.HashMap;

public abstract class ObjectPool<T> {
    
   private final HashMap<T,Long> available = new HashMap<>(10);
   private final HashMap<T,Long> inUse = new HashMap<>(10);

   protected abstract T create();

   public synchronized T getObject(){
       if(available.isEmpty()){
           available.put(create(),System.currentTimeMillis());
       }

       T obj = available.entrySet().iterator().next().getKey();
       inUse.put(obj,System.currentTimeMillis());
       available.remove(obj);
       return obj;
   }

   public synchronized void returnObject(T obj){
       inUse.remove(obj);
       available.put(obj, System.currentTimeMillis());
   }

}


