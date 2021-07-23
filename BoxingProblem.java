public class BoxingProblem {

    public static void main(String[] args) {
        long sum=0;
        long time = System.currentTimeMillis();
        for(long i=0;i<Integer.MAX_VALUE;i++){
            sum+=i;
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis()-time);
    }
    
}
