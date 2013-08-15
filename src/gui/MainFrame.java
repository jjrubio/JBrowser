package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;

/**
 *
 * @author Jefferson Rubio <jefferson.jrubio@gmail.com>
 */
public class MainFrame extends JFrame{
    
    private JPanel panelHeader, panelContent, panelFooter;
    private JButton btnLeftArrow, btnRightArrow, btnRefreshPage, btnHomePage, btnSetting;
    private JTextField txtUrl;
    private JLabel lblStatusTitle, lblStatusValue;
    private TabPanel panelTabs;
    
    //Constructor
    public MainFrame(){
    
        //----Title Bar
        
        super("JBrowser");
        ImageIcon imgIcon = new ImageIcon ("src/images/browser_icon.png");
        setIconImage(imgIcon.getImage());

        
        //----Header
        //Main panel      
        panelHeader = new JPanel();
        panelHeader.setLayout(new FlowLayout(FlowLayout.LEADING));
        panelHeader.setBackground(new Color(0,0,0));//179,227,3
        
        //Left arrow
        btnLeftArrow = new JButton();
        btnLeftArrow.setIcon(new ImageIcon ("src/images/left_arrow.png"));
        btnLeftArrow.setPreferredSize(new Dimension(50,30));
        btnLeftArrow.setContentAreaFilled(false);
        btnLeftArrow.setBorderPainted(false);
        btnLeftArrow.setFocusPainted(false);
        btnLeftArrow.setEnabled(false);
        btnLeftArrow.addMouseListener(new BorderButton());
        
        //Right arrow
        btnRightArrow = new JButton(); 
        btnRightArrow.setIcon(new ImageIcon ("src/images/right_arrow.png"));
        btnRightArrow.setPreferredSize(new Dimension(50,30));
        btnRightArrow.setContentAreaFilled(false);
        btnRightArrow.setBorderPainted(false);
        btnRightArrow.setFocusPainted(false);
        btnRightArrow.setEnabled(false);
        btnRightArrow.addMouseListener(new BorderButton());
        
        //URL box
        txtUrl = new JTextField(35);
        Font fontUrl = new Font("Verdana", Font.BOLD, 15);
        txtUrl.setFont(fontUrl);
       
        //Refresh page
        btnRefreshPage = new JButton(); 
        btnRefreshPage.setIcon(new ImageIcon ("src/images/refresh.png"));
        btnRefreshPage.setContentAreaFilled(false);
        btnRefreshPage.setBorderPainted(false);
        btnRefreshPage.setFocusPainted(false);
        btnRefreshPage.addMouseListener(new BorderButton());
        
        //Home page
        btnHomePage = new JButton(); 
        btnHomePage.setIcon(new ImageIcon ("src/images/home.png"));
        btnHomePage.setToolTipText("Página de inicio de JBrowser");
        btnHomePage.setContentAreaFilled(false);
        btnHomePage.setBorderPainted(false);
        btnHomePage.setFocusPainted(false);
        btnHomePage.addMouseListener(new BorderButton());
        
        //Settings
        btnSetting = new JButton(); 
        btnSetting.setLayout( new FlowLayout(FlowLayout.RIGHT));
        btnSetting.setIcon(new ImageIcon ("src/images/settings.png"));
        btnSetting.setContentAreaFilled(false);
        btnSetting.setBorderPainted(false);
        btnSetting.setFocusPainted(false);
        btnSetting.addMouseListener(new BorderButton());
        
        //Adds components
        panelHeader.add(btnLeftArrow);
        panelHeader.add(btnRightArrow);
        panelHeader.add(txtUrl);
        panelHeader.add(btnRefreshPage);
        panelHeader.add(btnHomePage);
        panelHeader.add(btnSetting);
        add(panelHeader, BorderLayout.NORTH);
        
        
        //----Content
        //Main panel
        panelContent = new JPanel();
        panelContent.setLayout( new GridLayout(1,1));
        panelContent.setBackground(new Color(243,201,120));
        
        //TabPanel
        panelTabs = new TabPanel();
        
        //Adds components
        panelContent.add(panelTabs);
        add(panelContent, BorderLayout.CENTER);
       
        
        //----Footer
        //Main panel
        panelFooter = new JPanel();
        panelFooter.setLayout( new FlowLayout(FlowLayout.RIGHT));
        panelFooter.setBackground(new Color(0,0,0));
        
        //Page Status
        lblStatusTitle = new JLabel("Page Status: ");
        lblStatusTitle.setForeground(new Color(243,201,120));
        lblStatusValue = new JLabel("No access");
        lblStatusValue.setForeground(Color.white);
        
        
        //Adds components
        panelFooter.add(lblStatusTitle);
        panelFooter.add(lblStatusValue);
        add(panelFooter, BorderLayout.SOUTH);
        
        //--Actions Listeners
        
        //Show page on tab
        txtUrl.addKeyListener(new KeyAdapter() 
        {
            public void keyPressed(KeyEvent evt)
            {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    JScrollPane scrollPanel = (JScrollPane) panelTabs.getComponentAt(panelTabs.getSelectedIndex());
                    JViewport view = (JViewport) scrollPanel.getComponent(0);
                    PageView pageContent = (PageView) view.getComponent(0);
                    
                    pageContent.go(txtUrl.getText());
                    
                    btnLeftArrow.setEnabled(true);
                }
            }
        });
        
