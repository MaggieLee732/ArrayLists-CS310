/* Margaret Lee
 * cssc0933
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.ConcurrentModificationException;

public class OrderedList<E extends Comparable<E>> implements Iterable<E> {
	public class Node<T>{
		T data;
		Node<T> next;
	
	public Node (T data){
		this.data = data;
		next = null;
		}
	}
	
	private Node <E> head, tail;
	private int currentSize;
	private long modificationCounter;
	
	public OrderedList(){
		head = null;
		currentSize = 0;
	}
	
	public boolean insert(E obj) {
		Node<E> newNode = new Node<E>(obj);
		Node<E> previous = null, current = head;
		while(current != null && obj.compareTo(current.data) >= 0){
			previous = current;
			current = current.next;
		}
		if(previous == null){
			newNode.next = head;
			head = newNode;
		}
		else if(current == null){
			previous.next = newNode;
			newNode.next = current;
		}
		else{
			previous.next = newNode;
			newNode.next = current;
		}
		currentSize++;
		return true;
	}

	public E removeMin() {
		if(isEmpty())
			return null;
		Node<E> current = head;
		head = current.next;
		currentSize--;
		return current.data;
	}

	public E findMin() {
		if(head == null)
			return null;
		return head.data;	
	}

	public boolean contains(E obj) {
		Node<E> tmp = head;
		while(tmp != null){
			if(((Comparable<E>)obj).compareTo(tmp.data) == 0)
				return true;
			tmp = tmp.next;
		}
		return false;
	}

	public int size() {
		int size = currentSize;
		return size;
	}

	public void clear() {
		head = null;
		currentSize = 0;
	}

	public boolean isEmpty() {
		if(currentSize==0)
			return true;
		return false;
	}

	public boolean isFull() {
		return false;
	}

	public Iterator<E> iterator() {
		return new IteratorHelper();
	}
	private class IteratorHelper implements Iterator<E>{
		long stateCheck;
		private Node<E> tmpNode;
		
		public IteratorHelper(){
			stateCheck = modificationCounter;
			tmpNode = head;
		}
		
		public boolean hasNext(){
			if(stateCheck != modificationCounter)
				throw new ConcurrentModificationException();
			return tmpNode != null;
		}

		public E next() {
		if (!hasNext())
			throw new NoSuchElementException();
		E data = tmpNode.data;
		tmpNode = tmpNode.next;
		return data;
		}

		public void remove() {
		throw new UnsupportedOperationException();
		}
	}
}
