/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fluidsimulation;

import java.awt.*;

public abstract class FluidType {

    protected String name;
    protected double viscosity;
    protected Color color;

    public String getName() {
        return name;
    }

    public double getViscosity() {
        return viscosity;
    }

    public Color getColor() {
        return color;
    }

    public static FluidType createFluid(
            String type
    ) {

        switch (type.toLowerCase()) {

            case "oil":
                return new Oil();

            case "honey":
                return new Honey();

            default:
                return new Water();
        }
    }
}
