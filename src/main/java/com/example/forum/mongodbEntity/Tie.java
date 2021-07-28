package com.example.forum.mongodbEntity;

public class Tie {

    private long tieId;


    Tie(){
        int [][] twoDmlArray = {{1,2,3}, {4,5}, {6,7,8,9}};
        int [] oneDmlArray = new int[100];
        int index = 0;
        for (int[] ints : twoDmlArray) {
            for (int anInt : ints) {
                oneDmlArray[index] = anInt;
                index++;
            }
        }

    }
}
