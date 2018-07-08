package bfs_traversal;

import java.io.FileInputStream;
import java.util.Scanner;

public class BfsTraversal {
	
	static class Node {
		
		int data;
		Node next;
		
		public Node(int d) {
			data = d;
			next = null;
		}
	}
	
	static class LinkList {
		
		Node head;
		
		public LinkList() {
			head = null;
		}
		
		Node addNode(int d) {
			if (head == null) {
				head = new Node(d);
			} else {
				Node n = new Node(d);
				n.next = head;
				head = n;
			}
			
			return head;
		}
	}
	
	static class Queue {
		
		Node top;
		
		public Queue() {
			top = null;
		}
		
		void push(Node node) {
			
			Node n = new Node(node.data);
			if (top == null) {
				top = n;
			} else {
				n.next = top;
				top = n;
			}
		}
		
		Node pop() {
			Node n = top;
			if (top.next != null) {
				top = top.next;
			} else  {
				top = null;
			}
			return n;
		}
		
		boolean isEmpty() {
			if (top == null) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	static class Graph {
		
		int v;
		LinkList adjList[];
		
		public Graph(int v) {
			this.v = v;
			adjList = new LinkList[v];
			for (int i = 0; i < v; i++) {
				adjList[i] = new LinkList();
			}
		}
		
		void addEdge(int v, int w) {
			adjList[v].addNode(w);
		}

		public void bfsTraversal(Node startNode) {
			
			boolean visited[] = new boolean[v];
			Queue queue = new Queue();

			visited[startNode.data] = true;
			queue.push(startNode);

			while (!queue.isEmpty()) {
				
				Node n = queue.pop();
				System.out.print(n.data + " ");

				Node tempHead = adjList[n.data].head;
				while (tempHead != null) {

					if (!visited[tempHead.data]) {
						visited[tempHead.data] = true;
						queue.push(tempHead);
					}

					tempHead = tempHead.next;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("src/bfs_traversal/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int v = sc.nextInt();
		Graph g = new Graph(v);
		int e = sc.nextInt();
		for (int i = 0; i < e; i++) {
			g.addEdge(sc.nextInt(), sc.nextInt());
		}
		Node startNode = new Node(2);
		g.bfsTraversal(startNode);
		
		System.out.println();
		sc.close();
	}

}
