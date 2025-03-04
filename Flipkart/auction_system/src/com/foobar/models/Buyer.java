package com.foobar.models;

import java.util.HashSet;
import java.util.Set;

public class Buyer extends User {
    private Set<String> participatedAuctions = new HashSet<>();

    public Buyer(String name) {
        super(name);
    }

    public void participate(String auctionId) {
        participatedAuctions.add(auctionId);
    }

    public boolean isPreferred() {
        return participatedAuctions.size() > 2;
    }
}
