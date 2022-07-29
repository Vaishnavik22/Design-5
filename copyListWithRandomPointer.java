/*
Problem: https://leetcode.com/problems/copy-list-with-random-pointer/
*/
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

// TC: O(n)
// SC: O(n)
class Solution {
    HashMap<Node, Node> oldToNewMap = null;
    Node dummy;
    public Node copyRandomList(Node head) {
        oldToNewMap = new HashMap<>();
        dummy = new Node(-1);
        
        constructNewList(head, dummy);
        setRandomPointers(head, dummy.next);
        
        return dummy.next;
    }
    
    private void constructNewList(Node oldList, Node newList) {
        Node ptrOld = oldList;
        Node ptrNew = newList;
        
        while (ptrOld != null) {
            ptrNew.next = new Node(ptrOld.val);
            ptrNew = ptrNew.next;
            oldToNewMap.put(ptrOld, ptrNew);
            ptrOld = ptrOld.next;
        }
    }
    
    private void setRandomPointers(Node oldList, Node newList) {
        Node ptrOld = oldList;
        Node ptrNew = newList;
        
        while (ptrOld != null) {
            if (ptrOld.random == null) {
                ptrNew.random = null;
            } else {
                Node newRandom = oldToNewMap.get(ptrOld.random);
                ptrNew.random = newRandom;
            }
            ptrOld = ptrOld.next;
            ptrNew = ptrNew.next;
        }
    }
}

// TC: O(n)
// SC: O(1)
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        // Step 1: create a deep copy
        Node cur = head;
        while (cur != null) {
            Node curCopy = new Node(cur.val);
            curCopy.next = cur.next;
            cur.next = curCopy;
            cur = cur.next.next;
        }
        
        // Step 2 : Set random pointers
        cur = head;
        while (cur != null) {
            cur.next.random = (cur.random == null ? null : cur.random.next);
            cur = cur.next.next;
        }
        
        // Step 3 : split the two lists
        cur = head;
        Node curCopy = cur.next;
        Node copyHead = curCopy;
        while (cur != null) {
            cur.next = curCopy.next;
            // When we reach the end of the list, curCopy.next is null.
            // We need to account for that here
            if (curCopy.next == null)
                break;
            curCopy.next = curCopy.next.next;
            cur = cur.next;
            curCopy = curCopy.next;
        }
        return copyHead;
    }
}