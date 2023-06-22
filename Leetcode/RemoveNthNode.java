// https://leetcode.com/problems/remove-nth-node-from-end-of-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode node = head;
        ListNode actual = head;
        ListNode start = head;
        int totalNodes = 0;
        
        while(node.next != null) 
        {
            totalNodes ++;
            node = node.next;
        }
        

        if (totalNodes == 0) return null;

         
        if(totalNodes < n)
        {
            start = head.next;
        }

        while(totalNodes > n)
        {
            totalNodes --;
            actual = actual.next;
        }


        if (actual.next != null)
            actual.next = actual.next.next;


        return start;
    }
}