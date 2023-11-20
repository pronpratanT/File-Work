import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  
import java.util.Date;
import java.util.*;
import java.io.*;

public class HomeForm extends JFrame implements ActionListener{
    private Container home;
    private final Font mainfFont = new Font("Arial Rounded MT", Font.BOLD, 18);
    private final Font titleFont = new Font("Arial Rounded MT", Font.BOLD, 28);
    private JButton checkForm, buyForm, reportForm;
    private JLabel hometitle;
    private static String prize="";
    private static String prizeforward1="";
    private static String prizeforward2="";
    private static String prizebackward1="";
    private static String prizebackward2="";

    public HomeForm(){
        super("Program Lottery");
        LotteryRandom();
        homeGui();
    }

    public void homeGui(){
        home = getContentPane();
        home.setBackground(new Color(245, 245, 245));
        home.setLayout(new GridLayout(4, 1, 6, 6));
        
        hometitle = new JLabel("Lottery");
        hometitle.setFont(titleFont);
        hometitle.setHorizontalAlignment(JLabel.CENTER);
        hometitle.setVerticalAlignment(JLabel.CENTER);
        home.add(hometitle);

        checkForm = new JButton("Check Lottery");
        checkForm.addActionListener(this);
        checkForm.setBackground(new Color(20, 108, 148));
        checkForm.setForeground(new Color(245, 245, 245));
        checkForm.setFont(mainfFont);
        home.add(checkForm);

        buyForm = new JButton("Buy Lottery");
        buyForm.addActionListener(this);
        buyForm.setBackground(new Color(20, 108, 148));
        buyForm.setForeground(new Color(245, 245, 245));
        buyForm.setFont(mainfFont);
        home.add(buyForm);
        
        reportForm = new JButton("Report");
        reportForm.addActionListener(this);
        reportForm.setBackground(new Color(20, 108, 148));
        reportForm.setForeground(new Color(245, 245, 245));
        reportForm.setFont(mainfFont);
        home.add(reportForm);

        setSize(550,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() ==  checkForm){
            CheckForm check = new CheckForm();
        }else if(event.getSource() == buyForm){
            //home.setVisible(false);
            BuyForm buy = new BuyForm();
        }else if(event.getSource() == reportForm){
            Report report = new Report();
        }
    }

    public void LotteryRandom(){
        java.util.Date date = new java.util.Date();
        String currentdate = "";
        String month, day, year, showdate = "";
        currentdate = date.toString();
        month = currentdate.substring(4, 7);
        day = currentdate.substring(8, 10);
        year = currentdate.substring(24, 28);
        System.out.println(month);
        System.out.println(day);
        System.out.println(year);
        String datestr = day +" "+ month +" "+ year;
        System.out.println(datestr);
        int daycheck=Integer.parseInt(day);
        System.out.println(daycheck);
        String datestrsave="";
        boolean logic = true;
        if(daycheck >=1 && daycheck <=15){
            datestrsave = "01" +" "+ month +" "+ year;
        }
        if(daycheck >=16 && daycheck <=31){
            datestrsave = "16" +" "+ month +" "+ year;
        }
        try {
            Scanner scan = new Scanner(new FileReader("Prize.dat"));
            String checkdate="";
            while (scan.hasNext()) {
                checkdate = scan.nextLine();
                System.out.println(datestrsave+"date now");
                System.out.println(checkdate.substring(0, 11)+"date check file");
                if(checkdate.substring(0, 11).equals(datestrsave)){
                    logic = false;
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        if (logic) {
            try {
                FileWriter buydata = new FileWriter("Prize.dat", true);
                BufferedWriter bd = new BufferedWriter(buydata);
                PrintWriter pw = new PrintWriter(bd);
                pw.print(datestrsave);
                pw.print(",");
                String temp="";
                int lec6tua = Integer.parseInt(ran(6));
                int lec6tuahigh = lec6tua + 1;
                int lec6tualow = lec6tua - 1;
                String lec6tuastr="0";
                String lec6tuahighstr="0";
                String lec6tualowstr="0";
                String lec6tuastr2="0";
                String lec6tuahighstr2="0";
                String lec6tualowstr2="0";
                temp = Integer.toString(lec6tua);
                if(temp.length() < 6){
                    lec6tuastr += temp;
                    if(temp.length() < 6){
                        lec6tuastr2 += lec6tuastr;
                    }else{
                        lec6tuastr2 = lec6tuastr;
                    }
                }else{
                    lec6tuastr2 = temp;
                }
                temp = Integer.toString(lec6tuahigh);
                if(temp.length() < 6){
                    lec6tuahighstr += temp;
                    if(temp.length() < 6){
                        lec6tuahighstr2 += lec6tuahighstr;
                    }else{
                        lec6tuahighstr2 = lec6tuahighstr;
                    }
                }else{
                    lec6tuahighstr2 = temp;
                }
                temp = Integer.toString(lec6tualow);
                if(temp.length() < 6){
                    lec6tualowstr += temp;
                    if(temp.length() < 6){
                        lec6tualowstr2 += lec6tualowstr;
                    }else{
                        lec6tualowstr2 = lec6tualowstr;
                    }
                }else{
                    lec6tualowstr2 = temp;
                }
                pw.print(lec6tuastr2);
                pw.print(",");
                pw.print(lec6tuahighstr2);
                pw.print(",");
                pw.print(lec6tualowstr2);
                pw.print(",");
                pw.print(ran(3));
                pw.print(",");
                pw.print(ran(3));
                pw.print(",");
                pw.print(ran(3));
                pw.print(",");
                pw.print(ran(3));
                pw.print(",");
                pw.print(ran(2));
                for(int j = 0; j < 165; j++){
                    pw.print(",");
                    pw.print(ran(6));
                }
                //pw.print(",");
                //System.out.println(countBox.getSelectedItem());
                pw.println();
                pw.close();
                //JOptionPane.showMessageDialog(null, "Buy Success");
            } catch (IOException ioe) {
                System.out.println("Error");
            }
        }

        // try {
        //     String arrayPrizeraw[] = new String[173];
        //     String arrayPrize[][] = new String[173][173];
        //     int locate = 0;
        //     Scanner scan = new Scanner(new FileReader("Prize.dat"));



        //     while (scan.hasNext()) {
        //         arrayPrizeraw[locate] = scan.nextLine();
        //         locate ++;
        //     }
        //     for (int i = 0; i < arrayPrizeraw.length; i++) {
        //         arrayPrize[i] = arrayPrizeraw[i].split(",");
        //     }
        //     for(int i = 0; i < arrayPrize.length; i++){
        //         for(int j = 0; j < arrayPrize[i].length; j++){
        //             System.out.print(arrayPrize[i][j]);
        //         }
        //         System.out.println("");
        //     }
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
            // // check
            // for (int i = 0; i < lotData.length; i++) {
            //     if (lotData[i][2].equals(lot)) {
            //         reward = 6000000;
            //         rewardLabel.setText("Reward : " + reward + " Baht");
            //         status = "Check Success";
            //         System.out.println(rewardString);
            //     } else {
            //         status = "Check Success";
            //         rewardLabel.setText("Reward : No Reward");
            //     }
            // }
        // } catch (Exception e) {
        //     System.out.println("Error");
        // }
    }

    public static void main(String[] args) {
        HomeForm home = new HomeForm();
    }

    public static String ran(int loop){
        String temp = "";
        for(int i = 0; i < loop; i++){
            temp += Integer.toString((int)(Math.random() * 10));
        }
        return temp;
    }
}
