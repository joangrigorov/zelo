package org.zelo.ast;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.zelo.ast.expression.literal.*;
import org.zelo.ast.function.LiteralPattern;
import org.zelo.ast.function.Signature;
import org.zelo.ast.function.TypedArgument;
import org.zelo.ast.module.Declaration;
import org.zelo.ast.module.Function;
import org.zelo.ast.type.*;
import org.zelo.syntax.concrete.ZeloBaseVisitor;
import org.zelo.syntax.concrete.ZeloLexer;
import org.zelo.syntax.concrete.ZeloParser;
import org.zelo.syntax.concrete.ZeloVisitor;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.zelo.ast.SourceLocationHydrator.*;
import static org.zelo.ast.StringUnquoter.unquoteString;

public class ASTBuilder extends ZeloBaseVisitor<Node> implements ZeloVisitor<Node> {

    public Module buildAST(InputStream inputStream) throws IOException {
        return visitModule(createParser(inputStream).module());
    }

    public Module buildAST(String input) throws IOException {
        return visitModule(createParser(input).module());
    }

    private ZeloParser createParser(String input) {
        return new ZeloParser(new CommonTokenStream(new ZeloLexer(new ANTLRInputStream(input))));
    }

    private ZeloParser createParser(InputStream inputStream) throws IOException {
        return new ZeloParser(new CommonTokenStream(new ZeloLexer(new ANTLRInputStream(inputStream))));
    }

    @Override
    public Module visitModule(ZeloParser.ModuleContext ctx) {
        return hydrateSourceLocation(new Module(
            visitQualifiedName(ctx.qualifiedName()),
            visitExported(ctx.exports()),
            visitImports(ctx.use()),
            visitFunctions(ctx.function())
        ), ctx);
    }

    private List<Function> visitFunctions(List<ZeloParser.FunctionContext> functionContexts) {
        List<Function> functions = new ArrayList<>();

        functionContexts.forEach(functionContext -> functions.add(visitFunction(functionContext)));

        return functions;
    }

    private List<Name> visitExported(ZeloParser.ExportsContext exports) {
        List<Name> names = new ArrayList<>();

        if (exports != null) {
            exports.names().nameList.forEach(name -> names.add(visitName(name)));
        }

        return names;
    }

    private List<QualifiedName> visitImports(List<ZeloParser.UseContext> useContexts) {
        List<QualifiedName> imports = new ArrayList<>();

        useContexts.forEach(useContext -> imports.add(visitQualifiedName(useContext.qualifiedName())));

        return imports;
    }

    private Name visitName(Token name) {
        return hydrateSourceLocation(new Name(name.getText()), name);
    }

    @Override
    public Node visitNames(ZeloParser.NamesContext ctx) {
        return null;
    }

    @Override
    public Node visitUse(ZeloParser.UseContext ctx) {
        return null;
    }

    @Override
    public QualifiedName visitQualifiedName(ZeloParser.QualifiedNameContext ctx) {
        List<Name> names = new ArrayList<>();

        ctx.parts.forEach(name -> names.add(visitName(name)));

        return hydrateSourceLocation(new QualifiedName(names, visitName(ctx.finalName)), ctx);
    }

    @Override
    public Function visitFunction(ZeloParser.FunctionContext ctx) {
        return hydrateSourceLocation(new Function(
            (Type) visit(ctx.type()),
            visitName(ctx.name),
            visitDeclarations(ctx.declarations)
        ), ctx);
    }

    private List<Declaration> visitDeclarations(List<ZeloParser.DeclarationContext> declarationContexts) {
        List<Declaration> declarations = new ArrayList<>();

        declarationContexts.forEach(declarationContext -> declarations.add(visitDeclaration(declarationContext)));

        return declarations;
    }

    @Override
    public Declaration visitDeclaration(ZeloParser.DeclarationContext ctx) {
        return hydrateSourceLocation(
            new Declaration(visitSignature(ctx.signature), (Expression) visit(ctx.expression())), ctx);
    }

    private Signature visitSignature(ZeloParser.PatternsContext patternsContext) {
        List<Pattern> patterns = new ArrayList<>();

        patternsContext.pattern().forEach(patternContext -> patterns.add((Pattern) visit(patternContext)));

        return hydrateSourceLocation(new Signature(patterns), patternsContext);
    }

