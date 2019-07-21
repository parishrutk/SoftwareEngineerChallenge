package com.paypaycorp;

import com.paypaycorp.exception.InvalidInputException;
import com.paypaycorp.queue.ImmutableQueue;
import com.paypaycorp.queue.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        Integer[] intArray = new Integer[10];
        Scanner inputs = new Scanner(System.in);

        System.out.println("Hello! Please enter Integers separated by comma --> ");
        String input = inputs.next();

        try {
            intArray = populateArrayForQueue(input);
        } catch (InvalidInputException e) {
            LOGGER.info("There is some problem with given Input, please try again.");
            e.printStackTrace();
        }

        Queue<Integer> queue = new ImmutableQueue<Integer>();

        LOGGER.info("HashCode Before adding elements to Queue: {} ",queue.hashCode());
        LOGGER.info("Adding elements to Queue.");

        for (Integer number : intArray) {
            queue.enQueue(number);
        }

        LOGGER.info("Added elements to Queue.");

        LOGGER.info("DeQueuing Elements from the QUEUE.");

        queue.deQueue();
        queue.deQueue();
        queue.deQueue();

        LOGGER.info("Final Queue Size:: {} ",queue.getSize());
        LOGGER.info("HashCode After modifying elements of Queue: {} ",queue.hashCode());
    }

    private static Integer[] populateArrayForQueue(String input) throws InvalidInputException {

        if (input == null || input.length() == 0) {
            LOGGER.error("Given Input {} String is Invalid. Please try again.");
            throw new InvalidInputException("Given Input " + input + " String in invalid.");
        }
        String[] tokens = input.split(",");
        Integer[] intArray = new Integer[tokens.length];
        int count = 0;
        for (String token : tokens) {
            if (token != null && token.length() > 0) {
                try {
                    int temp = Integer.valueOf(token);
                    intArray[count++] = temp;
                } catch (NumberFormatException numberFormatException) { //if NFE occurs fill that block of array with -1 value.
                    intArray[count++] = -1;
                }
            }
        }
        return intArray;
    }
}
