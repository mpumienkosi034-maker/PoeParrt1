package com.mycompany.poeparrt1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageHash() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String hash = msg.createHash();
        assertTrue(hash.endsWith(":0:HITONIGHT"), "The hash should end with :0:HITONIGHT");
    }

    @Test
    public void testRecipientCellSuccess() {
        Message msg = new Message("+27718693002", "Hello");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        Message msg = new Message("08575975889", "Hello");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testSentMessage() {
        Message msg = new Message("+27718693002", "Hello");
        String response = msg.sentMessage("send");
        assertEquals("Message successfully sent.", response);
    }
    
    @Test
    public void testCheckMessageID() {
        Message msg = new Message("+27718693002", "Hello");
        assertTrue(msg.checkMessageID(), "Message ID should be 10 characters or less");
    }
}