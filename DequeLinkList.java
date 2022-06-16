class Link {
    public int iData;
    public double dData;
    public Link next;
    public Link previous;
    
    public Link(int id, double dd) {
       iData = id;
       dData = dd;
    }
    
    public void displayLink() {
       System.out.print("{" + iData + ", " + dData + "} ");
    }
}

class DequeLinkList {
    private Link first;
    private Link last;
    
    public DequeLinkList() {
       first = null;
    }
    
    private boolean isEmpty() {
       return (first==null);
    }
    
    public void insertLeft(int id, double dd) {
       Link newLink = new Link(id, dd);
       newLink.next = first;
       if(isEmpty())
           enterInEmpty(newLink);
       
       else
           enterFirstElem(newLink);
    }
    
    private void enterFirstElem(Link newLink){
        first.previous = newLink;
        newLink.previous = null;
        first = newLink;
    }
    
    public void insertRight(int id, double dd){
       Link newLink = new Link(id, dd);
       newLink.next = null;
       if(isEmpty())
           enterInEmpty(newLink);
       else
           enterLastElem(newLink);
    }
    
    private void enterInEmpty(Link newLink){
        first = newLink;
        last = newLink;
        first.previous = null;
    }
    
    private void enterLastElem(Link newLink){
        newLink.previous = last;
        last.next = newLink;
        last = newLink;
    }
    
    public Link deleteFirst() {
       Link temp = first;
       first = first.next;
       return temp;
    } 
    
    public Link deleteLast() {
       Link temp = last;
       last = last.previous;
       last.next = null;
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
        DequeLinkList qll = new DequeLinkList();
        qll.insertRight(10, 15);
        qll.displayList();
        qll.insertLeft(9, 13);
        qll.displayList();
        qll.insertRight(16, 5);
        qll.displayList();
        qll.insertRight(11, 21);
        qll.displayList();
        qll.insertLeft(13, 23);
        qll.displayList();

        qll.deleteFirst().displayLink();
        qll.displayList();

        qll.deleteFirst().displayLink();
        qll.displayList();

        qll.deleteLast().displayLink();
        qll.displayList();

        qll.deleteLast().displayLink();
        qll.displayList();
    }
}