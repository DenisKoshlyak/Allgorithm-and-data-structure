import java.io.*;
import java.util.*;

class Node {
    public int iData;
    public Node leftChild;
    public Node rightChild;
    public void displayNode() {
        System.out.print('{');
        System.out.print(iData);
        System.out.print("} ");
    }
}

class Tree {
    private Node root;

    public Tree() {
        root = null;
    }

    public boolean insert(int id) {
        Node newNode = new Node();
        newNode.iData = id;
        if(root==null) {
            root = newNode;
            return true;
        }
        else {
            Node current = root;
            Node parent;
            while(true) {
                parent = current;
                if(id < current.iData) {
                    current = current.leftChild;
                    if(current == null) {
                        parent.leftChild = newNode;
                        return true;
                    }
                }
                else {
                    current = current.rightChild;
                    if(current == null) {
                        parent.rightChild = newNode;
                        return true;
                    }
                }
            }
        }
    }

    public boolean removeMax(){
        Node current = root;
        Node parent = current;
        if(current == null)
            return false;
        while(current.rightChild != null){
            parent = current;
            current = current.rightChild;
        }
        if(current == root){
            root = current.leftChild;
        }
        else if(current.leftChild != null){
            parent.rightChild = current.leftChild;
        }
        else
            parent.rightChild = null;
        return true;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("......................................................");
        while(isRowEmpty==false) {
            Stack localStack = new Stack();
            isRowEmpty = true;
            for(int j=0; j<nBlanks; j++)
                System.out.print(' ');
            while(globalStack.isEmpty()==false) {
                Node temp = (Node)globalStack.pop();
                if(temp != null) {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);
                    if(temp.leftChild != null ||
                            temp.rightChild != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<nBlanks*2-2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            nBlanks /= 2;
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        }
        System.out.println("......................................................");
    }

}

class PriorityQ {
    private int nItems;
    private Tree tr;

    public PriorityQ() {
        tr = new Tree();
    }

    public boolean insert(int item) {
        return tr.insert(item);
    }

    public boolean remove() {
        return tr.removeMax();
    }

    public void displayQ() {
        tr.displayTree();
    }

    public boolean isEmpty() {
        return tr.isEmpty();
    }
}
class PriorityTreeApp{
    public static void main(String[] args) throws IOException {
        int value, value2;
        PriorityQ thePQ = new PriorityQ();
        boolean success;
        char choice;
        while (true) {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, remove, change: ");
            choice = getChar();
            switch (choice) {
                case 's':
                    thePQ.displayQ();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = getInt();
                    success = thePQ.insert(value);
                    if (!success)
                        System.out.println("Can’t insert; heap full");
                    break;
                case 'r':
                    if (!thePQ.isEmpty())
                        thePQ.remove();
                    else
                        System.out.println("Can’t remove; heap empty");
                    break;
                default:
                    System.out.println("Invalid entry\n");
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}