class ZadachaORance{
    private int[] array;
    
    public ZadachaORance(int[] array){
        this.array = array;
    }
    
    public boolean findCombination(int idx, int sum){
        if(idx < 0)
            return false;
        if(array[idx] == sum)
            return true;
        else if(sum < array[idx]){
            if(idx == 0)
                return false;
            return findCombination(idx - 1, sum);
        }
        else 
            return findCombination(idx - 1, sum) || findCombination(idx - 1, sum - array[idx]);
    }
    
    public static void main(String args[]){
        int[] array = {11, 8, 12, 9, 14, 16};
        ZadachaORance zor = new ZadachaORance(array);
        
        System.out.println(zor.findCombination(5, 18));
    }
}