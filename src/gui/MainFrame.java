package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Jefferson Rubio <jefferson.jrubio@gmail.com>
 */
public class MainFrame extends JFrame{
    
    private JPanel panelHeader, panelContent, panelFooter;
    private JButton btnLeftArrow, btnRightArrow, btnRefreshPage, btnHomePage, btnSetting;
    private JTextField txtUrl;
    private TabPanel tabs;
    private JLabel lblStatusTitle, lblStatusValue;
    
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
		btnLeftArrow.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
                                int currentPage = ((PageView)tabs.getSelectedComponent()).getCurrentPage();
                                
                                System.out.println("C:"+currentPage+"S");
                                
				( (PageView)tabs.getSelectedComponent() ).back();
                                btnRightArrow.setEnabled(true);
                                
                                if(currentPage == 1)
                                    btnLeftArrow.setEnabled(false);
			}
		});
        
        //Right arrow
        btnRightArrow = new JButton(); 
        btnRightArrow.setIcon(new ImageIcon ("src/images/right_arrow.png"));
        btnRightArrow.setPreferredSize(new Dimension(50,30));
        btnRightArrow.setContentAreaFilled(false);
        btnRightArrow.setBorderPainted(false);
        btnRightArrow.setFocusPainted(false);
        btnRightArrow.setEnabled(false);
        btnRightArrow.addMouseListener(new BorderButton());
		btnRightArrow.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
                                int historySize = ((PageView)tabs.getSelectedComponent()).getHistorySize();
                                int currentPage = ((PageView)tabs.getSelectedComponent()).getCurrentPage();
				
                                ( (PageView)tabs.getSelectedComponent() ).forward();
                                btnLeftArrow.setEnabled(true);
                                System.out.println("C:"+currentPage+"S"+historySize);
                                if((currentPage + 2) == historySize){
                                    btnRightArrow.setEnabled(false);
                                }
			}
		});
        
        //URL box
        txtUrl = new JTextField(35);
        Font fontUrl = new Font("Verdana", Font.BOLD, 15);
        txtUrl.setFont(fontUrl);
		txtUrl.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				( (PageView)tabs.getSelectedComponent() ).go( txtUrl.getText() );
                                int historySize = ((PageView)tabs.getSelectedComponent()).getHistorySize();
                                int currentPage = ((PageView)tabs.getSelectedComponent()).getCurrentPage();
                                
                                if( currentPage > 0 ){
                                    btnLeftArrow.setEnabled(true);
                                }else{
                                    btnLeftArrow.setEnabled(false);
                                }
                                   
			}
		});
       
        //Refresh page
        btnRefreshPage = new JButton(); 
        btnRefreshPage.setIcon(new ImageIcon ("src/images/refresh.png"));
        btnRefreshPage.setContentAreaFilled(false);
        btnRefreshPage.setBorderPainted(false);
        btnRefreshPage.setFocusPainted(false);
        btnRefreshPage.addMouseListener(new BorderButton());
        btnRefreshPage.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
                            if(((PageView)tabs.getSelectedComponent()).getHistorySize() > 0)
				( (PageView)tabs.getSelectedComponent() ).reload();
			}
		});
		
        //Home page
        btnHomePage = new JButton(); 
        btnHomePage.setIcon(new ImageIcon ("src/images/home.png"));
        btnHomePage.setToolTipText("Página de inicio de JBrowser");
        btnHomePage.setContentAreaFilled(false);
        btnHomePage.setBorderPainted(false);
        btnHomePage.setFocusPainted(false);
        btnHomePage.addMouseListener(new BorderButton());
        btnHomePage.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				( (PageView)tabs.getSelectedComponent() ).go("http://www.cs.bham.ac.uk/~tpc/testpages/");
                                txtUrl.setText("http://www.cs.bham.ac.uk/~tpc/testpages/");
                        }
		});
        
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
        tabs = new TabPanel();
        tabs.addChangeListener( new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent ce) {
				PageView page = (PageView) tabs.getSelectedComponent();
				System.out.println(page.getUrl());
				txtUrl.setText(page.getUrl());
			}
		} );
		
        //Adds components
        panelContent.add(tabs);
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
    
    //Getters and Setters

    //Getters and Setters
    public JButton getBtnLeftArrow() {
        return btnLeftArrow;
    }

    public JButton getBtnRightArrow() {
        return btnRightArrow;
    }
    
}
