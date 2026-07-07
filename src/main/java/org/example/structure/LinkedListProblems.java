package org.example.structure;
import java.util .*;

public class LinkedListProblems {
        static class Node {
            int data;
            int val; // for cloning consistency
            Node next, prev, bottom, random, arb;
            Node(int d) {
                this.data = d;
                this.val = d;
            }
        }

        static class ListNode {
            int val;
            ListNode next;

            ListNode(int x) {
                val = x;
            }
        }

        static class Pair<K, V> {
            K first;
            V second;

            Pair(K first, V second) {
                this.first = first;
                this.second = second;
            }
        }

        private static Node head;

        //==========================================================================//
        // REVERSE LINKED LIST
        //==========================================================================//

        /**
         * Input:  1 -> 2 -> 3 -> 4 -> null
         * Output: 4 -> 3 -> 2 -> 1 -> null
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        Node reverse(Node node) {
            Node prev = null;
            Node current = node;
            Node next = null;
            while (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            node = prev;
            return node;
        }

        //==========================================================================//
        // REVERSE A LINKED LIST IN GROUPS OF GIVEN SIZE
        //==========================================================================//

        /**
         * Input:  1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null, k = 2
         * Output: 2 -> 1 -> 4 -> 3 -> 6 -> 5 -> null
         * * Time Complexity:  O(N)
         * Space Complexity: O(N/k) -> Due to recursive call stack
         */
        public static Node reverse(Node node, int k) {
            Node current = node;
            Node next = null, prev = null;
            int c = 0;

            while (c < k && current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
                c++;
            }

            if (next != null) {
                node.next = reverse(next, k);
            }

            return prev;
        }

        //==========================================================================//
        // DETECT LOOP (Floyd's Cycle-Finding Algorithm)
        //==========================================================================//

        /**
         * Input:  1 -> 2 -> 3 -> 4 -> 5 -> (loops back to 3)
         * Output: true
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        public static boolean detectLoop(Node head) {
            Node fast = head, slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;

                if (fast == slow) {
                    return true;
                }
            }
            return false;
        }

        //==========================================================================//
        // REMOVE LOOP
        //==========================================================================//

        /**
         * Input:  1 -> 2 -> 3 -> 4 -> 5 -> (loops back to 3)
         * Output: 1 -> 2 -> 3 -> 4 -> 5 -> null (Loop unlinked)
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        public static void removeLoop(Node head) {
            Node fast = head, slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;

                if (fast == slow) {
                    break;
                }
            }

            if (fast == null || fast.next == null) {
                return;
            }

            slow = head;
            while (slow != fast) {
                fast = fast.next;
                slow = slow.next;
            }

            Node startNode = slow;
            Node temp = startNode;
            while (startNode != temp.next) {
                temp = temp.next;
            }
            temp.next = null;
        }

        //==========================================================================//
        // REMOVE DUPLICATES FROM SORTED LIST
        //==========================================================================//

        /**
         * Input:  1 -> 1 -> 2 -> 3 -> 3 -> null
         * Output: 1 -> 2 -> 3 -> null
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        Node removeDuplicatesSorted(Node head) {
            Node current = head;
            while (current != null) {
                if (current.next != null && current.data == current.next.data) {
                    current.next = current.next.next;
                } else {
                    current = current.next;
                }
            }
            return head;
        }

        //==========================================================================//
        // REMOVE DUPLICATES FROM UNSORTED LIST
        //==========================================================================//

        /**
         * Input:  3 -> 2 -> 3 -> 1 -> 2 -> null
         * Output: 3 -> 2 -> 1 -> null
         * * Time Complexity:  O(N)
         * Space Complexity: O(N) -> To store unique elements in HashSet
         */
        public Node removeDuplicatesUnsorted(Node head) {
            HashSet<Integer> set = new HashSet<>();
            Node current = head, prev = null;

            while (current != null) {
                if (set.contains(current.data)) {
                    prev.next = current.next;
                } else {
                    set.add(current.data);
                    prev = current;
                }
                current = current.next;
            }
            return head;
        }

