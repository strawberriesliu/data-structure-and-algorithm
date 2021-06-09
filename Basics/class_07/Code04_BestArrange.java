package Basics.class_07;

import java.util.Arrays;
import java.util.Comparator;

import static sun.misc.Version.print;

public class Code04_BestArrange {

	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}

	}

	public static int bestArrange(Program[] programs, int start) {
		Arrays.sort(programs, new ProgramComparator());
		int result = 0;
		for (int i = 0; i < programs.length; i++) {
			if(start <= programs[i].start){
				result++;
				start = programs[i].end;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Program[] programs = new Program[6];
		programs[0] = new Program(0, 3);
		programs[1] = new Program(2, 4);
		programs[2] = new Program(4, 9);
		programs[3] = new Program(5, 7);
		programs[4] = new Program(6, 10);
		programs[5] = new Program(10, 12);
		int count = bestArrange(programs, 0);
		System.out.println("最多能安排" + count + "个项目");
	}

}
