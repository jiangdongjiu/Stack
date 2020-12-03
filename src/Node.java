public class Node<T> {
    private T element;
    private Node<T> previousNode;

    public Node(){
        this.element = null;
        this.previousNode = null;
    }

    public Node(T element){
        this.element = element;
        this.previousNode = null;
    }
    public Node(T element, Node<T> previousNode){
        this.element = element;
        this.previousNode = previousNode;
    }


    public T getElement(){
        return element;
    }

    public Node<T> getPrevious(){
        return  previousNode;
    }

    public void setElement(T element){
        this.element = element;
    }

    public void setPrevious(Node<T> previousNode){
        this.previousNode = previousNode;
    }
}
