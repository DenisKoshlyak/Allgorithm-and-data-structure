import java.util.*;

class DeleteDuplicateFromArray{
  private static int[] resultArray;
  private static int[] arrayWithoutDups;
  private static Random rand = new Random();
  
  public static void createSortArray(int[] array1, int[] array2){
      resultArray = new int[array1.length + array2.length];
      for(int i = 0; i<resultArray.length; i++){
         if(i<array1.length)
            resultArray[i] = array1[i];
         else 
            resultArray[i] = array2[i-array1.length];
      }
      insertSort();
      displayResultArray();
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
  
 public static void deleteDuplicateAndShow(){
      int countUnique = 1;
      for(int i = 0; i<resultArray.length-1; i++){
          if(isUnique(i))
              countUnique++;
      }
      int numberInUnique = 0;
      arrayWithoutDups = new int[countUnique];
      for(int j = 0; j<resultArray.length-1; j++){
          if(isUnique(j)){
              arrayWithoutDups[numberInUnique] = resultArray[j];
              numberInUnique++;
          }
          if(numberInUnique == arrayWithoutDups.length-1)
              insertLastElement(numberInUnique);
      }
      displayArrayWithoutDups();
  }
  
  private static void insertLastElement(int k){
      arrayWithoutDups[k] = resultArray[resultArray.length-1];
  }
  
  private static boolean isUnique(int i){
      return resultArray[i]<resultArray[i+1];
  }
  
  private static void displayArrayWithoutDups() {
     for(int j=0; j<arrayWithoutDups.length; j++) 
       System.out.print(arrayWithoutDups[j] + " "); 
     System.out.println("");
  }
  
  public static void displayResultArray() {
       for(int j=0; j<resultArray.length; j++) 
         System.out.print(resultArray[j] + " "); 
       System.out.println("");
  }
   
   public static void main(String args[]){
       int [] array1 = {rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20)};
       int [] array2 = {rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20)};
       createSortArray(array1, array2);
       deleteDuplicateAndShow();
   }
}