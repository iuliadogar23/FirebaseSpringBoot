package com.spring.FirebaseSpringBoot.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.spring.FirebaseSpringBoot.model.Movie;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class MovieService {

    public static final String COLLECTION_NAME ="movies";

    public String saveMovieDetails(Movie movie) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_NAME).document(movie.getTitle()).set(movie);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Movie getMovieDetails(String title) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(title);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Movie movie = null;

        if(document.exists()) {
            movie = document.toObject(Movie.class);
            return movie;
        }else {
            return null;
        }
    }

    public String updateMovieDetails(Movie person) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_NAME).document(person.getTitle()).set(person);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteMovie(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COLLECTION_NAME).document(name).delete();
        return "Movie with title "+name+" has been deleted";
    }

}
