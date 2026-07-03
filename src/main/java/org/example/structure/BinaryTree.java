package org.example.structure;

import java.util.*;

public class BinaryTree {

    // Dummy Node definition for reference
    static class Node {
        int data;
        int val; // Some methods use val, some use data based on your input
        Node left, right;
        Node(int data) {
            this.data = data;
            this.val = data;
        }
    }

    // Dummy TreeNode definition for reference
    static class TreeNode {
        int val;
        TreeNode left, right;
    }

    //================ level order traversal ======================//
    /**
     * Tree Structure:
     * 1
     * / \
     * 2   3
     * * Input: Node root (points to node 1)
     * Expected Output: 1 2 3
     * Time Complexity: O(N) where N is the number of nodes
     */
    public static void BFS(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            System.out.print(curr.data + " ");
            if(curr.left != null)
                queue.add(curr.left);
            if(curr.right != null)
                queue.add(curr.right);
        }
    }

    //==================search key====================//
    /**
     * Tree Structure:
     * 4
     * / \
     * 2   7
     * * Input: Node root (points to node 4), int key = 7
     * Expected Output: true
     * Time Complexity: O(N)
     */
    public static boolean search(Node root, int key) {
        if (root == null)
            return false;
        if (root.val == key || root.data == key)
            return true;
        return search(root.left, key) || search(root.right, key);
    }

    //by BSF Search key
    /**
     * Tree Structure:
     * 4
     * / \
     * 2   7
     * * Input: Node root (points to node 4), int key = 9
     * Expected Output: false
     * Time Complexity: O(N)
     */
    public static boolean searchBFS(Node root, int key) {
        if (root == null) return false;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            if(curr.data == key || curr.val == key){
                return true;
            }
            if(curr.left != null)
                queue.add(curr.left);
            if(curr.right != null)
                queue.add(curr.right);
        }
        return false;
    }

    // --------------------------------------return output as list -------------------------------
    /**
     * Tree Structure:
     * 1
     * / \
     * 2   3
     * * Input: Node root (points to node 1)
     * Expected Output: [[1], [2, 3]]
     * Time Complexity: O(N)
     */
    public ArrayList<ArrayList<Integer>> levelOrder(Node root) {
        if (root == null) return null;
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> currentLevel = new ArrayList<Integer>();
            for(int i = 0; i < size; i++) {
                Node curr = queue.poll();
                currentLevel.add(curr.data);
                if(curr.left != null) queue.add(curr.left);
                if(curr.right != null) queue.add(curr.right);
            }
            res.add(currentLevel);
        }
        return res;
    }

    // =======-====================Reverse level order================
    // 1) Approach using stack
    public static class ReverseLevelOrder {
        /**
         * Tree Structure:
         * 1
         * / \
         * 2   3
         * * Input: Node root (points to node 1)
         * Expected Output: 2 3 1
         * Time Complexity: O(N)
         */
        public static void reverseLevelOrder(Node root) {
            if (root == null) return;
            Queue<Node> queue = new LinkedList<>();
            Stack<Node> stack = new Stack<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node curr = queue.poll();
                stack.push(curr);

                if (curr.right != null)
                    queue.add(curr.right); // IMP insert right first bcos of stack
                if (curr.left != null)
                    queue.add(curr.left);
            }
            while (!stack.isEmpty()) {
                System.out.print(stack.pop().val + " ");
            }
        }
    }

    // 2) Approach using List
    /**
     * Tree Structure:
     * 1
     * / \
     * 2   3
     * * Input: Node root (points to node 1)
     * Expected Output: [[2, 3], [1]]
     * Time Complexity: O(N^2) due to shifting insertions in ArrayList at index 0
     */
    public static List<List<Integer>> reverseLevelOrderWithList(Node root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                level.add(curr.val);
                if (curr.left != null)
                    queue.add(curr.left);
                if (curr.right != null)
                    queue.add(curr.right);
            }
            result.add(0, level); // add level at 0 and shift previously added to next
        }
        return result;
    }

    // 3) Using LinkedList to avoid O(N) extra due to list.add(0,level)
    public static class ReverseLevelOrderOptimized {
        /**
         * Tree Structure:
         * 1
         * / \
         * 2   3
         * * Input: Node root (points to node 1)
         * Expected Output: [[2, 3], [1]]
         * Time Complexity: O(N)
         */
        public static List<List<Integer>> reverseLevelOrder(Node root) {
            LinkedList<List<Integer>> result = new LinkedList<>();
            if (root == null) return result;
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> level = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Node curr = queue.poll();
                    level.add(curr.val);
                    if (curr.left != null)
                        queue.add(curr.left);
                    if (curr.right != null)
                        queue.add(curr.right);
                }
                result.addFirst(level); // O(1) bcos it adds to head, all pointer gets updated
            }
            return result;
        }
    }

    //==================The maximum depth or height of the tree is the number of node===========
    /**
     * Tree Structure:
     * 1
     * /
     * 2
     * * Input: Node root (points to node 1)
     * Expected Output: 2
     * Time Complexity: O(N)
     */
    int heightByNodes(Node root) {
        if (root == null)
            return 0;
        int leftHeight = heightByNodes(root.left);
        int rightHeight = heightByNodes(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    //==================The maximum depth or height of the tree is the number of edge===========
    /**
     * Tree Structure:
     * 1
     * /
     * 2
     * * Input: Node root (points to node 1)
     * Expected Output: 1
     * Time Complexity: O(N)
     */
    int heightByEdges(Node root) {
        if (root == null)
            return -1; // only change
        int leftHeight = heightByEdges(root.left);
        int rightHeight = heightByEdges(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    //===============longest Diameter length from any node =====================================
    int diameter = 0;
    /**
     * Tree Structure:
     * 1
     * / \
     * 2   3
     * /
     * 4
     * * Input: Node root (points to node 1)
     * Expected Output: 3 (Path: 4 -> 2 -> 1 -> 3)
     * Time Complexity: O(N)
     */
    public int diameter(Node root) {
        diameter = 0;
        heightForDiameter(root);
        return diameter;
    }

    public int heightForDiameter(Node node) {
        if (node == null)
            return 0;
        int left = heightForDiameter(node.left);
        int right = heightForDiameter(node.right);
        diameter = Math.max(diameter, left + right);
        return 1 + Math.max(left, right);
    }

    //================MIRROR TREE====================================
    /**
     * Tree Structure:
     * 1               1
     * / \     ==>     / \
     * 2   3           3   2
     * * Input: Node root (points to original node 1)
     * Expected Output: Root node of the inverted structural copy
     * Time Complexity: O(N)
     */
    public Node mirror(Node root) {
        if (root == null) return null;
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
        return root;
    }

    //---------------------- BFS way------
    /**
     * Tree Structure:
     * 1               1
     * / \     ==>     / \
     * 2   3           3   2
     * * Input: Node root (points to original node 1)
     * Expected Output: Modifies tree in-place to its mirror structure
     * Time Complexity: O(N)
     */
    public void mirrorBFS(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            Node temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }

    //======================================================================
    //⭐1) Inorder (Left → Root → Right)
    /**
     * Tree Structure (BST):
     * 2
     * / \
     * 1   3
     * * Input: TreeNode root (points to node 2)
     * Expected Output: 123 (Prints sequentially)
     * Time Complexity: O(N)
     */
    void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val);
        inorder(root.right);
    }

    //⭐2) Preorder (Root → Left → Right)
    /**
     * Tree Structure:
     * 1
     * / \
     * 2   3
     * * Input: TreeNode root (points to node 1)
     * Expected Output: 123
     * Time Complexity: O(N)
     */
    void preorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val);
        preorder(root.left);
        preorder(root.right);
    }

    //⭐3) Postorder (Left → Right → Root)
    /**
     * Tree Structure:
     * 1
     * / \
     * 2   3
     * * Input: TreeNode root (points to node 1)
     * Expected Output: 231
     * Time Complexity: O(N)
     */
    void postorder(TreeNode root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val);
    }

    //================LEFT VIEW=============
    /**
     * Tree Structure:
     * 1
     * / \
     * 2   3
     * \
     * 4
     * * Input: Node root (points to node 1)
     * Expected Output: [1, 2, 4]
     * Time Complexity: O(N)
     */
    public ArrayList<Integer> leftView(Node root) {
        Queue<Node> q = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                Node c = q.poll();
                if(i==0){
                    ans.add(c.data);
                }
                if(c.left!=null)
                    q.offer(c.left);
                if(c.right!=null)
                    q.offer(c.right);
            }
        }
        return ans;
    }

    //===================TOP view===================
    static class Pair {
        int hd;
        Node node;
        Pair(Node node,int hd){
            this.hd = hd;
            this.node = node;
        }
    }
    /**
     * Tree Structure:
     * 1
     * / \
     * 2   3
     * \
     * 4
     * * Input: Node root (points to node 1)
     * Expected Output: [2, 1, 3]  (Node 4 is hidden behind 1)
     * Time Complexity: O(N log N)
     */
    public ArrayList<Integer> topView(Node root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root==null) return res;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root,0));
        Map<Integer,Integer> map = new TreeMap<>();
        while(!q.isEmpty()){
            Pair pair = q.poll();
            Node curr = pair.node;
            int hd = pair.hd;
            if(!map.containsKey(hd)){
                map.put(hd,curr.data);
            }
            if(curr.left!=null){
                q.add(new Pair(curr.left,hd-1));
            }
            if(curr.right!=null){
                q.add(new Pair(curr.right,hd+1));
            }
        }
        for(int value : map.values()){
            res.add(value);
        }
        return res;
    }

    //================== Bottom View================
    /**
     * Tree Structure:
     * 1
     * / \
     * 2   3
     * \
     * 4
     * * Input: Node root (points to node 1)
     * Expected Output: [2, 4, 3] (Node 4 overwrites node 1 from the bottom perspective)
     * Time Complexity: O(N log N)
     */
    public ArrayList<Integer> bottomView(Node root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root==null) return res;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root,0));
        Map<Integer,Integer> map = new TreeMap<>();
        while(!q.isEmpty()){
            Pair pair = q.poll();
            Node curr = pair.node;
            int hd = pair.hd;
            map.put(hd,curr.data);
            if(curr.left!=null){
                q.add(new Pair(curr.left,hd-1));
            }
            if(curr.right!=null){
                q.add(new Pair(curr.right,hd+1));
            }
        }
        for(int value : map.values()){
            res.add(value);
        }
        return res;
    }

    //===============Balanced tree ==========================
    boolean isBalanced;
    /**
     * Tree Structure (Unbalanced):
     * 1
     * /
     * 2
     * /
     * 3
     * * Input: Node root (points to node 1)
     * Expected Output: false
     * Time Complexity: O(N)
     */
    boolean isBalanced(Node root) {
        isBalanced = true;
        solve(root);
        return isBalanced;
    }

    int solve(Node root){
        if(root == null) return 0;
        int l = solve(root.left);
        int r = solve(root.right);
        if(Math.abs(l-r) > 1)
            isBalanced = false;
        return Math.max(l,r) + 1;
    }

    //===================== ZigZag ==========================
    /**
     * Tree Structure:
     * 1
     * /   \
     * 2     3
     * / \   / \
     * 4   5 6   7
     * * Input: Node root
     * Expected Output: [1, 3, 2, 4, 5, 6, 7]
     * Time Complexity: O(N)
     */
    ArrayList<Integer> zigZagTraversal(Node root) {
        ArrayList<Integer> ans = new ArrayList<Integer> ();
        if(root == null) return ans;
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        int c = 0;
        while(!q.isEmpty()){
            int size = q.size();
            ArrayList<Integer> list = new ArrayList<Integer> ();
            for(int i=0; i<size; i++){
                Node curr = q.poll();
                list.add(curr.data);
                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
            }
            if(c%2 != 0)
                Collections.reverse(list);
            for(int i=0; i<list.size(); i++){
                ans.add(list.get(i));
            }
            c++;
        }
        return ans;
    }

    //======================= Traverse diagonal ===========================================
    /**
     * Tree Structure:
     * 1
     * / \
     * 2   3
     * /   /
     * 4   5
     * * Input: Node root (points to node 1)
     * Expected Output: [1, 3, 2, 5, 4]
     * Time Complexity: O(N)
     */
    public ArrayList<Integer> diagonal(Node root) {
        Queue<Node> q = new LinkedList<Node>();
        Node temp = root;
        ArrayList<Integer> list = new ArrayList<Integer> ();
        if(root == null) return list;
        q.add(temp);
        while(!q.isEmpty()){
            temp = q.poll();
            while(temp != null){
                if(temp.left != null)
                    q.add(temp.left);
                list.add(temp.data);
                temp = temp.right;
            }
        }
        return list;
    }

    //===================Boundary traversal ==================================
    /**
     * Tree Structure:
     * 1
     * /   \
     * 2     3
     * / \   / \
     * 4   5 6   7
     * * Input: Node root
     * Expected Output: [1, 2, 4, 5, 6, 7, 3]
     * Time Complexity: O(N)
     */
    ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root==null) return res;
        if(!isLeaf(root))
            res.add(root.data);
        addLeftNode(root,res);
        addLeafNode(root,res);
        addRightNode(root,res);
        return res;
    }

    void addLeftNode(Node root,ArrayList<Integer> res ){
        Node node = root.left;
        while(node!=null){
            if(!isLeaf(node))
                res.add(node.data);
            if(node.left==null)
                node = node.right;
            else
                node = node.left;
        }
    }

    void addLeafNode(Node root,ArrayList<Integer> res ){
        if(root==null)
            return;
        if(isLeaf(root))
            res.add(root.data);
        addLeafNode(root.left,res);
        addLeafNode(root.right,res);
    }

    void addRightNode(Node root,ArrayList<Integer> res ){
        Node node = root.right;
        Stack<Integer> stack = new Stack<>();
        while(node!=null){
            if(!isLeaf(node)){
                stack.add(node.data);
            }
            if(node.right!=null)
                node = node.right;
            else
                node = node.left;
        }
        while(!stack.isEmpty()){
            res.add(stack.pop());
        }
    }

    boolean isLeaf(Node node){
        return node.left==null && node.right==null ? true : false;
    }

    //=============IMP Construct BT from bracket String================
    static int start = 0;
    /**
     * Input: "4(2(3)(1))(6(5))"
     * Expected Output Tree Node Structure:
     * 4
     * /   \
     * 2     6
     * / \   /
     * 3   1 5
     * * Time Complexity: O(N)
     */
    public static Node treeFromString(String s) {
        if (s == null || s.length() == 0)
            return null;
        start = 0;
        return t2BT(s);
    }

    public static Node t2BT(String s) {
        if (start >= s.length())
            return null;
        if (s.charAt(start) == ')')
            return null;
        boolean negative = false;
        if (s.charAt(start) == '-') {
            negative = true;
            start++;
        }
        int num = 0;
        while (start < s.length() && Character.isDigit(s.charAt(start))) {
            int digit = Character.getNumericValue(s.charAt(start));
            num = num * 10 + digit;
            start++;
        }
        num = negative ? -num : num;
        Node root = new Node(num);
        if (start < s.length() && s.charAt(start) == '(') {
            start++;
            if (s.charAt(start) != ')') {
                root.left = t2BT(s);
            }
            start++;
        }
        if (start < s.length() && s.charAt(start) == '(') {
            start++;
            if (s.charAt(start) != ')') {
                root.right = t2BT(s);
            }
            start++;
        }
        return root;
    }

    //=========================BT to Linked LIST ===================
    class SolutionFlatten {
        Node prev = null;
        /**
         * Tree Structure:
         * 1
         * / \
         * 2   5
         * * Input: Node root (points to node 1)
         * Expected Output: Linked List chain style node: 1 -> 2 -> 5 (all left pointers nullified)
         * Time Complexity: O(N)
         */
        public void flatten(Node root) {
            if (root == null)
                return;
            flatten(root.right);
            flatten(root.left);
            root.right = prev;
            root.left = null;
            prev = root;
        }
    }

    // ---------------MARRIS WAY TO LL Space O(1) ---------
    class SolutionMorrisFlatten {
        /**
         * Input: Node root
         * Expected Output: Flattened matching linked list variant handled inside original object pointers
         * Time Complexity: O(N)
         */
        public void flatten(Node root) {
            Node curr = root;
            while (curr != null) {
                if (curr.left != null) {
                    Node prev = curr.left;
                    while (prev.right != null) {
                        prev = prev.right;
                    }
                    prev.right = curr.right;
                    curr.right = curr.left;
                    curr.left = null;
                }
                curr = curr.right;
            }
        }
    }

    //=========================BT TO DLL============================
    Node head=null, temp=null;
    /**
     * Tree Structure:
     * 10
     * /  \
     * 5    20
     * * Input: Node root
     * Expected Output: Head node points to 5. Linkages look like: 5 <=> 10 <=> 20
     * Time Complexity: O(N)
     */
    Node bToDLL(Node root) {
        head = null;
        temp = null;
        inorderToDLL(root);
        return head;
    }

    public void inorderToDLL(Node root){
        if(root==null) return;
        inorderToDLL(root.left);
        if(temp==null)
            head = root;
        else {
            temp.right = root;
            root.left = temp;
        }
        temp = root;
        inorderToDLL(root.right);
    }

    //================= SUM TREE========================
    /**
     * Tree Structure:
     * 10                30
     * /  \      ==>     /  \
     * 20  10            0    0
     * * Input: Node root
     * Expected Output: Tree mutated to values calculated via structural child additions
     * Time Complexity: O(N)
     */
    public void toSumTree(Node root) {
        t(root);
    }

    public int t(Node root){
        if(root == null ) return 0;
        int sum = t(root.left) + t(root.right);
        int oldValue = root.data;
        root.data = sum;
        return sum + oldValue;
    }

    //========================Check if BT is SUM TREE===============================
    /**
     * Tree Structure:
     * 26
     * /  \
     * 10   3
     * /  \   \
     * 4    6   3
     * * Input: Node root (points to node 26)
     * Expected Output: true  (since 10 = 4 + 6, 3 = 3, and 26 = 10 + 4 + 6 + 3 + 3)
     * Time Complexity: O(N)
     */
    public boolean isSumTree(Node root) {
        return check(root) != -1;
    }

    private int check(Node root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return root.data;
        int left = check(root.left);
        int right = check(root.right);
        if (left == -1 || right == -1)
            return -1;
        int sum = left + right;
        if (root.data != sum)
            return -1;
        return sum + root.data;
    }

    // OR -------------------
    boolean is = true;
    public boolean isSumTreeAlternative(Node root) {
        is = true;
        tAlternative(root);
        return is;
    }

    public int tAlternative(Node root){
        if(root == null ) return 0 ;
        if(root.left == null && root.right==null) return root.data ;
        int sum = tAlternative(root.left) + tAlternative(root.right);
        int val = root.data;
        if(val!=sum) is=false;
        return val+sum;
    }

    //============================LARGEST SUMTREE FIND ===================================
    static int max;
    /**
     * Tree Structure:
     * 1
     * / \
     * -2   3
     * / \
     * 4   5
     * * Input: Node root
     * Expected Output: 7 (Derived via subtree rooted at -2: -2 + 4 + 5 = 7)
     * Time Complexity: O(N)
     */
    public static int findLargestSubtreeSum(Node root) {
        max = Integer.MIN_VALUE;
        sum(root);
        return max;
    }

    public static int sum(Node node){
        if(node==null) return 0;
        int sum = sum(node.left) + sum(node.right) + node.data;
        max = Math.max(max,sum);
        return sum;
    }

    //======================== Inorder/Preorder to BT===============================
    /**
     * Input arrays:
     * inorder[]  = {4, 2, 5, 1, 3}
     * preorder[] = {1, 2, 4, 5, 3}
     * * Expected Output Tree Root Node pointing to:
     * 1
     * /   \
     * 2     3
     * / \
     * 4   5
     * * Time Complexity: O(N)
     */
    public static Node buildTreePre(int inorder[], int preorder[]) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0; i<inorder.length; i++)
            map.put(inorder[i],i);
        return buildPre(preorder,0,preorder.length-1 , inorder,0,inorder.length-1,map);
    }

    public static Node buildPre(int preorder[], int preStart, int preEnd,
                                int inorder[], int inStart , int inEnd, HashMap<Integer,Integer> map)
    {
        if(preStart > preEnd || inStart > inEnd) return null;
        Node root = new Node(preorder[preStart]);
        int inRoot = map.get(root.data);
        int numsLeft = inRoot - inStart;
        root.left = buildPre(preorder, preStart+1, preStart + numsLeft,inorder,inStart,inRoot-1,map);
        root.right = buildPre(preorder, preStart+numsLeft+1, preEnd, inorder, inRoot+1,inEnd,map);
        return root;
    }

    //======================== Inorder/PostOrder to BT===============================
    /**
     * Input arrays:
     * inorder[]   = {4, 2, 5, 1, 3}
     * postorder[] = {4, 5, 2, 3, 1}
     * * Expected Output Tree Root Node pointing to:
     * 1
     * /   \
     * 2     3
     * / \
     * 4   5
     * * Time Complexity: O(N)
     */
    public static Node buildTreePost(int inorder[], int postorder[]) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0; i<inorder.length; i++)
            map.put(inorder[i],i);
        return buildPost(postorder,0,postorder.length-1 , inorder,0,inorder.length-1,map);
    }

    public static Node buildPost(int postorder[], int postStart, int postEnd,
                                 int inorder[], int inStart , int inEnd, HashMap<Integer,Integer> map)
    {
        if(postStart > postEnd || inStart > inEnd) return null;
        Node root = new Node(postorder[postEnd]);
        int inRoot = map.get(root.data);
        int numsLeft = inRoot - inStart;
        root.left = buildPost(postorder, postStart, postStart + numsLeft - 1, inorder, inStart, inRoot-1, map);
        root.right = buildPost(postorder, postStart + numsLeft, postEnd - 1, inorder, inRoot+1, inEnd, map);
        return root;
    }

    //==================== Leaf at same level ====================
    Integer leafLevel = null;
    /**
     * Tree Structure:
     * 1
     * / \
     * 2   3
     * * Input: Node root (points to node 1)
     * Expected Output: true (both leaf nodes 2 and 3 lie on level 1)
     * Time Complexity: O(N)
     */
    public boolean checkLeafLevel(Node root) {
        leafLevel = null;
        return dfsLeaf(root, 0);
    }

    private boolean dfsLeaf(Node root, int level) {
        if (root == null) return true;
        if (root.left == null && root.right == null) {
            if (leafLevel == null) {
                leafLevel = level;
                return true;
            }
            return level == leafLevel;
        }
        return dfsLeaf(root.left, level + 1) && dfsLeaf(root.right, level + 1);
    }

    //================= TWO TREE MIRROR (NODE) 1 =======================
    /**
     * Tree 1:         Tree 2:
     * 1               1
     * / \             / \
     * 2   3           3   2
     * * Input: Node t1 (Tree 1), Node t2 (Tree 2)
     * Expected Output: true
     * Time Complexity: O(N)
     */
    public boolean isMirror(Node t1, Node t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.data == t2.data)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    //=================TWO MIRROR TREE (ARRAY) 2 ==========================================
    /**
     * Input arrays:
     * arr1[] = {1, 2, 3}
     * arr2[] = {1, 3, 2}
     * * Expected Output: true
     * Time Complexity: O(N)
     */
    public boolean isMirror(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        return check(arr1, arr2, 0, 0);
    }

    private boolean check(int[] arr1, int[] arr2, int i, int j) {
        if (i >= arr1.length && j >= arr2.length) return true;
        if (i >= arr1.length || j >= arr2.length) return false;
        if (arr1[i] != arr2[j]) return false;
        return check(arr1, arr2, 2*i + 1, 2*j + 2) && check(arr1, arr2, 2*i + 2, 2*j + 1);
    }

    //================TWO MIRROR TREE (ARRAY) GFG STYLE 3==========================================
    /**
     * Input variables:
     * n = 3, e = 2
     * A[] = {1, 2, 1, 3}  (Node 1 maps to children 2, 3)
     * B[] = {1, 3, 1, 2}  (Node 1 maps to children 3, 2)
     * * Expected Output: true
     * Time Complexity: O(E)
     */
    public boolean checkMirror(int n, int e, int[] A, int[] B) {
        Map<Integer, Stack<Integer>> map = new HashMap<>();
        for (int i = 0; i < 2 * e; i += 2) {
            int parent = A[i];
            int child = A[i + 1];
            map.putIfAbsent(parent, new Stack<>());
            map.get(parent).push(child);
        }
        for (int i = 0; i < 2 * e; i += 2) {
            int parent = B[i];
            int child = B[i + 1];
            if (!map.containsKey(parent) || map.get(parent).isEmpty())
                return false;
            if (map.get(parent).pop() != child)
                return false;
        }
        return true;
    }

    //=================== Duplicate Tree ============================================
    /**
     * Tree Structure:
     * 1
     * / \
     * 2   2
     * /   /
     * 3   3
     * * Input: Node root
     * Expected Output: List containing reference to Node 2 (Since subtree 2->3 appears twice)
     * Time Complexity: O(N)
     */
    List<Node> res;
    HashMap<String,Integer> map;
    public List<Node> printAllDups(Node root) {
        res = new ArrayList<Node>();
        map = new HashMap<>();
        serialize(root);
        return res;
    }

    public String serialize(Node root){
        if(root==null) return "#";
        String left = serialize(root.left);
        String right = serialize(root.right);
        String currentString = root.data + "," + left + "," + right;
        map.put(currentString, map.getOrDefault(currentString, 0) + 1);
        if(map.containsKey(currentString) && map.get(currentString) == 2)
            res.add(root);
        return currentString;
    }
}
