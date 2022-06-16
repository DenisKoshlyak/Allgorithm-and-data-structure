import java.io.*;
import My_Collections.*;

class Node {
    public char iData;

    public Node leftChild;
    public Node rightChild;
    
    public Node(char iData){
        this.iData = iData;
    }
    
    public void displayNode() {
        System.out.print("{" + iData + "} ");
    }
}

class ModifyTree {
    private Node root;
    private int height;
    private int count = 0;
    private int maxCountInHeight = 0;
    private int upperCount;
    
    public ModifyTree(){ 
        root = null;
    }
    
    public void insert(char id) {
        Node newNode = new Node(id);
        if(count == 0){
            root = new Node('+');
            root.leftChild = newNode;
            count = 2;
            height = 2;
            maxCountInHeight = (int) (Math.pow(2, height) - 1);
        }
        else if(count < maxCountInHeight){
            upperCount = count + 1;
            int tempHeight = 2;
            if(root.rightChild != null)
                findEmptyNodeAndInsert(root.rightChild, tempHeight, newNode);
            else{
                root.rightChild = newNode;
                count++;
            }
        }
        else{
            createNewRootNode();
            int tempHeight = 2;
            insertPlusInEndedNode(root.rightChild, tempHeight);
            upperCount = count + 1;
            findEmptyNodeAndInsert(root.rightChild, tempHeight, newNode);
        }
    }
    
    private void createNewRootNode(){
        height++;
        maxCountInHeight = (int) (Math.pow(2, height) - 1);
        Node newRoot = new Node('+');
        newRoot.leftChild = root;
        root = newRoot;
        root.rightChild = new Node('+');
        count += 2;
    }
    
    private void findEmptyNodeAndInsert(Node temp, int tempHeight, Node newNode){
        if(temp.leftChild != null)
            findEmptyNodeAndInsert(temp.leftChild, tempHeight + 1, newNode);
        else if(shouldInsertInLeft(temp, tempHeight)){
            temp.leftChild = newNode;
            count++;
            return;
        }
        if(temp.rightChild != null)
            findEmptyNodeAndInsert(temp.rightChild, tempHeight + 1, newNode);
        else if(shouldInsertInRight(temp, tempHeight)){
            temp.rightChild = newNode;
            count++;
            return;
        }
    }
    
    private boolean shouldInsertInLeft(Node temp, int tempHeight){
        return (tempHeight < height && temp.leftChild == null && count < upperCount) ;
    }
    
    private boolean shouldInsertInRight(Node temp, int tempHeight){
        return (tempHeight < height && temp.rightChild == null && count < upperCount) ;
    }
    
    private void insertPlusInEndedNode(Node temp, int tempHeight){
        if(tempHeight + 1 < height && temp.leftChild == null){
            temp.leftChild = new Node('+');
            count++;
            insertPlusInEndedNode(temp.leftChild, tempHeight + 1);
        }
        if(tempHeight + 1 < height && temp.rightChild == null){
            temp.rightChild = new Node('+');
            count++;
            insertPlusInEndedNode(temp.rightChild, tempHeight + 1);
        }
    }
    
    public void displayTree() {
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
                for(int j=0; j<nBlanks*2-1; j++)
                    System.out.print(' ');
            }
            System.out.println();
            nBlanks /= 2;
            while(!localStack.isEmpty())
                globalStack.push(localStack.pop());
        }
    }
    
    public static void main(String[] args) throws IOException {
        ModifyTree tr = new ModifyTree();
        
        tr.insert('A');
        tr.insert('B');
        tr.insert('C');
        tr.insert('D');
        tr.insert('E');
        tr.insert('F');
        tr.insert('G');
        tr.insert('H');

        tr.displayTree();
    }
 } 