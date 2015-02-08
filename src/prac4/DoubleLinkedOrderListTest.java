package prac4;


import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author dorian
 */
public class DoubleLinkedOrderListTest {
    private DoubleLinkedOrderList<String> list;
    private DoubleLinkedOrderList<String> another;
    
    @Before
    public void setUp(){
        list=new DoubleLinkedOrderList<>();
        another=new DoubleLinkedOrderList<>();
        list.add("A");
        list.add("B");
        list.add("C");
    }
    
    @Test
    public void testAdd(){
        list.add("D");
        assertEquals(4, list.size());
        assertTrue(list.contains("D"));
        assertEquals("D", list.last());
        
        
        another.add("A");
        assertEquals(1, another.size());
        assertTrue(another.contains("A"));
        assertEquals("A", another.last());
        assertEquals("A", another.first());
        
        list.add("A");
        list.add("A");
        list.add("C");
        assertEquals("A A A B C C D", list.toString());
    }
    
    @Test
    public void testEliminaDuplicados(){
        list.add("A");
        list.add("A");
        list.add("C");
        assertEquals("A A A B C C", list.toString());
        assertEquals(6, list.size());
        list.eliminaDuplicados();
        assertEquals("A B C", list.toString());
        assertEquals(3, list.size());
    }
    
    @Test
    public void testInterseccion(){
        another.add("A");
        another.add("A");
        list.add("A");
        another.add("B");
        another.add("R");
        another.add("O");
        assertEquals("A B", list.interseccion(another).toString());
    }
    
    @Test
    public void testUnion(){
        list.add("A");
        another.add("A");
        another.add("B");
        another.add("R");
        another.add("O");
        assertEquals("A B C O R", list.union(another).toString());
    }
    
    @Test
    public void testRemoveFirst(){
        assertEquals("A", list.removeFirst());
        assertEquals(2, list.size());
        assertEquals("B", list.first());
        
        assertEquals(null, another.removeFirst());
    }
    
    @Test
    public void testRemoveLast(){
        list.add("D");
        assertEquals("D", list.removeLast());
        assertEquals(3, list.size());
        assertEquals("C", list.last());
        
        assertEquals(null, another.removeLast());
    }
    
    @Test
    public void testRemove(){
        assertEquals("A", list.remove("A"));
        assertEquals("B C", list.toString());
        list.add("A");
        list.add("C");
        list.add("C");
        list.remove("C");
        assertEquals("A B", list.toString());
        list.add("E");
        list.add("R");
        list.add("O");
        list.remove("E");
        assertEquals("A B O R", list.toString());
        
        assertEquals(null, another.remove("A"));
        
    }
    
    @Test
    public void testFirst(){
        assertEquals("A", list.first());
        assertEquals(null, another.first());
        another.add("B");
        assertEquals("B", another.first());
    }
    
    @Test
    public void testLast(){
        assertEquals("C", list.last());
        assertEquals(null, another.last());
        another.add("B");
        assertEquals("B", another.last());
    }
    
    @Test
    public void testContains(){
        assertTrue(list.contains("A"));
        assertFalse(list.contains("J"));
        assertFalse(another.contains("E"));
    }
    
    @Test
    public void testIsEmpty(){
        assertFalse(list.isEmpty());
        assertTrue(another.isEmpty());
    }
    
    @Test
    public void testSize(){
        assertEquals(3, list.size());
        list.add("A");
        list.add("B");
        assertEquals(5, list.size());
        assertEquals(0, another.size());
    }
    
    @Test
    public void testToString(){
        assertEquals("A B C", list.toString());
        assertEquals("", another.toString());
        another.add("E");
        assertEquals("E", another.toString());
        
        list.add("R");
        assertEquals("A B C R", list.toString());
    }
    
    @Test
    public void testIterator(){
        StringBuilder buffer=new StringBuilder();
        for (Iterator<String> it = list.iterator(); it.hasNext();) {
            String element = it.next();
            buffer.append(element.toString());
            buffer.append(" ");
        }
        buffer.delete(buffer.length()-1, buffer.length());
        assertEquals("A B C", buffer.toString());
    }
}