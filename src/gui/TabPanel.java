package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
/**
 * Creates a structure contains all tabs of the browser
 * @author Jefferson Rubio <jefferson.jrubio@gmail.com>
 */
public class TabPanel extends JTabbedPane{

    private JButton newTab;
    private PageView.PageListener pageListener;
    
    /**
     * Add empty tab and "+" tab
     *
     * @author Jefferson Rubio <jefferson.jrubio@gmail.com>
     * @author Ramón Carrillo <racarrillo91@gmail.com>
     */
    public TabPanel(PageView.PageListener pageListener){

	this.pageListener = pageListener;

        setUI(new PPTTabbedPaneUI());
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        newTab = new JButton("+");
        newTab.setForeground(Color.white);
        newTab.setContentAreaFilled(false);
        newTab.setBorderPainted(false);
        newTab.setFocusPainted(false);
        addTab("", null);
        setTabComponentAt(0, newTab);
        getTabComponentAt(0).addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                addNewTab();
            }
        });
        addNewTab();
    }

    /**
     * Add a new tab with an empty page
     *
     * @author Ramón Carrillo <racarrillo91@gmail.com>
     * @author Sanny Florencia <sbflorenc@gmail.com>
     * @author Jefferson Rubio <jefferson.jrubio@gmail.com>
     */
    private void addNewTab() {
        PageView page = new PageView();
	page.addPageListener(pageListener);

        int index = getTabCount() - 1;
        
        insertTab("", null, page, "", index);

        ButtonTabComponent btnTab = new ButtonTabComponent("New Tab", this);
        setTabComponentAt(index, btnTab);
        setSelectedIndex(index);
    }

}
