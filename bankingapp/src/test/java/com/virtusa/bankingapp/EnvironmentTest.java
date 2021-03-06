package com.virtusa.bankingapp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

public class EnvironmentTest {

	@Test
    @EnabledOnOs(OS.MAC)
    void testOnMacOs() {
        assertTrue(true);
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void doNotTestOnWindows() {
        assertTrue(true);
    }
    
    @Test
    @EnabledOnJre({ JRE.JAVA_11, JRE.JAVA_12 })
    void onJava11Or12() {
        assertTrue(true);
    }

    @Test
    @DisabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_9)
    void notFromJava8to9() {
        assertTrue(true);
    }

    

   
}
