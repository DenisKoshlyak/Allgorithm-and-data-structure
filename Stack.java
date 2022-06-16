class Stack {
   private int maxSize;
   private long[] queArray;
   private int rear;
   private int nItems;
   
   public Stack(int maxSize){
       this.maxSize = maxSize;
       queArray = new long[maxSize];
       rear = -1;
   }
   
   public void push(long j){
       if(isFull()){
          System.out.println("The Stack is full");
       }
       else {
          queArray[++rear] = j;
          nItems++;
       }
   }
   
   public void pop(){
       if(isEmpty()){
           System.out.println("The Stack is empty");
       }
       else {
           long temp = 0;
           temp = queArray[rear--];
           System.out.println(temp + " ");
       }
       
   }
   
   private void displayStack(){
       int number = 0;
       while(number<nItems){
           System.out.println(queArray[number] + " ");
           number++;
       }
   }
   
   public boolean isEmpty(){
       return (rear == -1);
   }
   
   public boolean isFull(){
       return (rear == maxSize-1);
   }
   
   public static void main(String args[]){
       Stack theStack = new Stack(5);
       
       theStack.push(10);
       theStack.push(20);
       theStack.push(30);
       theStack.push(40);
       theStack.push(50);
       theStack.push(60);
       
       theStack.pop();
       theStack.pop();
       theStack.pop();
       theStack.pop();
       theStack.pop();
       theStack.pop();

   }
}