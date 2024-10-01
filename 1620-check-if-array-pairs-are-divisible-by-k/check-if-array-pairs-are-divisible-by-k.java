class Solution {
    public boolean canArrange(int[] arr, int k) {

        int[] remainderFreq = new int[k];
        
        // Step 1: Calculate remainder frequencies
        for (int num : arr) {
            int remainder = ((num % k) + k) % k; // Handle negative remainders
            remainderFreq[remainder]++;
        }
        
        // Step 2: Check if pairs can be formed
        for (int i = 1; i < k; i++) {
            if (remainderFreq[i] != remainderFreq[k - i]) {
                return false; // For remainders i and k-i, the counts must be equal
            }
        }
        
        // Step 3: Special case for remainder 0 (must be even)
        return remainderFreq[0] % 2 == 0;

        // int slow = 0;
        // int fast = 1;
        // while(slow<arr.length-1){
        //     while(fast < arr.length){
        //     int sum = 0;
        //     sum = arr[slow] + arr[fast];
        //     if(sum % k == 0){
        //         return true;
        //     }
        //     fast++;
        //     }
        //     slow++;
        // }
        // return false;
    }
}