import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckForm extends JFrame implements ActionListener {
    private Container c;
    private final Font mainfFont = new Font("Arial Rounded MT", Font.BOLD, 20);
    private final Font titleFont = new Font("Arial Rounded MT", Font.BOLD, 25);
    private JButton cancelButton, chkButton, showButton;
    private JTextField psnField, lotteryField, dateField;
    private JLabel psnLabel, lotteryLabel, dateLabel, titleLabel, rewardLabel, roundLabel, yearLabel;
    private JComboBox<String> roundBox, yearBox;
    private JTextArea area;
    private JScrollPane scrollBar;
    private static long millis = System.currentTimeMillis();
    private String rewardString = "", roundString[], yearString[];
    public static String dataX[];
    public static String lotData[][];
    public static String Textdata = "";
    private int reward = 0;
    Show show;

    public CheckForm() {
        super("Program Lottery");
        countNum();
        checkGui();
    }

    public void countNum() {
        java.util.Date date = new java.util.Date(millis);
        // System.out.println(date);
        String currentdate = "";
        String month, day, year, showdate = "";
        currentdate = date.toString();
        month = currentdate.substring(4, 7);
        day = currentdate.substring(8, 10);
        year = currentdate.substring(24, 28);
        // System.out.println(day + " " + month + " " + year);
        int y;
        y = Integer.parseInt(year);
        roundString = new String[24];
        roundString[0] += "01 " + "January";
        roundString[1] += "16 " + "January";
        roundString[2] += "01 " + "February";
        roundString[3] += "16 " + "February";
        roundString[4] += "01 " + "March";
        roundString[5] += "16 " + "March";
        roundString[6] += "01 " + "Apr";
        roundString[7] += "16 " + "Apr";
        roundString[8] += "01 " + "May";
        roundString[9] += "16 " + "May";
        roundString[10] += "01 " + "June";
        roundString[11] += "16 " + "June";
        roundString[12] += "01 " + "July";
        roundString[13] += "16 " + "July";
        roundString[14] += "01 " + "August";
        roundString[15] += "16 " + "August";
        roundString[16] += "01 " + "September";
        roundString[17] += "16 " + "September";
        roundString[18] += "01 " + "October";
        roundString[19] += "16 " + "October";
        roundString[20] += "01 " + "November";
        roundString[21] += "16 " + "November";
        roundString[22] += "01 " + "December";
        roundString[23] += "16 " + "December";

        yearString = new String[10];
        for (int i = 0; i < 10; i++) {
            yearString[i] += y - i;
        }
        for (int i = 0; i < 24; i++) {
            roundString[i] = roundString[i].substring(4);
        }
        for (int i = 0; i < 10; i++) {
            yearString[i] = yearString[i].substring(4);
        }
        // System.out.print(yearString[1]);
    }

    public void checkGui() {
        java.util.Date date = new java.util.Date(millis);
        // System.out.println(date);
        String currentdate = "";
        String month, day, year, showdate = "";
        currentdate = date.toString();
        month = currentdate.substring(4, 7);
        day = currentdate.substring(8, 10);
        year = currentdate.substring(24, 28);
        // System.out.println(day + " " + month + " " + year);
        showdate += day + " " + month + " " + year;

        c = getContentPane();
        c.setBackground(new Color(245, 245, 245));
        c.setLayout(new GridLayout(8, 2));

        titleLabel = new JLabel("CHECK FORM");
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

        dateLabel = new JLabel("Current Date :");
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

        yearLabel = new JLabel("Year :");
        yearLabel.setFont(mainfFont);
        c.add(yearLabel);
        yearBox = new JComboBox<String>(yearString);
        yearBox.setMaximumRowCount(5);
        yearBox.setFont(mainfFont);
        c.add(yearBox);

        roundLabel = new JLabel("Round :");
        roundLabel.setFont(mainfFont);
        c.add(roundLabel);
        roundBox = new JComboBox<String>(roundString);
        roundBox.setMaximumRowCount(5);
        roundBox.setFont(mainfFont);
        c.add(roundBox);

        rewardLabel = new JLabel("Reward : No Reward");
        rewardLabel.setFont(mainfFont);
        rewardLabel.setHorizontalAlignment(JLabel.CENTER);
        rewardLabel.setVerticalAlignment(JLabel.CENTER);
        c.add(rewardLabel);

        showButton = new JButton("Show");
        showButton.addActionListener(this);
        showButton.setEnabled(true);
        showButton.setBackground(new Color(68, 60, 104));
        showButton.setForeground(new Color(245, 245, 245));
        showButton.setFont(mainfFont);
        c.add(showButton);

        chkButton = new JButton("Check");
        chkButton.addActionListener(this);
        chkButton.setEnabled(true);
        chkButton.setBackground(new Color(95, 141, 78));
        chkButton.setForeground(new Color(245, 245, 245));
        chkButton.setFont(mainfFont);
        c.add(chkButton);

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
        boolean flag = true, fg = false;
        if (event.getSource() == showButton) {
            if (psn.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Enter Personal ID", "Alert", JOptionPane.INFORMATION_MESSAGE);
            } else if (psn.equals("") == false) {
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
                    if (psn.length() != 13) {
                        JOptionPane.showMessageDialog(null,
                                psn + " Personal ID numbers consist of 13 numbers. Try again.",
                                "Alert",
                                JOptionPane.INFORMATION_MESSAGE);
                        psnField.setText("");
                    } else {
                        Show show = new Show(psn);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, psn + " is not Personal ID. Try again.", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                    psnField.setText("");
                    flag = true;
                }
            }
        } else if (event.getSource() == chkButton) {
            if (lot.equals("") == true) {
                JOptionPane.showMessageDialog(null, "Enter Lottery number", "Alert", JOptionPane.INFORMATION_MESSAGE);
            } else if (lot.equals("") == false) {
                // lotCheck
                Boolean ltflag = true;
                for (int a = 0; a < lot.length(); a++) {
                    if (a == 0 && lot.charAt(a) == '-')
                        continue;
                    if (!Character.isDigit(lot.charAt(a)))
                        ltflag = false;
                }
                if (ltflag) {
                    if (lot.length() != 6) {
                        JOptionPane.showMessageDialog(null, lot + " : Lottery numbers consist of 6 numbers. Try again.",
                                "Alert",
                                JOptionPane.INFORMATION_MESSAGE);
                        lotteryField.setText("");
                    } else {
                        String date = (String) roundBox.getSelectedItem();
                        JOptionPane.showMessageDialog(null, checkLot(lot, date), "Alert",
                                JOptionPane.INFORMATION_MESSAGE);
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

    public String checkLot(String lot, String date) {
        int num = 0;
        String str = "", status = "";
        int locate = 0;
        try {
            String arrayPrizeraw[] = new String[100];
            String arrayPrize[][] = new String[100][173];
            Scanner scan = new Scanner(new FileReader("Prize.dat"));
            while (scan.hasNext()) {
                arrayPrizeraw[locate] = scan.nextLine();
                System.out.println(arrayPrizeraw[locate]);
                System.out.println(arrayPrizeraw.length);
                locate ++;
            }
            for (int i = 0; i < locate; i++) {
                arrayPrize[i] = arrayPrizeraw[i].split(",");
            }
            for(int i = 0; i < locate; i++){
                for(int j = 0; j < arrayPrize[i].length; j++){
                    //System.out.println(arrayPrize[i][j]);
                }
                System.out.println();
            }

            // dataX = str.split("\\|");
            // lotData = new String[dataX.length][];
            // for (int i = 0; i < dataX.length; i++) {
            //     lotData[i] = dataX[i].split(",");
            // }
            // for (int i = 0; i < lotData.length; i++) {
            //     for (int j = 0; j < lotData[i].length; j++) {
            //         if (j != lotData[i].length - 1) {
            //             Textdata += lotData[i][j] + "\t\t";
            //         } else {
            //             Textdata += lotData[i][j];
            //         }
            //     }
            //     Textdata += "\n";
            // }

            //check
            boolean logic = true;
            System.out.println(arrayPrize[0][1]);
            System.out.println(arrayPrize[0][2]);
            System.out.println(arrayPrize[0][3]);
            System.out.println(lot);

            for (int i = 0; i < locate; i++) {

                String datestring = roundBox.getSelectedItem().toString()+" "+yearBox.getSelectedItem().toString();
                System.out.println(datestring);
                System.out.println(datestring.length());
                System.out.println(arrayPrize[i][0]);
                if(datestring.equals(arrayPrize[i][0])){
                    System.out.println("Entering loop");
                    if (logic!=false) {
                        if (arrayPrize[i][1].equals(lot)) {
                                reward = 6000000;
                                rewardLabel.setText("Reward : " + reward + " Baht");
                                status = "Check Success";
                                System.out.println(rewardString);
                                logic = false;
                                System.out.println("Hello");
                        }
                    }
                    if(logic!=false) {
                        for (int j = 9; j < 14; j++){
                            if(arrayPrize[i][j].equals(lot)){
                                reward = 200000;
                                rewardLabel.setText("Reward : " + reward + " Baht");
                                status = "Check Success";
                                System.out.println(rewardString);
                                logic = false;
                            }
                        }
                    }
                    if (logic!=false) {
                        System.out.println("Entering kaikeang");
                        if(arrayPrize[i][2].equals(lot) || arrayPrize[i][3].equals(lot)){
                            reward = 100000;
                            rewardLabel.setText("Reward : " + reward + " Baht");
                            status = "Check Success";
                            System.out.println(rewardString);
                            logic = false;
                        }
                    }
                    if (logic!=false) {
                        for (int j = 14; j < 24; j++){
                            if(arrayPrize[i][j].equals(lot)){
                                reward = 80000;
                                rewardLabel.setText("Reward : " + reward + " Baht");
                                status = "Check Success";
                                System.out.println(rewardString);
                                logic = false;
                            }
                        }
                    }
                    if (logic!=false) {
                        for (int j = 24; j < 74; j++){
                            if(arrayPrize[i][j].equals(lot)){
                                reward = 40000;
                                rewardLabel.setText("Reward : " + reward + " Baht");
                                status = "Check Success";
                                System.out.println(rewardString);
                                logic = false;
                            }
                        }
                    }
                    if (logic!=false) {
                        for (int j = 74; j < 174; j++){
                            if(arrayPrize[i][j].equals(lot)){
                                reward = 20000;
                                rewardLabel.setText("Reward : " + reward + " Baht");
                                status = "Check Success";
                                System.out.println(rewardString);
                                logic = false;
                            }
                        }
                    }
                    if(logic!=false) {
                        String for3 = lot.substring(0,3);
                        String back3 = lot.substring(3,6);
                        String back2 = lot.substring(4,6);
                        System.out.println(for3);
                        System.out.println(back3);
                        System.out.println(back2);
                        if(arrayPrize[i][4].equals(for3) || arrayPrize[i][5].equals(for3) || arrayPrize[i][6].equals(back3) || arrayPrize[i][7].equals(back3)){
                            reward = 4000;
                            rewardLabel.setText("Reward : " + reward + " Baht");
                            status = "Check Success";
                            System.out.println(rewardString);
                            logic = false;
                        }else{
                            if(arrayPrize[i][8].equals(back2)){
                                reward = 2000;
                                rewardLabel.setText("Reward : " + reward + " Baht");
                                status = "Check Success";
                                System.out.println(rewardString);
                                logic = false;
                            }
                        }
                    }
                }
            }
            System.out.println(logic);
            if(logic == true){
                status = "Check Success : No Reward";
                rewardLabel.setText("Reward : No Reward");
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
        //System.out.println(lotData[0][2] + " - " + lot);
        return status;
    }

    public void clearText() {
        psnField.setText("");
        lotteryField.setText("");
        yearBox.setSelectedIndex(0);
        roundBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        CheckForm check = new CheckForm();
    }
}
