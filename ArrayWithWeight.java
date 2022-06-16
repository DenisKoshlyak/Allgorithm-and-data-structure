import java.util.*;

class ArrayWithWeight{
    private static double[] arrayValue;
    private static double[] arrayWeight;
    private static int sum;
    static Random rand = new Random();
    public static double valArray = 0;
    
    public ArrayWithWeight(double[] arrayValue, double[] arrayWeight){
        this.arrayValue = arrayValue;
        this.arrayWeight = arrayWeight;
    }
    
    public static double returnRandomValueFromArray(){
        sumWeight();
        delenieMassiva();
        opredelenieValue();
        return valArray;
    }
 
    private static int sumWeight(){
        for(int i=0; i<arrayWeight.length; i++){
            sum +=arrayWeight[i];
        } 
        System.out.println("summ = " + sum);
        return sum;
    }
    
    private static void delenieMassiva(){
        for(int i = 0; i<arrayWeight.length; i++)
            arrayWeight[i] /= sum; 
    }
    
    public static void opredelenieValue(){
        double randomVal = rand.nextDouble();
        System.out.println("randomnoe " + randomVal);
        double sumWeight = arrayWeight[0];
        for(int i = 0; i<arrayWeight.length; i++){
            if(sumWeight>randomVal){
                valArray = arrayValue[i];
                break;
            }
            else if(i<9) sumWeight += arrayWeight[i+1];
            else break;
        }
    }

   public static void main(String args[]){
       double arrayValue[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
       double arrayWeight[] = {4, 3, 7, 100, 8, 5, 3, 7, 3, 2};
       ArrayWithWeight aww = new ArrayWithWeight(arrayValue, arrayWeight);
       returnRandomValueFromArray();
       System.out.println(aww.valArray);
   }
}