package com.paypaycorp;

import com.paypaycorp.exception.InvalidInputException;
import com.paypaycorp.queue.ImmutableQueue;
import com.paypaycorp.queue.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Hello world!
 */
public class MainService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainService.class);

    public static void main(String[] args) throws InvalidInputException {

        Scanner scannerInput = new Scanner(System.in);
        System.out.println("Please Enter initial Size of the Queue: ");
        int size = scannerInput.nextInt();
        if (size < 1) {
            throw new InvalidInputException("Invalid Initia size entered. Please provide valid Size.");
        }
        Queue<String> queue = new ImmutableQueue<String>(size);
        System.out.println("\n");
        String choice;
        do {
            System.out.println("**********************QUEUE MENU OPTIONS*************************");
            System.out.println("1. EnQueue. \n2. DeQueue. \n3. GetHead \n4. isEmpty \n5. Size \n6. Display Queue\n0. Exit!");
            System.out.println("*****************************************************************");

            choice = scannerInput.next();

            switch (choice) {
                case "1":
                    if (!queue.isFull()) {
                        System.out.println("Please Enter an Element value to enQueue: ");
                        String key = scannerInput.next();
                        LOGGER.debug("Adding element [{}] to Queue.", key);
                        queue.enQueue(key);
                    } else {
                        LOGGER.warn("Queue is FULL!, can not enQueue anymore !");
                    }
                    break;
                case "2":
                    if (!queue.isEmpty()) {
                        LOGGER.debug("Removing an element from Queue.");
                        queue.deQueue();
                    } else
                        LOGGER.warn("Queue is Empty, can not deQueue anymore !");
                    break;
                case "3":
                    if (!queue.isEmpty()) {
                        Object head = queue.head();
                        LOGGER.debug("Retrieved Head [{}] from Queue.", head);
                    } else
                        LOGGER.warn("Queue is Empty, no head present");
                    break;
                case "4":
                    LOGGER.debug("Queue is [{}].", queue.isEmpty() ? "Empty!" : "not Empty!");
                    break;
                case "5":
                    LOGGER.debug("Queue Size is [{}].", queue.getSize());
                    break;
                case "6":
                    System.out.println("=====================================================");
                    if (!queue.isEmpty())
                        displayQueue(queue);
                    else
                        LOGGER.warn("Queue is Empty!");
                    System.out.println("=====================================================");
                    break;
                case "0":
                default:
                    System.out.println("Do you Want to Exit from System? Press Y or N ");
                    String option = scannerInput.next();
                    if (option != null && option.toUpperCase().equalsIgnoreCase("Y"))
                        System.exit(0);
                    else continue;
            }
        } while (!choice.toUpperCase().equals("N"));
    }

    private static void displayQueue(Queue<String> queue) {
        Object[] elements = ((ImmutableQueue) queue).getElements();
        System.out.println("Element : ");
        for (int count = ImmutableQueue.getFront(); count < queue.getSize(); count++) {
            System.out.println("[" + elements[count] + "],");
        }
    }
}
