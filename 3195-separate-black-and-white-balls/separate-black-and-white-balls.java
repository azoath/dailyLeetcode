class Solution {
    public long minimumSteps(String s) {
        char[] charArray = s.toCharArray();  // Convert string to a char array for easy modification
        int n = charArray.length;
        int slow = 0;
        int fast = 0;
        long count = 0;  // To track the number of swaps

        // Move the fast pointer through the entire string
        while (fast < n) {
            // If fast points to a '0', and slow points to a '1', we need to swap
            if (charArray[slow] == '1' && charArray[fast] == '0') {
                // Swap the characters
                swap(charArray, slow, fast);
                count += (fast - slow);  // The number of steps is the distance between slow and fast
                slow++;  // After the swap, increment the slow pointer to continue processing
            }

            // If slow points to a '0', it is in the correct place, so move it forward
            if (charArray[slow] == '0') {
                slow++;
            }
            
            // Move the fast pointer to keep searching for the next '0'
            fast++;
        }

        return count;
    }

    // Helper function to swap characters in a char array
    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
