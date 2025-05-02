package ut.pp.codeGen;

import java.util.LinkedList;

public class Code {
    public static final int NUM_RESERVED_SHARED_ADDRESSES = 16; // These are used to start and join threads.

    private static final String REG_0 = "reg0";
    private static final String REG_ID = "regSprID";
    private static final String REG_A = "regA";
    private static final String REG_B = "regB";
    private static final String REG_C = "regC";
    private static final String REG_D = "regD";
    private static final String REG_TRUE = "regE";      // Reserved register for printing true symbol.
    private static final String REG_FALSE = "regF";     // Reserved register for printing false symbol.

    private static final String NUMBER_IO = "numberIO";
    private static final String CHAR_IO = "charIO";

    private final LinkedList<String> instructions;

    public Code() {
        this.instructions = new LinkedList<>();
        loadImm('T', REG_TRUE);
        loadImm('F', REG_FALSE);
    }

    public int size() {
        return this.instructions.size();
    }

    public void appendCode(Code other) {
        this.instructions.addAll(other.instructions);
    }

    // === LOADING VALUES =====================================================

    public void appendLiteral(int value) {
        loadImm(value, REG_A);
        push(REG_A);
    }

    public void appendVariableGet(int memAddress, boolean isShared) {
        if (isShared)
            readDir(memAddress, REG_A);
        else
            loadDir(memAddress, REG_A);
        push(REG_A);
    }

    public void appendArrayElementGet(int memAddress, boolean isShared) {
        pop(REG_A);
        loadImm(memAddress, REG_B);
        compute("Add", REG_A, REG_B, REG_B);
        if (isShared) {
            readInd(REG_B, REG_A);
        } else {
            loadInd(REG_B, REG_A);
        }
        push(REG_A);
    }

    public void appendArrayGet(int memAddress, int size, boolean isShared) {
        loadImm(memAddress, REG_B);
        loadImm(size, REG_C);
        relativeBranch(REG_C, 2);
        relativeJump(7);
        compute("Add", REG_C, REG_B, REG_D);
        compute("Decr", REG_D, REG_D, REG_D);
        if (isShared) {
            readInd(REG_D, REG_A);
        } else {
            loadInd(REG_D, REG_A);
        }
        push(REG_A);
        compute("Decr", REG_C, REG_C, REG_C);
        relativeJump(-7);
    }

    // === STORING VALUES =====================================================

    public void appendVariableSet(int address, boolean isShared) {
        pop(REG_A);
        if (isShared)
            writeDir(REG_A, address);
        else
            storeDir(REG_A, address);
    }

    public void appendArrayElementSet(int memAddress, boolean isShared) {
        pop(REG_A);
        loadImm(memAddress, REG_B);
        compute("Add", REG_A, REG_B, REG_B);
        pop(REG_A);
        if (isShared) {
            writeInd(REG_A, REG_B);
        } else {
            storeInd(REG_A, REG_B);
        }
    }

    public void appendArraySet(int memAddress, int size, boolean isShared) {
        loadImm(memAddress + size, REG_B);
        loadImm(size, REG_C);
        relativeBranch(REG_C, 2);
        relativeJump(6);
        compute("Sub", REG_B, REG_C, REG_D);
        pop(REG_A);
        if (isShared) {
            writeInd(REG_A, REG_D);
        } else {
            storeInd(REG_A, REG_D);
        }
        compute("Decr", REG_C, REG_C, REG_C);
        relativeJump(-6);
    }

    public void appendLockInit(int memAddress) {
        writeDir(REG_0, memAddress);
    }

    // === EXPRESSIONS ========================================================

    public void appendBinaryExpression(String operator) {
        pop(REG_A);
        pop(REG_B);
        compute(operator, REG_B, REG_A, REG_A);
        push(REG_A);
    }

    public void appendLogicalNot() {
        pop(REG_A);
        loadImm(1, REG_B);
        compute("Xor", REG_A, REG_B, REG_A);
        push(REG_A);
    }

    public void appendNegation() {
        pop(REG_A);
        compute("Sub", REG_0, REG_A, REG_A);
        push(REG_A);
    }

    // === STATEMENTS =========================================================

    public void appendIfStatement(Code conditionCode, Code trueCode) {
        appendCode(conditionCode);
        pop(REG_A);
        relativeBranch(REG_A, 2);
        relativeJump(trueCode.size() + 1);
        appendCode(trueCode);
    }

    public void appendIfStatement(Code conditionCode, Code trueCode, Code falseCode) {
        appendCode(conditionCode);
        pop(REG_A);
        relativeBranch(REG_A, falseCode.size() + 2);
        appendCode(falseCode);
        relativeJump(trueCode.size() + 1);
        appendCode(trueCode);
    }

    public void appendWhileStatement(Code conditionCode, Code loopCode) {
        appendCode(conditionCode);
        pop(REG_A);
        relativeBranch(REG_A, 2);
        relativeJump(loopCode.size() + 2);
        appendCode(loopCode);
        relativeJump(-(conditionCode.size() + loopCode.size() + 1));
    }

