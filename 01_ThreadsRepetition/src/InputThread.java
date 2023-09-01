import java.util.Scanner;

public class InputThread extends Thread {
    private final Word word;

    public InputThread(Word word) {
        this.word = word;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            word.setWord(input);
        }
    }
}
