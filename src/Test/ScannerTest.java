package Test;

import java.io.File;
import java.util.Scanner;

/**
 * @author focusxyhoo
 * @date 2019-05-14 10:26
 */
public class ScannerTest {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter a directory: ");
            String directory = scanner.nextLine();
            System.out.println("Now open directory: " + directory);
            File myDir = new File(directory);
            File[] files = myDir.listFiles();
            for (File file : files) {
                System.out.println(file.getName());
            }
        }
    }

    private static void findFiles(File directory) {

    }

}
