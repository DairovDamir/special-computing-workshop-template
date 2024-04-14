package ru.spbu.apcyb.svp.tasks.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AtmTest {
    private Atm atm;

    @BeforeEach
    void setUp() {
        atm = new Atm();
    }

    @Test
    void getCombOf_5_3_2() throws Exception{
        atm.setSum(5L);
        atm.setBanknotes("3 2");
        atm.getComb();

        long[] expected = {1L, 1L};

        List<long[]> ans = atm.getAllCombWithList();

        assertArrayEquals(expected, ans.get(0));

    }

    @Test
    void getCombOf_1_5_6() throws Exception{
        atm.setSum(1L);
        atm.setBanknotes("5 6");
        assertThrows(Exception.class, () -> atm.getComb());
    }

    @Test
    void getCombOf_1000_1() throws Exception{
        atm.setSum(1000L);
        atm.setBanknotes("1");
        atm.getComb();
        List<long[]> ans = atm.getAllCombWithList();

        assertEquals(1, ans.size());
    }

    @Test
    void geCombOf_5_4_2() throws Exception{
        atm.setSum(5L);
        atm.setBanknotes("4 2");
        atm.getComb();

        List<long[]> ans = atm.getAllCombWithList();

        assertTrue(ans.isEmpty());
    }

}