package software.davidson.ian.linkedList.RemoveNthNodeFromEndOfLinkedList;

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
    public static void main(String [] args){
        Solution solution = new Solution();
        //[1,2]
        ListNode l2 = new ListNode(2);
        ListNode l1 = new ListNode(1, l2);

        System.out.println(solution.removeNthFromEnd(l1, 1));
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null){
            return null;
        }

        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode left = temp;
        ListNode right = temp;

        while(n > 0){
            right = right.next;
            n--;
        }

        while(right.next != null){
            right = right.next;
            left = left.next;
        }

        System.out.println("left:  "+ left.val);

        left.next = left.next.next;
        return temp.next;
    }
}
