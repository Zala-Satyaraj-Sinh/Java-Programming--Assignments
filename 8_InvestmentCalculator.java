import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class InvestmentCalculator extends JFrame {

    // GUI Components
    private JLabel principalLabel, rateLabel, yearsLabel, resultLabel;
    private JTextField principalField, rateField, yearsField;
    private JButton calculateButton;
    private JTextArea resultArea;

    public InvestmentCalculator() {
        // --- Frame Setup ---
        setTitle("Investment Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Main layout with padding

        // --- Input Panel ---
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5)); // Grid for labels and fields
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        principalLabel = new JLabel("Principal Amount ($):");
        principalField = new JTextField(10);
        inputPanel.add(principalLabel);
        inputPanel.add(principalField);

        rateLabel = new JLabel("Annual Interest Rate (%):");
        rateField = new JTextField(10);
        inputPanel.add(rateLabel);
        inputPanel.add(rateField);

        yearsLabel = new JLabel("Number of Years:");
        yearsField = new JTextField(10);
        inputPanel.add(yearsLabel);
        inputPanel.add(yearsField);

        calculateButton = new JButton("Calculate");
        inputPanel.add(new JLabel()); // Empty cell for alignment
        inputPanel.add(calculateButton);

        // --- Result Panel ---
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        resultLabel = new JLabel("Future Value:");
        resultArea = new JTextArea(3, 20);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.BOLD, 16));
        resultPanel.add(resultLabel, BorderLayout.NORTH);
        resultPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // --- Add panels to frame ---
        add(inputPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);

        // --- Action Listener for the button ---
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateFutureValue();
            }
        });
    }

    private void calculateFutureValue() {
        try {
            // Get values from text fields
            double principal = Double.parseDouble(principalField.getText());
            double rate = Double.parseDouble(rateField.getText());
            int years = Integer.parseInt(yearsField.getText());

            // Formula for compound interest: A = P(1 + r/n)^(nt)
            // Here, we assume compounding annually (n=1)
            double annualRate = rate / 100.0;
            double futureValue = principal * Math.pow(1 + annualRate, years);

            // Format the result as currency
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
            resultArea.setText(currencyFormatter.format(futureValue));
            resultArea.setForeground(Color.BLACK);

        } catch (NumberFormatException ex) {
            // Handle invalid input
            resultArea.setText("Invalid input. Please enter valid numbers.");
            resultArea.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InvestmentCalculator().setVisible(true);
            }
        });
    }
}
