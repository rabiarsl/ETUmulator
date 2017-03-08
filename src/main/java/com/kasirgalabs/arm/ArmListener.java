// Generated from Arm.g4 by ANTLR 4.6
package com.kasirgalabs.arm;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ArmParser}.
 */
public interface ArmListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ArmParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(ArmParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(ArmParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(ArmParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(ArmParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(ArmParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(ArmParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(ArmParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(ArmParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#adds}.
	 * @param ctx the parse tree
	 */
	void enterAdds(ArmParser.AddsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#adds}.
	 * @param ctx the parse tree
	 */
	void exitAdds(ArmParser.AddsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#adc}.
	 * @param ctx the parse tree
	 */
	void enterAdc(ArmParser.AdcContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#adc}.
	 * @param ctx the parse tree
	 */
	void exitAdc(ArmParser.AdcContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#adcs}.
	 * @param ctx the parse tree
	 */
	void enterAdcs(ArmParser.AdcsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#adcs}.
	 * @param ctx the parse tree
	 */
	void exitAdcs(ArmParser.AdcsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#sub}.
	 * @param ctx the parse tree
	 */
	void enterSub(ArmParser.SubContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#sub}.
	 * @param ctx the parse tree
	 */
	void exitSub(ArmParser.SubContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#subs}.
	 * @param ctx the parse tree
	 */
	void enterSubs(ArmParser.SubsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#subs}.
	 * @param ctx the parse tree
	 */
	void exitSubs(ArmParser.SubsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#sbc}.
	 * @param ctx the parse tree
	 */
	void enterSbc(ArmParser.SbcContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#sbc}.
	 * @param ctx the parse tree
	 */
	void exitSbc(ArmParser.SbcContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#sbcs}.
	 * @param ctx the parse tree
	 */
	void enterSbcs(ArmParser.SbcsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#sbcs}.
	 * @param ctx the parse tree
	 */
	void exitSbcs(ArmParser.SbcsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#rsb}.
	 * @param ctx the parse tree
	 */
	void enterRsb(ArmParser.RsbContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#rsb}.
	 * @param ctx the parse tree
	 */
	void exitRsb(ArmParser.RsbContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#rsbs}.
	 * @param ctx the parse tree
	 */
	void enterRsbs(ArmParser.RsbsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#rsbs}.
	 * @param ctx the parse tree
	 */
	void exitRsbs(ArmParser.RsbsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#rsc}.
	 * @param ctx the parse tree
	 */
	void enterRsc(ArmParser.RscContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#rsc}.
	 * @param ctx the parse tree
	 */
	void exitRsc(ArmParser.RscContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#rscs}.
	 * @param ctx the parse tree
	 */
	void enterRscs(ArmParser.RscsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#rscs}.
	 * @param ctx the parse tree
	 */
	void exitRscs(ArmParser.RscsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#mul}.
	 * @param ctx the parse tree
	 */
	void enterMul(ArmParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#mul}.
	 * @param ctx the parse tree
	 */
	void exitMul(ArmParser.MulContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#muls}.
	 * @param ctx the parse tree
	 */
	void enterMuls(ArmParser.MulsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#muls}.
	 * @param ctx the parse tree
	 */
	void exitMuls(ArmParser.MulsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#mla}.
	 * @param ctx the parse tree
	 */
	void enterMla(ArmParser.MlaContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#mla}.
	 * @param ctx the parse tree
	 */
	void exitMla(ArmParser.MlaContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#mlas}.
	 * @param ctx the parse tree
	 */
	void enterMlas(ArmParser.MlasContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#mlas}.
	 * @param ctx the parse tree
	 */
	void exitMlas(ArmParser.MlasContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#mls}.
	 * @param ctx the parse tree
	 */
	void enterMls(ArmParser.MlsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#mls}.
	 * @param ctx the parse tree
	 */
	void exitMls(ArmParser.MlsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#umull}.
	 * @param ctx the parse tree
	 */
	void enterUmull(ArmParser.UmullContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#umull}.
	 * @param ctx the parse tree
	 */
	void exitUmull(ArmParser.UmullContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#umulls}.
	 * @param ctx the parse tree
	 */
	void enterUmulls(ArmParser.UmullsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#umulls}.
	 * @param ctx the parse tree
	 */
	void exitUmulls(ArmParser.UmullsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#umlal}.
	 * @param ctx the parse tree
	 */
	void enterUmlal(ArmParser.UmlalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#umlal}.
	 * @param ctx the parse tree
	 */
	void exitUmlal(ArmParser.UmlalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#umlals}.
	 * @param ctx the parse tree
	 */
	void enterUmlals(ArmParser.UmlalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#umlals}.
	 * @param ctx the parse tree
	 */
	void exitUmlals(ArmParser.UmlalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#smull}.
	 * @param ctx the parse tree
	 */
	void enterSmull(ArmParser.SmullContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#smull}.
	 * @param ctx the parse tree
	 */
	void exitSmull(ArmParser.SmullContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#smulls}.
	 * @param ctx the parse tree
	 */
	void enterSmulls(ArmParser.SmullsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#smulls}.
	 * @param ctx the parse tree
	 */
	void exitSmulls(ArmParser.SmullsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#smlal}.
	 * @param ctx the parse tree
	 */
	void enterSmlal(ArmParser.SmlalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#smlal}.
	 * @param ctx the parse tree
	 */
	void exitSmlal(ArmParser.SmlalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#smlals}.
	 * @param ctx the parse tree
	 */
	void enterSmlals(ArmParser.SmlalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#smlals}.
	 * @param ctx the parse tree
	 */
	void exitSmlals(ArmParser.SmlalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#sdiv}.
	 * @param ctx the parse tree
	 */
	void enterSdiv(ArmParser.SdivContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#sdiv}.
	 * @param ctx the parse tree
	 */
	void exitSdiv(ArmParser.SdivContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#udiv}.
	 * @param ctx the parse tree
	 */
	void enterUdiv(ArmParser.UdivContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#udiv}.
	 * @param ctx the parse tree
	 */
	void exitUdiv(ArmParser.UdivContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#mov}.
	 * @param ctx the parse tree
	 */
	void enterMov(ArmParser.MovContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#mov}.
	 * @param ctx the parse tree
	 */
	void exitMov(ArmParser.MovContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#movs}.
	 * @param ctx the parse tree
	 */
	void enterMovs(ArmParser.MovsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#movs}.
	 * @param ctx the parse tree
	 */
	void exitMovs(ArmParser.MovsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#mvn}.
	 * @param ctx the parse tree
	 */
	void enterMvn(ArmParser.MvnContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#mvn}.
	 * @param ctx the parse tree
	 */
	void exitMvn(ArmParser.MvnContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#mvns}.
	 * @param ctx the parse tree
	 */
	void enterMvns(ArmParser.MvnsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#mvns}.
	 * @param ctx the parse tree
	 */
	void exitMvns(ArmParser.MvnsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#asr}.
	 * @param ctx the parse tree
	 */
	void enterAsr(ArmParser.AsrContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#asr}.
	 * @param ctx the parse tree
	 */
	void exitAsr(ArmParser.AsrContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#asrs}.
	 * @param ctx the parse tree
	 */
	void enterAsrs(ArmParser.AsrsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#asrs}.
	 * @param ctx the parse tree
	 */
	void exitAsrs(ArmParser.AsrsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#lsl}.
	 * @param ctx the parse tree
	 */
	void enterLsl(ArmParser.LslContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#lsl}.
	 * @param ctx the parse tree
	 */
	void exitLsl(ArmParser.LslContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#lsls}.
	 * @param ctx the parse tree
	 */
	void enterLsls(ArmParser.LslsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#lsls}.
	 * @param ctx the parse tree
	 */
	void exitLsls(ArmParser.LslsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#lsr}.
	 * @param ctx the parse tree
	 */
	void enterLsr(ArmParser.LsrContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#lsr}.
	 * @param ctx the parse tree
	 */
	void exitLsr(ArmParser.LsrContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#lsrs}.
	 * @param ctx the parse tree
	 */
	void enterLsrs(ArmParser.LsrsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#lsrs}.
	 * @param ctx the parse tree
	 */
	void exitLsrs(ArmParser.LsrsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#ror}.
	 * @param ctx the parse tree
	 */
	void enterRor(ArmParser.RorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#ror}.
	 * @param ctx the parse tree
	 */
	void exitRor(ArmParser.RorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#rors}.
	 * @param ctx the parse tree
	 */
	void enterRors(ArmParser.RorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#rors}.
	 * @param ctx the parse tree
	 */
	void exitRors(ArmParser.RorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#rrx}.
	 * @param ctx the parse tree
	 */
	void enterRrx(ArmParser.RrxContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#rrx}.
	 * @param ctx the parse tree
	 */
	void exitRrx(ArmParser.RrxContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#rrxs}.
	 * @param ctx the parse tree
	 */
	void enterRrxs(ArmParser.RrxsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#rrxs}.
	 * @param ctx the parse tree
	 */
	void exitRrxs(ArmParser.RrxsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#cmp}.
	 * @param ctx the parse tree
	 */
	void enterCmp(ArmParser.CmpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#cmp}.
	 * @param ctx the parse tree
	 */
	void exitCmp(ArmParser.CmpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#cmn}.
	 * @param ctx the parse tree
	 */
	void enterCmn(ArmParser.CmnContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#cmn}.
	 * @param ctx the parse tree
	 */
	void exitCmn(ArmParser.CmnContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#tst}.
	 * @param ctx the parse tree
	 */
	void enterTst(ArmParser.TstContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#tst}.
	 * @param ctx the parse tree
	 */
	void exitTst(ArmParser.TstContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#teq}.
	 * @param ctx the parse tree
	 */
	void enterTeq(ArmParser.TeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#teq}.
	 * @param ctx the parse tree
	 */
	void exitTeq(ArmParser.TeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#and}.
	 * @param ctx the parse tree
	 */
	void enterAnd(ArmParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#and}.
	 * @param ctx the parse tree
	 */
	void exitAnd(ArmParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#ands}.
	 * @param ctx the parse tree
	 */
	void enterAnds(ArmParser.AndsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#ands}.
	 * @param ctx the parse tree
	 */
	void exitAnds(ArmParser.AndsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#eor}.
	 * @param ctx the parse tree
	 */
	void enterEor(ArmParser.EorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#eor}.
	 * @param ctx the parse tree
	 */
	void exitEor(ArmParser.EorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#eors}.
	 * @param ctx the parse tree
	 */
	void enterEors(ArmParser.EorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#eors}.
	 * @param ctx the parse tree
	 */
	void exitEors(ArmParser.EorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#orr}.
	 * @param ctx the parse tree
	 */
	void enterOrr(ArmParser.OrrContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#orr}.
	 * @param ctx the parse tree
	 */
	void exitOrr(ArmParser.OrrContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#orrs}.
	 * @param ctx the parse tree
	 */
	void enterOrrs(ArmParser.OrrsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#orrs}.
	 * @param ctx the parse tree
	 */
	void exitOrrs(ArmParser.OrrsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#orn}.
	 * @param ctx the parse tree
	 */
	void enterOrn(ArmParser.OrnContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#orn}.
	 * @param ctx the parse tree
	 */
	void exitOrn(ArmParser.OrnContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#orns}.
	 * @param ctx the parse tree
	 */
	void enterOrns(ArmParser.OrnsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#orns}.
	 * @param ctx the parse tree
	 */
	void exitOrns(ArmParser.OrnsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bic}.
	 * @param ctx the parse tree
	 */
	void enterBic(ArmParser.BicContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bic}.
	 * @param ctx the parse tree
	 */
	void exitBic(ArmParser.BicContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bics}.
	 * @param ctx the parse tree
	 */
	void enterBics(ArmParser.BicsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bics}.
	 * @param ctx the parse tree
	 */
	void exitBics(ArmParser.BicsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#b}.
	 * @param ctx the parse tree
	 */
	void enterB(ArmParser.BContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#b}.
	 * @param ctx the parse tree
	 */
	void exitB(ArmParser.BContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#beq}.
	 * @param ctx the parse tree
	 */
	void enterBeq(ArmParser.BeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#beq}.
	 * @param ctx the parse tree
	 */
	void exitBeq(ArmParser.BeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bne}.
	 * @param ctx the parse tree
	 */
	void enterBne(ArmParser.BneContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bne}.
	 * @param ctx the parse tree
	 */
	void exitBne(ArmParser.BneContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bcs}.
	 * @param ctx the parse tree
	 */
	void enterBcs(ArmParser.BcsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bcs}.
	 * @param ctx the parse tree
	 */
	void exitBcs(ArmParser.BcsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bhs}.
	 * @param ctx the parse tree
	 */
	void enterBhs(ArmParser.BhsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bhs}.
	 * @param ctx the parse tree
	 */
	void exitBhs(ArmParser.BhsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bcc}.
	 * @param ctx the parse tree
	 */
	void enterBcc(ArmParser.BccContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bcc}.
	 * @param ctx the parse tree
	 */
	void exitBcc(ArmParser.BccContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#blo}.
	 * @param ctx the parse tree
	 */
	void enterBlo(ArmParser.BloContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#blo}.
	 * @param ctx the parse tree
	 */
	void exitBlo(ArmParser.BloContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bmi}.
	 * @param ctx the parse tree
	 */
	void enterBmi(ArmParser.BmiContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bmi}.
	 * @param ctx the parse tree
	 */
	void exitBmi(ArmParser.BmiContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bpl}.
	 * @param ctx the parse tree
	 */
	void enterBpl(ArmParser.BplContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bpl}.
	 * @param ctx the parse tree
	 */
	void exitBpl(ArmParser.BplContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bvs}.
	 * @param ctx the parse tree
	 */
	void enterBvs(ArmParser.BvsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bvs}.
	 * @param ctx the parse tree
	 */
	void exitBvs(ArmParser.BvsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bvc}.
	 * @param ctx the parse tree
	 */
	void enterBvc(ArmParser.BvcContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bvc}.
	 * @param ctx the parse tree
	 */
	void exitBvc(ArmParser.BvcContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bhi}.
	 * @param ctx the parse tree
	 */
	void enterBhi(ArmParser.BhiContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bhi}.
	 * @param ctx the parse tree
	 */
	void exitBhi(ArmParser.BhiContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bls}.
	 * @param ctx the parse tree
	 */
	void enterBls(ArmParser.BlsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bls}.
	 * @param ctx the parse tree
	 */
	void exitBls(ArmParser.BlsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bge}.
	 * @param ctx the parse tree
	 */
	void enterBge(ArmParser.BgeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bge}.
	 * @param ctx the parse tree
	 */
	void exitBge(ArmParser.BgeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#blt}.
	 * @param ctx the parse tree
	 */
	void enterBlt(ArmParser.BltContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#blt}.
	 * @param ctx the parse tree
	 */
	void exitBlt(ArmParser.BltContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bgt}.
	 * @param ctx the parse tree
	 */
	void enterBgt(ArmParser.BgtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bgt}.
	 * @param ctx the parse tree
	 */
	void exitBgt(ArmParser.BgtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#ble}.
	 * @param ctx the parse tree
	 */
	void enterBle(ArmParser.BleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#ble}.
	 * @param ctx the parse tree
	 */
	void exitBle(ArmParser.BleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bal}.
	 * @param ctx the parse tree
	 */
	void enterBal(ArmParser.BalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bal}.
	 * @param ctx the parse tree
	 */
	void exitBal(ArmParser.BalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#bl}.
	 * @param ctx the parse tree
	 */
	void enterBl(ArmParser.BlContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#bl}.
	 * @param ctx the parse tree
	 */
	void exitBl(ArmParser.BlContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#ldr}.
	 * @param ctx the parse tree
	 */
	void enterLdr(ArmParser.LdrContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#ldr}.
	 * @param ctx the parse tree
	 */
	void exitLdr(ArmParser.LdrContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#ldrb}.
	 * @param ctx the parse tree
	 */
	void enterLdrb(ArmParser.LdrbContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#ldrb}.
	 * @param ctx the parse tree
	 */
	void exitLdrb(ArmParser.LdrbContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#ldrsb}.
	 * @param ctx the parse tree
	 */
	void enterLdrsb(ArmParser.LdrsbContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#ldrsb}.
	 * @param ctx the parse tree
	 */
	void exitLdrsb(ArmParser.LdrsbContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#ldrh}.
	 * @param ctx the parse tree
	 */
	void enterLdrh(ArmParser.LdrhContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#ldrh}.
	 * @param ctx the parse tree
	 */
	void exitLdrh(ArmParser.LdrhContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#ldrsh}.
	 * @param ctx the parse tree
	 */
	void enterLdrsh(ArmParser.LdrshContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#ldrsh}.
	 * @param ctx the parse tree
	 */
	void exitLdrsh(ArmParser.LdrshContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#str}.
	 * @param ctx the parse tree
	 */
	void enterStr(ArmParser.StrContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#str}.
	 * @param ctx the parse tree
	 */
	void exitStr(ArmParser.StrContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#strb}.
	 * @param ctx the parse tree
	 */
	void enterStrb(ArmParser.StrbContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#strb}.
	 * @param ctx the parse tree
	 */
	void exitStrb(ArmParser.StrbContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#strsb}.
	 * @param ctx the parse tree
	 */
	void enterStrsb(ArmParser.StrsbContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#strsb}.
	 * @param ctx the parse tree
	 */
	void exitStrsb(ArmParser.StrsbContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#strh}.
	 * @param ctx the parse tree
	 */
	void enterStrh(ArmParser.StrhContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#strh}.
	 * @param ctx the parse tree
	 */
	void exitStrh(ArmParser.StrhContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#strsh}.
	 * @param ctx the parse tree
	 */
	void enterStrsh(ArmParser.StrshContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#strsh}.
	 * @param ctx the parse tree
	 */
	void exitStrsh(ArmParser.StrshContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#push}.
	 * @param ctx the parse tree
	 */
	void enterPush(ArmParser.PushContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#push}.
	 * @param ctx the parse tree
	 */
	void exitPush(ArmParser.PushContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#pop}.
	 * @param ctx the parse tree
	 */
	void enterPop(ArmParser.PopContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#pop}.
	 * @param ctx the parse tree
	 */
	void exitPop(ArmParser.PopContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#nop}.
	 * @param ctx the parse tree
	 */
	void enterNop(ArmParser.NopContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#nop}.
	 * @param ctx the parse tree
	 */
	void exitNop(ArmParser.NopContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#rd}.
	 * @param ctx the parse tree
	 */
	void enterRd(ArmParser.RdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#rd}.
	 * @param ctx the parse tree
	 */
	void exitRd(ArmParser.RdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#rn}.
	 * @param ctx the parse tree
	 */
	void enterRn(ArmParser.RnContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#rn}.
	 * @param ctx the parse tree
	 */
	void exitRn(ArmParser.RnContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#rm}.
	 * @param ctx the parse tree
	 */
	void enterRm(ArmParser.RmContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#rm}.
	 * @param ctx the parse tree
	 */
	void exitRm(ArmParser.RmContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#rs}.
	 * @param ctx the parse tree
	 */
	void enterRs(ArmParser.RsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#rs}.
	 * @param ctx the parse tree
	 */
	void exitRs(ArmParser.RsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#rdlo}.
	 * @param ctx the parse tree
	 */
	void enterRdlo(ArmParser.RdloContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#rdlo}.
	 * @param ctx the parse tree
	 */
	void exitRdlo(ArmParser.RdloContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#rdhi}.
	 * @param ctx the parse tree
	 */
	void enterRdhi(ArmParser.RdhiContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#rdhi}.
	 * @param ctx the parse tree
	 */
	void exitRdhi(ArmParser.RdhiContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#reglist}.
	 * @param ctx the parse tree
	 */
	void enterReglist(ArmParser.ReglistContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#reglist}.
	 * @param ctx the parse tree
	 */
	void exitReglist(ArmParser.ReglistContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#operand2}.
	 * @param ctx the parse tree
	 */
	void enterOperand2(ArmParser.Operand2Context ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#operand2}.
	 * @param ctx the parse tree
	 */
	void exitOperand2(ArmParser.Operand2Context ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#shiftedrm}.
	 * @param ctx the parse tree
	 */
	void enterShiftedrm(ArmParser.ShiftedrmContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#shiftedrm}.
	 * @param ctx the parse tree
	 */
	void exitShiftedrm(ArmParser.ShiftedrmContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(ArmParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(ArmParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#shift}.
	 * @param ctx the parse tree
	 */
	void enterShift(ArmParser.ShiftContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#shift}.
	 * @param ctx the parse tree
	 */
	void exitShift(ArmParser.ShiftContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#imm8m}.
	 * @param ctx the parse tree
	 */
	void enterImm8m(ArmParser.Imm8mContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#imm8m}.
	 * @param ctx the parse tree
	 */
	void exitImm8m(ArmParser.Imm8mContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#offset}.
	 * @param ctx the parse tree
	 */
	void enterOffset(ArmParser.OffsetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#offset}.
	 * @param ctx the parse tree
	 */
	void exitOffset(ArmParser.OffsetContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(ArmParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(ArmParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#decimal}.
	 * @param ctx the parse tree
	 */
	void enterDecimal(ArmParser.DecimalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#decimal}.
	 * @param ctx the parse tree
	 */
	void exitDecimal(ArmParser.DecimalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#hex}.
	 * @param ctx the parse tree
	 */
	void enterHex(ArmParser.HexContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#hex}.
	 * @param ctx the parse tree
	 */
	void exitHex(ArmParser.HexContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArmParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(ArmParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArmParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(ArmParser.LabelContext ctx);
}