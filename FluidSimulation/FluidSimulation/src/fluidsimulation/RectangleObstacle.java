/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fluidsimulation;

import java.awt.*;

public class RectangleObstacle
        extends Obstacle {

    int width;
    int height;

    public RectangleObstacle(
            double x,
            double y,
            int width,
            int height
    ) {

        super(x, y);

        this.width = width;
        this.height = height;
    }

    @Override
    public void checkCollision(
            Particle p
    ) {

        if (p.position.x > x &&
            p.position.x < x + width &&
            p.position.y > y &&
            p.position.y < y + height) {

            // Bounce
            p.velocity.vx *= -0.7;
            p.velocity.vy *= -0.7;

            // Push particle outside
            p.position.x +=
                    p.velocity.vx * 2;

            p.position.y +=
                    p.velocity.vy * 2;
        }
    }

    @Override
    public void draw(
            Graphics2D g2d
    ) {

        g2d.setColor(
                new Color(100,100,100)
        );

        g2d.fillRect(
                (int)x,
                (int)y,
                width,
                height
        );

        g2d.setColor(Color.WHITE);

        g2d.drawRect(
                (int)x,
                (int)y,
                width,
                height
        );
    }
}