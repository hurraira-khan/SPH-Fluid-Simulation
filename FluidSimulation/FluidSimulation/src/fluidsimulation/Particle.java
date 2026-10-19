/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fluidsimulation;

import java.awt.*;

public class Particle {

    Position position;

    Velocity velocity;

    Density density;

    Pressure pressure;

    Color color;

    public Particle(
            double x,
            double y,
            Color color
    ) {

        position =
                new Position(x, y);

        velocity =
                new Velocity(
                        (Math.random() - 0.5) * 1.5,
                        (Math.random() - 0.5) * 1.5
                );

        density =
                new Density(1.0);

        pressure =
                new Pressure(1.0);

        this.color = color;
    }
}
