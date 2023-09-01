public class OutputThread extends Thread {
    private final Word word;

    public OutputThread(Word word) {
        this.word = word;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(3000);
                System.out.println(word.getWord());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
