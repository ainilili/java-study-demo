package org.nico.data.structure.tree;

/**
 * AVL：左右子树最大深度差的绝对值小于2
 *
 * @author nico
 */
public class AVLTree extends BinarySortTree{
	
	@Override
	public Node insert(int index, Object value) {
		Node newNode = super.insert(index, value);
		if(index == 13) {
		    System.out.println(1);
		}
		rebalance((ANode)newNode);
		return newNode;
	}
	
    public Node insert(int index) {
	    return insert(index, index);
    }

	@Override
	public Node remove(int index) {
		return super.remove(index);
	}

	@Override
	public Node createNode(int index, Object value, Node parent) {
		return new ANode(index, value, parent);
	}

	private void rebalance(ANode node) {
	    flushLinkDeep(node);
	}
	
	private boolean rebalanceF(ANode node, ANode newNode) {
	    if(Math.abs(node.leftDeep - node.rightDeep) > 1) {
            if(node.leftDeep > node.rightDeep) {
                if(newNode.index < node.left.index) {
                    rightRotate(node);
                }else {
                    rightRotate(node);
                    ANode n = findTarget(node);
                    if(n != null) {
                        leftRotate(n);
                    }
                }
            }else {
                if(newNode.index < node.right.index) {
                    leftRotate(node);
                    ANode n = findTarget(node);
                    if(n != null) {
                        rightRotate(n);
                    }
                }else {
                    leftRotate(node);
                }
                
            }
            return true;
        }
	    return false;
	}
	
	private void flushLinkDeep(ANode node) {
	    node.flushDeep();
	    ANode parent = null;
	    ANode cur = node;
	    while((parent = (ANode)cur.parent) != null) {
	        parent.flushDeep();
	        cur = parent;
	        if(rebalanceF(cur, node)) {
	            break;
	        }
	    }
	}
	
	private ANode findTarget(ANode node) {
        node.flushDeep();
        ANode parent = null;
        ANode cur = node;
        while((parent = (ANode)cur.parent) != null) {
            parent.flushDeep();
            cur = parent;
            if(Math.abs(cur.leftDeep - cur.rightDeep) > 1) {
                return cur;
            }
        }
        return null;
    }
	
	protected void leftRotate(Node node) {
	    Node center = node.right;
	    
	    center.parent = node.parent;
	    if(center.parent == null) {
	        this.root = center;
	    }else {
	        if(node.isLeft) {
                node.parent.left = center;    
            }else {
                node.parent.right = center;
            }
	        center.isLeft = node.isLeft;
	    }
	    
	    node.right = null;
	    node.isLeft = true;
	    
	    Node leftMost = center.left;
	    if(leftMost == null) {
	        leftMost = center;
	    }
	    while(leftMost.left != null) {
	        leftMost = leftMost.left;
	    }
	    leftMost.setLeft(node);
	    aNode(node).rightDeep = 0;
        aNode(node).flushDeep();
	    aNode(leftMost).flushDeep();
	}
	
	private ANode aNode(Node node) {
	    return (ANode)node;
	}
	
	protected void rightRotate(Node node) {
        Node center = node.left;

        center.parent = node.parent;
        if(center.parent == null) {
            this.root = center;
        }else {
            if(node.isLeft) {
                node.parent.left = center;    
            }else {
                node.parent.right = center;
            }
            center.isLeft = node.isLeft;
        }
        
        node.left = null;
        node.isLeft = false;
        
        Node rightMost = center.right;
        if(rightMost == null) {
            rightMost = center;
        }
        while(rightMost.right != null) {
            rightMost = rightMost.right;
        }
        rightMost.setRight(node);
        aNode(node).leftDeep = 0;
        aNode(node).flushDeep();
        aNode(rightMost).flushDeep();
    }
	

	class ANode extends Node{

	    private int leftDeep;
	    
	    private int rightDeep;
	    
		public ANode(int index, Object value, Node parent) {
			super(index, value, parent);
		}

        public int getLeftDeep() {
            return leftDeep;
        }

        public void setLeftDeep(int leftDeep) {
            this.leftDeep = leftDeep;
        }

        public int getRightDeep() {
            return rightDeep;
        }

        public void setRightDeep(int rightDeep) {
            this.rightDeep = rightDeep;
        }
        
        public void flushDeep() {
            if(right != null) {
                rightDeep =  Math.max(aNode(right).leftDeep, aNode(right).rightDeep) + 1;
            }
            if(left != null) {
                leftDeep =  Math.max(aNode(left).leftDeep, aNode(left).rightDeep) + 1;
            }
        }
		
	}
}
