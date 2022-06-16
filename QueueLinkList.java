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

class QueueLinkList {
    private Link first;
    private Link current;
    
    public QueueLinkList() {
       first = null;
    }
    
    private boolean isEmpty() {
       return (first==null);
    }
    
    public void insertLowPriority(int id, double dd) {
       Link previous = null;
       Link newLink = new Link(id, dd);
       current = first;
       while(current != null && current.dData < newLink.dData){
           previous = current;
           current = current.next;
       } 
       if(previous == null)
           first = newLink;
       else
           previous.next = newLink;
       newLink.next = current;
    } 
    
    public Link deleteFirst() {
       Link temp = first;
       first = first.next;
       return temp;
    } 
    
    public void displayList() {
       System.out.print("List (first-->last): ");
       Link current = first;
       while(current != null) {
            current.displayLink();
            current = current.next;
       }
       System.out.println("");
    }
    
    public static void main(String args[]){
        QueueLinkList qll = new QueueLinkList();
        qll.insertLowPriority(10, 15);
        qll.insertLowPriority(9, 13);
        qll.insertLowPriority(16, 5);
        qll.insertLowPriority(11, 21);
        qll.deleteFirst().displayLink();
    }
}