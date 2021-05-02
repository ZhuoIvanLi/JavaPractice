import DataStructure.ListNode;

import java.util.HashSet;

public class LinkedList {
    /**
     * Definition for singly-linked list.
     * public class DataStructure.ListNode {
     *     int val;
     *     DataStructure.ListNode next;
     *     DataStructure.ListNode(int x) { val = x; }
     * }
     */

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static void prettyPrintLinkedList(ListNode node) {
        while (node != null && node.next != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }

        if (node != null) {
            System.out.println(node.val);
        } else {
            System.out.println("Empty LinkedList");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode node = Wrapper.stringToListNode(line);

            //node=removeDups(node);
            //kthToLast(node, 4);
            //node=deleteMiddleNode(node);
            //node=partition(node, 8);
            //node=partition2(node, 8);
            //node = sumLists(node,node);

            //node=reverseList(node);
            //System.out.println(isPalindrome(node));

            ListNode a=new ListNode(0);
            ListNode b = new ListNode(0);

            for(int i=1; i<5; i++){
                ListNode tempA = new ListNode(i);
                tempA.next=a;
                a=tempA;

                ListNode tempB = new ListNode(i);
                tempB.next=b;
                b=tempB;
            }

            intersection(a, b);


            //Wrapper.prettyPrintLinkedList(node);
        }
    }

    // 2.1 Remove Dup: remove duplicates from unsorted Linked List
    // Hint: use hash table, or iterate and compare one by one O(n squared)
    public static ListNode removeDups(ListNode head){
        HashSet<Integer> hs = new HashSet<Integer>();
        ListNode temp = head;

        hs.add(temp.val);

        while(temp.next!=null){
            if(hs.contains(temp.next.val)){
                temp.next=temp.next.next;
            }else{
                hs.add(temp.next.val);
                temp=temp.next;
            }
        }

        return head;
    }

    // 2.2 Return Kth to last: find the Kth to last element of a singly linked list.
    // Ex: 2->5->39->32->10->9 and k=3, so kth element is 32.
    // Answer: use recursion to return value from end to start
    // or use iterative to add one more pointer p2. move p2 to kth position, then move both p1 and p2 until p2 hit the end.
    // p1 will be in length - kth position.
    public static int kthToLast(ListNode head, int k){
        if(head==null){
            return 0;
        }

        int index=kthToLast(head.next, k) +1;
        if(index==k){
            System.out.println(k+"th to last node is " + head.val);
        }

        return index;
    }


    // 2.3 Delete Middle Node: delete a given node in a middle of a linked list(not first or last node)
    public static ListNode deleteMiddleNode(ListNode head){
        ListNode n=head.next.next.next.next;

        if(n==null || n.next==null){
            return head;
        }

        n.val=n.next.val;
        n.next=n.next.next;

        return head;
    }

    // 2.4 Partition: sort the linked list to make it "left partition" less than the pivot
    // create two partition nodes, one has the less number and one has greater number
    public static ListNode partition(ListNode head, int pivot){
        ListNode p1=head;

        while(p1.val<pivot && p1!=null){
            p1=p1.next;
        }

        ListNode p2=p1.next;

        while(p1!=null && p2!=null){
            if(p1.val<pivot){
                p1=p1.next;
                p2=p2.next;
            }else{
                if(p2.val<pivot){
                    int temp=p2.val;
                    p2.val=p1.val;
                    p1.val=temp;
                }else{
                    p2=p2.next;
                }
            }
        }

        return head;
    }

    // Another solution for 2.4
    public static ListNode partition2(ListNode node, int pivot){
        ListNode head = node;
        ListNode end = node;

        while(node!=null){
            ListNode next = node.next;

            // move to head
            if(node.val < pivot){
                node.next=head;
                head=node;
            }
            // move to end
            else{
                end.next=node;
                end=node;
            }

            node=next;
        }

        end.next=null;

        return head;
    }

    // 2.5 Sum Lists: two number represented by a linked list, add them and return sum number in linked list. reverse order and forward order. forward order is more complex and solution is in pg 214
    public static ListNode sumLists(ListNode n1, ListNode n2){
        int carry=0;

        ListNode res = addNumber(n1, n2, carry);

        return res;
    }

    public static ListNode addNumber(ListNode n1, ListNode n2, int carry){
        if(n1==null && n2==null && carry==0){
            return null;
        }

        ListNode newNode = new ListNode();
        int value=carry;
        if(n1!=null){
            value+=n1.val;
        }
        if(n2!=null){
            value+=n2.val;
        }

        newNode.val=value%10;

        if(n1!=null || n2!=null){
            ListNode more = addNumber(n1==null?null:n1.next, n2==null?null:n2.next, value>=10?1:0);

            newNode.next=more;
        }

        return newNode;
    }

    // 2.6 palindrome: check if linked list is palindrome
    public static boolean isPalindrome(ListNode n){
        ListNode reversed = reverseList(n);

        while(n!=null){
            if(reversed.val!=n.val){
                return false;
            }

            reversed=reversed.next;
            n=n.next;
        }

        return true;
    }

    public static ListNode reverseList(ListNode n){
        ListNode end=null;

        while(n!=null){
            ListNode newNode=new ListNode();
            newNode.val=n.val;
            newNode.next=end;
            end=newNode;

            n=n.next;
        }

        return end;
    }

    // 2.7 Intersection:return the intersecting node if two singly linked list intersect to each other.
    public static ListNode intersection(ListNode n1, ListNode n2){
        // check if intersect
        int len1=1;
        int len2=1;
        ListNode temp1=n1;
        ListNode temp2=n2;

        while(temp1.next!=null){
            len1++;
            temp1=temp1.next;
        }

        while(temp2.next!=null){
            len2++;
            temp2=temp2.next;
        }

        if(temp1!=temp2){
            return null;
        }

        if(len1>len2){
            for(int i=0; i<len1-len2;i++){
                n1=n1.next;
            }
        }else if(len2>len1){
            for(int i=0;i<len2-len1;i++){
                n2=n2.next;
            }
        }

        // Another work around for 2.6
//         if(len1<len2){
//             n1=padList(n1, len2-len1);
//         }else{
//             n2=padList(n2, len1-len2);
//         }

        return getIntersection(n1, n2);
    }

    public static ListNode padList(ListNode n, int l){
        ListNode head=n;
        for(int i=0; i<l; i++){
            ListNode res = new ListNode(0);
            res.next=head;
            head=res;
        }
        return head;
    }

    public static ListNode getIntersection(ListNode n1, ListNode n2){
        if(n1==null && n2==null){
            return null;
        }

        while(n1!=n2){
            n1=n1.next;
            n2=n2.next;
        }

        return n1;
    }

    // 2.8 Loop Detection: return the node at beginning of the loop in circular linked list.
    // ex: a->b->c->d->e->c
    // If slow goes to beginning of loop used K steps, then fast is LOOP_SIZE - K behind slower. We also know every move fast will move one step closer to slow. So after LOOP_SIZE - K step, they will colide and they are K steps behind beginning of loop.
    // solution: using "ruuner" to find collision point which is k steps behind the beginning of the loop
    public static ListNode loopDetection(ListNode n){
        ListNode slow=n;
        ListNode fast=n;

        // Find the collision point which is k step behind the beginning point.
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;

            if(slow==fast){
                break;
            }
        }

        // If fast equal to null, no loop.
        if(fast==null || fast.next==null){
            return null;
        }

        // Set slow to head and find the beginning of the loop.
        slow=n;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }

        return slow;
    }
}
