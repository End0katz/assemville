package com.end0katz.assemville;

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

// java.util.logging.Logger didn't seem to include date so I wanted to do it MY way
/**
 * Utility logging class, similar to that of java.util.logging
 */
public class Logger {

    public static enum Level {
        DBUG, VRBS, INFO, WARN, EROR, FATL;
        // Debug, Verbose, Info, Warning, Error, Fatal
    }

    public static record Item(
            Logger.Level lvl, // e.g. Info
            LocalDateTime time, // For formatting
            String id, // For formatting
            String state, // For formatting
            String msg) {

        public static DateTimeFormatter datefmt = DateTimeFormatter.ofPattern(
                "yyMMMdd hhmmass.SS%"
        ); // <year as 2 digits><3 letter month name><day as 2 digits> <hour>:<minute><am/pm><seconds>.<seconds>S

        @Override
        public String toString() {
            return "[date idst lv] msg"
            // [25Jan30 12:00pm01.00s mod:asvl/init INFO] Initialization completed
                    .replace(
                            "date",
                            time.format(datefmt).replace('%', 's')
                            // 25Jan30 12:00pm01.00s
                    )
                    .replace("id", id()) // mod:asvl
                    .replace("st", (state.equals("") ? "" : "/") + state) // /init
                    .replace("lv", lvl.toString()) // INFO, DBUG, VRBS
                    .replace("msg", msg()); // Initialization completed
        }
    }

    protected PrintStream output = System.err;
    protected String id; //group:name
    public List<Logger.Item> logs = new ArrayList<>(); // Yes I know you can insert things here; please don't
    protected String state = "";

    public Logger(String group, String name) {
        this(group, name, System.err);
    }

    public Logger(String group, String name, PrintStream output) {
        this.output = output;
        this.id = group + ":" + name;
    }

    public Logger log(Level lvl, String msg, Object... fmts) {
        Item item = new Item(
                lvl,
                LocalDateTime.now(),
                id,
                state,
                msg.formatted(fmts)
        );
        logs.add(item);
        output.println(item);
        return this;
    }

    public static void main(String[] args) {
        Logger logger = new Logger("mod", "asvl");
        for (int i = 0; i < 10; i++) {
            logger.debug("Hello, World!");
        }
    }

    public Logger state(String state) {
        this.state = state;
        return this;
    }

    public Logger debug(String msg, Object... fmts) {
        return this.log(Level.DBUG, msg, fmts);
    }

    public Logger verbose(String msg, Object... fmts) {
        return this.log(Level.VRBS, msg, fmts);
    }

    public Logger info(String msg, Object... fmts) {
        return this.log(Level.INFO, msg, fmts);
    }

    public Logger warn(String msg, Object... fmts) {
        return this.log(Level.WARN, msg, fmts);
    }

    public Logger error(String msg, Object... fmts) {
        return this.log(Level.EROR, msg, fmts);
    }

    public Logger fatal_err(String msg, Object... fmts) {
        return this.log(Level.FATL, msg, fmts);
    }

    public String logs() {
        return logs(Level.VRBS);
    }

    public String logs(Level lvl) {
        String result = "";
        for (Item i : logs) {
            if (i.lvl().compareTo(lvl) >= 0) {
                result += i.toString() + "\n";
            }
        }
        return result;
    }
}
