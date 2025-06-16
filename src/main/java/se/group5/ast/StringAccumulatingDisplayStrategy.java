package se.group5.ast;


/**
 * Concrete strategy that accumulates everything in-memory.
 */
public final class StringAccumulatingDisplayStrategy implements ProgramDisplayStrategy {

    private final StringBuilder buffer = new StringBuilder();

    /** Appends the given text exactly as received. */
    @Override
    public void display(String text) {
        buffer.append(text);
    }

    /** Returns everything that has been displayed so far. */
    public String getOutput() {
        return buffer.toString();
    }

    /** Optional helper if you ever need to clear the buffer. */
    public void reset() {
        buffer.setLength(0);
    }
}
