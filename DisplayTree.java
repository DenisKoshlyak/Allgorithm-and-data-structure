class DisplayTree{
    private int width;
    private char[] array;
    private int row = 1;
    
    public DisplayTree(int width){
        this.width = width;
        array = new char[calc_N_Row()*width];
    }
    
    public void makeBranches(int left, int right, int numRow){ 
        int pivot = (right + left) / 2;
        int n = left;
        while(n <= right){
            if(n == pivot)
                array[n + numRow] = 'X';
            else
                array[n + numRow] = '-';
            n++;
        }
        numRow += width;
        if((right - left) > 0){
            makeBranches(left, pivot, numRow);
            makeBranches(pivot + 1, right, numRow);
        }
    }
    
    private int calc_N_Row(){
        int i = 0;
        while((row * 2) <= width +1){
          row *= 2;
          i++;
        }
        return (i+2);
    }
    
    public void displayTree(){
        int n = 1;
        for(int i = 0; i < array.length; i++){
           System.out.print(array[i] + " ");
           if(n == width){
               n=0;
               System.out.println();
           }
           n++;
        }
    }
    
    public static void main(String args[]){
        DisplayTree dt = new DisplayTree(8);
        
        dt.makeBranches(0, 7, 0);
        dt.displayTree();
    }
}