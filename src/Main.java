import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.leseTSV(System.getProperty("user.dir") + "\\src\\fallakten.tsv");
    }
}