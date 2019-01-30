package com.ubs.opsit.interviews;

import java.util.HashMap;
import java.util.Map;

public class TimeConverterImpl implements TimeConverter {

    private static final Map<Integer, String> redSignalsMap = new HashMap<>();
    private static final Map<Integer, String> yellowSignalsMap = new HashMap<>();

    static {
        redSignalsMap.put(0, "OOOO");
        redSignalsMap.put(1, "ROOO");
        redSignalsMap.put(2, "RROO");
        redSignalsMap.put(3, "RRRO");
        redSignalsMap.put(4, "RRRR");

        yellowSignalsMap.put(0, "OOOO");
        yellowSignalsMap.put(1, "YOOO");
        yellowSignalsMap.put(2, "YYOO");
        yellowSignalsMap.put(3, "YYYO");
        yellowSignalsMap.put(4, "YYYY");
    }

    @Override
    public String convertTime(String aTime) {

        Clock clock = parse(aTime);

        String oneSecondView = (clock.getSecond() % 2) == 0 ? "Y" : "O";
        String oneHourSignalView = redSignalsMap.get(clock.getHour() % 5);
        String fiveHourSignalView = redSignalsMap.get(clock.getHour() / 5);

        String[] fiveMinSignal = new String[11];
        for (int i = 1; i <= fiveMinSignal.length; i++) {
            fiveMinSignal[i - 1] = clock.getMinute() / 5 >= i ? (i * 5 % 15 == 0 ? "R" : "Y") : "O";
        }

        String fiveMinSignalView = String.join("", fiveMinSignal);
        String oneMinuteSignalView = yellowSignalsMap.get(clock.getMinute() % 5);

        return String.join("\n", oneSecondView,
                fiveHourSignalView,
                oneHourSignalView,
                fiveMinSignalView,
                oneMinuteSignalView);
    }

    private Clock parse(String aTime) {
        if (aTime == null || aTime.isEmpty()) {
            throw new IllegalArgumentException("Time mut not be empty");
        }

        String[] parts = aTime.split(":");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid time format");
        }

        return new Clock(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]), Integer.valueOf(parts[2]));
    }
}
