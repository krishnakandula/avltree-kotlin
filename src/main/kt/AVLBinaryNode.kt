/**
 * Created by Krishna Chaitanya Kandula on 10/11/2017.
 */
class AVLBinaryNode<T : Comparable<T>>(var data: T,
                                       var leftChild: AVLBinaryNode<T>?,
                                       var rightChild: AVLBinaryNode<T>?) {

    var height: Int = 0

    constructor(data: T) : this(data, null, null)

}
