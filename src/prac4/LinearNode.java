package prac4;

/**
 *
 * @author dorian
 */
public class LinearNode<T extends Comparable> implements Comparable<T>{
    /**
     * The next node of this node
     */
    private LinearNode<T> next;
    /**
     * the element of the node
     */
    private T element;

    /**
     * Default Constructor
     */
    public LinearNode() {
        next=null;
        element=null;
    }

    /**
     * Constructor
     * @param element element of the node
     */
    public LinearNode(T element) {
        this.next = null;
        this.element = element;
    }
    
    /**
     * Set the next node
     * @param node the next node
     */
    public void setNext(LinearNode<T> node){
        next=node;
    }
    
    /**
     * Get the next node
     * @return the next node
     */
    public LinearNode<T> getNext(){
        return next;
    }
    
    /**
     * Get the element of the node
     * @return the element of the node
     */
    public T getElement(){
        return element;
    }
    
    /**
     * Set the element of the node
     * @param element the new element of the node
     */
    public void setElement(T element){
        this.element=element;
    }

    /**
     * 
     * @see java.lang.String#compareTo(java.lang.String) 
     */
    @Override
    public int compareTo(T o) {
        return element.compareTo(o);
    }
}
