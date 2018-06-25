package cc.momas.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		int[] arr = new int[10];
		init(arr);

		System.err.println("原数组:");
		print(arr);

		System.out.println("堆排序:");
		print(heapSort(arr.clone()));

		System.out.println("归并排序");
		print(mergeSort(arr.clone(), 0, arr.length - 1));

		System.out.println("基数排序");
		print(radixSort(arr.clone(), 3));

		System.out.println("快速排序");
		print(quickSort(arr.clone(), 0, arr.length - 1));

		System.out.println("冒泡排序:");
		print(bubleSort(arr.clone()));

		System.out.println("拓扑排序:");
		new TopoSortB().demo();

		System.out.println("插入排序:");
		print(insertionSort(arr.clone()));

		System.out.println("希尔排序(优化的插入排序):");
		print(shellSort(arr.clone()));

		System.out.println("选择排序:");
		print(selectSort(arr.clone()));

		System.out.println("鸽巢排序:");
		print(pigeonholeSort(arr.clone()));

		System.err.println("原数组:");
		print(arr);

		System.out.println("系统排序");
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));

	}

	/**
	 * 希尔排序
	 *
	 * @param array
	 *            需要被排序的数组
	 * @return 排序完成的数组
	 */
	private static int[] shellSort(int[] array) {
		int d = array.length;
		while (true) {
			d = d / 2;
			for (int x = 0; x < d; x++) {
				for (int i = x + d; i < array.length; i = i + d) {
					int temp = array[i];
					int j;
					for (j = i - d; j >= 0 && array[j] > temp; j = j - d) {
						array[j + d] = array[j];
					}
					array[j + d] = temp;
				}
			}
			if (d == 1) {
				break;
			}
		}

		return array;
	}

	/**
	 * 插入排序法
	 *
	 * @param array
	 *            需要被排序的数组
	 * @return 排序完成的数组
	 */
	private static int[] insertionSort(int[] array) {
		for (int i = 0; i < array.length; i++) {// 遍历整个数组
			for (int j = i; j > 0; j--) {//
				if (array[j] < array[j - 1]) {
					swap(array, j - 1, j);
				}
			}
		}
		return array;
	}

	/**
	 * 鸽巢排序
	 *
	 * @param array
	 *            需要排序的数组
	 * @return 排序好的数组
	 */
	public static int[] pigeonholeSort(int[] array) {
		int max = array[0];
		for (int i = 0; i < array.length; i++) {
			if (max < array[i]) {
				max = array[i];
			}
		}

		int[] countArray = new int[max + 1];// 创建一个数组，统计重复数据个数(计数)
		for (int i = 0; i < array.length; i++) {
			countArray[array[i]]++;// 数组当下表，统计重复数据的个数，a1中保存元素重复次数(-1即可)，a1下标即对应的元素
		} // 此时不仅找到了元素的重复个数，同时完成了从小到大排序，下边要做的就是把数依次放到原数组中去了

		for (int i = 0, k = 0; i < max + 1; i++)// 注意是max+1
			for (int j = 0; j < countArray[i]; j++)
				array[k++] = i;
		return array;
	}

	/**
	 * 基数排序,又称 桶排序
	 *
	 * @param array
	 *            要排序的数组
	 * @param d
	 *            数组中元素有几位数就填几，1就
	 * @return 排序好的数组
	 */
	private static int[] radixSort(int[] array, int d) {
		int numberBit = 1;// 数组元素中的最大值
		for (int i = 0; i < d; i++) {
			numberBit *= 10;
		}
		int n = 1;// 代表位数对应的数:1,10,100 ...
		int k = 0;// 保存每一位排序后的结果用于下一位的排序输入
		int length = array.length;
		// 排序桶用于保存每次排序后的结果,这一位与一排序结果相同的数字放在同一个桶里
		int[][] bucket = new int[10][length];
		// 用于保存每个桶里有多少个数字
		int[] order = new int[length];

		while (n < numberBit) {
			for (int num : array) {
				int digit = (num / n) % 10;
				bucket[digit][order[digit]] = num;
				order[digit]++;
			}

			for (int i = 0; i < length; i++) {
				// 将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
				if (order[i] != 0) {
					// 这个桶里有数据,从上到下遍历这个桶并将数据保存到原数组中
					for (int j = 0; j < order[i]; j++) {
						array[k] = bucket[i][j];
						k++;
					}
				}
				order[i] = 0;// 将桶里计数器置0,用于下一次排序
			}
			n *= 10;
			k = 0;// 将k置0,用于下一轮保存位排序结果
		}
		return array;
	}

	/**
	 * 快速排序
	 *
	 * @param array
	 *            要排序的数组
	 * @param start
	 *            数组开始下标值
	 * @param end
	 *            数组结束下标值
	 * @return 排序好的数组
	 */
	private static int[] quickSort(int[] array, int start, int end) {
		if (start >= end) {// 递归停止的开关
			return null;
		}

		int temp = array[start];// temp是基准数,可以在数组范围内随便取,这里取数组第一位
		int i = start; // 数组的第一位的下标
		int j = end;// 数组最后一位的下标

		while (i != j) {
			// 要先从右边往左边找
			while (array[j] >= temp && i < j) {
				j--;
			}
			// 然后从左往右边找
			while (array[i] <= temp && i < j) {
				i++;
			}
			// 交换两个数在数组中的位置
			if (i < j) {
				swap(array, i, j);
			}
		}
		// 把基准数归位
		swap(array, start, i);

		quickSort(array, start, i - 1);// 继续处理左边的半边数组
		quickSort(array, i + 1, end); // 继续处理右边的半边数组
		return array;
	}

	/**
	 * 堆排序
	 *
	 * @param array
	 * @return
	 */
	private static int[] heapSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			createMaxHeap(array, array.length - 1 - i);
			swap(array, 0, array.length - 1 - i);
		}
		return array;
	}

	/**
	 * 创建最大值堆
	 *
	 * @param array
	 * @param lastIndex
	 */
	private static void createMaxHeap(int[] array, int lastIndex) {
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// 保存正在判断的节点
			int k = i;
			// 若当前节点的子节点存在
			while (2 * k + 1 <= lastIndex) {
				// biggerIndex总是记录较大节点的值,先赋值为当前判断节点的左子节点
				int biggerIndex = 2 * k + 1;
				if (biggerIndex < lastIndex) {
					// 若右子节点存在,否则此时biggerIndex应该等于 lastIndex
					if (array[biggerIndex] < array[biggerIndex + 1]) {
						biggerIndex++;
					}
				}
				if (array[k] < array[biggerIndex]) {
					// 若当前节点值比子节点最大值小,则交换两者的值,然后将biggerIndex赋值给k
					swap(array, k, biggerIndex);
					k = biggerIndex;
				} else {
					break;
				}
			}
		}

	}

	/**
	 * 直接选择排序法
	 *
	 * @param a
	 * @return
	 */
	private static int[] selectSort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			int minIndex = i;// 记录最小的索引
			for (int j = minIndex + 1; j < a.length; j++) {
				if (a[j] < a[minIndex]) {// 如果有比当前值小的值,则交换下标
					minIndex = j;
				}
			}
			if (minIndex != i) {// 如果上一次循环交换了下标,则交换数组元素值
				swap(a, i, minIndex);
			}
		}
		return a;
	}

	/**
	 * 归并排序
	 *
	 * @param a
	 * @param low
	 * @param high
	 * @return
	 */
	public static int[] mergeSort(int[] a, int low, int high) {
		int mid = (low + high) / 2;
		if (low < high) {
			// 左边
			mergeSort(a, low, mid);
			// 右边
			mergeSort(a, mid + 1, high);
			// 左右归并
			merge(a, low, mid, high);
			// System.out.println(Arrays.toString(a));
		}
		return a;
	}

	/**
	 * 归并
	 *
	 * @param a
	 * @param low
	 * @param mid
	 * @param high
	 */
	public static void merge(int[] a, int low, int mid, int high) {
		int[] tmp = new int[high - low + 1];
		int i = low;
		int j = mid + 1;
		int k = 0;
		// 把较小的数先移到新数组中
		while (i <= mid && j <= high) {
			if (a[i] < a[j]) {
				tmp[k++] = a[i++];
			} else {
				tmp[k++] = a[j++];
			}
		}
		// 把左边剩余的数移入数组
		while (i <= mid) {
			tmp[k++] = a[i++];
		}
		// 把右边剩余的数移入数组
		while (j <= high) {
			tmp[k++] = a[j++];
		}

		for (int k2 = 0; k2 < tmp.length; k2++) {
			a[k2 + low] = tmp[k2];
		}
	}

	/**
	 * 冒泡排序
	 *
	 * @param intArray
	 *            需要排序的数组
	 * @return 排序完成的数组
	 */
	private static int[] bubleSort(int[] intArray) {
		for (int i = 0; i < intArray.length - 1; i++) {
			for (int j = i + 1; j < intArray.length; j++) {
				if (intArray[i] > intArray[j]) {
					swap(intArray, i, j);
				}
			}
		}
		return intArray;
	}

	/**
	 * 输出一个数组
	 *
	 * @param arr
	 */
	public static void print(final int[] arr) {
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 初始化数组
	 *
	 * @param arr
	 * @return
	 */
	private static int[] init(int[] arr) {
		Random random = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(1000);
		}
		return arr;
	}

	/**
	 * 交换两个数字的值
	 *
	 * @param a
	 * @param b
	 */
	private static void swap(int[] arr, int a, Integer b) {
		if (arr[a] == arr[b]) {
			return;
		}
		arr[a] += arr[b];
		arr[b] = arr[a] - arr[b];
		arr[a] = arr[a] - arr[b];
	}

}

