package se.group5.ast.statement;

import se.group5.ast.Atomic;
import se.group5.ast.Identifier;
import se.group5.ast.Program;
import se.group5.ast.data.*;
import se.group5.ast.literal.Literal;
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
                        if (element.getType() == Type.NUMERIC)
                            element.setValue(Double.valueOf(element.picture().maxValueStringForRepresentation()));
                        else if (element.getType() == Type.ALPHANUMERIC)
                            element.setValue(element.picture().maxValueStringForRepresentation());

                    } else if(data instanceof DataGroup group) {
                        for (DataDefinition groupChild : group.children.values()) {
                            if(groupChild instanceof DataElement element) {
                                if (element.getType() == Type.NUMERIC)
                                    element.setValue(Double.valueOf(element.picture().maxValueStringForRepresentation()));
                                else if (element.getType() == Type.ALPHANUMERIC)
                                    element.setValue(element.picture().maxValueStringForRepresentation());
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
                        if (element.getType() == Type.NUMERIC)
                            element.setValue(Double.valueOf(element.picture().minValueStringForRepresentation()));
                        else if (element.getType() == Type.ALPHANUMERIC)
                            element.setValue(element.picture().minValueStringForRepresentation());
                    } else if(data instanceof DataGroup group) {
                        for (DataDefinition groupChild : group.children.values()) {
                            if(groupChild instanceof DataElement element) {
                                if (element.getType() == Type.NUMERIC)
                                    element.setValue(Double.valueOf(element.picture().minValueStringForRepresentation()));
                                else if (element.getType() == Type.ALPHANUMERIC)
                                    element.setValue(element.picture().minValueStringForRepresentation());
                            }
                        }
                    }
                }
            } else if(moveType == MoveType.SPACES) {
                // Apply logic only to the numeric fields
                for(Identifier target : targets) {
                    String fullyQualifiedIdentifier = state.symbolTable.getFullyQualifiedIdentifier(target.value());
                    DataDefinition data = state.symbolTable.table.get(fullyQualifiedIdentifier);

                    if(data instanceof DataElement element) {
                        if (element.getType() == Type.NUMERIC)
                            element.setValue(Double.valueOf(element.picture().spacesValueStringForRepresentation()));
                        else if (element.getType() == Type.ALPHANUMERIC)
                            element.setValue(element.picture().spacesValueStringForRepresentation());
                    } else if(data instanceof DataGroup group) {
                        for (DataDefinition groupChild : group.children.values()) {
                            if(groupChild instanceof DataElement element) {
                                if (element.getType() == Type.NUMERIC)
                                    element.setValue(Double.valueOf(element.picture().spacesValueStringForRepresentation()));
                                else if (element.getType() == Type.ALPHANUMERIC)
                                    element.setValue(element.picture().spacesValueStringForRepresentation());
                            }
                        }
                    }
                }
            }
        } else if(type instanceof Atomic atomic) {
            if (atomic.isComposite()) {
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
            } else if (atomic.isElement()) {
                // Logic for DataElements
                DataElement element = atomic.getElement();
                for (Identifier target : targets) {
                    String fullyQualifiedIdentifier = state.symbolTable.getFullyQualifiedIdentifier(target.value());
                    DataDefinition data = state.symbolTable.table.get(fullyQualifiedIdentifier);
                    if (data instanceof DataElement dataElement) {
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
            } else if (atomic.isLiteral()) {
                Literal literal = atomic.getLiteral();
                for (Identifier target : targets) {
                    String fullyQualifiedIdentifier = state.symbolTable.getFullyQualifiedIdentifier(target.value());
                    DataDefinition data = state.symbolTable.table.get(fullyQualifiedIdentifier);
                    if (data instanceof DataElement dataElement) {
                        dataElement.setValue(literal.raw());
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
