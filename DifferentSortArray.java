import java.util.*;

class DifferentSortArray {
  private static int[] resultArray;
  private static int[] arrayWithoutDups;
  private static int[] array1;
  private static int[] array2;
  private static Random rand = new Random();
  
  public DifferentSortArray(int[] array1, int[] array2){
      this.array1 = array1;
      this.array2 = array2;
  }
  
  public static void createSortArray(){
      resultArray = new int[array1.length + array2.length];
      for(int i = 0; i<resultArray.length; i++){
          connectArrays(i);
      }
      chetnoNechetnayaSort();
      displayResultArray();
  }
  
  private static void connectArrays(int i){
      if(i<array1.length)
            resultArray[i] = array1[i];
      else 
            resultArray[i] = array2[i-array1.length];
  }

  private static void chetnoNechetnayaSort(){
      for(int k = 0; k<resultArray.length; k++){
          iterateChetnih();
          iterateNechetnih();
          }
      }
  
  private static void iterateChetnih(){
      int nextElem = 0; 
      for(int i = 0; i<resultArray.length; i+=2){
             nextElem = i+1;
             if(resultArray[i]>resultArray[nextElem])
                swap(i, nextElem);
      }
  }
  
  private static void iterateNechetnih(){
      int  nextElem = 0;
       for(int i = 1; i<resultArray.length-1; i+=2){
             nextElem = i+1;
             if(resultArray[i]>resultArray[nextElem])
                swap(i, nextElem);
       }
  }
  
  private static void insertSort(){
      int in;
      for(int out = 1; out<resultArray.length; out++){
          int temp = resultArray[out];
          in = out;
          while(in>0 && resultArray[in-1]>=temp){
              resultArray[in] = resultArray[in-1];
              --in;
          }
          resultArray[in] = temp;
          }
      } 
  
  public static void median(){
      int medianIndex = resultArray.length / 2;
      System.out.println("Median is " + resultArray[medianIndex]);
  }
  
  private static void selectionSort(){
    int prev, in, min;
    for(prev=0; prev<resultArray.length-1; prev++){
        min = prev;
        for(in=prev+1; in<resultArray.length; in++)
            if(resultArray[in] < resultArray[min])
                min = in;
        swap(prev, min);
      }
 }
  
  private static void bubbleSort(){
      int out, in;
      for(out=resultArray.length-1; out>0; out--){
          for(in=0; in<out; in++)
              if( resultArray[in] > resultArray[in+1] ) 
                  swap(in, in+1); 
      }   
 }
  
  private static void swap(int prev, int next){
      int timeSave = resultArray[prev];
      resultArray[prev] = resultArray[next];
      resultArray[next] = timeSave;
  }
  
   public static void displayResultArray() {
       for(int j=0; j<resultArray.length; j++) 
         System.out.print(resultArray[j] + " "); 
       System.out.println("");
  }
   
   public static void main(String args[]){
       int [] array1 = {rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20)};
       int [] array2 = {rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20)};
       DifferentSortArray dsf = new DifferentSortArray(array1, array2);
       dsf.createSortArray();
   }
}