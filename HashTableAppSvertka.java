import java.io.*;

class DataItem {
    private int iData;
    public DataItem(int ii) {
        iData = ii;
    }

    public int getKey() {
        return iData;
    }
}

class HashTable {
    private DataItem[] hashArray;
    private static int arraySize;
    private DataItem nonItem;

    public HashTable(int size) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1);
    }
    public void displayTable() {
        System.out.print("Table: ");
        for(int j=0; j<arraySize; j++) {
            if(hashArray[j] != null)
                System.out.print(hashArray[j].getKey() + " ");
            else
                System.out.print("** ");
        }
        System.out.println("");
    }

    public static int hashFunc(int key) {
        int hashVal = 0;
        int pow = 10;
        if(key > arraySize) {
            while (key > 10) {
                if (key % pow < arraySize)
                    pow *= 10;
                else {
                    hashVal += key % (pow /= 10);
                    key /= pow;
                    pow = 10;
                }
            }
            hashVal += key;
        }
        else
            hashVal = key % arraySize;
        return hashVal % arraySize;
    }

    public void insert(DataItem item) {
        int key = item.getKey();
        int multiplier = 1;
        int hashVal = hashFunc(key);
        while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            hashVal += Math.pow(multiplier++, 2);
            hashVal %= arraySize;
        }
        hashArray[hashVal] = item;
    }

    public DataItem delete(int key) {
        int hashVal = hashFunc(key);
        int multiplier = 1;
        while(hashArray[hashVal] != null) {
            if(hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal];
                hashArray[hashVal] = nonItem;
                return temp;
            }
            hashVal += Math.pow(multiplier++, 2);
            hashVal %= arraySize;
        }
        return null;
    }

    public DataItem find(int key) {
        int hashVal = hashFunc(key);
        int multiplier = 1;
        while(hashArray[hashVal] != null) {
            if(hashArray[hashVal].getKey() == key)
                return hashArray[hashVal];
            hashVal += Math.pow(multiplier++, 2);
            hashVal %= arraySize;
        }
        return null;
    }
}

class HashTableSvertka {
    public static void main(String[] args) throws IOException {
        DataItem aDataItem;
        int size;
        int aKey;
        System.out.print("Enter size of hash table: ");
        size = getInt();
        HashTable theHashTable = new HashTable(size);
        while(true) {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete, or find: ");
            char choice = getChar();
            switch(choice) {
                case 's':
                    theHashTable.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    aDataItem = new DataItem(aKey);
                    theHashTable.insert(aDataItem);
                    break;
                case 'd':
                    System.out.print("Enter key to delete: ");
                    aKey = getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem = theHashTable.find(aKey);
                    if(aDataItem != null) {
                        System.out.println("Found " + aKey);
                    }
                    else
                        System.out.println("Could not find " + aKey);
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