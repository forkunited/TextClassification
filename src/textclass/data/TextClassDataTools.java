package textclass.data;

import textclass.util.TextClassProperties;

import ark.data.DataTools;
import ark.util.OutputWriter;

public class TextClassDataTools extends DataTools {
	private TextClassProperties properties;
	
	public TextClassDataTools(OutputWriter outputWriter, TextClassProperties properties) {
		super(outputWriter);
		this.properties = properties;
	}
	
	public TextClassProperties getProperties() {
		return this.properties;
	}
}