        //==========================================================================//
        // ADD 1 TO THE LIST
        //==========================================================================//

        /**
         * Input:  1 -> 9 -> 9 -> 9 -> null (represents 1999)
         * Output: 2 -> 0 -> 0 -> 0 -> null (represents 2000)
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        public static Node addOne(Node head) {
            head = reverseStatic(head);

            int sum = 0;
            int carry = 1;
            Node current = head, prev = null;

            while (current != null) {
                sum = current.data + carry;
                carry = sum >= 10 ? 1 : 0;

                current.data = sum % 10;
                prev = current;
                current = current.next;
            }

            if (carry != 0) {
                Node newnode = new Node(carry);
                prev.next = newnode;
            }
            return reverseStatic(head);
        }

        private static Node reverseStatic(Node node) {
            Node current = node, prev = null, next = null;
            while (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            return prev;
        }

        //==========================================================================//
        // ADD TWO LISTS (REVERSED REPRESENTATION)
        //==========================================================================//

        /**
         * Input:  First: 5 -> 3 -> 3 -> null (335 reversed), Second: 5 -> 4 -> null (45 reversed)
         * Output: 0 -> 8 -> 3 -> null (380 reversed)
         * * Time Complexity:  O(max(N, M))
         * Space Complexity: O(max(N, M)) -> Space for resultant list
         */
        static Node addTwoListsReversedInput(Node first, Node second) {
            Node current = new Node(0);
            Node temp = current;
            int sum = 0, carry = 0;

            while (first != null || second != null || carry == 1) {
                sum = 0;
                if (first != null) {
                    sum += first.data;
                    first = first.next;
                }
                if (second != null) {
                    sum += second.data;
                    second = second.next;
                }
                sum += carry;
                carry = sum / 10;
                Node newnode = new Node(sum % 10);

                temp.next = newnode;
                temp = temp.next;
            }
            return current.next;
        }

        //==========================================================================//
        // ADD TWO LISTS (STANDARD REPRESENTATION)
        //==========================================================================//

        /**
         * Input:  First: 3 -> 3 -> 5 -> null (335), Second: 4 -> 5 -> null (45)
         * Output: 3 -> 8 -> 0 -> null (380)
         * * Time Complexity:  O(N + M)
         * Space Complexity: O(max(N, M)) -> Space for output linked list
         */
        static Node addTwoListsStandardInput(Node first, Node second) {
            first = reverseStatic(first);
            second = reverseStatic(second);

            Node temp = null;
            int sum = 0, carry = 0;

            while (first != null || second != null || carry == 1) {
                sum = 0;
                if (first != null) {
                    sum += first.data;
                    first = first.next;
                }
                if (second != null) {
                    sum += second.data;
                    second = second.next;
                }

                sum += carry;
                carry = sum / 10;
                Node newnode = new Node(sum % 10);

                newnode.next = temp;
                temp = newnode;
            }
            return temp;
        }

        //==========================================================================//
        // FIND INTERSECTION OF TWO SORTED LISTS (HASHSET APPROACH)
        //==========================================================================//

        /**
         * Input:  List1: 2 -> 3 -> 4 -> 5 -> 6 -> null, List2: 3 -> 5 -> 7 -> 8 -> 9 -> null
         * Output: 3 -> 5 -> null
         * * Time Complexity:  O(N + M)
         * Space Complexity: O(N) -> Extra memory used by HashSet
         */
        public static Node findIntersectionSet(Node head1, Node head2) {
            Node n1 = head1, n2 = head2;
            HashSet<Integer> set = new HashSet<>();

            Node newlist = new Node(0);
            Node current = newlist;

            while (n1 != null) {
                set.add(n1.data);
                n1 = n1.next;
            }

            while (n2 != null) {
                if (set.contains(n2.data)) {
                    Node newnode = new Node(n2.data);
                    current.next = newnode;
                    current = current.next;
                    set.remove(n2.data);
                }
                n2 = n2.next;
            }
            return newlist.next;
        }

