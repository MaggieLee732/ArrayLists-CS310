package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class UnorderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
	private E[] array;
	private int currentSize;
	private int maxSize;
	protected long modificationCounter;
	
	public UnorderedArrayPriorityQueue(int max) {
		currentSize = 0;
		maxSize = max;
		array = (E[]) new Comparable[maxSize];
	}
	
	public boolean insert(E object) {
		if(currentSize < maxSize){
			currentSize++;
			array[currentSize - 1] = object;
			modificationCounter++;
			return true;
		}
		return false;
	}

	public E remove() {
		if(isEmpty())
			return null;
		E highestPri = array[0];
		int index = 0;
		for(int i = 1; i < currentSize; i++){
			if(highestPri.compareTo(array[i]) > 0){
				highestPri = array[i];
				index = i;
			}
		}
		for(int i = index; i < currentSize - 1; i++)
			array[i] = array[i+1];
		currentSize--;
		modificationCounter++;
		return highestPri;
	}

	public E peek() {
		if(isEmpty())
			return null;
		E highestPri = array[0];
		for(int i = 1; i < currentSize; i++){
			if(highestPri.compareTo(array[i]) > 0){
				highestPri = array[i];
			}
		}
		return highestPri;
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
		currentSize = 0;
		modificationCounter++;
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

