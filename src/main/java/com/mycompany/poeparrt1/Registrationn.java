/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poeparrt1;

/**
 *
 * @author Student
 */
public class Registrationn { //start of class
    
    String userName;
    String Password;
    String CellPhone;
    
    public boolean checkUserName(String username){ //start of username method
        return username.contains("@") && username.length()==3;
    
    } //end of username method
    
    public boolean checkPasswordComplexity(String password){ //start of password method
        return password.length() >=8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[a-z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[^a-zA-Z0-9].*");
        
    } //end of password method
    
    public boolean checkCellPhoneNumber(String cellphone) {
    if (cellphone == null) return false;

    boolean matchesInternational = cellphone.matches("\\+27[0-9]{9}");
    boolean matchesLocal = cellphone.matches("[0-9]{9}");

    return matchesInternational || matchesLocal;
}
    
    public String registerUser (String username, String password, String cellphone){
        
        if(checkUserName (username) && checkPasswordComplexity(password) && checkCellPhoneNumber (cellphone)){
            this.userName = username;
            this.Password = password;
            this.CellPhone = cellphone;
            
        return "Registration was unsuccessful";
        }else{
            return "Registration was successful";
        }
    }
     public boolean registerStatus(String username, String password, String cellphone){
         return checkUserName(username) &&
                 checkPasswordComplexity(password) &&
                 checkCellPhoneNumber(cellphone);
                 
     }
    
} // end of class

