package com.administrator.filmarte.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

@Document(collection = "ShareSocialNetwork")
public class ShareSocialNetwork {

    @Id
    @Field
    private String idNetwork;

    @NotBlank(message = "Network name must not be blank.")
    @Size(max = 100, message = "Network name must be less than 100 characters.")
    private String nameNetwork;

    @NotNull(message = "URL must not be null.")
    @URL(message = "Invalid URL format.")
    private String urlNetwork;

    public ShareSocialNetwork(String idNetwork, String nameNetwork, String urlNetwork) {
        this.idNetwork = idNetwork;
        this.nameNetwork = nameNetwork;
        this.urlNetwork = urlNetwork;
    }

    public ShareSocialNetwork() {
    }

    public String getIdNetwork() {
        return idNetwork;
    }

    public void setIdNetwork(String idNetwork) {
        this.idNetwork = idNetwork;
    }

    public String getNameNetwork() {
        return nameNetwork;
    }

    public void setNameNetwork(String nameNetwork) {
        this.nameNetwork = nameNetwork;
    }

    public String getUrlNetwork() {
        return urlNetwork;
    }

    public void setUrlNetwork(String urlNetwork) {
        this.urlNetwork = urlNetwork;
    }

    @Override
    public String toString() {
        return "ShareSocialNetwork [idNetwork=" + idNetwork + ", nameNetwork=" + nameNetwork + ", urlNetwork=" + urlNetwork + "]";
    }
}
