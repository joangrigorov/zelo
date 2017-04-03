package org.zelo.ast;

import org.junit.Test;
import org.zelo.ast.expression.Call;
import org.zelo.ast.expression.Expression;
import org.zelo.ast.expression.NativeCall;
import org.zelo.ast.expression.Symbol;
import org.zelo.ast.expression.literal.BooleanTrue;
import org.zelo.ast.expression.literal.FloatLiteral;
import org.zelo.ast.expression.literal.IntegerLiteral;
import org.zelo.ast.expression.literal.StringLiteral;
import org.zelo.ast.function.LiteralPattern;
import org.zelo.ast.function.Pattern;
import org.zelo.ast.function.TypedArgument;
import org.zelo.ast.function.essential.*;
import org.zelo.ast.module.Declaration;
import org.zelo.ast.module.Function;
import org.zelo.ast.module.Module;
import org.zelo.ast.name.Name;
import org.zelo.ast.name.QualifiedName;
import org.zelo.ast.type.BooleanType;
import org.zelo.ast.type.IntegerType;
import org.zelo.ast.type.RealType;
import org.zelo.ast.type.StringType;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.*;

public class ASTBuilderTest {
    @Test
    public void shouldBuildModuleASTWithNameOnly() throws IOException {
        String actualCode = "модул Тест";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertEquals("Тест", actualModule.getName().toString());
    }

    @Test
    public void shouldBuildModuleASTNameInNamespaceOnly() throws IOException {
        String actualCode = "модул Пример::Тест";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertEquals("Пример::Тест", actualModule.getName().toString());
    }

    @Test
    public void shouldBuildModuleWithOneExport() throws IOException {
        String actualCode = "модул Тест разкрива фибоначи";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertEquals("фибоначи", actualModule.getExported().iterator().next().toString());
    }

    @Test
    public void shouldBuildModuleWithTwoExports() throws IOException {
        String actualCode = "модул Тест разкрива фибоначи, фибоначиДве";
        Module actualModule = new ASTBuilder().buildAST(actualCode);
        Iterator<Name> iterator = actualModule.getExported().iterator();
        iterator.next();

        assertEquals("фибоначиДве", iterator.next().toString());
    }

    @Test
    public void shouldBuildModuleWithAnImport() throws IOException {
        String actualCode = "модул Тест ползва Тест";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertEquals("Тест", actualModule.getImported().iterator().next().toString());
    }

    @Test
    public void shouldBuildModuleWithANamespacedImport() throws IOException {
        String actualCode = "модул Тест ползва Пример::Тест";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertEquals("Пример::Тест", actualModule.getImported().iterator().next().toString());
    }

    @Test
    public void shouldBuildModuleWithTwoImports() throws IOException {
        String actualCode = "модул Тест ползва Пример::Тест ползва Друг::Пример";
        Module actualModule = new ASTBuilder().buildAST(actualCode);
        Iterator<QualifiedName> qualifiedNameIterator = actualModule.getImported().iterator();
        qualifiedNameIterator.next();

        assertEquals("Друг::Пример", qualifiedNameIterator.next().toString());
    }

