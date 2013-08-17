package gui;



import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

/**
 * An implementation of the TabbedPaneUI that looks like the tabs that are used in Microsoft Powerpoint
 * <p/>
 * Copyright (C) 2005 by Jon Lipsky
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. Y
 * ou may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software d
 * istributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class PPTTabbedPaneUI extends BasicTabbedPaneUI
{
	private static final Insets TAB_INSETS = new Insets(1, 0, 0, 0);

	/**
	 * The font to use for the selected tab
	 */
	private Font boldFont;

	/**
	 * The font metrics for the selected font
	 */
	private FontMetrics boldFontMetrics;

	/**
	 * The color to use to fill in the background
	 */
	private Color selectedColor;


	/**
	 * The color to use to fill in the background
	 */
	private Color unselectedColor;

	// ------------------------------------------------------------------------------------------------------------------
	//  Custom installation methods
	// ------------------------------------------------------------------------------------------------------------------

	/**
     *
     * @param c
     * @return
     */
    public static ComponentUI createUI(JComponent c)
	{
		return new PPTTabbedPaneUI();
	}

	/**
     *
     */
    protected void installDefaults()
	{
		super.installDefaults();
		tabAreaInsets.left = (calculateTabHeight(0, 0, tabPane.getFont().getSize()) / 4) + 1;
		selectedTabPadInsets = new Insets(0, 0, 0, 0);

		selectedColor = Color.black;
		unselectedColor = tabPane.getBackground().darker();

		boldFont = tabPane.getFont().deriveFont(Font.BOLD);
		boldFontMetrics = tabPane.getFontMetrics(boldFont);
	}

	// ------------------------------------------------------------------------------------------------------------------
	//  Custom sizing methods
	// ------------------------------------------------------------------------------------------------------------------

	/**
     *
     * @param pane
     * @return
     */
    public int getTabRunCount(JTabbedPane pane)
	{
		return 1;
	}

	/**
     *
     * @param tabPlacement
     * @return
     */
    protected Insets getContentBorderInsets(int tabPlacement)
	{
		return TAB_INSETS;
	}

	/**
     *
     * @param tabPlacement
     * @param tabIndex
     * @param fontHeight
     * @return
     */
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight)
	{
		int vHeight = fontHeight + 10;
		if (vHeight % 2 == 0)
		{
			vHeight += 5;
		}
		return vHeight;
	}

	/**
     *
     * @param tabPlacement
     * @param tabIndex
     * @param metrics
     * @return
     */
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics)
	{
		return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + metrics.getHeight();
	}

	// ------------------------------------------------------------------------------------------------------------------
	//  Custom painting methods
	// ------------------------------------------------------------------------------------------------------------------


	// ------------------------------------------------------------------------------------------------------------------
	//  Methods that we want to suppress the behaviour of the superclass
	// ------------------------------------------------------------------------------------------------------------------

	/**
     *
     * @param g
     * @param tabPlacement
     * @param tabIndex
     * @param x
     * @param y
     * @param w
     * @param h
     * @param isSelected
     */
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected)
	{
		Polygon shape = new Polygon();

		shape.addPoint(x - (h / 4), y + h);
		shape.addPoint(x + (h / 4), y);
		shape.addPoint(x + w - (h / 4), y);

		if (isSelected || (tabIndex == (rects.length - 1)))
		{
			if (isSelected)
			{
				g.setColor(selectedColor);
			}
			else
			{
				g.setColor(unselectedColor);
			}
			shape.addPoint(x + w + (h / 4), y + h);
		}
		else
		{
			g.setColor(unselectedColor);
			shape.addPoint(x + w, y + (h / 2));
			shape.addPoint(x + w - (h / 4), y + h);
		}

		g.fillPolygon(shape);
	}

	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected)
	{
		g.setColor(darkShadow);
		g.drawLine(x - (h / 4), y + h, x + (h / 4), y);
		g.drawLine(x + (h / 4), y, x + w - (h / 4), y);
		g.drawLine(x + w - (h / 4), y, x + w + (h / 4), y + h);
	}

	/**
     *
     * @param g
     * @param tabPlacement
     * @param selectedIndex
     * @param x
     * @param y
     * @param w
     * @param h
     */
    protected void paintContentBorderTopEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h)
	{
		Rectangle selectedRect = selectedIndex < 0 ? null : getTabBounds(selectedIndex, calcRect);
		g.setColor(darkShadow);
		g.drawLine(x, y, selectedRect.x - (selectedRect.height / 4), y);
		g.drawLine(selectedRect.x + selectedRect.width + (selectedRect.height / 4), y, x + w, y);
		g.setColor(selectedColor);
		g.drawLine(selectedRect.x - (selectedRect.height / 4)+1, y,selectedRect.x + selectedRect.width + (selectedRect.height / 4)-1, y);		

	}

	/**
     *
     * @param g
     * @param tabPlacement
     * @param selectedIndex
     * @param x
     * @param y
     * @param w
     * @param h
     */
    protected void paintContentBorderRightEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h)
	{

	}

	/**
     *
     * @param g
     * @param tabPlacement
     * @param selectedIndex
     * @param x
     * @param y
     * @param w
     * @param h
     */
    protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h)
	{

	}

	/**
     *
     * @param g
     * @param tabPlacement
     * @param selectedIndex
     * @param x
     * @param y
     * @param w
     * @param h
     */
    protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h)
	{

	}

	/**
     *
     * @param g
     * @param tabPlacement
     * @param rects
     * @param tabIndex
     * @param iconRect
     * @param textRect
     * @param isSelected
     */
    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected)
	{
		// Do nothing
	}

	/**
     *
     * @param g
     * @param tabPlacement
     * @param font
     * @param metrics
     * @param tabIndex
     * @param title
     * @param textRect
     * @param isSelected
     */
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected)
	{
		if (isSelected)
		{
			int vDifference = (int) (boldFontMetrics.getStringBounds(title, g).getWidth()) - textRect.width;
			textRect.x -= (vDifference / 2);
			super.paintText(g, tabPlacement, boldFont, boldFontMetrics, tabIndex, title, textRect, isSelected);
		}
		else
		{
			super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
		}
	}

	/**
     *
     * @param tabPlacement
     * @param tabIndex
     * @param isSelected
     * @return
     */
    protected int getTabLabelShiftY(int tabPlacement, int tabIndex, boolean isSelected)
	{
		return 0;
	}
}