import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        App app = new App();
        Scanner scanner = new Scanner(System.in);
        List<Entry> entries = app.leseTSV(System.getProperty("user.dir") + "\\src\\fallakten.tsv");
        char g = scanner.next().charAt(0);
        app.grossbuchstaben(g,entries);
    }
}