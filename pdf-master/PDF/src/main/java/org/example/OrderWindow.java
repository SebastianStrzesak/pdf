package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderWindow {
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton dodajDoListyButton;
    private JTextField textField4;
    private JTextField textField5;
    private JRadioButton gotówkaRadioButton;
    private JRadioButton kartaRadioButton;
    private JRadioButton przelewRadioButton;
    private JButton generujPlikPDFDoButton;
    private JTextArea textArea1;
    private javax.swing.JPanel mainPanel;
    //OTHER
    static double suma = 0;
    static double zaliczka = 0;
    static String metodaPlatnosci;
    int i = 0;

    public static String getMetodaPlatnosci() {
        return metodaPlatnosci;
    }

    public static double getSuma() {
        return suma;
    }

    public static double getZaliczka() {
        return zaliczka;
    }

    ArrayList<OneRow> Rows = new ArrayList<>();

    public ArrayList<OneRow> getRows() {
        return Rows;
    }

    public OrderWindow() {
        JFrame frame = new JFrame("Order Window");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        textField4.setText(suma+"");


        dodajDoListyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = textField1.getText();
                String b = textField2.getText();
                try {
                    double doubleA = Double.parseDouble(a);
                    double doubleB = Double.parseDouble(b);

                    if (doubleA < 0 || doubleB < 0) {
                        JOptionPane.showMessageDialog(mainPanel, "Podaj dodatnie wartości!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Wprowadzono nieprawidłową wartość!");
                }
                suma+=(Double.parseDouble(a)*Double.parseDouble(b));
                textField4.setText(suma+"");
                String name = (String) comboBox1.getSelectedItem();
                Rows.add(new OneRow(name, Double.parseDouble(a), Double.parseDouble(b)));
                textArea1.append(Rows.get(i++).toString());
                textField1.setText("");
                textField2.setText("");
            }
        });
        generujPlikPDFDoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                zaliczka = Double.parseDouble(textField5.getText());
                if (gotówkaRadioButton.isSelected()) metodaPlatnosci="Gotowka";
                else if (kartaRadioButton.isSelected()) metodaPlatnosci = "Karta";
                else metodaPlatnosci = "Przelew 14 dni";

                PdfGenerator pdf = new PdfGenerator();
                pdf.generatePdf(Rows);
            }
        });
    }
}
