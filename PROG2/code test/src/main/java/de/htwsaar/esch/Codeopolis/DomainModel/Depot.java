package de.htwsaar.esch.Codeopolis.DomainModel;

import de.htwsaar.esch.Codeopolis.DomainModel.Harvest.*;

import java.text.DecimalFormat;
import java.util.Iterator;

public class Depot {
    private final LinkedList<Silo> silos;

    /**
     * Constructs a Depot object with the specified number of silos and capacity per silo.
     * For (Übung 4)
     * we had to modify Depot so that the values are stored in Linked-list in case we want to
     * modify the size of the list dynamically
     * see below we added (this.silos = new LinkedList<>();) and in most of the functions so that we make sure that the linked list is used
     *
     * @param numberOfSilos   The number of silos in the depot.
     * @param capacityPerSilo The capacity per silo.
     */

    public Depot(int numberOfSilos, int capacityPerSilo) {
        this.silos = new LinkedList<>();
        for (int i = 0; i < numberOfSilos; i++) {
            this.silos.set(i, new Silo(capacityPerSilo));
        }
    }

    /**
     * Constructs a Depot object with the specified array of silos.
     * Each silo in the array is deeply copied to ensure that the Depot has its own separate instances.
     *
     * @param silosArray The array of Silo objects to be copied into the depot.
     */
    public Depot(Silo[] silosArray) {
        silos = new LinkedList<>();
        if (silosArray != null) {
            for (Silo silo : silosArray) {
                // Assuming Silo has a copy constructor to create a deep copy
                silos.addLast(new Silo(silo));
            }
        }
    }


    /**
     * Retrieves the current fill level of the depot for a specific grain type.
     *
     * @param grainType The grain type for which to retrieve the fill level.
     * @return The total amount of grain stored in the depot for the specified grain type.
     */
    public int getFillLevel(Game.GrainType grainType) {
        int totalFillLevel = 0;
        Iterator<Silo> iterator = silos.iterator();
        while (iterator.hasNext()) {
            Silo silo = iterator.next();
            if (silo.getGrainType() == grainType) {
                totalFillLevel += silo.getFillLevel();
            }
        }
        return totalFillLevel;
    }

