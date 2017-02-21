// Generated from /home/yoan/github/zelo/src/org/zelo/syntax/concrete/Zelo.g4 by ANTLR 4.6
package org.zelo.syntax.concrete;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ZeloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ZeloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ZeloParser#module}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModule(ZeloParser.ModuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZeloParser#use}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUse(ZeloParser.UseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZeloParser#qualifiedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedName(ZeloParser.QualifiedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZeloParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(ZeloParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZeloParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(ZeloParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZeloParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(ZeloParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZeloParser#literal_pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral_pattern(ZeloParser.Literal_patternContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZeloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ZeloParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZeloParser#visibility}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVisibility(ZeloParser.VisibilityContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZeloParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(ZeloParser.TypeContext ctx);
}