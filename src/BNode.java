public class BNode<T> {
    T data;
    BNode<T> left;
    BNode<T> right;

    public BNode(T value, BNode<T> leftTree,BNode<T> rightTree){
        data = value;
        left = leftTree;
        right = rightTree;
    }
    public BNode(T value){
        this(value,null,null);
    }
}
