/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poeparrt1;

import java.util.Scanner;

/*

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class PoeParrt1 {
    
    /**
     * 
     * @param args the command line arguments
     */

    public static void main(String[] args) {
    //TODO code application logic here
                 Login register = new Login();
                 Scanner scan = new Scanner(System.in);
                 
                 
                 System.out.println("\n=====Register=====");
                 
             System.out.println("Please enter the username");
            String name = scan.nextLine();
            
            
             System.out.println("Please enter the passowrd");
             String password = scan.nextLine();
             
             System.out.println("Please enter cellphone number");
             String cellphone = scan.nextLine();
             
             System.out.println("register.registerUser(name, password, cellphone");
             
             if(!register.registerStatus(name, password, cellphone)){
             }
    }
             
             
    }
