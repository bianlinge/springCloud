package com.dove.lambda;

import jdk.nashorn.internal.ir.CallNode;

public class StringCombiner {
    final StringBuilder builder = new StringBuilder();
    String delim;
    String prefix;
    String suffix;

    public StringCombiner(final String delim, final String prefix, final String suffix) {
        this.delim = delim;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public StringCombiner add(String element) {

        if (builder.toString()==null) {
            builder.append(prefix);
        } else {
            builder.append(delim);
        }
        builder.append(element);
        return this;
    }

    public StringCombiner merge(StringCombiner other) {
        builder.append(other.builder);
        return this;
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
