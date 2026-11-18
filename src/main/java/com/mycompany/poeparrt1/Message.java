package com.mycompany.poeparrt1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class Message {

    // Instance Variables
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Static Counters and Arrays (Part 3 Requirement 1)
    private static int totalMessagesSent = 0;
    public static int messageCounter = 0; 
    
    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static ArrayList<Message> storedMessages = new ArrayList<>();
    public static ArrayList<Message> disregardedMessages = new ArrayList<>();
    
    // Specific arrays for IDs and Hashes required by the rubric
    public static ArrayList<String> messageIDs = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    //Empty constructor required to Gson to read the JSON file
    public Message(){
    }
    // Constructor
    public Message(String recipient, String messageText) {
        this.messageID = generateID();
        this.messageNumber = messageCounter; 
        messageCounter++; 
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createHash();
        
        // Add ID and Hash to arrays immediately upon creation
        messageIDs.add(this.messageID);
        messageHashes.add(this.messageHash);
    }

    // Generate random 10-digit ID
    private String generateID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // FIXED: Strictly requires international code (+ and 10-11 digits) to satisfy Part 3 tests
    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("^\\+\\d{10,11}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // FIXED: Matches the exact hash format required (e.g., 00:0:HITONIGHT)
    public String createHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        
        String firstWord = words[0].replaceAll("[^A-Za-z]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^A-Za-z]", "").toUpperCase();
        
        return firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // Method to handle Send, Store, or Discard
    public String sentMessage(String action) {
        if (action.equalsIgnoreCase("send") || action.equals("1")) {
            totalMessagesSent++;
            sentMessages.add(this);
            saveToJson();
            return "Message successfully sent.";
        } else if (action.equalsIgnoreCase("store") || action.equals("3")) {
            storedMessages.add(this);
            saveToJson();
            return "Message successfully stored.";
        } else if (action.equalsIgnoreCase("discard") || action.equals("2") || action.equals("0")) {
            disregardedMessages.add(this);
            return "Press 0 to delete the message.";
        } else {
            return "Invalid action.";
        }
    }

    // Save to JSON file
    private static void saveToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String fileName = "messages.json";
        
        try {
            ArrayList<Message> allMessages = new ArrayList<>();
            allMessages.addAll(sentMessages);
            allMessages.addAll(storedMessages);
            
            FileWriter writer = new FileWriter(fileName);
            gson.toJson(allMessages, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving to JSON: " + e.getMessage());
        }
    }

    // NEW: Load from JSON file into StoredMessages array (Part 3 Requirement 1)
    public static void loadMessagesFromJson() {
        Gson gson = new Gson();
        String fileName = "messages.json";
        
        try (FileReader reader = new FileReader(fileName)) {
            Type listType = new TypeToken<ArrayList<Message>>() {}.getType();
            ArrayList<Message> loadedMessages = gson.fromJson(reader, listType);
            
            if (loadedMessages != null) {
                storedMessages.clear();
                // Only add messages that aren't already in the array to avoid duplicates
                for (Message msg : loadedMessages) {
                    storedMessages.add(msg);
                }
            }
        } catch (IOException e) {
            System.out.println("No existing JSON file found or error reading: " + e.getMessage());
        }
    }

    // ==========================================
    // PART 3 REQUIREMENT 2: STORED MESSAGES MENU METHODS
    // ==========================================

    // 2a. Display sender and recipient of all stored messages
    public static void displayStoredMessages() {
        System.out.println("\n--- Stored Messages ---");
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages.");
            return;
        }
        for (Message msg : storedMessages) {
            System.out.println("Sender: Developer | Recipient: " + msg.recipient + " | Message: " + msg.messageText);
        }
    }

    // 2b. Display the longest stored message
    public static void displayLongestMessage() {
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages.");
            return;
        }
        Message longest = storedMessages.get(0);
        for (Message msg : storedMessages) {
            if (msg.messageText.length() > longest.messageText.length()) {
                longest = msg;
            }
        }
        System.out.println("\nLongest Stored Message: " + longest.messageText);
    }

    // 2c. Search for a message ID and display recipient and message
    public static void searchByMessageID(String id) {
        for (Message msg : storedMessages) {
            if (msg.messageID.equals(id)) {
                System.out.println("\nMessage Found:");
                System.out.println("Recipient: " + msg.recipient);
                System.out.println("Message: " + msg.messageText);
                return;
            }
        }
        System.out.println("Message ID not found in stored messages.");
    }

    // 2d. Search for all messages stored for a particular recipient
    public static void searchByRecipient(String recipient) {
        boolean found = false;
        System.out.println("\nMessages for recipient: " + recipient);
        for (Message msg : storedMessages) {
            if (msg.recipient.equals(recipient)) {
                System.out.println("- " + msg.messageText);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found for this recipient.");
        }
    }

    // 2e. Delete a message using the message hash
    public static void deleteByHash(String hash) {
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).messageHash.equals(hash)) {
                String deletedMessage = storedMessages.get(i).messageText;
                storedMessages.remove(i);
                saveToJson(); // Update the JSON file
                System.out.println("Message: \"" + deletedMessage + "\" successfully deleted.");
                return;
            }
        }
        System.out.println("Message hash not found.");
    }

    // 2f. Display a report that lists full details of all stored messages
    public static void displayReport() {
        System.out.println("\n=== STORED MESSAGES REPORT ===");
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages to report.");
            return;
        }
        for (Message msg : storedMessages) {
            System.out.println("Message Hash: " + msg.messageHash);
            System.out.println("Recipient:    " + msg.recipient);
            System.out.println("Message:      " + msg.messageText);
            System.out.println("------------------------------");
        }
    }

    // Getters
    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    public String getMessageIDForMenu() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }
    
    public String getRecipient() {
        return recipient;
    }
    
    public String getMessageText() {
        return messageText;
    }
}