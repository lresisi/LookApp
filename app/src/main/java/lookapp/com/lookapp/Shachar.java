package lookapp.com.lookapp;

public class Shachar {

    private static int height = 240;
    private static int width = 320;
    private static int woFloor = 200;

    private static int criticalThreshold = 400;
    private static int cautionThreshold = 600;
    private static int noiseThreshold = 1300;

    private static int criticalPoints = 0;
    private static int cautionPoints = 0;

    public enum AlertType {
        CRITICAL,
        CAUTION,
        OK
    }

	public interface ACTION {
		public static String MAIN_ACTION = "com.truiton.foregroundservice.action.main";
		public static String PREV_ACTION = "com.truiton.foregroundservice.action.prev";
		public static String PLAY_ACTION = "com.truiton.foregroundservice.action.play";
		public static String NEXT_ACTION = "com.truiton.foregroundservice.action.next";
		public static String START_FOREGROUND_ACTION = "com.truiton.foregroundservice.action.startforeground";
		public static String STOP_FOREGROUND_ACTION = "com.truiton.foregroundservice.action.stopforeground";
	}

	public interface NOTIFICATION_ID {
		public static int FOREGROUND_SERVICE = 101;
	}

	private static char[][] processImage(int[][] matrix) {
        char[][] vals = new char[woFloor][width];

        for (int i = 0, row = 0, col = 0; i < woFloor*width; ++i) {
            int pixel = matrix[row][col];
            if ( (pixel > 0) && (pixel < criticalThreshold) ) {
                vals[row][col] = 'c';
                criticalPoints++;
            } else if ( (pixel > 0) && (pixel < cautionThreshold) ) {
                vals[row][col] = 't';
                cautionPoints++;
            } else {
                vals[row][col] = 0;
            }
            col++;
            if (col == width) {
                col = 0;
                row++;
            }
        }
        return vals;
    }

	public static AlertType getAlertType(int[][] matrix) {
        criticalPoints = 0; // zero indexes
        cautionPoints = 0;
        processImage(matrix);

        if (criticalPoints > noiseThreshold) {
            return AlertType.CRITICAL;
        } else if ( (criticalPoints + cautionPoints) > noiseThreshold ) {
            return AlertType.CAUTION;
        } else {
            return AlertType.OK;
        }
    }
}
