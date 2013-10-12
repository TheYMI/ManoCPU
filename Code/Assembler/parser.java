
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Thu Jul 18 11:45:06 IDT 2013
//----------------------------------------------------

package Assembler;

import Emulator.Components.AddressRegister;
import Emulator.Global.Constants;
import Emulator.Global.Value;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Thu Jul 18 11:45:06 IDT 2013
  */
public class parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\026\000\002\002\004\000\002\002\002\000\002\003" +
    "\004\000\002\004\004\000\002\004\003\000\002\005\005" +
    "\000\002\005\005\000\002\005\004\000\002\005\004\000" +
    "\002\005\005\000\002\005\003\000\002\006\004\000\002" +
    "\007\003\000\002\011\004\000\002\011\005\000\002\011" +
    "\004\000\002\011\005\000\002\011\004\000\002\012\003" +
    "\000\002\012\003\000\002\010\005\000\002\010\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\046\000\016\004\012\006\016\007\020\010\011\013" +
    "\017\014\013\001\002\000\020\004\ufffd\005\ufffd\006\ufffd" +
    "\007\ufffd\010\ufffd\013\ufffd\014\ufffd\001\002\000\012\006" +
    "\016\007\020\010\011\014\044\001\002\000\004\013\ufff5" +
    "\001\002\000\004\013\043\001\002\000\004\002\042\001" +
    "\002\000\004\015\041\001\002\000\004\015\037\001\002" +
    "\000\010\011\032\013\uffec\014\033\001\002\000\020\004" +
    "\012\005\031\006\016\007\020\010\011\013\017\014\013" +
    "\001\002\000\004\013\027\001\002\000\006\012\025\015" +
    "\024\001\002\000\020\004\ufff7\005\ufff7\006\ufff7\007\ufff7" +
    "\010\ufff7\013\ufff7\014\ufff7\001\002\000\006\012\022\015" +
    "\021\001\002\000\004\013\ufff2\001\002\000\004\015\023" +
    "\001\002\000\004\013\ufff1\001\002\000\004\013\ufff4\001" +
    "\002\000\004\015\026\001\002\000\004\013\ufff3\001\002" +
    "\000\020\004\ufffa\005\ufffa\006\ufffa\007\ufffa\010\ufffa\013" +
    "\ufffa\014\ufffa\001\002\000\020\004\ufffe\005\ufffe\006\ufffe" +
    "\007\ufffe\010\ufffe\013\ufffe\014\ufffe\001\002\000\004\002" +
    "\uffff\001\002\000\012\006\ufff6\007\ufff6\010\ufff6\014\ufff6" +
    "\001\002\000\006\013\000\014\035\001\002\000\004\013" +
    "\uffed\001\002\000\004\013\uffef\001\002\000\004\013\uffee" +
    "\001\002\000\004\013\040\001\002\000\020\004\ufff8\005" +
    "\ufff8\006\ufff8\007\ufff8\010\ufff8\013\ufff8\014\ufff8\001\002" +
    "\000\004\013\ufff0\001\002\000\004\002\001\001\002\000" +
    "\020\004\ufff9\005\ufff9\006\ufff9\007\ufff9\010\ufff9\013\ufff9" +
    "\014\ufff9\001\002\000\006\013\uffec\014\033\001\002\000" +
    "\004\013\050\001\002\000\004\013\047\001\002\000\020" +
    "\004\ufffb\005\ufffb\006\ufffb\007\ufffb\010\ufffb\013\ufffb\014" +
    "\ufffb\001\002\000\020\004\ufffc\005\ufffc\006\ufffc\007\ufffc" +
    "\010\ufffc\013\ufffc\014\ufffc\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\046\000\020\003\007\004\013\005\003\006\004\007" +
    "\014\010\006\011\005\001\001\000\002\001\001\000\010" +
    "\007\045\010\044\011\005\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\014\005\027\006\004\007" +
    "\014\010\006\011\005\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\006" +
    "\002\035\012\033\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}




    AddressRegister address = new AddressRegister(0, "");
    List<String> progLines = new ArrayList<String>();
    HashMap<String, String> tagTable = new HashMap<String, String>();
    String accessMode;

	public void report_error(String message, Object info){

		StringBuffer m = new StringBuffer("Error");

		if (info instanceof java_cup.runtime.Symbol) {
			java_cup.runtime.Symbol s = ((java_cup.runtime.Symbol) info);
			if (s.left >= 0) {
				m.append(" in line "+(s.left+1));
				if (s.right >= 0)
					m.append(", column "+(s.right+1));
			}
		}

		m.append(" : "+message);
        JOptionPane.showMessageDialog(null, m + "\n");

	}

	public void report_fatal_error(String message, Object info){
		report_error(message, info);
		throw new Error("Syntax Error");
	}
    
    public void print_to_files(String program, String varTable){
        String filename = "appData\\Program.csv";
        try {
            FileWriter outFile = new FileWriter(filename);
            BufferedWriter out = new BufferedWriter(outFile);
            out.write(program);
            out.close();
        }catch (Exception e){//Catch exception if any
            JOptionPane.showMessageDialog(null, "ERROR: Could not write program to " + filename + "!");
        }

        filename = "appData\\VarTable.csv";
        try {
            FileWriter outFile = new FileWriter(filename);
            BufferedWriter out = new BufferedWriter(outFile);
            out.write(varTable);
            out.close();
        }catch (Exception e){//Catch exception if any
            JOptionPane.showMessageDialog(null, "ERROR: Could not write variable table to " + filename + "!");
        }
    }
    
    public String get_program_code(){
    
        String content = "";
        String[] lines = progLines.toArray(new String[progLines.size()]);
		Pattern p = Pattern.compile("<([A-Za-z_][A-Za-z_0-9]*)>");
		Matcher m;
        
        for (int i = 0; i < lines.length; ++i){
			m = p.matcher(lines[i]);
			if (m.find()){
				String tagAddress = tagTable.get(m.group(1));
				lines[i] = lines[i].replaceAll("<([A-Za-z_][A-Za-z_0-9]*)>", tagAddress);
			}
			String[] values = lines[i].split(",");
			content += Value.toHexadecimal(values[0]) + "," + Value.toHexadecimal(values[1]) + "," + values[2] + "\r\n";
        }
		
		return content;
        
    }

    public String get_varTable_code(){
    
        String content = "";
        
        for (String h : tagTable.keySet()){
			content += h + "," + tagTable.get(h) + "\n";
        }
		
		return content;
        
    }


}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$parser$actions {
  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // operation ::= ID 
            {
              Object RESULT =null;
		int commandleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int commandright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String command = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				String line = parser.address.get_value().toString() + ",";
				if (command.equals("CLA")) { line += new Value("7800").toString(); }
				if (command.equals("CLE")) { line += new Value("7400").toString(); }
				if (command.equals("CMA")) { line += new Value("7200").toString(); }
				if (command.equals("CME")) { line += new Value("7100").toString(); }
				if (command.equals("CIR")) { line += new Value("7080").toString(); }
				if (command.equals("CIL")) { line += new Value("7040").toString(); }
				if (command.equals("INC")) { line += new Value("7020").toString(); }
				if (command.equals("SPA")) { line += new Value("7010").toString(); }
				if (command.equals("SNA")) { line += new Value("7008").toString(); }
				if (command.equals("SZA")) { line += new Value("7004").toString(); }
				if (command.equals("SZE")) { line += new Value("7002").toString(); }
				if (command.equals("HLT")) { line += new Value("7001").toString(); }
				if (command.equals("INP")) { line += new Value("F800").toString(); }
				if (command.equals("OUT")) { line += new Value("F400").toString(); }
				if (command.equals("SKI")) { line += new Value("F200").toString(); }
				if (command.equals("SKO")) { line += new Value("F100").toString(); }
				if (command.equals("ION")) { line += new Value("F080").toString(); }
				if (command.equals("IOF")) { line += new Value("F040").toString(); }
				if (line.charAt(line.length() - 1) == ','){
					throw new Error("Illegal command '" + command + "'");
				}
				line += "," + command;
				parser.progLines.add(line);
			  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("operation",6, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // operation ::= ID ID access_mode 
            {
              Object RESULT =null;
		int commandleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int commandright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		String command = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int tagleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int tagright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		String tag = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int amleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int amright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String am = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				String line = parser.address.get_value().toString() + ",";
				if (command.equals("AND")) { line += am + new Value(3, 0).toString() + "<" + tag + ">"; }
				if (command.equals("ADD")) { line += am + new Value(3, 1).toString() + "<" + tag + ">"; }
				if (command.equals("LDA")) { line += am + new Value(3, 2).toString() + "<" + tag + ">"; }
				if (command.equals("STA")) { line += am + new Value(3, 3).toString() + "<" + tag + ">"; }
				if (command.equals("BUN")) { line += am + new Value(3, 4).toString() + "<" + tag + ">"; }
				if (command.equals("BSA")) { line += am + new Value(3, 5).toString() + "<" + tag + ">"; }
				if (command.equals("ISZ")) { line += am + new Value(3, 6).toString() + "<" + tag + ">"; }
				if (line.charAt(line.length() - 1) == ','){
					throw new Error("Illegal command '" + command + "'");
				}
				line += "," + command + " " + tag + " " + parser.accessMode;
				parser.progLines.add(line);
			  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("operation",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // access_mode ::= epsilon 
            {
              String RESULT =null;
		 parser.accessMode = ""; RESULT = "0"; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("access_mode",8, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // access_mode ::= ID 
            {
              String RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int idright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				  if (id.equals("I")) { parser.accessMode = "I"; RESULT = "1"; }
				  if (RESULT == null) { throw new Error("Illegal Access Mode " + id + "!"); }
				
              CUP$parser$result = parser.getSymbolFactory().newSymbol("access_mode",8, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // number ::= BIN NUMBER 
            {
              String RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String n = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = n; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("number",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // number ::= DEC NEGATIVE NUMBER 
            {
              String RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String n = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 Value val = new Value(Constants.DATA_REGISTER_SIZE, new Integer(n)); val.complement(); val.increment(); RESULT = val.toString(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("number",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // number ::= DEC NUMBER 
            {
              String RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String n = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new Value(Constants.DATA_REGISTER_SIZE, new Integer(n)).toString(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("number",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // number ::= HEX NEGATIVE NUMBER 
            {
              String RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String n = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 Value val = new Value(n); val.complement(); val.increment(); RESULT = val.toString(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("number",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // number ::= HEX NUMBER 
            {
              String RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String n = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new Value(n).toString(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("number",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // var_declaration ::= number 
            {
              Object RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String n = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 String line = parser.address.get_value().toString() + "," + n + "," + " "; parser.progLines.add(line); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("var_declaration",5, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // tag_declaration ::= ID COMMA 
            {
              Object RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 parser.tagTable.put(id, parser.address.get_value().toString()); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("tag_declaration",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // command_line ::= NEWLINE 
            {
              Object RESULT =null;
		 /* Do nothing */ 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("command_line",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // command_line ::= ORG NUMBER NEWLINE 
            {
              Object RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		String n = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 parser.address.enableWrite(); parser.address.set_value(new Value(n)); parser.address.update(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("command_line",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // command_line ::= operation NEWLINE 
            {
              Object RESULT =null;
		 parser.address.enableWrite(); parser.address.increment(); parser.address.update(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("command_line",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // command_line ::= var_declaration NEWLINE 
            {
              Object RESULT =null;
		 parser.address.enableWrite(); parser.address.increment(); parser.address.update(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("command_line",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // command_line ::= tag_declaration var_declaration NEWLINE 
            {
              Object RESULT =null;
		 parser.address.enableWrite(); parser.address.increment(); parser.address.update(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("command_line",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // command_line ::= tag_declaration operation NEWLINE 
            {
              Object RESULT =null;
		 parser.address.enableWrite(); parser.address.increment(); parser.address.update(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("command_line",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // command_list ::= command_line 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("command_list",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // command_list ::= command_list command_line 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("command_list",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // program ::= command_list END 
            {
              Object RESULT =null;
		int clleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int clright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object cl = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 parser.print_to_files(parser.get_program_code(), parser.get_varTable_code()); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("program",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // epsilon ::= 
            {
              Object RESULT =null;
		 /* Empty statement */ 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("epsilon",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= program EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

