/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jefferson
 */
public class MainFrame extends JFrame{
    
    //Constructor
    public MainFrame(){
    
        //----Settings
        this.setLayout(new BorderLayout());
        ImageIcon imgIcon = new ImageIcon ("src/images/browser_icon.png");
        this.setIconImage(imgIcon.getImage());
        this.setTitle("JBrowser");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //----Header
        
        //main panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.CYAN);
        
        //right arrow
        JButton leftArrow = new JButton();
        leftArrow.setIcon(new ImageIcon ("src/images/left_arrow.png"));
        leftArrow.setContentAreaFilled(false);
        
        //right arrow
        JButton rightArrow = new JButton();
        rightArrow.setIcon(new ImageIcon ("src/images/right_arrow.png"));
        rightArrow.setContentAreaFilled(false);
//        
//      //URL box
//        JTextField url = new JTextField("hola que hace");
//        url.setBounds(100, 10, 30, 20);
//        
        headerPanel.add(leftArrow);
        headerPanel.add(rightArrow);
//        headerPanel.add(url);
        this.add(headerPanel, BorderLayout.NORTH);
        
        //pack();
    }
}
