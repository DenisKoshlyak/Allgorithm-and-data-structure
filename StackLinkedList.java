class Link {
    public int iData;
    public double dData;
    public Link next;
    
    public Link(int id, double dd) {
       iData = id;
       dData = dd;
    }
    
    public void displayLink() {
       System.out.print("{" + iData + ", " + dData + "} ");
    }
}

class StackLinkedList {
    private Link current;
    
    public StackLinkedList() {
       current = null;
    }
    
    public void insert(int id, double dd) {
       Link newLink = new Link(id, dd);
       if(isEmpty())
           insertInEmpty(newLink);
       else if(oneElem())
           insertSecondElem(newLink);
       else
           insertOtherElem(newLink);
    }
    
    private void insertInEmpty(Link newLink){
        current = newLink;
        current.next = null;
    }
    
    private void insertSecondElem(Link newLink){
        current.next = newLink;
        newLink.next = current;
        current = newLink;
    }
    
    private void insertOtherElem(Link newLink){
        newLink.next = current.next;
        current.next = newLink;
        current = newLink;
    }
    
    private boolean isEmpty() {
       return (current == null);
    }
    
    private boolean oneElem(){
        return (current.next == null);
    }
    
    public Link delete() {
        if(isEmpty()){
            return null;
        }
        else if(onlyOneElem()){
            Link temp = current;
            current = null;
            return temp;
        }
        else if(twoElem()){
            Link temp = current;
            deleteOneOfTwo();
            return temp;
        }
        else{
            Link deleted = current;
            deleteCurrent();
            return deleted;
       }
    } 
    
    private boolean onlyOneElem(){
        return (current.next == null);
    }
    
    private boolean twoElem(){
        return ((current.next).next == current);
    }
    
    private void deleteOneOfTwo(){
        current = current.next;
        current.next = null;
    }
    
    private void deleteCurrent(){
        Link temp = current.next;    
        while(temp.next != current)
            temp = temp.next;
        temp.next = current.next;
        current = temp;
    }
    
    public void displayStack() {
       if(isEmpty())
           System.out.println("List is Empty");
       else if(current.next == null){
           current.displayLink();
       }
       else{
           Link temp = current.next;
           do{
               temp.displayLink();
               temp = temp.next;
           }
           while(temp != current.next);
       }
       System.out.println();
    }
    
    public static void main(String args[]){
        StackLinkedList qll = new StackLinkedList();
        
        qll.insert(10, 15);
        qll.displayStack();
        qll.insert(9, 13);
        qll.displayStack();
        qll.insert(16, 5);
        qll.displayStack();
        qll.insert(11, 21);
        qll.displayStack();
        qll.insert(13, 23);
        qll.displayStack();
        
        System.out.print("Deleted ");
        qll.delete().displayLink();
        System.out.println();
        
        System.out.print("Deleted ");
        qll.delete().displayLink();
        System.out.println();
        
        System.out.print("Deleted ");
        qll.delete().displayLink();
        System.out.println();
        qll.displayStack();
        
        System.out.print("Deleted ");
        qll.delete().displayLink();
        System.out.println();
        
        System.out.print("Deleted ");
        qll.delete().displayLink();
        System.out.println();
        
        qll.displayStack();
    }
}