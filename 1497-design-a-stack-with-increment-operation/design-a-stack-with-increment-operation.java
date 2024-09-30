class CustomStack {
    private int[] stack;
    private int[] incremental;
    private int size;

    public CustomStack(int maxSize) {
        stack = new int[maxSize];
        incremental = new int[maxSize];
        size = 0;
    }

    public void push(int x) {
        if (size < stack.length) {
            stack[size] = x;
            size++;
        }
    }

    public int pop() {
        if (size == 0) {
            return -1;
        }
        size--;
        int res = stack[size] + incremental[size];
        if (size > 0) {
            incremental[size - 1] += incremental[size]; // propagate the increment value downwards
        }
        incremental[size] = 0; // reset increment after applying
        return res;
    }

    public void increment(int k, int val) {
        int limit = Math.min(k, size); // only increment the first 'k' or all elements in the stack
        if (limit > 0) {
            incremental[limit - 1] += val;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */
