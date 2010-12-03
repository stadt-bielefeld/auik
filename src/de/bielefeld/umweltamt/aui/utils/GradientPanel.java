package de.bielefeld.umweltamt.aui.utils;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Paint;

import javax.swing.JPanel;
import javax.swing.UIManager;


/**
 * A panel with a horizontal gradient background.
 * @author Karsten Lentzsch
 */
public class GradientPanel extends JPanel {

    public GradientPanel(LayoutManager lm, Color background) {
        super(lm);
        setBackground(background);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isOpaque()) {
            return;
        }
        Color control = UIManager.getColor("control");
        int width  = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g;
        Paint storedPaint = g2.getPaint();
        g2.setPaint(
            new GradientPaint(0, 0, getBackground(), width, 0, control));
        g2.fillRect(0, 0, width, height);
        g2.setPaint(storedPaint);
    }
}