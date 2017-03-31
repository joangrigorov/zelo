package org.zelo.ast;

import org.junit.Test;
import org.zelo.ast.module.Declaration;
import org.zelo.ast.type.IntegerType;

import java.io.IOException;

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
    public void shouldBuildModuleWithFunctionTestingDeclarationArguments() throws IOException {
        String actualCode = "модул Тест цял фибоначи : цял н -> + н 1, реално н -> + н 1 .";
        Module actualModule = new ASTBuilder().buildAST(actualCode);
        Declaration actualDeclaration = actualModule.getFunctions().get(0).getDeclarations().get(0);

        assertSame(2, actualDeclaration);
    }
}