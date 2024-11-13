package com.administrator.filmarte.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ShareSocialNetwork")
public class ShareSocialNetwork {

    @Id
    @Field
    private String idNetwork;
    private String nameNetwork;
    private String urlNetwork;

    public ShareSocialNetwork(String idNetwork, String nameNetwork, String urlNetwork) {
        this.idNetwork = idNetwork;
        this.nameNetwork = nameNetwork;
        this.urlNetwork = urlNetwork;
    }

    public ShareSocialNetwork() {
   
    }

    
    public String getIdNetwork(){
        return idNetwork;
    }

    public void setIdNetwork(String idNetwork){
        this.idNetwork = idNetwork;
    }

    public String getNameNetwork(){
        return nameNetwork;
    }

    public void setNameNetwork(String nameNetwork){
        this.nameNetwork = nameNetwork;
    }

    public String getUrlNetwork(){
        return urlNetwork;
    }

    public void setUrlNetwork(String urlNetwork){
        this.urlNetwork =urlNetwork;
    }

    @Override
    public String toString(){
        return "ShareSocialNetwork [idNetwork=" + idNetwork + ", nameNetwork=" + nameNetwork + ", urlNetwork=" + urlNetwork + "]";
    }
}
