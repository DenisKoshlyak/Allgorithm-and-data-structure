import java.io.*;
import My_Collections.SortDoubleLinkedList;

class Node {
    public double iData;

    public Node leftChild;
    public Node rightChild;
    
    public Node(double iData){
        this.iData = iData;
    }
    
    public void displayNode() {
        System.out.print("{" + iData + "} ");
    }
}
        
class PiramideTree {
    private Node root;
    private SortDoubleLinkedList sdl;
    private int listCount = 0;
    private int height = 0;
    private double maxSummElemForThisRow;
    int treeCount = 0;
    
    public PiramideTree(){
        sdl = new SortDoubleLinkedList();
        root = null;
    }
    
    public void insert(double dd){
        sdl.push(dd);
        listCount++;
    }
    
    public void construct(){
        maxSummElemForThisRow = Math.pow(2, height) - 1;
        if(isEmpty()){
            treeCount++;
            root = new Node(sdl.pop());
            maxSummElemForThisRow = 1;
            height++;
        }
        else if(treeCount == maxSummElemForThisRow){
            height++;   
            traverseLeft(root);
        }
        else{
            int tempHeight = 1;
            traverseMidle(root, tempHeight + 1);
        }
    }
    
    private void traverseLeft(Node temp){
        if(temp.leftChild != null)
            traverseLeft(temp.leftChild);
        else{
            temp.leftChild = new Node(sdl.pop());
            treeCount++;
            return;
        }
    }
    
    private void traverseMidle(Node temp, int tempHeight){
        if(temp.leftChild != null && treeCount < listCount)
            traverseMidle(temp.leftChild, tempHeight + 1);
        else if(temp.leftChild == null && treeCount < listCount && tempHeight == height){
            treeCount++;
            temp.leftChild = new Node(sdl.pop());
            return;
        }
        if(temp.rightChild != null && treeCount < listCount)
            traverseMidle(temp.rightChild, tempHeight + 1);
        else if(temp.rightChild == null && treeCount < listCount && tempHeight == height){
            treeCount++;
            temp.rightChild = new Node(sdl.pop());
            return;
        }
    }
    
    private boolean isEmpty(){
        return (root == null);
    }
            
    public void displayTree() {
        System.out.println(treeCount);
        System.out.print(listCount);
        while(treeCount < listCount){
            construct();
        }
        StackLinkedList<Node> globalStack = new StackLinkedList<Node>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        while(isRowEmpty == false) {
            StackLinkedList<Node> localStack = new StackLinkedList<Node>();
            isRowEmpty = true;
            for(int j=0; j<nBlanks; j++)
                System.out.print(' ');
            while(!globalStack.isEmpty()) {
                Node temp = globalStack.pop();
                if(temp != null) {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);
                    if(temp.leftChild != null || temp.rightChild != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<nBlanks*2 + 1; j++)
                    System.out.print(' ');
            }
            System.out.println();
            nBlanks /= 2;
            while(!localStack.isEmpty())
                globalStack.push(localStack.pop());
        }
    }
    
    public static void main(String[] args) throws IOException {
        PiramideTree tr = new PiramideTree();
        
        tr.insert(10);
        tr.insert(20);
        tr.insert(30);
        tr.insert(40);
        tr.insert(50);
        tr.insert(60);
        tr.insert(70);
        tr.insert(80);
        tr.insert(90);
        tr.insert(100);

        
        tr.displayTree();
    }
 }