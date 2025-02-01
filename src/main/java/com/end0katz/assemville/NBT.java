package com.end0katz.assemville;

import java.util.HashMap;

public class NBT extends HashMap<String, NBT.Component> {

    public NBT() {
        super();
    }

    public NBT(String key, NBT.Component data) {
        super();
        put(key, data);
    }

    public static record Component(Component.Type type, NBT nbt, Integer i,
            Boolean b, String str) {

        public Component(NBT nbt) {
            this(Type.NBT, nbt, null, null, null);
        }

        public Component(Integer i) {
            this(Type.INT, null, i, null, null);
        }

        public Component(Boolean b) {
            this(Type.BOOL, null, null, b, null);
        }

        public Component(String str) {
            this(Type.STR, null, null, null, str);
        }

        public static enum Type {
            NBT, INT, STR, BOOL;
        }

        @Override
        public String toString() {
            return switch (type) {
                case Type.NBT ->
                    nbt.toString();
                case Type.INT ->
                    i.toString();
                case Type.BOOL ->
                    b ? "y" : "n";
                case Type.STR ->
                    escape(str);
            };
        }

        public String toString(boolean prettyprint) {
            return prettyprint ? toString(4) : toString();
        }

        public String toString(int indentation) {
            return switch (type) {
                case Type.NBT ->
                    nbt.toString(indentation);
                case Type.INT ->
                    i.toString();
                case Type.BOOL ->
                    b ? "y" : "n";
                case Type.STR ->
                    escape(str);
            };
        }
    }

    public String toString(boolean prettyprint) {
        return prettyprint ? toString(4) : toString();
    }

    public String toString(int indentation) {
        String indent = " ".repeat(indentation);

        if (this.isEmpty()) {
            return "{}";
        } else if (this.size() == 1) {
            return "{key: val}"
                    .replace("key", escape(this.keySet().toArray()[0].toString()))
                    .replace("val", ((NBT.Component) this.values().toArray()[0]).toString(true));
        } else {
            String result = "";
            for (String key : this.keySet()) {
                result += indent
                        + escape(key)
                        + ": "
                        + this.get(key).toString(indentation).replace("\n", "\n" + indent)
                        + ",\n";
            }
            return "{\n" + result.substring(0, result.length() - 2) + "\n}";
        }
    }

    /**
     * Escapes a string. e.g. newline -> \n, backspace -> \b, ESC -> \033
     *
     * @param str the string to escape
     * @return str, with double quotes on either side, with all non-printable
     * ascii characters replaced
     */
    public static String escape(String str) {
        String result = "";
        for (Character c : str.toCharArray()) {
            if ((int) c < 0x20 /* Space */
                    || (int) c == 127 /* Delete Character */
                    || c == '\\'
                    || c == '"') {
                result += switch (c) {
                    case (char) 0x0 -> // Null
                        "\\0";
                    case (char) 0x7 -> // Bell
                        "\\a";
                    case (char) 0x8 -> // Backspace
                        "\\b";
                    case (char) 0x9 -> // Tab
                        "\\t";
                    case (char) 0xA -> // Newline
                        "\\n";
                    case (char) 0xB -> // Vertical tab
                        "\\b";
                    case (char) 0xC -> // Form Feed (Page Break)
                        "\\f";
                    case (char) 0xD -> // Carriage Return
                        "\\r";
                    case '\033' -> // Escape
                        "\\033";
                    case '\\' ->
                        "\\\\";
                    case '"' ->
                        "\\\"";
                    default ->
                        "\\x" + Integer.toHexString((int) c);
                };
            } else {
                result += c.toString();
            }
        }
        return '"' + result + '"';
    }

    public NBT tag(String key, Component val) {
        this.put(key, val);
        return this;
    }

    public NBT tag(String key, NBT val) {
        return tag(key, new Component(val));
    }

    public NBT tag(String key, int val) {
        return tag(key, new Component(val));
    }

    public NBT tag(String key, boolean val) {
        return tag(key, new Component(val));
    }

    public NBT tag(String key, String val) {
        return tag(key, new Component(val));
    }
}
