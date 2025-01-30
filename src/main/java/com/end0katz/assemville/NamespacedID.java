package com.end0katz.assemville;

public record NamespacedID(
        String namespace,
        String id) {

    private static int namespacegen = 0;

    public NamespacedID  {
        if (namespace == null || id == null) {
            if (namespace == null && id == null) {
                throw new NullPointerException("Both paramaters to NamespacedID are null");
            } else if (namespace == null) {
                throw new NullPointerException("Cannot create NamespacedID with null namespace (id: %s)".formatted(id));
            } else {
                throw new NullPointerException(
                        "Cannot create NamespacedID with null id. (namespace: %s)".formatted(namespace));
            }
        }

        if (!namespace.matches("[a-z_-][a-z0-9_-]*")) {
            throw new RuntimeException("Namespace %s does not follow pattern (a-z_- followed by any number, including 0, of a-z0-9_-)".formatted(namespace));
        }
        if (!id.matches("[a-z_-][a-z0-9_-]*")) {
            throw new RuntimeException("Id %s does not follow pattern (a-z_- followed by any number, including 0, of a-z0-9_-)".formatted(id));
        }
    }

    public NamespacedID(String from) {
        this(from.split(" *: *")[0], from.split(" *: *")[1]);
    }

    public NamespacedID() {
        this("undefined", "_" + Integer.toString(namespacegen++));
    }

    @Override
    public String toString() {
        return "`%s:%s`".formatted(namespace, id);
    }
}
