package com.example.forum.leetcode;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolutionTest {

    private final Logger log = LoggerFactory.getLogger(SolutionTest.class);
    private Solution solution = new Solution();

    @Test
    public void checkValidGridTest() {
        boolean result = solution.checkValidGrid(new int[][]{{24,11,22,17,4},{21,16,5,12,9},{6,23,10,3,18},{15,20,1,8,13},{0,7,14,19,2}});
        log.info("result:"+ result);
    }

}
