package prac4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author dorian
 */
public class LinkedIterator<T extends Comparable> implements Iterator<T> {
    /**
     * The current element of the iteration of the list
     */
    private LinearNode<T> current;

    /**
     * Constructor
     * @param list the first node of the list 
     */
    public LinkedIterator(LinearNode<T> list) {
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
     * Not implement
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
