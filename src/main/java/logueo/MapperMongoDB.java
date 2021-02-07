package logueo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MapperMongoDB {

	private static final MapperMongoDB instance = new MapperMongoDB();

	private MapperMongoDB() {
	}

	public static MapperMongoDB getInstance() {
		return instance;
	}

	public void insertarLog(OperacionLog logMongo) {
		try (MongoClient mongoClient = MongoClients
				.create("mongodb+srv://juan:57a8Otdhqg5i0TNW@cluster0.lksmg.mongodb.net/gesoc")) {
			MongoDatabase gesoc = mongoClient.getDatabase("gesoc");
			MongoCollection<Document> logsGesoc = gesoc.getCollection("gesoc_logs");
			Document logDocumento = new Document("_id", new ObjectId());
			logDocumento.append("fecha", logMongo.getFecha().toString())
					.append("tipoOperacion", logMongo.getTipoOperacion().toString())
					.append("entidadAfectada", logMongo.getEntidadAfectada());

			logsGesoc.insertOne(logDocumento);
		}
	}

	public List<Document> buscarLog(TipoOperacion tipo, String entidad) {

		try (MongoClient mongoClient = MongoClients
				.create("mongodb+srv://juan:57a8Otdhqg5i0TNW@cluster0.lksmg.mongodb.net/gesoc")) {
			MongoDatabase gesoc = mongoClient.getDatabase("gesoc");
			MongoCollection<Document> logs = gesoc.getCollection("gesoc_logs");

			// find one document with new Document
			Document logDocumentoBuscar = new Document();
			logDocumentoBuscar.append("tipoOperacion", tipo.toString()).append("entidadAfectada", entidad);

			List<Document> logs_lista = logs.find(logDocumentoBuscar).into(new ArrayList<>());
			return logs_lista;
		}

	}
}
