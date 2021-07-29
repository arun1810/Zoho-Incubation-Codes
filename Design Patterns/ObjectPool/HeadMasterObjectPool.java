package ObjectPool;

public class HeadMasterObjectPool extends ObjectPool<HeadMaster> {

    private static HeadMasterObjectPool INSTANCE;

    private HeadMasterObjectPool(){
        //default constructor
    }

    @Override
    protected HeadMaster create() {
    
        
        return new HeadMaster();
    }

    public static HeadMasterObjectPool getInstance(){
        if(INSTANCE==null){
            synchronized(HeadMasterObjectPool.class){
                if(INSTANCE==null){
                    return new HeadMasterObjectPool();
                }
            }
        }

        return INSTANCE;
    }
    
}
