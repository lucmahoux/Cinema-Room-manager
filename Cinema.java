package cinema;
import java.util.Scanner;
public class Cinema {

    static int seats = 0;
    static int rows = 0;
    static String[][] matrix;
    static int totalSeats = 0;
    static Scanner scanner = new Scanner(System.in);
    static int totalTickets = 0;
    static float percentage = 0;
    static int income = 0;
    static int totalIncome = 0;

    public static void initProg() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        totalSeats = rows * seats;
        System.out.println();
        initMatrix(rows, seats);
    }

    public static void initMatrix(int a, int b) {
        String[][] mat = new String[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                mat[i][j] = "S";
            }
        }
        setIncome(a, b);
        matrix = mat;
    }

    public static void addIncome(int row) {
        if (totalSeats <= 60) {
            income += 10;
        } else if (row <= rows / 2) {
            income += 10;
        } else {
            income += 8;
        }
    }

    public static void setIncome(int a, int b) {
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else if (a % b == 0) {
            totalIncome = totalSeats / 2 * 10 + totalSeats / 2 * 8;
        } else {
            totalIncome = (rows / 2) * seats * 10 + (rows / 2 + 1) * seats * 8;
        }
    }

    public static void showSeats() {
        System.out.println();
        System.out.println("Cinema:");
        System.out.println();
        System.out.print(" ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int i = 1; i <= rows; i++) {
            System.out.print(i + " ");
            for (int j = 1; j <= seats; j++) {
                System.out.print(matrix[i - 1][j - 1] + " ");
            }
            System.out.println();
        }
        System.out.println();
        menu();
    }

    public static boolean freeTicket(int row, int seat) {
        return matrix[row - 1][seat - 1].equals("S");
    }

    public static void buyTicket() {
        System.out.println("Enter a row number:");
        int rowNb = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNb = scanner.nextInt();
        if (rowNb <= rows && rowNb > 0 && seatNb > 0 && seatNb <= seats) {
            if (freeTicket(rowNb, seatNb)) {
                matrix[rowNb - 1][seatNb - 1] = "B";

                if (totalSeats <= 60) {
                    System.out.println("Ticket price: $10");
                } else if (rowNb <= rows / 2) {
                    System.out.println("Ticket price: $10");
                } else {
                    System.out.println("Ticket price: $8");
                }
                totalTickets++;
                addIncome(rowNb);
                System.out.println();
                menu();
            } else {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
                buyTicket();
            }
        } else {
            System.out.println("Wrong input!");
            System.out.println();
            buyTicket();
        }

    }

    public static void statistics() {
        System.out.printf("Number of purchased tickets: %s%n", totalTickets);
        percentage = (float) totalTickets / totalSeats * 100;
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");
        System.out.printf("Current income: $%d%n", income);
        System.out.printf("Total income: $%d%n", totalIncome);
        System.out.println();
        menu();
    }

    public static void menu() {

        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                showSeats();
                break;
            case 2:
                buyTicket();
                break;
            case 3:
                statistics();
                break;
            case 0:
                break;
        }
    }

    public static void main(String[] args) {
        // Write your code here
        initProg();
        menu();
    }
}