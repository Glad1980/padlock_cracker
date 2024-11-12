package com.cleverthis.interview.crack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.cleverthis.interview.connect.IPadlockConnector;


/**
 * Implement PadlockCracker class for cracking padlock
 * <br/>
 * <ul>
 *     <li>Created PadlockCracker class to solve the padlock cracking problem using the provided padlock connector.</li>
 *     <li>Implemented the solve method to initialize the padlock connector and perform the cracking operation.</li>
 *     <li>Generated permutations of keys to attempt cracking the padlock.</li>
 *     <li>Implemented logic to crack the padlock using the generated permutations.</li>
 *     <li>while the most expensive operation is writing into memory buffer, 
 *      a method to check for the nearest permutation used, to reduce the cost of changing the buffer</li>
 * </ul>
 */
public class PadlockCracker implements IPadlockCracker {
    private IPadlockConnector padlockConnector;
    
    @Override
    public void solve(IPadlockConnector padlockConnector) {

        this.padlockConnector = padlockConnector;
        int numpadSize = padlockConnector.getNumpadSize();
        System.out.println("Starting Cracker with numpadSize : " + numpadSize);
        if (numpadSize < 1) {
            throw new IllegalArgumentException("numpadSize must be a positive number" + numpadSize);
        }
        List<Integer> keys = IntStream.range(0, numpadSize).boxed().collect(Collectors.toList());
        List<List<Integer>> permutations = generatePermutations(keys);
        crackPadLock(permutations, keys);
    }

    private void crackPadLock(List<List<Integer>> permutations, List<Integer> keys) {
        int[] mem = new int[padlockConnector.getNumpadSize()];
        Arrays.fill(mem, -1);
        List<Integer> permutation = new ArrayList<>(keys);
        while (!permutations.isEmpty()) {
            permutation = getNearstArray(permutation, permutations);
            for (int i = 0; i < permutation.size(); i++) {
                if (mem[i] != permutation.get(i)) {
                    mem[i] = permutation.get(i);
                    padlockConnector.writeInputBuffer(i, permutation.get(i));
                }
                try {
                    if (padlockConnector.isPasscodeCorrect()) {
                        return;
                    }
                } catch (IllegalStateException e) {
                    // No need to throws any exception, since this may happened while cracking the Padlock.
                    System.out.println("error while checking password: " + e.getMessage());
                }
            }

        }
        System.out.println("Passcode not found.");
    }

    private List<Integer> getNearstArray(List<Integer> current, List<List<Integer>> permutations) {
        int index = 0;
        for (List<Integer> per : permutations) {
            if (changesCount(per, current) <= 2) {
                break;
            } else {
                index++;
            }
        }
        List<Integer> per = permutations. get(index);
        permutations.remove(index);
        return per;
    }

    private int changesCount(List<Integer> a, List<Integer> b) {
        int changesCount = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != b.get(i)) {
                changesCount++;
            }
        }
        return changesCount;
    }

    private List<List<Integer>> generatePermutations(List<Integer> keys) {
        List<List<Integer>> permutations = new ArrayList<>();
        generatePermutationsHelper(keys, 0, permutations);
        return permutations;
    }

    private void generatePermutationsHelper(List<Integer> keys, int start, List<List<Integer>> permutations) {
        if (start == keys.size() - 1) {
            permutations.add(new ArrayList<>(keys));
            return;
        }
        for (int i = start; i < keys.size(); i++) {
            Collections.swap(keys, start, i);
            generatePermutationsHelper(keys, start + 1, permutations);
            Collections.swap(keys, start, i);
        }
    }
}
