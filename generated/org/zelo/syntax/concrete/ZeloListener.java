// Generated from /home/yoan/github/zelo/src/org/zelo/syntax/concrete/Zelo.g4 by ANTLR 4.6
package org.zelo.syntax.concrete;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ZeloParser}.
 */
public interface ZeloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ZeloParser#module}.
	 * @param ctx the parse tree
	 */
	void enterModule(ZeloParser.ModuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZeloParser#module}.
	 * @param ctx the parse tree
	 */
	void exitModule(ZeloParser.ModuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZeloParser#use}.
	 * @param ctx the parse tree
	 */
	void enterUse(ZeloParser.UseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZeloParser#use}.
	 * @param ctx the parse tree
	 */
	void exitUse(ZeloParser.UseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZeloParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(ZeloParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZeloParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(ZeloParser.QualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZeloParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(ZeloParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZeloParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(ZeloParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZeloParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(ZeloParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZeloParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(ZeloParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZeloParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(ZeloParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZeloParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(ZeloParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZeloParser#literal_pattern}.
	 * @param ctx the parse tree
	 */
	void enterLiteral_pattern(ZeloParser.Literal_patternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZeloParser#literal_pattern}.
	 * @param ctx the parse tree
	 */
	void exitLiteral_pattern(ZeloParser.Literal_patternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZeloParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ZeloParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZeloParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ZeloParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZeloParser#visibility}.
	 * @param ctx the parse tree
	 */
	void enterVisibility(ZeloParser.VisibilityContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZeloParser#visibility}.
	 * @param ctx the parse tree
	 */
	void exitVisibility(ZeloParser.VisibilityContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZeloParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ZeloParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZeloParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ZeloParser.TypeContext ctx);
}