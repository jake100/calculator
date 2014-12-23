import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class VM
{
	private static String[] code;
	private Map<Integer, String> vars = new HashMap<Integer, String>();
	private static final String[] symbols = new String[]{"+", "-", "*", "/", "^", "&", "|", "%", "<", ">"};
	private static final String identifiers[] = new String[]{"+", "-", "*", "/", "^", "&", "|", "%", "<", ">", "abs", "cos", "tan", "sin", "pi", "e", "rnd", "pop", "push", "floor", "ceil", "round", "sqrt", "log", "logten", "exp", "min", "max"};
	private static final String constansts[] = new String[]{"pi", "e", "rnd", "pop"};
	private Stack<String> mathStack = new Stack<String>();
	private Stack<String> stack = new Stack<String>();
	private int index;
	private ArrayList<String> queue= new ArrayList<String>();
	private Map<String, Integer> arity = new HashMap<String, Integer>();
	public VM(String str)
	{
		arity.put("+", 0);
		arity.put("-", 0);
		arity.put("*", 1);
		arity.put("/", 1);
		arity.put("%", 1);
		arity.put("^", 2);
		arity.put("&", 3);
		arity.put("|", 3);
		arity.put("<", 3);
		arity.put(">", 3);
		arity.put("ceil", 4);
		arity.put("floor", 4);
		arity.put("round", 4);
		arity.put("push", 4);
		arity.put("log", 4);
		arity.put("logten", 4);
		arity.put("sqrt", 4);
		arity.put("sin", 4);
		arity.put("cos", 4);
		arity.put("tan", 4);
		arity.put("abs", 4);
		arity.put("exp", 4);
		arity.put("min", 4);
		arity.put("max", 4);
		arity.put("rnd", 5);
		arity.put("pop", 5);
		arity.put("e", 5);
		arity.put("pi", 5);
		StringTokenizer tokenizer = new StringTokenizer(str);
		ArrayList<String> code = new ArrayList<String>();
		
		for (int i = 0; tokenizer.hasMoreElements(); i++) {
			code.add(tokenizer.nextToken(" \n\t"));
		}
		str = "";
		for (int i = 0; i < code.size(); i++) {
			str += code.get(i) + " ";
		}
		String newStr = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			char nextChar = ' ';
			if(str.length() > i + 1)nextChar = str.charAt(i + 1);
			if((c == '-' && (""+nextChar).matches("\\d")))
			{
				newStr += " " + c + "" + nextChar;
				i++;
			}
			else if(isAny(""+c, symbols))
			{
				newStr += " " + c + " ";
			}
			else if(c == '(' || c == ')')
			{
				newStr += " " + c + " ";
			}
			else newStr += c;
		}

		tokenizer = new StringTokenizer(newStr);
		code = new ArrayList<String>();
		
		for (int i = 0; tokenizer.hasMoreElements(); i++) {
			code.add(tokenizer.nextToken(" \n\t"));
		}
		this.code = new String[code.size()];
		for (int i = 0; i < this.code.length; i++)
		{
			this.code[i] = code.get(i);
		}
	}
	public boolean isAny(int num, String strings[])
	{
		if(num >= code.length)return false;
		for (int i = 0; i < strings.length; i++) {
			if(code[num].equals(strings[i])) return true;
		}
		return false;
	}
	public static boolean isAny(String str, String strings[])
	{
		for (int i = 0; i < strings.length; i++) {
			if(str.equals(strings[i])) return true;
		}
		return false;
	}
	public String evalNum()
	{
		int end = code.length;
		for (int j = index; j < end; j++) {
			if(code[j].matches("(\\d+)|(\\-\\d+)") || code[j].matches("(\\d+\\.\\d+)|(\\-\\d+\\.\\d+)") || code[j].matches("\\w") || code[j].matches(".+ .+"))
			{
				queue.add(code[j]);
			}
			else if(code[j].equals("("))
			{
				mathStack.push(code[j]);
			}
			else if(code[j].equals(")"))
			{
				while(!mathStack.empty() && !mathStack.peek().equals("("))
				{
					queue.add(mathStack.pop());
				}
				mathStack.pop();
				
			}
			else if(isAny(code[j], identifiers))
			{
				while(!mathStack.empty() && isAny(mathStack.peek(), identifiers) && arity.get(code[j]) <= arity.get(mathStack.peek()))
				{
					String top = mathStack.pop();
					queue.add(top);
				}
				mathStack.push(code[j]);
			}
			else
			{
				System.out.println("ff");
				break;
			}
		}
		while(!mathStack.empty())
		{
			queue.add(mathStack.pop());
		}
		return evalRPN();
	}
	public String evalRPN()
	{
		System.out.println(queue);
		for (int i = 0; i < queue.size(); i++)
		{
			if(queue.get(i).matches("(\\d+)|(\\-\\d+)") || queue.get(i).matches("(\\d+\\.\\d+)|(\\-\\d+\\.\\d+)"))
			{
				mathStack.push(queue.get(i));
			}
			else if(isAny(queue.get(i), identifiers))
			{
				if(isAny(queue.get(i), constansts))
				{
					if(queue.get(i).equals("pi"))
					{
						mathStack.push(""+Math.PI);
					}
					else if(queue.get(i).equals("e"))
					{
						mathStack.push(""+Math.E);
					}
					else if(queue.get(i).equals("rnd"))
					{
						mathStack.push(""+Math.random());
					}
					else if(queue.get(i).equals("pop"))
					{
						mathStack.push(stack.pop());
					}
				}
				else
				{
					double num0 = 0;
					double num1 = 0;
					if(!mathStack.isEmpty())num1 = Double.parseDouble(mathStack.pop());
					if(!mathStack.isEmpty())num0 = Double.parseDouble(mathStack.pop());
					if(queue.get(i).equals("+"))
					{
						mathStack.push(""+ (num0 + num1));
					}
					else if(queue.get(i).equals("-"))
					{
						mathStack.push(""+ (num0 - num1));
					}
					else if(queue.get(i).equals("*"))
					{
						mathStack.push(""+ (num0 * num1));
					}
					else if(queue.get(i).equals("/"))
					{
						mathStack.push(""+ (num0 / num1));
					}
					else if(queue.get(i).equals("^"))
					{
						mathStack.push(""+ (Math.pow(num0, num1)));
					}
					else if(queue.get(i).equals("%"))
					{
						mathStack.push(""+ (num0 % num1));
					}
					else if(queue.get(i).equals("|"))
					{
						mathStack.push(""+ (double)(((int)num0) | (int)(num1)));
					}
					else if(queue.get(i).equals("&"))
					{
						mathStack.push(""+ (double)(((int)num0) & (int)(num1)));
					}
					else if(queue.get(i).equals("<"))
					{
						mathStack.push(""+ (double)(((int)num0) << (int)(num1)));
					}
					else if(queue.get(i).equals(">"))
					{
						mathStack.push(""+ (double)(((int)num0) >> (int)(num1)));
					}
					else if(queue.get(i).equals("abs"))
					{
						mathStack.push(""+num0);
						mathStack.push(""+ Math.abs(num1));
					}
					else if(queue.get(i).equals("exp"))
					{
						mathStack.push(""+num0);
						mathStack.push(""+ Math.exp(num1));
					}
					else if(queue.get(i).equals("log"))
					{
						mathStack.push(""+num0);
						mathStack.push(""+ Math.log(num1));
					}
					else if(queue.get(i).equals("logten"))
					{
						mathStack.push(""+num0);
						mathStack.push(""+ Math.log10(num1));
					}
					else if(queue.get(i).equals("sqrt"))
					{
						mathStack.push(""+num0);
						mathStack.push(""+ Math.sqrt(num1));
					}
					else if(queue.get(i).equals("cos"))
					{
						mathStack.push(""+num0);
						mathStack.push(""+ Math.cos(num1));
					}
					else if(queue.get(i).equals("tan"))
					{
						mathStack.push(""+num0);
						mathStack.push(""+ Math.tan(num1));
					}
					else if(queue.get(i).equals("sin"))
					{
						mathStack.push(""+num0);
						mathStack.push(""+ Math.sin(num1));
					}
					else if(queue.get(i).equals("min"))
					{
						mathStack.push(""+Math.min(num1, num0));

					}
					else if(queue.get(i).equals("max"))
					{
						mathStack.push(""+Math.max(num1, num0));

					}
					else if(queue.get(i).equals("push"))
					{
						mathStack.push(""+num0);
						
						if(isAny(""+num1, constansts))
						{
							stack.push(""+ evalConstant(""+num1));
						}
						else
						{
							stack.push(""+ ""+num1);
						}
					}
					else if(queue.get(i).equals("ceil"))
					{
						mathStack.push(""+num0);
						mathStack.push(""+(double)Math.ceil(num1));
					}
					else if(queue.get(i).equals("floor"))
					{
						mathStack.push(""+num0);
						mathStack.push(""+(double)Math.floor(num1));
					}
					else if(queue.get(i).equals("round"))
					{
						mathStack.push(""+num0);
						mathStack.push(""+(double)Math.round(num1));
					}
				}
			}
		}
		
		if(!mathStack.isEmpty())
		{
			String top = mathStack.pop();
			return top;
		}
		return "";
	}
	public String eval()
	{
		if(code.length == 1)
		{
			if(isAny(code[0], constansts))
			{
				return evalConstant(code[0]);
			}
		}
		return evalNum();
	}
	public String evalConstant(String s)
	{
		if(s.equals("pi"))
		{
			return ""+Math.PI;
		}
		if(s.equals("e"))
		{
			return ""+Math.E;
		}
		if(s.equals("rnd"))
		{
			return ""+Math.random();
		}
		return s;
	}
	public static void main(String[] args)
	{
		VM vm = new VM("4 min 5");

		System.out.println(vm.eval());
	}

}
