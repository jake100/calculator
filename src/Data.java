
public class Data
{
	private static int total = 0;
	protected int id;
	protected String name;
	protected String dataString;
	public Data(String name)
	{
		id = ++total;
		if(name == null)this.name = id + "";
		else this.name = name;
	}
}
