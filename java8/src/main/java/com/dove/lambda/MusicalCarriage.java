package com.dove.lambda;

public class MusicalCarriage implements Jukebox, Carriage{

    @Override
    public String rock() {
        //指明使用 Carriage的 rock 方法
        return Carriage.super.rock();
    }
    

}
