/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fluidsimulation;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JFrame {

    public Renderer() {

        setTitle("Professional 2D SPH Fluid Simulation");

        setSize(450, 320);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout());

        panel.setBackground(new Color(25, 25, 30));

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        JLabel title =
                new JLabel("Select Fluid Type");

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(title, gbc);

        String[] fluids = {
            "Water",
            "Oil",
            "Honey"
        };

        JComboBox<String> combo =
                new JComboBox<>(fluids);

        gbc.gridy = 1;

        panel.add(combo, gbc);

        JButton start =
                new JButton("Start Simulation");

        gbc.gridy = 2;

        panel.add(start, gbc);

        JLabel names =
                new JLabel("Huraira | Niaz");

        names.setForeground(Color.CYAN);

        gbc.gridy = 3;

        panel.add(names, gbc);

        add(panel);

        start.addActionListener(e -> {

            FluidType fluid =
                    FluidType.createFluid(
                            (String)
                            combo.getSelectedItem()
                    );

            dispose();

            JFrame frame =
                    new JFrame(
                            "2D SPH Fluid Engine"
                    );

            frame.setSize(820, 620);

            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            frame.setLocationRelativeTo(null);

            frame.add(
                    new ParticleManager(fluid)
            );

            frame.setVisible(true);
        });
    }
}
