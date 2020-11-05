package utils;

public class ParseString {
    public static double findDoubleInString(String line) {
        String[] splitted = line.split(" ");
        double cost = 0;
        for (String s : splitted) {
            try {
                cost = Double.parseDouble(s);
                break;
            } catch (NumberFormatException ignored) {
            }
        }
        return cost;
    }

    public static String getCostFromStringEstimate(String s) {
        String cost = null;
        String[] splitted = s.split(" ");
        for (int i = 0; i < splitted.length; i++) {
            if (splitted[i].equals("USD")) {
                cost = splitted[i + 1];
                break;
            }
        }

        return cost;
    }
}
