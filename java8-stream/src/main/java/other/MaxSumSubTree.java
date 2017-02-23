package other;

/**
 * Created by hjy on 17-2-23.
 * 二叉树，节点全部为整数，找到所有节点和最大的子树
 * 使用递归算法，使用前序遍历，首先分别计算左右子树各自的子树和，然后记录目前最大的
 * 再加入当前的父节点，再计算父节点开头的子树是否最大，改层递归上去即可
 */
public class MaxSumSubTree {

    class TreeNode{
        TreeNode left,right;
        int tag;

        public TreeNode(int tag) {
            this.tag = tag;
        }
    }


    private TreeNode maxRoot = new TreeNode(0);
    public int find(TreeNode root){
        if (root==null){
            return 0;
        }else {
            int lsum = find(root.left);
            int rsum = find(root.right);
            if (maxRoot.tag<lsum){
                maxRoot = root.left;
            }
            if (maxRoot.tag<rsum){
                maxRoot = root.right;
            }
            return root.tag+lsum+rsum;
        }
    }

}
