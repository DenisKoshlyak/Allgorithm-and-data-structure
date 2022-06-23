import java.io.*;

class DataItem
{
    public long dData;

    public DataItem(long dd) {
        dData = dd;
    }

    public void displayItem() {
        System.out.print("/"+dData);
    }
}

class Node {
    private static final int ORDER = 3;
    private int numItems;
    private Node parent;
    private Node childArray[] = new Node[ORDER];
    private DataItem itemArray[] = new DataItem[ORDER - 1];

    public void connectChild(int childNum, Node child) {
        childArray[childNum] = child;
        if (child != null)
            child.parent = this;
    }

    public Node disconnectChild(int childNum) {
        Node tempNode = childArray[childNum];
        childArray[childNum] = null;
        return tempNode;
    }

    public Node getChild(int childNum) {
        return childArray[childNum];
    }

    public Node getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return (childArray[0] == null) ? true : false;
    }

    public int getNumItems() {
        return numItems;
    }

    public DataItem getItem(int index) {
        return itemArray[index];
    }

    public boolean isFull() {
        return (numItems == ORDER - 1) ? true : false;
    }

    public int findItem(long key) {
        for (int j = 0; j < ORDER - 1; j++) {
            if (itemArray[j] == null)
                break;
            else if (itemArray[j].dData == key)
                return j;
        }
        return -1;
    }

    public int insertItem(DataItem newItem) {
        numItems++;
        long newKey = newItem.dData;
        long itsKey;
        if(itemArray[0] != null) {
            itsKey = itemArray[0].dData;
            if (newKey < itsKey) {
                itemArray[1] = itemArray[0];
                itemArray[0] = newItem;
            }
            else {
                itemArray[1] = newItem;
                return 1;
            }
        }
        else {
            itemArray[0] = newItem;
            return 0;
        }
        return 0;
    }

    public void removeItem() {
        DataItem temp = itemArray[numItems - 1];
        itemArray[numItems - 1] = null;
        numItems--;
    }

    public void displayNode() {
        for (int j = 0; j < numItems; j++)
            itemArray[j].displayItem();
        System.out.println("/");
    }
}

class Tree {
    private Node root=new Node();

    public int find(long key){
        Node curNode=root;
        int childNumber;
        while(true) {
            if((childNumber=curNode.findItem(key))!=-1)
                return childNumber;
            else if(curNode.isLeaf())
                return-1;
            else
                curNode=getNextChild(curNode,key);
        }
    }

    public void insert(long dValue){
        Node curNode=root;
        DataItem tempItem=new DataItem(dValue);
        while(true){
            if(curNode.isLeaf())
                break;
            else
                curNode=getNextChild(curNode,dValue);
        }
        if(!curNode.isFull())
            curNode.insertItem(tempItem);
        else{
            split(curNode, tempItem);
        }
    }

