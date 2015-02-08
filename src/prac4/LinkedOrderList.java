package prac4;

import java.util.Iterator;

/**
 *
 * @author dorian
 */
public class LinkedOrderList<T extends Comparable> implements OrderedListADT<T>{
    /**
     * The last node of the list
     */
    private LinearNode<T> last;
    /**
     * Number of elements
     */
    private int count;

    /**
     * @see prac4.OrderedListADT#add(java.lang.Object) 
     */
    @Override
    public void add(T element) {
        LinearNode<T> node=new LinearNode<>(element);
        
        //si es el primero...
        if(count==0){
            last=node;
            
        //si es el más grande... (último de la lista)
        }else if(last.compareTo(element)<=0){
            node.setNext(last);
            last=node;
        
        //si está por el medio...
        }else{
            //guardo referencias de dos elementos contiguos
            LinearNode previous=last;
            LinearNode actual=last.getNext();
            boolean found=false;
            
            //mientras haya elementos y no se haya colocado...
            while(actual!=null && !found){
                //si el elemento es más grande o igual, hemos encontrado sitio
                if(actual.compareTo(element)<=0){
                    //delante del anterior que es más grande todavia
                    previous.setNext(node);
                    //el nodo es más grande, se coloca detrás del actual
                    node.setNext(actual);
                    found=true;
                }else{
                    //se avanza en una posición
                    previous=actual;
                    actual=actual.getNext();
                }
            }
            
            //si no encontró la posición, es que es el más pequeño de todos
            if(!found){
                previous.setNext(node);
            }
            
        }
        //incremento el contador
        count++;
    }

    /**
     * @see prac4.OrderedListADT#eliminaDuplicados() 
     */
    @Override
    public void eliminaDuplicados() {
        LinearNode actual=last;
        LinearNode next=last.getNext();
        
        //mientras haya elementos...
        while(actual!=null && next!=null){
            //si el elemento actual es igual al siguiente...
            if(actual.compareTo(next.getElement())==0){
                //recojo el siguiente nodo...
                next = next.getNext();
                //y lo junto con el actual, eliminando el del medio repetido
                actual.setNext(next);
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
        T target;
        
        //si la lista está vacía...
        if(count==0){
            target=null;
            
        //si la lista sólo tiene un elemento...
        }else if(count==1){
            //coincide con el last
            target=last.getElement();
            last=null;
            count=0;
            
        //para el resto de casos...
        }else{
            //variable para guardar el nodo anterior al primero
            LinearNode previousFirst=last;
            //itero para alcanzar ese nodo segundo
            for(int i=0; i<count-2; i++){
                previousFirst=previousFirst.getNext();
            }
            
            //recojo el elemento del primer nodo
            target=(T)previousFirst.getNext().getElement();
            //hago los cambios, el segundo no apunta a nada
            previousFirst.setNext(null);
            //decremento contador
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
        //si hay elemento
        if(last!=null){
            //recojo el elemento
            T target=last.getElement();
            //dejo como último al siguiente
            last=last.getNext();
            //decremento el contador
            count--;
            //devuelvo resultado
            return target;
            
        //si no hay elemento, devuelvo null
        }else{
            return null;
        }
    }

    /**
     * 
     * @see prac4.ListADT#remove(java.lang.Object)  
     */
    @Override
    public T remove(T element) {
        //si hay elementos....
        if(last!=null){
            //guardo referencia a dos nodos contiguos
            LinearNode previous=last;
            LinearNode actual=last.getNext();
            boolean found=false;

            //si es el último, me remito a eliminar el último hasta que cambie
            while(last.compareTo(element)==0){
                removeLast();
                found=true;
            }

            //mientras haya elementos y no se haya eliminado...
            while(actual!=null && !found){
                //si el elemento es igual, lo hemos encontrado
                //y entramos en un bucle que eliminará todos esos elementos
                while(actual.compareTo(element)==0){
                    //cojo el siguiente elemento
                    actual=actual.getNext();
                    //el anterior apuntará a ese elemento, perdiendo (eliminando)
                    //el nodo del medio
                    previous.setNext(actual);
                    
                    //he encontrado el elemento y eliminado para parar el bucle
                    //superior cuando termine con los repetidos
                    found=true;
                    
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

            //si lo he encontrado, devuelvo el elemento
            if(found){
                return element;
            }
        }
        
        //para cualquier otro caso (lista vacia, no encontrado...), devuelvo null
        return null;
    }

    /**
     * 
     * @see prac4.ListADT#first() 
     */
    @Override
    public T first() {
        T target=null;
        //itero hasta alcanzar el primero
        for (Iterator<T> it = this.iterator(); it.hasNext();) {
            target = it.next();
        }
        return target;
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
        return new LinkedIterator<>(last);
    }
    
    /**
     * 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString(){
        StringBuilder buffer=new StringBuilder();
        //itero todos los elementos
        for (Iterator<T> it = this.iterator(); it.hasNext();) {
            T element = it.next();
            //como los voy recorriendo al revés, añado por delante
            buffer.insert(0, element.toString());
            buffer.insert(0, " ");
        }
        //elimino el espacio inicial que me queda
        if(buffer.length()>0){
            buffer.delete(0, 1);
        }
        return buffer.toString();
    }
    
}