    @Override
    public TypedArgument visitTypedArgumentPattern(ZeloParser.TypedArgumentPatternContext ctx) {
        return hydrateSourceLocation(new TypedArgument((Type) visit(ctx.type()), visitName(ctx.NAME())), ctx);
    }

    private Name visitName(TerminalNode name) {
        return visitName(name.getSymbol());
    }

    @Override
    public LiteralPattern visitLiteralPattern(ZeloParser.LiteralPatternContext ctx) {
        return hydrateSourceLocation(new LiteralPattern((Literal) visit(ctx.literal())), ctx);
    }

    @Override
    public BooleanTrue visitBooleanTrueLiteral(ZeloParser.BooleanTrueLiteralContext ctx) {
        return hydrateSourceLocation(new BooleanTrue(), ctx);
    }

    @Override
    public BooleanFalse visitBooleanFalseLiteral(ZeloParser.BooleanFalseLiteralContext ctx) {
        return hydrateSourceLocation(new BooleanFalse(), ctx);
    }

    @Override
    public IntegerLiteral visitIntegerLiteral(ZeloParser.IntegerLiteralContext ctx) {
        return hydrateSourceLocation(new IntegerLiteral(Integer.parseInt(ctx.getText())), ctx);
    }

    @Override
    public FloatLiteral visitFloatLiteral(ZeloParser.FloatLiteralContext ctx) {
        return hydrateSourceLocation(new FloatLiteral(new BigDecimal(ctx.getText())), ctx);
    }

    @Override
    public StringLiteral visitStringLiteral(ZeloParser.StringLiteralContext ctx) {
        return hydrateSourceLocation(new StringLiteral(unquoteString(ctx.getText())), ctx);
    }

    @Override
    public Node visitIdentifier(ZeloParser.IdentifierContext ctx) {
        return null;
    }

    @Override
    public Node visitNegation(ZeloParser.NegationContext ctx) {
        return null;
    }

    @Override
    public Node visitSubtraction(ZeloParser.SubtractionContext ctx) {
        return null;
    }

    @Override
    public Node visitIncrement(ZeloParser.IncrementContext ctx) {
        return null;
    }

    @Override
    public Node visitNotEqual(ZeloParser.NotEqualContext ctx) {
        return null;
    }

    @Override
    public Node visitLogicalAnd(ZeloParser.LogicalAndContext ctx) {
        return null;
    }

    @Override
    public Node visitLowerThan(ZeloParser.LowerThanContext ctx) {
        return null;
    }

    @Override
    public Node visitGreaterThanOrEqual(ZeloParser.GreaterThanOrEqualContext ctx) {
        return null;
    }

    @Override
    public Node visitDivision(ZeloParser.DivisionContext ctx) {
        return null;
    }

    @Override
    public Node visitCall(ZeloParser.CallContext ctx) {
        return null;
    }

    @Override
    public Node visitComposition(ZeloParser.CompositionContext ctx) {
        return null;
    }

    @Override
    public Node visitDecrement(ZeloParser.DecrementContext ctx) {
        return null;
    }

    @Override
    public Node visitEquals(ZeloParser.EqualsContext ctx) {
        return null;
    }

    @Override
    public Node visitLowerThanOrEqual(ZeloParser.LowerThanOrEqualContext ctx) {
        return null;
    }

    @Override
    public Node visitMultiplication(ZeloParser.MultiplicationContext ctx) {
        return null;
    }

    @Override
    public Node visitLiteralExpression(ZeloParser.LiteralExpressionContext ctx) {
        return null;
    }

    @Override
    public Node visitLogicalOr(ZeloParser.LogicalOrContext ctx) {
        return null;
    }

    @Override
    public Node visitGroup(ZeloParser.GroupContext ctx) {
        return null;
    }

    @Override
    public Node visitAddition(ZeloParser.AdditionContext ctx) {
        return null;
    }

    @Override
    public Node visitGreaterThan(ZeloParser.GreaterThanContext ctx) {
        return null;
    }

    @Override
    public StringType visitTypeString(ZeloParser.TypeStringContext ctx) {
        return new StringType();
    }

    @Override
    public IntegerType visitTypeInteger(ZeloParser.TypeIntegerContext ctx) {
        return new IntegerType();
    }

    @Override
    public RealType visitTypeReal(ZeloParser.TypeRealContext ctx) {
        return new RealType();
    }

    @Override
    public BooleanType visitTypeBoolean(ZeloParser.TypeBooleanContext ctx) {
        return new BooleanType();
    }
}
