package com.easydynamics.calculator;

import com.easydynamics.calculator.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddServiceImpl implements AddService {

    @Autowired
    Util util;

    @Override
    public String add(String a, String b) {
        int  carryValue=0;

        String sumValue="";


        System.out.print("util-------------------"+util);

        String[] abVal = util.makeNumberEqualLength(a, b);

        a = abVal[0];
        b = abVal[1];

        for(int i=a.length() -1; i >= 0; i--){
            int aVal = Integer.parseInt(a.charAt(i)+"");
            int bVal = Integer.parseInt(b.charAt(i)+"");

            int [] writeAndCarry = addNumbers(aVal, bVal, carryValue);

            sumValue += writeAndCarry[0];
            carryValue = writeAndCarry[1];
        }

        carryValue=0;
        return new StringBuilder(sumValue).reverse().toString();
    }



    private  int[] addNumbers(int first, int second, int carry){

        int writeValue=0;
        int carryValue=0;

        int totalVal= first + second + carry;

        writeValue = totalVal % 10;
        carryValue = totalVal / 10;

        int[] writeAndCarryVal = {writeValue, carryValue};

        return writeAndCarryVal;
    }
}
