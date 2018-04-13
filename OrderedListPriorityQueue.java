package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class OrderedListPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
	
	private OrderedList<E> list;
	public OrderedListPriorityQueue(){
		list = new OrderedList<E>();
	}
	
	public boolean insert(E object) {
		return list.insert(object);
	}
	public E remove() {
		return list.removeMin();
	}

	public E peek() {
		return list.findMin();
	}

	public boolean contains(E obj) {
		return list.contains(obj);
	}

	public int size() {
		return list.size();
	}

	public void clear() {
		list.clear();
	}

	public boolean isEmpty() {	
		return list.isEmpty();
	}
	
	public boolean isFull() {
		return list.isFull();
	}

	public Iterator<E> iterator() {
		return list.iterator();
	}
	
}