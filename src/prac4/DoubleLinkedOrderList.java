package prac4;

import java.util.Iterator;

/**
 *
 * @author dorian
 */
public class DoubleLinkedOrderList<T extends Comparable> implements prac4.OrderedListADT<T>{
    /**
     * The first node of the list
     */
    private DoubleNode<T> first;
    /**
     * The last node of the list
     */
    private DoubleNode<T> last;
    /**
     * Number of elements
     */
    private int count;

    /**
     * @see prac4.OrderedListADT#add(java.lang.Object) 
     */
    @Override
    public void add(T element) {
        DoubleNode<T> node=new DoubleNode<>(element);
        
        //si es el primero...
        if(count==0){
            first=node;
            last=first;
            
        //si es el más grande...
        }else if(last.compareTo(element)<=0){
            last.setNext(node);
            node.setPrevious(last);
            last=node;
            
        //si es el más pequeño...
        }else if(first.compareTo(element)>=0){
            node.setNext(first);
            first.setPrevious(node);
            first=node;
            
        //si está por el medio...
        }else{
            DoubleNode<T> target=first.getNext();
            boolean colocado=false;
            //mientras haya elementos y no esté colocado...
            while(target!=null && !colocado){
                if(target.compareTo(element)>=0){
                    //establezco antes y después del nodo
                    node.setNext(target);
                    node.setPrevious(target.getPrevious());
                    //el anterior nodo tiene de siguiente el nuevo nodo
                    //siempre habrá un previous porque mínimo son dos al entrar
                    //aquí y empiezo en el segundo que tiene de previous el primero
                    target.getPrevious().setNext(node);
                    //el nodo posterior tiene como anterior el nuevo nodo
                    target.setPrevious(node);
                    colocado=true;
                }
                //voy al siguiente nodo
                target=target.getNext();
            }
        }
        count++;
    }

    /**
     * @see prac4.OrderedListADT#eliminaDuplicados() 
     */
    @Override
    public void eliminaDuplicados() {
        DoubleNode actual=first;
        DoubleNode next=first.getNext();
        
        //mientras haya elementos...
        while(actual!=null && next!=null){
            //si el elemento actual es igual al siguiente...
            if(actual.compareTo(next.getElement())==0){
                //recojo el siguiente nodo...
                next = next.getNext();
                //y lo junto con el actual, eliminando el del medio repetido
                actual.setNext(next);
                //actualizo el previous también para perder toda referencia 
                //al nodo del medio
                if(next!=null){
                    next.setPrevious(actual);
                }
                //decremento el contador
                count--;
                //la siguiente iteración volverá a comparar el actual con
                //el siguiente por si también está repetido
            }else{
                //avanza en una posición
                actual=next;
                next=next.getNext();
            }
        }
    }

    /**
     * 
     * @see prac4.OrderedListADT#interseccion(prac4.OrderedListADT) 
     */
    @Override
    public OrderedListADT<T> interseccion(OrderedListADT<T> lista) {
        //creo una nueva lista
        OrderedListADT interseccion=new LinkedOrderList();
        //referencia al iterador...
        Iterator iterator;
        //referencia a una de las dos listas
        OrderedListADT looking;
        
        //selecciono como iterador principal al de mayor tamaño
        if(lista.size()<this.size()){
            iterator=this.iterator();
            looking=lista;
        }else{
            iterator=lista.iterator();
            looking=this;
        }
        
        //itero y si está en ambas listas y no en la intersección, lo incluyo
        for (; iterator.hasNext();) {
            T target = (T)iterator.next();
            if(looking.contains(target)){
                if(!interseccion.contains(target)){
                    interseccion.add(target);
                }
            }
        }
        
        return interseccion;
    }

    /**
     * 
     * @see prac4.OrderedListADT#union(prac4.OrderedListADT) 
     */
    @Override
    public OrderedListADT<T> union(OrderedListADT<T> lista) {
        //creo una nueva lista
        OrderedListADT union=new LinkedOrderList();
        
        //itero una de las dos listas e incluyo todos sus elementos si no están
        for (Iterator<T> it = this.iterator(); it.hasNext();) {
            T target = it.next();
            if(!union.contains(target)){
                union.add(target);
            }
        }
        
        //itero la otra e incluyo todos sus elementos si no están
        for (Iterator<T> it = lista.iterator(); it.hasNext();) {
            T target = it.next();
            if(!union.contains(target)){
                union.add(target);
            }
        }
        return union;
    }

