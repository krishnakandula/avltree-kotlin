/**
 * Created by Krishna Chaitanya Kandula on 10/11/2017.
 */
interface BinarySearchTree<in T : Comparable<T>> {

    fun insert(data: T)

    fun remove(data: T)

    fun search(data: T): Boolean
}
