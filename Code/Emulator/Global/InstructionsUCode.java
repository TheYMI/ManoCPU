/**
 * **********************
 * Date: 10/06/2013
 * Time: 19:46:31
 * Description: This class is the default instruction set implementation.
 * ***********************
 */

package Emulator.Global;

public class InstructionsUCode implements iInstructionsUCode, Constants {

	// Members:
	Processor mCPU;
	String mCycleDescription;

	// Constructor:
	public InstructionsUCode(){ /* Does nothing. */}

	public void setCPU(Processor CPU){
		mCPU = CPU;
	}

	// Defines micro instructions for t0.
	public String t0(){

		mCycleDescription = "";

        // Interrupts: T0(R): AR.clr, TR <- PC;
        if (mCPU.mComponentsList[R].evaluateAsBoolean()){
			mCycleDescription = "AR.clr, TR <- PC";
			mCPU.mComponentsList[AR].clear();
			mCPU.moveData(PC, TR);
		}
        // Fetch: T0(!R): AR <- PC;
		if (!mCPU.mComponentsList[R].evaluateAsBoolean()){
			mCycleDescription = "AR <- PC";
			mCPU.moveData(PC, AR);
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t1.
	public String t1(){

		mCycleDescription = "";

		// Interrupts: T1(R): M <- TR, PC.clr;
        if (mCPU.mComponentsList[R].evaluateAsBoolean()){
			mCycleDescription = "M <- TR, PC.clr";
			mCPU.moveData(TR, M);
			mCPU.mComponentsList[PC].clear();
		}
        // Fetch: T1(!R): IR <- M, PC.inc;
		if (!mCPU.mComponentsList[R].evaluateAsBoolean()){
			mCycleDescription = "IR <- M, PC.inc";
			mCPU.moveData(M, IR);
			mCPU.mComponentsList[PC].increment();
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t2.
	public String t2(){

		mCycleDescription = "";

		// Interrupts: T2(R): PC.inc, IEN.clr, R.clr, end;
        if (mCPU.mComponentsList[R].evaluateAsBoolean()){
			mCycleDescription = "PC.inc, IEN.clr, R.clr, end";
			mCPU.mComponentsList[PC].increment();
			mCPU.mComponentsList[IEN].clear();
			mCPU.mComponentsList[R].clear();
			mCPU.resetTimer();
		}
        // Fetch: T2(!R): opcode = #IR[12-14], AR <- IR[0-11], I <- IR[15];
		if (!mCPU.mComponentsList[R].evaluateAsBoolean()){
			mCycleDescription = "Op-Code = IR[12-14], AR <- IR[0-11], I <- IR[15]";
			mCPU.set_opCode(mCPU.mComponentsList[IR].get_decimal(12, 14));
			mCPU.moveData(mCPU.mComponentsList[IR].get_value(0, 11), AR);
			mCPU.moveData(mCPU.mComponentsList[IR].get_value(15), I);
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t3.
	public String t3(){

		mCycleDescription = "";

        // Interrupts: T3(IEN && (FGI.chn || FGO.chn)): R.set;
        if (mCPU.mComponentsList[IEN].evaluateAsBoolean() && (mCPU.mComponentsList[FGI].changed() || mCPU.mComponentsList[FGO].changed())){
			mCycleDescription = "R.set";
			mCPU.mComponentsList[R].set();
		}
        // Fetch: T3(!(opcode(7)) && I): AR <- M;
		if (!(mCPU.checkOpCode(7)) && mCPU.mComponentsList[I].evaluateAsBoolean()){
			mCycleDescription = "AR <- M";
			mCPU.moveData(M, AR);
		}
        // CLA: T3(opcode(7) && !I && IR[11]): AC.clr, end;
		if (mCPU.checkOpCode(7) && !mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(11)){
			mCycleDescription = "AC.clr, end";
			mCPU.mComponentsList[AC].clear();
			mCPU.resetTimer();
		}
        // CLE: T3(opcode(7) && !I && IR[10]): E.clr, end;
		if (mCPU.checkOpCode(7) && !mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(10)){
			mCycleDescription = "E.clr, end";
			mCPU.mComponentsList[E].clear();
			mCPU.resetTimer();
		}
        // CMA: T3(opcode(7) && !I && IR[9]): AC <- <ALU:1:`~`AC>, end;
		if (mCPU.checkOpCode(7) && !mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(9)){
			mCycleDescription = "AC <- <ALU:1: `~` AC>, end";
			mCPU.moveData(AC, ALU);
			mCPU.mComponentsList[ALU].complement1();
			mCPU.moveData(ALU, AC);
			mCPU.resetTimer();
		}
        // CME: T3(opcode(7) && !I && IR[8]): E.cmp, end;
		if (mCPU.checkOpCode(7) && !mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(8)){
			mCycleDescription = "E.cmp, end";
			mCPU.mComponentsList[E].complement();
			mCPU.resetTimer();
		}
        // CIR: T3(opcode(7) && !I && IR[7]): <ALU:1:AC `>>(0)` 1>, AC[15] <- E, E <- AC[0], end;
		if (mCPU.checkOpCode(7) && !mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(7)){
			mCycleDescription = "<ALU:1:AC `>>(0)` 1>, AC[15] <- E, E <- AC[0], end";
			mCPU.moveData(AC, ALU);
			mCPU.mComponentsList[ALU].shiftRight1(1, _0);
			mCPU.moveData(ALU, AC);
			mCPU.mComponentsList[AC].set_value(15, mCPU.mComponentsList[E].get_value());
			mCPU.moveData(mCPU.mComponentsList[AC].get_value(0), E);
			mCPU.resetTimer();
		}
        // CIL: T3(opcode(7) && !I && IR[6]): <ALU:1:AC `<<(0)` 1>, AC[0] <- E, E <- AC[15], end;
		if (mCPU.checkOpCode(7) && !mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(6)){
			mCycleDescription = "<ALU:1:AC `<<(0)` 1>, AC[0] <- E, E <- AC[15], end";
			mCPU.moveData(AC, ALU);
			mCPU.mComponentsList[ALU].shiftLeft1(1, _0);
			mCPU.moveData(ALU, AC);
			mCPU.mComponentsList[AC].set_value(0, mCPU.mComponentsList[E].get_value());
			mCPU.moveData(mCPU.mComponentsList[AC].get_value(15), E);
			mCPU.resetTimer();
		}
        // INC: T3(opcode(7) && !I && IR[5]): AC.inc, end;
		if (mCPU.checkOpCode(7) && !mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(5)){
			mCycleDescription = "AC.inc, end";
			mCPU.mComponentsList[AC].increment();
			mCPU.resetTimer();
		}
        // SPA: T3(opcode(7) && !I && IR[4]): if(!AC[15] && AC[0-14]) { PC.inc }, end;
		if (mCPU.checkOpCode(7) && !mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(4)){
			mCycleDescription = "if(!AC[15] && AC[0-14]) { PC.inc }, end";
			if (!mCPU.mComponentsList[AC].evaluateAsBoolean(15) && mCPU.mComponentsList[AC].evaluateAsBoolean(0, 14)){
				mCPU.mComponentsList[PC].increment();
			}
			mCPU.resetTimer();
		}
        // SNA: T3(opcode(7) && !I && IR[3]): if(AC[15]) { PC.inc }, end;
		if (mCPU.checkOpCode(7) && !mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(3)){
			mCycleDescription = "if(AC[15]) { PC.inc }, end";
			if (mCPU.mComponentsList[AC].evaluateAsBoolean(15)){
				mCPU.mComponentsList[PC].increment();
			}
			mCPU.resetTimer();
		}
        // SZA: T3(opcode(7) && !I && IR[2]): if(!AC) { PC.inc }, end;
		if (mCPU.checkOpCode(7) && !mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(2)){
			mCycleDescription = "if(!AC) { PC.inc }, end";
			if (!mCPU.mComponentsList[AC].evaluateAsBoolean()){
				mCPU.mComponentsList[PC].increment();
			}
			mCPU.resetTimer();
		}
        // SZE: T3(opcode(7) && !I && IR[1]): if(!E) { PC.inc }, end;
		if (mCPU.checkOpCode(7) && !mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(1)){
			mCycleDescription = "if(!E) { PC.inc }, end";
			if (!mCPU.mComponentsList[E].evaluateAsBoolean()){
				mCPU.mComponentsList[PC].increment();
			}
			mCPU.resetTimer();
		}
        // HLT: T3(opcode(7) && !I && IR[0]): hlt;
		if (mCPU.checkOpCode(7) && !mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(0)){
			mCycleDescription = "hlt";
			mCPU.halt();
		}
        // INP: T3(opcode(7) && I && IR[11]): AC[0-7] <- INPR, FGI.clr, end;
		if (mCPU.checkOpCode(7) && mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(11)){
			mCycleDescription = "AC[0-7] <- INPR, FGI.clr, end";
			mCPU.mComponentsList[AC].set_value(0, 7, mCPU.mComponentsList[INPR].get_value());
			mCPU.mComponentsList[FGI].clear();
			mCPU.resetTimer();
		}
        // OUT: T3(opcode(7) && I && IR[10]): OUTR <- AC[0-7], FGO.clr, end;
		if (mCPU.checkOpCode(7) && mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(10)){
			mCycleDescription = "OUTR <- AC[0-7], FGO.clr, end";
			mCPU.moveData(mCPU.mComponentsList[AC].get_value(0, 7), OUTR);
			mCPU.mComponentsList[FGO].clear();
			mCPU.resetTimer();
		}
        // SKI: T3(opcode(7) && I && IR[9]): if(FGI) { PC.inc }, end;
		if (mCPU.checkOpCode(7) && mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(9)){
			mCycleDescription = "if(FGI) { PC.inc }, end";
			if (mCPU.mComponentsList[FGI].evaluateAsBoolean()){
				mCPU.mComponentsList[PC].increment();
			}
			mCPU.resetTimer();
		}
        // SKO: T3(opcode(7) && I && IR[8]): if(FGO) { PC.inc }, end;
		if (mCPU.checkOpCode(7) && mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(8)){
			mCycleDescription = "if(FGO) { PC.inc }, end";
			if (mCPU.mComponentsList[FGO].evaluateAsBoolean()){
				mCPU.mComponentsList[PC].increment();
			}
			mCPU.resetTimer();
		}
        // ION: T3(opcode(7) && I && IR[7]): IEN.set, end;
		if (mCPU.checkOpCode(7) && mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(7)){
			mCycleDescription = "IEN.set, end";
			mCPU.mComponentsList[IEN].set();
			mCPU.resetTimer();
		}
        // IOF: T3(opcode(7) && I && IR[6]): IEN.clr, end;
		if (mCPU.checkOpCode(7) && mCPU.mComponentsList[I].evaluateAsBoolean() && mCPU.mComponentsList[IR].evaluateAsBoolean(6)){
			mCycleDescription = "IEN.clr, end";
			mCPU.mComponentsList[IEN].clear();
			mCPU.resetTimer();
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t4.
	public String t4(){

		mCycleDescription = "";

        // Interrupts: T4(IEN && (FGI.chn || FGO.chn)): R.set;
        if (mCPU.mComponentsList[IEN].evaluateAsBoolean() && (mCPU.mComponentsList[FGI].changed() || mCPU.mComponentsList[FGO].changed())){
			mCycleDescription = "R.set";
			mCPU.mComponentsList[R].set();
		}
        // AND: T4(opcode(0)): DR <- M;
		if (mCPU.checkOpCode(0)){
			mCycleDescription = "DR <- M";
			mCPU.moveData(M, DR);
		}
        // ADD: T4(opcode(1)): DR <- M;
		if (mCPU.checkOpCode(1)){
			mCycleDescription = "DR <- M";
			mCPU.moveData(M, DR);
		}
        // LDA: T4(opcode(2)): DR <- M;
		if (mCPU.checkOpCode(2)){
			mCycleDescription = "DR <- M";
			mCPU.moveData(M, DR);
		}
        // STA: T4(opcode(3)): M <- AC, end;
		if (mCPU.checkOpCode(3)){
			mCycleDescription = "M <- AC, end";
			mCPU.moveData(AC, M);
			mCPU.resetTimer();
		}
        // BUN: T4(opcode(4)): PC <- AR, end;
		if (mCPU.checkOpCode(4)){
			mCycleDescription = "PC <- AR, end";
			mCPU.moveData(AR, PC);
			mCPU.resetTimer();
		}
        // BSA: T4(opcode(5)): M <- PC, AR.inc;
		if (mCPU.checkOpCode(5)){
			mCycleDescription = "M <- PC, AR.inc";
			mCPU.moveData(PC, M);
			mCPU.mComponentsList[AR].increment();
		}
        // ISZ: T4(opcode(6)): DR <- M;
		if (mCPU.checkOpCode(6)){
			mCycleDescription = "DR <- M";
			mCPU.moveData(M, DR);
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t5.
	public String t5(){

		mCycleDescription = "";

        // Interrupts: T5(IEN && (FGI.chn || FGO.chn)): R.set;
        if (mCPU.mComponentsList[IEN].evaluateAsBoolean() && (mCPU.mComponentsList[FGI].changed() || mCPU.mComponentsList[FGO].changed())){
			mCycleDescription = "R.set";
			mCPU.mComponentsList[R].set();
		}
        // AND: T5(opcode(0)): AC <- <ALU:AC `&` DR>, end;
		if (mCPU.checkOpCode(0)){
			mCycleDescription = "AC <- <ALU:AC `&` DR>, end";
			mCPU.moveData(DR, ALU);
			mCPU.moveData(AC, ALU);
			mCPU.mComponentsList[ALU].and();
			mCPU.moveData(ALU, AC);
			mCPU.resetTimer();
		}
        // ADD: T5(opcode(1)): AC <- <ALU:AC `+` DR>, end;
		if (mCPU.checkOpCode(1)){
			mCycleDescription = "AC <- <ALU:AC `+` DR>, end";
			mCPU.moveData(DR, ALU);
			mCPU.moveData(AC, ALU);
			mCPU.mComponentsList[ALU].sum();
			mCPU.moveData(ALU, AC);
			mCPU.resetTimer();
		}
        // LDA: T5(opcode(2)): AC <- DR, end;
		if (mCPU.checkOpCode(2)){
			mCycleDescription = "AC <- DR, end";
			mCPU.moveData(DR, AC);
			mCPU.resetTimer();
		}
        // BSA: T5(opcode(5)): PC <- AR, end;
		if (mCPU.checkOpCode(5)){
			mCycleDescription = "PC <- AR, end";
			mCPU.moveData(AR, PC);
			mCPU.resetTimer();
		}
        // ISZ: T5(opcode(6)): DR.inc;
		if (mCPU.checkOpCode(6)){
			mCycleDescription = "DR.inc";
			mCPU.mComponentsList[DR].increment();
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t6.
	public String t6(){

		mCycleDescription = "";

        // Interrupts: T6(IEN && (FGI.chn || FGO.chn)): R.set;
        if (mCPU.mComponentsList[IEN].evaluateAsBoolean() && (mCPU.mComponentsList[FGI].changed() || mCPU.mComponentsList[FGO].changed())){
			mCycleDescription = "R.set";
			mCPU.mComponentsList[R].set();
		}
        // ISZ: T6(opcode(6)): M <- DR, if(!DR) { PC.inc }, end;
		if (mCPU.checkOpCode(6)){
			mCycleDescription = "M <- DR, if(!DR) { PC.inc }, end";
			mCPU.moveData(DR, M);
			if (!mCPU.mComponentsList[DR].evaluateAsBoolean()){
				mCPU.mComponentsList[PC].increment();
			}
			mCPU.resetTimer();
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t7.
	public String t7(){

		mCycleDescription = "";

        // Interrupts: T7(IEN && (FGI.chn || FGO.chn)): R.set;
        if (mCPU.mComponentsList[IEN].evaluateAsBoolean() && (mCPU.mComponentsList[FGI].changed() || mCPU.mComponentsList[FGO].changed())){
			mCycleDescription = "R.set";
			mCPU.mComponentsList[R].set();
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t8.
	public String t8(){

		mCycleDescription = "";

        // Interrupts: T8(IEN && (FGI.chn || FGO.chn)): R.set;
        if (mCPU.mComponentsList[IEN].evaluateAsBoolean() && (mCPU.mComponentsList[FGI].changed() || mCPU.mComponentsList[FGO].changed())){
			mCycleDescription = "R.set";
			mCPU.mComponentsList[R].set();
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t9.
	public String t9(){

		mCycleDescription = "";

        // Interrupts: T9(IEN && (FGI.chn || FGO.chn)): R.set;
        if (mCPU.mComponentsList[IEN].evaluateAsBoolean() && (mCPU.mComponentsList[FGI].changed() || mCPU.mComponentsList[FGO].changed())){
			mCycleDescription = "R.set";
			mCPU.mComponentsList[R].set();
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t10.
	public String t10(){

		mCycleDescription = "";

        // Interrupts: T10(IEN && (FGI.chn || FGO.chn)): R.set;
        if (mCPU.mComponentsList[IEN].evaluateAsBoolean() && (mCPU.mComponentsList[FGI].changed() || mCPU.mComponentsList[FGO].changed())){
			mCycleDescription = "R.set";
			mCPU.mComponentsList[R].set();
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t11.
	public String t11(){

		mCycleDescription = "";

        // Interrupts: T11(IEN && (FGI.chn || FGO.chn)): R.set;
        if (mCPU.mComponentsList[IEN].evaluateAsBoolean() && (mCPU.mComponentsList[FGI].changed() || mCPU.mComponentsList[FGO].changed())){
			mCycleDescription = "R.set";
			mCPU.mComponentsList[R].set();
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t12.
	public String t12(){

		mCycleDescription = "";

        // Interrupts: T12(IEN && (FGI.chn || FGO.chn)): R.set;
        if (mCPU.mComponentsList[IEN].evaluateAsBoolean() && (mCPU.mComponentsList[FGI].changed() || mCPU.mComponentsList[FGO].changed())){
			mCycleDescription = "R.set";
			mCPU.mComponentsList[R].set();
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t13.
	public String t13(){

		mCycleDescription = "";

        // Interrupts: T13(IEN && (FGI.chn || FGO.chn)): R.set;
        if (mCPU.mComponentsList[IEN].evaluateAsBoolean() && (mCPU.mComponentsList[FGI].changed() || mCPU.mComponentsList[FGO].changed())){
			mCycleDescription = "R.set";
			mCPU.mComponentsList[R].set();
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t14.
	public String t14(){

		mCycleDescription = "";

        // Interrupts: T14(IEN && (FGI.chn || FGO.chn)): R.set;
        if (mCPU.mComponentsList[IEN].evaluateAsBoolean() && (mCPU.mComponentsList[FGI].changed() || mCPU.mComponentsList[FGO].changed())){
			mCycleDescription = "R.set";
			mCPU.mComponentsList[R].set();
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

	// Defines micro instructions for t15.
	public String t15(){

		mCycleDescription = "";

        // Interrupts: T15(IEN && (FGI.chn || FGO.chn)): R.set;
        if (mCPU.mComponentsList[IEN].evaluateAsBoolean() && (mCPU.mComponentsList[FGI].changed() || mCPU.mComponentsList[FGO].changed())){
			mCycleDescription = "R.set";
			mCPU.mComponentsList[R].set();
		}

		mCPU.nextCycle();
		return mCycleDescription;

	}

}