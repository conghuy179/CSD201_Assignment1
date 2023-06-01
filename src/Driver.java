import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class Driver {
    public static final int SELECTION_INPUT = 1;
    public static final int SELECTION_OUTPUT = 2;
    public static final int SELECTION_BUBBLE = 3;
    public static final int SELECTION_SELECTION = 4;
    public static final int SELECTION_INSERTION = 5;
    public static final int SELECTION_LINEAR = 6;
    public static final int SELECTION_BINARY = 7;
    public static final int SELECTION_EXIT = 0;
    private Scanner sc;
    private int selection;

    public Driver(Scanner sc) {
        this.sc = sc;
    }

    public void printMenu() {
        System.out.println("+-------------------Menu------------------+");
        System.out.println("|      1.Input                            |");
        System.out.println("|      2.Output                           |");
        System.out.println("|      3.Bubble Sort                      |");
        System.out.println("|      4.Selection Sort                   |");
        System.out.println("|      5.Insertion Sort                   |");
        System.out.println("|      6.Search > Value                   |");
        System.out.println("|      7.Search = Value                   |");
        System.out.println("|      0.Exit                             |");
        System.out.println("Choice: ");
    }

    public void run() throws IOException {
        String selectionS;
        do {
            printMenu();
            try {
                selectionS = sc.next();
                isSelectionValid(selectionS);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            selection = Integer.parseInt(selectionS);
            if (selection == SELECTION_INPUT) {
                runInputSelection();
            } else if (selection == SELECTION_OUTPUT) {
                runOutputSelection();
            } else if (selection == SELECTION_BUBBLE) {
                runBubbleSortSelection();
            } else if (selection == SELECTION_INSERTION) {
                runInsertionSortSelection();
            } else if (selection == SELECTION_LINEAR) {
                runLinearSelection();
            } else if (selection == SELECTION_BINARY) {
                runBinarySelection();
            }
        } while (selection != SELECTION_EXIT);
    }

    /**
     * Nhap du lieu tu ban phim
     * Cho phep nhap do dai mang, nhap tung gia tri cua mang, luu du lieu
     */
    private void runInputSelection() throws IOException {
        int arrLength = 0;
        String arrLengthS;
        do {
            System.out.println("Input number of elements: ");
            try {
                arrLengthS = sc.next();
                isArrLengthValid(arrLengthS);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            arrLength = Integer.parseInt(arrLengthS);
            isArrLengthValid(arrLength);
            if (isArrLengthValid(arrLength)) {
                int[] arr = new int[arrLength];
                BufferedWriter writer = new BufferedWriter(new FileWriter("INPUT.TXT"));
                for (int i = 0; i < arrLength; i++) {
                    System.out.println("Nhap gia tri thu " + (i + 1) + " trong mang: ");
                    arr[i] = sc.nextInt();
                    writer.write(Integer.toString(arr[i]) + " ");
                }
                System.out.println("Arr = " + Arrays.toString(arr));
                writer.flush();
            } else {
                System.out.println("Chi nhap 1 so duong tu 1 den 20. Yeu cau nhap lai.");
            }
        } while (!isArrLengthValid(arrLength));
    }

    private boolean isArrLengthValid(int arrLength) {
        if (arrLength > 20 || arrLength < 1) {
            return false;
        }
        return true;
    }

    private boolean isArrLengthValid(String arrLengthS) {
        if (arrLengthS.charAt(0) < '0' || arrLengthS.charAt(0) > '9') {
            throw new IllegalArgumentException("Chi nhap 1 so duong < 20.");
        } else {
            return true;
        }
    }

    /**
     * in ra danh sach du lieu duoc doc tu file
     */
    private void runOutputSelection() {
        try {
            File input = new File("INPUT.TXT");
            Scanner reader = new Scanner(input);
            while (reader.hasNextLine()) {
                String a = reader.nextLine();
                System.out.println(a);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void runBinarySelection() {
    }

    private void runLinearSelection() {

    }

    private void runInsertionSortSelection() {

    }

    private void runBubbleSortSelection() {

    }


    private boolean isSelectionValid(String selectionS) {
        if (selectionS.length() > 1) {
            throw new IllegalArgumentException("Chi nhap 1 so tu 0 den 7.");
        } else if (selectionS.charAt(0) < '0' || selectionS.charAt(0) > '7') {
            throw new IllegalArgumentException("CHi nhap 1 so trong khoang 0 den 7.");
        } else {
            return true;
        }
    }
}
