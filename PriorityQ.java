class PriorityQ{
    private int maxSize;
    private long[] queArray;
    private int nItems;
    
    public PriorityQ(int s){
        maxSize = s;
        queArray = new long[maxSize];
        nItems = 0;
    }
    
    public void insert(long j){
        if(isFull())
            System.out.println("The queue is full");
        else queArray[nItems++] = j;
    }
    
    public long removePriority(){
        if(isEmpty())
            return -1;
        long temp = 0;
        int idx = 0;
        for(int i = 0; i<nItems; i++){
            if(queArray[i]>temp){
                temp = queArray[i];
                idx = i;
            }
        }
        queArray[idx] = -1;
        return temp;
    }
    
    public boolean isEmpty(){
       return (nItems == 0);
   }
    
    public boolean isFull(){
        return (nItems == maxSize);
   }

    public void displayPriQ(){
        for(int i = 0; i<nItems; i++)
             System.out.print(queArray[i] + " ");
        System.out.println();
    }
    
    public static void main(String args[]){
        PriorityQ pq = new PriorityQ(8);
        
        pq.insert(1);
        pq.insert(101);
        pq.insert(51);
        pq.insert(221);
        pq.insert(1);
        pq.insert(101);
        pq.insert(51);
        pq.insert(221);
        pq.insert(1);
        pq.insert(101);
        pq.insert(51);
        pq.insert(221);
 System.out.println();

        pq.displayPriQ();
        
        System.out.println(pq.removePriority());
        System.out.println(pq.removePriority());
        System.out.println(pq.removePriority());
        System.out.println(pq.removePriority());

pq.displayPriQ();
    }
}