package ut.pp.codeGen;

import java.util.ArrayList;
import java.util.List;

public class RootCode extends Code {
    private final List<Code> threads;
    private int currentThreadID;
    private int nextThreadID;

    public RootCode() {
        super();
        this.threads = new ArrayList<>();
        this.threads.add(new Code());
        this.currentThreadID = 0;
        this.nextThreadID = 1;
    }



    // === LOADING VALUES =====================================================

    public void appendLiteral(int value) {
        threads.get(currentThreadID).appendLiteral(value);
    }

    public void appendVariableGet(int memAddress, boolean isShared) {
        threads.get(currentThreadID).appendVariableGet(memAddress, isShared);
    }

    public void appendArrayElementGet(int memAddress, boolean isShared) {
        threads.get(currentThreadID).appendArrayElementGet(memAddress, isShared);
    }

    public void appendArrayGet(int memAddress, int size, boolean isShared) {
        threads.get(currentThreadID).appendArrayGet(memAddress, size, isShared);
    }

    // === STORING VALUES =====================================================

    public void appendVariableSet(int memAddress, boolean isShared) {
        threads.get(currentThreadID).appendVariableSet(memAddress, isShared);
    }

    public void appendArrayElementSet(int memAddress, boolean isShared) {
        threads.get(currentThreadID).appendArrayElementSet(memAddress, isShared);
    }

    public void appendArraySet(int memAddress, int size, boolean isShared) {
        threads.get(currentThreadID).appendArraySet(memAddress, size, isShared);
    }

    // === EXPRESSIONS ========================================================

    public void appendBinaryExpression(String operator) {
        threads.get(currentThreadID).appendBinaryExpression(operator);
    }

    public void appendLogicalNot() {
        threads.get(currentThreadID).appendLogicalNot();
    }

    public void appendNegation() {
        threads.get(currentThreadID).appendNegation();
    }

    // === STATEMENTS =========================================================

    public void appendIfStatement(Code conditionCode, Code trueCode) {
        threads.get(currentThreadID).appendIfStatement(conditionCode, trueCode);
    }

    public void appendIfStatement(Code conditionCode, Code trueCode, Code falseCode) {
        threads.get(currentThreadID).appendIfStatement(conditionCode, trueCode, falseCode);
    }

    public void appendWhileStatement(Code conditionCode, Code loopCode) {
        threads.get(currentThreadID).appendWhileStatement(conditionCode, loopCode);
    }

    // === INPUT & OUTPUT =====================================================

    public void appendInputNumber(String request) {
        threads.get(currentThreadID).appendInputNumber(request);
    }

    public void appendPrintNumber() {
        threads.get(currentThreadID).appendPrintNumber();
    }

    public void appendPrintChar() {
        threads.get(currentThreadID).appendPrintChar();
    }

    public void appendPrintBool() {
        threads.get(currentThreadID).appendPrintBool();
    }

    public void appendPrintNewline() {
        threads.get(currentThreadID).appendPrintNewline();
    }

    // === THREADING ==========================================================

    @Override
    public int startFork() {
        int previousThreadID = this.currentThreadID;
        this.currentThreadID = this.nextThreadID;
        this.nextThreadID++;

        if (currentThreadID >= threads.size()) {
            threads.add(new Code());
        }

        threads.get(currentThreadID).appendThreadContinueBarrier();
        return previousThreadID;
    }

    @Override
    public void endFork(int previousThreadID) {
        threads.get(currentThreadID).appendThreadDone();
        threads.get(previousThreadID).appendThreadStart(currentThreadID);
        this.currentThreadID = previousThreadID;
    }

    @Override
    public void join() {
        this.nextThreadID--;
        threads.get(currentThreadID).appendJoinBarrier(nextThreadID);
    }

    @Override
    public void appendSetlock(int memAddress) {
        threads.get(currentThreadID).appendSetlock(memAddress);
    }

    @Override
    public void appendUnlock(int memAddress) {
        threads.get(currentThreadID).appendUnlock(memAddress);
    }

    // === TO STRING ==========================================================

    @Override
    public String toString()
    {
        StringBuilder code = new StringBuilder();
        code.append("module Main where\n");
        code.append("import Sprockell\n");
        for (int i = 0; i < threads.size(); i++) {
            code.append(threads.get(i).toString(String.format("thread_%d", i)));
        }
        code.append("main = run [thread_0");
        for (int i = 1; i < threads.size(); i++) {
            code.append(String.format(", thread_%d", i));
        }
        code.append("]\n");
        return code.toString();
    }
}
