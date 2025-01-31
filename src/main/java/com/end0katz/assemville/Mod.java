package com.end0katz.assemville;

;

/**
 * Base entrypoint for mods. All mods should have a public class Main extends
 * Mod, and must override the abstract methods
 */
public abstract class Mod {

    public static enum LoadingState {
        NONE, ITEM, BLOCK, ENTITY, DONE;
    }

    /**
     * Returns a NamespacedID for a block, etc so you don't have to keep using
     * {@code new NamespacedID(Main.id(), "myblock")}.
     *
     * @param id the id to use
     * @return new NamespacedID(Main.id(), id)
     */
    public static NamespacedID id(String id) {
        return new NamespacedID(id(), id);
    }

    /**
     * Method to return id of this mod.
     *
     * @return id of this mod
     */
    public static String id() {
        return "undefined";
    }

    /**
     * Returns the name of the mod you see in the mod menu that is visible to
     * the player.
     *
     * @return the display name of this mod
     */
    public static String name() {
        return "Untitled (override static String name() to rename)";
    }

    @SuppressWarnings("NonConstantLogger")
    public static Logger logger; // See com.end0katz.assemville/Logger.java
    public static LoadingState state = LoadingState.NONE;

    public static void markcompleted(String action) {
        logger.info(" # Completed: %s", action);
    }

    public static void markfailed(String action) {
        logger.warn(" - FAILED: %s", action);
    }

    /**
     * Called to initialize blocks, items and entities from the mod
     *
     * @return status string,
     */
    public static String initialize() {
        ////logger.entering("Mod %s".formatted(MODID), "Initialization");
        logger.info("----- %s Initialization -----", name());

        String result = "";

        state = LoadingState.ITEM;
        result += iteminit();
        state = LoadingState.BLOCK;
        result += blockinit();
        state = LoadingState.ENTITY;
        result += entityinit();
        logger.info("----- %s Initialized -----", name());
        state = LoadingState.DONE;
        //// logger.exiting("Mod %s".formatted(MODID), "Initialization");
        return result.equals("") ? null : result;
    }

    public static String iteminit() {
        return "";
    }

    public static String blockinit() {
        return "";
    }

    public static String entityinit() {
        return "";
    }
}
