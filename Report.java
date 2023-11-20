import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class Report extends JFrame implements ActionListener{
    JTextArea trea;
    JScrollPane scroll;
    JLabel labelText;
    JTable tableXD;
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private final Font mainfFont = new Font("Arial Rounded MT", Font.BOLD, 22);
    private final Font titleFont = new Font("Arial Rounded MT", Font.BOLD, 48);
    private final Font textFont = new Font("Arial Rounded MT", Font.BOLD, 20);
    private JButton cancelButton, chkButton;
    private JTextField psnField, dateField;
    private JLabel psnLabel, dateLabel, roundLabel, yearLabel;
    private static JLabel moneyLabel;
    private static JLabel payLabel;
    private JComboBox<String> roundBox, yearBox;
    public static String dataX[],  roundString[], yearString[];
    public static String buyData[][];
    public static String buyDataraw[][];
    private static String moneyStr="",paystr="";
    public static String rewardData[][] = new String[100][100];
    public static String Textdata = "", showdate = "";
    private static int relocate = 0;
    private static long millis = System.currentTimeMillis();
    private static boolean buttoncounter = false;
    private static boolean pidcounter = false;
    public Report() {
        countDay();
        setData();
        ReportGui();
    }
    public void table(){
        Object columns[] = { "Personal ID", "Round", "Lottery", "Amount", "Reward" };
        Object rows[][] = new Object[getrow()][getcolomn()];
        Object[][] objectArray = new Object[relocate][];
        for (int i = 0; i < relocate; i++) {
            objectArray[i] = rewardData[i];
            rows[i] = objectArray[i];
        }
        TableModel model = new DefaultTableModel(rows, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableXD = new JTable(model);
        JTableHeader header = tableXD.getTableHeader();
        header.setBackground(new Color(20, 47, 67));
        header.setFont(mainfFont);
        header.setForeground(new Color(245, 245, 245));
        tableXD.setGridColor(new Color(20, 47, 67));
        tableXD.setFont(textFont);
        tableXD.setRowHeight(30);
        scroll = new JScrollPane(tableXD);
    }
    public void ReportGui(){
        table();
        labelText = new JLabel();
        labelText.setFont(titleFont);
        labelText.setText("Check Lottery");
        labelText.setHorizontalAlignment(JLabel.CENTER);
        labelText.setVerticalAlignment(JLabel.CENTER);

        psnLabel = new JLabel("Personal ID");
        psnLabel.setFont(mainfFont);
        psnField = new JTextField(10);
        psnField.setFont(mainfFont);

        dateLabel = new JLabel("CurrentDate");
        dateLabel.setFont(mainfFont);
        dateField = new JTextField(10);
        dateField.setEnabled(false);
        dateField.setText(showdate);
        dateField.setFont(mainfFont);

        yearLabel = new JLabel("Year");
        yearLabel.setFont(mainfFont);
        yearBox = new JComboBox<String>(yearString);
        yearBox.setMaximumRowCount(5);
        yearBox.setFont(mainfFont);

        roundLabel = new JLabel("Round");
        roundLabel.setFont(mainfFont);
        roundBox = new JComboBox<String>(roundString);
        roundBox.setMaximumRowCount(5);
        roundBox.setFont(mainfFont);

        chkButton = new JButton("Check");
        chkButton.addActionListener(this);
        chkButton.setEnabled(true);
        chkButton.setBackground(new Color(95, 141, 78));
        chkButton.setForeground(new Color(245, 245, 245));
        chkButton.setFont(mainfFont);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setEnabled(true);
        cancelButton.setBackground(new Color(221, 83, 83));
        cancelButton.setForeground(new Color(245, 245, 245));
        cancelButton.setFont(mainfFont);

        moneyLabel = new JLabel(moneyStr);
        moneyLabel.setFont(mainfFont);
        moneyLabel.setHorizontalAlignment(JLabel.CENTER);
        moneyLabel.setVerticalAlignment(JLabel.CENTER);
        moneyLabel.setForeground(new Color(25, 55, 109));

        payLabel = new JLabel(paystr);
        payLabel.setFont(mainfFont);
        payLabel.setHorizontalAlignment(JLabel.CENTER);
        payLabel.setVerticalAlignment(JLabel.CENTER);
        payLabel.setForeground(new Color(233,46,56));

        setTitle("Program Lottery");
        setSize(900, 830);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container c = getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints p = new GridBagConstraints();
        if (RIGHT_TO_LEFT) {
            c.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        if (shouldFill) {
            // natural height, maximum width
            p.fill = GridBagConstraints.HORIZONTAL;
        }
        p.fill = GridBagConstraints.HORIZONTAL;
        p.gridx = 0;
        p.gridy = 0;
        p.gridwidth = 2;
        c.add(labelText, p);
        if (shouldWeightX) {
            p.weightx = 0.5;
        }
        p.fill = GridBagConstraints.HORIZONTAL;
        p.gridx = 0;
        p.gridy = 1;
        p.gridwidth = 1;
        c.add(dateLabel, p);
        p.fill = GridBagConstraints.HORIZONTAL;
        p.gridx = 1;
        p.gridy = 1;
        p.gridwidth = 1;
        c.add(dateField, p);

        p.fill = GridBagConstraints.HORIZONTAL;
        p.gridx = 0;
        p.gridy = 2;
        p.gridwidth = 1;
        c.add(psnLabel, p);
        p.fill = GridBagConstraints.HORIZONTAL;
        p.gridx = 1;
        p.gridy = 2;
        p.gridwidth = 1;
        c.add(psnField, p);
        
        p.fill = GridBagConstraints.HORIZONTAL;
        p.gridx = 0;
        p.gridy = 3;
        p.gridwidth = 1;
        c.add(roundLabel, p);
        p.fill = GridBagConstraints.HORIZONTAL;
        p.gridx = 1;
        p.gridy = 3;
        p.gridwidth = 1;
        c.add(roundBox, p);
        
        p.fill = GridBagConstraints.HORIZONTAL;
        p.gridx = 0;
        p.gridy = 4;
        p.gridwidth = 1;
        c.add(yearLabel, p);
        p.fill = GridBagConstraints.HORIZONTAL;
        p.gridx = 1;
        p.gridy = 4;
        p.gridwidth = 1;
        c.add(yearBox, p);

        p.fill = GridBagConstraints.HORIZONTAL;
        p.gridx = 0;
        p.gridy = 5;
        p.gridwidth = 1;
        c.add(chkButton, p);
        p.fill = GridBagConstraints.HORIZONTAL;
        p.gridx = 1;
        p.gridy = 5;
        p.gridwidth = 1;
        c.add(cancelButton, p);
        
        p.fill = GridBagConstraints.HORIZONTAL;
        p.ipady = 40;
        p.gridx = 0;
        p.gridy = 6;
        p.gridwidth = 2;
        c.add(scroll, p);

        p.fill = GridBagConstraints.HORIZONTAL;
        p.ipady = 40;
        p.gridx = 0;
        p.gridy = 7;
        p.gridwidth = 1;
        c.add(moneyLabel, p);
        p.fill = GridBagConstraints.HORIZONTAL;
        p.ipady = 40;
        p.gridx = 1;
        p.gridy = 7;
        p.gridwidth = 1;
        c.add(payLabel, p);
        // pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        Report report = new Report();
    }

    public void actionPerformed(ActionEvent event){
        String psn = psnField.getText();
        boolean flag = true, fg = false;
        if (event.getSource() == chkButton) {
            buttoncounter = true;
            if (psn.equals("") == true) {
                //JOptionPane.showMessageDialog(null, "Enter Personal ID", "Alert", JOptionPane.INFORMATION_MESSAGE);
                pidcounter = false;
                setData();
                //(new Report()).validate();
            } else if (psn.equals("") == false) {
                for (int a = 0; a < psn.length(); a++) {
                    if (a == 0 && psn.charAt(a) == '-') {
                        continue;
                    }
                    if (!Character.isDigit(psn.charAt(a))) {
                        flag = false;
                        pidcounter = false;
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
                        pidcounter = false;
                    } else {
                        //check ID
                        pidcounter = true;
                        setData();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, psn + " is not Personal ID. Try again.", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                    psnField.setText("");
                    flag = true;
                    pidcounter = false;
                }
            }
            buttoncounter = false;
        } else if (event.getSource() == cancelButton) {
            clearText();
        }
    }

    public void clearText() {
        psnField.setText("");
        yearBox.setSelectedIndex(0);
        roundBox.setSelectedIndex(0);
        moneyLabel.setText("Money : 0 Bath");
        payLabel.setText("Pay : 0 Bath");
    }

    public void countDay() {
        java.util.Date date = new java.util.Date(millis);
        // System.out.println(date);
        String currentdate = "";
        String month, day, year;
        currentdate = date.toString();
        month = currentdate.substring(4, 7);
        day = currentdate.substring(8, 10);
        year = currentdate.substring(24, 28);
        // System.out.println(day + " " + month + " " + year);
        currentdate = date.toString();
        month = currentdate.substring(4, 7);
        day = currentdate.substring(8, 10);
        year = currentdate.substring(24, 28);
        showdate += day + " " + month + " " + year;
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

    public void setData() {
        int num = 0;
        String str = "";
        for(int h = 0; h < relocate; h++){
            for(int j = 0; j < 6; j++){
                    if(rewardData[h][j]!=null){
                        rewardData[h][j] = null;
                    }
            }
        }
        relocate = 0;
        try {
            Scanner scan = new Scanner(new FileReader("BuyData.dat"));
            while (scan.hasNext()) {
                str += scan.nextLine() + "|";
                num++;
            }

            dataX = str.split("\\|");
            
            buyDataraw = new String[dataX.length][];
            if(pidcounter == false){
                buyData = new String[buyDataraw.length][];
                for (int i = 0; i < dataX.length; i++) {
                    buyData[i] = dataX[i].split(",");
                }
            }else{
                int t=0;
                int TT=0;
                String pidstr = psnField.getText();
                System.out.println(pidstr);
                for (int i = 0; i < dataX.length; i++) {
                    buyDataraw[i] = dataX[i].split(",");
                }
                for(int mk = 0; mk < buyDataraw.length; mk++){
                    for(int mk1 = 0; mk1 < buyDataraw[mk].length; mk1++){
                        System.out.println(buyDataraw[mk][mk1]);
                    }
                }
                for (int i = 0; i < buyDataraw.length; i++) {
                    if(buyDataraw[i][0].equals(pidstr)){
                        TT++;
                    }
                }
                buyData = new String[TT][];
                System.out.println("========================");
                for (int i = 0; i < buyDataraw.length; i++) {
                    if(buyDataraw[i][0].equals(pidstr)){
                        buyData[t] = buyDataraw[i];
                        t++;
                        // buyData[i] = dataX[i].split(",");
                    }
                    //for(int j = 0; j < buyDataraw[i].length; j++){
                        //}
                }
                System.out.println("========================Afterrrr");
                //System.out.println(buyData[0][0]);
                for(int mk = 0; mk < t; mk++){
                    for(int mk1 = 0; mk1 < buyData[mk].length; mk1++){
                        System.out.println(buyData[mk][mk1]);
                    }
                }
            }
                


            for (int i = 0; i < buyData.length; i++) {
                for (int j = 0; j < buyData[i].length; j++) {
                    System.out.print(buyData[i][j]+" ");
                }
                System.out.println();
            }

            int locate = 0;
            
            String arrayPrizeraw[] = new String[100];
            String arrayPrize[][] = new String[100][173];
            Scanner scanprize = new Scanner(new FileReader("Prize.dat"));
            while (scanprize.hasNext()) {
                arrayPrizeraw[locate] = scanprize.nextLine();
                // System.out.println(arrayPrizeraw[locate]);
                // System.out.println(arrayPrizeraw.length);
                locate++;
            }
            for (int i = 0; i < locate; i++) {
                arrayPrize[i] = arrayPrizeraw[i].split(",");
            }
            for (int i = 0; i < locate; i++) {
                for (int j = 0; j < arrayPrize[i].length; j++) {
                    System.out.print(arrayPrize[i][j]);
                }
                System.out.println();
            }

            int reward;
            int money = 0;
            int pay = 0;
            String dateget = "";
            String strreward = "";
                for (int numcus = 0; numcus < buyData.length; numcus++) {

                    // check
                    boolean logic = true;
                    // System.out.println(arrayPrize[0][1]);
                    // System.out.println(arrayPrize[0][2]);
                    // System.out.println(arrayPrize[0][3]);
                    for (int i = 0; i < locate; i++) {
                        logic = true;
                        reward = 0;

                        //System.out.println(arrayPrize[i][0]);
                        if(buttoncounter == true){
                            dateget = (String) roundBox.getSelectedItem() + " " + (String) yearBox.getSelectedItem();
                            //System.out.println("Button is press"+dateget);
                        }else{
                            dateget = arrayPrize[i][0];
                            //System.out.println("Button is not press"+dateget);
                        }
                        //dateget = "01 Jan 2023";
                    

                        System.out.println(buyData[numcus][1]+" = "+dateget);
                        if (buyData[numcus][1].equals(dateget)) {
                            if (arrayPrize[i][0].equals(dateget)) {
                                System.out.println("===========");

                                money += Integer.parseInt(buyData[numcus][3]) * 80;
                                if (arrayPrize[i][1].equals(buyData[numcus][2])) {
                                    reward += 6000000;
                                    logic = false;
                                }
                                for (int j = 9; j < 14; j++) {
                                    if (arrayPrize[i][j].equals(buyData[numcus][2])) {
                                        reward += 200000;
                                        logic = false;
                                    }
                                }
                                if (arrayPrize[i][2].equals(buyData[numcus][2]) || arrayPrize[i][3].equals(buyData[numcus][2])) {
                                    reward += 100000;
                                    logic = false;
                                }
                                for (int j = 14; j < 24; j++) {
                                    if (arrayPrize[i][j].equals(buyData[numcus][2])) {
                                        reward += 80000;
                                        logic = false;
                                    }
                                }
                                for (int j = 24; j < 74; j++) {
                                    if (arrayPrize[i][j].equals(buyData[numcus][2])) {
                                        reward += 40000;
                                        logic = false;
                                    }
                                }
                                for (int j = 74; j < 174; j++) {
                                    if (arrayPrize[i][j].equals(buyData[numcus][2])) {
                                        reward += 20000;
                                        logic = false;
                                    }
                                }
                                String for3 = buyData[numcus][2].substring(0, 3);
                                String back3 = buyData[numcus][2].substring(3, 6);
                                String back2 = buyData[numcus][2].substring(4, 6);
                                // System.out.println(for3);
                                // System.out.println(back3);
                                // System.out.println(back2);
                                if (arrayPrize[i][4].equals(for3) || arrayPrize[i][5].equals(for3)
                                        || arrayPrize[i][6].equals(back3) || arrayPrize[i][7].equals(back3)) {
                                    reward += 4000;
                                    logic = false;
                                } else {
                                    if (arrayPrize[i][8].equals(back2)) {
                                        reward += 2000;
                                        logic = false;
                                    }
                                }
                                if (logic == false && buyData[numcus][1].equals(dateget) == true) {
                                    int rewardint = Integer.parseInt(buyData[numcus][3]);
                                    // System.out.println(buyData[numcus][0] + "+" + buyData[numcus][1] + "+" + buyData[numcus][2]
                                    //         + "+" + buyData[numcus][3]);
                                    rewardData[relocate][0] = buyData[numcus][0];
                                    rewardData[relocate][1] = buyData[numcus][1];
                                    rewardData[relocate][2] = buyData[numcus][2];
                                    rewardData[relocate][3] = buyData[numcus][3];
                                    rewardData[relocate][4] = Integer.toString(rewardint * reward);
                                    // System.out.println(rewardData[relocate][0] + "+" + rewardData[relocate][1] + "+"
                                    //         + rewardData[relocate][2] + "+" + rewardData[relocate][3]);
                                    relocate++;
                                    pay += rewardint * reward;
                                    strreward += rewardint * reward + " ";
                                    System.out.println(rewardint * reward);
                                }
                        }
                        }
                    }
                    //System.out.println(logic);
                }
                //moneyLabel.setText(Integer.toString(money)+" Bath");
                //payLabel.setText("4000");
                //moneyLabel.setText("5000");
                moneyStr += "Money : " + money + " Bath";
                paystr += "Pay : " + pay + " Bath";
                System.out.println("Money : "+money);
                System.out.println("Pay : "+pay);
                System.out.println("Date : "+dateget);
                System.out.println("Reward : "+strreward);

                for(int h = 0; h < relocate; h++){
                    for(int k = 0; k < rewardData[0].length; k++){
                        if(rewardData[h][k]!=null){
                            System.out.print(rewardData[h][k]+" ");
                        }
                    }
                    System.out.println();
                }

                // System.out.println(rewardData[relocate][0] + "+" + rewardData[relocate][1] + "+"
                //         + rewardData[relocate][2] + "+" + rewardData[relocate][3]);

                for (int i = 0; i < rewardData.length; i++) {
                    for (int j = 0; j < rewardData[i].length; j++) {
                        if (j != rewardData[i].length) {
                            Textdata += rewardData[i][j] + "\t\t";
                        } else {
                            Textdata += rewardData[i][j];
                        }
                    }
                    Textdata += "\n";
                }
                //System.out.println(rewardData[0][0] + "report1");
                ReportID obj1 = new ReportID();
                if((buttoncounter == true) && (pidcounter == true)){
                    obj1.Show(psnField.getText(),dateget);
                }else if((buttoncounter == true)){
                    obj1.Show("",dateget);
                }
                //ReportGui();
                buttoncounter = false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getrow() {
        return relocate;
    }

    public static int getcolomn() {
        return buyData[0].length;
    }

}
