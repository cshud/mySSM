package com.shud.test;

import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * Created by shud on 2017/5/20.
 */
public class Exam {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Exam.class);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            logger.info(a);
        }
    }
}
