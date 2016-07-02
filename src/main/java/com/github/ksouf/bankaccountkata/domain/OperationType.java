package com.github.ksouf.bankaccountkata.domain;


public enum OperationType {

    DEPOSIT("DEPOSIT"),
    WITHDRAW("WITHDRAW");

    private String operationName;

    OperationType(String operationName) {
        this.operationName = operationName;
    }

    @Override
    public String toString() {
        return operationName;
    }

    public static OperationType findByOperationName(String operationName) {
        for (OperationType operation : OperationType.values()) {
            if (operation.operationName.equals(operationName)) {
                return operation;
            }
        }
        return null;
}
}
