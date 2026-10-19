/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fluidsimulation;

import java.awt.*;

public class CircleObstacle
        extends Obstacle {

    double radius;

    public CircleObstacle(
            double x,
            double y,
            double radius
    ) {

        super(x, y);

        this.radius = radius;
    }

    @Override
    public void checkCollision(
            Particle p
    ) {

        double dx =
                p.position.x - x;

        double dy =
                p.position.y - y;

        double dist =
                Math.sqrt(dx * dx + dy * dy);

        if (dist < radius && dist > 0) {

            double nx = dx / dist;
            double ny = dy / dist;

            // Push particle outside
            p.position.x =
                    x + nx * radius;

            p.position.y =
                    y + ny * radius;

            // Bounce effect
            double dot =
                    p.velocity.vx * nx
                    + p.velocity.vy * ny;

            p.velocity.vx =
                    (p.velocity.vx
                    - 2 * dot * nx)
                    * 0.7;

            p.velocity.vy =
                    (p.velocity.vy
                    - 2 * dot * ny)
                    * 0.7;
        }
    }

    @Override
    public void draw(
            Graphics2D g2d
    ) {

        g2d.setColor(
                new Color(150,150,150)
        );

        g2d.fillOval(
                (int)(x - radius),
                (int)(y - radius),
                (int)(radius * 2),
                (int)(radius * 2)
        );

        g2d.setColor(Color.WHITE);

        g2d.drawOval(
                (int)(x - radius),
                (int)(y - radius),
                (int)(radius * 2),
                (int)(radius * 2)
        );
    }
}