/**
 * 拓扑排序，当前方案并没有在节点类中加入过多的内容 但是在图类中加入了边的集合adjaNode
 */
class TopoSortB {
	/**
	 * 启动一个小demo
	 */
	public void demo() {
		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		Node F = new Node("F");

		Graph graph = new Graph();
		graph.addNode(A, B);
		graph.addNode(B, C);
		graph.addNode(B, D);
		graph.addNode(D, C);
		graph.addNode(E, C);
		graph.addNode(C, F);

		KahnTopo topo = new KahnTopo(graph);
		topo.process();
		for (Node temp : topo.getResult()) {
			System.out.print(temp.val.toString() + "-->");
		}
		System.out.println();
	}

	/**
	 * 拓扑排序节点类
	 */
	private static class Node {
		public Object val;
		public int pathIn = 0; // 入链路数量

		public Node(Object val) {
			this.val = val;
		}
	}

	/**
	 * 拓扑图类
	 */
	private static class Graph {
		// 图中节点的集合
		public Set<Node> vertexSet = new HashSet<Node>();
		// 相邻的节点，纪录边
		public Map<Node, Set<Node>> adjaNode = new HashMap<Node, Set<Node>>();

		// 将节点加入图中
		public boolean addNode(Node start, Node end) {
			if (!vertexSet.contains(start)) {
				vertexSet.add(start);
			}
			if (!vertexSet.contains(end)) {
				vertexSet.add(end);
			}
			if (adjaNode.containsKey(start) && adjaNode.get(start).contains(end)) {
				return false;
			}
			if (adjaNode.containsKey(start)) {
				adjaNode.get(start).add(end);
			} else {
				Set<Node> temp = new HashSet<Node>();
				temp.add(end);
				adjaNode.put(start, temp);
			}
			end.pathIn++;
			return true;
		}
	}

