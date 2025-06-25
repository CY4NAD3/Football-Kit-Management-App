import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FootballKitStore extends JFrame {

    private JPanel centralPanel;
    private JLabel[] imageLabels, priceLabels, quantityLabels, stockLabels;
    private JComboBox[] quantityBoxes;
    private JLabel totalLabel;
    private JButton saveButton, confirmButton, doNotPress;
    private ImageIcon[] icons;
    private JTextArea orderHistoryArea;
    private String[] productNames = {"Football", "Jersey", "Shorts", "Watersipper", "Scarf", "Boots"};
    private int[] prices = {500, 800, 800, 20, 100, 150};
    private int[] stocks = {150, 100, 100, 100, 80, 200};

    private int[] selectedQuantities;

    public FootballKitStore() {
        super("Football Kit Store");
        setSize(1800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon frameIcon = new ImageIcon("BlueFootball/BlueFootball.png");
        Image scaledFrameIcon = frameIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        setIconImage(scaledFrameIcon);


        int n = productNames.length;

        // Transparent container panel with FlowLayout
        JPanel containerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        containerPanel.setBackground(new Color(255, 255, 255));
        containerPanel.setOpaque(false);

        // Central panel with solid background
        centralPanel = new JPanel(new BorderLayout());
        centralPanel.setBackground(new Color(30, 31, 34));
        centralPanel.add(containerPanel, BorderLayout.CENTER);
        add(centralPanel);

        imageLabels = new JLabel[n];
        priceLabels = new JLabel[n];
        stockLabels = new JLabel[n];
        quantityLabels = new JLabel[n];
        quantityBoxes = new JComboBox[n];
        icons = new ImageIcon[n];
        selectedQuantities = new int[n];



        for (int i = 0; i < n; i++) {
            // Load and scale image
            String path = "Kit images/Product" + (i + 1) + ".jpg";
            ImageIcon rawIcon = new ImageIcon(path);
            Image scaledImage = rawIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            icons[i] = new ImageIcon(scaledImage);
            imageLabels[i] = new JLabel(icons[i]);

            //Panel for holding products
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS)); //using BoxLayout manager along Y-axis inside product panel
            productPanel.setPreferredSize(new Dimension(200, 320));
            productPanel.setBackground(new Color(40, 40, 40));
            productPanel.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90), 4));

            //making image panel, adding imageLabel to image panel and adding imagePanel to product panel
            JPanel imagePanel = new JPanel();
            imagePanel.setBackground(new Color(40, 40, 40));
            imagePanel.add(imageLabels[i]);
            productPanel.add(imagePanel);

            //price labels and adding to product panel
            priceLabels[i] = new JLabel(productNames[i] + " Price $ " + prices[i] + "/-");
            priceLabels[i].setFont(new Font("Segoe UI", Font.BOLD, 14));
            priceLabels[i].setForeground(Color.WHITE);
            priceLabels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            productPanel.add(priceLabels[i]);

            //stock labels and adding to product panel
            stockLabels[i] = new JLabel("Stock: " + stocks[i]);
            stockLabels[i].setFont(new Font("Segoe UI", Font.PLAIN, 13));
            stockLabels[i].setForeground(Color.LIGHT_GRAY);
            stockLabels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            productPanel.add(stockLabels[i]);

            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            quantityPanel.setBackground(new Color(40, 40, 40));

            quantityLabels[i] = new JLabel("Qty:");
            quantityLabels[i].setFont(new Font("Segoe UI", Font.PLAIN, 13));
            quantityLabels[i].setForeground(Color.WHITE);
            quantityPanel.add(quantityLabels[i]);

            quantityBoxes[i] = new JComboBox();
            for (int j = 0; j <= stocks[i]; j++) {
                quantityBoxes[i].addItem(j);
            }
            quantityBoxes[i].setBackground(new Color(70, 70, 70));
            quantityBoxes[i].setForeground(Color.WHITE);
            quantityPanel.add(quantityBoxes[i]);

            productPanel.add(quantityPanel);
            containerPanel.add(productPanel);


        }


        // centerGroupPanel to hold total label and buttons
        JPanel centerGroupPanel = new JPanel();
        centerGroupPanel.setLayout(new BoxLayout(centerGroupPanel, BoxLayout.Y_AXIS));
        centerGroupPanel.setBackground(new Color(30, 31, 34));

        // Total Price Label
        totalLabel = new JLabel("Total Price: $0");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setOpaque(true);
        totalLabel.setBackground(new Color(40, 40, 40));
        totalLabel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        //totalLabel.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90), 1));
        totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center in BoxLayout
        centerGroupPanel.add(totalLabel);

        // Spacer
        centerGroupPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(30, 31, 34));

        saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(80, 35));

        confirmButton = new JButton("Confirm");
        confirmButton.setPreferredSize(new Dimension(80, 35));



        // Skull icon (scaled) E-G
        ImageIcon rawSkull = new ImageIcon("skull/skull.png");
        Image scaledSkullImg = rawSkull.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon skull = new ImageIcon(scaledSkullImg);

        doNotPress = new JButton("Press it", skull);
        doNotPress.setPreferredSize(new Dimension(150, 35));
        doNotPress.setHorizontalTextPosition(SwingConstants.RIGHT);
        doNotPress.setIconTextGap(10);


        buttonPanel.add(saveButton);
        buttonPanel.add(confirmButton);
        buttonPanel.add(doNotPress);



        centerGroupPanel.add(buttonPanel);


        //We have used Action Listener instead of mouse listener in this project.
        //DO NOT PRESS button — Bolbona ki kore eida LOL!
        doNotPress.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Close the main FootballKitStore window
                dispose(); // OR use setVisible(false); if you want to keep program running silently

                // Start infinite loop of popup prank
                while (true) {
                    JOptionPane.showMessageDialog(
                            null,
                            "💀 Your computer has been hacked!\n" +
                                    "You have been infected with multiple viruses.\n" +
                                    "All of your data and bank information is encrypted.\n" +
                                    "GG ez! 💸💻🔥",
                            "☠️ SYSTEM BREACH ☠️",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });




        // When SAVE button is clicked: we are calculating and displaying the total price
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int total = 0;
                // Loop through all products
                for (int i = 0; i < productNames.length; i++) {
                    // Get selected quantity from combo box and store it
                    selectedQuantities[i] = (int) quantityBoxes[i].getSelectedItem();
                    // Add to total price
                    total += selectedQuantities[i] * prices[i];
                }
                // Update the label to show the total price
                totalLabel.setText("Total Price: $" + total);
            }
        });


        // When CONFIRM button is clicked we are validating stock, show confirmation, updating file and UI
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int total = 0;
                StringBuilder orderSummary = new StringBuilder("🛍️ Order Summary:\n\n");

                // Loop through all products to check selected quantities
                for (int i = 0; i < productNames.length; i++) {
                    int qty = selectedQuantities[i];

                    if (qty > 0) {
                        // Check if stock is enough
                        if (stocks[i] >= qty) {
                            int cost = qty * prices[i];
                            stocks[i] -= qty; // Reduce stock
                            total += cost;

                            // Update stock label on GUI
                            stockLabels[i].setText("Stock: " + stocks[i]);

                            // Add line to order summary
                            orderSummary.append(productNames[i])
                                    .append(" x").append(qty)
                                    .append(" = $").append(cost)
                                    .append(" | Remaining: ").append(stocks[i])
                                    .append("\n");

                            // Reset selected quantity and combo box
                            quantityBoxes[i].setSelectedIndex(0);
                            selectedQuantities[i] = 0;
                        } else {
                            // If not enough stock, we are going to show a warning message and cancel
                            JOptionPane.showMessageDialog(FootballKitStore.this,
                                    "⚠️ Not enough stock for " + productNames[i],
                                    "Stock Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }

                // Only proceed if total > 0 (at least one item selected)
                if (total > 0) {
                    // Show confirmation dialog before saving
                    int confirm = JOptionPane.showConfirmDialog(
                            FootballKitStore.this,
                            "Are you sure you want to confirm this order?",
                            "Confirm Purchase",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm != JOptionPane.YES_OPTION) {
                        return; // If user clicks "No", cancel the process
                    }

                    // Add total price to summary
                    orderSummary.append("\n✅ Total price: $").append(total).append("\n------------------------------------------------\n");

                    try {
                        // Create "orders" directory if not exists
                        File dir = new File("./orders");
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }

                        //  Create or open "order_history.txt" file in append mode
                        File file = new File(dir, "order_history.txt");
                        FileWriter fw = new FileWriter(file, true);
                        fw.write(orderSummary.toString()); // Write summary to file
                        fw.close();

                        // Show same summary in the JTextArea
                        orderHistoryArea.append(orderSummary.toString());

                        // Reset total price label
                        totalLabel.setText("Total Price: $0");

                        // Show success popup
                        JOptionPane.showMessageDialog(FootballKitStore.this,
                                "Order saved successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);

                    }
                    catch (Exception ex) {
                        // If file error occurs, show error message
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(FootballKitStore.this,
                                "Error saving order to file.",
                                "File I/O Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
                else {
                    // If no item was selected, show a warning in the UI
                    orderHistoryArea.append("⚠️ No items selected to confirm.\n------------------------------------------------\n");
                }
            }
        });




        // Add to center of the main panel
        // Main vertical box that holds both products and center group
        JPanel verticalStack = new JPanel();
        verticalStack.setLayout(new BoxLayout(verticalStack, BoxLayout.Y_AXIS));
        verticalStack.setOpaque(false);

        // Add product cards
        verticalStack.add(containerPanel);

        // Spacer to push center group lower
        verticalStack.add(Box.createVerticalStrut(20));

        // Add total + buttons center group
        verticalStack.add(centerGroupPanel);

        // Add the vertical stack to center of main layout
        centralPanel.add(verticalStack, BorderLayout.CENTER);

        orderHistoryArea = new JTextArea(10, 70); // Set rows and columns
        orderHistoryArea.setEditable(false);
        orderHistoryArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        orderHistoryArea.setBackground(new Color(0, 0, 0));
        orderHistoryArea.setForeground(Color.GREEN);
        orderHistoryArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        JScrollPane scrollPane = new JScrollPane(orderHistoryArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(1000, 150)); // Width adjusted for product cards
        centerGroupPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        centerGroupPanel.add(scrollPane);


        setVisible(true);
    }
}
