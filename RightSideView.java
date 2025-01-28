// bfs
// In this problem, we are doing bfs traversal, and if it is the last node of each level, we are adding it to the result list

// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // Base Case
        if (root == null) {
            return new ArrayList<>();
        }
        // Result list
        List<Integer> result = new ArrayList<>();
        // Queue for bfs
        Queue<TreeNode> q = new LinkedList<>();
        // Add root to it
        q.add(root);
        // Run a loop for all nodes
        while (!q.isEmpty()) {
            // Calc size of queue for the level order traversal
            int size = q.size();
            // Run a loop for that level(till that size)
            for (int i = 0; i < size; i++) {
                // Poll the current node
                TreeNode curr = q.poll();
                // Check if i=size-1 that means we are on last node in that level
                if (i == size - 1) {
                    // Add that node in result list
                    result.add(curr.val);
                }
                // Add it's left and right child to the queue if present
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
        }
        // Return result
        return result;
    }
}

// Using dfs --> following [Root R L]
// In this approach, just maintain a level for each node, check if the size of
// result list == level, yes than add the node to result list
// Time Complexity : O(n)
// Space Complexity : O(h)
class Solution {
    List<Integer> result;

    public List<Integer> rightSideView(TreeNode root) {
        // Base Case
        if (root == null) {
            return new ArrayList<>();
        }
        result = new ArrayList<>();
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (result.size() == level) {
            result.add(root.val);
        }
        dfs(root.right, level + 1);
        dfs(root.left, level + 1);
    }
}

// Using dfs --> following [Root L R]
// In this approach, just maintain a level for each node, check if the size of
// result list == level, yes than add the node to result list and
// if no than, update the node at that level index in result list
// Time Complexity : O(n)
// Space Complexity : O(h)
class Solution {
    List<Integer> result;

    public List<Integer> rightSideView(TreeNode root) {
        // Base Case
        if (root == null) {
            return new ArrayList<>();
        }
        result = new ArrayList<>();
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (result.size() == level) {
            result.add(root.val);
        } else {
            result.set(level, root.val);
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);

    }
}
