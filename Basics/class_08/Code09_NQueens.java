package Basics.class_08;

public class Code09_NQueens {

	public static int num1(int n) {
		if (n < 1) {
			return 0;
		}
		// recode[i] -> i行的皇后，放在了第几列
		int[] record = new int[n];
		return process1(0, record, n);
	}

	// recode[0..i-1]的皇后，一定不共行不共列不共斜线
	// 目前来到了第i行
	// record[0..i-1]表示之前的行，放了的皇后位置
	// n表示整体一共有多少行
	// 返回值是：摆完所有的皇后，合法的摆法有多少种
	public static int process1(int i, int[] record, int n) {
		// 来到终止行
		if (i == n) {
			return 1;
		}
		int res = 0;
		// 当前行在i行，尝试i行所有的列 -> j
		for (int j = 0; j < n; j++) {
			// 当前i行的皇后，放在j列，会不会和之前（0..i-1）的皇后共行共列或者共斜线
			// 如果是，认为无效
			// 如果不是，认为有效
			if (isValid(record, i, j)) {
				record[i] = j;
				res += process1(i + 1, record, n);
			}
		}
		return res;
	}

	public static boolean isValid(int[] record, int i, int j) {
		for (int k = 0; k < i; k++) {
			if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
				return false;
			}
		}
		return true;
	}

	// 请不要超过32皇后问题
	public static int num2(int n) {
		if (n < 1 || n > 32) {
			return 0;
		}
		int upperLim = n == 32 ? -1 : (1 << n) - 1;
		return process2(upperLim, 0, 0, 0);
	}

	// colLim 列的限制，1的位置不能放皇后，0的位置可以
	// leftDiaLim 左斜线的限制，1的位置不能放皇后，0的位置可以
	// rightDiaLim 右斜线的限制，1的位置不能放皇后，0的位置可以
	public static int process2(int upperLim, int colLim, int leftDiaLim,
			int rightDiaLim) {
		if (colLim == upperLim) {
			return 1;
		}
		int pos = 0;
		int mostRightOne = 0;
		// 所有候选皇后的位置，都在pos上
		pos = upperLim & (~(colLim | leftDiaLim | rightDiaLim));
		int res = 0;
		while (pos != 0) {
			mostRightOne = pos & (~pos + 1);
			pos = pos - mostRightOne;
			res += process2(upperLim, colLim | mostRightOne,
					(leftDiaLim | mostRightOne) << 1,
					(rightDiaLim | mostRightOne) >>> 1);
		}
		return res;
	}

	public static void main(String[] args) {
		int n = 14;

		long start = System.currentTimeMillis();
		System.out.println(num2(n));
		long end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");

		start = System.currentTimeMillis();
		System.out.println(num1(n));
		end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");

	}
}
