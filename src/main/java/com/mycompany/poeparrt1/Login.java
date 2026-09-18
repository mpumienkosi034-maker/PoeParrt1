/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poeparrt1;

/**
 *
 * @author Student
 */
public class Login extends Registrationn {
    
    public boolean loginUser (String name, String password){
        return name.equals(userName) && password.equals(Password);
    }
    
    public String returnLoginStatus(boolean status){
        if (status){
            return "Login success";
            
        }else { 
            return "Login failed";
        }
    }

   
       
 }

    
 
    
