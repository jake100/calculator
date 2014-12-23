package loadLang;

public class CommentStripper {
	public static String strip(String str)
	{
		String[] lines = str.split("\n");
		str = "";
		for(String line : lines)
		{
			str += line.replaceAll("//.*", "") + "\n";
		}
		return str.substring(0, str.length() - 1);
	}
	public static void main(String[] args) {
		System.out.println(strip("hello world//I am dope\nfuck yall\n//I'm dope"));
	}

}
