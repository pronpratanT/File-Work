import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class Show extends JFrame {
    JTextArea trea;
    JScrollPane scroll;
    JLabel labelText;
    JTable tableXD;
    private final Font mainfFont = new Font("Arial Rounded MT", Font.BOLD, 22);
    private final Font titleFont = new Font("Arial Rounded MT", Font.BOLD, 48);
    private final Font textFont = new Font("Arial Rounded MT", Font.BOLD, 20);
    public static String dataX[];
    public static String buyData[][];
    public static String Textdata = "";
    private static String pid = "";
    private static int count = 0;
    private static int countrow = 0;

    public Show(String psn) {
        setShow(psn);
        setData();
        Object columns[] = { "Personal ID", "Round", "Lottery", "Amount" };
        Object rows[][] = new Object[getrow()][getcolomn()];
        Object[][] objectArray = new Object[buyData.length][];
        for (int i = 0; i < buyData.length; i++) {
            if (buyData[i][0].equals(pid)) {
                objectArray[i] = buyData[i];
                rows[count] = objectArray[i];
                count ++;
            }
        }
        // System.out.println(count);
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
        labelText = new JLabel();
        labelText.setFont(titleFont);
        labelText.setText("Purchase History");
        labelText.setHorizontalAlignment(JLabel.CENTER);
        labelText.setVerticalAlignment(JLabel.CENTER);

        setTitle("Program Lottery");
        setSize(900, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(labelText, BorderLayout.NORTH);
        c.add(scroll, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        Show show = new Show(pid);
    }

    public void setShow(String psn) {
        pid = psn;
    }

    public static String getTextdata() {
        return Textdata;
    }

    public static void setData() {
        int num = 0;
        String str = "";
        try {
            Scanner scan = new Scanner(new FileReader("BuyData.dat"));
            while (scan.hasNext()) {
                str += scan.nextLine() + "|";
                num++;
            }
            dataX = str.split("\\|");
            buyData = new String[dataX.length][];
            for (int i = 0; i < dataX.length; i++) {
                buyData[i] = dataX[i].split(",");
            }
            for (int i = 0; i < buyData.length; i++) {
                if (buyData[i][0].equals(pid)) {
                    System.out.println(buyData[i][0]+" == "+pid);
                    countrow++;
                }
                for (int j = 0; j < buyData[i].length; j++) {
                    if (buyData[i][0].equals(pid)) {
                        if (j != buyData[i].length - 1) {
                            Textdata += buyData[i][j] + "\t\t";
                            // System.out.println(buyData[i][j]);
                        } else {
                            Textdata += buyData[i][j];
                            // System.out.println(Textdata + " 2");
                        }
                        // count++;
                        // System.out.println("true");

                    } else {
                        // System.out.println("false");
                    }
                }
                Textdata += "\n";
            }
            System.out.println(Textdata);
            // System.out.println(buyData[0][0]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getrow() {
        // System.out.println(countrow);
        return countrow;
    }

    public static int getcolomn() {
        return buyData[0].length;
    }
}
