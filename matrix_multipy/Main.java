import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;




public class Main{
    static int size = 5;
    static int matrix1[][] = new int[size][size];
    static int matrix2[][] = new int[size][size];
    public static void main(String args[]){
        
        Random rand = new Random();
        for(int i = 0 ; i <size ; i++){
            for(int j = 0; j<size ; j++){

                matrix1[i][j] = rand.nextInt();
                matrix2[i][j] = rand.nextInt();
                matrix1[i][j] = i;
                matrix2[i][j] = j;
            }
            
        }
        System.out.println("matrix1 is ");
            for(int i = 0 ; i<matrix1.length ; i++){
                for(int j = 0 ; j<matrix2[0].length ; j++){
                    System.out.print(matrix1[i][j]);
                    System.out.print(",");
                }
                System.out.println();
            }
        System.out.println("matrix2 is ");
            for(int i = 0 ; i<matrix1.length ; i++){
                for(int j = 0 ; j<matrix2[0].length ; j++){
                    System.out.print(matrix2[i][j]);
                    System.out.print(",");
                }
                System.out.println();
            }
        singleThreadMultipy(matrix1, matrix2);
        multilThreadMultipy(matrix1, matrix2);

    }
    public static void singleThreadMultipy(int[][] matrix1,int[][] matrix2){
        long initTime = System.currentTimeMillis();
        System.out.println("啟動時間"+initTime);
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
        System.out.println("結束時間"+stopTime);
        System.out.println("總耗時（milli second)"+(stopTime-initTime));
        for(int i = 0 ; i<matrix1.length ; i++){
            for(int j = 0 ; j<matrix2[0].length ; j++){
                System.out.print(result[i][j]);
                System.out.print(",");
            }
            System.out.println();
        }
    }
    public static void multilThreadMultipy(int[][] matrix1,int[][] matrix2){
        long initTime = System.currentTimeMillis();
        System.out.println("啟動時間"+initTime);
        long result[][] = new long[matrix1.length][matrix2[0].length];
        ExecutorService es = Executors.newFixedThreadPool(4);
        Collection<Callable<Long>> tasks = new  LinkedList<>();
        List<Future<Long>> ls ;
        for(int i = 0 ; i<matrix1.length ; i++){
            for(int j = 0 ; j<matrix2[0].length ; j++){
                tasks.add(new myCallable(i, j, matrix1, matrix2));
            }
        }
        try{
            ls = es.invokeAll(tasks);
            for(int i = 0 ; i<matrix1.length ; i++){
                for(int j = 0 ; j<matrix2[0].length ; j++){
                    result[i][j] = ls.get(0).get();
                    ls.remove(0);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        finally{
            es.shutdown();
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("結束時間"+stopTime);
        System.out.println("總耗時（milli second)"+(stopTime-initTime));
        for(int i = 0 ; i<matrix1.length ; i++){
            for(int j = 0 ; j<matrix2[0].length ; j++){
                System.out.print(result[i][j]);
                System.out.print(",");
            }
            System.out.println();
        }

    }

}

class myCallable implements Callable<Long>{
    int i;
    int j;
    int matrix1[][];
    int matrix2[][];
    public myCallable(int i,int j,int matrix1[][],int matrix2[][]){
        this.i=i;
        this.j=j;
        this.matrix1=matrix1;
        this.matrix2=matrix2;

    }
    public Long call(){
        long temp = 0;
        
        for(int k=0 ; k<matrix1.length ; k++){
            temp += matrix1[this.i][k]*matrix2[k][this.j];
        }
        return temp;
    }
}