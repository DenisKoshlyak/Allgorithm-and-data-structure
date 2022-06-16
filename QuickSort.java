class QuickSort {
    long[] theArray;
    private int nElems; 
    public int copy = 0;
    public int compare = 0;
    
    public QuickSort(int max) {
        theArray = new long[max];
        nElems = 0; 
    }
    
    public void insert(long value) {
        theArray[nElems] = value;
        nElems++;
    }
    
    public int size() { 
        return nElems;
    }
    
    public void display() {
        System.out.print("A=");
        for(int j=0; j<nElems; j++)
            System.out.print(theArray[j] + " ");
        System.out.println("");
        System.out.println("Copy = " + copy);
        System.out.println("Compare = " + compare);
    }
    
    public void quickSort() {
        recQuickSort(0, nElems-1);
    }
    
    private void recQuickSort(int left, int right) {
        int size = right - left + 1;
        if(size <=3 )
            manualSort(left, right);
        else {
            long median = medianOf3(left, right);
            int partition = partitionIt(left, right, median);
            recQuickSort(left, partition-1);
            recQuickSort(partition+1, right);
        }
    }
    
    public long medianOf3(int left, int right) {
        int center = (left+right)/2;
        if(theArray[left] > theArray[center])
            swap(left, center);
        if(theArray[left] > theArray[right])
            swap(left, right);
        if(theArray[center] > theArray[right])
            swap(center, right);
        swap(center, right );
        compare += 3;
        return theArray[right]; 
    }

    public void manualSort(int left, int right) {
        int size = right-left+1;
        if(size <= 1)
            return;
        if(size == 2) {
            if(theArray[left] > theArray[right])
                swap(left, right);
            return;
        }
        else {
            if(theArray[left] > theArray[right-1])
                swap(left, right-1);
            if(theArray[left] > theArray[right])
                swap(left, right);
            if(theArray[right-1] > theArray[right])
                swap(right-1, right);
        }
        compare += 4;
    }

    private int partitionIt(int left, int right, long pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;
        while(true) {
            while(leftPtr < right && theArray[++leftPtr] < pivot)
                compare++;
            while(rightPtr > left && theArray[--rightPtr] > pivot)
                compare++;
            if(leftPtr >= rightPtr){
                compare++;
                break;
            }
            else
                swap(leftPtr, rightPtr);
        }
        swap(leftPtr, right);
        return leftPtr;
    }
    
    public void swap(int dex1, int dex2) {
        long temp;
        temp = theArray[dex1];
        theArray[dex1] = theArray[dex2];
        theArray[dex2] = temp;
        copy += 3;
    }
    
    public static void main(String args[]){     
        int maxSize = 16;
        QuickSort qr = new QuickSort(maxSize);
        for(int j=0; j<maxSize; j++) {
            long n = (int)(java.lang.Math.random()*199);
            qr.insert(n);
        }
        qr.display();
        qr.quickSort();

        qr.display(); 
    }
}