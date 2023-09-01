package opg8.fletning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestFlettesortering {
	// Køretid før: 1817, 1685, 1784
	// Køretid efter: 1196, 1145, 1245
	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		List<Integer> list = new ArrayList<Integer>();
		Random r=new Random();
		for (int i=0;i<1_000_000;i++) {
			list.add(Math.abs(r.nextInt()%10000));
		};
		
//		System.out.println(list);
		MergeThread	t1 = new MergeThread(list, 0, list.size() / 2);
		MergeThread	t2 = new MergeThread(list, (list.size() / 2) + 1, list.size() - 1);
		FletteSortering sort = new FletteSortering();
		long l1,l2;
		l1 = System.nanoTime();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		sort.merge(list, 0, list.size() / 2, list.size() - 1);
		l2 = System.nanoTime();
//		System.out.println();
		System.out.println("K�retiden var " + (l2-l1)/1000000);
//		System.out.println();
//		System.out.println(list);
	}

}