        //==========================================================================//
        // FIND INTERSECTION OF TWO SORTED LISTS (TWO-POINTER APPROACH)
        //==========================================================================//

        /**
         * Input:  List1: 2 -> 3 -> 4 -> 5 -> 6 -> null, List2: 3 -> 5 -> 7 -> 8 -> 9 -> null
         * Output: 3 -> 5 -> null
         * * Time Complexity:  O(N + M)
         * Space Complexity: O(1) -> Optimal constant auxiliary space
         */
        public static Node findIntersectionTwoPointer(Node head1, Node head2) {
            Node newNode = new Node(-1);
            Node temp = newNode;
            Node p1 = head1, p2 = head2;

            while (p1 != null && p2 != null) {
                if (p1.data == p2.data) {
                    newNode.next = new Node(p1.data);
                    newNode = newNode.next;
                    p2 = p2.next;
                    p1 = p1.next;
                } else if (p1.data > p2.data) {
                    p2 = p2.next;
                } else {
                    p1 = p1.next;
                }
            }
            return temp.next;
        }

        //==========================================================================//
        // FIND INTERSECTION POINT OF TWO LISTS (DIFFERENCE OF SIZE METHOD)
        //==========================================================================//

        /**
         * Input:  List1: 3 -> 6 -> 9 -> [15 -> 30 -> null], List2: 10 -> [15 -> 30 -> null]
         * Output: 15 (Node reference value match)
         * * Time Complexity:  O(N + M)
         * Space Complexity: O(1)
         */
        int intersectPoint(Node head1, Node head2) {
            int size1 = getSize(head1);
            int size2 = getSize(head2);

            Node big = size1 > size2 ? head1 : head2;
            Node small = size1 > size2 ? head2 : head1;

            int diff = Math.abs(size1 - size2);

            for (int i = 0; i < diff; i++) {
                big = big.next;
            }

            while (big != small) {
                big = big.next;
                small = small.next;
            }

            return small != null ? small.data : -1;
        }

        public static int getSize(Node node) {
            int size = 0;
            while (node != null) {
                node = node.next;
                size++;
            }
            return size;
        }

        //==========================================================================//
        // FIND INTERSECTION POINT OF TWO LISTS (POINTER-SWAP APPROACH)
        //==========================================================================//

        /**
         * Input:  List1: 3 -> 6 -> 9 -> [15 -> 30 -> null], List2: 10 -> [15 -> 30 -> null]
         * Output: Node reference at 15
         * * Time Complexity:  O(N + M)
         * Space Complexity: O(1)
         */
        public Node getIntersectionNodeOptimal(Node node1, Node node2) {
            if (node1 == null || node2 == null) return null;
            Node a = node1;
            Node b = node2;

            while (a != b) {
                a = (a == null) ? node2 : a.next;
                b = (b == null) ? node1 : b.next;
            }
            return a;
        }

        //==========================================================================//
        // MERGE SORT ON LINKED LIST
        //==========================================================================//

        /**
         * Input:  4 -> 2 -> 1 -> 3 -> null
         * Output: 1 -> 2 -> 3 -> 4 -> null
         * * Time Complexity:  O(N log N)
         * Space Complexity: O(log N) -> Call stack frames
         */
        static Node mergeSort(Node head) {
            if (head == null || head.next == null) {
                return head;
            }

            Node left = getMiddle(head);
            Node right = left.next;
            left.next = null;
            left = head;

            left = mergeSort(left);
            right = mergeSort(right);

            return merge(left, right);
        }

        static Node merge(Node l, Node r) {
            Node merged = new Node(-1);
            Node temp = merged;

            while (l != null && r != null) {
                if (l.data <= r.data) {
                    temp.next = l;
                    l = l.next;
                } else {
                    temp.next = r;
                    r = r.next;
                }
                temp = temp.next;
            }

            if (l != null) temp.next = l;
            if (r != null) temp.next = r;

            return merged.next;
        }