    /**
     * 
     * @see prac4.ListADT#removeFirst() 
     */
    @Override
    public T removeFirst() {
        T target=null;
        //si la lista no está vacía...
        if(first!=null){
            //cojo el elemento para devolverlo
            target=first.getElement();
            //actulizo el first
            first=first.getNext();
            //eliminando el de antes con previous=null
            first.setPrevious(null);
            //actualizo contador
            count--;
        }
        return target;
    }

    /**
     * 
     * @see prac4.ListADT#removeLast()  
     */
    @Override
    public T removeLast() {
        T target=null;
        //si hay elementos...
        if(last!=null){
            //recojo el elemento a devolver
            target=last.getElement();
            //actualizo el last
            last=last.getPrevious();
            //elimino el nodo borrando la referencia
            last.setNext(null);
            //actualizo contador
            count--;
        }
        return target;
    }

    /**
     * 
     * @see prac4.ListADT#remove(java.lang.Object)  
     */
    @Override
    public T remove(T element) {
        boolean found=false;
        //si hay elementos....
        if(last!=null){
            //guardo referencia a dos nodos contiguos
            DoubleNode previous=first;
            DoubleNode actual=first.getNext();

            //si es el último, me remito a eliminar el último hasta que cambie
            while(last.compareTo(element)==0){
                removeLast();
                found=true;
            }
            
            //lo mismo para el caso del primero
            while(first.compareTo(element)==0){
                removeFirst();
                found=true;
            }

            //mientras haya elementos y no se haya eliminado...
            while(actual!=null && !found){
                //si el elemento es igual, lo hemos encontrado
                //y entramos en un bucle que eliminará todos esos elementos
                while(actual.compareTo(element)==0){
                    //he encontrado el elemento y eliminado para parar el bucle
                    //superior cuando termine con los repetidos
                    found=true;
                    
                    //cojo el siguiente elemento
                    actual=actual.getNext();
                    //el anterior apuntará a ese elemento, perdiendo (eliminando)
                    //el nodo del medio
                    previous.setNext(actual);
                    //actualizo también el previous para perder el elemento del medio
                    if(actual!=null){
                        actual.setPrevious(previous);
                    }
                    
                    //si en algún momento se me acaba la lista, rompo este bucle
                    if(actual==null){
                        break;
                    }
                }
                
                //compruebo siempre que no me haya quedado la variable a null
                if(actual!=null){
                    //actualizamos referencias
                    previous=actual;
                    actual=actual.getNext();
                }
            }
        }
        
        //si lo he encontrado, devuelvo el elemento
        if(found){
            return element;
            
        //para cualquier otro caso (lista vacia, no encontrado...), devuelvo null
        }else{
            return null;
        }
    }

    /**
     * 
     * @see prac4.ListADT#first() 
     */
    @Override
    public T first() {
        if(first==null){
            return null;
        }
        return first.getElement();
    }

    /**
     * 
     * @see prac4.ListADT#last() 
     */
    @Override
    public T last() {
        if(last==null){
            return null;
        }
        return last.getElement();
    }

    /**
     * 
     * @see prac4.ListADT#contains(java.lang.Object) 
     */
    @Override
    public boolean contains(T target) {
        //si la lista está vacía, no hay nada
        if(count==0){
            return false;
            
        //si el elemento es más grande que el último de la lista
        //no está
        }else if(last.compareTo(target)<0){
            return false;
        
        
        //si el elemento es más pequeño que el primer de la lista
        //no está
        }else if(first.compareTo(target)>0){
            return false;
        }
        
        boolean found=false;
        //itero hasta encontrar el elemento (si está)
        for (Iterator<T> it = this.iterator(); it.hasNext() && !found;) {
            T actual = it.next();
            if(actual.equals(target)){
                found=true;
            }
        }
        
        //devuelvo resultados
        return found;
    }

    /**
     * 
     * @see prac4.ListADT#isEmpty() 
     */
    @Override
    public boolean isEmpty() {
        return count==0;
    }

    /**
     * 
     * @see prac4.ListADT#size() 
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * 
     * @see prac4.ListADT#iterator() 
     */
    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedIterator<>(first);
    }
    
    /**
     * 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString(){
        StringBuilder buffer=new StringBuilder();
        for (Iterator<T> it = this.iterator(); it.hasNext();) {
            T element = it.next();
            buffer.append(element.toString());
            buffer.append(" ");
        }
        if(buffer.length()>0){
            buffer.delete(buffer.length()-1, buffer.length());
        }
        return buffer.toString();
    }
    
}
