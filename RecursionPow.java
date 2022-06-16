import java.util.Scanner;

class RecursionPow{
    private int tempVal;
    private int tempStepen;
    public static Scanner sc = new Scanner(System.in);
    static int val = sc.nextInt();
    static int stepen = sc.nextInt();

    public int power(int val, int stepen){
        tempVal = val * val;
        tempStepen = stepen / 2;
        if(tempStepen > 1)
            power(tempVal, tempStepen);
        if(stepen % 2 != 0)
            tempVal *= val;
        return tempVal;
    }
    
    public static void main(String args[]){
        RecursionPow vvs = new RecursionPow();
        System.out.println(val + " v stepeni " + stepen + " ravnyaetsya " + vvs.power(val, stepen));
    }
}