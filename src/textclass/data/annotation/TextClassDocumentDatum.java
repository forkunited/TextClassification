package textclass.data.annotation;

import ark.data.DataTools;
import ark.data.annotation.Datum;
import ark.data.annotation.nlp.TokenSpan;

public class TextClassDocumentDatum<L> extends Datum<L> {
	protected TextClassDocument textClassDocument;
	
	public TextClassDocumentDatum(int id, TextClassDocument textClassDocument, L label) {
		this.id = id;
		this.textClassDocument = textClassDocument;
		this.label = label;
	}
	
	public TextClassDocument getTextClassDocument() {
		return this.textClassDocument;
	}
	
	public static Tools<String> getCategoryTools(DataTools dataTools) {
		Tools<String> tools = new Tools<String>(dataTools) {
			@Override
			public String labelFromString(String str) {
				return str;
			}
		};
		
		return tools;
	}
	
	private static abstract class Tools<L> extends Datum.Tools<TextClassDocumentDatum<L>, L> {
		public Tools(DataTools dataTools) {
			super(dataTools);
			
			this.addTokenSpanExtractor(new TokenSpanExtractor<TextClassDocumentDatum<L>, L>() {
				@Override
				public String toString() {
					return "SentenceTokenSpans";
				}
				
				@Override
				public TokenSpan[] extract(TextClassDocumentDatum<L> textClassDocumentDatum) {
					TextClassDocument document = textClassDocumentDatum.getTextClassDocument();
					int sentenceCount = document.getSentenceCount();
					TokenSpan[] sentenceSpans = new TokenSpan[sentenceCount];
					for (int i = 0; i < sentenceCount; i++) {
						sentenceSpans[i] = new TokenSpan(document, i, 0, document.getSentenceTokenCount(i));
					}
					
					return sentenceSpans;
				}
			});
		}
	}
}
