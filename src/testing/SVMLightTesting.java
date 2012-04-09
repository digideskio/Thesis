package testing;

import java.io.File;
import java.net.MalformedURLException;
import java.text.ParseException;

import jnisvmlight.LabeledFeatureVector;
import jnisvmlight.SVMLightInterface;
import jnisvmlight.SVMLightModel;

public class SVMLightTesting {
	
	public static void main(String[] args) throws MalformedURLException, ParseException {
		SVMLightInterface inter = new SVMLightInterface();
		LabeledFeatureVector[] featureVectors = SVMLightInterface.getLabeledFeatureVectorsFromURL(new File("src/data/svm/training_svm").toURL(), 0);
		SVMLightInterface.SORT_INPUT_VECTORS = true;
		SVMLightModel model  = inter.trainModel(featureVectors);
		
	}

}
