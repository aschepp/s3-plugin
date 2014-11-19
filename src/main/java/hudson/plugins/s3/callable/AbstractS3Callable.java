package hudson.plugins.s3.callable;

import java.io.Serializable;

import com.amazonaws.services.s3.AmazonS3Client;

public class AbstractS3Callable implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final AmazonS3Client client;

    public AbstractS3Callable(AmazonS3Client client) 
    {
        this.client = client;
    }

    protected AmazonS3Client getClient() {
        return client;
    }
}
