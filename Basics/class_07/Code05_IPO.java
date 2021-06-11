package Basics.class_07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code05_IPO {
	// 每个项目由花费和收益两部分组成
	public static class Node {
		public int p;
		public int c;

		public Node(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	// 定义项目最小花费的比较器
	public static class MinCostComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.c - o2.c;
		}

	}

	// 定义项目最大收益的比较器
	public static class MaxProfitComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o2.p - o1.p;
		}

	}

	// 主方法
	public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Costs) {
		// 根据花费和收益数组创建对应项目数组
		ArrayList<Node> nodes = new ArrayList<>();
		for (int i = 0; i < Profits.length; i++) {
			nodes.add(new Node(Profits[i], Costs[i]));
		}
		// 创建小根堆（花费最小）和大根堆（收益最大）
		PriorityQueue<Node> minCostsHeap = new PriorityQueue<>(new MinCostComparator());
		PriorityQueue<Node> maxProfitsHeap = new PriorityQueue<>(new MaxProfitComparator());
		// 将所有项目放入小根堆
		minCostsHeap.addAll(nodes);
		for (int i = 0; i < k; i++) {
			// 如果小根堆不为空并且其元素小于等于初始资金，将其解锁弹出放入大根堆中。
			while(!minCostsHeap.isEmpty() && minCostsHeap.peek().c <= W){
				maxProfitsHeap.add(minCostsHeap.poll());
			}
			// 没有可以做的项目，返回W
			if(maxProfitsHeap.isEmpty()){
				return W;
			}
			// 将选择的项目收益加入初始资金中，更新初始资金
			W += maxProfitsHeap.poll().p;
		}
		return W;
	}

	public static void main(String[] args) {
		int[] Profits = new int[] {5, 6, 3, 9, 10, 4, 1, 3};
		int[] Costs = new int[] {2, 5, 2, 6, 8, 3, 1, 4};
		int result = findMaximizedCapital(4, 2, Profits, Costs);
		System.out.println("完成项目最终获得的最大资金为：" + result);
	}

}
