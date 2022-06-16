class Link {
    public double num;
    public double col;
    public Link next;
    public Link down;
    
    public Link(double n, double c) {
       num = n;
       col = c;
    }
    
    public void displayLink() {
       System.out.print("{ " + num + ", " + col + " } ");
    }
}

class MatrixLinkedList {
    private int column;
    private int numeric;
    private Link current;
    private Link first;
    private Link downCurrent;
    
    public MatrixLinkedList() {
       current = null;
       downCurrent = null;
    }
    
    public void insert(int num, int col){
        Link newLink = new Link(num, col);
        
        if(current == null)
            insertFirstInFirst(newLink);
        else if(num == first.num)
            insertInFirstRow(newLink);
        else if(num < first.num)
            insertInNewFirstRow(newLink);
        else if(num > first.num)
            insertInNextRow(newLink);
        
    }

    private void insertFirstInFirst(Link newLink){
        newLink.next = null;
        newLink.down = null;
        first = newLink;
        current = first;
        downCurrent = first;
    }
    
    private void insertInFirstRow(Link newLink){
        Link temp = null;
        while(notFirstElem(newLink)){
            temp = current;
            current = current.next;
        }
        if(temp == null){
            insertNewFirstElem(newLink);
        }
        if(temp != null){
            insertInNotFirstPosition(temp, newLink);
        }
    }
    
    private boolean notFirstElem(Link newLink){
        return (current != null && newLink.col > current.col);
    }
    
    private void insertNewFirstElem(Link newLink){
        newLink.next = first;
        newLink.down = first.down;
        first.down = null;
        first = newLink;
        current = first;
        downCurrent = first;
    }
    
    private void insertInNotFirstPosition(Link temp, Link newLink){
        newLink.next = temp.next;
        temp.next = newLink;
        current = first;
    }
    
    private void insertInNewFirstRow(Link newLink){
        newLink.next = null;
        newLink.down = first;
        first = newLink;
        downCurrent = first;
        current = first;
    }
    
    private void insertInNextRow(Link newLink){
        Link temp = downCurrent;
        while(temp != null && newLink.num > temp.num){
            downCurrent = temp;
            temp = downCurrent.down;
        }
        if(temp == null){
            downCurrent.down = newLink;
        }
        else if(temp.col > newLink.col){
            newLink.next = temp;
            newLink.down = temp.down;
            downCurrent.down = newLink;
        }
        else if(temp.col < newLink.col){
            while(temp != null && newLink.col > temp.col){
                downCurrent = temp;
                temp = downCurrent.next;
            }
            newLink.next = temp;
            downCurrent.next = newLink;
        }
        downCurrent = first;
    }
    
    public void displayList() {
        if(current != null){
            do{
                displayOneRow(current);
                System.out.println();
                current = current.down;
            }
            while(current != null);
            current = first;
            }
        }
    
    private void displayOneRow(Link current){
        do{
            current.displayLink();
            current = current.next;
        }
        while(current != null);
    }
    
    public static void main(String args[]){
        MatrixLinkedList qll = new MatrixLinkedList();
 
        qll.insert(10, 10);
         
        qll.displayList();
    }
}