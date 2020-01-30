package com.easydynamics.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class CalculatorService {


    public static String add(String a, String b) {




         int  carryValue=0;

        String sumValue="";

        String[] abVal = makeNumberEqualLength( a, b);

        a = abVal[0];
        b = abVal[1];

        System.out.println("--=---"+a);
        System.out.println("--=---"+b);

        for(int i=a.length() -1; i >= 0; i--){
            int aVal = Integer.parseInt(a.charAt(i)+"");
            int bVal = Integer.parseInt(b.charAt(i)+"");

            int [] writeAndCarry = addNumbers(aVal, bVal, carryValue);

            sumValue += writeAndCarry[0];
            carryValue = writeAndCarry[1];

            if(i==0){
                sumValue+=carryValue;
            }

            System.out.println("sumValue:   "+sumValue);
            System.out.println("carryValue: "+carryValue);
        }



        return new StringBuilder(sumValue).reverse().toString();
    }



    public static String subtract(String a, String b) {


        String sumValue = "";


        int diff = a.length() - b.length();


        int carryValue = 0;


        for (int i = b.length() - 1; i >= 0; i--) {

            int sub = (((int)a.charAt(i + diff) - (int)'0') -
                    ((int)b.charAt(i) - (int)'0') - carryValue);
            if (sub < 0) {
                sub = sub+10;
                carryValue = 1;
            }
            else
                carryValue = 0;

            sumValue += String.valueOf(sub);
        }

        // subtract remaining digits of a[]
        for (int i = a.length() - b.length() - 1; i >= 0; i--) {
            if (a.charAt(i) == '0' && carryValue > 0) {
                sumValue += "9";
                continue;
            }
            int sub = (((int)a.charAt(i) - (int)'0') - carryValue);
            if (i > 0 || sub > 0) {// remove preceding 0's
                sumValue += String.valueOf(sub);
            }
            carryValue = 0;

        }

        return new StringBuilder(sumValue).reverse().toString();


    }


    public static int [] getCarryAddingValue( int value){

        int carryVal = value / 10;
        int valueToAdd = value % 10;

        return new int []{carryVal, valueToAdd};
    }




    public static String divide(String a, String b) {


        String resultVal = "";


        int i = 0;
        char []num = a.toCharArray();
        int temp = num[i] - '0';

        while (temp < Integer.parseInt(b)) {
            temp = temp * 10 + (num[++i] - '0');
        }

        i +=1;


        while (num.length > i){
            // Store result in answer i.e. temp / divisor
            resultVal += (temp / Integer.parseInt(b)) ;


            temp = (temp % Integer.parseInt(b)) * 10 + num[i++] - '0';
        }



        // else return ans
        return resultVal;
    }


    private static boolean isNumZero(String num){
        boolean numZero = true;

        if(num.length() >= 1){
            for(int i=0; i<num.length(); i++){
                if( Integer.parseInt(num.charAt(i)+"")>0){
                    numZero = false;
                    break;
                }

                System.out.println("i: "+i);
            }
        }

        return numZero;
    }


    /**
     * this method will add first number, second number with carry value so we will have total number when we do addtion.
     * @param first  the value of that will be added
     * @param second the value that will be added with the first number
     * @param carry if there was any carry value from the previous addtion.
     * @return the the number that we will be saving as a result as well as if there is a carry value if the addtion is greater than or equal to 10 in a array 
     */
    private static int[] addNumbers(int first, int second, int carry){

        int writeValue=0;
        int carryValue=0;

        int totalVal= first + second + carry;

        writeValue = totalVal % 10;
        carryValue = totalVal / 10;

        int[] writeAndCarryVal = {writeValue, carryValue};

        return writeAndCarryVal;
    }


    private static boolean findLongestNumber(String a, String b){
        if( a.length() >= b.length()){
            return true;
        }else{
            return false;
        }

    }


    /**
     * this method will tell me if a and be have same length
     * @param a  first number as a string
     * @param b  second number as a string
     * @return return true if both are "1234" and "4321" or "8888" "1111"
     */
    private static boolean isNumLengthEqual(String a, String b){
        if(a.length() == b.length()){
            return true;
        }else{
            return false;
        }
    }


    /**
     * This method is used for addtion. if both number are the same size it will be easier to add them so we do not have to code for shorter numbers.
     * example: 9999 + 99  the application will write it 9999 + 0099
     * @param a first number as String
     * @param b second number as String
     * @return  the value of a and b with an array
     */
    private static String[] makeNumberEqualLength(String a, String b){

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
        String [] abValue = { a, b };
        return abValue;

    }

    /**
     * this is strictly is helping method which return how many zeros are needed in order to make a and b equal
     * @param equalize how many zero needed in order to make a or b equal to one another.
     * @return zeros as String format.
     */
    private static String zerosToEqualizeNumbersLength(int equalize){
        String zeros="";
        for(int i=0; i<equalize; i++){
            zeros += "0";
        }
        return zeros;
    }


    /**
     * This method will say  if the a  is smaller than b if so it will return true else false
     * @param a number as String
     * @param b number as String
     * @return return boolean value based on the comparison.
     */

    static boolean isSmaller(String a, String b) {
        if (a.length() < b.length()) {
            return true;
        }else if (b.length() < a.length()) {
            return false;
        }

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) < b.charAt(i)) {
                return true;
            } else if (a.charAt(i) > b.charAt(i)) {
                return false;
            }
        }
        return false;
    }


    /*--------
    MULTIPLICATON
     */


    public static String multiply(String a, String b) {


        // if one of them is zero then just return value zero for any number multiplied by 0 will get you zero.
        if ( a.length() == 0 ||  b.length() == 0  || a.equals("0") || b.equals("0")) {
            return "0";
        }

        int resultArraySize = a.length()+b.length();

        int resultHolder[] = new int[resultArraySize];


        // find positions in resultHolder array.
        int firstIndex = 0;
        int secondIndex = 0;

        // Go from right to left in a
        for (int i = a.length() - 1; i >= 0; i--) {
            int carryValue = 0;
            int n1 = a.charAt(i) - '0';

            // To shift position to left after every
            // multipliccharAtion of a digit in b
            secondIndex = 0;

            // Go from right to left in b
            for (int j = b.length() - 1; j >= 0; j--)
            {
                // Take current digit of second number
                int n2 = b.charAt(j) - '0';

                // Multiply with current digit of first number
                // and add result to previously stored result
                // charAt current position.
                int sum = n1 * n2 + resultHolder[firstIndex + secondIndex] + carryValue;

                // carryValue for next itercharAtion
                carryValue = sum / 10;

                // save  result
                resultHolder[firstIndex + secondIndex] = sum % 10;

                secondIndex++;
            }

            // save the carryValue  in next cell if it is greater than carryValue
            if (carryValue > 0) {
                resultHolder[firstIndex + secondIndex] += carryValue;
            }

            // To shift position to left after every
            // multipliccharAtion of a digit in a.
            firstIndex++;
        }

        // ignore all zeros from the right
        int i = resultHolder.length - 1;
        while (i >= 0 && resultHolder[i] == 0) {
            i--;
        }

        // create the answer value from  the result holder array
        String answerValue = "";

        while (i >= 0) {
            answerValue += (resultHolder[i--]);
        }

        return answerValue;
    }


    public static void addingZeroInTheBack(String[] value){

        String zeros="";

        String [] zeroValue = new String[value.length];

        java.util.Arrays.fill(zeroValue,"");


        for(int i=0; i<value.length; i++){
            System.out.println("zeros: "+zeros);

            zeroValue[i]+=zeros;
            zeros+="0";
        }


        for(int i=0; i<value.length; i++){
            value[i]+=zeroValue[i];
            System.out.println("value: "+value[i]);
        }
    }


    public static String swap(String str, int i, int j) {
        char ch[] = str.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return  String.valueOf(ch);
    }
}