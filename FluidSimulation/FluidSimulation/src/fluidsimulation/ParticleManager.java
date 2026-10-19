/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fluidsimulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ParticleManager
        extends JPanel
        implements ActionListener {

    ArrayList<Particle> particles;

    ArrayList<Obstacle> obstacles;

    FluidType fluid;

    PhysicsSolver solver;

    Timer timer;

    boolean isPaused = false;

    JButton pauseButton;

    JComboBox<String> fluidSelector;

    public ParticleManager(
            FluidType selectedFluid
    ) {

        fluid = selectedFluid;

        particles = new ArrayList<>();

        obstacles = new ArrayList<>();

        solver = new PhysicsSolver();

        setBackground(
                new Color(10, 10, 15)
        );

        setLayout(new BorderLayout());

        // ==================================
        // CONTROL PANEL
        // ==================================
        JPanel controlPanel =
                new JPanel();

        controlPanel.setBackground(
                new Color(25, 25, 30)
        );

        // Pause Button
        pauseButton =
                new JButton("Pause");

        pauseButton.setFocusPainted(false);

        pauseButton.addActionListener(
                e -> {

            isPaused = !isPaused;

            if (isPaused) {

                pauseButton.setText(
                        "Resume"
                );

            } else {

                pauseButton.setText(
                        "Pause"
                );
            }
        });

        // Exit Button
        JButton exitButton =
                new JButton("Exit");

        exitButton.setBackground(
                new Color(220, 53, 69)
        );

        exitButton.setForeground(
                Color.WHITE
        );

        exitButton.setFocusPainted(false);

        exitButton.addActionListener(
                e -> System.exit(0)
        );

        // Fluid Selector
        String[] fluids = {
            "Water",
            "Oil",
            "Honey"
        };

        fluidSelector =
                new JComboBox<>(fluids);

        fluidSelector.addActionListener(
                e -> {

            String selected =
                    (String)
                    fluidSelector
                    .getSelectedItem();

            fluid =
                    FluidType.createFluid(
                            selected
                    );

            for (Particle p : particles) {

                p.color =
                        fluid.getColor();
            }
        });

        controlPanel.add(
                pauseButton
        );

        controlPanel.add(
                exitButton
        );

        controlPanel.add(
                new JLabel("Fluid:")
        );

        controlPanel.add(
                fluidSelector
        );

        add(
                controlPanel,
                BorderLayout.NORTH
        );

        // ==================================
        // OBSTACLES
        // ==================================
        obstacles.add(
                new CircleObstacle(
                        400,
                        300,
                        60
                )
        );

        obstacles.add(
                new RectangleObstacle(
                        250,
                        450,
                        300,
                        25
                )
        );

        // ==================================
        // PARTICLES
        // ==================================
        int particleCount = 1000;

        for (int i = 0; i < particleCount; i++) {

            particles.add(
                    new Particle(
                            250 + Math.random() * 250,
                            50 + Math.random() * 120,
                            fluid.getColor()
                    )
            );
        }

        timer =
                new Timer(16, this);

        timer.start();
    }

    @Override
    protected void paintComponent(
            Graphics g
    ) {

        super.paintComponent(g);

        Graphics2D g2d =
                (Graphics2D) g;

        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Draw Obstacles
        for (Obstacle o : obstacles) {

            o.draw(g2d);
        }

        // Draw Particles
        for (Particle p : particles) {

            // Glow Effect
            g2d.setColor(
                    new Color(
                            p.color.getRed(),
                            p.color.getGreen(),
                            p.color.getBlue(),
                            40
                    )
            );

            g2d.fillOval(
                    (int)p.position.x - 7,
                    (int)p.position.y - 7,
                    14,
                    14
            );

            // Main Particle
            g2d.setColor(p.color);

            g2d.fillOval(
                    (int)p.position.x - 3,
                    (int)p.position.y - 3,
                    6,
                    6
            );
        }

        // ==================================
        // HUD
        // ==================================
        g2d.setColor(Color.WHITE);

        g2d.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        g2d.drawString(
                "Fluid : "
                + fluid.getName(),
                20,
                60
        );

        g2d.drawString(
                "Viscosity : "
                + fluid.getViscosity(),
                20,
                80
        );

        g2d.drawString(
                "Particles : "
                + particles.size(),
                20,
                100
        );

        g2d.drawString(
                "2D SPH Fluid Simulation",
                20,
                120
        );
    }

    @Override
    public void actionPerformed(
            ActionEvent e
    ) {

        if (!isPaused) {

            for (Particle p : particles) {

                solver.applyForce(
                        p,
                        fluid,
                        particles
                );

                for (Obstacle o : obstacles) {

                    o.checkCollision(p);
                }
            }
        }

        repaint();
    }
}