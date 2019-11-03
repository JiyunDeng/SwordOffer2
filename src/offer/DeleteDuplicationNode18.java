package offer;

public class DeleteDuplicationNode18 {
	 public ListNode deleteDuplication(ListNode pHead){
		 if (pHead == null) return null;
		 //虚拟一个头结点，避免头节点被删除找不到
		 ListNode dummy = new ListNode(0);
		 dummy.next = pHead;
		 ListNode p = pHead;
		 ListNode preNode = dummy;
		 
		 while(p != null && p.next != null) {
			 if(p.val == p.next.val) {
				 int val = p.val;
				//多个重复节点时
				 while(p != null && p.val == val) {
					 p = p.next;
				 }
				 //赋值：相当于删除
				 preNode.next = p;
			 } else {
				 preNode = p;
				 p = p.next;
			 }
		 }
		 return dummy.next;
	 }
}