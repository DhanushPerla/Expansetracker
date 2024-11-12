import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Date;


public class ExpenseManager {
    private ArrayList<Expense> expenses;

    public ExpenseManager() {
        this.expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        System.out.println("Expense added successfully!");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    public void viewCategoryWiseSum() {
        HashMap<String, Double> categorySum = new HashMap<>();
        for (Expense expense : expenses) {
            categorySum.put(expense.getCategory(),
                categorySum.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount());
        }
        for (String category : categorySum.keySet()) {
            System.out.println("Category: " + category + ", Total: $" + categorySum.get(category));
        }
    }

    public void saveExpensesToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Expense expense : expenses) {
                writer.write(expense.getDate() + "," + expense.getCategory() + "," + expense.getAmount());
                writer.newLine();
            }
        }
        System.out.println("Expenses saved to file.");
    }

    public void loadExpensesFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Date date = new Date(parts[0]);
                String category = parts[1];
                double amount = Double.parseDouble(parts[2]);
                expenses.add(new Expense(date, category, amount));
            }
        }
        System.out.println("Expenses loaded from file.");
    }
}
