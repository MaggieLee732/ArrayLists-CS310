/* Margaret Lee
 * cssc0933
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

import data_structures.OrderedList.Node;

import java.util.ConcurrentModificationException;

public class UnorderedList<E extends Comparable<E>> implements Iterable<E> {
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
	
	public UnorderedList(){
		head = tail = null;
		currentSize = 0;
	}

	public boolean insertLast(E obj) {
		Node<E> newNode = new Node<E>(obj);
		if(isEmpty())
			head = tail = newNode;
		else{
			tail.next = newNode;
			tail = newNode;
		}
		currentSize++;
		modificationCounter++;
		return true;
	}
	
	public boolean insertFirst(E obj){
		Node<E> newNode = new Node<E>(obj);
		if(isEmpty())
			head = tail = newNode;
		else{
			newNode.next = head;
			head = newNode; 
		}
		currentSize++;
		modificationCounter++;
		return true;
	}

	public E removeMin() {
		if(isEmpty())
			return null;
		Node<E> current = head , previous = null, tmp = null, best = head;
		while(current != null){
			if(current.data.compareTo(best.data) < 0){
				best = current;
				tmp = previous;
			}
			previous = current;
			current = current.next;
		}
		if(best == head){
			head = head.next;
			if(head == null)
				tail = null;
		}
		else if(best == tail){
			tail = tmp;
			tail.next = null;
		}
		else
			tmp.next = best.next;
			
		currentSize--;
		modificationCounter++;
		return best.data;
	}

	public E findMin() {
		if(isEmpty())
			return null;
		E best = head.data;
		Node<E> current = head.next;
		while(current != null){
			if(current.data.compareTo(best) < 0)
				best = current.data;
			current = current.next;	
		}
		return best;	
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
		return currentSize;
	}

	public void clear() {
		head = tail = null;
		currentSize = 0;
		modificationCounter++;
	}

	public boolean isEmpty() {
		if(head == null)
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
