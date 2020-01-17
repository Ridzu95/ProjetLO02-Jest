package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.player.Player;

/**
 * Interface to implement in a Jest-counting class.
 * Mainly here for course project restrictions but can be useful if the goal is to create more than one counting variant in the future.
 */

public interface JestCounter {

    void countJest(Player player);

}
