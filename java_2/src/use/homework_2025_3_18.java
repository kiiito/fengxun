package use;

import java.util.concurrent.Semaphore;

public class homework_2025_3_18 {
        private static final int BUFFER_SIZE = 10;
        private static int[] buffer = new int[BUFFER_SIZE];
        private static int in = 0;
        private static int out = 0;

        private static Semaphore mutex = new Semaphore(1);
        private static Semaphore empty = new Semaphore(BUFFER_SIZE);
        private static Semaphore full = new Semaphore(0);

        private static final int MAX_ITEMS = 20;
        private static int producedCount = 0;
        private static int consumedCount = 0;

        static class Producer implements Runnable {
            private int id;

            public Producer(int id) {
                this.id = id;
            }

            @Override
            public void run() {
                try {
                    while (producedCount < MAX_ITEMS) {
                        int item = produceItem();
                        empty.acquire();
                        mutex.acquire();
                        buffer[in] = item;
                        in = (in + 1) % BUFFER_SIZE;
                        producedCount++;
                        System.out.println("Producer " + id + " produced item " + item + " (Total produced: " + producedCount + ")");
                        mutex.release();
                        full.release();
                        Thread.sleep(100);
                    }
                    System.out.println("Producer " + id + " finished.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            private int produceItem() {
                return (int) (Math.random() * 100);
            }
        }

        static class Consumer implements Runnable {
            private int id;

            public Consumer(int id) {
                this.id = id;
            }

            @Override
            public void run() {
                try {
                    while (consumedCount < MAX_ITEMS) {
                        full.acquire();
                        mutex.acquire();
                        int item = buffer[out];
                        out = (out + 1) % BUFFER_SIZE;
                        consumedCount++;
                        System.out.println("Consumer " + id + " consumed item " + item + " (Total consumed: " + consumedCount + ")");
                        mutex.release();
                        empty.release();
                        Thread.sleep(100);
                    }
                    System.out.println("Consumer " + id + " finished.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) {
            int n = 3;
            int m = 3;

            for (int i = 0; i < n; i++) {
                new Thread(new Producer(i)).start();
            }

            for (int i = 0; i < m; i++) {
                new Thread(new Consumer(i)).start();
            }
        }

    }

