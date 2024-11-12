import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager expenseManager = new ExpenseManager();

        System.out.println("Welcome to the Expense Tracker!");
        System.out.print("Register or Login (R/L): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("R")) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            User.registerUser(username, password);
        }

        System.out.print("Enter username to login: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (!User.loginUser(username, password)) {
            System.out.println("Invalid login.");
            return;
        }
        System.out.println("Login successful!");

        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Category-wise Summation");
            System.out.println("4. Save Expenses to File");
            System.out.println("5. Load Expenses from File");
            System.out.println("6. Exit");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter date (yyyy-MM-dd): ");
                    String dateStr = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                        Expense expense = new Expense(date, category, amount);
                        expenseManager.addExpense(expense);
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Use yyyy-MM-dd.");
                    }
                    break;
                case 2:
                    expenseManager.viewExpenses();
                    break;
                case 3:
                    expenseManager.viewCategoryWiseSum();
                    break;
                case 4:
                    try {
                        expenseManager.saveExpensesToFile("expenses.txt");
                    } catch (IOException e) {
                        System.out.println("Error saving to file.");
                    }
                    break;
                case 5:
                    try {
                        expenseManager.loadExpensesFromFile("expenses.txt");
                    } catch (IOException e) {
                        System.out.println("Error loading from file.");
                    }
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
