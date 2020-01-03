/*
 * Created by JFormDesigner on Tue Dec 17 12:53:56 CET 2019
 */

package fr.utt.lo02.xfmv.jest.vue.graphicInterface;

import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.BlockingQueue;
import javax.swing.*;

import fr.utt.lo02.xfmv.jest.controller.MenuController;
import fr.utt.lo02.xfmv.jest.controller.Partie;
import fr.utt.lo02.xfmv.jest.vue.Message;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class Menu extends JPanel {



    public Menu() {
        initComponents();
        MenuController menuController = new MenuController(this);
        this.setVisible(true);
    }

    private void rulesButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        title = new JLabel();
        playButton = new JButton();
        rulesButton = new JButton();
        quitButton = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
        javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax
        . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
        .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt
        . Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans.
        PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .
        equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- title ----
        title.setText("Jest");
        add(title, "cell 0 0,alignx center,growx 0");

        //---- playButton ----
        playButton.setText("Jouer");
        add(playButton, "cell 0 2,alignx center,growx 0");

        //---- rulesButton ----
        rulesButton.setText("R\u00e8gles");
        add(rulesButton, "cell 0 3,alignx center,growx 0");

        //---- quitButton ----
        quitButton.setText("Quitter");
        add(quitButton, "cell 0 4,alignx center,growx 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel title;
    private JButton playButton;

    public JButton getPlayButton() {
        return playButton;
    }

    public void setPlayButton(JButton playButton) {
        this.playButton = playButton;
    }

    public JButton getRulesButton() {
        return rulesButton;
    }

    public void setRulesButton(JButton rulesButton) {
        this.rulesButton = rulesButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public void setQuitButton(JButton quitButton) {
        this.quitButton = quitButton;
    }

    private JButton rulesButton;
    private JButton quitButton;

    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
