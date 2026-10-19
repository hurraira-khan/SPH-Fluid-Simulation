/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fluidsimulation;

public class ExternalForce
        implements ForceCalculator {

    @Override
    public void apply(Particle p) {

        p.velocity.vy += 0.12;
    }
}