    // === INPUT & OUTPUT =====================================================

    public void appendInputNumber(String request) {
        for (int i = 0; i < request.length(); i++) {
            loadImm(request.charAt(i), REG_A);
            writeIO(REG_A, CHAR_IO);
        }
        readIO(REG_A);
        push(REG_A);
    }

    public void appendPrintNumber() {
        pop(REG_A);
        writeIO(REG_A, NUMBER_IO);
    }

    public void appendPrintChar() {
        pop(REG_A);
        writeIO(REG_A, CHAR_IO);
    }

    public void appendPrintBool() {
        pop(REG_A);
        relativeBranch(REG_A, 3);
        writeIO(REG_FALSE, CHAR_IO);
        relativeJump(2);
        writeIO(REG_TRUE, CHAR_IO);
    }

    public void appendPrintNewline() {
        loadImm('\n', REG_A);
        writeIO(REG_A, CHAR_IO);
    }

    // === THREADING ==========================================================

    public int startFork() {
        assert false;
        return 0;
    }

    public void endFork(int previousThreadID) {
        assert false;
    }

    public void join() {
        assert false;
    }

    public void appendSetlock(int memAddress) {
        testAndSet(memAddress, REG_A);
        relativeBranch(REG_A, 2);
        relativeJump(-3);
    }

    public void appendUnlock(int memAddress) {
        writeDir(REG_0, memAddress);
    }

    protected void appendThreadContinueBarrier() {
        readInd(REG_ID, REG_A);
        relativeBranch(REG_A, 2);
        relativeJump(-3);
    }

    protected void appendThreadDone() {
        writeInd(REG_0, REG_ID);
    }

    protected void appendThreadStart(int threadID) {
        loadImm(1, REG_A);
        writeDir(REG_A, threadID);
    }

    protected void appendJoinBarrier(int threadID) {
        readDir(threadID, REG_A);
        relativeBranch(REG_A, -2);
    }

    // === SINGLE INSTRUCTIONS ================================================

    private void loadImm(int value, String register) {
        this.instructions.add(String.format("Load (ImmValue %d) %s", value, register));
    }

    private void loadDir(int address, String register) {
        this.instructions.add(String.format("Load (DirAddr %d) %s", address, register));
    }

    private void loadInd(String addressRegister, String register) {
        this.instructions.add(String.format("Load (IndAddr %s) %s", addressRegister, register));
    }

    private void storeDir(String register, int address) {
        this.instructions.add(String.format("Store %s (DirAddr %d)", register, address));
    }

    private void storeInd(String register, String addressRegister) {
        this.instructions.add(String.format("Store %s (IndAddr %s)", register, addressRegister));
    }

    private void readDir(int address, String register) {
        this.instructions.add(String.format("ReadInstr (DirAddr %d)", address));
        this.instructions.add("Receive " + register);
    }

    private void readInd(String addressRegister, String register) {
        this.instructions.add(String.format("ReadInstr (IndAddr %s)", addressRegister));
        this.instructions.add("Receive " + register);
    }

    private void readIO(String register) {
        this.instructions.add("ReadInstr numberIO");
        this.instructions.add("Receive " + register);
    }

    private void writeDir(String register, int address) {
        this.instructions.add(String.format("WriteInstr %s (DirAddr %d)", register, address));
    }

    private void writeInd(String register, String addressRegister) {
        this.instructions.add(String.format("WriteInstr %s (IndAddr %s)", register, addressRegister));
    }

    private void writeIO(String register, String ioAddress) {
        this.instructions.add(String.format("WriteInstr %s %s", register, ioAddress));
    }

    private void testAndSet(int memAddress, String register) {
        this.instructions.add(String.format("TestAndSet (DirAddr %d)", memAddress));
        this.instructions.add("Receive " + register);
    }

    private void pop(String register) {
        if (this.instructions.getLast().equals("Push " + register)) {
            this.instructions.removeLast();
        } else {
            this.instructions.add("Pop " + register);
        }
    }

    private void push(String register) {
        this.instructions.add("Push " + register);
    }

    private void compute(String operator, String regIn1, String regIn2, String regOut) {
        this.instructions.add(String.format("Compute %s %s %s %s", operator, regIn1, regIn2, regOut));
    }

    private void relativeBranch(String testRegister, int offset) {
        this.instructions.add(String.format("Branch %s (Rel (%d))", testRegister, offset));
    }

    private void relativeJump(int offset) {
        this.instructions.add(String.format("Jump (Rel (%d))", offset));
    }

    protected String toString(String progName) {
        StringBuilder code = new StringBuilder();
        code.append(String.format("%s :: [Instruction]\n%s = [ ", progName, progName));
        for (String instr : this.instructions)
            code.append(instr).append("\n           , ");
        code.append("EndProg ]\n\n");
        return code.toString();
    }
}
