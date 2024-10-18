class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascalList = new ArrayList<>();

        // Iterate through each row
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();

            // The first and last value in every row is 1
            row.add(1);
            
            // For the inner values of the row, calculate based on the previous row
            for (int j = 1; j < i; j++) {
                int value = pascalList.get(i - 1).get(j - 1) + pascalList.get(i - 1).get(j);
                row.add(value);
            }
            
            // Add 1 at the end of each row if it's not the first row
            if (i > 0) {
                row.add(1);
            }

            // Add the row to the pascal list
            pascalList.add(row);
        }

        return pascalList;
    }
}