        static Node getMiddle(Node head) {
            Node slow = head, fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        //==========================================================================//
        // MERGE SORT WITH RECURSIVE MERGING
        //==========================================================================//

        /**
         * Input:  9 -> 5 -> 7 -> 1 -> null
         * Output: 1 -> 5 -> 7 -> 9 -> null
         * * Time Complexity:  O(N log N)
         * Space Complexity: O(log N) + Recursive merge trace depth stack frame bounds
         */
        static Node mergeSortRecursive(Node head) {
            if (head == null || head.next == null) return head;

            Node left = getMiddle(head);
            Node right = left.next;
            left.next = null;
            left = head;

            left = mergeSortRecursive(left);
            right = mergeSortRecursive(right);

            return mergeRecursive(left, right);
        }

        static Node mergeRecursive(Node l, Node r) {
            if (l == null) return r;
            if (r == null) return l;

            Node sorted = null;
            if (l.data <= r.data) {
                sorted = l;
                sorted.next = mergeRecursive(l.next, r);
            } else {
                sorted = r;
                sorted.next = mergeRecursive(l, r.next);
            }
            return sorted;
        }

        //==========================================================================//
        // FIND MIDDLE ELEMENT OF LINKED LIST (TWO-PASS COUNT METHOD)
        //==========================================================================//

        /**
         * Input:  1 -> 2 -> 3 -> 4 -> 5 -> null
         * Output: 3 -> 4 -> 5 -> null
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        public ListNode middleNodeTwoPass(ListNode head) {
            int size = 0;
            ListNode current = head;

            while (current != null) {
                current = current.next;
                size++;
            }
            size = size / 2;

            while (size != 0) {
                head = head.next;
                size--;
            }
            return head;
        }

        //==========================================================================//
        // FIND MIDDLE ELEMENT OF LINKED LIST (TORTOISE AND HARE MECHANISM)
        //==========================================================================//

        /**
         * Input:  1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
         * Output: 4 -> 5 -> 6 -> null
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        public ListNode middleNodeOptimal(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;

            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

        //==========================================================================//
        // CHECK IF LINKED LIST IS CIRCULAR
        //==========================================================================//

        /**
         * Input:  1 -> 2 -> 3 -> (points back to 1)
         * Output: true
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        boolean isCircular(Node head) {
            if (head == null) return true;
            Node current = head.next;

            while (current != null && current != head) {
                current = current.next;
            }

            return (current == head);
        }

        //==========================================================================//
        // SPLIT A CIRCULAR LINKED LIST INTO TWO HALVES
        //==========================================================================//

        /**
         * Input:  1 -> 2 -> 3 -> 4 -> (loops back to 1)
         * Output: Pair containing List1: 1 -> 2 -> (loops to 1), List2: 3 -> 4 -> (loops to 3)
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        public Pair<Node, Node> splitList(Node head) {
            if (head == null) {
                return new Pair<>(null, null);
            }
            Node fast = head;
            Node slow = head;

            while (fast.next != head && fast.next.next != head) {
                fast = fast.next.next;
                slow = slow.next;
            }

            if (fast.next.next == head) {
                fast = fast.next;
            }

            Node head1 = head;
            Node head2 = slow.next;

            slow.next = head1;
            fast.next = head2;

            return new Pair<>(head1, head2);
        }

        //==========================================================================//
        // CHECK IF LINKED LIST IS A PALINDROME
        //==========================================================================//

        /**
         * Input:  1 -> 2 -> 2 -> 1 -> null
         * Output: true
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        boolean isPalindrome(Node head) {
            Node fast = head, slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            Node prev = null;
            Node current = slow;
            Node next = null;

            while (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }

            Node reverseHead = prev;
            current = head;

            while (current != null && reverseHead != null) {
                if (current.data != reverseHead.data) {
                    return false;
                }
                current = current.next;
                reverseHead = reverseHead.next;
            }
            return true;
        }

        //==========================================================================//
        // DELETE KEY IN CIRCULAR LINKED LIST
        //==========================================================================//

        /**
         * Input:  1 -> 2 -> 3 -> 4 -> (loops to 1), Key = 3
         * Output: 1 -> 2 -> 4 -> (loops to 1)
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        Node deleteNodeCircular(Node head, int key) {
            if (head == null) return null;

            if (head.data == key && head.next == head) {
                return null;
            }

            Node current = head;
            Node prev = null;

            if (head.data == key) {
                while (current.next != head) {
                    current = current.next;
                }
                current.next = head.next;
                head = head.next;
                return head;
            }

            current = head.next;
            prev = head;
            while (current != head) {
                if (current.data == key) {
                    prev.next = current.next;
                    return head;
                }
                prev = current;
                current = current.next;
            }
            return head;
        }

        //==========================================================================//
        // REVERSE DOUBLY LINKED LIST (THREE-POINTER STRUCTURAL ASSIGNMENT)
        //==========================================================================//

        /**
         * Input:  null <- 1 <=> 2 <=> 3 -> null
         * Output: null <- 3 <=> 2 <=> 1 -> null
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        public static Node reverseDLL(Node head) {
            Node current = head;
            Node prev1 = null, next1 = null;

            while (current != null) {
                next1 = current.next;
                current.next = prev1;
                current.prev = next1;
                prev1 = current;
                current = next1;
            }

            head = prev1;
            return head;
        }

        //==========================================================================//
        // REVERSE DOUBLY LINKED LIST (TWO-POINTER SWAP METHOD)
        //==========================================================================//

        /**
         * Input:  null <- 1 <=> 2 <=> 3 -> null
         * Output: null <- 3 <=> 2 <=> 1 -> null
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        public static Node reverseDLLSwapApproach(Node head) {
            Node p = head;
            Node current = head;
            while (current != null) {
                p = current;
                Node temp = current.prev;
                current.prev = current.next;
                current.next = temp;
                current = current.prev; // Moves down using mutated link
            }
            return p;
        }

        //==========================================================================//
        // FIND PAIRS WITH A GIVEN SUM IN A DOUBLY LINKED LIST
        //==========================================================================//

        /**
         * Input:  1 <=> 2 <=> 3 <=> 4 <=> 5 <=> 6 <=> 7, Target = 7
         * Output: Prints (1, 6), (2, 5), (3, 4)
         * * Time Complexity:  O(N) -> If list is sorted
         * Space Complexity: O(1)
         */
        static void pairSum(Node head, int x) {
            Node first = head;
            Node second = head;
            while (second.next != null) {
                second = second.next;
            }

            boolean found = false;

            while (first != null && second != null && first != second && first.prev != second) {
                if ((first.data + second.data) == x) {
                    found = true;
                    System.out.println("(" + first.data + ", " + second.data + ")");
                    first = first.next;
                    second = second.prev;
                } else if ((first.data + second.data) < x) {
                    first = first.next;
                } else {
                    second = second.prev;
                }
            }

            if (!found) {
                System.out.println("No pair found");
            }
        }

        //==========================================================================//
        // COUNT TRIPLETS IN A SORTED DOUBLY LINKED LIST EQUAL TO X
        //==========================================================================//

        /**
         * Input:  1 <=> 2 <=> 4 <=> 5 <=> 6 <=> 8 <=> 9, Target = 15
         * Output: 5 -> Triplets: (1,6,8), (2,4,9), (2,5,8), (4,5,6)
         * * Time Complexity:  O(N^2)
         * Space Complexity: O(1)
         */
        static int countPairs(Node first, Node second, int value) {
            int count = 0;
            while (first != null && second != null && first != second && second.next != first) {
                if ((first.data + second.data) == value) {
                    count++;
                    first = first.next;
                    second = second.prev;
                } else if ((first.data + second.data) < value) {
                    first = first.next;
                } else {
                    second = second.prev;
                }
            }
            return count;
        }

        static int countTriplets(Node head, int x) {
            if (head == null) return 0;

            Node current, first, last;
            int count = 0;

            last = head;
            while (last.next != null) {
                last = last.next;
            }

            for (current = head; current != null; current = current.next) {
                first = current.next;
                count += countPairs(first, last, x - current.data);
            }
            return count;
        }

        //==========================================================================//
        // ROTATE DOUBLY LINKED LIST BY N NODES
        //==========================================================================//

        /**
         * Input:  1 <=> 2 <=> 3 <=> 4 <=> 5 <=> 6, N = 2
         * Output: 3 <=> 4 <=> 5 <=> 6 <=> 1 <=> 2
         * * Time Complexity:  O(N) -> Where N is length of total collection
         * Space Complexity: O(1)
         */
        static Node rotate(Node headRef, int N) {
            if (N == 0 || headRef == null) return headRef;

            Node current = headRef;

            int count = 1;
            while (count < N && current != null) {
                current = current.next;
                count++;
            }

            if (current == null) return headRef;

            Node nthNode = current;

            while (current.next != null) {
                current = current.next;
            }

            current.next = headRef;
            headRef.prev = current;

            headRef = nthNode.next;
            headRef.prev = null;

            nthNode.next = null;
            return headRef;
        }

        //==========================================================================//
        // FLATTENING OF MULTI-LEVEL LINKED LIST
        //==========================================================================//

        /**
         * Input:  5 -> 10 -> 19
         * |    |     |
         * 7    20    22
         * Output: 5 -> 7 -> 10 -> 19 -> 20 -> 22 -> null
         * * Time Complexity:  O(Total Nodes)
         * Space Complexity: O(Number of vertical columns) -> Stack trace depth
         */
        Node flatten(Node root) {
            if (root == null || root.next == null) {
                return root;
            }

            root.next = flatten(root.next);
            return mergeFlatten(root, root.next);
        }

        public static Node mergeFlatten(Node a, Node b) {
            if (a == null) return b;
            if (b == null) return a;

            Node result;
            if (a.data < b.data) {
                result = a;
                result.bottom = mergeFlatten(a.bottom, b);
            } else {
                result = b;
                result.bottom = mergeFlatten(a, b.bottom);
            }

            result.next = null;
            return result;
        }

        //==========================================================================//
        // SEGREGATE 0, 1, AND 2 IN A LINKED LIST (DATA MODIFICATION METHOD)
        //==========================================================================//

        /**
         * Input:  1 -> 2 -> 0 -> 1 -> 2 -> 0 -> null
         * Output: 0 -> 0 -> 1 -> 1 -> 2 -> 2 -> null
         * * Time Complexity:  O(N)
         * Space Complexity: O(1) -> Storage array allocation tracking map is absolute constant size 3
         */
        static Node segregateByData(Node head) {
            Node current = head;
            int[] arr = {0, 0, 0};
            int i = 0;
            while (current != null) {
                arr[current.data]++;
                current = current.next;
            }
            current = head;
            while (current != null) {
                if (arr[i] == 0) {
                    i++;
                } else {
                    current.data = i;
                    --arr[i];
                    current = current.next;
                }
            }
            return head;
        }

        //==========================================================================//
        // SEGREGATE 0, 1, AND 2 IN A LINKED LIST (POINTER MANIPULATION METHOD)
        //==========================================================================//

        /**
         * Input:  2 -> 1 -> 0 -> 2 -> 1 -> null
         * Output: 0 -> 1 -> 1 -> 2 -> 2 -> null
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        static Node segregateByPointer(Node head) {
            Node zeroD = new Node(0), oneD = new Node(0), twoD = new Node(0);
            Node zero = zeroD, one = oneD, two = twoD;

            Node curr = head;
            while (curr != null) {
                if (curr.data == 0) {
                    zero.next = curr;
                    zero = zero.next;
                } else if (curr.data == 1) {
                    one.next = curr;
                    one = one.next;
                } else {
                    two.next = curr;
                    two = two.next;
                }
                curr = curr.next;
            }

            zero.next = (oneD.next != null) ? oneD.next : twoD.next;
            one.next = twoD.next;
            two.next = null;

            return zeroD.next;
        }

        //==========================================================================//
        // CLONE A LINKED LIST WITH NEXT AND RANDOM POINTER (HASHMAP APPROACH)
        //==========================================================================//

        /**
         * Input:  Node(1) -> Node(2) with alternate cyclic cross definitions via arbitrary links
         * Output: Explicit deep clone duplicate collection representation
         * * Time Complexity:  O(N)
         * Space Complexity: O(N) -> To store copy reference mapping elements
         */
        public Node copyRandomListMap(Node head) {
            if (head == null) return null;

            Map<Node, Node> map = new HashMap<>();
            Node curr = head;

            while (curr != null) {
                map.put(curr, new Node(curr.val));
                curr = curr.next;
            }

            curr = head;
            while (curr != null) {
                Node clone = map.get(curr);
                clone.next = map.get(curr.next);
                clone.random = map.get(curr.random);
                curr = curr.next;
            }

            return map.get(head);
        }

        //==========================================================================//
        // CLONE A LINKED LIST WITH NEXT AND RANDOM POINTER (IN-PLACE OPTIMAL METHOD)
        //==========================================================================//

        /**
         * Input:  Node(1) -> Node(2) with dynamic cross references via arbitrary links
         * Output: Deep cloned identical copy allocation
         * * Time Complexity:  O(N)
         * Space Complexity: O(1) -> Mutates intermediate links explicitly without a HashMap
         */
        Node copyListInPlace(Node head) {
            if (head == null) return null;

            Node original = head;
            Node temp = null;

            while (original != null) {
                temp = original.next;
                original.next = new Node(original.data);
                original.next.next = temp;
                original = temp;
            }

            original = head;
            while (original != null) {
                if (original.next != null) {
                    original.next.arb = original.arb != null ? original.arb.next : original.arb;
                }
                original = original.next != null ? original.next.next : null;
            }

            temp = head.next;
            Node copy = temp;
            original = head;

            while (original != null && temp != null) {
                original.next = original.next.next;
                temp.next = temp.next != null ? temp.next.next : null;

                original = original.next;
                temp = temp.next;
            }
            return copy;
        }

        //==========================================================================//
        // MERGE K SORTED LINKED LISTS
        //==========================================================================//

        /**
         * Input:  arr = [[1->2->3], [4->5], [5->6], [7->8]], K = 4
         * Output: 1 -> 2 -> 3 -> 4 -> 5 -> 5 -> 6 -> 7 -> 8 -> null
         * * Time Complexity:  O(N * K) -> Where N is total nodes aggregated across sets
         * Space Complexity: O(N) -> Recursive trace stack space logic for structural sorting calls
         */
        Node mergeKList(Node[] arr, int K) {
            if (arr == null || K == 0) return null;
            for (int i = 1; i < K; i++) {
                arr[0] = mergeK(arr[0], arr[i]);
            }
            return arr[0];
        }

        Node mergeK(Node a, Node b) {
            if (a == null) return b;
            if (b == null) return a;

            Node result = null;
            if (a.data <= b.data) {
                result = a;
                result.next = mergeK(a.next, b);
            } else {
                result = b;
                result.next = mergeK(a, b.next);
            }
            return result;
        }

        //==========================================================================//
        // MULTIPLY TWO LINKED LISTS
        //==========================================================================//

        /**
         * Input:  L1: 3 -> 2 -> 1 -> null (321), L2: 1 -> 2 -> null (12)
         * Output: 3852 (Calculated via Modulo values arithmetic)
         * * Time Complexity:  O(N + M)
         * Space Complexity: O(1)
         */
        static long multiplyTwoLists(Node l1, Node l2) {
            long num1 = 0, num2 = 0;
            long MOD = 1000000007;

            while (l1 != null) {
                num1 = (num1 * 10 + l1.data) % MOD;
                l1 = l1.next;
            }

            while (l2 != null) {
                num2 = (num2 * 10 + l2.data) % MOD;
                l2 = l2.next;
            }

            return (num1 * num2) % MOD;
        }

        //==========================================================================//
        // DELETE NODES WHICH HAVE A GREATER VALUE ON RIGHT SIDE (RECURSIVE)
        //==========================================================================//

        /**
         * Input:  12 -> 15 -> 10 -> 11 -> 5 -> 6 -> 2 -> 3 -> null
         * Output: 15 -> 11 -> 6 -> 3 -> null
         * * Time Complexity:  O(N)
         * Space Complexity: O(N) -> Implicit execution stack frame tracking parameters
         */
        public static Node deleteNodesOnRightSideRecursive(Node head) {
            if (head == null || head.next == null) {
                return head;
            }

            Node nextNode = deleteNodesOnRightSideRecursive(head.next);

            if (nextNode.data > head.data) {
                return nextNode;
            }

            head.next = nextNode;
            return head;
        }

        //==========================================================================//
        // DELETE NODES WHICH HAVE A GREATER VALUE ON RIGHT SIDE (REVERSE LOOP METHOD)
        //==========================================================================//

        /**
         * Input:  12 -> 15 -> 10 -> 11 -> 5 -> 6 -> 2 -> 3 -> null
         * Output: 15 -> 11 -> 6 -> 3 -> null
         * * Time Complexity:  O(N)
         * Space Complexity: O(1) -> Optimal memory footprints
         */
        static Node deleteNodesOnRightSideOptimal(Node head) {
            if (head == null || head.next == null) return head;
            head = reverseStatic(head);

            Node curr = head;
            int maxSoFar = curr.data;

            while (curr != null && curr.next != null) {
                if (curr.next.data < maxSoFar) {
                    curr.next = curr.next.next;
                } else {
                    curr = curr.next;
                    maxSoFar = curr.data;
                }
            }
            return reverseStatic(head);
        }

        //==========================================================================//
        // SEGREGATE EVEN AND ODD NODES IN A LINKED LIST
        //==========================================================================//

        /**
         * Input:  17 -> 15 -> 8 -> 12 -> 10 -> 5 -> 4 -> null
         * Output: 8 -> 12 -> 10 -> 4 -> 17 -> 15 -> 5 -> null
         * * Time Complexity:  O(N)
         * Space Complexity: O(1) -> Uses existing nodes inside placeholder dummy shells
         */
        public void segregateEvenOdd() {
            Node evenStart = new Node(-1);
            Node evenEnd = evenStart;
            Node oddStart = new Node(-1);
            Node oddEnd = oddStart;

            Node currentNode = head;

            while (currentNode != null) {
                int element = currentNode.data;
                if (element % 2 == 0) {
                    evenEnd.next = currentNode;
                    evenEnd = evenEnd.next;
                } else {
                    oddEnd.next = currentNode;
                    oddEnd = oddEnd.next;
                }
                currentNode = currentNode.next;
            }

            evenEnd.next = oddStart.next;
            oddEnd.next = null;
            head = evenStart.next;
        }

        //==========================================================================//
        // N-TH NODE FROM THE END OF LINKED LIST
        //==========================================================================//

        /**
         * Input:  1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null, n = 3
         * Output: 4
         * * Time Complexity:  O(N)
         * Space Complexity: O(1)
         */
        public int getNthFromLast(Node head, int n) {
            Node p = head, f = head;
            for (int i = 1; i <= n - 1; i++) {
                if (f == null) return -1;
                f = f.next;
            }
            if (f == null) return -1;

            while (f.next != null) {
                p = p.next;
                f = f.next;
            }
            return p.data;
        }

        //==========================================================================//
        // THEORETICAL ANALYSIS & INQUIRIES
        //==========================================================================//
        /*
         * Q1: Can we reverse a linked list in less than O(n)?
         * A1: No. To change references, every node must be visited at least once.
         * XOR linked lists let you traverse both directions with 1 pointer variable per node,
         * but Java doesn't support direct RAM address reference arithmetic anyway.
         * * Q2: Why QuickSort is preferred for Arrays and Merge Sort for LinkedLists?
         * A2: 1. Arrays provide O(1) random index access. QuickSort uses this for picking random pivots.
         * 2. Merge sort requires O(N) auxiliary space for arrays, but for LinkedLists,
         * nodes can be merged in place by modifying pointers—bringing extra space down to O(1) (ignoring call stack).
         * 3. Merge sort accesses elements sequentially, matching the O(N) access pattern of Linked Lists.
         */
    }

