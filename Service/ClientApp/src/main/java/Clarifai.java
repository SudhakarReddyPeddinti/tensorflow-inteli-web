import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import okhttp3.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naga on 18-02-2017.
 */
public class Clarifai {
    String url;
    public Clarifai(String url){
        this.url = url;
    }

    public List<String> getAnnotation(){
        List<String> annotations = new ArrayList<String>();
        final ClarifaiClient client = new ClarifaiBuilder("KKQIegBW9uOl_3vaMSzqq4QCfPNyNBvB7XNBz1vE", "xsY48eiDhhsFo5M7HE3F71ZYkB_tEQmemlWekTgG")
                .client(new OkHttpClient()) // OPTIONAL. Allows customization of OkHttp by the user
                .buildSync(); // or use .build() to get a Future<ClarifaiClient>
        client.getToken();


        ClarifaiResponse response = client.getDefaultModels().generalModel().predict()
                .withInputs(
//                        ClarifaiInput.forImage(ClarifaiImage.of(new File("input/maxresdefault.jpg")))
                        ClarifaiInput.forImage(ClarifaiImage.of("http://wallpaper-gallery.net/images/pigeon-images/pigeon-images-20.png"))
                )
                .executeSync();
        List<ClarifaiOutput<Concept>> predictions = (List<ClarifaiOutput<Concept>>) response.get();
        if (predictions.isEmpty()) {
            System.out.println("No Predictions");
            return null;
        }
        else{
            List<Concept> data = predictions.get(0).data();
            for (int i = 0; i < data.size(); i++) {
                annotations.add(data.get(i).name());
            }
        }
        return annotations;
    }
}
