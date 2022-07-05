import java.io.*;

class DataItem {
    private String iData;
    public DataItem(String ii) {
        iData = ii;
    }

    public String getKey() {
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
        nonItem = new DataItem("-1");
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

    public static int hashFunc(String key) {
        int hashVal = 0;
        for(int j=0; j<key.length(); j++) {
            int letter = key.charAt(j) - 96;
            hashVal = (hashVal * 27 + letter) % arraySize;
        }
        return hashVal;
    }

    public void insert(DataItem item) {
        String key = item.getKey();
        int multiplier = 1;
        int hashVal = hashFunc(key);
        while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != "EMPTY") {
            hashVal += Math.pow(multiplier++, 2);
            hashVal %= arraySize;
        }
        hashArray[hashVal] = item;
    }

    public DataItem delete(String key) {
        int hashVal = hashFunc(key);
        int multiplier = 1;
        while(hashArray[hashVal] != null) {
            if(hashArray[hashVal].getKey().equals(key)) {
                DataItem temp = hashArray[hashVal];
                hashArray[hashVal] = nonItem;
                return temp;
            }
            hashVal += Math.pow(multiplier++, 2);
            hashVal %= arraySize;
        }
        return null;
    }

    public DataItem find(String key) {
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

class HashTableAppQuad {
    public static void main(String[] args) throws IOException {
        DataItem aDataItem;
        int size;
        String aKey;
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
                    aKey = getString();
                    aDataItem = new DataItem(aKey);
                    theHashTable.insert(aDataItem);
                    break;
                case 'd':
                    System.out.print("Enter key to delete: ");
                    aKey = getString();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getString();
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