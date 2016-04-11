package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.insightcentre.nn_classifiers.dl4j.ext.Dl4j_ExtendedWVSerializer;

public class EmbeddingConverter {

	public static void main(String[] args) {
		String vocabFile = "src/main/resources/data/nips/vocab.txt";
		String WORD_VECTORS_TO_BE_USED = "src/main/resources/data/glove.6B.50d.txt";
		WordVectors wordVectors = null;
		try {
			wordVectors = Dl4j_ExtendedWVSerializer.loadTxtVectors(new File(WORD_VECTORS_TO_BE_USED));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String embFileCreate = "src/main/resources/data/nips/embeddingVocab.txt";
		StringBuilder bld = new StringBuilder();

		BufferedReader br = BasicFileTools.getBufferedReader(vocabFile);
		String line = null;

		try {
			while((line = br.readLine())!=null){
				double[] wv = wordVectors.getWordVector(line.trim());
				for(double n : wv){
					bld.append(n + " ");
				}
				bld.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		BasicFileTools.writeFile(embFileCreate, bld.toString().trim());	
	}	

}
