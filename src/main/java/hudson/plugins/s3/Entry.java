package hudson.plugins.s3;

import com.amazonaws.regions.Regions;
import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.util.ListBoxModel;
import org.kohsuke.stapler.DataBoundConstructor;

public final class Entry extends AbstractDescribableImpl<Entry> {

    /**
     * Destination bucket for the copy. Can contain macros.
     */
    public String bucket;
    /**
     * File name relative to the workspace root to upload.
     * Can contain macros and wildcards.
     */
    public String sourceFile;
    /**
     * options for x-amz-storage-class can be STANDARD or REDUCED_REDUNDANCY
     */
    public static final String[] storageClasses = {"STANDARD", "REDUCED_REDUNDANCY"};
    /**
     * what x-amz-storage-class is currently set
     */
    public String storageClass;
    /**
     * Regions Values
     */
    public static final Regions[] regions = Regions.values();
    /**
     * Stores the Region Value
     */
    public String selectedRegion;
    
    /**
     * Do not publish the artifacts when build fails
     */
    public boolean noUploadOnFailure;

    /**
     * Upload either from the slave or the master
     */
    public boolean uploadFromSlave;

    /**
     * Let Jenkins manage the S3 uploaded artifacts
     */
    public boolean managedArtifacts;
    
    /**
     * Use S3 server side encryption when uploading the artifacts
     */
    public boolean useServerSideEncryption;

    /**
     * Flatten directories
     */
    public boolean flatten;

    @DataBoundConstructor
    public Entry(String bucket, String sourceFile, String storageClass, String selectedRegion,
                 boolean noUploadOnFailure, boolean uploadFromSlave, boolean managedArtifacts,
                 boolean useServerSideEncryption, boolean flatten) {
        this.bucket = bucket;
        this.sourceFile = sourceFile;
        this.storageClass = storageClass;
        this.selectedRegion = selectedRegion;
        this.noUploadOnFailure = noUploadOnFailure;
        this.uploadFromSlave = uploadFromSlave;
        this.managedArtifacts = managedArtifacts;
        this.useServerSideEncryption = useServerSideEncryption;
        this.flatten = flatten;
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<Entry> {

        @Override
        public String getDisplayName() {
            return "File to upload";
        }

        public ListBoxModel doFillStorageClassItems() {
            ListBoxModel model = new ListBoxModel();
            for (String s : storageClasses) {
                model.add(s, s);
            }
            return model;
        }

        public ListBoxModel doFillSelectedRegionItems() {
            ListBoxModel model = new ListBoxModel();
            for (Regions r : regions) {
                model.add(r.getName(), r.getName());
            }
            return model;
        }

    }

}
