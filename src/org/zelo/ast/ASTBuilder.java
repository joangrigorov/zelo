package org.zelo.ast;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.zelo.ast.expression.*;
import org.zelo.ast.expression.literal.*;
import org.zelo.ast.function.*;
import org.zelo.ast.expression.essential_functions.*;
import org.zelo.ast.module.*;
import org.zelo.ast.name.Name;
import org.zelo.ast.name.Names;
import org.zelo.ast.name.QualifiedName;
import org.zelo.ast.name.QualifiedNameList;
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

import static org.zelo.ast.util.SourceLocationHydrator.*;
import static org.zelo.ast.util.StringUnquoter.unquoteString;

public class ASTBuilder extends ZeloBaseVisitor<Node> implements ZeloVisitor<Node> {

    public Module buildAST(InputStream inputStream) throws IOException {
        return (Module) visit(createParser(inputStream).module());
    }

    public Module buildAST(String input) throws IOException {
        return (Module) visit(createParser(input).module());
    }

    private ZeloParser createParser(String input) {
        return new ZeloParser(new CommonTokenStream(new ZeloLexer(new ANTLRInputStream(input))));
    }

    private ZeloParser createParser(InputStream inputStream) throws IOException {
        return new ZeloParser(new CommonTokenStream(new ZeloLexer(new ANTLRInputStream(inputStream))));
    }

    @Override
    public Module visitModuleWithExports(ZeloParser.ModuleWithExportsContext ctx) {
        return hydrateSourceLocation(new Module(
            visitQualifiedName(ctx.qualifiedName()),
            visitExports(ctx.exports()),
            visitImports(ctx.use()),
            visitFunctions(ctx.function())
        ), ctx);
    }

    @Override
    public Module visitModuleWithoutExports(ZeloParser.ModuleWithoutExportsContext ctx) {
        return hydrateSourceLocation(new Module(
            visitQualifiedName(ctx.qualifiedName()),
            new Names(new ArrayList<>()),
            visitImports(ctx.use()),
            visitFunctions(ctx.function())
        ), ctx);
    }

    @Override
    public Names visitExports(ZeloParser.ExportsContext ctx) {
        return visitNames(ctx.names());
    }

    private FunctionList visitFunctions(List<ZeloParser.FunctionContext> functionContexts) {
        List<Function> functions = new ArrayList<>();

        functionContexts.forEach(functionContext -> functions.add((Function) visit(functionContext)));

        return new FunctionList(functions);
    }

    private QualifiedNameList visitImports(List<ZeloParser.UseContext> useContexts) {
        List<QualifiedName> imports = new ArrayList<>();

        useContexts.forEach(useContext -> imports.add(visitQualifiedName(useContext.qualifiedName())));

        return new QualifiedNameList(imports);
    }

    private Name visitName(Token name) {
        return hydrateSourceLocation(new Name(name.getText()), name);
    }

    @Override
    public Names visitNames(ZeloParser.NamesContext ctx) {
        List<Name> nameList = new ArrayList<>();

        ctx.nameList.forEach(name -> nameList.add(visitName(name)));

        return hydrateSourceLocation(new Names(nameList), ctx);
    }

    @Override
    public Node visitUse(ZeloParser.UseContext ctx) {
        return null;
    }

    @Override
    public QualifiedName visitQualifiedName(ZeloParser.QualifiedNameContext ctx) {
        return hydrateSourceLocation(new QualifiedName(visitNames(ctx.parts), visitName(ctx.finalName)), ctx);
    }

    private Names visitNames(List<Token> parts) {
        List<Name> names = new ArrayList<>();
        parts.forEach(name -> names.add(visitName(name)));
        return new Names(names);
    }

    private DeclarationList visitDeclarations(List<ZeloParser.DeclarationContext> declarationContexts) {
        List<Declaration> declarations = new ArrayList<>();

        declarationContexts.forEach(declarationContext -> declarations.add(visitDeclaration(declarationContext)));

        return new DeclarationList(declarations);
    }

    @Override
    public Declaration visitDeclaration(ZeloParser.DeclarationContext ctx) {
        return hydrateSourceLocation(
            new Declaration(visitSignature(ctx.signature), (Expression) visit(ctx.expression())), ctx);
    }

