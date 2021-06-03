package com.dove.multithread;

public class Demo {
    PrintProcessor printProcessor;

    public Demo() {
        SaveProsessor saveProsessor = new SaveProsessor();
        saveProsessor.start();
        printProcessor = new PrintProcessor(saveProsessor);
        printProcessor.start();
    }

    public static void main(String[] args) {

        Request request = new Request("testrequest");
        Demo demo = new Demo();
        demo.doTest(request);
    }

    public  void doTest(Request request){
        printProcessor.processRequest(request);
    }
}
