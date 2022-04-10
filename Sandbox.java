// This class is for testing other new classes

public class Sandbox {
    public static void main(String[] args) {
        GenInitData genInitData = new GenInitData(100, 200, 8, 8);
        double[][] result_coords = genInitData.gen_points_coords();
        System.out.println(result_coords);
    }
}
