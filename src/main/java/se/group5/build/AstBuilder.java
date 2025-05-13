package se.group5.build;

import se.group5.ast.IdentificationDivision;
import se.group5.ast.Identifier;
import se.group5.ast.Node;
import se.group5.ast.literal.AlphanumericLiteral;
import se.group5.ast.literal.Literal;
import se.group5.ast.literal.NumericLiteral;
import se.group5.gen.CoBabyBoLBaseVisitor;
import se.group5.gen.CoBabyBoLParser;

import java.util.HashMap;

public final class AstBuilder extends CoBabyBoLBaseVisitor<Node> {

    @Override
    public Node visitProgram(CoBabyBoLParser.ProgramContext ctx) {
        Node id = visit(ctx.identification_division());
        return id;
    }

    @Override
    public IdentificationDivision visitIdentification_division(CoBabyBoLParser.Identification_divisionContext ctx) {
        HashMap<String, String> properties = new HashMap<>();

        for (var clause : ctx.identification_clause()) {
            properties.put(clause.clause_name().getText(), clause.clause_value().getText());
        }
        return new IdentificationDivision(properties);
    }


    @Override
    public Node visitAtomic(CoBabyBoLParser.AtomicContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public Literal visitLiteral(CoBabyBoLParser.LiteralContext ctx) {
        return (Literal) visit(ctx.getChild(0));
    }

    @Override
    public NumericLiteral visitNumeric_literal(CoBabyBoLParser.Numeric_literalContext ctx) {
        return new NumericLiteral(ctx.NUMERICLITERAL().getText());
    }

    @Override
    public AlphanumericLiteral visitAlphanumeric_literal(CoBabyBoLParser.Alphanumeric_literalContext ctx) {
        return new AlphanumericLiteral(ctx.STRINGLITERAL().getText());
    }

    @Override public Identifier visitIdentifier(CoBabyBoLParser.IdentifierContext ctx) {
        return new Identifier(ctx.getText());
    }

    @Override
    protected Node defaultResult() {
        return null;
    }

    @Override
    protected Node aggregateResult(Node aggregate, Node nextResult) {
        return nextResult != null ? nextResult : aggregate;
    }
}
