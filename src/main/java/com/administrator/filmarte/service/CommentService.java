package com.administrator.filmarte.service;

import com.administrator.filmarte.model.Comment;
import com.administrator.filmarte.repository.CommentRepository;
import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.azure.ai.textanalytics.models.DocumentSentiment;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository repo;

    private final TextAnalyticsClient textAnalyticsClient;

    public CommentService() {
        this.textAnalyticsClient = new TextAnalyticsClientBuilder()
                .credential(new AzureKeyCredential("f282479ac2f046f4ab847e1f092400f0"))
                .endpoint("https://analisis-de-texto.cognitiveservices.azure.com/")
                .buildClient();
    }

    public String analyzeSentiment(String content) {
        DocumentSentiment sentiment = textAnalyticsClient.analyzeSentiment(content);
        return sentiment.getSentiment().toString();
    }

    public List<Comment> getAll() {
        return repo.findAll();
    }

    public void save(Comment comment) {
        repo.save(comment);
    }

    public Comment getById(Integer idComment) {
        return repo.findById(idComment).orElse(null);
    }

    public void delete(Integer idComment) {
        repo.deleteById(idComment);
    }

    public List<Comment> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Comment> commentPage = repo.findAll(pageReq);
        return commentPage.getContent();
    }

    public List<Comment> findByAuthor(String author) {
        return repo.findByAuthorIgnoreCase(author);
    }
}