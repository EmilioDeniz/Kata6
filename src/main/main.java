package main;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.joining;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import main.Fish;


public class main {


    public static void main(String[] args) throws MalformedURLException, IOException, JAXBException {
        URL url = new URL("https://www.fishwatch.gov/api/species");
        String json = read(url);
        Gson gson = new Gson();
        JsonElement element = gson.fromJson(json, JsonElement.class);
        JsonArray arr = element.getAsJsonArray();
        List<Fish> fishes = new ArrayList<Fish>();
        
        for (int i = 0; i < arr.size(); i++) {
            JsonObject obj = arr.get(i).getAsJsonObject();
            deserealizarObjeto(obj,fishes);
        }
        
        for (Fish fish : fishes) {
            serializarObjeto(fish);
        }
    }

    private static String read(URL url) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return reader.lines().collect(joining());
        }
    }

    private static void deserealizarObjeto(JsonObject obj, List<Fish> fishes) {
        String speciesName = obj.get("Species Name").getAsString();
        String scientificName = obj.get("Scientific Name").getAsString();
          
        Fish fish = new Fish(speciesName,scientificName,obj.get("Species Illustration Photo").getAsJsonObject());
        fishes.add(fish);
    }

    private static void serializarObjeto(Fish fish) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Fish.class);
        Marshaller marshall = context.createMarshaller();
        
        marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshall.marshal(fish, System.out);
    }
}
