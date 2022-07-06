import java.io.*;

class Node {
    private int iData;
    public Node(int key) {
        iData = key;
    }
    public int getKey() {
        return iData;
    }
    public void setKey(int id) {
        iData = id;
    }
}
class Heap {
    private Node[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap(int mx) {
        maxSize = mx;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean insert(int key) {
        if (currentSize == maxSize)
            return false;
        Node newNode = new Node(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }

    public void trickleUp(int index) {
        int parent = (index - 1) / 2;
        Node bottom = heapArray[index];
        while (index > 0 &&
                heapArray[parent].getKey() < bottom.getKey()) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    public Node remove() {
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    public void trickleDown(int index) {
        int largerChild;
        Node top = heapArray[index];
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            if (rightChild < currentSize && heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
                largerChild = rightChild;
            else
                largerChild = leftChild;

            if (top.getKey() >= heapArray[largerChild].getKey())
                break;
            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }
        heapArray[index] = top;
    }

    public boolean change(int index, int newValue) {
        if (index < 0 || index >= currentSize)
            return false;
        int oldValue = heapArray[index].getKey();
        heapArray[index].setKey(newValue);
        if (oldValue < newValue)
            trickleUp(index);
        else
            trickleDown(index);
        return true;
    }

    public void displayHeap() {
        System.out.print("heapArray: ");
        for (int m = 0; m < currentSize; m++)
            if (heapArray[m] != null)
                System.out.print(heapArray[m].getKey() + " ");
            else
                System.out.print("-- ");
        System.out.println();
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;
        String dots = "...............................";
        System.out.println(dots + dots);
        while (currentSize > 0) {
            if (column == 0)
                for (int k = 0; k < nBlanks; k++)
                    System.out.print(' ');
            System.out.print(heapArray[j].getKey());
            if (++j == currentSize)
                break;
            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            }
            else
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(" ");
        }
        System.out.println("\n" + dots + dots);
    }
}

class PriorityQ {
    private int maxSize;
    private int nItems;
    private Heap hp;

    public PriorityQ(int s) {
        maxSize = s;
        hp = new Heap((maxSize));
        nItems = 0;
    }

    public boolean insert(int item) {
        return hp.insert(item);
    }

    public Node remove() {
        return hp.remove();
    }

    public void displayQ() {
        hp.displayHeap();
    }

    public boolean isEmpty() {
        return hp.isEmpty();
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

}
class PriorityHeapApp{
    public static void main(String[] args) throws IOException {
        int value, value2;
        PriorityQ thePQ = new PriorityQ(31);
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