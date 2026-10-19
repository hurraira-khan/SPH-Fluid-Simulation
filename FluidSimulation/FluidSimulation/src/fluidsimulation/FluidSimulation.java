package fluidsimulation;

import javax.swing.*;

public class FluidSimulation {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new Renderer().setVisible(true);

        });
    }
}