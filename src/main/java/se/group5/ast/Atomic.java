package se.group5.ast;

import se.group5.ast.data.DataElement;
import se.group5.ast.literal.Literal;

import javax.swing.text.Element;
import javax.xml.crypto.Data;

public class Atomic implements Node {
    Literal literal;
    DataElement element;

    public Atomic(Literal literal) {
        this.literal = literal;
    }

    public Atomic(DataElement element) {
        this.element = element;
    }
}
