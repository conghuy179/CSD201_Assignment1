import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Driver driver = new Driver(sc);
        driver.run();
    }
}