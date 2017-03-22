// Generated from Arm.g4 by ANTLR 4.6
package com.kasirgalabs.arm;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ArmParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ArmVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ArmParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(ArmParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(ArmParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(ArmParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic(ArmParser.ArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#multiplyAndDivide}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyAndDivide(ArmParser.MultiplyAndDivideContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#move}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMove(ArmParser.MoveContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#shift}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShift(ArmParser.ShiftContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare(ArmParser.CompareContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#logical}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical(ArmParser.LogicalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#branch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch(ArmParser.BranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#singleDataMemory}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleDataMemory(ArmParser.SingleDataMemoryContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#stack}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStack(ArmParser.StackContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(ArmParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#adds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdds(ArmParser.AddsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#adc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdc(ArmParser.AdcContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#adcs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdcs(ArmParser.AdcsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#sub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(ArmParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#subs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubs(ArmParser.SubsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#sbc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSbc(ArmParser.SbcContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#sbcs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSbcs(ArmParser.SbcsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#rsb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRsb(ArmParser.RsbContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#rsbs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRsbs(ArmParser.RsbsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#rsc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRsc(ArmParser.RscContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#rscs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRscs(ArmParser.RscsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#mul}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(ArmParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#muls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMuls(ArmParser.MulsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#mla}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMla(ArmParser.MlaContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#mlas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMlas(ArmParser.MlasContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#mls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMls(ArmParser.MlsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#sdiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSdiv(ArmParser.SdivContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#udiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUdiv(ArmParser.UdivContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#mov}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMov(ArmParser.MovContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#movs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMovs(ArmParser.MovsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#mvn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMvn(ArmParser.MvnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#mvns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMvns(ArmParser.MvnsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#movt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMovt(ArmParser.MovtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#asr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsr(ArmParser.AsrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#asrs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsrs(ArmParser.AsrsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#lsl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsl(ArmParser.LslContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#lsls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsls(ArmParser.LslsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#lsr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsr(ArmParser.LsrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#lsrs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLsrs(ArmParser.LsrsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#ror}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRor(ArmParser.RorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#rors}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRors(ArmParser.RorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#rrx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRrx(ArmParser.RrxContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#rrxs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRrxs(ArmParser.RrxsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#cmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmp(ArmParser.CmpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#cmn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmn(ArmParser.CmnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#tst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTst(ArmParser.TstContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#teq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTeq(ArmParser.TeqContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(ArmParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#ands}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnds(ArmParser.AndsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#eor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEor(ArmParser.EorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#eors}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEors(ArmParser.EorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#orr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrr(ArmParser.OrrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#orrs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrrs(ArmParser.OrrsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#orn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrn(ArmParser.OrnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#orns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrns(ArmParser.OrnsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBic(ArmParser.BicContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bics}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBics(ArmParser.BicsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitB(ArmParser.BContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#beq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeq(ArmParser.BeqContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBne(ArmParser.BneContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bcs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBcs(ArmParser.BcsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBhs(ArmParser.BhsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bcc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBcc(ArmParser.BccContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#blo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlo(ArmParser.BloContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bmi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBmi(ArmParser.BmiContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBpl(ArmParser.BplContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bvs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBvs(ArmParser.BvsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bvc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBvc(ArmParser.BvcContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bhi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBhi(ArmParser.BhiContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBls(ArmParser.BlsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bge}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBge(ArmParser.BgeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#blt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlt(ArmParser.BltContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bgt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBgt(ArmParser.BgtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#ble}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBle(ArmParser.BleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBal(ArmParser.BalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#bl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBl(ArmParser.BlContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#ldr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLdr(ArmParser.LdrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#ldrb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLdrb(ArmParser.LdrbContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#ldrsb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLdrsb(ArmParser.LdrsbContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#ldrh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLdrh(ArmParser.LdrhContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#ldrsh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLdrsh(ArmParser.LdrshContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#ldrAddress}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLdrAddress(ArmParser.LdrAddressContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#immediateOffset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImmediateOffset(ArmParser.ImmediateOffsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#postIndexedImmediate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIndexedImmediate(ArmParser.PostIndexedImmediateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#registerOffset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterOffset(ArmParser.RegisterOffsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#postIndexedRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIndexedRegister(ArmParser.PostIndexedRegisterContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#pcRelative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPcRelative(ArmParser.PcRelativeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#str}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr(ArmParser.StrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#strb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrb(ArmParser.StrbContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#strsb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrsb(ArmParser.StrsbContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#strh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrh(ArmParser.StrhContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#strsh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrsh(ArmParser.StrshContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#strOperationType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrOperationType(ArmParser.StrOperationTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#push}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPush(ArmParser.PushContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#pop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPop(ArmParser.PopContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#regList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegList(ArmParser.RegListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#rd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRd(ArmParser.RdContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#rn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRn(ArmParser.RnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#rm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRm(ArmParser.RmContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#rs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRs(ArmParser.RsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#operand2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperand2(ArmParser.Operand2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#registerShiftedByRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterShiftedByRegister(ArmParser.RegisterShiftedByRegisterContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#registerShiftedByConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterShiftedByConstant(ArmParser.RegisterShiftedByConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#shiftOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftOption(ArmParser.ShiftOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#opsh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpsh(ArmParser.OpshContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#sh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSh(ArmParser.ShContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#offset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOffset(ArmParser.OffsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#imm16}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImm16(ArmParser.Imm16Context ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#imm12}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImm12(ArmParser.Imm12Context ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#imm8m}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImm8m(ArmParser.Imm8mContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(ArmParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#data}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData(ArmParser.DataContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#asciz}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsciz(ArmParser.AscizContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArmParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(ArmParser.NumberContext ctx);
}