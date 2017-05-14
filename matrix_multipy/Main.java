import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;




public class Main{
    static boolean debug = false;
    static int size = 1000;
    static long matrix1[][] = new long[size][size];
    static long matrix2[][] = new long[size][size];
    public static void main(String args[]){
        
        Random rand = new Random();
        for(int i = 0 ; i <size ; i++){
            for(int j = 0; j<size ; j++){

                matrix1[i][j] = rand.nextInt(10);
                matrix2[i][j] = rand.nextInt(10);
                
                // matrix1[i][j] = i;
                // matrix2[i][j] = j;
            }
            
        }
        // System.out.println("matrix1 is ");
        //     for(int i = 0 ; i<matrix1.length ; i++){
        //         for(int j = 0 ; j<matrix2[0].length ; j++){
        //             System.out.print(matrix1[i][j]);
        //             System.out.print(",");
        //         }
        //         System.out.println();
        //     }
        // System.out.println("matrix2 is ");
        //     for(int i = 0 ; i<matrix1.length ; i++){
        //         for(int j = 0 ; j<matrix2[0].length ; j++){
        //             System.out.print(matrix2[i][j]);
        //             System.out.print(",");
        //         }
        //         System.out.println();
        //     }
        singleThreadMultipy(matrix1, matrix2);
        multilThreadMultipy(matrix1, matrix2);

    }
    public static void singleThreadMultipy(long[][] matrix1,long[][] matrix2){
        long initTime = System.currentTimeMillis();
        System.out.println("start time"+initTime);
        long result[][] = new long[matrix1.length][matrix2[0].length];
        for(int i = 0 ; i<matrix1.length ; i++){
            for(int j = 0 ; j<matrix2[0].length ; j++){
                long temp = 0 ;
                for(int k=0 ; k<matrix1.length ; k++){
                    temp += matrix1[i][k]*matrix2[k][j];
                }
                result[i][j] = temp;
            }
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("finish time"+stopTime);
        System.out.println("time consumption(milli second)"+(stopTime-initTime));
        if(debug){
            for(int i = 0 ; i<matrix1.length ; i++){
                for(int j = 0 ; j<matrix2[0].length ; j++){
                    System.out.print(result[i][j]);
                    System.out.print(",");
                }
                System.out.println();
            }
        }
        
    }
    public static void multilThreadMultipy(long[][] matrix1,long[][] matrix2){
        long initTime = System.currentTimeMillis();
        System.out.println("start time"+initTime);
        long result[][] = new long[matrix1.length][matrix2[0].length];
        ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Collection<Callable<long[]>> tasks = new  LinkedList<>();
        List<Future<long[]>> ls ;
        for(int i = 0 ; i<matrix1.length ; i++){
            tasks.add(new myCallable(i,  matrix1, matrix2));
        }
        try{
            ls = es.invokeAll(tasks);
            for(int i = 0 ; i<matrix1.length ; i++){
                result[i] = ls.get(0).get();
                ls.remove(0);
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        finally{
            es.shutdown();
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("finish time"+stopTime);
        System.out.println("time consumption(milli second)"+(stopTime-initTime));
        if(debug){
            for(int i = 0 ; i<matrix1.length ; i++){
                for(int j = 0 ; j<matrix2[0].length ; j++){
                    System.out.print(result[i][j]);
                    System.out.print(",");
                }
                System.out.println();
            }
        }

    }

}

class myCallable implements Callable<long[]>{
    int i;
    int j;
    long matrix1[][];
    long matrix2[][];
    public myCallable(int i,long matrix1[][],long matrix2[][]){
        this.i=i;
        this.matrix1=matrix1;
        this.matrix2=matrix2;

    }
    public long[] call(){
        long temp[] = new long[matrix2[0].length];
        for(int j = 0 ; j<matrix2[0].length ; j++){
            for(int k=0 ; k<matrix1.length ; k++){
                temp[j] += matrix1[this.i][k]*matrix2[k][j];
            }
        }
        
        return temp;
    }
}