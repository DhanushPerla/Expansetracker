import java.util.Date;

public class Expense {
    private Date date;
    private String category;
    private double amount;

    public Expense(Date date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public Date getDate() { return date; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return "Date: " + date + ", Category: " + category + ", Amount: $" + amount;
    }
}
