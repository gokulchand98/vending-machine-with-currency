import java.util.Scanner;

public class VendingMachine {
    public static void main(String[] args) {
        // Menu items and prices
        String[] items = {"Water", "Soda", "Chips"};
        double[] prices = {1.00, 1.50, 2.00};
        int[] stock = {10, 10, 10}; // Initial stock for each item

        // Currency denominations
        double[] denominations = {0.25, 0.50, 1.00, 5.00, 10.00, 20.00};

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu
            System.out.println("Select an item:");
            for (int i = 0; i < items.length; i++) {
                System.out.println(i + 1 + ". " + items[i] + " - $" + prices[i]);
            }

            // Get user selection
            int selection = scanner.nextInt() - 1;

            // Check if selection is valid
            if (selection < 0 || selection >= items.length) {
                System.out.println("Invalid selection. Please try again.");
                continue;
            }

            // Check if item is in stock
            if (stock[selection] == 0) {
                System.out.println("Sorry, " + items[selection] + " is out of stock.");
                continue;
            }

            // Get quantity
            System.out.println("Enter quantity:");
            int quantity = scanner.nextInt();

            // Calculate total cost
            double totalCost = prices[selection] * quantity;

            // Accept payment
            System.out.println("Total cost: $" + totalCost);
            System.out.println("Enter payment amount using denominations (0.25, 0.50, 1.00, 5.00, 10.00, 20.00):");
            double payment = 0;
            while (payment < totalCost) {
                double denomination = scanner.nextDouble();
                boolean validDenomination = false;
                for (double d : denominations) {
                    if (denomination == d) {
                        validDenomination = true;
                        break;
                    }
                }
                if (validDenomination) {
                    payment += denomination;
                } else {
                    System.out.println("Invalid denomination. Please use valid denominations.");
                }
                System.out.println("Current payment: $" + payment);
            }

            // Calculate change
            double change = payment - totalCost;

            if (change < 0) {
                System.out.println("Insufficient payment. Transaction cancelled.");
            } else {
                System.out.println("Change: $" + change);
                System.out.println("Thank you for your purchase!");
                stock[selection] -= quantity; // Update stock
            }
        }
    }
}
