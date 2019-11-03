package offer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import offer.TreeNode;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * @author dengjiyun
 *
 */
public class ReConstructBinaryTree7 {
	
	/**
	 * 链接：https://www.nowcoder.com/questionTerminal/8a19cbe657394eeaac2f6ea9b0f6fcf6?answerType=1&f=discussion
	 *来源：牛客网
	 * @return
	 */
	public TreeNode reConstructBinaryTree3(int[] pre, int[] in) {
		if (pre.length == 0 || in.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(pre[0]);
		// 在中序中找到前序的根
		for (int i = 0; i < in.length; i++) {
			if (in[i] == pre[0]) {
				// 左子树，注意 copyOfRange 函数，左闭右开
				root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
				// 右子树，注意 copyOfRange 函数，左闭右开
				root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length),
						Arrays.copyOfRange(in, i + 1, in.length));
				break;
			}
		}
		return root;
	}
    
	/**
	 * 先拿出前序遍历的头节点，再拿出中序遍历的节点，遍历中序，二值相等者找到了下一次的前序遍历的长度
	 * @param preorder
	 * @param inorder
	 * @return
	 */
    public TreeNode reConstructBinaryTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 ||
                inorder == null || inorder.length == 0) return null;
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    private TreeNode helper(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR) {
        if (preL > preR || inL > inR) {
            return null;
        }
        int rootVal = preorder[preL];
        int index = 0;
        //寻找根在中序遍历中的位置
        while (index <= inR && inorder[index] != rootVal) {
            index++;
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = helper(preorder, preL + 1, preL - inL + index, inorder, inL, index);
        root.right = helper(preorder, preL - inL + index + 1, preR, inorder, index + 1, inR);
        return root;
    }
    
	
	/**
	 * 
	 * @param pre
	 * @param in
	 * @return
	 */
    public TreeNode reConstructBinaryTree1(int [] pre,int [] in) {
        return reConBTree(pre,0,pre.length-1,in,0,in.length-1);
    }
    public TreeNode reConBTree(int[] pre,int preleft,int preright,int[] in,int inleft,int inright){
        if(preleft > preright || inleft> inright)//当到达边界条件时候返回null
            return null;
        //新建一个TreeNode
        TreeNode pRootOfTree = new TreeNode(pre[preleft]);
        //对中序数组进行输入边界的遍历
        for(int i = inleft; i<= inright; i++){
        	/*递归嵌套在循环，不够优雅*/
            if(pre[preleft] == in[i]){
                //重构左子树，注意边界条件
                pRootOfTree.left = reConBTree(pre,preleft+1,preleft+i-inleft,in,inleft,i-1);
                //重构右子树，注意边界条件
                pRootOfTree.right = reConBTree(pre,preleft+i+1-inleft,preright,in,i+1,inright);
                break;
            }
        }
        return pRootOfTree;    
    }
	
	
	/**
     * 缓存中序遍历数组每个值对应的索引
     */
    private Map<Integer, Integer> indexForInOrders = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        for (int i = 0; i < in.length; i++){
            indexForInOrders.put(in[i], i);
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL){
        if (preL > preR){
            return null;
        }

        TreeNode root = new TreeNode(pre[preL]);

        int inIndex = indexForInOrders.get(root.val);
        int leftTreeSize = inIndex - inL;

        root.left = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL);
        root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);

        return root;
    }
}
