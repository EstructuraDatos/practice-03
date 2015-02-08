package prac4;


import java.util.Iterator;

/**
 *
 * @author dorian
 */
public interface ListADT<T>{
    /**
     * Elimina y devuelve el primer elemento de la lista
     */
    public T removeFirst ();
    
    /**
     * Elimina y devuelve el último elemento de la lista 
     * @return el último elemento de la lista
     */
    public T removeLast ();
    
    /**
     * Elimina y devuelve un elemento concreto de la lista
     * @param element elemento a eliminar
     * @return el elemento si se ha eliminado o null si no está
     */
    public T remove (T element);
    
    
    /**
     * Devuelve una referencia al primer elemento de la lista
     * @return primer elemento de la lista
     */
    public T first ();
    
    /**
     * Devuelve una referencia al último elemento de la lista
     * @return último elemento de la lista
     */
    public T last ();
    
    /**
     * Devuelve true si la lista contiene el elemento pasado como parámetro
     * @param target elemento a comprobar
     * @return true si está, false si no está
     */
    public boolean contains (T target);
    
    /**
     * Devuelve true si la lista no contiene elementos 
     * @return true si la lista está vacía, false si no
     */
    public boolean isEmpty();
    
    /**
     * Devuelve el número de elementos de la lista 
     * @return número de elementos de la lista
     */
    public int size();
    
    /**
     * Devuelve un iterador para los elementos de la lista
     * @return iterador de la lista
     */
    public Iterator<T> iterator();
    
    /**
     * Devuelve una representación String de la lista
     * @return una representación String de la lista 
     */
    @Override
    public String toString(); 
}
