package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Jefferson Rubio <jefferson.jrubio@gmail.com>
 */
public class MainFrame extends JFrame{
    
    private JPanel panelHeader, panelContent, panelFooter;
    private JButton btnLeftArrow, btnRightArrow, btnRefreshPage, btnHomePage, btnSetting;
    private JTextField txtUrl;
    private JLabel lblEstado;
    
    //Constructor
    public MainFrame(){
    
        //----Title Bar
        super("JBrowser");
        ImageIcon imgIcon = new ImageIcon ("src/images/browser_icon.png");
        this.setIconImage(imgIcon.getImage());

        
        //----Header
        //Main panel      
        panelHeader = new JPanel();
        panelHeader.setLayout(new FlowLayout(FlowLayout.LEADING));
        panelHeader.setBackground(new Color(179,227,3));
        
        //Left arrow
        btnLeftArrow = new JButton();
        btnLeftArrow.setIcon(new ImageIcon ("src/images/left_arrow.png"));
        btnLeftArrow.setPreferredSize(new Dimension(50,30));
        btnLeftArrow.setContentAreaFilled(false);
        btnLeftArrow.setBorderPainted(false);
        btnLeftArrow.setFocusPainted(false);
        
        //Right arrow
        btnRightArrow = new JButton(); 
        btnRightArrow.setIcon(new ImageIcon ("src/images/right_arrow.png"));
        btnRightArrow.setPreferredSize(new Dimension(50,30));
        btnRightArrow.setContentAreaFilled(false);
        btnRightArrow.setBorderPainted(false);
        btnRightArrow.setFocusPainted(false);
      
        //URL box
        txtUrl = new JTextField(45);
        txtUrl.setPreferredSize(new Dimension(80,30));
        Font fontUrl = new Font("Verdana", Font.BOLD, 15);
        txtUrl.setFont(fontUrl);
       
        //Refresh page
        btnRefreshPage = new JButton(); 
        btnRefreshPage.setIcon(new ImageIcon ("src/images/refresh.png"));
        btnRefreshPage.setContentAreaFilled(false);
        btnRefreshPage.setBorderPainted(false);
        btnRefreshPage.setFocusPainted(false);
        
        //Home page
        btnHomePage = new JButton(); 
        btnHomePage.setIcon(new ImageIcon ("src/images/home.png"));
        btnHomePage.setToolTipText("Página de inicio de JBrowser");
        btnHomePage.setContentAreaFilled(false);
        btnHomePage.setBorderPainted(false);
        btnHomePage.setFocusPainted(false);
        
        //Settings
        btnSetting = new JButton(); 
        btnSetting.setLayout( new FlowLayout(FlowLayout.RIGHT));
        btnSetting.setIcon(new ImageIcon ("src/images/settings.png"));
        btnSetting.setContentAreaFilled(false);
        btnSetting.setBorderPainted(false);
        
        //Adds components
        panelHeader.add(btnLeftArrow);
        panelHeader.add(btnRightArrow);
        panelHeader.add(txtUrl);
        panelHeader.add(btnRefreshPage);
        panelHeader.add(btnHomePage);
        panelHeader.add(btnSetting);
        this.add(panelHeader, BorderLayout.NORTH);
        
        
        //----Content
        //Main panel
        panelContent = new JPanel();
        panelContent.setLayout( new FlowLayout(FlowLayout.LEFT));
        panelContent.setBackground(new Color(251,251,176));
        
//        //Tabs
//        JPanel panel = new JPanel();
//        panel.setLayout( new FlowLayout(FlowLayout.RIGHT));
//        panel.setBackground(new Color(79,27,3));
//        panel.setPreferredSize(new Dimension(1074, 768));
//        
//        JTabbedPane page = new JTabbedPane();
//        page.addTab("Facebook", null, panel, "Does nothing");
//        page.addTab("Gmail", null, new JPanel(), "Does nothing");
//        
//        //Adds components
//        panelContent.add(page);
        this.add(panelContent, BorderLayout.CENTER);
        
        
        //----Footer
        //Main panel
        panelFooter = new JPanel();
        panelFooter.setLayout( new FlowLayout(FlowLayout.RIGHT));
        panelFooter.setBackground(new Color(179,227,3));
        
        //Estado
        lblEstado = new JLabel("Estado : ___________");
        
        
        //Adds components
        panelFooter.add(lblEstado);
        this.add(panelFooter, BorderLayout.SOUTH);
        
        
        //----Settings
        this.setSize(1074, 768);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    //----Getters and Setters
    
    public JButton getBtnLeftArrow() {
        return btnLeftArrow;
    }

    public void setBtnLeftArrow(JButton btnLeftArrow) {
        this.btnLeftArrow = btnLeftArrow;
    }

    public JButton getBtnRightArrow() {
        return btnRightArrow;
    }

    public void setBtnRightArrow(JButton btnRightArrow) {
        this.btnRightArrow = btnRightArrow;
    }

    public JButton getBtnRefreshPage() {
        return btnRefreshPage;
    }

    public void setBtnRefreshPage(JButton btnRefreshPage) {
        this.btnRefreshPage = btnRefreshPage;
    }

    public JButton getBtnHomePage() {
        return btnHomePage;
    }

    public void setBtnHomePage(JButton btnHomePage) {
        this.btnHomePage = btnHomePage;
    }

    public JButton getBtnSetting() {
        return btnSetting;
    }

    public void setBtnSetting(JButton btnSetting) {
        this.btnSetting = btnSetting;
    }

    public JTextField getTxtUrl() {
        return txtUrl;
    }

    public void setTxtUrl(JTextField txtUrl) {
        this.txtUrl = txtUrl;
    }

}
