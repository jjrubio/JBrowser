
package gui;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 *
 * @author Jefferson Rubio <jefferson.jrubio@gmail.com>
 */
public class TabPanel extends JTabbedPane{
    
    ButtonTabComponent btnTab;
    
    public TabPanel(){
        
        this.setUI(new PPTTabbedPaneUI());
        
        this.addTab("New Tab", null);
        
        btnTab = new ButtonTabComponent("New Tab", this);
        
        this.setTabComponentAt(0,btnTab);
        
        this.addTab("+", null);
        
        this.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(getSelectedIndex() == getTabCount()-1){
//                    btnTab = new ButtonTabComponent("New Tab", this);
//                    setTabComponentAt(getSelectedIndex(),b);
//                    setTitleAt(getSelectedIndex(), "New Tab");
                    addTab("+", null);
                }
//              System.out.println("Tab=" + getSelectedIndex());
//              System.out.println("Tabs=" + getTabCount());
            }
        });
    }
}
