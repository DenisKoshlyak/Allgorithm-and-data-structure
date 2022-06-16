package My_Collections;

class Link<T> {
    public T iData;
    public Link next;
    
    public Link(T dd) {
       iData = dd;
    }
    
    public void displayLink() {
       System.out.print("{ " + iData + " } ");
    }
}

class LinkedList<T> {
    private Link current;
    private Link first;
    
    public LinkedList() {
       current = null;
    }
    
    public void insert(T dd) {
       Link newLink = new Link(dd);
       newLink. next = first;
       first = newLink;
       current = first;
    }
    
    private boolean isEmpty() {
       return (current == null);
    }
    
    public boolean delete() {
        if(isEmpty()){
            return false;
        }
        else {
            current = first.next;
            first = current;
            return true;
        } 
    }
 
    public void displayList() {
       while(current != null){
           current.displayLink();
           current = current.next;
       }
       current = first;
    }
}