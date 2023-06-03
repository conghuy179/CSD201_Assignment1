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

    public Driver(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Menu hien thi ban dau.
     */
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

    /**
     * Ham chay chuong trinh:
     *
     * @throws IOException: trong truong hop khong tim thay file.
     */
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
     * Ham kiem tra dieu kien khi nguoi dung nhap luu chon.
     *
     * @param selectionS: String lua chon.
     * @return: Throw loi neu lua chon khong phai so tu 0 den 7.
     * Return true neu thoa man.
     */
    public boolean isSelectionValid(String selectionS) {
        if (selectionS.length() > 1) {
            throw new IllegalArgumentException("Chi nhap 1 so tu 0 den 7.");
        } else if (selectionS.charAt(0) < '0' || selectionS.charAt(0) > '7') {
            throw new IllegalArgumentException("CHi nhap 1 so trong khoang 0 den 7.");
        } else {
            return true;
        }
    }

    /**
     * Nhap du lieu tu ban phim: Xuat hien khi nguoi dung chon chuc nang 1.
     * Cho phep nhap do dai mang, nhap tung gia tri cua mang, luu du lieu
     *
     * @throws IOException: Truong hop khong tim thay file => bao loi
     */
    public void runInput() throws IOException {
        int n = 0;
        String nS;

        // Nhap so phan tu trong mang
        do {
            System.out.println("Input number of elements: ");
            try {
                nS = sc.next();
                isNValid(nS);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            n = Integer.parseInt(nS);

            // Kiem tra dieu kien phan tu
            // Neu thoa man thi truyen vao tep INPUT
            if (isNValid(n)) {
                int[] arr = new int[n];
                BufferedWriter writer = new BufferedWriter(new FileWriter("INPUT.TXT"));
                // Nhap chi tiet tung so va truong vao tep INPUT.TXT
                for (int i = 0; i < n; i++) {
                    System.out.println("Nhap gia tri thu " + (i + 1) + " trong mang: ");
                    arr[i] = sc.nextInt();
                    writer.write((arr[i]) + " ");
                }
                System.out.println("Arr = " + Arrays.toString(arr));
                writer.flush();
            }
            // Neu khong thoa man thi yeu cau nhap lai.
            else {
                System.out.println("Chi nhap 1 so duong tu 1 den 20. Yeu cau nhap lai.");
            }
        } while (!isNValid(n));
    }

    /**
     * Kiem tra dieu kien so luong cac phan tu trong mang.
     *
     * @param n: so luong phan tu trong mang.
     * @return: True neu n la so duong < 20.
     * False neu n nam ngoai khoang.
     */
    private boolean isNValid(int n) {
        if (n > 20 || n < 1) {
            return false;
        }
        return true;
    }

    /**
     * Kiem tra dieu kien so luong cac phan tu trong mang khi nguoi dung nhap tu ban phim.
     *
     * @param nS: String so luong phan tu trong mang.
     * @return: True neu n la so duong < 20. Neu khong se throw loi.
     */
    public boolean isNValid(String nS) {
        if (nS.charAt(0) < '0' || nS.charAt(0) > '9') {
            throw new IllegalArgumentException("Chi nhap 1 so duong < 20.");
        } else {
            return true;
        }
    }

    /**
     * Ham output: Xuat hien khi nguoi dung chon chuc nang 2.
     * in ra danh sach du lieu duoc doc tu file.
     */
    public void runOutput() {
        String temp = null;
        // Kiem tra file, neu khong tim thay file thi bao loi

        try {
            File input = new File("INPUT.TXT");
            Scanner reader = new Scanner(input);
            // Neu tim thay line trong tep INPUT thi truyen vao bien temp.
            while (reader.hasNextLine()) {
                temp = reader.nextLine();
            }
            // Dua gia tri vao mang a va in ra man hinh
            a = new ArrayList<String>(Arrays.asList(temp.split(" ")));
            System.out.println(a);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Ham sort kieu bubble: Xuat hien khi nguoi dung chon chuc nang 3.
     * Luu du lieu mang a sang mang b.
     * Thuc hien bubble sort tren mang b.
     * In ra man hinh ket qua tung buoc va luu vao tep.
     * Tu dong in ket qua buoc cuoi cung vao file OUTPUT2.TXT.
     */
    public void runBubbleSort() throws IOException {
        int[] b = new int[a.size()];
        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT1.TXT"));

        // Luu du lieu tu a sang b
        for (int i = 0; i < a.size(); i++) {
            b[i] = Integer.parseInt(a.get(i));
        }

        // Thuc hien bubble sort tren b
        // Ket qua sap xep tung buoc se duoc in ra man hinh va luu vao tep
        bubbleSort(b);
        System.out.println(Arrays.toString(b));
        writer.write(Arrays.toString(b) + "\n");
        writer.flush();
    }

    /**
     * Bubble Sort
     *
     * @param arr: Mang arr
     *             Sap xep lai mang, khong tra ve gia tri
     */
    public void bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {

                if (arr[j + 1] < arr[j]) {

                    int swap = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = swap;

                }
            }
        }
    }

    /**
     * Ham swap gia tri.
     *
     * @param b: Mang b.
     * @param x: Vi tri can doi gia tri voi vi tri y.
     * @param y: Vi tri can doi gia tri voi vi tri x.
     */
    public void swap(int[] b, int x, int y) {
        int swap = b[x];
        b[x] = b[y];
        b[y] = swap;
    }

    /**
     * Ham thuc hien chuc nang sap xep Selection Sort:
     * Xuat hien khi nguoi dung chon chuc nang 4.
     *
     * @throws IOException: Throw loi khi khong nhan duoc gia tri.
     */
    public void runSelectionSort() throws IOException {
        int[] b = new int[a.size()];
        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT2.TXT"));

        // Luu du lieu tu a sang b
        for (int i = 0; i < a.size(); i++) {
            b[i] = Integer.parseInt(a.get(i));
        }

        // Thuc hien selection sort tren b
        // Ket qua sap xep tung buoc se duoc in ra man hinh va luu vao tep
        selectionSort(b);
        System.out.println(Arrays.toString(b));
        writer.write(Arrays.toString(b) + "\n");
        writer.flush();
    }

    /**
     * Ham Selection Sort.
     *
     * @param a: Mang a.
     *           Sap xep lai mang, khong tra ve gia tri.
     */
    public void selectionSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            // Tim vi tri co gia tri nho nhat tinh tu i den a.length - 1
            int tem = i;
            for (int j = i; j < a.length; j++) {
                if (a[tem] > a[j]) {
                    tem = j;
                }
            }
            // doi cho gia tri tai i va gia tri tai vi tri tem
            swap(a, i, tem);
        }
    }

    /**
     * Ham sap xep Insertion Sort.
     * Xuat hien khi nguoi dung chon chuc nang 5.
     *
     * @throws IOException: Throw loi khi khong nhan duoc gia tri.
     */
    public void runInsertionSort() throws IOException {
        int[] b = new int[a.size()];
        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT3.TXT"));

        // Luu du lieu tu a sang b
        for (int i = 0; i < a.size(); i++) {
            b[i] = Integer.parseInt(a.get(i));
        }

        // Thuc hien Insertion sort tren b
        // Ket qua sap xep tung buoc se duoc in ra man hinh va luu vao tep
        insertionSort(b);
        System.out.println(Arrays.toString(b));
        writer.write(Arrays.toString(b) + "\n");
        writer.flush();
    }

    /**
     * Insertion sort.
     *
     * @param a: Mang a.
     *           Sap xep lai mang, khong tra ve gia tri.
     */
    public void insertionSort(int[] a) {
        if (a[0] > a[1]) {
            swap(a, 0, 1);
        }

        for (int i = 2; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    swap(a, j, j - 1);
                }
            }
        }
    }

    /**
     * Tim kiem tuan tu.
     * xuat hien khi nguoi dung nhap chuc nang 6.
     *
     * @throws IOException: throw loi Khi khong nhan ve gia tri.
     */
    public void runLinear() throws IOException {
        String xS;
        int x = -1;
        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT4.TXT"));

        // Nhap so thuc luu vao x
        do {
            System.out.println("Tim kiem tuyen tinh:");
            System.out.println("Nhap so thuc: ");
            try {
                xS = sc.next();
                isXValid(xS);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            x = Integer.parseInt(xS);
        } while (x < 0);

        // Thuc hien Linear Search gia tri lon hon x tai mang a
        //Luu ket qua vao Arraylist c
        ArrayList<String> c = linearSearch(a, x);

        // Ket qua tim duoc hien thi ra man hinh va luu vao tep
        if (c != null) {
            System.out.println(c);
            writer.write(String.valueOf(c));
        } else {
            System.out.println("Khong co gia tri nao lon hon " + x + ".");
        }
        writer.flush();
    }

    /**
     * LinearSearch gia tri x tren ArrayList arr.
     * Neu tim thay gia tri lon hon x => luu vao ArrayList c.
     *
     * @param arr Mang Arraylist kieu String.
     * @param x   Gia tri x can tim.
     * @return Arraylist kieu String.
     */
    public ArrayList<String> linearSearch(ArrayList<String> arr, int x) {
        ArrayList<String> c = new ArrayList<String>();
        // Neu co phan tu lon hon x => luu vao c
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).compareTo(String.valueOf(x)) > 0) {
                c.add(String.valueOf(i));
            }
        }
        return c;
    }

    /**
     * Kiem tra gia tri X nhap vao.
     *
     * @param xS: String gia tri X nguoi dung nhap vao.
     * @return True neu X la so thuc.
     */
    public boolean isXValid(String xS) {
        if (xS.charAt(0) < '0') {
            throw new IllegalArgumentException("Chi nhap so thuc.");
        } else {
            return true;
        }
    }

    /**
     * Ham tim kiem nhi phan.
     * Xuat hien khi nguoi dung nhap chuc nang 7.
     *
     * @throws IOException Throw loi neu khong nhan duoc gia tri.
     */

    public void runBinary() throws IOException {
        String xS;
        int x = -1;
        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT5.TXT"));

        // Sap xep mang a bang bubble sort
        bubbleSort(a);
        // Nhap so thuc luu vao x
        do {
            System.out.println("Tim kiem theo thuat toan nhi phan.");
            System.out.println("Nhap so thuc: ");
            try {
                xS = sc.next();
                isXValid(xS);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            x = Integer.parseInt(xS);
        } while (x < 0);

        // Thuc hien Binary Search gia tri lon bang x tren a
        int c = binarySearch(a, x);

        // Ket qua tim duoc hien thi ra man hinh va luu vao tep
        if (c != -1) {
            System.out.println(c);
            writer.write(c);
        } else {
            System.out.println("Khong co gia tri nao bang " + x + ".");
        }
        writer.flush();
    }

    /**
     * Bubble Sort.
     *
     * @param arr ArrayList kieu String.
     *            Ham sap xep ArrayList, khong tra ve gia tri.
     */
    public void bubbleSort(ArrayList<String> arr) {

        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = 0; j < arr.size() - 1 - i; j++) {
                if (arr.get(j + 1).compareTo(arr.get(i)) < 0) {
                    {
                        int swap = Integer.parseInt(arr.get(j));
                        arr.set(j, String.valueOf(Integer.parseInt(arr.get(j + 1))));
                        arr.set(j + 1, String.valueOf(swap));

                    }
                }
            }
        }
    }

    /**
     * Binary Search gia tri a tren ArrayList arr.
     *
     * @param arr: Arraylist kieu String.
     * @param a:   Gia tri a can tim.
     * @return: Vi tri cua phan tu co gia tri = a.
     * Return -1 neu khong tim thay gia tri.
     */
    public int binarySearch(ArrayList<String> arr, int a) {
        int low = 1;
        int high = arr.size() - 1;

        while (low < high - 1) {
            int mid = low + (high - low) / 2;
            if (Integer.parseInt(arr.get(mid)) == a) {
                return mid;
            } else if (Integer.parseInt(arr.get(mid)) > a) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        if (Integer.parseInt(arr.get(low)) == a) {
            return low;
        }
        return -1;
    }
}