	// Kahn算法
	private static class KahnTopo {
		private List<Node> result; // 用来存储结果集
		private Queue<Node> setOfZeroIndegree; // 用来存储入度为0的顶点
		private Graph graph;

		// 构造函数，初始化
		public KahnTopo(Graph di) {
			this.graph = di;
			this.result = new ArrayList<Node>();
			this.setOfZeroIndegree = new LinkedList<Node>();
			// 对入度为0的集合进行初始化
			for (Node iterator : this.graph.vertexSet) {
				if (iterator.pathIn == 0) {
					this.setOfZeroIndegree.add(iterator);
				}
			}
		}

		// 拓扑排序处理过程
		private void process() {
			while (!setOfZeroIndegree.isEmpty()) {
				Node v = setOfZeroIndegree.poll();

				// 将当前顶点添加到结果集中
				result.add(v);

				if (this.graph.adjaNode.keySet().isEmpty()) {
					return;
				}

				// 遍历由v引出的所有边
				for (Node w : this.graph.adjaNode.get(v)) {
					// 将该边从图中移除，通过减少边的数量来表示
					w.pathIn--;
					if (0 == w.pathIn) // 如果入度为0，那么加入入度为0的集合
					{
						setOfZeroIndegree.add(w);
					}
				}
				this.graph.vertexSet.remove(v);
				this.graph.adjaNode.remove(v);
			}

			// 如果此时图中还存在边，那么说明图中含有环路
			if (!this.graph.vertexSet.isEmpty()) {
				throw new IllegalArgumentException("Has Cycle !");
			}
		}

		// 结果集
		public Iterable<Node> getResult() {
			return result;
		}
	}
}
