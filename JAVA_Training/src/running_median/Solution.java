package running_median;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	interface Heap {

		void insert(int val);
		int extractTop();
		int getTop();
		int parent(int index);
		int leftChild(int index);
		int rightChild(int index);
		void swap(int i, int j);
		
	}
	
	static class MinHeap implements Heap {
		
		int maxSize;
		int heapSize;
		int heap[];
		
		public MinHeap(int size) {
			maxSize = size;
			heapSize = 0;
			heap = new int[size];
		}

		@Override
		public void insert(int val) {
			
			if(heapSize == 0) {
				heap[0] = val;
				heapSize++;
			} else {
				heap[heapSize] = val;
				heapSize++;
				int i = heapSize-1;
				while(i > 0 && heap[parent(i)]  > heap[i]) {
					swap(i, parent(i));
					i = parent(i);
				}
			}
		}

		@Override
		public int extractTop() {
			
			int top = heap[0];
			
			heap[0] = heap[heapSize-1];
			heapSize--;
			int current = 0;
			while (current < heapSize && leftChild(current) < heapSize) {
				int child;
				if (rightChild(current) > heapSize) {
					child = leftChild(current);
				} else  {
					child = heap[leftChild(current)]  < heap[rightChild(current)] ? leftChild(current) : rightChild(current);
				}
				
				if (heap[current] < heap[child]) {
					break;
				}
				
				swap(current, child);
				current = child;
			}
			return top;
		}
		
		@Override
		public void swap(int i, int j) {
			
			int temp = heap[i];
			heap[i] = heap[j];
			heap[j] = temp;
		}

		@Override
		public int getTop() {
			return heap[0];
		}

		@Override
		public int parent(int index) {
			return (index - 1) / 2;
		}

		@Override
		public int leftChild(int index) {
			return index * 2 + 1;
		}

		@Override
		public int rightChild(int index) {
			return index*2 +2;
		}
		
	}
	
	static class MaxHeap implements Heap {
		
		int maxSize;
		int heapSize;
		int heap[];
		
		public MaxHeap(int size) {
			maxSize = size;
			heapSize = 0;
			heap = new int[size];
		}

		@Override
		public void insert(int val) {
			
			if(heapSize == 0) {
				heap[0] = val;
				heapSize++;
			} else {
				heap[heapSize] = val;
				heapSize++;
				int i = heapSize-1;
				while(i > 0 && heap[parent(i)]  < heap[i]) {
					swap(i, parent(i));
					i = parent(i);
				}
			}
		}

		@Override
		public int extractTop() {
			int top = heap[0];
			
			heap[0] = heap[heapSize-1];
			heapSize--;
			int current = 0;
			while (current < heapSize && leftChild(current) < heapSize) {
				int child;
				if (rightChild(current) > heapSize) {
					child = leftChild(current);
				} else  {
					child = heap[leftChild(current)]  > heap[rightChild(current)] ? leftChild(current) : rightChild(current);
				}
				
				if (heap[current] > heap[child]) {
					break;
				}
				
				swap(current, child);
				current = child;
			}
			return top;
		}
		
		@Override
		public void swap(int i, int j) {
			
			int temp = heap[i];
			heap[i] = heap[j];
			heap[j] = temp;
		}

		@Override
		public int getTop() {
			return heap[0];
		}

		@Override
		public int parent(int index) {
			return (index - 1) / 2;
		}

		@Override
		public int leftChild(int index) {
			return index * 2 + 1;
		}

		@Override
		public int rightChild(int index) {
			return index*2 +2;
		}
	}
	
	static MinHeap r;
	static MaxHeap l;
	static final int SMALLER = -1;
	static final int EQUAL = 0;
	static final int BIGGER = 1;
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("src/running_median/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		l = new MaxHeap(n);
		
		r = new MinHeap(n);
		
		float median = 0;
		for (int i = 0; i < n; i++) {
			
			int val = sc.nextInt();
			median = findMadian(median, val);
			
			System.out.println(median);
		}
		sc.close();
	}
	
	private static float findMadian(float median, int val) {
		
		int equility = findEquility();
		
		switch (equility) {
		case SMALLER :
			if (val < median) {
				l.insert(val);
			} else  {
				l.insert(r.extractTop());
				r.insert(val);
			}
			median = (float)(l.getTop() + r.getTop()) / 2;
			break;
		
		case BIGGER :
			if (val < median) {
				r.insert(l.extractTop());
				l.insert(val);
			} else {
				r.insert(val);
			}
			median = (float)(l.getTop() + r.getTop()) / 2;
			break;
		
		case EQUAL:
			if (val < median) {
				l.insert(val);
				median = l.getTop();
			} else {
				r.insert(val);
				median = r.getTop();
			}
		}

		return median;
	}

	private static int findEquility() {
		
		if (l.heapSize > r.heapSize) {
			return BIGGER;
		} else if (l.heapSize < r.heapSize) {
			return SMALLER;
		} else {
			return EQUAL;
		}
	}

}
