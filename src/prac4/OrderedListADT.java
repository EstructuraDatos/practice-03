package prac4;

/**
 *
 * @author dorian
 */
public interface OrderedListADT<T> extends ListADT<T> {
    /**
     * Añade de forma ordenada el elemento a la lista 
     * @param element elemento a añadir
     */
    public void add (T element); 
    
    /**
     * elimina los elementos repetidos de la lista.
     */
    public void eliminaDuplicados();
    
    /**
     * Devuelve la intersección de dos listas ordenadas sin elementos repetidos
     * @param lista lista con la que hacer la intersección
     * @return nueva lista con la intersección
     */
    public OrderedListADT<T> interseccion(OrderedListADT<T> lista);
    
    /**
     * Devuelve la unión de dos listas ordenadas sin elementos repetidos
     * @param lista lista con la que hacer la unión
     * @return una nueva lista con la unión
     */
    public OrderedListADT<T> union(OrderedListADT<T> lista);
} 