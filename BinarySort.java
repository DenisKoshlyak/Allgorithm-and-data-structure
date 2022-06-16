import My_Collections.LinkedList;

class BinarySort{
    private int array[];
    LinkedList[] arrayList = new LinkedList[10];
    private int num;
    
    BinarySort(int array[], int num){
        this.array = array;
        this.num = num;
    }
    
    public void sorted(){
        for(int i = 0; i <= 9; i++)
           arrayList[i] = new LinkedList();
        for(int j = 1; j <= num ; j++){
           insertInList(j);
           insertInArray();
        }
    }
    
    private void insertInList(int j){
         int k;
           for(int col = 0; col < array.length; col++){
               k = getDigit(j, col);
               arrayList[k].insert(array[col]);
           }
    }
    
    private void insertInArray(){
        int col = 0;
        for(int q = 0; q <= 9; q++){
            while(!(arrayList[q].isEmpty())){
                array[col] = arrayList[q].delete();
                col++;
            }
        }
    }
    
    private int getDigit(int num, int col){
        int k;
        if(num == 1){
            k = (int) array[col] % 10;
            return k;
        }
        else if(num == 2){
            k = (int) (array[col] % 100) / 10;
            return k;
        }
        else if(num == 3){
            k = (int) array[col] / 100;
            return k;
        }
        else 
            return -1;
    }
    
    public void displayArray(){
        for(int i = 0; i <array.length; i++)
            System.out.print(array[i] + " ");
    }
    
    public static void main(String args[]){
        int[] array = {573, 187, 908, 345, 563, 928, 452, 245, 382, 999};
        BinarySort bs = new BinarySort(array, 3);
        bs.sorted();
        bs.displayArray();
    }
}