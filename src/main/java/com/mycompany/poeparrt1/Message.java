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

    // Variables for the message
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Variables to keep track of counts and arrays
    private static int totalMessages = 0;
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();
    private static ArrayList<Message> disregardedMessages = new ArrayList<>();

    // Constructor (Creates a new message)
    public Message(String recipient, String messageText) {
        this.messageID = generateID();
        this.messageNumber = totalMessages;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createHash();
    }

    // Method to generate a random 10-digit ID
    private String generateID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }

    // Method to check if ID is 10 chars or less
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Method to check if cellphone is correct
    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("^\\+\\d{1,11}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Method to create the Message Hash
    public String createHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        
        String firstWord = words[0].replaceAll("[^A-Za-z]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^A-Za-z]", "").toUpperCase();
        
        // Example format: 00:0:HITONIGHT
        return firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // Method to handle Send, Store, or Discard
    public String sentMessage(String action) {
        if (action.equalsIgnoreCase("send")) {
            totalMessages++;
            sentMessages.add(this);
            saveToJson();
            return "Message successfully sent.";
        } else if (action.equalsIgnoreCase("store")) {
            storedMessages.add(this);
            saveToJson();
            return "Message successfully stored.";
        } else if (action.equalsIgnoreCase("discard")) {
            disregardedMessages.add(this);
            return "Message disregarded.";
        } else {
            return "Invalid action.";
        }
    }

    // Method to save to JSON file using Gson
    private void saveToJson() {
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

    // Print details of this message
    public void printMessageDetails() {
        System.out.println("Message ID:    " + messageID);
        System.out.println("Message Hash:  " + messageHash);
        System.out.println("Recipient:     " + recipient);
        System.out.println("Message:       " + messageText);
    }

    // Get total messages sent
    public static int returnTotalMessages() {
        return totalMessages;
    }
        public String getMessageIDForMenu() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }
}