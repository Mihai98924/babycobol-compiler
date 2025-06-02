package se.group5.ast.procedure;

import org.antlr.v4.runtime.ParserRuleContext;
import se.group5.ast.Node;
import se.group5.ast.Program;
import se.group5.parser.CoBabyBoL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ProcedureList implements Node {
    private final List<Procedure> procedures = new ArrayList<>();
    private HashMap<ParserRuleContext, List<Procedure>> procedureMap = new HashMap<>();

    /**
     * Add a Procedure to the list
     */
    public void add(Procedure procedure, ParserRuleContext ctx) {
        procedures.add(procedure);
        if (procedureMap.containsKey(getAncestorContext(ctx))) {
            procedureMap.get(getAncestorContext(ctx)).add(procedure);
        } else {
            procedureMap.put(getAncestorContext(ctx), new ArrayList<>(List.of(procedure)));
        }
    }

    private ParserRuleContext getAncestorContext(ParserRuleContext ctx) {
        if (ctx == null) {
            return null;
        }
        if (ctx instanceof CoBabyBoL.Procedure_divisionContext || ctx instanceof CoBabyBoL.FunctionContext) {
            return ctx;
        }
        return getAncestorContext(ctx.getParent());
    }

    /**
     * Get a Procedure by index, safely wrapped in Optional
     */
    public Optional<Procedure> get(int index) {
        if (index >= 0 && index < procedures.size()) {
            return Optional.of(procedures.get(index));
        } else {
            return Optional.empty();
        }
    }

    public boolean isEmpty() {
        return procedures.isEmpty();
    }

    public int size() {
        return procedures.size();
    }

    @Override
    public String toString() {
        return procedures.toString();
    }
}