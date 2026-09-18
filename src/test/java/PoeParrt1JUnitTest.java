 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.poeparrt1.Login;
import com.mycompany.poeparrt1.Registrationn;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class PoeParrt1JUnitTest {
    
    public PoeParrt1JUnitTest() {
    }
    
    @Test
    public void testCheckUserNameValid() {
        Registrationn registration = new Registrationn();
        
        assertTrue(registration.checkUserName("a@b"));
    }
    
    @Test
    public void testCheckUserNameinValid() {
        Registrationn registration = new Registrationn();
        
        assertFalse(registration.checkUserName("a77b"));
    }
    
    
    @Test
    public void testCheckPasswordComplexityValid(){
        Registrationn registration = new Registrationn();
        
        assertTrue(registration.checkPasswordComplexity("Password1!"));
        
    }
    
    @Test
    public void testCheckPasswordComplexityinValid(){
        Registrationn registration = new Registrationn();
        
        assertFalse(registration.checkPasswordComplexity("Pas!"));
    }    
    
    
    @Test
    public void testCheckCellPhoneValid(){
        Registrationn registration = new Registrationn();
        
        assertTrue(registration.checkCellPhoneNumber("+27606511872"));
        
    }
    
    @Test
    public void testCheckCellPhoneinValid(){
        Registrationn registration = new Registrationn();
        
        assertFalse(registration.checkCellPhoneNumber("+2772344"));
    
    }
    @Test
    public void testRegisterUserValid() {
        Registrationn registration = new Registrationn();
        
        String result = registration.registerUser(
                "a@b",
                "Password1!",
                "+27606511872"
                
        );  
        
        assertEquals("Registration was unsuccessful", result);
        
    }
    
    @Test
    public void testRegisterUserinValid() {
        Registrationn registration = new Registrationn();
        
        String result = registration.registerUser(
                "a@b",
                "Password1!",
                "+27606511872"
                
        );  
        
        assertEquals("Registration was unsuccessful", result);
    }
    
    
    
    @Test 
    public void testRegisterStatusValid(){
        Registrationn registration = new Registrationn();
        
        boolean result = registration.registerStatus(
                "a@b",
                "Password1!",
                "+27606511872"
        );
        
        assertTrue(result);
    }
    
    @Test
    public void testRegisterStatusinValid(){
        Registrationn registration = new Registrationn();
        
        boolean result = registration.registerStatus(
                "a77b",
                "pas1",
                "+27 60761"
        );
        
        assertFalse(result);
    }
    
    
    
    @Test
    public void testLoginUserValid(){
        Login login = new Login();
        
        login.registerUser(
                "a@b",
                "Password1!",
                "+27606511872"
        );
        
        assertTrue(login.loginUser("a@b", "Password1!"));
    }
    
    @Test
    public void testLoginUserinValid(){
        Login login = new Login();
        
        login.registerUser(
                "a77b",
                "pas1!",
                "+271872"
        );
        
        assert(login.loginUser("a77b", "Pas1!"));
    
    }
    @Test
    public void testReturnLoginStatusSuccess() {
        Login login = new Login();
        
        assertEquals("Login success", login.returnLoginStatus(true));
    }
    
    @Test 
    public void testReturnLoginStatusFailure(){
        Login login = new Login();
        
        assertEquals("Login failed", login.returnLoginStatus(false));
    }
}
