/*
 * Created by JFormDesigner on Mon Jan 06 20:57:34 CET 2020
 */

package fr.utt.lo02.xfmv.jest.vue.graphicInterface;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class Results extends JPanel {
    public Results() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        label7 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label8 = new JLabel();
        label14 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label15 = new JLabel();
        label9 = new JLabel();
        label20 = new JLabel();
        label19 = new JLabel();
        label18 = new JLabel();
        label17 = new JLabel();
        label16 = new JLabel();
        label10 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setMaximumSize(new Dimension(800, 1200));
        setMinimumSize(new Dimension(800, 700));
        setPreferredSize(new Dimension(800, 820));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
        . EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax
        . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,
        12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans
        . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .
        getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "0[160,fill]0" +
            "[160,fill]0" +
            "[160,fill]0" +
            "[160,fill]0" +
            "[160,fill]0" +
            "[160,fill]",
            // rows
            "0[]para" +
            "[]" +
            "[]para" +
            "[]" +
            "[]para" +
            "[]" +
            "[]30" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/Results.png")));
        add(label1, "cell 0 0 5 1");

        //---- label7 ----
        label7.setText("Joueur 1 - 10 Points");
        label7.setFont(new Font("Ubuntu Light", Font.BOLD, 14));
        add(label7, "cell 0 1 5 1,alignx center,growx 0");

        //---- label2 ----
        label2.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label2, "cell 0 2,alignx right,growx 0");

        //---- label3 ----
        label3.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label3, "cell 1 2,alignx center,growx 0");

        //---- label4 ----
        label4.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label4, "cell 2 2,alignx center,growx 0");

        //---- label5 ----
        label5.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label5, "cell 3 2,alignx center,growx 0");

        //---- label6 ----
        label6.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label6, "cell 4 2,alignx left,growx 0");

        //---- label8 ----
        label8.setText("Joueur 1 - 10 Points");
        label8.setFont(new Font("Ubuntu Light", Font.BOLD, 14));
        add(label8, "cell 0 3 5 1,alignx center,growx 0");

        //---- label14 ----
        label14.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label14, "cell 0 4,alignx right,growx 0");

        //---- label11 ----
        label11.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label11, "cell 1 4,alignx center,growx 0");

        //---- label12 ----
        label12.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label12, "cell 2 4,alignx center,growx 0");

        //---- label13 ----
        label13.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label13, "cell 3 4,alignx center,growx 0");

        //---- label15 ----
        label15.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label15, "cell 4 4,alignx left,growx 0");

        //---- label9 ----
        label9.setText("Joueur 1 - 10 Points");
        label9.setFont(new Font("Ubuntu Light", Font.BOLD, 14));
        add(label9, "cell 0 5 5 1,alignx center,growx 0");

        //---- label20 ----
        label20.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label20, "cell 0 6,alignx right,growx 0");

        //---- label19 ----
        label19.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label19, "cell 1 6,alignx center,growx 0");

        //---- label18 ----
        label18.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label18, "cell 2 6,alignx center,growx 0");

        //---- label17 ----
        label17.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label17, "cell 3 6,alignx center,growx 0");

        //---- label16 ----
        label16.setIcon(new ImageIcon(getClass().getResource("/CardBack.png")));
        add(label16, "cell 4 6,alignx left,growx 0");

        //---- label10 ----
        label10.setText("Joueur 1 gagne la partie !");
        label10.setFont(new Font("Ubuntu Light", Font.BOLD, 22));
        add(label10, "cell 0 7 5 1,alignx center,growx 0");

        //---- button1 ----
        button1.setText("Retour au menu");
        button1.setFont(new Font("Ubuntu Light", Font.BOLD, 16));
        add(button1, "cell 1 8 3 1,align center top,grow 0 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JLabel label7;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label8;
    private JLabel label14;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label15;
    private JLabel label9;
    private JLabel label20;
    private JLabel label19;
    private JLabel label18;
    private JLabel label17;
    private JLabel label16;
    private JLabel label10;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