    /**
     * Creates and returns a copy of the silos array.
     * This method creates a new array and populates it with copies of the Silo objects,
     * ensuring that modifications to the returned array do not affect the original silos.
     *
     * @return A copy of the silos array.
     */
    public Silo[] getSilos() {
        // Create a new array of Silo with the same length as the LinkedList size
        Silo[] silosCopy = new Silo[silos.size()];
        Iterator<Silo> iterator = silos.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            // Assume Silo has a copy constructor to create a deep copy of each Silo object
            silosCopy[index++] = new Silo(iterator.next());
        }
        return silosCopy;
    }

    /**
     * Gets the total amount of bushels (grain) stored in the depot.
     *
     * @return The total amount of bushels stored in the depot.
     */
    public int getTotalFillLevel() {
        int totalBushels = 0;

        for (int i = 0; i < this.silos.size(); i++) {
            totalBushels += silos.get(i).getFillLevel();
        }
        return totalBushels;
    }

    /**
     * Retrieves the capacity of the depot for a specific grain type.
     *
     * @param grainType The grain type for which to retrieve the capacity.
     * @return The total capacity of the depot for the specified grain type.
     */
    public int getCapacity(Game.GrainType grainType) {
        int totalCapacity = 0;
        Iterator<Silo> iterator = silos.iterator();

        while (iterator.hasNext()) {
            Silo silo = iterator.next();
            if (silo.getGrainType() == grainType || silo.getGrainType() == null) {
                totalCapacity += silo.getCapacity();
            }
        }

        return totalCapacity;
    }

    /**
     * Stores a harvest in the depot.
     *
     * @param harvest The harvest to be stored in the depot.
     * @return True if the harvest was successfully stored, false otherwise.
     */
    public boolean store(Harvest harvest) {
        Iterator<Silo> iterator = silos.iterator();

        // Erste Iteration: Versuchen, die Ernte zu speichern
        while (iterator.hasNext()) {
            Silo silo = iterator.next();
            if (silo.getGrainType() == harvest.getGrainType() || silo.getFillLevel() == 0) {
                harvest = silo.store(harvest);
                if (harvest == null) {
                    return true;
                }
            }
        }

        // Defragmentierung
        defragment();

        // Zweite Iteration: Versuchen, die verbleibende Ernte nach der Defragmentierung zu speichern
        iterator = silos.iterator();
        while (iterator.hasNext()) {
            Silo silo = iterator.next();
            if (silo.getGrainType() == harvest.getGrainType() || silo.getFillLevel() == 0) {
                harvest = silo.store(harvest);
                if (harvest == null) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Takes out a specified amount of grain from the depot for a specific grain type.
     *
     * @param amount    The amount of grain to be taken out.
     * @param grainType The grain type for which to take out the grain.
     * @return The actual amount of grain taken out from the depot.
     */
    public int takeOut(int amount, Game.GrainType grainType) {
        int takenAmount = 0;
        Iterator<Silo> iterator = silos.iterator();

        while (iterator.hasNext()) {
            Silo silo = iterator.next();
            if (silo.getGrainType() == grainType) {
                int amountTaken = silo.takeOut(amount);
                amount -= amountTaken;
                takenAmount += amountTaken;
                if (amount <= 0) {
                    break;
                }
            }
        }
        return takenAmount;
    }

    /**
     * Takes out the specified amount of grain from the silo, distributing it evenly among the stored bushels.
     * If the specified amount exceeds the total amount of grain in the silo, all grain is removed and returned.
     * If the specified amount is less than the total amount of grain, the grain is taken out evenly from each bushel,
     * with any remaining grain distributed among the bushels in a round-robin fashion.
     *
     * @param amount The amount of grain to be taken out from the silo.
     * @return The actual amount of grain taken out from the silo.
     */
    public int takeOut(int amount) {
        if (amount >= this.getTotalFillLevel()) {
            int totalAmountOfBushels = this.getTotalFillLevel();
            Iterator<Silo> iterator = silos.iterator();
            while (iterator.hasNext()) {
                Silo silo = iterator.next();
                silo.emptySilo();
            }
            return totalAmountOfBushels;
        }

        int partition = amount / silos.size();
        int remainder = amount % silos.size();

        Iterator<Silo> iterator = silos.iterator();
        while (iterator.hasNext()) {
            Silo silo = iterator.next();
            if (silo.getFillLevel() < partition) {
                remainder += partition - silo.getFillLevel();
                silo.emptySilo();
            } else {
                silo.takeOut(partition);
            }
        }

        iterator = silos.iterator(); // Reset iterator
        while (remainder > 0 && iterator.hasNext()) {
            Silo silo = iterator.next();
            if (silo.getFillLevel() > 0) {
                silo.takeOut(1);
                remainder--;
            }
            if (!iterator.hasNext() && remainder > 0) {
                iterator = silos.iterator(); // Restart iterator if we reach the end
            }
        }

        return amount;
    }

    /**
     * Expands the depot by adding more silos with the specified capacity per silo.
     *
     * @param numberOfSilos   The number of silos to add.
     * @param capacityPerSilo The capacity per silo.
     */
    public void expand(int numberOfSilos, int capacityPerSilo) {
        for (int i = 0; i < numberOfSilos; i++) {
            silos.addLast(new Silo(capacityPerSilo));
        }
        this.takeOut((int) (numberOfSilos * GameConfig.DEPOT_EXPANSION_COST)); //#Issue42
    }

    /**
     * Performs defragmentation on the depot to redistribute grain across silos.
     */
    public void defragment() {
        LinkedList<Harvest> allHarvests = new LinkedList<>();

        // Iterate over each silo
        for (Iterator<Silo> iterator = silos.iterator(); iterator.hasNext(); ) {
            Silo silo = iterator.next();
            Harvest[] siloHarvests = silo.emptySilo();
            if (siloHarvests != null) {
                // Add the harvested grains to the list
                for (Harvest harvest : siloHarvests) {
                    allHarvests.addLast(harvest);
                }
                // Remove the emptied silo from the linked list
                iterator.remove();
            }
        }

        // Store all harvests back into the silos
        for (Iterator<Harvest> harvestIterator = allHarvests.iterator(); harvestIterator.hasNext(); ) {
            Harvest harvest = harvestIterator.next();
            store(harvest);
            // Remove the stored harvest from the list
            harvestIterator.remove();
        }
    }

    /**
     * Retrieves the total count of harvests across all silos.
     *
     * @return The total count of harvests stored in all silos combined.
     */
    private int getTotalHarvestCount() {
        int totalCount = 0;
        for (Iterator<Silo> iterator = silos.iterator(); iterator.hasNext(); ) {
            Silo silo = iterator.next();
            totalCount += silo.getHarvestCount();
        }
        return totalCount;
    }


    /**
     * Simulates the decay of grain in the depot over time.
     *
     * @return The total amount of grain that decayed in the depot.
     */
    public int decay(int currentYear) {
        int totalDecayedAmount = 0;
        for (Iterator<Silo> iterator = silos.iterator(); iterator.hasNext(); ) {
            Silo silo = iterator.next();
            totalDecayedAmount += silo.decay(currentYear);
        }
        return totalDecayedAmount;
    }


    /**
     * Checks if the depot is fully occupied with grain.
     *
     * @return {@code true} if the total fill level of all silos equals or exceeds the total capacity of the storage system, {@code false} otherwise.
     */
    public boolean full() {
        if (this.getTotalFillLevel() >= this.totalCapacity())
            return true;
        return false;
    }

    /**
     * Calculates the total capacity of the depot by summing the capacities of all silos.
     *
     * @return The total capacity of the storage system.
     */
    public int totalCapacity() {
        int totalCapacity = 0;
        Iterator<Silo> iterator = silos.iterator();
        while (iterator.hasNext()) {
            Silo silo = iterator.next();
            totalCapacity += silo.getCapacity();
        }
        return totalCapacity;
    }

    /**
     * Retrieves the total amount of grain categorized by grain type.
     *
     * @return An array containing the total amount of grain for each grain type, indexed by the grain type constants defined in the {@code GameConfig} class.
     */
    public int[] getBushelsCategorizedByGrainType() {
        int[] result = new int[Game.GrainType.values().length];
        for (Game.GrainType grainType : Game.GrainType.values()) {
            result[grainType.ordinal()] = getFillLevel(grainType);
        }
        return result;
    }


    /**
     * Returns a string representation of the depot, including information about each silo's grain type, fill level, capacity, and absolute amount of grain.
     *
     * @return A string containing information about the depot, including each silo's grain type, fill level, capacity, and absolute amount of grain.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        DecimalFormat df = new DecimalFormat("0.00");

        // Silos sortieren
        silos.bubbleSort();

        Iterator<Silo> iterator = silos.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Silo currentSilo = iterator.next();
            builder.append("Silo ").append(index).append(": ");

            String grainName;
            if (currentSilo.getGrainType() != null)
                grainName = currentSilo.getGrainType().toString();
            else
                grainName = "EMPTY";

            builder.append(grainName).append("\n");

            int fillLevel = currentSilo.getFillLevel();
            int capacity = currentSilo.getCapacity();

            double fillPercentage = (double) fillLevel / capacity * 100;
            double emptyPercentage = 100 - fillPercentage;

            // Absolute amount of grain
            builder.append("Amount of Grain: ").append(fillLevel).append(" units\n");

            int fillBarLength = 20;
            int filledBars = (int) (fillPercentage / 100 * fillBarLength);
            int emptyBars = fillBarLength - filledBars;

            builder.append("|");
            for (int j = 0; j < filledBars; j++) {
                builder.append("=");
            }
            for (int j = 0; j < emptyBars; j++) {
                builder.append("-");
            }
            builder.append("| ").append(df.format(fillPercentage)).append("% filled\n");

            builder.append("Capacity: ").append(capacity).append(" units\n\n");

            index++;
        }

        return builder.toString();
    }


}
