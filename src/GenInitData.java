/* 
Generating initial data. Basically this class would generate a nested array 
of points of object which we need to triangulate
*/

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class GenInitData {

    private float inR;
    private float outR;
    private int inAngAmount;
    private int outAngAmount;

    public GenInitData(float inR, float outR, int inAngAmount, int outAngAmount) {
        this.inR = inR;
        this.outR = outR;
        this.inAngAmount = inAngAmount;
        this.outAngAmount = outAngAmount;
    }

    public double[][] gen_points_coords() {
        double[][] inPointsCoords = get_points_on_circle(true);
        double[][] outPointsCoords = get_points_on_circle(false);

        double[][] result_coords = merge_in_and_out(inPointsCoords, outPointsCoords);
        Plotting.plotInitialFigure("Initial Data", result_coords);

        return result_coords;
    }

    private double[][] get_points_on_circle(boolean in) {
        int angAmount;
        float R;
        if (in) {
            angAmount = this.inAngAmount;
            R = this.inR;
        } else {
            angAmount = this.outAngAmount;
            R = this.outR;
        }

        double[][] xy_coords = new double[angAmount][2];
        // Theta is an angle in radians
        for (int loop = 0; loop < angAmount; loop+=1) {
            double theta = loop*2*Math.PI/angAmount;
            float x_temp = R * (float)Math.cos(theta);
            float y_temp = R * (float)Math.sin(theta);

            xy_coords[loop][0] = x_temp;
            xy_coords[loop][1] = y_temp;
        }
        return xy_coords;
    }

    private double[][] merge_in_and_out(double[][] in_arr, double[][] out_arr) {
        double[][] result_arr = new double[in_arr.length + out_arr.length][2];

        System.arraycopy(in_arr, 0, result_arr, 0, in_arr.length);
        System.arraycopy(out_arr, 0, result_arr, in_arr.length, out_arr.length);

        return result_arr;
    }

}
