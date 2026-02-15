package com.freightfox.dispatchLoadBalancer.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DistenceUtilTest {

    @Test
    void shouldReturnZeroWhenSameCoordinates() {
        double distance = DistanceUtil.haversine(28.6139, 77.2090,
                28.6139, 77.2090);

        assertEquals(0.0, distance, 0.0001);
    }

    @Test
    void shouldCalculateCorrectDistanceBetweenTwoCities() {
        // Delhi to Mumbai approx 1150 km
        double distance = DistanceUtil.haversine(28.6139, 77.2090,
                19.0760, 72.8777);

        assertTrue(distance > 1100 && distance < 1200);
    }
}
