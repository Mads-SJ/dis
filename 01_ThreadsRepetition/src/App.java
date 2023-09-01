public class App {
    public static void main(String[] args) {
        Word word = new Word();
        InputThread inputThread = new InputThread(word);
        OutputThread outputThread = new OutputThread(word);

        inputThread.start();
        outputThread.start();
    }
}