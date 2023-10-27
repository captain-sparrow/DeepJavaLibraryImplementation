package com.example.djl.demoDJL.rest;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.djl.MalformedModelException;
import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications.Classification;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.modality.cv.output.DetectedObjects.DetectedObject;
import ai.djl.ndarray.NDList;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;

@RestController
public class Rest {

	@GetMapping("/load")
	public void loadModel() throws TranslateException, ModelNotFoundException, MalformedModelException, IOException {
		  String text = "This is an example sentence";

	        Criteria<String, float[]> criteria =
	                Criteria.builder()
	                        .setTypes(String.class, float[].class)
	                        .optModelUrls(
	                                "djl://ai.djl.huggingface.pytorch/sentence-transformers/all-MiniLM-L6-v2")
	                        .optEngine("PyTorch")
//	                        .optTranslatorFactory(new TextEmbeddingTranslatorFactory())
//	                        .optProgress(new ProgressBar())
	                        .build();

//	        try (ZooModel<String, float[]> model = criteria.loadModel();
//	                Predictor<String, float[]> predictor = model.newPredictor()) {
//	            float[] res = predictor.predict(text);
//	            System.out.println("Embedding: " + Arrays.toString(res));
//}
	        
	        ZooModel<String,float[]> model = criteria.loadModel();
	        System.out.print("model block : " + model.getBlock());
	        System.out.print("loaded model successfully");
	        
	        model.getModelPath();
	        final String EXTERNAL_MODEL_PATH = "/Users/sharvari/eclipse-workspace/modelLoadedHere/";
//	        Path pathToSave = Paths.get(EXTERNAL_MODEL_PATH);
//	        model.save(pathToSave, "newlycreatedmodel");
	        
	        Path sourceFolder = model.getModelPath();
	        Path destinationFolder = Paths.get(EXTERNAL_MODEL_PATH);
	}
	        
//	        try {
//	            // Copy the entire folder and its contents to the destination
//	            copyFolder(sourceFolder, destinationFolder);
//	            System.out.println("Folder copied successfully!");
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
	        
	        private static void copyFolder(Path source, Path destination) throws IOException {
	            // Create the destination folder if it doesn't exist
	            if (!Files.exists(destination)) {
	                Files.createDirectories(destination);
	            }
	            
	            Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
	                @Override
	                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
	                    Path relativePath = source.relativize(file);
	                    Path destinationFile = destination.resolve(relativePath);
	                    Files.copy(file, destinationFile, StandardCopyOption.REPLACE_EXISTING);
	                    return FileVisitResult.CONTINUE;
	                }
	                
	                @Override
	                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
	                    Path relativePath = source.relativize(dir);
	                    Path destinationDir = destination.resolve(relativePath);
	                    if (!Files.exists(destinationDir)) {
	                        Files.createDirectories(destinationDir);
	                    }
	                    return FileVisitResult.CONTINUE;
	                }
	            });
	        
	 
	       // Path modelPath = externalFolderPath.resolve("my_model_folder");
            // Save the model to the external folder
            //model.save(Paths.get(EXTERNAL_MODEL_PATH), "sbert_model");
	        

	
}
	
	
	 @GetMapping("/hi")
     public String returnHello() {
     	return "Hello!";
     }
}
