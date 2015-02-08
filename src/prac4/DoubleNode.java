package prac4;

/**
 *
 * @author dorian
 */
public class DoubleNode<T extends Comparable> implements Comparable<T>{
    /**
     * The next node
     */
    private DoubleNode<T> next;
    /**
     * The previous node
     */
    private DoubleNode<T> previous;
    /**
     * The element of the node
     */
    private T element;

    /**
     * Default Constructor
     */
    public DoubleNode() {
        next=null;
        element=null;
        previous=null;
    }

    /**
     * Constructor
     * @param element the element of the node 
     */
    public DoubleNode(T element) {
        this.next = null;
        this.element = element;
    }
    
    /**
     * Set the next node
     * @param node the new next node
     */
    public void setNext(DoubleNode<T> node){
        next=node;
    }
    
    /**
     * Get the next node
     * @return the next node
     */
    public DoubleNode<T> getNext(){
        return next;
    }

    /**
     * Get the previous node
     * @return the previous node
     */
    public DoubleNode<T> getPrevious() {
        return previous;
    }

    /**
     * Set the previous node
     * @param previous the new previous node
     */
    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
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
     * @see java.lang.Comparable#compareTo(java.lang.Object) 
     */
    @Override
    public int compareTo(T o) {
        //Comparable thisElement=element;
        return element.compareTo(o);
    }
}
