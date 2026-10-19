/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fluidsimulation;

import java.awt.*;

public abstract class Obstacle {

    double x, y;

    public Obstacle(
            double x,
            double y
    ) {

        this.x = x;
        this.y = y;
    }

    public abstract void checkCollision(
            Particle p
    );

    public abstract void draw(
            Graphics2D g2d
    );
}
