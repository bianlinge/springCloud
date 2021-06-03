package com.dove.multithread;


import java.util.concurrent.LinkedBlockingQueue;

public class PrintProcessor extends Thread implements RequestProcessor  {

    LinkedBlockingQueue<Request> linkedBlockingQueue = new LinkedBlockingQueue<>();

    private final RequestProcessor nextProcesser;

    public PrintProcessor(RequestProcessor nextProcesser){
        this.nextProcesser = nextProcesser;
    }

    @Override
    public void processRequest(Request request) {
        linkedBlockingQueue.add(request);
    }


    @Override
    public void run() {
        while (true){
            try {
                Request request = linkedBlockingQueue.take();
                System.out.println("print++++++"+request.getName());
                nextProcesser.processRequest(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
