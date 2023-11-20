import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BuyForm extends JFrame implements ActionListener{
    protected Container c;
    private final Font mainfFont = new Font("Arial Rounded MT", Font.BOLD, 20);
    private final Font titleFont = new Font("Arial Rounded MT", Font.BOLD, 25);
    private JButton cancelButton, buyButton;
    protected JTextField psnField;
    protected JTextField lotteryField;
    private JTextField dateField;
    private JLabel psnLabel, lotteryLabel, dateLabel, titleLabel, countLabel;
    protected JComboBox<String> countBox;
    private static long millis = System.currentTimeMillis();
    private String countString[];
    protected int lotterry;

    public BuyForm() {
        countNum();
        buyGui();
    }

    public void countNum() {
        countString = new String[100];
        for (int i = 0; i < 100; i++) {
            String num = "";
            int ber;
            ber = i + 1;
            num = String.valueOf(ber);
            countString[i] += num;
            countString[i] = countString[i].substring(4);
        }
        System.out.println(countString[1]);
    }

    public void buyGui() {
        java.util.Date date = new java.util.Date(millis);      
        System.out.println(date);
        String currentdate = "";
        String month, day, year, showdate = "";
        currentdate = date.toString();
        month = currentdate.substring(4, 7);
        day = currentdate.substring(8, 10);
        year = currentdate.substring(24, 28);
        System.out.println(day + " " + month + " " + year);
        int d;
        d = Integer.parseInt(day);
        if(d >= 1 && d <= 15){
            showdate += "01 " + month + " " + year;
        } else{
            showdate += "16 " + month + " " + year;
        }

        c = getContentPane();
        c.setBackground(new Color(245, 245, 245));
        c.setLayout(new GridLayout(6, 2, 5, 5));

        titleLabel = new JLabel("BUY FORM");
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        c.add(titleLabel);
        titleLabel = new JLabel("-----------");
        titleLabel.setVisible(false);
        c.add(titleLabel);

        psnLabel = new JLabel("Personal ID :");
        psnLabel.setFont(mainfFont);
        c.add(psnLabel);
        psnField = new JTextField(10);
        psnField.setFont(mainfFont);
        c.add(psnField);

        dateLabel = new JLabel("Date :");
        dateLabel.setFont(mainfFont);
        c.add(dateLabel);
        dateField = new JTextField(10);
        dateField.setEnabled(false);
        dateField.setText(showdate);
        dateField.setFont(mainfFont);
        c.add(dateField);

        lotteryLabel = new JLabel("Lottery Number :");
        lotteryLabel.setFont(mainfFont);
        c.add(lotteryLabel);
        lotteryField = new JTextField(10);
        lotteryField.setFont(mainfFont);
        c.add(lotteryField);

        countLabel = new JLabel("Amount :");
        countLabel.setFont(mainfFont);
        c.add(countLabel);
        countBox = new JComboBox<String>(countString);
        countBox.setMaximumRowCount(5);
        countBox.setFont(mainfFont);
        c.add(countBox);

        buyButton = new JButton("Buy");
        buyButton.addActionListener(this);
        buyButton.setEnabled(true);
        buyButton.setBackground(new Color(95, 141, 78));
        buyButton.setForeground(new Color(245, 245, 245));
        buyButton.setFont(mainfFont);
        c.add(buyButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setEnabled(true);
        cancelButton.setBackground(new Color(221, 83, 83));
        cancelButton.setForeground(new Color(245, 245, 245));
        cancelButton.setFont(mainfFont);
        c.add(cancelButton);

        setSize(550, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        String psn = psnField.getText();
        String lot = lotteryField.getText();
        if (event.getSource() == buyButton) {
            if (psn.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Enter Personal ID", "Alert", JOptionPane.INFORMATION_MESSAGE);
            } else if (lot.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Enter Lottery number", "Alert", JOptionPane.INFORMATION_MESSAGE);
            } else if (psn.equals("") == false && lot.equals("") == false) {
                // psnCheck
                Boolean flag = true;
                for (int a = 0; a < psn.length(); a++) {
                    if (a == 0 && psn.charAt(a) == '-') {
                        continue;
                    }
                    if (!Character.isDigit(psn.charAt(a))) {
                        flag = false;
                    }
                }
                if (flag) {
                    // System.out.println(psn + " is valid Integer");
                } else {
                    JOptionPane.showMessageDialog(null, psn + " is not Personal ID. Try again.", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                    psnField.setText("");
                }

                // lotCheck
                Boolean ltflag = true;
                for (int a = 0; a < lot.length(); a++) {
                    if (a == 0 && lot.charAt(a) == '-')
                        continue;
                    if (!Character.isDigit(lot.charAt(a)))
                        ltflag = false;
                }
                if (ltflag) {
                    // System.out.println(lot + " is valid Integer");
                    if (lot.length() != 6) {
                        JOptionPane.showMessageDialog(null, lot + " : Lottery numbers consist of 6 numbers. Try again.",
                                "Alert",
                                JOptionPane.INFORMATION_MESSAGE);
                        lotteryField.setText("");
                    }else if(psn.length() != 13){
                        JOptionPane.showMessageDialog(null, psn + " Personal ID numbers consist of 13 numbers. Try again.",
                            "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                    psnField.setText("");
                    }else if(reafilebuy()==false){
                        JOptionPane.showMessageDialog(null, lot + " is Sold out.", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        writeToFile();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, lot + " is not Lottery number. Try again.", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                    lotteryField.setText("");
                }
            }
        } else if (event.getSource() == cancelButton) {
            clearText();
        }
    }

    public void writeToFile() {
        try {
            FileWriter buydata = new FileWriter("BuyData.dat", true);
            BufferedWriter bd = new BufferedWriter(buydata);
            PrintWriter pw = new PrintWriter(bd);
            pw.print(psnField.getText());
            pw.print(",");
            pw.print(dateField.getText());
            pw.print(",");
            pw.print(lotteryField.getText());
            pw.print(",");
            pw.print(countBox.getSelectedItem());
            //System.out.println(countBox.getSelectedItem());
            pw.println();
            pw.close();
            JOptionPane.showMessageDialog(null, "Buy Success");
            clearText();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
    }

    public void clearText(){
        //super.clearText();
        psnField.setText("");
        lotteryField.setText("");
        countBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        BuyForm buy = new BuyForm();
    }
    
    public Boolean reafilebuy(){
        String arrayBuyraw[] = new String[100];
        String arrayBuy[][] = new String[100][173];
        int locate = 0;
        String temp = countBox.getSelectedItem().toString();
        int count = Integer.parseInt(temp);
        String lot = lotteryField.getText();
        try {
            Scanner scan = new Scanner(new FileReader("BuyData.dat"));
            while (scan.hasNext()) {
                arrayBuyraw[locate] = scan.nextLine();
                locate ++;
            }
            for (int i = 0; i < locate; i++) {
                arrayBuy[i] = arrayBuyraw[i].split(",");
            }
            for(int i = 0; i < locate; i++){
                for(int j = 0; j < arrayBuy[i].length; j++){
                    //System.out.println(arrayPrize[i][j]);
                }
                System.out.println();
            }

            //check
            for (int i = 0; i < locate; i++) {

                String datestring = dateField.getText();
                System.out.println(datestring);
                if(datestring.equals(arrayBuy[i][1])){
                    System.out.println("Entering date");
                    if (arrayBuy[i][2].equals(lot)) {
                        count = count + Integer.parseInt(arrayBuy[i][3]);
                        System.out.println("Entering lot");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
        if(count > 100){
            return false;
        }else{
            return true;
        }
    }
}
