// Generated from /home/yoan/github/zelo/src/org/zelo/syntax/concrete/Zelo.g4 by ANTLR 4.6
package org.zelo.syntax.concrete;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ZeloParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, TYPE_STRING=12, TYPE_INTEGER=13, TYPE_REAL=14, TYPE_BOOLEAN=15, 
		PUBLIC=16, PRIVATE=17, INTEGER=18, FLOAT=19, STRING=20, NAME=21, BOOLEAN=22, 
		WS=23;
	public static final int
		RULE_module = 0, RULE_use = 1, RULE_qualifiedName = 2, RULE_declaration = 3, 
		RULE_arguments = 4, RULE_argument = 5, RULE_literal_pattern = 6, RULE_expression = 7, 
		RULE_visibility = 8, RULE_type = 9;
	public static final String[] ruleNames = {
		"module", "use", "qualifiedName", "declaration", "arguments", "argument", 
		"literal_pattern", "expression", "visibility", "type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'модул'", "'ползва'", "';'", "'::'", "'('", "')'", "'='", "','", 
		"'[]'", "'{'", "'}'", "'низ'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"TYPE_STRING", "TYPE_INTEGER", "TYPE_REAL", "TYPE_BOOLEAN", "PUBLIC", 
		"PRIVATE", "INTEGER", "FLOAT", "STRING", "NAME", "BOOLEAN", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Zelo.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ZeloParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ModuleContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public List<UseContext> use() {
			return getRuleContexts(UseContext.class);
		}
		public UseContext use(int i) {
			return getRuleContext(UseContext.class,i);
		}
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).enterModule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).exitModule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZeloVisitor ) return ((ZeloVisitor<? extends T>)visitor).visitModule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_module);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			match(T__0);
			setState(21);
			qualifiedName();
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(22);
				use();
				}
				}
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << TYPE_STRING) | (1L << TYPE_INTEGER) | (1L << TYPE_REAL) | (1L << TYPE_BOOLEAN) | (1L << PUBLIC) | (1L << PRIVATE))) != 0)) {
				{
				{
				setState(28);
				declaration();
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UseContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public UseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_use; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).enterUse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).exitUse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZeloVisitor ) return ((ZeloVisitor<? extends T>)visitor).visitUse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UseContext use() throws RecognitionException {
		UseContext _localctx = new UseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_use);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__1);
			setState(35);
			qualifiedName();
			setState(36);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QualifiedNameContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(ZeloParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(ZeloParser.NAME, i);
		}
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).enterQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).exitQualifiedName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZeloVisitor ) return ((ZeloVisitor<? extends T>)visitor).visitQualifiedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_qualifiedName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(NAME);
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(39);
				match(T__3);
				setState(40);
				match(NAME);
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NAME() { return getToken(ZeloParser.NAME, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public VisibilityContext visibility() {
			return getRuleContext(VisibilityContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZeloVisitor ) return ((ZeloVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaration);
		int _la;
		try {
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
			case TYPE_STRING:
			case TYPE_INTEGER:
			case TYPE_REAL:
			case TYPE_BOOLEAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				type(0);
				setState(47);
				match(NAME);
				setState(48);
				match(T__4);
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << TYPE_STRING) | (1L << TYPE_INTEGER) | (1L << TYPE_REAL) | (1L << TYPE_BOOLEAN) | (1L << INTEGER) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN))) != 0)) {
					{
					setState(49);
					arguments();
					}
				}

				setState(52);
				match(T__5);
				setState(53);
				match(T__6);
				setState(54);
				expression();
				setState(55);
				match(T__2);
				}
				break;
			case PUBLIC:
			case PRIVATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				visibility();
				setState(58);
				type(0);
				setState(59);
				match(NAME);
				setState(60);
				match(T__4);
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << TYPE_STRING) | (1L << TYPE_INTEGER) | (1L << TYPE_REAL) | (1L << TYPE_BOOLEAN) | (1L << INTEGER) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN))) != 0)) {
					{
					setState(61);
					arguments();
					}
				}

				setState(64);
				match(T__5);
				setState(65);
				match(T__6);
				setState(66);
				expression();
				setState(67);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZeloVisitor ) return ((ZeloVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			argument();
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(72);
				match(T__7);
				setState(73);
				argument();
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NAME() { return getToken(ZeloParser.NAME, 0); }
		public Literal_patternContext literal_pattern() {
			return getRuleContext(Literal_patternContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZeloVisitor ) return ((ZeloVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_argument);
		try {
			setState(83);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
			case TYPE_STRING:
			case TYPE_INTEGER:
			case TYPE_REAL:
			case TYPE_BOOLEAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				type(0);
				setState(80);
				match(NAME);
				}
				break;
			case INTEGER:
			case FLOAT:
			case STRING:
			case BOOLEAN:
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
				literal_pattern();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Literal_patternContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(ZeloParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(ZeloParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(ZeloParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(ZeloParser.STRING, 0); }
		public Literal_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).enterLiteral_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).exitLiteral_pattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZeloVisitor ) return ((ZeloVisitor<? extends T>)visitor).visitLiteral_pattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Literal_patternContext literal_pattern() throws RecognitionException {
		Literal_patternContext _localctx = new Literal_patternContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_literal_pattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(ZeloParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(ZeloParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(ZeloParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(ZeloParser.STRING, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZeloVisitor ) return ((ZeloVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VisibilityContext extends ParserRuleContext {
		public TerminalNode PUBLIC() { return getToken(ZeloParser.PUBLIC, 0); }
		public TerminalNode PRIVATE() { return getToken(ZeloParser.PRIVATE, 0); }
		public VisibilityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_visibility; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).enterVisibility(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).exitVisibility(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZeloVisitor ) return ((ZeloVisitor<? extends T>)visitor).visitVisibility(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VisibilityContext visibility() throws RecognitionException {
		VisibilityContext _localctx = new VisibilityContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_visibility);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_la = _input.LA(1);
			if ( !(_la==PUBLIC || _la==PRIVATE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode TYPE_STRING() { return getToken(ZeloParser.TYPE_STRING, 0); }
		public TerminalNode TYPE_INTEGER() { return getToken(ZeloParser.TYPE_INTEGER, 0); }
		public TerminalNode TYPE_REAL() { return getToken(ZeloParser.TYPE_REAL, 0); }
		public TerminalNode TYPE_BOOLEAN() { return getToken(ZeloParser.TYPE_BOOLEAN, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZeloListener ) ((ZeloListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZeloVisitor ) return ((ZeloVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE_STRING:
				{
				setState(92);
				match(TYPE_STRING);
				}
				break;
			case TYPE_INTEGER:
				{
				setState(93);
				match(TYPE_INTEGER);
				}
				break;
			case TYPE_REAL:
				{
				setState(94);
				match(TYPE_REAL);
				}
				break;
			case TYPE_BOOLEAN:
				{
				setState(95);
				match(TYPE_BOOLEAN);
				}
				break;
			case T__9:
				{
				setState(96);
				match(T__9);
				setState(97);
				type(0);
				setState(98);
				match(T__7);
				setState(99);
				type(0);
				setState(100);
				match(T__10);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(108);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(104);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(105);
					match(T__8);
					}
					} 
				}
				setState(110);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9:
			return type_sempred((TypeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\31r\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\3\2\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\2\7\2 \n\2\f\2\16\2#\13\2\3\3"+
		"\3\3\3\3\3\3\3\4\3\4\3\4\7\4,\n\4\f\4\16\4/\13\4\3\5\3\5\3\5\3\5\5\5\65"+
		"\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5A\n\5\3\5\3\5\3\5\3\5"+
		"\3\5\5\5H\n\5\3\6\3\6\3\6\7\6M\n\6\f\6\16\6P\13\6\3\7\3\7\3\7\3\7\5\7"+
		"V\n\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\5\13i\n\13\3\13\3\13\7\13m\n\13\f\13\16\13p\13\13\3\13"+
		"\2\3\24\f\2\4\6\b\n\f\16\20\22\24\2\4\4\2\24\26\30\30\3\2\22\23t\2\26"+
		"\3\2\2\2\4$\3\2\2\2\6(\3\2\2\2\bG\3\2\2\2\nI\3\2\2\2\fU\3\2\2\2\16W\3"+
		"\2\2\2\20Y\3\2\2\2\22[\3\2\2\2\24h\3\2\2\2\26\27\7\3\2\2\27\33\5\6\4\2"+
		"\30\32\5\4\3\2\31\30\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2"+
		"\34!\3\2\2\2\35\33\3\2\2\2\36 \5\b\5\2\37\36\3\2\2\2 #\3\2\2\2!\37\3\2"+
		"\2\2!\"\3\2\2\2\"\3\3\2\2\2#!\3\2\2\2$%\7\4\2\2%&\5\6\4\2&\'\7\5\2\2\'"+
		"\5\3\2\2\2(-\7\27\2\2)*\7\6\2\2*,\7\27\2\2+)\3\2\2\2,/\3\2\2\2-+\3\2\2"+
		"\2-.\3\2\2\2.\7\3\2\2\2/-\3\2\2\2\60\61\5\24\13\2\61\62\7\27\2\2\62\64"+
		"\7\7\2\2\63\65\5\n\6\2\64\63\3\2\2\2\64\65\3\2\2\2\65\66\3\2\2\2\66\67"+
		"\7\b\2\2\678\7\t\2\289\5\20\t\29:\7\5\2\2:H\3\2\2\2;<\5\22\n\2<=\5\24"+
		"\13\2=>\7\27\2\2>@\7\7\2\2?A\5\n\6\2@?\3\2\2\2@A\3\2\2\2AB\3\2\2\2BC\7"+
		"\b\2\2CD\7\t\2\2DE\5\20\t\2EF\7\5\2\2FH\3\2\2\2G\60\3\2\2\2G;\3\2\2\2"+
		"H\t\3\2\2\2IN\5\f\7\2JK\7\n\2\2KM\5\f\7\2LJ\3\2\2\2MP\3\2\2\2NL\3\2\2"+
		"\2NO\3\2\2\2O\13\3\2\2\2PN\3\2\2\2QR\5\24\13\2RS\7\27\2\2SV\3\2\2\2TV"+
		"\5\16\b\2UQ\3\2\2\2UT\3\2\2\2V\r\3\2\2\2WX\t\2\2\2X\17\3\2\2\2YZ\t\2\2"+
		"\2Z\21\3\2\2\2[\\\t\3\2\2\\\23\3\2\2\2]^\b\13\1\2^i\7\16\2\2_i\7\17\2"+
		"\2`i\7\20\2\2ai\7\21\2\2bc\7\f\2\2cd\5\24\13\2de\7\n\2\2ef\5\24\13\2f"+
		"g\7\r\2\2gi\3\2\2\2h]\3\2\2\2h_\3\2\2\2h`\3\2\2\2ha\3\2\2\2hb\3\2\2\2"+
		"in\3\2\2\2jk\f\4\2\2km\7\13\2\2lj\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2"+
		"\2o\25\3\2\2\2pn\3\2\2\2\f\33!-\64@GNUhn";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}