package com.dove.multithread;

import java.util.concurrent.LinkedBlockingQueue;

public class SaveProsessor extends Thread implements RequestProcessor {
    LinkedBlockingQueue<Request> linkedBlockingQueue = new LinkedBlockingQueue<>();

    @Override
    public void processRequest(Request request) {
        linkedBlockingQueue.add(request);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request take = linkedBlockingQueue.take();
                System.out.println("save++++++" + take.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
