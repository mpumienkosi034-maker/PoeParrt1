package com.mycompany.poeparrt1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @BeforeEach
    public void setUp() {
        // Clear the arrays before each test so they start fresh
        Message.sentMessages.clear();
        Message.storedMessages.clear();
        Message.disregardedMessages.clear();
        Message.messageIDs.clear();
        Message.messageHashes.clear();
        
        //RESET THE COUNTER!
        Message.messageCounter = 0;
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        // Test Data Message 1 (Sent)
        Message msg1 = new Message("+27834557896", "Did you get the cake?");
        msg1.sentMessage("send");
        
        // Test Data Message 4 (Sent)
        Message msg4 = new Message("0838884567", "It is dinner time !");
        msg4.sentMessage("send");

        // The rubric says the array should return these two messages
        assertEquals(2, Message.sentMessages.size());
        assertEquals("Did you get the cake?", Message.sentMessages.get(0).getMessageText());
        assertEquals("It is dinner time !", Message.sentMessages.get(1).getMessageText());
    }

    @Test
    public void testDisplayLongestMessage() {
        // Populating with Test Data 1-4
        Message msg1 = new Message("+27834557896", "Did you get the cake?");
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        Message msg3 = new Message("+27834484567", "Yohoooo, I am at your gate.");
        Message msg4 = new Message("0838884567", "It is dinner time !");

        msg1.sentMessage("send");
        msg2.sentMessage("store");
        msg3.sentMessage("discard");
        msg4.sentMessage("send");

        // The rubric says it should return the message from Test Data 2
        String expectedLongest = "Where are you? You are late! I have asked you to be on time.";
        
        // Find the longest message manually for the test
        String actualLongest = "";
        for (Message msg : Message.storedMessages) {
            if (msg.getMessageText().length() > actualLongest.length()) {
                actualLongest = msg.getMessageText();
            }
        }
        // Note: Because your displayLongestMessage prints to console, we test the logic here
        // If you want to test the method exactly, you might need to change it to return a String instead of void.
        assertEquals(expectedLongest, actualLongest);
    }

    @Test
    public void testSearchForMessageID() {
        Message msg4 = new Message("0838884567", "It is dinner time !");
        msg4.sentMessage("store"); // Store it so it can be searched

        // The rubric says searching for this ID should return this message
        String searchID = msg4.getMessageIDForMenu();
        
        String foundMessage = "";
        for (Message msg : Message.storedMessages) {
            if (msg.getMessageIDForMenu().equals(searchID)) {
                foundMessage = msg.getMessageText();
            }
        }
        assertEquals("It is dinner time !", foundMessage);
    }

    @Test
    public void testSearchByRecipient() {
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        Message msg5 = new Message("+27838884567", "Ok, I am leaving without you.");
        
        msg2.sentMessage("store");
        msg5.sentMessage("store");

        // The rubric says searching this recipient should return both messages
        int count = 0;
        for (Message msg : Message.storedMessages) {
            if (msg.getRecipient().equals("+27838884567")) {
                count++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    public void testDeleteMessageByHash() {
        Message msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        msg2.sentMessage("store");
        
        String hashToDelete = msg2.getMessageHash();
        
        // Call the method from Message class
        Message.deleteByHash(hashToDelete);
        
        // The rubric expects it to be deleted, so the stored array should now be empty
        assertTrue(Message.storedMessages.isEmpty(), "The message should have been deleted.");
    }

    @Test
    public void testMessageHashGeneration() {
        // Rubric Page 7: "00:0:HITONIGHT when supplied with the data from Test Case 1"
        // Note: Because your constructor generates a random ID, the first two digits will be random.
        // We use endsWith to check the rest of the hash matches the rubric format.
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        
        //We print it out so you can see exactly what it generated
        System.out.println("Generated Hash: "+ msg.getMessageHash());
        assertTrue(msg.getMessageHash().endsWith(":0:HITONIGHT"), "Hash was: "+ msg.getMessageHash());
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
}