package com.nuo.repairService.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

// 生产工单号
public class OrderNumberGenerator {
    private static int sequence = 0;
    private static final int MAX_SEQUENCE = 9999;

    public static synchronized String generateOrderNumber() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = dateFormat.format(new Date());
        String sequenceStr = String.format("%04d", getNextSequence());
        return dateStr + sequenceStr;
    }

    private static int getNextSequence() {
        if (sequence >= MAX_SEQUENCE) {
            sequence = 0;
        }
        return ++sequence;
    }
}
