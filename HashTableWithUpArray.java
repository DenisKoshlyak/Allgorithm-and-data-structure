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
    private int numItem = 0;
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

    public int hashFunc(int key)
    {
        return key % arraySize;
    }

    public void insert(DataItem item) {
        int key = item.getKey();
        double bound = 0.5;
        int multiplier = 1;
        int hashVal = hashFunc(key);
        while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            hashVal += Math.pow(multiplier++, 2);
            hashVal %= arraySize;
        }
        hashArray[hashVal] = item;
        numItem++;
        if(numItem >= arraySize * bound) {
            numItem = 0;
            rehash();
        }
    }

    private void rehash(){
        int oldSize = arraySize;
        arraySize = nextSimpleValue(arraySize);
        System.out.println("arraySize = " + arraySize);
        DataItem[] tempHashArray = new DataItem[arraySize];
        for(int i = 0; i < oldSize; i++){
            if(hashArray[i] != null && hashArray[i].getKey() != -1) {
                DataItem temp = hashArray[i];
                int key = temp.getKey();
                int hashVal = hashFunc(key);
                while(tempHashArray[hashVal] != null) {
                    ++hashVal;
                    hashVal %= arraySize;
                }
                tempHashArray[hashVal] = temp;
            }
        }
        hashArray = new DataItem[arraySize];
        for(int i = 0; i < arraySize; i++){
            if(tempHashArray[i] != null)
                insert(tempHashArray[i]);
        }
    }

    private int nextSimpleValue(int arraySize){
        arraySize = arraySize * 2;
        int count = 0;
        while(true){
            for(int i = 2; i < (int) Math.sqrt(arraySize); i++){
                if(arraySize % i == 0) {
                    count = 0;
                    break;
                }
                else count++;
            }
            if(count != 0)
                return arraySize;
            ++arraySize;
        }
    }
    public DataItem delete(int key) {
        int hashVal = hashFunc(key);
        int multiplier = 1;
        while(hashArray[hashVal] != null) {
            if(hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal];
                hashArray[hashVal] = nonItem;
                numItem--;
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

class HashTableWithUpArray{
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