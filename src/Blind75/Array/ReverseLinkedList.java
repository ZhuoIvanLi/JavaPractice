package Blind75.Array;

import DataStructure.ListNode;

/**
 * Reverse Linked List: Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 *
 * Input: head = [1,2]
 * Output: [2,1]
 *
 * Solution: use multiple pointers.
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = head;
        ListNode current = head.next;

        if (head.next.next == null) {
            ListNode ans = head.next;
            ans.next = head;
            head.next = null;
            return ans;
        }

        ListNode next = head.next.next;
        head.next = null;

        while (true) {
            current.next = prev;
            prev = current;
            current = next;
            if (current == null) break;
            next = next.next;
        }

        return prev;
    }
}
