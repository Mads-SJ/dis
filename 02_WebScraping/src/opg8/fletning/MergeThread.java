package opg8.fletning;

import java.util.List;

public class MergeThread extends Thread {
    private final List<Integer> list;
    private int start;
    private int end;

    public MergeThread(List<Integer> list, int start, int end) {
        this.list = list;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        FletteSortering sort = new FletteSortering();
        sort.mergesort(list, start, end);
    }
}
