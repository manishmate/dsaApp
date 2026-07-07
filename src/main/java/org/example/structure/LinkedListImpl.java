package org.example.structure;

class LinkedListClass {
    Node head;
    static class Node {
        int data;
        Node next;
        Node(int d) {
            data = d;
            next = null;
        }
    }

    // **************INSERTION**************
    public static LinkedListClass insert(LinkedListClass list,
                                    int data) {
        Node new_node = new Node(data);
        new_node.next = null;

        if (list.head == null) {
            list.head = new_node;
        } else {
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new_node;
        }
        return list;
    }

    // **************TRAVERSAL**************
    public static void printList(LinkedListClass list) {
        Node currNode = list.head;
        while (currNode != null) {
            System.out.print(currNode.data + " "); //print
            currNode = currNode.next;
        }
        System.out.println("\n");
    }

    // **************DELETION BY KEY**************
    public static LinkedListClass deleteByKey(LinkedListClass list,
                                         int key) {
        Node currNode = list.head, prev = null;
        // CASE 1:
        // If head node itself holds the key to be deleted
        if (currNode != null && currNode.data == key) {
            list.head = currNode.next;
            System.out.println(key + " found and deleted");
            return list;
        }
        // CASE 2:
        // If the key is somewhere other than at head
        while (currNode != null && currNode.data != key) {
            prev = currNode;
            currNode = currNode.next;
        }
        if (currNode != null) {
            prev.next = currNode.next;
            System.out.println(key + " found and deleted");
        }

        // CASE 3: The key is not present
        if (currNode == null) {
            System.out.println(key + " not found");
        }
        return list;
    }

    // **************DELETION AT A POSITION**************

    public static LinkedListClass
    deleteAtPosition(LinkedListClass list, int index) {
        Node currNode = list.head, prev = null;
        // CASE 1:
        // If index is 0, then head node itself is to be
        if (index == 0 && currNode != null) {
            list.head = currNode.next;
            System.out.println(
                    index + " position element deleted");
            return list;
        }

        //
        // CASE 2:
        // If the index is greater than 0 but less than the
        // size of LinkedListClass
        int counter = 0;
        while (currNode != null) {
            if (counter == index) {
                prev.next = currNode.next;
                System.out.println(
                        index + " position element deleted");
                break;
            } else {
                prev = currNode;
                currNode = currNode.next;
                counter++;
            }
        }


        // CASE 3: index not found
        if (currNode == null) {
            System.out.println(
                    index + " position element not found");
        }
        return list;
    }
}
