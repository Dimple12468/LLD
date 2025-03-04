package com.foobar.auctions;

import com.foobar.models.*;
import java.util.*;

public class Auction implements AuctionOperations {
    private String id;
    private double lowestBidLimit;
    private double highestBidLimit;
    private double participationCost;
    private Seller seller;
    private Map<Buyer, Double> bids = new HashMap<>();
    private AuctionStatus status;

    public Auction(String id, double lowestBidLimit, double highestBidLimit, double participationCost, Seller seller) {
        this.id = id;
        this.lowestBidLimit = lowestBidLimit;
        this.highestBidLimit = highestBidLimit;
        this.participationCost = participationCost;
        this.seller = seller;
        this.status = AuctionStatus.OPEN;
    }

    @Override
    public boolean placeBid(Buyer buyer, double amount) {
        if (status == AuctionStatus.CLOSED || amount < lowestBidLimit || amount > highestBidLimit) {
            return false;
        }
        bids.put(buyer, amount);
        buyer.participate(id);
        return true;
    }

    @Override
    public void updateBid(Buyer buyer, double amount) {
        if (bids.containsKey(buyer) && amount >= lowestBidLimit && amount <= highestBidLimit) {
            bids.put(buyer, amount);
        }
    }

    @Override
    public void withdrawBid(Buyer buyer) {
        bids.remove(buyer);
    }

    @Override
    public Buyer closeAuction() {
        status = AuctionStatus.CLOSED;
        Map<Double, Integer> bidCounts = new HashMap<>();
        for (double bid : bids.values()) {
            bidCounts.put(bid, bidCounts.getOrDefault(bid, 0) + 1);
        }

        double highestUniqueBid = -1;
        for (Map.Entry<Double, Integer> entry : bidCounts.entrySet()) {
            if (entry.getValue() == 1 && entry.getKey() > highestUniqueBid) {
                highestUniqueBid = entry.getKey();
            }
        }

        if (highestUniqueBid == -1) {
            return null;
        }

        Buyer winner = null;
        for (Map.Entry<Buyer, Double> entry : bids.entrySet()) {
            if (entry.getValue() == highestUniqueBid) {
                if (winner == null || (entry.getKey().isPreferred() && !winner.isPreferred())) {
                    winner = entry.getKey();
                }
            }
        }
        return winner;
    }

    @Override
    public double calculateProfit() {
        int bidders = bids.size();
        double participationShare = bidders * 0.2 * participationCost;

        Buyer winner = closeAuction();
        double winningBid = (winner != null) ? bids.get(winner) : 0;

        return winningBid + participationShare - (lowestBidLimit + highestBidLimit) / 2;
    }
}
