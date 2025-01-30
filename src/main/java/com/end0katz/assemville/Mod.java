package com.end0katz.assemville;

;

/**
 * Base entrypoint for mods. All mods should have a public class Main extends
 * ModEntry, and must override the abstract methods
 */
public abstract class Mod {

    public static enum LoadingState {
        NONE, ITEM, BLOCK, ENTITY;
    }

    public static NamespacedID id(String id) {
        return new NamespacedID(id(), id);
    }

    public static String id() {
        return "undefined";
    }

    public static String name() {
        return "Untitled (override static String name() to rename)";
    }

    @SuppressWarnings("NonConstantLogger")
    public static Logger logger;
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
