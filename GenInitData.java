/* 
Generating initial data. Basically this class would generate a nested array 
of points of object which we need to triangulate
*/

import java.util.*;
import java.util.stream.Collectors;
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

    public Map<String, List<Float>> gen_points_coords() {
        Map<String, List<Float>> inPointsCoords = get_points_on_circle(true);
        Map<String, List<Float>> outPointsCoords = get_points_on_circle(false);

        Map<String, List<Float>> result_coords = merge_in_and_out(inPointsCoords, outPointsCoords);
        Plotting.plot("Initial Data", result_coords);
        return result_coords;
    }

    private Map<String, List<Float>> get_points_on_circle(boolean in) {
        int angAmount;
        float R;
        if (in) {
            angAmount = this.inAngAmount;
            R = this.inR;
        } else {
            angAmount = this.outAngAmount;
            R = this.outR;
        }

        List<Float> x = new ArrayList<>(angAmount);
        List<Float> y = new ArrayList<>(angAmount);
        // Theta is an angle in radians
        for (float theta = 0; theta < 2*Math.PI; theta+=2*Math.PI/angAmount) {
            float x_temp = R * (float)Math.cos(theta);
            float y_temp = R * (float)Math.sin(theta);
            System.out.println(theta + " " + x_temp + " " + y_temp);
            x.add(x_temp);
            y.add(y_temp);
        }
        Map<String, List<Float>> result_circle_coords = new HashMap<>(2);
        result_circle_coords.put("x", x);
        result_circle_coords.put("y", y);
        return result_circle_coords;
    }

    private Map<String, List<Float>> merge_in_and_out(Map<String, List<Float>> in, Map<String, List<Float>> out) {
        Map<String, List<Float>> result_map = new HashMap<>(2);

        String[] keys = new String[]{"x", "y"};
        for (String key: keys) {
            List<Float> in_list = in.get(key);
            List<Float> out_list = out.get(key);
            // Thanks to Mark (https://stackoverflow.com/a/60854305) for this piece of code
            List<Float> merged_lists = Stream.of(in_list, out_list).flatMap(Collection::stream).collect(Collectors.toList());
            result_map.put(key, merged_lists);
        }

        return result_map;
    }

}
