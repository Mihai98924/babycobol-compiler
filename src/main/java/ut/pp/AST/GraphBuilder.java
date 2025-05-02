package ut.pp.AST;

import java.util.*;

import org.apache.commons.lang3.RandomStringUtils;

public class GraphBuilder {

    StringBuilder dotString;
    ArrayList<ArrayList<Node>> graphLevels;
    HashMap<Node, ArrayList<Node>> nodeConnections;
    Deque<Node> parentNodeDeQue;
    Integer currentLevel;
    ArrayList<String> existingLabels;


    public GraphBuilder(String label) {
        dotString = new StringBuilder("graph {\n");
        graphLevels = new ArrayList<>();
        parentNodeDeQue = new ArrayDeque<>();
        nodeConnections = new HashMap<>();
        currentLevel = 0;
        existingLabels = new ArrayList<>();
        Node newParentNode = newNode(label);
        nodeConnections.put(newParentNode, new ArrayList<>());
        parentNodeDeQue.push(newParentNode);
        graphLevels.add(new ArrayList<>());
        graphLevels.get(currentLevel).add(newParentNode);
        currentLevel++;
    }

    public void writeDot() {
        Iterator<ArrayList<Node>> graphLevelIterator = graphLevels.iterator();
        dotString.append("   ");
        while (graphLevelIterator.hasNext()) {
            Iterator<Node> nodeIterator = graphLevelIterator.next().iterator();
            Node node;
            while (nodeIterator.hasNext()) {
                node = nodeIterator.next();
                dotString.append(node.getIndexedLabel());
                dotString.append(";\n   ");
            }
        }
        dotString.append("\n   ");
        for (ArrayList<Node> level : graphLevels) {
            if (level.size() > 0) {
                dotString.append("{ rank=same; ");
                for (Node node : level) {
                    dotString.append(node.getRandomLabel()).append(" ");
                }
                dotString.append(" }\n   ");
            }
        }
        dotString.append("\n\n   ");
        for (Map.Entry<Node, ArrayList<Node>> nodeConnection : nodeConnections.entrySet()) {
            if (nodeConnection.getValue().size() > 0) {
                Node rootNode = nodeConnection.getKey();
                for (Node childNode : nodeConnection.getValue()) {
                    dotString.append(rootNode.getRandomLabel()).append(" -- ").append(childNode.getRandomLabel()).append("\n   ");
                }
            }
        }
        dotString.append("\n}");
    }

    public void push(String label) {
//        System.out.println("push: "+label);
        // The currently set parent node
        Node currentParentNode = parentNodeDeQue.peek();
        // The new parent node to set
        Node newParentNode = newNode(label);
        if (graphLevels.size() <= currentLevel) {
            graphLevels.add(new ArrayList<>());
        }
        // put the new parent node in the next level of the graph
        graphLevels.get(currentLevel).add(newParentNode);

        // add the new node to the children of the current parent node
        nodeConnections.get(currentParentNode).add(newParentNode);
        // set the current parent node to the new node
        parentNodeDeQue.push(newParentNode);
        // add an arraylist for storing the children of this node
        nodeConnections.put(newParentNode, new ArrayList<>());
        currentLevel++;
//        System.out.println("new level: " + currentLevel+"\n");
    }


    public void pop() {
        parentNodeDeQue.pop();
        if (currentLevel != 0) {
            currentLevel--;
        }
//        System.out.println("Pop \nnew level: "+currentLevel+"\n");

    }

    public StringBuilder getDotString() {
        return dotString;
    }


    public Node newNode(String label) {
        String randomLabel;
        do {
            randomLabel = RandomStringUtils.randomAlphabetic(8);
        } while (existingLabels.contains(randomLabel));
        existingLabels.add(randomLabel);
        return new Node(label, randomLabel);
    }

    public static class Node {
        String label;

        String randomLabel;

        public String getRandomLabel() {
            return randomLabel;
        }

        public Node(String label, String randomLabel) {
            this.label = label;
            this.randomLabel = randomLabel;
        }

        public String getIndexedLabel() {
            return randomLabel + " [label=<" + label + ">]";
        }
    }
}