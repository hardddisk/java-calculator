package com.easydynamics.calculator.util;

import org.springframework.stereotype.Component;

@Component
public class Util {


    public  String[] makeNumberEqualLength(String a, String b){

        String zeros="";

        if(!isNumLengthEqual( a,  b)){
            if(findLongestNumber( a,  b)){
                int diff= a.length() - b.length();
                zeros = zerosToEqualizeNumbersLength(diff);
                b=zeros+b;
            }else{
                int diff = b.length() - a.length();
                zeros = zerosToEqualizeNumbersLength(diff);
                a=zeros+a;
            }
        }
        String [] abValue = {a, b};
        return abValue;

    }


    public  boolean isNumLengthEqual(String a, String b){
        if(a.length() == b.length()){
            return true;
        }else{
            return false;
        }
    }


    public  boolean findLongestNumber(String a, String b){
        if( a.length() >= b.length()){
            return true;
        }else{
            return false;
        }

    }


    public  String zerosToEqualizeNumbersLength(int equalize){
        String zeros="";
        for(int i=0; i<equalize; i++){
            zeros += "0";
        }
        return zeros;
    }
}
