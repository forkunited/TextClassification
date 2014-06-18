package textclass.data.annotation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ark.model.annotator.nlp.NLPAnnotator;
import ark.data.annotation.Language;
import ark.data.annotation.DocumentInMemory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TextClassDocument extends DocumentInMemory {
	private Map<String, String[]> metaData;
	
	public TextClassDocument(JSONObject json) {
		super(json);
	}

	public TextClassDocument(String path) {
		super(path);
	}
	
	public TextClassDocument(String name, String text, Map<String, String[]> metaData, Language language, NLPAnnotator annotator) {
		super(name, text, language, annotator);
		this.metaData = metaData;
	}
	
	public TextClassDocument(String name, String[] sentences, Map<String, String[]> metaData, Language language, NLPAnnotator annotator) {
		super(name, sentences, language, annotator);
		this.metaData = metaData;
	}
	
	public String[] getMetaData(String type) {
		return this.metaData.get(type);
	}
	
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		JSONObject metaDataJson = new JSONObject();
		for (Entry<String, String[]> entry : this.metaData.entrySet()) {
			JSONArray metaDataList = new JSONArray();
			
			for (String value : entry.getValue())
				metaDataList.add(value);
			
			metaDataJson.put(entry.getKey(), metaDataList);
		}
		
		json.put("metaData", metaDataJson);
		
		return json;
	}
	
	protected boolean fromJSON(JSONObject json) {
		if (!super.fromJSON(json))
			return false;
		
		this.metaData = new HashMap<String, String[]>();
		
		JSONObject metaDataJson = json.getJSONObject("metaData");
		for (Object metaDataKey : metaDataJson.keySet()) {
			String metaDataKeyStr = metaDataKey.toString();
			JSONArray metaDataListJson = metaDataJson.getJSONArray(metaDataKeyStr);
			String[] metaDataList = new String[metaDataListJson.size()];
			for (int i = 0; i < metaDataListJson.size(); i++) {
				metaDataList[i] = metaDataListJson.getString(i);
			}
			this.metaData.put(metaDataKeyStr, metaDataList);
		}
	
		return true;
	}
}
