package Blind75.LinkedList;

import DataStructure.ListNode;

/**
 * 25. Reverse Nodes in k-Group: Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a
 * multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * Follow up:
 * Could you solve the problem in O(1) extra memory space?
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 * Input: head = [1,2,3,4,5], k = 1
 * Output: [1,2,3,4,5]
 *
 * Solution: logic question.
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) return head;

        // Find how many reverse needs
        int reverseNum = findReverseNumber(head, k);

        ListNode prev = head;
        ListNode current = head.next;
        ListNode prevEnd = null; // for previous reverse part
        ListNode ans = null; // setup answer after first reverse
        while (reverseNum > 0) {
            int temp = k;
            ListNode start = prev;

            // Reverse
            while (temp > 1) {
                ListNode nextPrev = current;
                current = current.next;
                nextPrev.next = prev;
                prev = nextPrev;

                temp--;
            }

            // Handle previous reversed component
            if (prevEnd != null) {
                prevEnd.next = prev;
            } else {
                ans = prev;
            }
            prevEnd = start;

            // Move to next reverse part
            start.next = current;
            prev = current;
            if (current != null) current = current.next;

            reverseNum--;
        }
        // System.out.println(reverseNum);

        return ans;
    }

    public int findReverseNumber(ListNode head, int k) {
        int count = 0;

        while (head != null) {
            int temp = k;

            while (temp > 0 && head != null) {
                head = head.next;
                temp--;
            }

            if (temp == 0) count++;
        }

        return count;

    }
}
