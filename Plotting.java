/*
This class is for plotting graphs and displaying them to user.
Thanks to the author of this video (https://youtu.be/2l5-5PMUc5Y) for explaining how to draw
basic shapes in Java.
 */

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class Plotting extends JPanel {

    private Map<String, List<Float>> points_coords;

    public Plotting(Map<String, List<Float>> points_coords) {
        this.points_coords = points_coords;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);

        g.setColor(Color.BLUE);
        for (int i = 0; i < points_coords.get("x").size(); i++) {
            g.fillOval(
                    Math.round(points_coords.get("x").get(i))+300,
                    Math.round(points_coords.get("y").get(i))+300,
                    10,
                    10
            );
        }
    }

    public static void plot(String plotName, Map<String, List<Float>> coords) {
        JFrame frame = new JFrame(plotName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Plotting p = new Plotting(coords);
        frame.add(p);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}
