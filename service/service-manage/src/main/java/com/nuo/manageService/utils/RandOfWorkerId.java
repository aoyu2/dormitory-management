package com.nuo.manageService.utils;

import org.junit.Test;


// 生成工号
public class RandOfWorkerId {

    public static String createWorkerId() {
        char[] initialString = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
        String workerId = "";
        String id = "";

        for (int j = 0; j < 3; j++) {
            int i = (int) (Math.random() * 10);
            workerId += initialString[i];
            id += i;
        }
        workerId += id;
        System.out.println(workerId);
        return workerId;
    }
}
