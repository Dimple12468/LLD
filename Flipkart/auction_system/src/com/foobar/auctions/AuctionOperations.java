package com.foobar.auctions;

import com.foobar.models.Buyer;

public interface AuctionOperations {
    boolean placeBid(Buyer buyer, double amount);
    void updateBid(Buyer buyer, double amount);
    void withdrawBid(Buyer buyer);
    Buyer closeAuction();
    double calculateProfit();
}
