class Link {
    public int iData;
    public double dData;
    public Link next;
    
    public Link(double dd) {
       dData = dd;
    }
    
    public void displayLink() {
       System.out.print("{ " + dData + " } ");
    }
}

class ZadachaFlaviya {
    private Link current;
    
    public ZadachaFlaviya() {
       current = null;
    }
    
    public void insert(double dd) {
       Link newLink = new Link(dd);
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

    public boolean delete() {
        if(isEmpty()){
            return false;
        }
        else if(onlyOneElem()){
            deleteOneOfOne();
            return true;
        }
        else if(twoElem()){
            deleteOneOfTwo();
            return true;
        }
        else{
            deleteCurrent();
            return true;
       }
    } 
    
    private boolean onlyOneElem(){
        return (current.next == null);
    }
    
    private boolean twoElem(){
        return ((current.next).next == current);
    }
    
    private void deleteOneOfOne(){
        current = null;
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
        current = temp.next;
    }
    
   public Link zadachaFlaviya(int count, int round){
        for(int i = 1; i<=count; i++){
            insert(i);
        }
        Link temp = current.next;;
        while(!onlyOneElem()){
            for(int j = 0; j<round; j++)
                temp = temp.next;
            current = temp;
            delete();

            temp = current;
        }
        return temp;
    }

    public void displayList() {
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
        ZadachaFlaviya qll = new ZadachaFlaviya();
        
        System.out.print("The last element ");
        qll.zadachaFlaviya(7, 3).displayLink();
        System.out.println();

        qll.displayList();
    }
}