        //Reload page on tab
        btnRefreshPage.addActionListener (new ActionListener()
        {
          public void actionPerformed (ActionEvent e)
          {
                JScrollPane scrollPanel = (JScrollPane) panelTabs.getComponentAt(panelTabs.getSelectedIndex());
                JViewport view = (JViewport) scrollPanel.getComponent(0);
                PageView pageContent = (PageView) view.getComponent(0);

                pageContent.reload();
          }
        });
        
        //Backward a page
        btnLeftArrow.addActionListener (new ActionListener()
        {
          public void actionPerformed (ActionEvent e)
          {
                JScrollPane scrollPanel = (JScrollPane) panelTabs.getComponentAt(panelTabs.getSelectedIndex());
                JViewport view = (JViewport) scrollPanel.getComponent(0);
                PageView pageContent = (PageView) view.getComponent(0);

                pageContent.back();
                
                btnRightArrow.setEnabled(true);
          }
        });
        
        //Forward a page
        btnRightArrow.addActionListener (new ActionListener()
        {
          public void actionPerformed (ActionEvent e)
          {
                JScrollPane scrollPanel = (JScrollPane) panelTabs.getComponentAt(panelTabs.getSelectedIndex());
                JViewport view = (JViewport) scrollPanel.getComponent(0);
                PageView pageContent = (PageView) view.getComponent(0);

                pageContent.forward();
          }
        });
        
        //----Settings
        setSize(1074, 768);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(890, 200));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

   
    
    
    
    //----BorderButton action
    class BorderButton extends MouseAdapter{
        
	public void mouseEntered(MouseEvent evt) {
            JButton btn = (JButton) evt.getSource();
            if(btn.isEnabled()){
                btn.setBorderPainted(true);
            }  
        }

        public void mouseExited(MouseEvent evt) {
            JButton btn = (JButton) evt.getSource();
            if(btn.isEnabled()){
                btn.setBorderPainted(false);
            } 
        }
    }
    
    
    //----Getters and Setters
    public JPanel getPanelHeader() {
        return panelHeader;
    }

    public void setPanelHeader(JPanel panelHeader) {
        this.panelHeader = panelHeader;
    }

    public JPanel getPanelContent() {
        return panelContent;
    }

    public void setPanelContent(JPanel panelContent) {
        this.panelContent = panelContent;
    }

    public JPanel getPanelFooter() {
        return panelFooter;
    }

    public void setPanelFooter(JPanel panelFooter) {
        this.panelFooter = panelFooter;
    }

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
