package com.cleverthis.interview.crack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermutationCache {

    // cache the permutations for every size,
    static Map<Integer, List<List<Integer>>> permutationsCache = Collections
            .synchronizedMap(new HashMap<Integer, List<List<Integer>>>());

    // generate all Permutations without duplicating the integer value in the same
    // permutation
    public static List<List<Integer>> generatePermutations(List<Integer> keys) {
        if (permutationsCache.containsKey(keys.size())) {
            return permutationsCache.get(keys.size());
        } else {
            List<List<Integer>> permutations = new ArrayList<>();
            generatePermutationsHelper(keys, 0, permutations);
            permutationsCache.put(keys.size(), permutations);
            return permutations;
        }
    }

    private static void generatePermutationsHelper(List<Integer> keys, int start, List<List<Integer>> permutations) {
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
