package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
/**
 *
 * @author Jefferson Rubio <jefferson.jrubio@gmail.com>
 */
public class TabPanel extends JTabbedPane{

    JLabel newTab;
    
    /**
     * Add empty tab and "+" tab
     *
     * @author Jefferson Rubio <jefferson.jrubio@gmail.com>
     * @author Ramón Carrillo <racarrillo91@gmail.com>
     */
    public TabPanel(){

        this.setUI(new PPTTabbedPaneUI());
        
        newTab = new JLabel("+");
        addTab("+", null);
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
     */
    private void addNewTab() {
        PageView page = new PageView();

        int index = getTabCount() - 1;

        insertTab("", null, page, "", index);

        ButtonTabComponent btnTab = new ButtonTabComponent("New Tab", this);
        setTabComponentAt(index, btnTab);
    }
}
