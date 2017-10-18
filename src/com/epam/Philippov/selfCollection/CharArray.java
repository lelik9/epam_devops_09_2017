package com.epam.Philippov.selfCollection;

import java.util.Arrays;

public class CharArray extends Array{
    public CharArray(Character[] array) {
        super(array);
    }

    @Override
    public Character[] getSet(){
        CharArray newArray = new CharArray(new Character[0]);
        int i = 0;

        for (Object o : toArray()) {
            if (!newArray.contains(o)) {
                newArray.add(o.toString().toCharArray()[0]);
                i += 1;
            }
        }
        return (Character[]) newArray.toArray();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (Object o : toArray()) {
            str.append(o.toString());
        }
        return str.toString();
    }

    public void add(Object value){
        Object[] newArr = new Character[toArray().length+1];
        Arrays.fill(toArray(),0, toArray().length, newArr);
        newArr[newArr.length-1] = value;
        setArray(newArr);
    }

}
