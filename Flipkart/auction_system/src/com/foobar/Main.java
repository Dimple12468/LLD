package com.foobar;

import com.foobar.models.*;
import com.foobar.auctions.*;
import java.util.*;

public class Main {
    private static Map<String, Buyer> buyers = new HashMap<>();
    private static Map<String, Seller> sellers = new HashMap<>();
    private static Map<String, Auction> auctions = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter command (ADD_BUYER, ADD_SELLER, CREATE_AUCTION, CREATE_BID, UPDATE_BID, WITHDRAW_BID, CLOSE_AUCTION, GET_PROFIT, EXIT):");
            String command = scanner.nextLine().trim().toUpperCase();
            if (command.equals("EXIT")) {
                break;
            }
            handleCommand(command);
        }
    }

    private static void handleCommand(String command) {
        try {
            switch (command) {
                case "ADD_BUYER":
                    System.out.print("Enter Buyer Name: ");
                    String buyerName = scanner.nextLine().trim();
                    buyers.put(buyerName, new Buyer(buyerName));
                    System.out.println("Buyer added: " + buyerName);
                    break;

                case "ADD_SELLER":
                    System.out.print("Enter Seller Name: ");
                    String sellerName = scanner.nextLine().trim();
                    sellers.put(sellerName, new Seller(sellerName));
                    System.out.println("Seller added: " + sellerName);
                    break;

                case "CREATE_AUCTION":
                    System.out.print("Enter Auction ID: ");
                    String auctionId = scanner.nextLine().trim();
                    System.out.print("Enter Lowest Bid Limit: ");
                    double lowLimit = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Enter Highest Bid Limit: ");
                    double highLimit = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Enter Participation Cost: ");
                    double cost = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Enter Seller Name: ");
                    String auctionSeller = scanner.nextLine().trim();
                    if (!sellers.containsKey(auctionSeller)) {
                        System.out.println("Seller not found!");
                        return;
                    }
                    auctions.put(auctionId, new Auction(auctionId, lowLimit, highLimit, cost, sellers.get(auctionSeller)));
                    System.out.println("Auction created: " + auctionId);
                    break;

                case "CREATE_BID":
                    System.out.print("Enter Buyer Name: ");
                    String bidBuyer = scanner.nextLine().trim();
                    System.out.print("Enter Auction ID: ");
                    String bidAuction = scanner.nextLine().trim();
                    System.out.print("Enter Bid Amount: ");
                    double bidAmount = Double.parseDouble(scanner.nextLine().trim());
                    if (buyers.containsKey(bidBuyer) && auctions.containsKey(bidAuction)) {
                        boolean success = auctions.get(bidAuction).placeBid(buyers.get(bidBuyer), bidAmount);
                        System.out.println(success ? "Bid placed successfully!" : "Invalid bid amount!");
                    } else {
                        System.out.println("Buyer or auction not found!");
                    }
                    break;

                case "UPDATE_BID":
                    System.out.print("Enter Buyer Name: ");
                    String updateBuyer = scanner.nextLine().trim();
                    System.out.print("Enter Auction ID: ");
                    String updateAuctionId = scanner.nextLine().trim();
                    System.out.print("Enter New Bid Amount: ");
                    double newBid = Double.parseDouble(scanner.nextLine().trim());

                    if (!buyers.containsKey(updateBuyer)) {
                        System.out.println("Buyer not found!");
                        return;
                    }
                    if (!auctions.containsKey(updateAuctionId)) {
                        System.out.println("Auction not found!");
                        return;
                    }

                    auctions.get(updateAuctionId).updateBid(buyers.get(updateBuyer), newBid);
                    break;
                
                case "WITHDRAW_BID":
                    System.out.print("Enter Buyer Name: ");
                    String withdrawBuyer = scanner.nextLine().trim();
                    System.out.print("Enter Auction ID: ");
                    String withdrawAuctionId = scanner.nextLine().trim();

                    if (!buyers.containsKey(withdrawBuyer)) {
                        System.out.println("Buyer not found!");
                        return;
                    }
                    if (!auctions.containsKey(withdrawAuctionId)) {
                        System.out.println("Auction not found!");
                        return;
                    }

                    auctions.get(withdrawAuctionId).withdrawBid(buyers.get(withdrawBuyer));
                    break;

                case "CLOSE_AUCTION":
                    System.out.print("Enter Auction ID: ");
                    String closeAuctionId = scanner.nextLine().trim();
                    if (auctions.containsKey(closeAuctionId)) {
                        Buyer winner = auctions.get(closeAuctionId).closeAuction();
                        System.out.println("Winner: " + (winner != null ? winner.getName() : "No Winner"));
                    } else {
                        System.out.println("Auction not found!");
                    }
                    break;
                    
                case "GET_PROFIT":
                    System.out.print("Enter Seller Name: ");
                    String profitSeller = scanner.nextLine().trim();
                    System.out.print("Enter Auction ID: ");
                    String profitAuctionId = scanner.nextLine().trim();

                    if (!sellers.containsKey(profitSeller)) {
                        System.out.println("Seller not found!");
                        return;
                    }
                    if (!auctions.containsKey(profitAuctionId)) {
                        System.out.println("Auction not found!");
                        return;
                    }

                    double profit = auctions.get(profitAuctionId).calculateProfit();
                    profit = Math.round(profit * 10.0) / 10.0;
                    System.out.println("Profit for seller " + profitSeller + " in auction " + profitAuctionId + ": " + profit);
                    break;

                default:
                    System.out.println("Invalid command!");
            }
        } catch (Exception e) {
            System.out.println("Error processing command: " + e.getMessage());
        }
    }
}