    private PatternList visitSignature(ZeloParser.PatternsContext patternsContext) {
        List<Pattern> patterns = new ArrayList<>();

        patternsContext.patternList.forEach(patternContext -> patterns.add((Pattern) visit(patternContext)));

        return hydrateSourceLocation(new PatternList(patterns), patternsContext);
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
    public Call visitCallDefined(ZeloParser.CallDefinedContext ctx) {
        List<Expression> argumentList = new ArrayList<>();

        ctx.args.forEach(arg -> argumentList.add((Expression) visit(arg)));

        ExpressionList arguments = hydrateSourceLocation(new ExpressionList(argumentList), ctx);

        return hydrateSourceLocation(new Call((Expression) visit(ctx.caller), arguments), ctx);
    }

    @Override
    public Composition visitComposition(ZeloParser.CompositionContext ctx) {
        return hydrateSourceLocation(new Composition((Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs)), ctx);
    }

    @Override
    public NativeCall visitCallNative(ZeloParser.CallNativeContext ctx) {
        List<Expression> arguments = new ArrayList<>();

        ctx.args.forEach(arg -> arguments.add((Expression) visit(arg)));

        return hydrateSourceLocation(new NativeCall(
            (Essential) visit(ctx.nativeFunction()),
            new ExpressionList(arguments)
        ), ctx);
    }

    @Override
    public Function visitUntypedFunction(ZeloParser.UntypedFunctionContext ctx) {
        return hydrateSourceLocation(new Function(
            visitName(ctx.name),
            visitDeclarations(ctx.declarations)
        ), ctx);
    }

    @Override
    public TypedFunction visitTypedFunction(ZeloParser.TypedFunctionContext ctx) {
        return hydrateSourceLocation(new TypedFunction(
            (Type) visit(ctx.type()),
            visitName(ctx.name),
            visitDeclarations(ctx.declarations)
        ), ctx);
    }

    @Override
    public Essential visitNegation(ZeloParser.NegationContext ctx) {
        return hydrateSourceLocation(new Negation(), ctx);
    }

    @Override
    public Essential visitAddition(ZeloParser.AdditionContext ctx) {
        return hydrateSourceLocation(new Addition(), ctx);
    }

    @Override
    public Essential visitSubtraction(ZeloParser.SubtractionContext ctx) {
        return hydrateSourceLocation(new Subtraction(), ctx);
    }

    @Override
    public Essential visitIncrement(ZeloParser.IncrementContext ctx) {
        return hydrateSourceLocation(new Increment(), ctx);
    }

    @Override
    public Essential visitDecrement(ZeloParser.DecrementContext ctx) {
        return hydrateSourceLocation(new Decrement(), ctx);
    }

    @Override
    public Essential visitMultiplication(ZeloParser.MultiplicationContext ctx) {
        return hydrateSourceLocation(new Multiplication(), ctx);
    }

    @Override
    public Essential visitDivision(ZeloParser.DivisionContext ctx) {
        return hydrateSourceLocation(new Division(), ctx);
    }

    @Override
    public Essential visitGreaterThan(ZeloParser.GreaterThanContext ctx) {
        return hydrateSourceLocation(new GreaterThan(), ctx);
    }

    @Override
    public Essential visitLowerThan(ZeloParser.LowerThanContext ctx) {
        return hydrateSourceLocation(new LowerThan(), ctx);
    }

    @Override
    public Essential visitEqual(ZeloParser.EqualContext ctx) {
        return hydrateSourceLocation(new Equal(), ctx);
    }

    @Override
    public Essential visitNotEqual(ZeloParser.NotEqualContext ctx) {
        return hydrateSourceLocation(new NotEqual(), ctx);
    }

    @Override
    public Essential visitLowerThanOrEqual(ZeloParser.LowerThanOrEqualContext ctx) {
        return hydrateSourceLocation(new LowerThanOrEqual(), ctx);
    }

    @Override
    public Essential visitGreaterThanOrEqual(ZeloParser.GreaterThanOrEqualContext ctx) {
        return hydrateSourceLocation(new GreaterThanOrEqual(), ctx);
    }

    @Override
    public Essential visitLogicalAnd(ZeloParser.LogicalAndContext ctx) {
        return hydrateSourceLocation(new LogicalAnd(), ctx);
    }

    @Override
    public Essential visitLogicalOr(ZeloParser.LogicalOrContext ctx) {
        return hydrateSourceLocation(new LogicalOr(), ctx);
    }

    @Override
    public Literal visitLiteralExpression(ZeloParser.LiteralExpressionContext ctx) {
        return (Literal) visit(ctx.literal());
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

    @Override
    public Symbol visitSymbol(ZeloParser.SymbolContext ctx) {
        return hydrateSourceLocation(new Symbol(visitName(ctx.NAME())), ctx);
    }
}
