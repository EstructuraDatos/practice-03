package prac4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author dorian
 */
public class DoubleLinkedIterator<T extends Comparable> implements Iterator<T> {
    /**
     * The current element of the list
     */
    private DoubleNode<T> current;

    /**
     * 
     * @param list the first node of the list
     */
    public DoubleLinkedIterator(DoubleNode<T> list) {
        this.current =list;
    }
    
    /**
     * 
     * @see java.util.Iterator#hasNext()  
     */
    @Override
    public boolean hasNext() {
        return (current!=null);
    }

    /**
     * 
     * @see java.util.Iterator#next() 
     */
    @Override
    public T next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        T target=current.getElement();
        current=current.getNext();
        return target;
    }

    /**
     * Not supported
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
