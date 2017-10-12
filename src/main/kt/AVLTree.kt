/**
 * Created by Krishna Chaitanya Kandula on 10/11/2017.
 */
class AVLTree<in T : Comparable<T>> : BinarySearchTree<T> {

    companion object {
        private val ALLOWED_IMBALANCE: Int = 1
    }

    private var head: AVLBinaryNode<T>? = null

    override fun insert(data: T) {
        if (head == null) head = AVLBinaryNode(data)
        else insert(data, head!!)
    }

    private fun insert(data: T, node: AVLBinaryNode<T>): AVLBinaryNode<T> {
        when {
            node.data < data -> {
                if (node.leftChild == null)
                    node.leftChild = AVLBinaryNode(data)
                else
                    node.leftChild = insert(data, node.leftChild!!)
            }
            node.data > data -> {
                if (node.rightChild == null)
                    node.rightChild = AVLBinaryNode(data)
                else
                    node.leftChild = insert(data, node.rightChild!!)
            }
        }

        return balance(node)
    }

    private fun balance(node: AVLBinaryNode<T>): AVLBinaryNode<T> {
        var balancedNode = node

        when {
            height(node.leftChild) - height(node.rightChild) > ALLOWED_IMBALANCE -> {
                //Check if we need to perform a single or double rotation
                balancedNode = if (height(node.leftChild!!.leftChild) >= height(node.leftChild!!.rightChild)) {
                    singleRotateLeftChild(node)
                } else {
                    doubleRotateLeftChild(node)
                }
            }

            height(node.rightChild) - height(node.leftChild) > ALLOWED_IMBALANCE -> {
                balancedNode = if (height(node.rightChild!!.rightChild) > height(node.rightChild!!.leftChild)) {
                    singleRotateRightChild(node)
                } else {
                    doubleRotateRightChild(node)
                }
            }
        }

        balancedNode.height = 1 + Math.max(height(balancedNode.leftChild), height(balancedNode.rightChild))
        return balancedNode
    }

    private fun singleRotateLeftChild(node: AVLBinaryNode<T>): AVLBinaryNode<T> {
        //Root becomes left child of current root
        //Node to right of new root becomes left of old root
        val newRoot = node.leftChild!!
        node.leftChild = newRoot.rightChild
        newRoot.rightChild = node

        //Recalculate heights
        node.height = 1 + Math.max(height(node.leftChild), height(node.rightChild))
        newRoot.height = 1 + Math.max(height(newRoot.leftChild), height(newRoot.rightChild))

        return newRoot
    }

    private fun singleRotateRightChild(node: AVLBinaryNode<T>): AVLBinaryNode<T> {
        val newRoot = node.rightChild!!
        node.rightChild = newRoot.leftChild
        newRoot.leftChild = node

        node.height = 1 + Math.max(height(node.leftChild), height(node.rightChild))
        newRoot.height = 1 + Math.max(height(newRoot.leftChild), height(newRoot.rightChild))

        return newRoot
    }

    private fun doubleRotateLeftChild(node: AVLBinaryNode<T>): AVLBinaryNode<T> {
        node.leftChild = singleRotateRightChild(node.leftChild!!)
        return singleRotateLeftChild(node)
    }

    private fun doubleRotateRightChild(node: AVLBinaryNode<T>): AVLBinaryNode<T> {
        node.rightChild = singleRotateLeftChild(node.rightChild!!)
        return singleRotateRightChild(node)
    }

    override fun remove(data: T) {
    }

    override fun search(data: T): Boolean {
        return true
    }

    private fun height(node: AVLBinaryNode<T>?) = node?.height ?: -1
}
