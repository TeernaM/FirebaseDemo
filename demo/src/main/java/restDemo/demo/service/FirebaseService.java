package restDemo.demo.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import restDemo.demo.object.Person;

import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    public String saveUserDetails(Person person) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore(); //will get the db connection that we created earlier
        //we need to get the collections from the database
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(person.getName()).set(person);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Person getUserDetails(String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(name);
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = documentSnapshotApiFuture.get();
        Person person = null;
        if(documentSnapshot.exists()){
            person = documentSnapshot.toObject(Person.class);
            return person;
        }else{
            return null;
        }
    }
    public String updateUserDetails(Person person) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore(); //will get the db connection that we created earlier
        //we need to get the collections from the database
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(person.getName()).set(person);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
    public String deleteUserDetails(String name){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResultApiFuture = dbFirestore.collection("users").document(name).delete();
        return "Document with ID "+name+" has been deleted";
    }
}
