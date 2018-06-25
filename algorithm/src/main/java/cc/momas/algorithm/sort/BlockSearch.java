
//=============================================================

package cc.momas.algorithm.sort;

import java.util.ArrayList;

/***
 * 分块查找的实现
 *
 * @author wjgs
 *
 */
public class BlockSearch {

	private int[] index; // 建立索引
	private ArrayList[] list;

	/**
	 * 初始化索引表
	 *
	 * @param index
	 */
	public BlockSearch(int[] index) {
		if (index != null && index.length != 0) {
			this.index = index;
			this.list = new ArrayList[index.length];

			for (int i = 0; i < list.length; i++) {
				list[i] = new ArrayList();// 初始化容器
			}
		} else {
			throw new Error("index cannot be null or empty");
		}
	}

	/**
	 * 插入元素
	 *
	 * @param value
	 */
	public void insert(int value) {

		int i = binarysearch(value);
		list[i].add(value);
	}

	/**
	 * 二分查找
	 *
	 * @param value
	 * @return
	 */
	private int binarysearch(int value) {
		int start = 0;
		int end = index.length;
		int mid = -1;
		while (start <= end) {
			mid = (start + end) / 2;
			if (index[mid] > value) {
				end = mid - 1;
			} else {
				// 如果相等，也插入后面
				start = mid + 1;
			}
		}

		return start;
	}

	/**
	 * 查找元素
	 *
	 * @param data
	 * @return
	 */
	public boolean search(int data) {
		int i = binarysearch(data);
		for (int j = 0; j < list[i].size(); j++) {
			if (data == (int) list[i].get(j)) {
				System.out.println(String.format("查找元素为第: %d块  第%d个 元素", i + 1, j + 1));
				return true;
			}
		}
		return false;
	}

	/**
	 * 打印每块元素
	 *
	 */
	public void printAll() {
		for (int i = 0; i < list.length; i++) {
			ArrayList l = list[i];
			System.out.print("ArrayList" + i + ":");

			for (int j = 0; j < l.size(); j++) {
				System.out.print(l.get(j) + "  ");
			}
		}
	}

	public static void main(String[] args) {
		int[] index = { 10, 20, 30 };
		BlockSearch blocksearch = new BlockSearch(index);
		blocksearch.insert(1);
		blocksearch.insert(12);
		blocksearch.insert(22);

		blocksearch.insert(9);
		blocksearch.insert(18);
		blocksearch.insert(23);

		blocksearch.insert(5);
		blocksearch.insert(15);
		blocksearch.insert(27);

		blocksearch.printAll();

		System.out.println(blocksearch.search(18));
		System.out.println(blocksearch.search(29));

	}

}