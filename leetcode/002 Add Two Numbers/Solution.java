/**
 * Classic add and carry operation
 * 
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addWithCarry(l1, l2, 0);

    }

    public ListNode addWithCarry(ListNode l1, ListNode l2, int carry) {

        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        int val1 = l1 == null ? 0 : l1.val;
        int val2 = l2 == null ? 0 : l2.val;
        ListNode next1 = l1 == null ? null : l1.next;
        ListNode next2 = l2 == null ? null : l2.next;

        int sum = (carry + val1 + val2) % 10;
        int newCarry = (carry + val1 + val2) / 10;

        return new ListNode(sum, addWithCarry(next1, next2, newCarry));

    }

    public static void main(String[] args) {
        
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
