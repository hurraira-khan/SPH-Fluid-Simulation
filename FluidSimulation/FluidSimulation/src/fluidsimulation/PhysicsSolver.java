/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fluidsimulation;

import java.util.ArrayList;

public class PhysicsSolver {

    private final double gravity = 0.12;

    private final double smoothingRadius = 35;

    public void applyForce(
            Particle p,
            FluidType fluid,
            ArrayList<Particle> particles
    ) {

        p.velocity.vy += gravity;

        double viscosity =
                fluid.getViscosity();

        for (Particle other : particles) {

            if (p == other) continue;

            double dx =
                    other.position.x
                    - p.position.x;

            double dy =
                    other.position.y
                    - p.position.y;

            double dist =
                    Math.sqrt(dx * dx + dy * dy);

            if (dist < smoothingRadius &&
                dist > 0) {

                double pressure =
                        (smoothingRadius - dist)
                        * 0.015;

                p.velocity.vx -=
                        pressure * (dx / dist);

                p.velocity.vy -=
                        pressure * (dy / dist);

                p.velocity.vx +=
                        (other.velocity.vx
                        - p.velocity.vx)
                        * viscosity * 0.02;

                p.velocity.vy +=
                        (other.velocity.vy
                        - p.velocity.vy)
                        * viscosity * 0.02;
            }
        }

        p.position.x += p.velocity.vx;

        p.position.y += p.velocity.vy;

        p.velocity.vx *= 0.995;

        p.velocity.vy *= 0.995;

        // Boundaries
        if (p.position.x < 5) {

            p.position.x = 5;

            p.velocity.vx *= -0.5;
        }

        if (p.position.x > 790) {

            p.position.x = 790;

            p.velocity.vx *= -0.5;
        }

        if (p.position.y < 5) {

            p.position.y = 5;

            p.velocity.vy *= -0.5;
        }

        if (p.position.y > 560) {

            p.position.y = 560;

            p.velocity.vy *= -0.3;
        }
    }
}