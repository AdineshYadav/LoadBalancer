package com.freightfox.dispatchLoadBalancer.model;

public enum Priority {
    HIGH(1),
    MEDIUM(2),
    LOW(3);

    private final int weight;

    Priority(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }


}
