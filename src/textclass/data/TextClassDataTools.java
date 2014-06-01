package textclass.data;

import textclass.util.TextClassProperties;

import ark.data.DataTools;
import ark.util.OutputWriter;

public class TextClassDataTools extends DataTools {
	private TextClassProperties properties;
	
	public TextClassDataTools(OutputWriter outputWriter, TextClassProperties properties) {
		super(outputWriter);
		this.properties = properties;
		this.addPath("ExperimentOutputPath", new Path("ExperimentOutputPath", this.properties.getExperimentOutputDirPath()));
		
		this.addCleanFn(new TextClassCleanFn());
	}
	
	public TextClassProperties getProperties() {
		return this.properties;
	}
}