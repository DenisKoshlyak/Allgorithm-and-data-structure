import java.io.*;
import java.util.*;

class Node {
    public int iData;
    public Node leftChild;
    public Node rightChild;
    public Node parent;

    public Node(int id){
        iData = id;
    }

    public void displayNode() {
        System.out.print('{');
        System.out.print(iData);
        System.out.print("} ");
    }
}

class Tree {
    private Node root;
    private int nElems;
    int index;
    private int[] path = new int[10];

    public Tree() {
        root = null;
        nElems = 0;
    }

    public boolean insert(int id) {
        Node newNode = new Node(id);
        if(root==null) {
            root = newNode;
            nElems++;
            return true;
        }
        else {
            path = new int[10];
            defineAPath(++nElems);
            Node current = root;
            Node parent;
            while(--index >= 0) {
                parent = current;
                if(path[index] == 0) {
                    current = current.leftChild;
                    if(current == null) {
                        current = newNode;
                        parent.leftChild = current;
                        current.parent = parent;
                        trickleUp(current);
                    }
                }
                else {
                    current = current.rightChild;
                    if(current == null) {
                        current = newNode;
                        parent.rightChild = current;
                        current.parent = parent;
                        trickleUp(current);
                    }
                }
            }
            return true;
        }
    }

    private void trickleUp(Node current){
        while(true){
            if(current.parent != null && current.iData > current.parent.iData){
                int temp = current.parent.iData;
                current.parent.iData = current.iData;
                current.iData = temp;
                current = current.parent;
            }
            else
                break;
        }
    }
    private void defineAPath(int num){
        index = -1;
        while(num > 0){
            path[++index] = num % 2;
            num /= 2;
        }
    }

    public void removeMax(){
        Node current = findLowerAndDeleteLink();
        if(current == null)
            return;
        root.iData = current.iData;
        trickleDown();
    }

    private Node findLowerAndDeleteLink(){
        if(nElems == 1) {
            root = null;
            return null;
        }
        defineAPath(nElems--);
        int mark = -1;
        Node current = root;
        while(--index >= 0) {
            if (path[index] == 0) {
                current = current.leftChild;
                mark = 0;
            }
            else {
                current = current.rightChild;
                mark = 1;
            }
        }
        if(mark == 0)
            current.parent.leftChild = null;
        else if(mark == 1)
            current.parent.rightChild = null;
        return current;
    }

    public void trickleDown() {
        Node largerChild = root;
        index = 0;
        Node current = root;
        while (current != null) {
            Node leftChild = current.leftChild;
            Node rightChild = current.rightChild;
            if (leftChild != null && rightChild != null) {
                if (leftChild.iData > rightChild.iData)
                    largerChild = leftChild;
                else
                    largerChild = rightChild;
            }
            else if(leftChild != null)
                largerChild = leftChild;
            if(current.iData < largerChild.iData){
                int temp = current.iData;
                current.iData = largerChild.iData;
                largerChild.iData = temp;
                current = largerChild;
            }
            else
                break;
        }
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
    private Tree tr;

    public PriorityQ() {
        tr = new Tree();
    }

    public boolean insert(int item) {
        return tr.insert(item);
    }

    public void remove() {
        tr.removeMax();
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

    public static char getChar()throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}