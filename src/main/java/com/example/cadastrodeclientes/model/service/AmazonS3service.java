package com.example.cadastrodeclientes.model.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.example.cadastrodeclientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AmazonS3service {
    private AmazonS3 s3cliente;

    @Value("${aws.key}")
    private String chave;

    @Value("${aws.secret}")
    private String segredo;

    @Value("${aws.region}")
    private String regiao;

    @Value("${aws.bucketName}")
    private String bucket;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostConstruct
    private void iniciarAmazonS3(){
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(chave, segredo);
        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(basicAWSCredentials);
        s3cliente = AmazonS3ClientBuilder.standard().withRegion(regiao).withCredentials(awsStaticCredentialsProvider).build();
    }

    public List<String> listarFotos (){
        List<String> nomeFotos = new ArrayList<>();
        ObjectListing objectListing = s3cliente.listObjects(bucket);
        List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        if(objectSummaries != null){
            for(S3ObjectSummary obj : objectSummaries){
                String nomeFoto = obj.getKey();
                nomeFotos.add(nomeFoto);
            }
        }
        return nomeFotos;
    }
    public void salvarFotos (File arquivo){
        String nomeArquivo = arquivo.getName();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket,nomeArquivo,arquivo);
        s3cliente.putObject(putObjectRequest);
    }
    public void deletarFotos (String nomeFoto){
        s3cliente.deleteObject(bucket,nomeFoto);
    }
    public File downloadFotos (String nomeFoto) throws IOException {
        S3Object object = s3cliente.getObject(bucket,nomeFoto);
        S3ObjectInputStream s3is = object.getObjectContent();
        byte[] bytes = s3is.readAllBytes();
        File file = File.createTempFile("temp",nomeFoto);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        s3is.close();
        fos.close();
        return file;
    }

    public void salvarFotos (MultipartFile multipartFile){
        try{
            File arquivo = converterArquivos(multipartFile);
            salvarFotos(arquivo);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private File converterArquivos (MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


}
