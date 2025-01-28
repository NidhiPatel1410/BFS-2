// In this bfs approach, just maintain a two boolean variables to denote whenever x and y found. At each node, check if it has two childrens and 
// if one equals x and other equals y than they are not cousins since they have same parents, so false. Then check if the node itself if x or y, 
// change boolean variable x or y true accordingly. Add it's left and right child in queue and continue. After each level check, if both true, 
// means cousins, so return true. If either one is true, after one level completed than other is still not found that means both are not on same 
// level, so return false.

// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> q = new LinkedList<>();
        boolean x_found = false;
        boolean y_found = false;
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                // Check if node is having 2 childrens,
                if (curr.left != null && curr.right != null) {
                    // Check if one is x and other is y
                    if (curr.left.val == x && curr.right.val == y) {
                        // If so, return false because they have same parent
                        return false;
                    }
                    // Check if one is y and other is x
                    else if (curr.left.val == y && curr.right.val == x) {
                        // If so, return false because they have same parent
                        return false;
                    }
                }
                // Check if the node itself is either x or y, update x_found or y_found
                // accordingly
                if (curr.val == x) {
                    x_found = true;
                }
                if (curr.val == y) {
                    y_found = true;
                }
                // Add left and right child to the queue if present
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
            // After completing of each level, check if both true, means diff parent and
            // same level --> cousins
            if (x_found == true && y_found == true) {
                return true;
            }
            // Else if either one is true, not same level --> not cousins
            if (x_found == true || y_found == true) {
                return false;
            }
        }
        return true;
    }
}

// In this dfs approach, check if current node is x or y, modify x_parent,
// y_parent, x_lvl, y_lvl accordingly. At last check, (x_parent != y_parent) &&
// (x_lvl == y_lvl);

// Time Complexity : O(n)
// Space Complexity : O(h)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    TreeNode x_parent, y_parent;
    int x_lvl, y_lvl;

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        // Declare parent and level variables
        x_parent = null;
        y_parent = null;
        x_lvl = -1;
        y_lvl = -1;
        // Call recursive method
        dfs(root, null, 0, x, y);
        // Check if parent not equal but level is equal that means cousins
        return (x_parent != y_parent) && (x_lvl == y_lvl);
    }

    private void dfs(TreeNode root, TreeNode parent, int level, int x, int y) {
        // Base case
        if (root == null || (x_lvl != -1 && y_lvl != -1)) {
            return;
        }
        // Check if node value either x or y, change parent and level accordingly
        if (root.val == x) {
            x_parent = parent;
            x_lvl = level;
        }
        if (root.val == y) {
            y_parent = parent;
            y_lvl = level;
        }
        // Recursively go left and right
        dfs(root.left, root, level + 1, x, y);
        dfs(root.right, root, level + 1, x, y);
    }
}