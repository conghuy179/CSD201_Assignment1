import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Driver {
    public static final int SELECTION_INPUT = 1;
    public static final int SELECTION_OUTPUT = 2;
    public static final int SELECTION_BUBBLE = 3;
    public static final int SELECTION_SELECTION_SORT = 4;
    public static final int SELECTION_INSERTION = 5;
    public static final int SELECTION_LINEAR = 6;
    public static final int SELECTION_BINARY = 7;
    public static final int SELECTION_EXIT = 0;
    private Scanner sc;
    private int selection;
    private ArrayList<String> a;
    private ArrayList<Integer> b;

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
                runInput();
            } else if (selection == SELECTION_OUTPUT) {
                runOutput();
            } else if (selection == SELECTION_BUBBLE) {
                runBubbleSort();
            } else if (selection == SELECTION_SELECTION_SORT) {
                runSelectionSort();
            } else if (selection == SELECTION_INSERTION) {
                runInsertionSort();
            } else if (selection == SELECTION_LINEAR) {
                runLinear();
            } else if (selection == SELECTION_BINARY) {
                runBinary();
            }
        } while (selection != SELECTION_EXIT);
    }


    /**
     * Nhap du lieu tu ban phim
     * Cho phep nhap do dai mang, nhap tung gia tri cua mang, luu du lieu
     */
    private void runInput() throws IOException {
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
                    writer.write((arr[i]) + " ");
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
     * Ham output:
     * in ra danh sach du lieu duoc doc tu file
     */
    private void runOutput() throws IOException {
        String temp = null;
        try {
            File input = new File("INPUT.TXT");
            Scanner reader = new Scanner(input);
            while (reader.hasNextLine()) {
                temp = reader.nextLine();
            }
            a = new ArrayList<String>(Arrays.asList(temp.split(" ")));
            System.out.println(a);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Ham sort kieu bubble:
     * Luu du lieu mang a sang mang b
     * Thuc hien bubble sort tren mang b
     * In ra man hinh ket qua tung buoc va luu vao tep
     * Tu dong in ket qua buoc cuoi cung vao file OUTPUT2.TXT
     */
    private void runBubbleSort() throws IOException {
        int[] b = new int[a.size()];
        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT1.TXT"));

        // Luu du lieu tu a sang b
        for (int i = 0; i < a.size(); i++) {
            b[i] = Integer.parseInt(a.get(i));
        }

        // Thuc hien bubble sort tren b
        // Ket qua sap xep tung buoc se duoc in ra man hinh va luu vao tep
        for (int i = 0; i < b.length - 1; i++) {
            for (int j = 0; j < b.length - 1 - i; j++) {
                if (b[j + 1] < b[j]) {
                    swap(b, j, j + 1);
                }
            }
            System.out.println(Arrays.toString(b));
            writer.write(Arrays.toString(b) + "\n");
        }
        writer.flush();
    }
    private void swap(int[] b, int x, int y) {
        int swap = b[x];
        b[x] = b[y];
        b[y] = swap;
    }

    private void runSelectionSort() throws IOException {
        int[] b = new int[a.size()];
        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT2.TXT"));

        // Luu du lieu tu a sang b
        for (int i = 0; i < a.size(); i++) {
            b[i] = Integer.parseInt(a.get(i));
        }

        // Thuc hien selection sort tren b
        // Ket qua sap xep tung buoc se duoc in ra man hinh va luu vao tep
        for (int i = 0; i < b.length; i++) {
            // Tim vi tri co gia tri nho nhat tinh tu i den a.length - 1
            int tem = i;
            for (int j = i; j < b.length; j++) {
                if (b[tem] > b[j]) {
                    tem = j;
                }
            }
            // doi cho gia tri tai i va gia tri tai vi tri tem
            swap(b, i, tem);
            System.out.println(Arrays.toString(b));
            writer.write(Arrays.toString(b) + "\n");
        }
        writer.flush();
    }

    private void runInsertionSort() throws IOException {
        int[] b = new int[a.size()];
        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT3.TXT"));

        // Luu du lieu tu a sang b
        for (int i = 0; i < a.size(); i++) {
            b[i] = Integer.parseInt(a.get(i));
        }

        // Thuc hien Insertion sort tren b
        // Ket qua sap xep tung buoc se duoc in ra man hinh va luu vao tep
        if (b[0] > b[1]) {
            swap(b, 0, 1);
            System.out.println(Arrays.toString(b));
        }

        for (int i = 2; i < b.length; i++) {
            for (int j = i; j > 0; j--) {
                if (b[j] < b[j - 1]) {
                    swap(b, j, j - 1);
                }
            }
            System.out.println(Arrays.toString(b));
            writer.write(Arrays.toString(b) + "\n");
            // doi cho gia tri tai i va gia tri tai vi tri tem
        }
        writer.flush();
    }

    private void runBinary() {
    }

    private void runLinear() {

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
