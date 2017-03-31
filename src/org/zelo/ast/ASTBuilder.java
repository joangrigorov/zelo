package org.zelo.ast;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.zelo.ast.expression.Call;
import org.zelo.ast.expression.Composition;
import org.zelo.ast.expression.NativeCall;
import org.zelo.ast.expression.literal.*;
import org.zelo.ast.function.LiteralPattern;
import org.zelo.ast.function.essential.Essential;
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

        patternsContext.patternList.forEach(patternContext -> patterns.add((Pattern) visit(patternContext)));

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
    public Call visitCall(ZeloParser.CallContext ctx) {
        List<Expression> arguments = new ArrayList<>();

        ctx.args.forEach(arg -> arguments.add((Expression) visit(arg)));

        return hydrateSourceLocation(new Call((Expression) visit(ctx.caller), arguments), ctx);
    }

    @Override
    public Composition visitComposition(ZeloParser.CompositionContext ctx) {
        return hydrateSourceLocation(new Composition((Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs)), ctx);
    }

    @Override
    public NativeCall visitNativeCall(ZeloParser.NativeCallContext ctx) {
        List<Expression> arguments = new ArrayList<>();

        ctx.args.forEach(arg -> arguments.add((Expression) visit(arg)));

        return hydrateSourceLocation(new NativeCall((Essential) visit(ctx.nativeFunction()), arguments), ctx);
    }

    @Override
    public Literal visitLiteralExpression(ZeloParser.LiteralExpressionContext ctx) {
        return (Literal) visit(ctx.literal());
    }

    @Override
    public Node visitGroup(ZeloParser.GroupContext ctx) {
        return null;
    }

    @Override
    public StringType visitTypeString(ZeloParser.TypeStringContext ctx) {
        return hydrateSourceLocation(new StringType(), ctx);
    }

    @Override
    public IntegerType visitTypeInteger(ZeloParser.TypeIntegerContext ctx) {
        return hydrateSourceLocation(new IntegerType(), ctx);
    }

    @Override
    public RealType visitTypeReal(ZeloParser.TypeRealContext ctx) {
        return hydrateSourceLocation(new RealType(), ctx);
    }

    @Override
    public BooleanType visitTypeBoolean(ZeloParser.TypeBooleanContext ctx) {
        return hydrateSourceLocation(new BooleanType(), ctx);
    }
}
