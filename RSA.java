/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa;

/**
 *
 * @author Moolfel
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Math.sqrt;
import java.util.Scanner;

public class RSA {//RSA CLASS DECLARATION

    public static void main(String[] args) throws IOException {//MAIN FUNCTION
        Scanner W = new Scanner(System.in);//scannerto capture inputs
        int p, q, n, t, e, d;    //variable declaration     
        int[] encryptedText = new int[100];
        int[] decryptedText = new int[100];
        boolean x, y; //flags used for checking if its prime number and if positive
        String msg;

        System.out.println("Welcome to RCA program");
        //ENTERING A PRIME NUMBER  P AND CHECKINF IF PRIME NUMBER AND POSITIVE
        do {
            System.out.println("Enter a Prime number  p :");
            p = W.nextInt();
            x = isPrime(p);
            y = isPositive(p);
            if ((x & y) == false) {
                System.out.println("\nWRONG INPUT (This number must Prime. \n A prime number is a natural number greater than 1 \nthat has no positive divisors other than 1 and itself)\n");
            }
            //ENTERING A PRIME NUMBER  Q AND CHECKINF IF PRIME NUMBER AND POSITIVE
        } while ((x & y) == false);
        do {
            System.out.println("Enter a Prime number  q :");
            q = W.nextInt();
            x = isPrime(q);
            y = isPositive(q);
            if ((x & y) == false) {
                System.out.println("\nWRONG INPUT (This number must Prime. \n A prime number is a natural number greater than 1 \nthat has no positive divisors other than 1 and itself)\n");
            }
        } while ((x & y) == false);

        //CALCULATING N AS P*Q AND (P-1)(Q-1)
        n = p * q;     //ITS USED WHILE FINDING THE KEY
        System.out.println("\nResult of computing n = p*q = " + n);
        t = (p - 1) * (q - 1);
        System.out.println("Result of Q(n):\t  = " + t);
        e = calculateE(t);
        d = calculateD(e, t);
        System.out.println("\nRSA public key is (n = " + n + ", e = " + e + ")");
        System.out.println("\nRSA private key is (n = " + n + ", d = " + d + ")");

        BufferedReader ob1 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nEnter Message to be encryped:");
        msg = ob1.readLine();
        System.out.println("\nThe message is: " + msg);

        // encryption 
        for (int i = 0; i < msg.length(); i++) {
            encryptedText[i] = encrypt(msg.charAt(i), e, n);
        }
        System.out.println("\nTHE ENCRYPTED MESSAGE IS:");
        for (int i = 0; i < msg.length(); i++) {
            System.out.print((char) encryptedText[i]);
        }

        //decryption 
        for (int i = 0; i < msg.length(); i++) {
            decryptedText[i] = decrypt(encryptedText[i], d, n);
        }
        System.out.print("\n\nTHE DECRYPTED MESSAGE IS: ");
        for (int i = 0; i < msg.length(); i++) {
            System.out.print((char) decryptedText[i]);
        }

        System.out.println();

    }

//greatestCommonDivisor class
    private static int greatestCommonDivisor(int e, int t) {

        while (e > 0) {
            int myTemp;
            myTemp = e;
            e = t % e;
            t = myTemp;
        }
        return t;
    }

    //Primitivity  class to check if entered number is prime number
    private static boolean isPrime(int prime) {

        int i, j;
        j = (int) sqrt((double) prime);
        for (i = 2; i <= j; i++) {
            if (prime % i == 0) {
                return false;
            }
        }
        return true;
    }

    //Positivity class to check if a number entered is positive
    private static boolean isPositive(int positive) {
        if (positive > 0) {
            return true;
        } else {
            return false;
        }
    }

    //calculating E to be used while calculating the greatest common divisor
    private static int calculateE(int t) {

        int e;
        for (e = 2; e < t; e++) {
            if (greatestCommonDivisor(e, t) == 1) {
                return e;
            }
        }
        return -1;
    }

    //calculating D to be used while finding the key
    private static int calculateD(int e, int t) {

        int d;
        int k = 1;
        while (true) {
            k = k + t;
            if (k % e == 0) {
                d = (k / e);
                return d;
            }
        }

    }

    //encryption  class to be used for encrypting
    private static int encrypt(char i, int e, int n) {

        int current, result;
        current = i - 97;
        result = 1;
        for (int j = 0; j < e; j++) {
            result = result * current;
            result = result % n;
        }
        return result;
    }

    //decryption  class to be called while decrypting
    private static int decrypt(int u, int f, int m) {

        int current, result;
        current = u;
        result = 1;
        for (int j = 0; j < f; j++) {
            result = result * current;
            result = result % m;
        }
        return result + 97;
    }
}
