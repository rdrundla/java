package dfs;

import java.io.FileInputStream;
import java.util.Scanner;

public class DFS {
	
	static class Node {
		
		int data;
		Node next;
		public Node () {
			data = -1;
			next = null;
		}
	}
	
	static class LinkList {
		
		Node head;
		LinkList() {
			head = new Node();
		}
		
		public Node addNode(int d) {
			if (head.data == -1) {
				head.data = d;
			} else {
				Node newNode = new Node();
				newNode.data = d;
				newNode.next = head;
				head = newNode;
			}
			return head;
		}
	}
	
	static class Graph {
		
		int v;
		LinkList adjLinkList[];
		
		public Graph(int v) {
			this.v = v;
			adjLinkList = new LinkList[v];
			for (int i = 0; i < v; i++) {
				adjLinkList[i] = new LinkList();
			}
		}
		
		public void addEdge(int v, int w) {
			adjLinkList[v].addNode(w);
		}

		public void dfsTraversal(Node node) {
			
			boolean visited[] = new boolean[v];
			dfsUtil(visited, node);
		}

		private void dfsUtil(boolean[] visited, Node node) {
			
			//traversed current node and mark visited
			visited[node.data] = true;
			System.out.print(node.data + " ");
			
			//Traverse all adjacents
			Node adjacents = adjLinkList[node.data].head;
			if (!visited[adjacents.data]) {
				dfsUtil(visited, adjacents);
			}
			while (adjacents.next != null) {
				
				Node n = adjacents.next;
				if (!visited[n.data]) {
					dfsUtil(visited, n);
				}
				//traverse next adjacent
				adjacents = adjacents.next;
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("src/dfs/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int v = sc.nextInt();
		Graph g = new Graph(v);
		int e = sc.nextInt();
		for (int i = 0; i < e; i++) {
			g.addEdge(sc.nextInt(), sc.nextInt());
		}
		Node startNode = new Node();
		startNode.data = 2;
		g.dfsTraversal(startNode);
		
		System.out.println();
		sc.close();
	}
}
