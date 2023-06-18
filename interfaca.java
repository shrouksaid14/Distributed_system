package rmi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author dell
 */
public class interfaca extends JFrame implements ActionListener {

    JRadioButton radiobutton1, radiobutton2, radiobutton3, radiobutton4, radiobutton5;
    JLabel question, title;
    JButton button, btn;

    interfaca() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("RMI");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        title = new JLabel("project distributed");
        title.setBounds(200, 50, 360, 50);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setOpaque(true);
        title.setForeground(Color.white);
        title.setBackground(Color.blue);
        title.setBorder(new EmptyBorder(0, 10, 0, 0));

        question = new JLabel("choose the method");
        question.setBounds(140, 120, 500, 50);
        question.setFont(new Font("Arial", Font.BOLD, 18));
        question.setBorder(new EmptyBorder(0, 10, 0, 0));

        btn = new JButton("result");
        btn.setBounds(250, 470, 250, 50);
        btn.setFocusable(false);
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setBackground(Color.red);
        btn.setForeground(Color.white);
        btn.addActionListener(this);

        radiobutton1 = new JRadioButton("Count");
        radiobutton1.setFocusable(false);
        radiobutton1.setFont(new Font("Arial", Font.BOLD, 14));
        radiobutton1.setBounds(320, 180, 300, 50);

        radiobutton2 = new JRadioButton("repeatedwords");
        radiobutton2.setFocusable(false);
        radiobutton2.setFont(new Font("Arial", Font.BOLD, 14));
        radiobutton2.setBounds(320, 230, 300, 50);

        radiobutton3 = new JRadioButton("longest");
        radiobutton3.setFocusable(false);
        radiobutton3.setFont(new Font("Arial", Font.BOLD, 14));
        radiobutton3.setBounds(320, 280, 300, 50);

        radiobutton4 = new JRadioButton("shortest");
        radiobutton4.setFocusable(false);
        radiobutton4.setFont(new Font("Arial", Font.BOLD, 14));
        radiobutton4.setBounds(320, 330, 300, 50);

        radiobutton5 = new JRadioButton("Repeat");
        radiobutton5.setFocusable(false);
        radiobutton5.setFont(new Font("Arial", Font.BOLD, 14));
        radiobutton5.setBounds(320, 380, 300, 50);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radiobutton1);
        bg.add(radiobutton2);
        bg.add(radiobutton3);
        bg.add(radiobutton4);
        bg.add(radiobutton5);

        this.add(title);
        this.add(question);
        this.add(radiobutton1);
        this.add(radiobutton2);
        this.add(radiobutton3);
        this.add(radiobutton4);
        this.add(radiobutton5);
        this.add(btn);

        this.setVisible(true);

    }

    int count = 44;
    String repeatedwords = "rrrrflk";
    String longest = "rrrrrrrrrrrrrrrrr";
    String shortest = "i";
    int Repeat = 9;

    @Override
    public void actionPerformed(ActionEvent evt) {

        if (radiobutton1.isSelected()) {
            JOptionPane.showMessageDialog(this, count);
        }

        if (radiobutton2.isSelected()) {
            JOptionPane.showMessageDialog(this, repeatedwords);
        }

        if (radiobutton3.isSelected()) {
            JOptionPane.showMessageDialog(this, longest);
        }

        if (radiobutton4.isSelected()) {
            JOptionPane.showMessageDialog(this, shortest);
        }

        if (radiobutton5.isSelected()) {
            JOptionPane.showMessageDialog(this, Repeat);
        }

    }
}