    private void split(Node thisNode, DataItem tempItem){
        DataItem[] itemArray = manualSort(thisNode, tempItem);
        thisNode.removeItem();
        thisNode.removeItem();
        Node parent, child1, child2;
        if(thisNode.getParent() == null){
            parent = new Node();
            child1 = new Node();
            root = parent;
            child1.insertItem(itemArray[2]);
            parent.connectChild(0, thisNode);
            parent.connectChild(1, child1);
            thisNode.insertItem(itemArray[0]);
            parent.insertItem(itemArray[1]);
        }
        else if((parent = thisNode.getParent()).isFull()){
                thisNode.insertItem(itemArray[0]);
                split(parent, itemArray[1]);
                Node grandSon0 = new Node();
                Node grandSon1 = new Node();
                if(thisNode == parent.getChild(2)) {
                    child2 = parent.disconnectChild(2);
                    grandSon0.insertItem(itemArray[0]);
                    grandSon1.insertItem(itemArray[2]);
                    parent.getParent().getChild(1).connectChild(0, grandSon0);
                    parent.getParent().getChild(1).connectChild(1, grandSon1);
                }
                else if(thisNode == parent.getChild(1)){
                    child2 = parent.disconnectChild(2);
                    grandSon0.insertItem(itemArray[2]);
                    grandSon1 = child2;
                    parent.getParent().getChild(1).connectChild(0, grandSon0);
                    parent.getParent().getChild(1).connectChild(1, grandSon1);
                }
                else{
                    child2 = parent.disconnectChild(2);
                    child1 = parent.disconnectChild(1);
                    grandSon0 = child1;
                    grandSon1 = child2;
                    child1 = new Node();
                    child1.insertItem(itemArray[2]);
                    parent.connectChild(1, child1);
                    parent.getParent().getChild(1).connectChild(0, grandSon0);
                    parent.getParent().getChild(1).connectChild(1, grandSon1);
                }
            }
        else if(thisNode == thisNode.getParent().getChild(0)){
            parent = thisNode.getParent();
            child1 = new Node();
            child2 = parent.disconnectChild(1);
            child1.insertItem(itemArray[2]);
            parent.connectChild(1, child1);
            parent.connectChild(2, child2);
            thisNode.insertItem(itemArray[0]);
            parent.insertItem(itemArray[1]);
        }
        else {
            parent = thisNode.getParent();
            child2 = new Node();
            child2.insertItem(itemArray[2]);
            parent.connectChild(2, child2);
            thisNode.insertItem(itemArray[0]);
            parent.insertItem(itemArray[1]);
        }
    }

    private DataItem[] manualSort(Node thisNode, DataItem tempItem){
        DataItem temp;
        DataItem[] itemArray = new DataItem[3];
        if(thisNode.getItem(1).dData < tempItem.dData) {
            itemArray[2] = tempItem;
            itemArray[1] = thisNode.getItem(1);
            itemArray[0] = thisNode.getItem(0);
        }
        else if(thisNode.getItem(0).dData < tempItem.dData ){
            itemArray[2] = thisNode.getItem(1);
            itemArray[1] = tempItem;
            itemArray[0] = thisNode.getItem(0);
        }
        else{
            itemArray[2] = thisNode.getItem(1);
            itemArray[1] = thisNode.getItem(0);
            itemArray[0] = tempItem;
        }
        return itemArray;
    }

    private int findPosition(Node thisNode){
        int idx;
        if(thisNode == thisNode.getParent().getChild(2))
            idx = 2;
        else if(thisNode == thisNode.getParent().getChild(1))
            idx = 1;
        else
            idx = 0;
        return idx;
    }

    public Node getNextChild(Node theNode,long theValue){
        int j;
        int numItems=theNode.getNumItems();
        for(j=0;j<numItems; j++){
            if(theValue<theNode.getItem(j).dData)
                return theNode.getChild(j);
        }
        return theNode.getChild(j);
    }
    public void displayTree(){
        recDisplayTree(root,0,0);
    }

    private void recDisplayTree(Node thisNode,int level,int childNumber){
        System.out.print("level="+level+" child="+childNumber+" ");
        thisNode.displayNode();
        int numItems=thisNode.getNumItems();
        for(int j=0;j<numItems+1;j++){
            Node nextNode=thisNode.getChild(j);
            if(nextNode!=null)
                recDisplayTree(nextNode,level+1,j);
            else
                return;
        }
    }


}
public class Tree23 {
    public static void main(String[] args) throws IOException {
        long value;

        Tree theTree = new Tree();

        theTree.insert(10);
        theTree.insert(20);
        theTree.insert(30);
        theTree.insert(40);
        theTree.insert(11);
        theTree.insert(21);
        theTree.insert(31);
        theTree.insert(41);
        theTree.insert(42);
        theTree.insert(43);


        while(true) {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete or find: ");
            char choice = getChar();
            switch(choice) {
                case 's':
                    theTree.displayTree();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = getInt();
                    theTree.insert(value);
                    break;
                case 'f':
                    System.out.print("Enter value to find: ");
                    value = getInt();
                    int found = theTree.find(value);
                    if(found != -1)
                        System.out.println("Found "+value);
                    else
                        System.out.println("Could not find "+value);
                    break;
                case 'd':
                    theTree = new Tree();
                    break;
                default:
                    System.out.print("Invalid entry\n");
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
