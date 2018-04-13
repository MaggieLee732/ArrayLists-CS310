/* Margaret Lee
 * cssc0933
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class OrderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
	protected E[] array;
	protected int currentSize;
	protected int maxSize;
	protected long modificationCounter;
	
	public OrderedArrayPriorityQueue(int max){
		currentSize = 0;
		maxSize = max;
		array = (E[]) new Comparable[maxSize];
	}
	public boolean insert(E object) {
		if(isFull())
			return false;
		else{
			int index = binSearch(object, 0, currentSize - 1); 
			currentSize++;
			for(int i = currentSize - 1; i > index; i--)
				array[i] = array[i-1];
			array[index] = object;
			modificationCounter++;
			return true;
		}
	}

	public E remove() {	
		if(isEmpty())
			return null;
		E highestPri = array[currentSize- 1];
		currentSize--;
		modificationCounter++;
		return highestPri;
	}

	public E peek() {
		if(isEmpty())
			return null;
		return array[currentSize-1];
	}

	public boolean contains(E obj) {
		for (int i = 0; i < currentSize; i++) {
			if(obj.compareTo(array[i]) == 0)
				return true;
		}
		return false;
	}

	public int size() {
		return currentSize;
	}

	public void clear() {
		modificationCounter++;
		currentSize = 0;	
	}

	public boolean isEmpty() {
		if(currentSize == 0)
			return true;
		return false;
	}

	public boolean isFull() {
		if(currentSize == maxSize)
			return true;
		return false;
	}
	
	private int binSearch(E object, int lo, int hi){
		if(hi < lo) return lo;
		int mid = (lo + hi) >> 1; 
		int C = (object.compareTo(array[mid]));
		if(C >= 0) 	
			return binSearch(object, lo, mid - 1);
		return binSearch(object, mid + 1, hi);
	}

	public Iterator<E> iterator() {
		return new IteratorHelper();
	}
	private class IteratorHelper implements Iterator<E>{
		int iterIndex;
		long stateCheck;
		
		public IteratorHelper(){
			iterIndex = 0;
			stateCheck = modificationCounter;
		}
		public boolean hasNext() {
			if(stateCheck != modificationCounter)
				throw new ConcurrentModificationException();
			return iterIndex < currentSize;
		}

		public E next() {
		if (!hasNext())
			throw new NoSuchElementException();
		return array[iterIndex++];
		}

		public void remove() {
		throw new UnsupportedOperationException();
		
		}

	}
}
