package data_structures;

import data_structures.*;

public class Driver2 {
	public Driver2(){
		PriorityQueue<Integer> list = new OrderedListPriorityQueue<Integer>();

		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.insert(4);
		list.insert(5);
		list.insert(6);
		list.insert(7);
		list.insert(8);
		list.insert(9);
		list.insert(10);
		list.insert(0);
		//list.remove();
		/*
		list.insert("a");
		list.insert("c");
		list.insert("e");
		list.insert("b");
		list.insert("d");
		list.insert("a");
		*/
		//list.clear();
		//System.out.println(list.contains(11));
		//System.out.println(list.remove());
		//System.out.println(list.size());
		//System.out.println(list.isEmpty());
		//System.out.println(list.remove());
		System.out.println(list.size());
		//System.out.println(list.peek());
		System.out.println("");
		for(Integer E : list)
			System.out.println(E);
		
	}
		public static void main(String[] args) {
			new Driver2();
			
			
		}
}

