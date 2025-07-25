package com.github.imbelo.calcolatricegrafica.model.token;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Dictionary;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Type;
import com.github.imbelo.calcolatricegrafica.model.interfaces.Variable;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class DictionaryToken implements Dictionary<Type> {
    public static List<Type> functions = new ArrayList<>(Arrays.asList(FunctionType.values()));
    public static List<Type> operations = new ArrayList<>(Arrays.asList(OperationType.values()));
    public static List<Type> variables = new ArrayList<>(Arrays.asList(VariableType.values()));
    // lazy evaluation of numberToken
    private static Supplier<Integer> getNumberToken = ()->
                    FunctionType.values().length +
                    OperationType.values().length +
                    VariableType.values().length;
    private PriorityQueue<Type> queue;
    public static List<Type> getOperations(){
        return operations;
    }
    public static List<Type> getFunctions(){
        return functions;
    }
    public static List<Type> getVariables(){
        return variables;
    }
    public void addFunction(Type function) {
        if(function.getSupplier().get() instanceof UnaryFunction)
            functions.add(function);
        else
            throw new IllegalArgumentException("This is not a function");

    }
    public void addOperation(Type operation) {
        if(operation.getSupplier().get() instanceof BinaryFunction)
            operations.add(operation);
        else
            throw new IllegalArgumentException("This is not an operation");

    }
    public void addVariable(Type variable) {
        if(variable.getSupplier().get() instanceof Variable)
            variables.add(variable);
        else
            throw new IllegalArgumentException("This is not a variable");

    }
    @Override
    public List<Type> getSymbols() {
        int numToken = getNumberToken.get();
        boolean needUpdate = queue == null || queue.size() != numToken;
        if (needUpdate) {
            queue = new PriorityQueue<Type>(numToken, Comparator.comparingInt(Type::getPriority));
            Stream.of(operations, functions, variables, Arrays.asList(TokenType.values()))
                    .flatMap(Collection::stream)
                    .forEach(queue::add);
            queue.addAll(Arrays.asList(TokenType.values()));
        }
        return new ArrayList<>(queue);
    }
}
