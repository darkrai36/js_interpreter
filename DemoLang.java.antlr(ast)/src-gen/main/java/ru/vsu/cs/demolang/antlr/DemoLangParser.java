// Generated from ru/vsu/cs/demolang/antlr/DemoLang.g4 by ANTLR 4.13.2
package ru.vsu.cs.demolang.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class DemoLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, NUMBER=34, CNAME=35, COMMENT=36, LINE_COMMENT=37, 
		WS=38;
	public static final int
		RULE_num = 0, RULE_ident = 1, RULE_lvalue = 2, RULE_inc_dec = 3, RULE_primary = 4, 
		RULE_unary = 5, RULE_binary = 6, RULE_expr = 7, RULE_call = 8, RULE_ident_assign = 9, 
		RULE_assign = 10, RULE_ident_or_ident_assign = 11, RULE_vars_decl = 12, 
		RULE_type = 13, RULE_return_stmt = 14, RULE_simple_stmt = 15, RULE_expr_or_empty = 16, 
		RULE_if_stmt = 17, RULE_while_stmt = 18, RULE_for_stmt = 19, RULE_func_param = 20, 
		RULE_func = 21, RULE_stmt = 22, RULE_stmt_list = 23, RULE_prog = 24, RULE_start = 25;
	private static String[] makeRuleNames() {
		return new String[] {
			"num", "ident", "lvalue", "inc_dec", "primary", "unary", "binary", "expr", 
			"call", "ident_assign", "assign", "ident_or_ident_assign", "vars_decl", 
			"type", "return_stmt", "simple_stmt", "expr_or_empty", "if_stmt", "while_stmt", 
			"for_stmt", "func_param", "func", "stmt", "stmt_list", "prog", "start"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'--'", "'++'", "'('", "')'", "'+'", "'-'", "'!'", "'*'", "'/'", 
			"'%'", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'&&'", "'||'", 
			"','", "'='", "'int'", "'string'", "'void'", "'return'", "'break'", "'continue'", 
			"'if'", "'else'", "'while'", "'for'", "';'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "NUMBER", 
			"CNAME", "COMMENT", "LINE_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "DemoLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DemoLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(DemoLangParser.NUMBER, 0); }
		public NumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_num; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitNum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumContext num() throws RecognitionException {
		NumContext _localctx = new NumContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_num);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(NUMBER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IdentContext extends ParserRuleContext {
		public TerminalNode CNAME() { return getToken(DemoLangParser.CNAME, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(CNAME);
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

	@SuppressWarnings("CheckReturnValue")
	public static class LvalueContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public LvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lvalue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitLvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LvalueContext lvalue() throws RecognitionException {
		LvalueContext _localctx = new LvalueContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_lvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			ident();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Inc_decContext extends ParserRuleContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public Inc_decContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inc_dec; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitInc_dec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Inc_decContext inc_dec() throws RecognitionException {
		Inc_decContext _localctx = new Inc_decContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_inc_dec);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				match(T__0);
				setState(59);
				lvalue();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				match(T__1);
				setState(61);
				lvalue();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				lvalue();
				setState(63);
				match(T__0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(65);
				lvalue();
				setState(66);
				match(T__1);
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public NumContext num() {
			return getRuleContext(NumContext.class,0);
		}
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public Inc_decContext inc_dec() {
			return getRuleContext(Inc_decContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_primary);
		try {
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				num();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				lvalue();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				inc_dec();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(73);
				match(T__2);
				setState(74);
				expr();
				setState(75);
				match(T__3);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(77);
				call();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryContext extends ParserRuleContext {
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitUnary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_unary);
		int _la;
		try {
			setState(83);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case T__5:
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 224L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(81);
				unary();
				}
				break;
			case T__0:
			case T__1:
			case T__2:
			case NUMBER:
			case CNAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
				primary();
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

	@SuppressWarnings("CheckReturnValue")
	public static class BinaryContext extends ParserRuleContext {
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public List<BinaryContext> binary() {
			return getRuleContexts(BinaryContext.class);
		}
		public BinaryContext binary(int i) {
			return getRuleContext(BinaryContext.class,i);
		}
		public BinaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitBinary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryContext binary() throws RecognitionException {
		return binary(0);
	}

	private BinaryContext binary(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BinaryContext _localctx = new BinaryContext(_ctx, _parentState);
		BinaryContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_binary, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(86);
			unary();
			}
			_ctx.stop = _input.LT(-1);
			setState(108);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(106);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binary);
						setState(88);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(89);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1792L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(90);
						binary(8);
						}
						break;
					case 2:
						{
						_localctx = new BinaryContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binary);
						setState(91);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(92);
						_la = _input.LA(1);
						if ( !(_la==T__4 || _la==T__5) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(93);
						binary(7);
						}
						break;
					case 3:
						{
						_localctx = new BinaryContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binary);
						setState(94);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(95);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 30720L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(96);
						binary(6);
						}
						break;
					case 4:
						{
						_localctx = new BinaryContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binary);
						setState(97);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(98);
						_la = _input.LA(1);
						if ( !(_la==T__14 || _la==T__15) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(99);
						binary(5);
						}
						break;
					case 5:
						{
						_localctx = new BinaryContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binary);
						setState(100);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(101);
						match(T__16);
						setState(102);
						binary(4);
						}
						break;
					case 6:
						{
						_localctx = new BinaryContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binary);
						setState(103);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(104);
						match(T__17);
						setState(105);
						binary(3);
						}
						break;
					}
					} 
				}
				setState(110);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public BinaryContext binary() {
			return getRuleContext(BinaryContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			binary(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CallContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			ident();
			setState(114);
			match(T__2);
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 51539607790L) != 0)) {
				{
				setState(115);
				expr();
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__18) {
					{
					{
					setState(116);
					match(T__18);
					setState(117);
					expr();
					}
					}
					setState(122);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(125);
			match(T__3);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Ident_assignContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Ident_assignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident_assign; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitIdent_assign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ident_assignContext ident_assign() throws RecognitionException {
		Ident_assignContext _localctx = new Ident_assignContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ident_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			ident();
			setState(128);
			match(T__19);
			setState(129);
			expr();
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

	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ParserRuleContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			lvalue();
			setState(132);
			match(T__19);
			setState(133);
			expr();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Ident_or_ident_assignContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public Ident_assignContext ident_assign() {
			return getRuleContext(Ident_assignContext.class,0);
		}
		public Ident_or_ident_assignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident_or_ident_assign; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitIdent_or_ident_assign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ident_or_ident_assignContext ident_or_ident_assign() throws RecognitionException {
		Ident_or_ident_assignContext _localctx = new Ident_or_ident_assignContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_ident_or_ident_assign);
		try {
			setState(137);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				ident();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(136);
				ident_assign();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class Vars_declContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<Ident_or_ident_assignContext> ident_or_ident_assign() {
			return getRuleContexts(Ident_or_ident_assignContext.class);
		}
		public Ident_or_ident_assignContext ident_or_ident_assign(int i) {
			return getRuleContext(Ident_or_ident_assignContext.class,i);
		}
		public Vars_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vars_decl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitVars_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Vars_declContext vars_decl() throws RecognitionException {
		Vars_declContext _localctx = new Vars_declContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_vars_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			type();
			setState(140);
			ident_or_ident_assign();
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__18) {
				{
				{
				setState(141);
				match(T__18);
				setState(142);
				ident_or_ident_assign();
				}
				}
				setState(147);
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

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_type);
		try {
			setState(152);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__20:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				match(T__20);
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				match(T__21);
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 3);
				{
				setState(150);
				match(T__22);
				}
				break;
			case CNAME:
				enterOuterAlt(_localctx, 4);
				{
				setState(151);
				ident();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Return_stmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitReturn_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_stmtContext return_stmt() throws RecognitionException {
		Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_return_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(T__23);
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 51539607790L) != 0)) {
				{
				setState(155);
				expr();
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_stmtContext extends ParserRuleContext {
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public Vars_declContext vars_decl() {
			return getRuleContext(Vars_declContext.class,0);
		}
		public Return_stmtContext return_stmt() {
			return getRuleContext(Return_stmtContext.class,0);
		}
		public Inc_decContext inc_dec() {
			return getRuleContext(Inc_decContext.class,0);
		}
		public Simple_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitSimple_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_stmtContext simple_stmt() throws RecognitionException {
		Simple_stmtContext _localctx = new Simple_stmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_simple_stmt);
		try {
			setState(166);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				call();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				assign();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(160);
				vars_decl();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(161);
				return_stmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(162);
				match(T__24);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(163);
				match(T__25);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(164);
				inc_dec();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class Expr_or_emptyContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_or_emptyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_or_empty; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitExpr_or_empty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_or_emptyContext expr_or_empty() throws RecognitionException {
		Expr_or_emptyContext _localctx = new Expr_or_emptyContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expr_or_empty);
		try {
			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__1:
			case T__2:
			case T__4:
			case T__5:
			case T__6:
			case NUMBER:
			case CNAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				expr();
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 2);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class If_stmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitIf_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_if_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(T__26);
			setState(173);
			match(T__2);
			setState(174);
			expr();
			setState(175);
			match(T__3);
			setState(176);
			stmt();
			setState(179);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(177);
				match(T__27);
				setState(178);
				stmt();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class While_stmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public While_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitWhile_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_stmtContext while_stmt() throws RecognitionException {
		While_stmtContext _localctx = new While_stmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_while_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(T__28);
			setState(182);
			match(T__2);
			setState(183);
			expr();
			setState(184);
			match(T__3);
			setState(185);
			stmt();
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

	@SuppressWarnings("CheckReturnValue")
	public static class For_stmtContext extends ParserRuleContext {
		public List<Simple_stmtContext> simple_stmt() {
			return getRuleContexts(Simple_stmtContext.class);
		}
		public Simple_stmtContext simple_stmt(int i) {
			return getRuleContext(Simple_stmtContext.class,i);
		}
		public Expr_or_emptyContext expr_or_empty() {
			return getRuleContext(Expr_or_emptyContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public For_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitFor_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_stmtContext for_stmt() throws RecognitionException {
		For_stmtContext _localctx = new For_stmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_for_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(T__29);
			setState(188);
			match(T__2);
			setState(189);
			simple_stmt();
			setState(190);
			match(T__30);
			setState(191);
			expr_or_empty();
			setState(192);
			match(T__30);
			setState(193);
			simple_stmt();
			setState(194);
			match(T__3);
			setState(195);
			stmt();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Func_paramContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public Func_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitFunc_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_paramContext func_param() throws RecognitionException {
		Func_paramContext _localctx = new Func_paramContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_func_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			type();
			setState(198);
			ident();
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

	@SuppressWarnings("CheckReturnValue")
	public static class FuncContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public List<Func_paramContext> func_param() {
			return getRuleContexts(Func_paramContext.class);
		}
		public Func_paramContext func_param(int i) {
			return getRuleContext(Func_paramContext.class,i);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			type();
			setState(201);
			ident();
			setState(202);
			match(T__2);
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34374418432L) != 0)) {
				{
				setState(203);
				func_param();
				setState(208);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__18) {
					{
					{
					setState(204);
					match(T__18);
					setState(205);
					func_param();
					}
					}
					setState(210);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(213);
			match(T__3);
			setState(214);
			match(T__31);
			setState(215);
			stmt_list();
			setState(216);
			match(T__32);
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

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public Simple_stmtContext simple_stmt() {
			return getRuleContext(Simple_stmtContext.class,0);
		}
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public While_stmtContext while_stmt() {
			return getRuleContext(While_stmtContext.class,0);
		}
		public For_stmtContext for_stmt() {
			return getRuleContext(For_stmtContext.class,0);
		}
		public FuncContext func() {
			return getRuleContext(FuncContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_stmt);
		try {
			setState(229);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				simple_stmt();
				setState(219);
				match(T__30);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(221);
				match(T__31);
				setState(222);
				stmt_list();
				setState(223);
				match(T__32);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(225);
				if_stmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(226);
				while_stmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(227);
				for_stmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(228);
				func();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class Stmt_listContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public Stmt_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitStmt_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Stmt_listContext stmt_list() throws RecognitionException {
		Stmt_listContext _localctx = new Stmt_listContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_stmt_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 42679140358L) != 0)) {
				{
				{
				setState(231);
				stmt();
				setState(235);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(232);
						match(T__30);
						}
						} 
					}
					setState(237);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				}
				}
				}
				setState(242);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			stmt_list();
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

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public TerminalNode EOF() { return getToken(DemoLangParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DemoLangVisitor ) return ((DemoLangVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			prog();
			setState(246);
			match(EOF);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return binary_sempred((BinaryContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean binary_sempred(BinaryContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001&\u00f9\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003E\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004O\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005T\b\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0005\u0006k\b\u0006\n\u0006\f\u0006n\t\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005"+
		"\bw\b\b\n\b\f\bz\t\b\u0003\b|\b\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u008a\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u0090\b\f"+
		"\n\f\f\f\u0093\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u0099\b\r\u0001"+
		"\u000e\u0001\u000e\u0003\u000e\u009d\b\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u00a7\b\u000f\u0001\u0010\u0001\u0010\u0003\u0010\u00ab\b\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0003\u0011\u00b4\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u00cf\b\u0015"+
		"\n\u0015\f\u0015\u00d2\t\u0015\u0003\u0015\u00d4\b\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00e6\b\u0016\u0001\u0017\u0001"+
		"\u0017\u0005\u0017\u00ea\b\u0017\n\u0017\f\u0017\u00ed\t\u0017\u0005\u0017"+
		"\u00ef\b\u0017\n\u0017\f\u0017\u00f2\t\u0017\u0001\u0018\u0001\u0018\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0000\u0001\f\u001a\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*,.02\u0000\u0005\u0001\u0000\u0005\u0007\u0001\u0000\b\n\u0001"+
		"\u0000\u0005\u0006\u0001\u0000\u000b\u000e\u0001\u0000\u000f\u0010\u0106"+
		"\u00004\u0001\u0000\u0000\u0000\u00026\u0001\u0000\u0000\u0000\u00048"+
		"\u0001\u0000\u0000\u0000\u0006D\u0001\u0000\u0000\u0000\bN\u0001\u0000"+
		"\u0000\u0000\nS\u0001\u0000\u0000\u0000\fU\u0001\u0000\u0000\u0000\u000e"+
		"o\u0001\u0000\u0000\u0000\u0010q\u0001\u0000\u0000\u0000\u0012\u007f\u0001"+
		"\u0000\u0000\u0000\u0014\u0083\u0001\u0000\u0000\u0000\u0016\u0089\u0001"+
		"\u0000\u0000\u0000\u0018\u008b\u0001\u0000\u0000\u0000\u001a\u0098\u0001"+
		"\u0000\u0000\u0000\u001c\u009a\u0001\u0000\u0000\u0000\u001e\u00a6\u0001"+
		"\u0000\u0000\u0000 \u00aa\u0001\u0000\u0000\u0000\"\u00ac\u0001\u0000"+
		"\u0000\u0000$\u00b5\u0001\u0000\u0000\u0000&\u00bb\u0001\u0000\u0000\u0000"+
		"(\u00c5\u0001\u0000\u0000\u0000*\u00c8\u0001\u0000\u0000\u0000,\u00e5"+
		"\u0001\u0000\u0000\u0000.\u00f0\u0001\u0000\u0000\u00000\u00f3\u0001\u0000"+
		"\u0000\u00002\u00f5\u0001\u0000\u0000\u000045\u0005\"\u0000\u00005\u0001"+
		"\u0001\u0000\u0000\u000067\u0005#\u0000\u00007\u0003\u0001\u0000\u0000"+
		"\u000089\u0003\u0002\u0001\u00009\u0005\u0001\u0000\u0000\u0000:;\u0005"+
		"\u0001\u0000\u0000;E\u0003\u0004\u0002\u0000<=\u0005\u0002\u0000\u0000"+
		"=E\u0003\u0004\u0002\u0000>?\u0003\u0004\u0002\u0000?@\u0005\u0001\u0000"+
		"\u0000@E\u0001\u0000\u0000\u0000AB\u0003\u0004\u0002\u0000BC\u0005\u0002"+
		"\u0000\u0000CE\u0001\u0000\u0000\u0000D:\u0001\u0000\u0000\u0000D<\u0001"+
		"\u0000\u0000\u0000D>\u0001\u0000\u0000\u0000DA\u0001\u0000\u0000\u0000"+
		"E\u0007\u0001\u0000\u0000\u0000FO\u0003\u0000\u0000\u0000GO\u0003\u0004"+
		"\u0002\u0000HO\u0003\u0006\u0003\u0000IJ\u0005\u0003\u0000\u0000JK\u0003"+
		"\u000e\u0007\u0000KL\u0005\u0004\u0000\u0000LO\u0001\u0000\u0000\u0000"+
		"MO\u0003\u0010\b\u0000NF\u0001\u0000\u0000\u0000NG\u0001\u0000\u0000\u0000"+
		"NH\u0001\u0000\u0000\u0000NI\u0001\u0000\u0000\u0000NM\u0001\u0000\u0000"+
		"\u0000O\t\u0001\u0000\u0000\u0000PQ\u0007\u0000\u0000\u0000QT\u0003\n"+
		"\u0005\u0000RT\u0003\b\u0004\u0000SP\u0001\u0000\u0000\u0000SR\u0001\u0000"+
		"\u0000\u0000T\u000b\u0001\u0000\u0000\u0000UV\u0006\u0006\uffff\uffff"+
		"\u0000VW\u0003\n\u0005\u0000Wl\u0001\u0000\u0000\u0000XY\n\u0007\u0000"+
		"\u0000YZ\u0007\u0001\u0000\u0000Zk\u0003\f\u0006\b[\\\n\u0006\u0000\u0000"+
		"\\]\u0007\u0002\u0000\u0000]k\u0003\f\u0006\u0007^_\n\u0005\u0000\u0000"+
		"_`\u0007\u0003\u0000\u0000`k\u0003\f\u0006\u0006ab\n\u0004\u0000\u0000"+
		"bc\u0007\u0004\u0000\u0000ck\u0003\f\u0006\u0005de\n\u0003\u0000\u0000"+
		"ef\u0005\u0011\u0000\u0000fk\u0003\f\u0006\u0004gh\n\u0002\u0000\u0000"+
		"hi\u0005\u0012\u0000\u0000ik\u0003\f\u0006\u0003jX\u0001\u0000\u0000\u0000"+
		"j[\u0001\u0000\u0000\u0000j^\u0001\u0000\u0000\u0000ja\u0001\u0000\u0000"+
		"\u0000jd\u0001\u0000\u0000\u0000jg\u0001\u0000\u0000\u0000kn\u0001\u0000"+
		"\u0000\u0000lj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000m\r\u0001"+
		"\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000op\u0003\f\u0006\u0000p\u000f"+
		"\u0001\u0000\u0000\u0000qr\u0003\u0002\u0001\u0000r{\u0005\u0003\u0000"+
		"\u0000sx\u0003\u000e\u0007\u0000tu\u0005\u0013\u0000\u0000uw\u0003\u000e"+
		"\u0007\u0000vt\u0001\u0000\u0000\u0000wz\u0001\u0000\u0000\u0000xv\u0001"+
		"\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000y|\u0001\u0000\u0000\u0000"+
		"zx\u0001\u0000\u0000\u0000{s\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000"+
		"\u0000|}\u0001\u0000\u0000\u0000}~\u0005\u0004\u0000\u0000~\u0011\u0001"+
		"\u0000\u0000\u0000\u007f\u0080\u0003\u0002\u0001\u0000\u0080\u0081\u0005"+
		"\u0014\u0000\u0000\u0081\u0082\u0003\u000e\u0007\u0000\u0082\u0013\u0001"+
		"\u0000\u0000\u0000\u0083\u0084\u0003\u0004\u0002\u0000\u0084\u0085\u0005"+
		"\u0014\u0000\u0000\u0085\u0086\u0003\u000e\u0007\u0000\u0086\u0015\u0001"+
		"\u0000\u0000\u0000\u0087\u008a\u0003\u0002\u0001\u0000\u0088\u008a\u0003"+
		"\u0012\t\u0000\u0089\u0087\u0001\u0000\u0000\u0000\u0089\u0088\u0001\u0000"+
		"\u0000\u0000\u008a\u0017\u0001\u0000\u0000\u0000\u008b\u008c\u0003\u001a"+
		"\r\u0000\u008c\u0091\u0003\u0016\u000b\u0000\u008d\u008e\u0005\u0013\u0000"+
		"\u0000\u008e\u0090\u0003\u0016\u000b\u0000\u008f\u008d\u0001\u0000\u0000"+
		"\u0000\u0090\u0093\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000"+
		"\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0019\u0001\u0000\u0000"+
		"\u0000\u0093\u0091\u0001\u0000\u0000\u0000\u0094\u0099\u0005\u0015\u0000"+
		"\u0000\u0095\u0099\u0005\u0016\u0000\u0000\u0096\u0099\u0005\u0017\u0000"+
		"\u0000\u0097\u0099\u0003\u0002\u0001\u0000\u0098\u0094\u0001\u0000\u0000"+
		"\u0000\u0098\u0095\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000"+
		"\u0000\u0098\u0097\u0001\u0000\u0000\u0000\u0099\u001b\u0001\u0000\u0000"+
		"\u0000\u009a\u009c\u0005\u0018\u0000\u0000\u009b\u009d\u0003\u000e\u0007"+
		"\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000"+
		"\u0000\u009d\u001d\u0001\u0000\u0000\u0000\u009e\u00a7\u0003\u0010\b\u0000"+
		"\u009f\u00a7\u0003\u0014\n\u0000\u00a0\u00a7\u0003\u0018\f\u0000\u00a1"+
		"\u00a7\u0003\u001c\u000e\u0000\u00a2\u00a7\u0005\u0019\u0000\u0000\u00a3"+
		"\u00a7\u0005\u001a\u0000\u0000\u00a4\u00a7\u0003\u0006\u0003\u0000\u00a5"+
		"\u00a7\u0001\u0000\u0000\u0000\u00a6\u009e\u0001\u0000\u0000\u0000\u00a6"+
		"\u009f\u0001\u0000\u0000\u0000\u00a6\u00a0\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a1\u0001\u0000\u0000\u0000\u00a6\u00a2\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a5\u0001\u0000\u0000\u0000\u00a7\u001f\u0001\u0000\u0000\u0000\u00a8"+
		"\u00ab\u0003\u000e\u0007\u0000\u00a9\u00ab\u0001\u0000\u0000\u0000\u00aa"+
		"\u00a8\u0001\u0000\u0000\u0000\u00aa\u00a9\u0001\u0000\u0000\u0000\u00ab"+
		"!\u0001\u0000\u0000\u0000\u00ac\u00ad\u0005\u001b\u0000\u0000\u00ad\u00ae"+
		"\u0005\u0003\u0000\u0000\u00ae\u00af\u0003\u000e\u0007\u0000\u00af\u00b0"+
		"\u0005\u0004\u0000\u0000\u00b0\u00b3\u0003,\u0016\u0000\u00b1\u00b2\u0005"+
		"\u001c\u0000\u0000\u00b2\u00b4\u0003,\u0016\u0000\u00b3\u00b1\u0001\u0000"+
		"\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4#\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b6\u0005\u001d\u0000\u0000\u00b6\u00b7\u0005\u0003\u0000"+
		"\u0000\u00b7\u00b8\u0003\u000e\u0007\u0000\u00b8\u00b9\u0005\u0004\u0000"+
		"\u0000\u00b9\u00ba\u0003,\u0016\u0000\u00ba%\u0001\u0000\u0000\u0000\u00bb"+
		"\u00bc\u0005\u001e\u0000\u0000\u00bc\u00bd\u0005\u0003\u0000\u0000\u00bd"+
		"\u00be\u0003\u001e\u000f\u0000\u00be\u00bf\u0005\u001f\u0000\u0000\u00bf"+
		"\u00c0\u0003 \u0010\u0000\u00c0\u00c1\u0005\u001f\u0000\u0000\u00c1\u00c2"+
		"\u0003\u001e\u000f\u0000\u00c2\u00c3\u0005\u0004\u0000\u0000\u00c3\u00c4"+
		"\u0003,\u0016\u0000\u00c4\'\u0001\u0000\u0000\u0000\u00c5\u00c6\u0003"+
		"\u001a\r\u0000\u00c6\u00c7\u0003\u0002\u0001\u0000\u00c7)\u0001\u0000"+
		"\u0000\u0000\u00c8\u00c9\u0003\u001a\r\u0000\u00c9\u00ca\u0003\u0002\u0001"+
		"\u0000\u00ca\u00d3\u0005\u0003\u0000\u0000\u00cb\u00d0\u0003(\u0014\u0000"+
		"\u00cc\u00cd\u0005\u0013\u0000\u0000\u00cd\u00cf\u0003(\u0014\u0000\u00ce"+
		"\u00cc\u0001\u0000\u0000\u0000\u00cf\u00d2\u0001\u0000\u0000\u0000\u00d0"+
		"\u00ce\u0001\u0000\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1"+
		"\u00d4\u0001\u0000\u0000\u0000\u00d2\u00d0\u0001\u0000\u0000\u0000\u00d3"+
		"\u00cb\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d4"+
		"\u00d5\u0001\u0000\u0000\u0000\u00d5\u00d6\u0005\u0004\u0000\u0000\u00d6"+
		"\u00d7\u0005 \u0000\u0000\u00d7\u00d8\u0003.\u0017\u0000\u00d8\u00d9\u0005"+
		"!\u0000\u0000\u00d9+\u0001\u0000\u0000\u0000\u00da\u00db\u0003\u001e\u000f"+
		"\u0000\u00db\u00dc\u0005\u001f\u0000\u0000\u00dc\u00e6\u0001\u0000\u0000"+
		"\u0000\u00dd\u00de\u0005 \u0000\u0000\u00de\u00df\u0003.\u0017\u0000\u00df"+
		"\u00e0\u0005!\u0000\u0000\u00e0\u00e6\u0001\u0000\u0000\u0000\u00e1\u00e6"+
		"\u0003\"\u0011\u0000\u00e2\u00e6\u0003$\u0012\u0000\u00e3\u00e6\u0003"+
		"&\u0013\u0000\u00e4\u00e6\u0003*\u0015\u0000\u00e5\u00da\u0001\u0000\u0000"+
		"\u0000\u00e5\u00dd\u0001\u0000\u0000\u0000\u00e5\u00e1\u0001\u0000\u0000"+
		"\u0000\u00e5\u00e2\u0001\u0000\u0000\u0000\u00e5\u00e3\u0001\u0000\u0000"+
		"\u0000\u00e5\u00e4\u0001\u0000\u0000\u0000\u00e6-\u0001\u0000\u0000\u0000"+
		"\u00e7\u00eb\u0003,\u0016\u0000\u00e8\u00ea\u0005\u001f\u0000\u0000\u00e9"+
		"\u00e8\u0001\u0000\u0000\u0000\u00ea\u00ed\u0001\u0000\u0000\u0000\u00eb"+
		"\u00e9\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec"+
		"\u00ef\u0001\u0000\u0000\u0000\u00ed\u00eb\u0001\u0000\u0000\u0000\u00ee"+
		"\u00e7\u0001\u0000\u0000\u0000\u00ef\u00f2\u0001\u0000\u0000\u0000\u00f0"+
		"\u00ee\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f1"+
		"/\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f3\u00f4"+
		"\u0003.\u0017\u0000\u00f41\u0001\u0000\u0000\u0000\u00f5\u00f6\u00030"+
		"\u0018\u0000\u00f6\u00f7\u0005\u0000\u0000\u0001\u00f73\u0001\u0000\u0000"+
		"\u0000\u0013DNSjlx{\u0089\u0091\u0098\u009c\u00a6\u00aa\u00b3\u00d0\u00d3"+
		"\u00e5\u00eb\u00f0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}