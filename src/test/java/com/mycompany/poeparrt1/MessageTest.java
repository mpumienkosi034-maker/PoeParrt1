package com.mycompany.poeparrt1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    // Test 1: Check Message Hash logic
    @Test
    public void testMessageHash() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        
        // Check if the hash ends with :0:HITONIGHT
        String hash = msg.createHash();
        assertTrue(hash.endsWith(":0:HITONIGHT"), "The hash should end with :0:HITONIGHT");
    }

    // Test 2: Check Valid Recipient Cell
    @Test
    public void testRecipientCellSuccess() {
        Message msg = new Message("+27718693002", "Hello");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    // Test 3: Check Invalid Recipient Cell
    @Test
    public void testRecipientCellFailure() {
        Message msg = new Message("08575975889", "Hello");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    // Test 4: Check SentMessage logic
    @Test
    public void testSentMessage() {
        Message msg = new Message("+27718693002", "Hello");
        String response = msg.sentMessage("send");
        assertEquals("Message successfully sent.", response);
    }
    
    // Test 5: Check Message ID length
    @Test
    public void testCheckMessageID() {
        Message msg = new Message("+27718693002", "Hello");
        assertTrue(msg.checkMessageID(), "Message ID should be 10 characters or less");
    }
}