package com.cleverthis.interview.crack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.cleverthis.interview.connect.IPadlockConnector;

/**
 * Implement PadlockCracker class for cracking padlock
 * <br/>
 * <ul>
 * <li>Created PadlockCracker class to solve the padlock cracking problem using
 * the provided padlock connector.</li>
 * <li>Implemented the crack  method to initialize the padlock connector and
 * perform the cracking operation.</li>
 * <li>Get or generate permutations of keys from cache to attempt cracking the padlock.</li>
 * <li>Implemented logic to crack the padlock using the generated
 * permutations.</li>
 * <li>while the most expensive operation is writing into memory buffer,
 * a method to check for the nearest permutation used, to reduce the cost of
 * changing the buffer</li>
 * <li>the best memory write for each padlock solve with size s,
 * permutations n = s!, then (n * 2) + (n - 2) memory write for the worst case
 * example, size = 4, then s! = 24, then memory write = 24 * 2 + 2 = 50</li>
 * </ul>
 */
public class PadlockCracker implements IPadlockCracker {
    private IPadlockConnector padlockConnector;
    private Set<Integer> visited ;
    @Override
    public void crack(IPadlockConnector padlockConnector) {

        visited = new HashSet<Integer>();
        this.padlockConnector = padlockConnector;
        int numpadSize = padlockConnector.getNumpadSize();
        System.out.println("Starting Cracker with numpadSize : " + numpadSize);
        // if (numpadSize < 1) {   // no need to handle this exception,since it's handled 
        //     throw new IllegalArgumentException("numpadSize must be a positive number" + numpadSize);
        // }
        List<Integer> keys = IntStream.range(0, numpadSize).boxed().collect(Collectors.toList());
        List<List<Integer>> permutations = PermutationCache.generatePermutations(keys);
        crackPadLock(permutations, keys);
    }

    private void crackPadLock(List<List<Integer>> permutations, List<Integer> keys) {
        // simulate the current buffer, to keep track of the values.
        int[] mem = new int[padlockConnector.getNumpadSize()];
        Arrays.fill(mem, -1);

        List<Integer> permutation = new ArrayList<>(keys);
        while (!permutations.isEmpty()) {
            permutation = getNearstArray(permutation, permutations);
            for (int i = 0; i < permutation.size(); i++) {
                // check if the value is already written in an address
                if (mem[i] != permutation.get(i)) {
                    mem[i] = permutation.get(i);
                    try {
                        padlockConnector.writeInputBuffer(i, permutation.get(i));
                    } catch (IllegalStateException e) {
                        // wrong address
                        System.out.println("error while write Input Buffer: " + e.getMessage());
                    }
                }

            }
            try {
                if (padlockConnector.isPasscodeCorrect()) {
                    System.out.println("Passcode found.");
                    return;
                }
            } catch (IllegalStateException e) {
                // No need to throws any exception, since this may happened while cracking the
                // Padlock.
                System.out.println("error while checking password: " + e.getMessage());
            }
        }
        System.out.println("Passcode not found.");
    }

    /**
     * Method to check for the nearest permutation used, to reduce the cost of
     * changing the buffer
     * <br/>
     * <ul>
     * <li>Iterate through the list of permutations.</li>
     * <li>Find the permutation with the least number of changes compared to the
     * current permutation.</li>
     * <li>Addd the chosen permutation to the visited Set</li>
     * <li>Return the chosen permutation.</li>
     * </ul>
     */
   

    private List<Integer> getNearstArray(List<Integer> current, List<List<Integer>> permutations) {
        int index = 0;
        for (List<Integer> per : permutations) {
            if (!visited.contains(index) && changesCount(per, current) <= 2) {
                break;
            } else {
                index++;
            }
        }
        visited.add(index);
        List<Integer> per = permutations.get(index);
        return per;
    }

    /**
     * Implement changesCount method to count the number of differences between two
     * lists
     * <br/>
     * <ul>
     * <li>Iterate through the elements of the two lists.</li>
     * <li>Compare each element of the lists.</li>
     * <li>Increment the count for each difference found.</li>
     * <li>Return the total count of differences.</li>
     * </ul>
     */
    private int changesCount(List<Integer> a, List<Integer> b) {
        int changesCount = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != b.get(i)) {
                changesCount++;
            }
        }
        return changesCount;
    }
}
