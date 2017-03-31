package org.zelo.ast;

import org.junit.Test;
import org.zelo.ast.expression.literal.BooleanTrue;
import org.zelo.ast.expression.literal.FloatLiteral;
import org.zelo.ast.expression.literal.IntegerLiteral;
import org.zelo.ast.expression.literal.StringLiteral;
import org.zelo.ast.function.LiteralPattern;
import org.zelo.ast.function.TypedArgument;
import org.zelo.ast.module.Declaration;
import org.zelo.ast.type.BooleanType;
import org.zelo.ast.type.IntegerType;
import org.zelo.ast.type.RealType;
import org.zelo.ast.type.StringType;

import java.io.IOException;
import java.util.List;

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

        assertEquals("фибоначи", actualModule.getExported().get(0).toString());
    }

    @Test
    public void shouldBuildModuleWithTwoExports() throws IOException {
        String actualCode = "модул Тест разкрива фибоначи, фибоначиДве";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertEquals("фибоначиДве", actualModule.getExported().get(1).toString());
    }

    @Test
    public void shouldBuildModuleWithAnImport() throws IOException {
        String actualCode = "модул Тест ползва Тест";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertEquals("Тест", actualModule.getImported().get(0).toString());
    }

    @Test
    public void shouldBuildModuleWithANamespacedImport() throws IOException {
        String actualCode = "модул Тест ползва Пример::Тест";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertEquals("Пример::Тест", actualModule.getImported().get(0).toString());
    }

    @Test
    public void shouldBuildModuleWithTwoImports() throws IOException {
        String actualCode = "модул Тест ползва Пример::Тест ползва Друг::Пример";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertEquals("Друг::Пример", actualModule.getImported().get(1).toString());
    }

    @Test
    public void shouldBuildModuleWithFunctionTestingName() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н -> + н 1 .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertEquals("фибоначи", actualModule.getFunctions().get(0).getName().toString());
    }

    @Test
    public void shouldBuildModuleWithFunctionTestingType() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н -> + н 1 .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertTrue(actualModule.getFunctions().get(0).getType() instanceof IntegerType);
    }

    @Test
    public void shouldBuildModuleWithFunctionAndOneDeclaration() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н -> + н 1 .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertSame(1, actualModule.getFunctions().get(0).getDeclarations().size());
    }

    @Test
    public void shouldBuildModuleWithFunctionAndTwoDeclarations() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н -> + н 1, реално н -> + н 1 .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);

        assertSame(2, actualModule.getFunctions().get(0).getDeclarations().size());
    }

    @Test
    public void shouldBuildModuleWithFunctionTestingDeclarationArgument() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н -> + н 1 .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);
        Declaration actualDeclaration = actualModule.getFunctions().get(0).getDeclarations().get(0);
        TypedArgument actualTypedArgument = (TypedArgument) actualDeclaration.getSignature().getPatterns().get(0);

        assertSame(1, actualDeclaration.getSignature().getPatterns().size());
        assertTrue(actualTypedArgument.getType() instanceof IntegerType);
        assertEquals("н", actualTypedArgument.getName().toString());
    }

    @Test
    public void shouldBuildModuleWithFunctionTestingWithTwoArguments() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н, реален стринг -> + н 1 .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);
        Declaration actualDeclaration = actualModule.getFunctions().get(0).getDeclarations().get(0);
        TypedArgument actualStringTypedArgument = (TypedArgument) actualDeclaration.getSignature().getPatterns().get(1);

        assertSame(2, actualDeclaration.getSignature().getPatterns().size());
        assertTrue(actualStringTypedArgument.getType() instanceof RealType);
        assertEquals("стринг", actualStringTypedArgument.getName().toString());
    }

    @Test
    public void shouldBuildModuleWithFunctionTestingAllScalarTypesAsArguments() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н, реален н2, низ н3, булев н4 -> + н 1 .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);
        Declaration actualDeclaration = actualModule.getFunctions().get(0).getDeclarations().get(0);
        List<Pattern> actualArguments = actualDeclaration.getSignature().getPatterns();

        assertTrue(((TypedArgument) actualArguments.get(0)).getType() instanceof IntegerType);
        assertTrue(((TypedArgument) actualArguments.get(1)).getType() instanceof RealType);
        assertTrue(((TypedArgument) actualArguments.get(2)).getType() instanceof StringType);
        assertTrue(((TypedArgument) actualArguments.get(3)).getType() instanceof BooleanType);
    }

    @Test
    public void shouldBuildModuleWithFunctionTestingAllLiteralsAsArguments() throws IOException {
        String actualCode = "модул Тест цял фибоначи : 13, 4.3, \"string\", да -> + н 1 .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);
        Declaration actualDeclaration = actualModule.getFunctions().get(0).getDeclarations().get(0);
        List<Pattern> actualArguments = actualDeclaration.getSignature().getPatterns();

        assertTrue(((LiteralPattern) actualArguments.get(0)).getLiteral() instanceof IntegerLiteral);
        assertTrue(((LiteralPattern) actualArguments.get(1)).getLiteral() instanceof FloatLiteral);
        assertTrue(((LiteralPattern) actualArguments.get(2)).getLiteral() instanceof StringLiteral);
        assertTrue(((LiteralPattern) actualArguments.get(3)).getLiteral() instanceof BooleanTrue);
    }
}
