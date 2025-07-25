package com.salmon.test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.UUID;

public class ReportMerger {
    private static final String REPORT_FILE_NAME = "report.js";
    private static final String REPORT_IMAGE_EXTENSION = "png";

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("Please provide the report directory path as an argument.");
            return;
        }
        File reportDirectory = new File(args[0]);
        if (reportDirectory.exists()) {
            ReportMerger merger = new ReportMerger();
            merger.mergeReports(reportDirectory);
        } else {
            System.err.println("Report directory does not exist: " + reportDirectory.getAbsolutePath());
        }
    }

    /**
     * Merge all reports together into a master report in the given reportDirectory.
     *
     * @param reportDirectory the directory containing report files
     * @throws Exception if an error occurs during merging
     */
    public void mergeReports(File reportDirectory) throws Exception {
        Collection<File> existingReports = FileUtils.listFiles(reportDirectory, new String[]{"js"}, true);
        File mergedReport = null;
        for (File report : existingReports) {
            if (REPORT_FILE_NAME.equals(report.getName())) {
                renameEmbeddedImages(report);
                if (mergedReport == null) {
                    FileUtils.copyDirectory(report.getParentFile(), reportDirectory);
                    mergedReport = new File(reportDirectory, REPORT_FILE_NAME);
                } else {
                    mergeFiles(mergedReport, report);
                }
            }
        }
    }

    /**
     * Merge source file into target.
     *
     * @param target the target report file
     * @param source the source report file
     * @throws Exception if an error occurs during merging
     */
    public void mergeFiles(File target, File source) throws Exception {
        Collection<File> embeddedImages = FileUtils.listFiles(source.getParentFile(), new String[]{REPORT_IMAGE_EXTENSION}, true);
        for (File image : embeddedImages) {
            FileUtils.copyFileToDirectory(image, target.getParentFile());
        }
        String targetReport = FileUtils.readFileToString(target, StandardCharsets.UTF_8);
        String sourceReport = FileUtils.readFileToString(source, StandardCharsets.UTF_8);
        FileUtils.writeStringToFile(target, targetReport + sourceReport, StandardCharsets.UTF_8);
    }

    /**
     * Give unique names to embedded images to ensure they aren't lost during merge.
     * Update report file to reflect new image names.
     *
     * @param reportFile the report file to update
     * @throws Exception if an error occurs during renaming
     */
    public void renameEmbeddedImages(File reportFile) throws Exception {
        File reportDirectory = reportFile.getParentFile();
        Collection<File> embeddedImages = FileUtils.listFiles(reportDirectory, new String[]{REPORT_IMAGE_EXTENSION}, true);
        String fileAsString = FileUtils.readFileToString(reportFile, StandardCharsets.UTF_8);
        for (File image : embeddedImages) {
            String curImageName = image.getName();
            String uniqueImageName = UUID.randomUUID().toString() + "." + REPORT_IMAGE_EXTENSION;
            boolean renamed = image.renameTo(new File(reportDirectory, uniqueImageName));
            if (renamed) {
                fileAsString = fileAsString.replace(curImageName, uniqueImageName);
            } else {
                System.err.println("Failed to rename image: " + curImageName);
            }
        }
        FileUtils.writeStringToFile(reportFile, fileAsString, StandardCharsets.UTF_8);
    }
}