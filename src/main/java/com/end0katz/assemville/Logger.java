package com.end0katz.assemville;

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class Logger {

    public static enum Level {
        DBUG, VRBS, INFO, WARN, EROR, FATL;
    }

    public static record Item(
            Logger.Level lvl,
            LocalDateTime time,
            String id,
            String state,
            String msg) {

        public static DateTimeFormatter datefmt = DateTimeFormatter.ofPattern(
                "yyMMMdd hhmmass.SS%"
        );

        @Override
        public String toString() {
            return "[date idst lv] msg"
                    .replace(
                            "date",
                            time.format(datefmt).replace('%', 's')
                    )
                    .replace("id", id())
                    .replace("st", (state.equals("") ? "" : "/") + state)
                    .replace("lv", lvl.toString())
                    .replace("msg", msg());
        }
    }

    protected PrintStream output = System.err;
    protected String id;
    public List<Logger.Item> logs = new ArrayList<>();
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
