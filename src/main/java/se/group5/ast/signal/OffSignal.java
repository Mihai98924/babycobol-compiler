package se.group5.ast.signal;

import se.group5.ast.Program;

public record OffSignal() implements Signal {
    @Override
    public void execute(Program program) {
        // OFF signals don't need to be executed, just considered.
    }
}
