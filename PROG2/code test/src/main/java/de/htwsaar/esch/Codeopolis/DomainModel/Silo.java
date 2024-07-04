package de.htwsaar.esch.Codeopolis.DomainModel;

import de.htwsaar.esch.Codeopolis.DomainModel.Harvest.Harvest;

import java.io.Serializable;
import java.util.PriorityQueue;

/**
 * The Silo class represents a storage unit for a specific type of grain.
 * It stores Harvest objects and manages operations such as storing,
 * taking out, and decaying of grain. The Silo uses a PriorityQueue to
 * ensure that the grain with the lowest durability is taken out first.
 */
public class Silo implements Serializable, Comparable<Silo> {
    private PriorityQueue<Harvest> stock;
    private final int capacity;
    private int fillLevel;

    /**
     * Constructs a Silo object with the specified initial capacity.
     *
     * @param capacity The initial capacity of the silo.
     */
    public Silo(int capacity) {
        this.capacity = capacity;
        this.stock = new PriorityQueue<>((h1, h2) -> Integer.compare(h1.getDurability(), h2.getDurability()));
        this.fillLevel = 0;
    }

    /**
     * Copy constructor for the Silo class.
     * Creates a new Silo object as a deep copy of another Silo object.
     * This constructor is used to ensure that each property of the Silo,
     * including mutable objects, is copied and independent of the original object.
     *
     * @param other The Silo object to copy.
     */
    public Silo(Silo other) {
        this.capacity = other.capacity;
        this.fillLevel = other.fillLevel;
        this.stock = new PriorityQueue<>(other.stock);
    }

    /**
     * Stores a harvest in the silo if there is available capacity.
     *
     * @param harvest The harvest to be stored in the silo.
     * @return The amount of grain that could not be stored due to capacity
     *         limitations, or null if all grain was stored.
     */
    public Harvest store(Harvest harvest) {
        if (fillLevel > 0 && !stock.isEmpty() && stock.peek().getGrainType() != harvest.getGrainType()) {
            throw new IllegalArgumentException(
                    "The grain type of the given Harvest does not match the grain type of the silo");
        }

        if (fillLevel >= capacity) {
            return harvest;
        }

        int remainingCapacity = this.capacity - this.fillLevel;
        if (harvest.getAmount() <= remainingCapacity) {
            this.stock.add(harvest);
            this.fillLevel += harvest.getAmount();
            return null;
        } else {
            Harvest remainingHarvest = harvest.split(remainingCapacity);
            this.stock.add(remainingHarvest);
            this.fillLevel += remainingHarvest.getAmount();
            return harvest;
        }
    }

    /**
     * Empties the silo by removing all stored harvests and returning them.
     *
     * @return An array containing all the removed harvests from the silo.
     */
    public Harvest[] emptySilo() {
        Harvest[] removedHarvests = new Harvest[stock.size()];
        int index = 0;
        while (!stock.isEmpty()) {
            removedHarvests[index++] = stock.poll();
        }
        fillLevel = 0;
        return removedHarvests;
    }

    /**
     * Takes out a specified amount of grain from the silo.
     *
     * @param amount The amount of grain to be taken out.
     * @return The actual amount of grain taken out from the silo.
     */
    public int takeOut(int amount) {
        int takenAmount = 0;
        while (amount > 0 && !stock.isEmpty()) {
            Harvest currentHarvest = stock.peek();
            int taken = currentHarvest.remove(amount);
            amount -= taken;
            takenAmount += taken;

            if (currentHarvest.getAmount() == 0) {
                stock.poll();
            }
        }
        fillLevel -= takenAmount;
        return takenAmount;
    }

    /**
     * Gets the current fill level of the silo.
     *
     * @return The number of harvests currently stored in the silo.
     */
    public int getFillLevel() {
        return stock.stream().mapToInt(Harvest::getAmount).sum();
    }

    /**
     * Gets the capacity of the silo.
     *
     * @return The maximum number of harvests the silo can store.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets the grain type stored in the silo.
     *
     * @return The grain type stored in the silo, or null if the silo is empty.
     */
    public Game.GrainType getGrainType() {
        return stock.isEmpty() ? null : stock.peek().getGrainType();
    }

    /**
     * Retrieves the number of harvests currently stored in the silo.
     *
     * @return The number of harvests stored in the silo.
     */
    public int getHarvestCount() {
        return stock.size();
    }

    /**
     * Simulates the decay of grain in all harvests stored in the silo over time.
     *
     * @param currentYear The current year used to calculate the decay.
     * @return The total amount of grain that decayed in all harvests in the silo.
     */
    public int decay(int currentYear) {
        int totalDecayedAmount = stock.stream().mapToInt(harvest -> harvest.decay(currentYear)).sum();
        fillLevel -= totalDecayedAmount;
        return totalDecayedAmount;
    }

    /**
     * Compares this Silo to another Silo based on the fill level.
     *
     * @param other The other Silo to compare to.
     * @return A negative integer, zero, or a positive integer as this Silo's fill
     *         level is less than, equal to, or greater than the specified Silo's
     *         fill level.
     */
    @Override
    public int compareTo(Silo other) {
        return Integer.compare(this.fillLevel, other.fillLevel);
    }
}