    @Test
    public void shouldBuildModuleWithFunctionTestingName() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н -> (+ н 1) .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertEquals("фибоначи", actualModule.getFunctions().iterator().next().getName().toString());
    }

    @Test
    public void shouldBuildModuleWithFunctionTestingType() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н -> (+ н 1) .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertTrue(actualModule.getFunctions().iterator().next().getType() instanceof IntegerType);
    }

    @Test
    public void shouldBuildModuleWithFunctionAndOneDeclaration() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н -> (+ н 1) .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertSame(1, actualModule.getFunctions().iterator().next().getDeclarations().size());
    }

    @Test
    public void shouldBuildModuleWithFunctionAndTwoDeclarations() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н -> (+ н 1), реално н -> (+ н 1) .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertSame(2, actualModule.getFunctions().iterator().next().getDeclarations().size());
    }

    @Test
    public void shouldBuildModuleWithFunctionTestingDeclarationArgument() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н -> (+ н 1) .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);
        Function actualFunction = actualModule.getFunctions().iterator().next();
        Declaration actualDeclaration = actualFunction.getDeclarations().iterator().next();
        TypedArgument actualTypedArgument = (TypedArgument) actualDeclaration.getSignature().iterator().next();

        assertSame(1, actualDeclaration.getSignature().size());
        assertTrue(actualTypedArgument.getType() instanceof IntegerType);
        assertEquals("н", actualTypedArgument.getName().toString());
    }

    @Test
    public void shouldBuildModuleWithFunctionTestingWithTwoArguments() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н, реален стринг -> (+ н 1) .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);
        Function actualFunction = actualModule.getFunctions().iterator().next();
        Declaration actualDeclaration = actualFunction.getDeclarations().iterator().next();
        Iterator<Pattern> patternIterator = actualDeclaration.getSignature().iterator();
        patternIterator.next();
        TypedArgument actualStringTypedArgument = (TypedArgument) patternIterator.next();

        assertSame(2, actualDeclaration.getSignature().size());
        assertTrue(actualStringTypedArgument.getType() instanceof RealType);
        assertEquals("стринг", actualStringTypedArgument.getName().toString());
    }

    @Test
    public void shouldBuildModuleWithFunctionTestingAllScalarTypesAsArguments() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н, реален н2, низ н3, булев н4 -> (+ н 1) .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);
        Function actualFunction = actualModule.getFunctions().iterator().next();
        Declaration actualDeclaration = actualFunction.getDeclarations().iterator().next();
        Iterator<Pattern> actualArguments = actualDeclaration.getSignature().iterator();

        assertTrue(((TypedArgument) actualArguments.next()).getType() instanceof IntegerType);
        assertTrue(((TypedArgument) actualArguments.next()).getType() instanceof RealType);
        assertTrue(((TypedArgument) actualArguments.next()).getType() instanceof StringType);
        assertTrue(((TypedArgument) actualArguments.next()).getType() instanceof BooleanType);
    }

    @Test
    public void shouldBuildModuleWithFunctionTestingAllLiteralsAsArguments() throws IOException {
        String actualCode = "модул Тест цял фибоначи : 13, 4.3, \"string\", да -> (+ н 1) .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);
        Function actualFunction = actualModule.getFunctions().iterator().next();
        Declaration actualDeclaration = actualFunction.getDeclarations().iterator().next();
        Iterator<Pattern> actualArguments = actualDeclaration.getSignature().iterator();

        assertTrue(((LiteralPattern) actualArguments.next()).getLiteral() instanceof IntegerLiteral);
        assertTrue(((LiteralPattern) actualArguments.next()).getLiteral() instanceof FloatLiteral);
        assertTrue(((LiteralPattern) actualArguments.next()).getLiteral() instanceof StringLiteral);
        assertTrue(((LiteralPattern) actualArguments.next()).getLiteral() instanceof BooleanTrue);
    }

    @Test
    public void shouldBuildModuleWithFunctionCallingAllNativeFunctions() throws IOException {
        String actualCode = "модул Тест цял фибоначи : " +
                "0 -> (!да)," +
                "0 -> (+ 1 1)," +
                "0 -> (- 1 1)," +
                "0 -> (++ 1 1)," +
                "0 -> (-- 1 1)," +
                "0 -> (* 1 1)," +
                "0 -> (/ 1 1)," +
                "0 -> (> 1 1)," +
                "0 -> (< 1 1)," +
                "0 -> (== 1 1)," +
                "0 -> (!= 1 1)," +
                "0 -> (>= 1 1)," +
                "0 -> (<= 1 1)," +
                "0 -> (&& 1 1)," +
                "0 -> (|| 1 1)," +
                "0 -> (! (== 1 1))" +
                ".";

        Module actualModule = new ASTBuilder().buildAST(actualCode);
        Function actualFunction = actualModule.getFunctions().iterator().next();
        Iterator<Declaration> declarationIterator = actualFunction.getDeclarations().iterator();

        assertNativeFunction(declarationIterator.next().getBody(), Negation.class);
        assertNativeFunction(declarationIterator.next().getBody(), Addition.class);
        assertNativeFunction(declarationIterator.next().getBody(), Subtraction.class);
        assertNativeFunction(declarationIterator.next().getBody(), Increment.class);
        assertNativeFunction(declarationIterator.next().getBody(), Decrement.class);
        assertNativeFunction(declarationIterator.next().getBody(), Multiplication.class);
        assertNativeFunction(declarationIterator.next().getBody(), Division.class);
        assertNativeFunction(declarationIterator.next().getBody(), GreaterThan.class);
        assertNativeFunction(declarationIterator.next().getBody(), LowerThan.class);
        assertNativeFunction(declarationIterator.next().getBody(), Equal.class);
        assertNativeFunction(declarationIterator.next().getBody(), NotEqual.class);
        assertNativeFunction(declarationIterator.next().getBody(), GreaterThanOrEqual.class);
        assertNativeFunction(declarationIterator.next().getBody(), LowerThanOrEqual.class);
        assertNativeFunction(declarationIterator.next().getBody(), LogicalAnd.class);
        assertNativeFunction(declarationIterator.next().getBody(), LogicalOr.class);
        Expression negationOfEqual = declarationIterator.next().getBody();
        assertNativeFunction(negationOfEqual, Negation.class);
    }

    private <T> void assertNativeFunction(Expression actualExpression, Class<T> expectedNativeFunction) {
        assertTrue(expectedNativeFunction.isInstance(((NativeCall) actualExpression).getNativeFunction()));
    }

    @Test
    public void shouldBuildModuleWithFunctionUsingParameter() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цяла число -> (+ число 1) .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);
        Function actualFunction = actualModule.getFunctions().iterator().next();
        Declaration actualDeclaration = actualFunction.getDeclarations().iterator().next();
        Iterator<Expression> expressionIterator = ((NativeCall) actualDeclaration.getBody()).getArguments().iterator();
        Expression actualArgument = expressionIterator.next();

        assertTrue(actualArgument instanceof Symbol);
        assertEquals("число", ((Symbol) actualArgument).getName().toString());
    }

    @Test
    public void shouldBuildModuleWithCustomNonNativeFunction() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цяла число -> (функция число 1) .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);
        Function actualFunction = actualModule.getFunctions().iterator().next();
        Declaration actualDeclaration = actualFunction.getDeclarations().iterator().next();
        Call actualDeclarationBody = (Call) actualDeclaration.getBody();

        assertTrue(actualDeclarationBody.getCaller() instanceof Symbol);
        assertEquals("функция", ((Symbol) actualDeclarationBody.getCaller()).getName().toString());
    }
}
