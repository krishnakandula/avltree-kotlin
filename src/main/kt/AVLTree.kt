/**
 * Created by Krishna Chaitanya Kandula on 10/11/2017.
 */
class AVLTree<in T : Comparable<T>> : BinarySearchTree<T> {

    private var head: AVLBinaryNode<T>? = null

    override fun insert(data: T) {
        if (head == null) head = AVLBinaryNode(data)
        else insert(data, head!!)
    }

    private fun insert(data: T, node: AVLBinaryNode<T>) {
        when {
            node.data < data -> {
                if(node.leftChild == null)
                    node.leftChild = AVLBinaryNode(data)
                else
                    insert(data, node.leftChild!!)
            }
            node.data > data -> {
                if(node.rightChild == null)
                    node.rightChild = AVLBinaryNode(data)
                else
                    insert(data, node.rightChild!!)
            }
        }
    }

    override fun remove(data: T) {
    }

    override fun search(data: T): Boolean {
        return true
    }
}
