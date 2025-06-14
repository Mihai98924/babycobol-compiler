package se.group5.ast.statement;

import se.group5.ast.Atomic;
import se.group5.ast.Identifier;
import se.group5.ast.Program;
import se.group5.ast.data.DataDefinition;
import se.group5.ast.data.DataElement;
import se.group5.ast.data.DataGroup;
import se.group5.ast.data.PictureSymbol;
import se.group5.ast.procedure.Procedure;

import java.util.List;
import java.util.Objects;

public record Move(Object type, List<Identifier> targets) implements Procedure {

    @Override
    public void execute(Program state) {
        if (type instanceof MoveType moveType) {
            if(moveType == MoveType.HIGH_VALUES) {
                // Fill the target identifiers with low values (double max for numeric, ascii max for alphanumeric)
                for(Identifier target : targets) {
                    String fullyQualifiedIdentifier = state.symbolTable.getFullyQualifiedIdentifier(target.value());
                    DataDefinition data = state.symbolTable.table.get(fullyQualifiedIdentifier);

                    if(data instanceof DataElement element) {
                        // TODO add logic for different types
                        element.setValue(Double.MAX_VALUE);
                    } else if(data instanceof DataGroup group) {
                        for (DataDefinition groupChild : group.children.values()) {
                            if(groupChild instanceof DataElement element) {
                                // TODO add logic for different types
                                element.setValue(Double.MAX_VALUE);
                            }
                        }
                    }
                }
            } else if(moveType == MoveType.LOW_VALUES) {
                // Fill the target identifiers with low values (double min for numeric, ascii 0 for alphanumeric)
                for(Identifier target : targets) {
                    String fullyQualifiedIdentifier = state.symbolTable.getFullyQualifiedIdentifier(target.value());
                    DataDefinition data = state.symbolTable.table.get(fullyQualifiedIdentifier);

                    if(data instanceof DataElement element) {
                        // TODO add logic for different types
                        element.setValue(Double.MIN_VALUE);
                    } else if(data instanceof DataGroup group) {
                        for (DataDefinition groupChild : group.children.values()) {
                            if(groupChild instanceof DataElement element) {
                                // TODO add logic for different types
                                element.setValue(Double.MIN_VALUE);
                            }
                        }
                    }
                }
            } else if(moveType == MoveType.SPACES) {
                // Apply logic only to the numeric fields
                for(Identifier target : targets) {
                    String fullyQualifiedIdentifier = state.symbolTable.getFullyQualifiedIdentifier(target.value());
                    DataDefinition data = state.symbolTable.table.get(fullyQualifiedIdentifier);

                    if(data instanceof DataElement dataElement) {
                        boolean containsA = dataElement.picture().containsSymbol(PictureSymbol.ALPHA);
                        boolean containsX = dataElement.picture().containsSymbol(PictureSymbol.ALPHANUM);
                        if(containsA || containsX) {
                            throw new IllegalArgumentException("MOVE SPACES can only be applied to a numeric" +
                                    " data definition, not " + dataElement.picture());
                        }
                    }

                    if(data != null) {
                        try {
                            data.setValue(0);
                        } catch (Exception e) {
                            throw new IllegalArgumentException("MOVE SPACES can only be applied to a numeric" +
                                    " data definition", e);
                        }
                    } else {
                        throw new RuntimeException("Data definition not found for identifier: " + target.value());
                    }
                }
            }
        } else if(type instanceof Atomic atomic) {
            if(atomic.isComposite()) {
                for (DataDefinition atomicData : atomic.getGroup().children.values()) {
                    // Logic for DataElements
                    for (Identifier target : targets) {
                        String fullyQualifiedIdentifier = state.symbolTable.getFullyQualifiedIdentifier(target.value());
                        DataDefinition data = state.symbolTable.table.get(fullyQualifiedIdentifier);
                        if (data instanceof DataGroup dataGroup) {
                            for (DataDefinition groupChild : dataGroup.children.values()) {
                                // We apply move only to the lowest level element child
                                if (groupChild instanceof DataElement dataElement) {
                                    if (!dataElement.name().value().equals(atomicData.name().value()))
                                        continue;

                                    // TODO apply type checking here (maybe in a data element to compare with other data elements)
                                    dataElement.setValue(atomicData.getValue());
                                }
                            }
                        } else {
                            throw new IllegalArgumentException("MOVE atomic can only be applied to composite fields!");
                        }
                    }
                }
            } else if(atomic.isElement()) {
                // Logic for DataElements
                DataElement element = atomic.getElement();
                for(Identifier target : targets) {
                    String fullyQualifiedIdentifier = state.symbolTable.getFullyQualifiedIdentifier(target.value());
                    DataDefinition data = state.symbolTable.table.get(fullyQualifiedIdentifier);
                    if(data instanceof DataElement dataElement) {
                        //TODO Do the identifiers have to be the same for not composite elements?
//                        if (!dataElement.name().value().equals(atomic.getElement().name().value()))
//                            continue;

                        // TODO apply type checking here
                        dataElement.setValue(element.getValue());
                    } else {
                        throw new IllegalArgumentException("MOVE field can only be applied to other" +
                                " non composite fields!");
                    }
                }
            } else {
                throw new IllegalArgumentException("MOVE has to include identifiers!");
            }
        }
    }

    public enum MoveType {
        HIGH_VALUES,
        LOW_VALUES,
        SPACES;
    }

    @Override
    public String toString() {
        return "MOVE(" + type + targets + ")";
    }
}
