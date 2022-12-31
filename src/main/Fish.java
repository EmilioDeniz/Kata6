package main;

import com.google.gson.JsonObject;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fish")

public class Fish {
    public String SpeciesName;
    public String ScientificName;
    public JsonObject Images;

    public Fish(String SpeciesName, String ScientificName, JsonObject Images) {
        this.SpeciesName = SpeciesName;
        this.ScientificName = ScientificName;
        this.Images = Images;
    }
    
    public Fish(){
        
    }

    public String getSpeciesName() {
        return SpeciesName;
    }

    public String getScientificName() {
        return ScientificName;
    }

    public JsonObject getImages() {
        return Images;
    }
    
    @XmlElement(name = "SpeciesName")
    public void setSpeciesName(String SpeciesName) {
        this.SpeciesName = SpeciesName;
    }
    
    @XmlElement(name = "ScientificName")
    public void setScientificName(String ScientificName) {
        this.ScientificName = ScientificName;
    }

    @XmlElement(name = "ImageGallery")
    public void setImages(JsonObject Images) {
        this.Images = Images;
    }

    @Override
    public String toString() {
        return "Fish{" + "SpeciesName=" + SpeciesName + ", ScientificName=" + ScientificName + ", Images=" + Images + '}';
    }    
}
